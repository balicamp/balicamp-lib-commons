package id.co.sigma.common.server.util.strings.fixedlength.impl;


/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ZeroLeadedItegerCustomFixedLengthSerializer extends AbstractCustomFixedLengthSerializer{

	@Override
	public String serialize(Object fieldData, int lengthOfString) {
		if ( fieldData== null || 
				!(fieldData instanceof Integer))
			return this.makeRepeatedString('0', lengthOfString); 
		String tglSaja = (Integer)fieldData + "" ;
		tglSaja = tglSaja.replaceAll("\\.", ""); 
		if  ( tglSaja.length()== lengthOfString)
			return tglSaja ; 
		if ( lengthOfString< tglSaja.length())
			return tglSaja.substring(0, lengthOfString ); 
		return  makeRepeatedString('0',   lengthOfString -  tglSaja.length()) + tglSaja  ;	
		
	}

}
