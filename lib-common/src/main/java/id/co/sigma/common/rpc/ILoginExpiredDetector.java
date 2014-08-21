package id.co.sigma.common.rpc;

/**
 * class untuk memeriksa login expired. RPC lemah terhadap login expired. dalam kasus login expired, server akan mengirim redirect ke login page. login page berbeda-beda. deteksi ini berbeda per app
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface ILoginExpiredDetector {
	
	
	/**
	 * checker untuk memeriksa login expired atau tidak
	 **/
	public boolean isLoginExpired (Throwable exception) ; 

}
