package id.co.sigma.common.form;



/**
 * panel yang bisa di close. universal proses close, agar tidak ada circular reference
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface CloseablePanel {
	
	/**
	 * command untuk menutup panel
	 **/
	public void close() ; 

}
