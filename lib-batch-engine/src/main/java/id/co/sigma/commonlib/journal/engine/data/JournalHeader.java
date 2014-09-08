package id.co.sigma.commonlib.journal.engine.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * data header journal
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class JournalHeader {
	
	
	
	/**
	 * nama table header jurnal
	 **/
	public static final String TABLE_NAME="t_jrn_header"; 
	
	
	/**
	 * ID dari journal Header
	 **/
	private Long id ; 
	
	/**
	 * tanggal accounting dari data akuntansi
	 **/
	private Date accountingDate ; 
	
	
	
	/**
	 * ID dari journal rule, mana yang berkorelasi dengan. master nya yang mana
	 **/
	private Long journalRuleId ; 
	/**
	 * tanggal aplikasi. ini untuk check tgl applikasi
	 **/
	private Date applicationDate ; 
	/**
	 * total debet
	 **/
	private BigDecimal totalDebet ;
	
	/**
	 * total credit
	 **/
	private BigDecimal totalCredit ; 
	
	
	
	/**
	 * kode periode akuntansi data
	 **/
	private String accountingPeriodCode ;
	
	
	/**
	 * nomor referensi data
	 **/
	private String reffNumber ; 
	
	
	/**
	 * data tambahan 1
	 **/
	private String additionalData1 ;
	
	
	/**
	 * data tambahan 2
	 **/
	private String additionalData2 ;
	
	
	/**
	 * kode unit dati jurnal
	 **/
	private String branchCode ; 
	
	
	/**
	 * id dari group
	 **/
	private Long batchGroupId ; 
	
	/**
	 * data details dari journal
	 **/
	private List<JournalDetail> details = new ArrayList<JournalDetail>(); 
	
	
	
	/**
	 * kode periode akuntansi data
	 **/
	public String getAccountingPeriodCode() {
		return accountingPeriodCode;
	}
	/**
	 * kode periode akuntansi data
	 **/
	public void setAccountingPeriodCode(String accountingPeriodCode) {
		this.accountingPeriodCode = accountingPeriodCode;
	}
	
	/**
	 * data details dari journal
	 **/
	public void setDetails(List<JournalDetail> details) {
		this.details = details;
	}
	/**
	 * data details dari journal
	 **/
	public List<JournalDetail> getDetails() {
		return details;
	}
	
	/**
	 * tanggal accounting dari data akuntansi
	 **/
	public void setAccountingDate(Date accountingDate) {
		this.accountingDate = accountingDate;
	}
	/**
	 * tanggal accounting dari data akuntansi
	 **/
	public Date getAccountingDate() {
		return accountingDate;
	}
	
	/**
	 * tanggal aplikasi. ini untuk check tgl applikasi
	 **/
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	/**
	 * tanggal aplikasi. ini untuk check tgl applikasi
	 **/
	public Date getApplicationDate() {
		return applicationDate;
	}
	
	/**
	 * total debet
	 **/
	public void setTotalDebet(BigDecimal totalDebet) {
		this.totalDebet = totalDebet;
	}
	/**
	 * total credit
	 **/
	public BigDecimal getTotalCredit() {
		return totalCredit;
	}
	
	/**
	 * total credit
	 **/
	public void setTotalCredit(BigDecimal totalCredit) {
		this.totalCredit = totalCredit;
	}
	/**
	 * ID dari journal Header
	 **/
	public Long getId() {
		return id;
	}
	/**
	 * ID dari journal Header
	 **/
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * total debet
	 **/
	public BigDecimal getTotalDebet() {
		return totalDebet;
		
	}
	/**
	 * nomor referensi data
	 **/
	public String getReffNumber() {
		return reffNumber;
	}
	/**
	 * nomor referensi data
	 **/
	public void setReffNumber(String reffNumber) {
		this.reffNumber = reffNumber;
	}
	
	
	/**
	 * kode unit dati jurnal
	 **/
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	/**
	 * kode unit dati jurnal
	 **/
	public String getBranchCode() {
		return branchCode;
	}
	
	/**
	 * data tambahan 1
	 **/
	public String getAdditionalData1() {
		return additionalData1;
	}
	/**
	 * data tambahan 1
	 **/
	public void setAdditionalData1(String additionalData1) {
		this.additionalData1 = additionalData1;
	}
	/**
	 * data tambahan 2
	 **/
	public String getAdditionalData2() {
		return additionalData2;
	}
	/**
	 * data tambahan 2
	 **/
	public void setAdditionalData2(String additionalData2) {
		this.additionalData2 = additionalData2;
	}
	
	/**
	 * id dari group
	 **/
	public void setBatchGroupId(Long batchGroupId) {
		this.batchGroupId = batchGroupId;
	}
	/**
	 * id dari group
	 **/
	public Long getBatchGroupId() {
		return batchGroupId;
	}
	
	/**
	 * ID dari journal rule, mana yang berkorelasi dengan. master nya yang mana
	 **/
	public void setJournalRuleId(Long journalRuleId) {
		this.journalRuleId = journalRuleId;
	}
	/**
	 * ID dari journal rule, mana yang berkorelasi dengan. master nya yang mana
	 **/
	public Long getJournalRuleId() {
		return journalRuleId;
	}
	
	
	@Override
	public String toString() {
		return "JournalHeader [id=" + id + ", accountingDate=" + accountingDate
				+ ", applicationDate=" + applicationDate + ", totalDebet="
				+ totalDebet + ", totalCredit=" + totalCredit
				+ ", accountingPeriodCode=" + accountingPeriodCode
				+ ", reffNumber=" + reffNumber + ", additionalData1="
				+ additionalData1 + ", additionalData2=" + additionalData2
				+ ", branchCode=" + branchCode + ", batchGroupId="
				+ batchGroupId +  "]";
	}
	
	
	
	
	
	
}
