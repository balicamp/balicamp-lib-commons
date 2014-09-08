package id.co.sigma.commonlib.journal.metadata;

import id.co.sigma.commonlib.journal.engine.data.SourceJournalRawData;

import java.util.Arrays;
import java.util.Date;




/**
 * journal rule header
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class JournalRuleHeader {
	
	
	/**
	 * ID dari rule
	 **/
	private Long ruleId  ;
	/**
	 * statement select untuk membaca data
	 **/
	private String selectStatement ; 
	
	/**
	 * nama field hasil select, untuk tgl akuntansi dari data.
	 **/
	private String accountingDateColumnName ; 
	
	
	
	
	
	
	/**
	 * nama column untuk reff number. ini reference transaction
	 **/
	private String reffNumberColumnName ; 
	
	
	/**
	 * nama column untuk kode unit
	 **/
	private String unitCodeColumnName ; 
	
	
	/**
	 * nama column periode akuntansi data
	 **/
	private String accountingPeriodColumName ; 
	
	
	/**
	 * nama column untuk additional data 1, additional data bisa bebas terserah implementer
	 **/
	private String additionalData1ColumnName ; 
	
	
	
	/**
	 * data tambahan 2
	 **/
	private String additionalData2ColumnName ;
	
	
	
	
	
	/**
	 * nama field untuk tgl aplikasi
	 **/
	private String applicationDateColumnName ;
	
	
	/**
	 * journal Rule details
	 **/
	private JournalRuleDetail[] ruleDetails ;
	
	/**
	 * data tambahan 2
	 **/
	public void setAdditionalData2ColumnName(String additionalData2ColumnName) {
		this.additionalData2ColumnName = additionalData2ColumnName;
	}
	/**
	 * data tambahan 2
	 **/
	public String getAdditionalData2ColumnName() {
		return additionalData2ColumnName;
	}
	
	/**
	 * nama column untuk additional data 1, additional data bisa bebas terserah implementer
	 **/
	public String getAdditionalData1ColumnName() {
		return additionalData1ColumnName;
	}
	
	
	/**
	 * nama column untuk additional data 1, additional data bisa bebas terserah implementer
	 **/
	public void setAdditionalData1ColumnName(String additionalData1ColumnName) {
		this.additionalData1ColumnName = additionalData1ColumnName;
	}
	
	/**
	 * nama column untuk reff number. ini reference transaction
	 **/
	public String getReffNumberColumnName() {
		return reffNumberColumnName;
	}
	/**
	 * nama column untuk reff number. ini reference transaction
	 **/
	public void setReffNumberColumnName(String reffNumberColumnName) {
		this.reffNumberColumnName = reffNumberColumnName;
	}
	/**
	 * journal Rule details
	 **/
	public JournalRuleDetail[] getRuleDetails() {
		return ruleDetails;
	}

	/**
	 * journal Rule details
	 **/
	public void setRuleDetails(JournalRuleDetail[] ruleDetails) {
		this.ruleDetails = ruleDetails;
	}


	/**
	 * mengambil tgl accounting dari raw data
	 **/
	public Date getAccountingDate(SourceJournalRawData rawData){
		return getDate(rawData ,  accountingDateColumnName); 
	}
	
	
	public Date getApplicationDate(SourceJournalRawData rawData) {
		return getDate(rawData ,  applicationDateColumnName); 
	}
	
	
	
	
	/**
	 * additional data 1
	 **/
	public String getAdditionalData1 (SourceJournalRawData rawData) {
		return getDataAsString(rawData, additionalData1ColumnName); 
	}
	
	
	
	/**
	 * refference number dari data jurnal
	 **/
	public String getReffNumber (SourceJournalRawData rawData) {
		return getDataAsString(rawData, reffNumberColumnName);
	}
	
	/**
	 * additional data 2
	 **/
	public String getAdditionalData2 (SourceJournalRawData rawData) {
		return getDataAsString(rawData, additionalData2ColumnName); 
	}
	
	
	
	/**
	 * kode unit dari data yang di baca
	 **/
	public String getUnitCode (SourceJournalRawData rawData) {
		return getDataAsString(rawData, unitCodeColumnName);
		
	}
	
	
	
	
	
	/**
	 * period akuntansi dari data
	 **/
	public String getAccountingPeriod(SourceJournalRawData rawData) {
		return getDataAsString(rawData, this.accountingPeriodColumName);
	}
	
	
	
	/**
	 * membaca date dari hasil select
	 **/
	protected Date getDate(SourceJournalRawData rawData , String key ) {
		if ( key==null||key.length()==0)
			return null ; 
		Object swap = rawData.get(key); 
		if ( swap ==null)
			return null ; 
		if ( !(swap instanceof Date)){
			//FIXME: kalau string mau di ambil juga ndak, sementara di set return null ; 
			return null ;
		}
		return (Date)swap ; 
	}
	
	
	/**
	 * worker untuk membaca data sebagai string dari raw data
	 **/
	protected String getDataAsString (SourceJournalRawData rawData , String key ) {
		if ( key==null||key.length()==0)
			return null ; 
		try {
			Object swap = rawData.get(key); 
			return swap.toString(); 
			
		} catch (Exception e) {
			return null ; 
		}
	}
	
	
	/**
	 * nama field hasil select, untuk tgl akuntansi dari data.
	 **/
	public void setAccountingDateColumnName(String accountingDateColumnName) {
		this.accountingDateColumnName = accountingDateColumnName;
	}
	/**
	 * nama field hasil select, untuk tgl akuntansi dari data.
	 **/
	public String getAccountingDateColumnName() {
		return accountingDateColumnName;
	}
	/**
	 * nama field untuk tgl aplikasi
	 **/
	public String getApplicationDateColumnName() {
		return applicationDateColumnName;
	}
	/**
	 * nama field untuk tgl aplikasi
	 **/
	public void setApplicationDateColumnName(String applicationDateColumnName) {
		this.applicationDateColumnName = applicationDateColumnName;
	}
	
	
	/**
	 * statement select untuk membaca data
	 **/
	public void setSelectStatement(String selectStatement) {
		this.selectStatement = selectStatement;
	}
	
	/**
	 * statement select untuk membaca data
	 **/
	public String getSelectStatement() {
		return selectStatement;
	}
	/**
	 * ID dari rule
	 **/
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * ID dari rule
	 **/
	public Long getRuleId() {
		return ruleId;
	}
	
	/**
	 * nama column periode akuntansi data
	 **/
	public void setAccountingPeriodColumName(String accountingPeriodColumName) {
		this.accountingPeriodColumName = accountingPeriodColumName;
	}
	/**
	 * nama column periode akuntansi data
	 **/
	public String getAccountingPeriodColumName() {
		return accountingPeriodColumName;
	}
	
	/**
	 * nama column untuk kode unit
	 **/
	public void setUnitCodeColumnName(String unitCodeColumnName) {
		this.unitCodeColumnName = unitCodeColumnName;
	}
	
	/**
	 * nama column untuk kode unit
	 **/
	public String getUnitCodeColumnName() {
		return unitCodeColumnName;
	}
	@Override
	public String toString() {
		return "JournalRuleHeader [ruleId=" + ruleId + ", selectStatement="
				+ selectStatement + ", accountingDateColumnName="
				+ accountingDateColumnName + ", reffNumberColumnName="
				+ reffNumberColumnName + ", unitCodeColumnName="
				+ unitCodeColumnName + ", accountingPeriodColumName="
				+ accountingPeriodColumName + ", additionalData1ColumnName="
				+ additionalData1ColumnName + ", additionalData2ColumnName="
				+ additionalData2ColumnName + ", applicationDateColumnName="
				+ applicationDateColumnName + ", ruleDetails="
				+ Arrays.toString(ruleDetails) + "]";
	}
}
