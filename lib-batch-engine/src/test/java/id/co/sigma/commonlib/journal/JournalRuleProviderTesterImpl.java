package id.co.sigma.commonlib.journal;

import java.util.ArrayList;

import id.co.sigma.commonlib.journal.engine.IJournalRuleProvider;
import id.co.sigma.commonlib.journal.metadata.JournalRuleDetail;
import id.co.sigma.commonlib.journal.metadata.JournalRuleHeader;
import id.co.sigma.commonlib.journal.metadata.JournalRuleMapper;

public class JournalRuleProviderTesterImpl implements IJournalRuleProvider{

	

	

	@Override
	public JournalRuleHeader getJournalRuleHeader(Long cnfId) {
		
		if ( cnfId== null){
			System.err.println("configuration id null");
			return null ; 
			
		}
		if ( cnfId.longValue()==1L)
			return getSimpleRule(); 
		return getComplexRule(); 
	}
	
	
	private JournalRuleHeader getComplexRule(){
		JournalRuleHeader  h = new JournalRuleHeader(); 
		h.setSelectStatement(
				"select " +
				"	now() ACCT_DATE , now() APP_DATE,   kode, " +
				"	sum(amt_1) amt_1 , sum(amt_2) amt_2 ," +
				"	'SMB-'|| kode reff_no  , :ID_BATCH  ext_data1 ," +
				"	'201303' ACT_PERIOD  , :KODE_UNIT  KODE_UNIT" +
				" from " +
				"	trx_jrn2   " +
				" where " +
				"	batch_id=:ID_BATCH and kode_unit=:KODE_UNIT group by kode ");
		h.setAccountingDateColumnName("ACCT_DATE");
		h.setApplicationDateColumnName("APP_DATE");
		h.setReffNumberColumnName("reff_no"); 
		h.setAdditionalData1ColumnName("ext_data1"); 
		h.setAccountingPeriodColumName("ACT_PERIOD");
		h.setUnitCodeColumnName("KODE_UNIT"); 
		
		JournalRuleDetail d1 = new JournalRuleDetail(); 
		d1.setGlCode("110501510B00"); 
		d1.setGlPositionCode("D"); 
		d1.setValueSourceColumn("amt_1"); 
		
		JournalRuleDetail d2 = new JournalRuleDetail();
		JournalRuleMapper a = new JournalRuleMapper("kode");
		
		a.appendGLMapper("B-3","110501270C00");
		a.appendGLMapper("B-9","110501280000");
		a.appendGLMapper("B-5","110501280A00");
		a.appendGLMapper("B-4","110501280B00");
		a.appendGLMapper("B-1","110501290000");
		a.appendGLMapper("B-6","110501290A00");
		a.appendGLMapper("B-8","110501290B00");
		a.appendGLMapper("B-7","110501290C00");
		a.appendGLMapper("B-2","110501300000");

		
		
		d2.setJournalRuleMapper(a);  
		d2.setGlPositionCode("C"); 
		d2.setValueSourceColumn("amt_2");
		
		
		
		ArrayList<JournalRuleDetail> dets = new ArrayList<JournalRuleDetail>();
		dets.add(d1); 
		dets.add(d2); 
		
		JournalRuleDetail arr[] =new JournalRuleDetail[dets.size()];
		dets.toArray(arr); 
		h.setRuleDetails(arr);
		
		
		
		return h;
	}
	
	
	
	private JournalRuleHeader getSimpleRule() {
		JournalRuleHeader  h = new JournalRuleHeader(); 
		h.setSelectStatement("select now() ACCT_DATE , now() APP_DATE,   sum(amt_1) amt_1 , sum(amt_2) amt_2  from trx_jrn1 where batch_id=:ID_BATCH and kode_unit=:KODE_UNIT");
		h.setAccountingDateColumnName("ACCT_DATE");
		h.setApplicationDateColumnName("APP_DATE");
		
		
		JournalRuleDetail d1 = new JournalRuleDetail(); 
		d1.setGlCode("110501510B00"); 
		d1.setGlPositionCode("D"); 
		d1.setValueSourceColumn("amt_1"); 
		
		JournalRuleDetail d2 = new JournalRuleDetail(); 
		d2.setGlCode("110501254C00"); 
		d2.setGlPositionCode("C"); 
		d2.setValueSourceColumn("amt_2");
		
		
		
		ArrayList<JournalRuleDetail> dets = new ArrayList<JournalRuleDetail>();
		dets.add(d1); 
		dets.add(d2); 
		
		JournalRuleDetail arr[] =new JournalRuleDetail[dets.size()];
		dets.toArray(arr); 
		h.setRuleDetails(arr);
		
		
		
		return h;
	}

	
	
	
}
