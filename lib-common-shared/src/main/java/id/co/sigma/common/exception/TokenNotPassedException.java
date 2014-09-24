package id.co.sigma.common.exception;



import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * ini kalau spec yang di pakai oleh developer salah. jadinya token sama sekali tidak di passed dari client. untuk kasus 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class TokenNotPassedException extends BaseIsSerializableException implements IsSerializable{
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1245306961380797756L;
	/**
	 * method yang kategori nya valid
	 */
	private String invalidMethod ;
	
	

	public TokenNotPassedException(){
		super() ; 
	}
	
	
	
	/**
	 *@param message message deskripsi
	 *@param invalidMethod method yang di mark invalid
	 */
	public TokenNotPassedException(String message  , String invalidMethod ){
		this.message = message ; 
		this.invalidMethod = invalidMethod ; 
	}
	
	
	/**
	 * method yang kategori nya valid
	 */
	public void setInvalidMethod(String invalidMethod) {
		this.invalidMethod = invalidMethod;
	}
	/**
	 * method yang kategori nya valid
	 */
	public String getInvalidMethod() {
		return invalidMethod;
	}
	
}
