package id.co.sigma.common.security;

import java.security.MessageDigest;

/**
 * MD5 Utils
 * @author I Gede Mahendra
 * @since Nov 19, 2012, 3:19:23 PM
 * @version $Id
 */
public class MD5Utils {
	
	private static MD5Utils instance;
	
	/**
	 * Get singleton instance
	 * @return MD5Utils
	 */
	public static MD5Utils getInstance(){
		if(instance == null)
			instance = new MD5Utils();		
		return instance;
	}
	
	/**
	 * Convert to MD5 String
	 * @param md5
	 * @return string
	 */
	public String hashMD5(String md5) {
	   try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(md5.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	        }
	        
	        return sb.toString();
	    } catch (java.security.NoSuchAlgorithmException e) {
	    	
	    }
	    return null;
	}
}