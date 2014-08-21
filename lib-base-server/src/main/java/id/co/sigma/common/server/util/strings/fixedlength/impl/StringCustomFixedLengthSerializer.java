package id.co.sigma.common.server.util.strings.fixedlength.impl;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class StringCustomFixedLengthSerializer extends AbstractCustomFixedLengthSerializer{

	@Override
	public String serialize(Object fieldData, int lengthOfString) {
		if ( fieldData== null || !(fieldData instanceof String))
			return makeRepeatedString(' ', lengthOfString); 
		String rawString = (String)fieldData; 
		if ( rawString.length()==lengthOfString)
			return rawString ; 
		if ( rawString.length()> lengthOfString)
			return rawString.substring(0, lengthOfString ); 
		return rawString + makeRepeatedString(' ', lengthOfString - rawString.length());
	}
	
	
	public static void main ( String[] t){
		String a ="123456789"; 
		System.out.println(a.substring(0, 5));
	}

}
