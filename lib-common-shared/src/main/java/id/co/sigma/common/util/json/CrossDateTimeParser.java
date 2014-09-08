package id.co.sigma.common.util.json;

import java.util.Date;



/**
 * date time parser yang cros antara client vs server
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface CrossDateTimeParser {

	
	
	/**
	 * format dari date menjadi string format. pls refer pada
	 * @param date tanggal yang akan di format menjadi string
	 * @param dateFormat format dari date 
	 **/
	public String format (Date date , String dateFormat)  ;
	
	
	
	/**
	 * worker utnuk mem-parse dari string menjadi date
	 * @param dateAsString date yang di jadikan string. ini apa yang akan di jadikan date
	 * @param dateFormat format dari date
	 **/
	public Date parse (String dateAsString , String dateFormat) throws Exception ; 
}
