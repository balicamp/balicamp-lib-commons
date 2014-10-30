package id.co.sigma.finance.report.domain;

import java.util.List;

/**
 *
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class SimpleReportDefinitionGroup extends BaseReportDefinitionGroup{
	
	

	
	
	
	

	
	
	
	
	/**
	 * definisi SQL 
	 */
	private List<SimpleReportDefinition> reportDefinitions ;
	/**
	 * nama column untuk amount 
	 */
	private String columnNameForAmount;
	/**
	 * nama column unutk label
	 */
	private String columnNameForLabel;
	/**
	 * nama column untuk kode data
	 */
	private String columnNameForCode; 
	
	
	
	
	/**
	 * xml path untuk amount
	 */
	private String xmlPathForAmount ; 
	
	/**
	 * definisi SQL 
	 */
	public void setReportDefinitions(List<SimpleReportDefinition> reportDefinitions) {
		this.reportDefinitions = reportDefinitions;
	}
	/**
	 * definisi SQL 
	 */
	public List<SimpleReportDefinition> getReportDefinitions() {
		return reportDefinitions;
	}
	/**
	 * nama column untuk amount 
	 */
	public void setColumnNameForAmount(String columnNameForAmount) {
		this.columnNameForAmount = columnNameForAmount;
	}
	/**
	 * nama column untuk amount 
	 */
	public String getColumnNameForAmount() {
		return columnNameForAmount;
	}
	/**
	 * nama column unutk label
	 */
	public void setColumnNameForLabel(String columnNameForLabel) {
		this.columnNameForLabel = columnNameForLabel;
	}
	/**
	 * nama column unutk label
	 */
	public String getColumnNameForLabel() {
		return columnNameForLabel;
	}
	/**
	 * nama column untuk kode data
	 */
	public void setColumnNameForCode(String columnNameForCode) {
		this.columnNameForCode = columnNameForCode;
	}
	/**
	 * nama column untuk kode data
	 */
	public String getColumnNameForCode() {
		return columnNameForCode;
	}
	
	/**
	 * xml path untuk amount
	 */
	public void setXmlPathForAmount(String xmlPathForAmount) {
		this.xmlPathForAmount = xmlPathForAmount;
	}
	/**
	 * xml path untuk amount
	 */
	public String getXmlPathForAmount() {
		return xmlPathForAmount;
	}

}
