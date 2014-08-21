package id.co.sigma.common.server.util.strings.fixedlength.impl;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * format ISO : YYYY-mm-dd
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class IsoDateCustomFixedLengthSerializer extends AbstractCustomFixedLengthSerializer{

	
	private static SimpleDateFormat ISO_FORMATTER = new SimpleDateFormat("yyyy-mm-dd");  
	
	@Override
	public String serialize(Object fieldData, int lengthOfString) {
		if ( fieldData== null || !(fieldData instanceof Date))
			return this.makeRepeatedString(' ', lengthOfString); 
		String tglSaja = ISO_FORMATTER.format((Date)fieldData) ;
		if  ( tglSaja.length()== lengthOfString)
			return tglSaja ; 
		if ( lengthOfString< tglSaja.length())
			return tglSaja.substring(0, lengthOfString ); 
		return tglSaja  + makeRepeatedString(' ',   lengthOfString -  tglSaja.length());
	}

}
