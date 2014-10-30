package id.co.sigma.finance.report.domain;

import java.math.BigDecimal;

/**
 * wrapper dari data  report(tunggal)
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class ReportExecutionResult {
	
	
	
	
	@Override
	public String toString() {
		return "ReportExecutionResult [dataCode=" + dataCode + ", dataLabel="
				+ dataLabel + ", amount=" + amount + "]";
	}
	/**
	 * kode dari data. ini sebagian besar adalah kode GL. sebagai basis dari report
	 */
	private String dataCode ; 
	
	
	
	
	/**
	 * label dari data
	 */
	private String dataLabel ; 
	/**
	 * nilai dari data
	 */
	private BigDecimal amount  ; 

	/**
	 * nilai dari data
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * nilai dari data
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	
	/**
	 * kode dari data. ini sebagian besar adalah kode GL. sebagai basis dari report
	 */
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}
	/**
	 * kode dari data. ini sebagian besar adalah kode GL. sebagai basis dari report
	 */
	public String getDataCode() {
		return dataCode;
	}
	/**
	 * label dari data
	 */
	public void setDataLabel(String dataLabel) {
		this.dataLabel = dataLabel;
	}
	/**
	 * label dari data
	 */
	public String getDataLabel() {
		return dataLabel;
	}
}
