package id.co.sigma.common.rpc;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * base class untuk async callback. ini sudah di managed kalau expired. sarat nya anda harus menaruh session expired detector
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public abstract class ManagedAsyncCallback<T> implements AsyncCallback<T>{
	
	/**
	 * ini detector kalau login expired. kalau RPC gagal bisa karena login nya sudah habis. page login di masukan lewat variable : 
	 * {@link #LOGIN_SCREEN_URL}
	 **/
	public static ILoginExpiredDetector LOGIN_EXPIRED_DETECTOR  ;
	
	/**
	 * URL untuk login screen
	 **/
	public static String LOGIN_SCREEN_URL  ;
	
	
	private static boolean LOGGING_OUT_IN_PROGRESS = false ; 
	
	
	
	
	/**
	 * pesan yang akan muncul kalau misal login sudah lewat. ini perlu di sesuaikan dengan language
	 **/
	public static String LOGIN_EXPIRED_MESSAGE ="Your login already expired.please relogin";
	
	
	
	@Override
	public final void onFailure(Throwable caught) {
		
		if ( LOGIN_EXPIRED_DETECTOR!= null){
			if(LOGIN_EXPIRED_DETECTOR.isLoginExpired(caught)){
				handleOnLoginExpired(caught);
				 
			}
		}
		customFailurehandler(caught);
	}
	
	
	
	
	/**
	 * handle kalau login sudah expired
	 */
	protected void handleOnLoginExpired (Throwable caught) {
		if ( LOGGING_OUT_IN_PROGRESS){
			return ; 
		}
		try{
			Window.alert(LOGIN_EXPIRED_MESSAGE);
			if ( LOGIN_SCREEN_URL== null|| LOGIN_SCREEN_URL.isEmpty()){
				Window.Location.assign(    "j_spring_security_logout");
			}	
			else
				Window.Location.assign(LOGIN_SCREEN_URL);
			return ;
		}finally{
			LOGGING_OUT_IN_PROGRESS = true ;
			 
		}
	}
	
	
	
	/**
	 * handler untuk failure custom. jadi yang tidak bisa di tangani di base class perlu di handle di sini
	 **/
	protected abstract void customFailurehandler (Throwable caught) ;

}
