package id.co.sigma.commonlib.journal.engine.io;

import java.math.BigDecimal;

import id.co.sigma.commonlib.journal.engine.IJournalConfigurationAware;
import id.co.sigma.commonlib.journal.engine.IJournalRuleProvider;
import id.co.sigma.commonlib.journal.engine.data.JournalDetail;
import id.co.sigma.commonlib.journal.engine.data.JournalHeader;
import id.co.sigma.commonlib.journal.engine.data.SourceJournalRawData;
import id.co.sigma.commonlib.journal.metadata.JournalRuleDetail;
import id.co.sigma.commonlib.journal.metadata.JournalRuleHeader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * Processor data journal
 **/
public class JournalDataProcessor implements ItemProcessor<SourceJournalRawData, JournalHeader> , IJournalConfigurationAware{
	
	
	/**
	 * kode posisi debet/credit
	 **/
	private static final String DEBET_POSITION_CODE="D"; 
	
	
	private Long journalConfigurationId ; 
	
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(JournalDataProcessor.class); 
	
	 
	
	/**
	 * rules , ini untuk mentranslasikan data menjadi jurnal
	 **/
	private JournalRuleHeader ruleHeader ;
	
	
	/**
	 * id dari group batch
	 **/
	private Long batchGroupId ; 
	
	
	/**
	 * provider journal rule
	 **/
	@Autowired
	private IJournalRuleProvider journalRuleProvider ; 

	@Override
	public JournalHeader process(SourceJournalRawData rawData) throws Exception {
		JournalHeader h = new JournalHeader();
		BigDecimal dTotal = BigDecimal.ZERO ; 
		BigDecimal cTotal = BigDecimal.ZERO ; 
		int seq = 1  ; 
		
		
		JournalRuleHeader hCurrent = getRuleHeader() ; 
		
		
		for ( JournalRuleDetail scn : hCurrent.getRuleDetails()){
			JournalDetail d = new JournalDetail();
			d.setSequenceNumber(seq); 
			seq ++ ; 
			Object swap  =  rawData.get(scn.getValueSourceColumn());
			BigDecimal amt = null ; 
			if ( swap instanceof BigDecimal)
				amt = (BigDecimal)swap ; 
			else if( swap instanceof Number  )
				amt = new BigDecimal(swap.toString());
			d.setAmount(amt); 
			h.getDetails().add(d); 
			if( scn.getGlCode()!=null&& scn.getGlCode().length()>0){
				d.setGlCode(scn.getGlCode()); 
			}else{
				d.setGlCode(scn.getJournalRuleMapper().getGLCode(rawData));
			}
			d.setGlPositionCode(scn.getGlPositionCode());
			if ( DEBET_POSITION_CODE.equalsIgnoreCase(scn.getGlPositionCode()))
				dTotal = dTotal.add(amt); 
			else
				cTotal = cTotal.add(amt); 
			
		
		}
		h.setTotalCredit(cTotal); 
		h.setTotalDebet(dTotal); 
		h.setAccountingDate(hCurrent.getAccountingDate(rawData)); 
		h.setApplicationDate(hCurrent.getApplicationDate(rawData)); 
		h.setAdditionalData1(hCurrent.getAdditionalData1(rawData));
		h.setAdditionalData2(hCurrent.getAdditionalData2(rawData));
		h.setReffNumber(hCurrent.getReffNumber(rawData));
		h.setAccountingPeriodCode(hCurrent.getAccountingPeriod(rawData)); 
		h.setBatchGroupId(batchGroupId) ; 
		h.setJournalRuleId(hCurrent.getRuleId());
		h.setBranchCode(hCurrent.getUnitCode(rawData)); 
		 
		System.out.println(h.toString());
		
		
		return h;
	}

	@Override
	public void setJournalConfigurationId(Long journalConfigurationId) {
		try {
			this.journalConfigurationId = journalConfigurationId ; 
			this.ruleHeader =  this.journalRuleProvider.getJournalRuleHeader(this.journalConfigurationId);
		} catch (Exception e) {
			logger.error("gagal set journal ID menjadi :" + journalConfigurationId  + ", error message :" + e.getMessage() , e); 
		}
		 
	}
	/**
	 * id dari group batch
	 **/
	public Long getBatchGroupId() {
		return batchGroupId;
	}
	/**
	 * id dari group batch
	 **/
	public void setBatchGroupId(Long batchGroupId) {
		this.batchGroupId = batchGroupId;
	}
	
	
	
	
	/**
	 * rule header yang di handler pada current 
	 **/
	public JournalRuleHeader getRuleHeader() {
		return ruleHeader;
	}

}
