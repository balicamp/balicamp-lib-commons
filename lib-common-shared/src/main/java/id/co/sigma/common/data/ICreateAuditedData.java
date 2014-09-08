package id.co.sigma.common.data;

import java.util.Date;

/**
 * interface untuk object yang ada create audit trail
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface ICreateAuditedData {
	
	
	/**
	 * username yang create
	 **/
	public void setCreatedBy (String username ) ;
	
	public String getCreatedBy () ;
	/**
	 * menaruh waktu create dari data
	 **/
	public void setCreatedOn (Date createTime) ; 
	
	
	public Date getCreatedOn ( ) ;
	
	
	/**
	 * IP address dari creator data. dalam beberapa kasus , info ini di perlukan untuk audit trail. untuk yang lebih extrim
	 **/
	public void setCreatorIPAddress(String ipAddress);
	
	public String getCreatorIPAddress();
	
	

}
