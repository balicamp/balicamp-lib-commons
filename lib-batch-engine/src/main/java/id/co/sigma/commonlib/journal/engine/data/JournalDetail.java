package id.co.sigma.commonlib.journal.engine.data;

import java.math.BigDecimal;


/**
 * data detail dari journal
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class JournalDetail {
	
	
	/**
	 * nama table detail jurnal
	 **/
	public static final String TABLE_NAME="t_jrn_detail";
	
	
	/**
	 * ID dari data
	 **/
	private Long id ; 
	
	/**
	 * reference ke header
	 **/
	private Long headerId ; 
	
	
	/**
	 * total amount dari line jurnal
	 **/
	private BigDecimal amount ; 
	
	
	/**
	 * Kode GL
	 **/
	private String glCode ; 
	
	
	
	
	/**
	 * posisi GL, debet atau credit
	 **/
	private String glPositionCode ;
	
	
	/**
	 * nomor urut data jurnal
	 **/
	private Integer sequenceNumber ; 
	
	/**
	 * total amount dari line jurnal
	 **/
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * total amount dari line jurnal
	 **/
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * reference ke header
	 **/
	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}
	/**
	 * reference ke header
	 **/
	public Long getHeaderId() {
		return headerId;
	}
	

	/**
	 * Kode GL
	 **/
	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}
	/**
	 * Kode GL
	 **/
	public String getGlCode() {
		return glCode;
	}
	
	/**
	 * posisi GL, debet atau credit
	 **/
	public String getGlPositionCode() {
		return glPositionCode;
	}
	/**
	 * posisi GL, debet atau credit
	 **/
	public void setGlPositionCode(String glPositionCode) {
		this.glPositionCode = glPositionCode;
	}

	/**
	 * ID dari data
	 **/
	public Long getId() {
		return id;
	}
	/**
	 * ID dari data
	 **/
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * nomor urut data jurnal
	 **/
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**
	 * nomor urut data jurnal
	 **/
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
}
