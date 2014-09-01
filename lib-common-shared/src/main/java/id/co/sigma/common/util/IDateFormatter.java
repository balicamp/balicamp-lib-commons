package id.co.sigma.common.util;

import java.util.Date;

/**
 * 
 * formatter tanggal ke string. interface untuk penyeragaman eksekusi nya
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public interface IDateFormatter {
	
	
	/**
	 * translate ke dalam string. ini bridge/ faceade proses antara server vs client formating. Karena code client tidak bisa di pakai di server
	 **/
	public String format (Date date) ; 
	
	/**
	 * parse String menjadi string
	 */
	public Date parse ( String dateAsString ) throws Exception ; 

}
