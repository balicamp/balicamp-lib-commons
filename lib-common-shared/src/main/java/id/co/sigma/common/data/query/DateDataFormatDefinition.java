package id.co.sigma.common.data.query;



/**
 * medadata untuk date formatting
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class DateDataFormatDefinition extends BaseDataFormatDefinition{
	
	
	
	/**
	 * format dari tanggal
	 **/
	private String dateFormat ; 
	
	
	/**
	 * format dari tanggal
	 **/
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	/**
	 * format dari tanggal
	 **/
	public String getDateFormat() {
		return dateFormat;
	}

}
