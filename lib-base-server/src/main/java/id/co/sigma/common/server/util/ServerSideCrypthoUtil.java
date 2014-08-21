package id.co.sigma.common.server.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Server side utilities untuk encrypt/decrypt dengan cipher AES. 
 * 
 * @author <a href="mailto:iwayan.ariagustina@gmail.com">Wayan Ari</a>
 * 
 * date: Jan 28, 2014
 * time: 6:26:29 PM
 *
 */
public class ServerSideCrypthoUtil {
	
	private static final ServerSideCrypthoUtil instance;
	
	static {
		instance = new ServerSideCrypthoUtil();
	}
	
	public static ServerSideCrypthoUtil getInstance() {
		return instance;
	}
	
	/**
	 * Transform dari encrypted AES text menjadi plain text. 
	 * 
	 * @param encrypted Encrypted text
	 * @param keyString AES Key/Password
	 * @return Plain Text
	 * @throws Exception
	 */
	public String aesDecrypt(String encrypted, String keyString) throws Exception {
    	SecretKeySpec key = new SecretKeySpec(keyString.getBytes(), "AES");
    	Cipher cipher = Cipher.getInstance("AES");
    	
    	byte[] input = encrypted.getBytes();
    	
    	cipher.init(Cipher.DECRYPT_MODE, key);
	    byte[] decrypted = cipher.doFinal(input);
	    
    	return new String(decrypted);
    }
	
	/**
	 * Transform dari plain text menjadi encrypted text.
	 * 
	 * @param plain Plain text
	 * @param keyString AES Key/Password
	 * @return Encypted text
	 * @throws Exception
	 */
	public String aesEncrypt(String plain, String keyString) throws Exception {
    	SecretKeySpec key = new SecretKeySpec(keyString.getBytes(), "AES");
    	Cipher cipher = Cipher.getInstance("AES");
    	
    	byte[] input = plain.getBytes();
    	
    	cipher.init(Cipher.ENCRYPT_MODE, key);
	    byte[] encrypted = cipher.doFinal(input);
	    
    	return new String(encrypted);
    }

}
