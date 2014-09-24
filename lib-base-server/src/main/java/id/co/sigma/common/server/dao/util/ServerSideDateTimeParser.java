package id.co.sigma.common.server.dao.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import id.co.sigma.common.util.json.CrossDateTimeParser;




/**
 * ini adalah wrapper date time formatter di sisi server
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public final class ServerSideDateTimeParser implements CrossDateTimeParser{
	
	
	
	
	private Hashtable<String	, SimpleDateFormat> indexedDateTimeFormat = new Hashtable<String, SimpleDateFormat>();
	
	
	private static ServerSideDateTimeParser instance ; 
	
	private ServerSideDateTimeParser(){}
	
	public static ServerSideDateTimeParser getInstance() {
		if (instance==null)
			instance = new ServerSideDateTimeParser();
		return instance;
	}
	
	@Override
	public String format(Date date, String dateFormat) {
		if ( date ==null )
			return null ;
		if ( !indexedDateTimeFormat.containsKey( dateFormat))
			indexedDateTimeFormat.put(dateFormat, new SimpleDateFormat(dateFormat)); 
		return indexedDateTimeFormat.get(dateFormat).format(date);
	}
	
	@Override
	public Date parse(String dateAsString, String dateFormat) throws Exception {
		if ( dateAsString==null||dateAsString.length()==0)
			return null ; 
		if ( dateFormat==null||dateFormat.length()==0)
			return null ;
		if ( !indexedDateTimeFormat.contains(dateFormat))
			indexedDateTimeFormat.put(dateFormat, new SimpleDateFormat(dateFormat)); 
		return indexedDateTimeFormat.get(dateFormat).parse(dateAsString);
	}

}
