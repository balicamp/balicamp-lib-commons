package id.co.sigma.finance.report.domain;

import java.math.BigDecimal;

/**
 * wrapper dari data. versi ini dengan beberapa column
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class NColumnsReportExecutionResult {
	
	
	
	
	
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
	private BigDecimal[] amounts  ; 

	
	
	
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
	/**
	 * nilai dari data
	 */
	public BigDecimal[] getAmounts() {
		return amounts;
	}
	/**
	 * nilai dari data
	 */
	public void setAmounts(BigDecimal[] amounts) {
		this.amounts = amounts;
	}
	
	
}
