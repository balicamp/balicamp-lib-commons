package id.co.sigma.commonlib.importengine.definition.src;

import id.co.sigma.commonlib.importengine.exception.DataTypeViolationException;

import java.text.SimpleDateFormat;
import java.util.Date;




/**
 * konverter dengan hasil date
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class DateFileColumnDefinition extends BaseFileColumnDefinition<Date>{

	/**
	 * date formatter
	 **/
	private String dateTimeFormat ; 
	
	
	private SimpleDateFormat formatter ;
	
	
	/**
	 * message kalau data melanggar 
	 **/
	private String invalidDateTypeMessageI18Key;
	
	
	/**
	 * date formatter
	 **/
	public String getDateTimeFormat() {
		return dateTimeFormat;
	}




	/**
	 * date formatter
	 **/
	public void setDateTimeFormat(String dateTimeFormat) {
		this.dateTimeFormat = dateTimeFormat;
		formatter = new SimpleDateFormat(this.dateTimeFormat);
	}





	@Override
	protected Date translateStringToData(String uploadSrcData) throws DataTypeViolationException{
		if ( uploadSrcData==null||uploadSrcData.trim().length()==0)
			return null ; 
		try {
			return formatter.parse(uploadSrcData);
		} catch (Exception e) {
			throw new DataTypeViolationException("tipe data untuk field " + getColumnTitle() + ",ini kemungkinan karena data tidak mengikuti format yang di perlukan.Format yang diperlukan adalah :" + dateTimeFormat, getColumnTitle(), uploadSrcData ,  this.invalidDateTypeMessageI18Key, Date.class.getName());
		}
	}
	
	/**
	 * message kalau data melanggar 
	 **/
	public String getInvalidDateTypeMessageI18Key() {
		return invalidDateTypeMessageI18Key;
	}
	/**
	 * message kalau data melanggar 
	 **/
	public void setInvalidDateTypeMessageI18Key(
			String invalidDateTypeMessageI18Key) {
		this.invalidDateTypeMessageI18Key = invalidDateTypeMessageI18Key;
	}

}
