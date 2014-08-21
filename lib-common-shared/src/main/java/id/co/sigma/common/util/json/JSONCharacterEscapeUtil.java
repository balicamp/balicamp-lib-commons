package id.co.sigma.common.util.json;

import java.util.HashMap;
import java.util.Map;

public class JSONCharacterEscapeUtil {
	
	private static final JSONCharacterEscapeUtil instance = new JSONCharacterEscapeUtil();
	
	public static JSONCharacterEscapeUtil getInstance() {
		return instance;
	}
	
	private Map<String, String> ENCODE_CHARS;
	private Map<String, String> DECODE_CHAR;
	
	
	private JSONCharacterEscapeUtil() {
		ENCODE_CHARS = new HashMap<String, String>();
		
		ENCODE_CHARS.put("&", "%26");
		ENCODE_CHARS.put("\\+", "%2B");
		
		DECODE_CHAR = new HashMap<String, String>();
		DECODE_CHAR.put("%26", "&");
		DECODE_CHAR.put("%2B", "+");
	}
	
	
	/**
	 * Convert beberapa karakter menjadi URL endcoded karakater.
	 * 
	 * @param string {@link String} yang akan direplace.
	 * 
	 * @return
	 */
	public String convertToUrlEncodedString(String string) {
		String str = string;
		
		for(String c : ENCODE_CHARS.keySet()) {
			str = str.replaceAll(c, ENCODE_CHARS.get(c));
		}
		
		return str;
	}
	
	/**
	 * Convert dari URL encoded karakter menjadi plain karakter.
	 * 
	 * @param string {@link String} yg akan direplace.
	 * @return
	 */
	public String convertToUrlDecodedString(String string) {
		String str = string;
		
		for(String c : DECODE_CHAR.keySet()) {
			str = str.replaceAll(c, DECODE_CHAR.get(c));
		}
		
		return str;
	}

}
