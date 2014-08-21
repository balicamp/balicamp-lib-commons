package id.co.sigma.commonlib.exportengine.data;





/**
 * 
 * 
 * definisi meta data untuk proses download
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class ExportToTextFileMetadata {
	
	
	/**
	 * ID data dalam format string
	 **/
	private String idAsString ; 
	
	/**
	 * SQL Statement
	 **/
	private String selectStatement ;
	
	/**
	 * date formatter
	 **/
	private String dateFormat ;
	/**
	 * berapa desimal yang akan di pertahankan
	 **/
	private int numberOfDecimal ; 
	
	/**
	 * pemisah adalah <h3>.</h3> (dot/titik), kalau false berarti pakai <h3>,</h3>
	 **/
	private boolean useDotDecimalSeparator ;
	
	
	/**
	 * pemisah data. default -> <bold>;</bold>
	 **/
	private String targetFileDelimiter =";"; 
	
	
	
	
	
	/**
	 * columns- columns yang tipe nya decimal, ini untuk proses formatter dengan currency formatter
	 **/
	private String [] decimalColumns ; 
	
	/**
	 * berapa desimal yang akan di pertahankan
	 **/
	public int getNumberOfDecimal() {
		return numberOfDecimal;
	}
	/**
	 * berapa desimal yang akan di pertahankan
	 **/
	public void setNumberOfDecimal(int numberOfDecimal) {
		this.numberOfDecimal = numberOfDecimal;
	}
	/**
	 * pemisah adalah <strong>.</strong> (dot/titik), kalau false berarti pakai <h3>,</h3>
	 **/
	public boolean isUseDotDecimalSeparator() {
		return useDotDecimalSeparator;
	}
	
	/**
	 * pemisah adalah <strong>.</strong> (dot/titik), kalau false berarti pakai <h3>,</h3>
	 **/
	public void setUseDotDecimalSeparator(boolean useDotDecimalSeparator) {
		this.useDotDecimalSeparator = useDotDecimalSeparator;
	}
	
	/**
	 * date formatter
	 **/
	public String getDateFormat() {
		return dateFormat;
	}
	/**
	 * date formatter
	 **/
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * SQL Statement
	 **/
	public String getSelectStatement() {
		return selectStatement;
	}
	/**
	 * SQL Statement
	 **/
	public void setSelectStatement(String selectStatement) {
		this.selectStatement = selectStatement;
	}
	
	/**
	 * pemisah data. default -> <bold>;</bold>
	 **/
	public void setTargetFileDelimiter(String targetFileDelimiter) {
		this.targetFileDelimiter = targetFileDelimiter;
	}
	/**
	 * pemisah data. default -> <bold>;</bold>
	 **/
	public String getTargetFileDelimiter() {
		return targetFileDelimiter;
	}
	
	
	
	/**
	 * ID data dalam format string
	 **/
	public void setIdAsString(String idAsString) {
		this.idAsString = idAsString;
	}
	/**
	 * ID data dalam format string
	 **/
	public String getIdAsString() {
		return idAsString;
	}
	
	
	/**
	 * columns- columns yang tipe nya decimal, ini untuk proses formatter dengan currency formatter
	 **/
	public void setDecimalColumns(String[] decimalColumns) {
		this.decimalColumns = decimalColumns;
	}
	
	/**
	 * columns- columns yang tipe nya decimal, ini untuk proses formatter dengan currency formatter
	 **/
	public String[] getDecimalColumns() {
		return decimalColumns;
	}
	
	
	
	
	
	
	
}
