package id.co.sigma.common.data;

import java.util.Date;

/**
 * interface untuk data yang di modify audit
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface IModifyAuditedData {
	
	/**
	 * username yang create
	 **/
	public void setModifiedBy (String username ) ;
	

	public String getModifiedBy (  ) ;
	/**
	 * menaruh waktu create dari data
	 **/
	public void setModifiedOn (Date modifyTime) ; 
	
	public Date getModifiedOn ( ) ;
	
	
	/**
	 * IP address dari creator data. dalam beberapa kasus , info ini di perlukan untuk audit trail. untuk yang lebih extrim
	 **/
	public void setModifiedByIPAddress(String ipAddress);
	
	public String getModifiedByIPAddress( );

}


