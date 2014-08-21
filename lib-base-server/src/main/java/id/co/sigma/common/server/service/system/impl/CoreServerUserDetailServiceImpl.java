package id.co.sigma.common.server.service.system.impl;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import id.co.sigma.common.server.data.security.SimpleUserData;
import id.co.sigma.common.server.data.security.UserLoginNotificationData;
import id.co.sigma.common.server.service.AbstractService;
import id.co.sigma.common.server.service.system.AuthorityTranslator;
import id.co.sigma.common.server.service.system.ICoreServerUserService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;




/**
 * worker untuk load user data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a> 
 * @version $Id
 * 
 **/
public class CoreServerUserDetailServiceImpl extends AbstractService implements UserDetailsService , InitializingBean, ICoreServerUserService{

	
	
	private List<UserLoginNotificationData> authenticatedUsers = new ArrayList<UserLoginNotificationData>();
	
	
	@Autowired
	protected HttpServletRequest  request ;
	
	@Autowired
	protected AuthorityTranslator authorityTranslator; 
	
	
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// cek dari array 
		if ( this.authenticatedUsers.isEmpty())
			return null ; 
		for ( UserLoginNotificationData scn : authenticatedUsers){
			if ( username.equalsIgnoreCase(  scn.getUserName())){
				return validateAndBuildUserData(scn); 
			}
		}
		return null;
	}
	
	
	
	/**
	 * validasi user : username , host
	 **/
	protected SimpleUserData validateAndBuildUserData(UserLoginNotificationData userData){
		String remoteHost=  request.getRemoteHost();
		
		
		if (! isSameHost( remoteHost , userData.getUserHost())){
			//FIXME : make event logging?
			System.out.println("user : " + userData.getUserName() + ", mencoba menerobos dari ip : " + remoteHost +", ip di ijinkan :" + userData.getUserHost());
			return null ; 
		}
		SimpleUserData baru = new SimpleUserData(); 
		baru.setBranchCode(userData.getBranchCode());
		baru.setBranchName(userData.getBranchName());
		baru.setEmail(userData.getEmail());
		baru.setFullName(userData.getFullName());
		baru.setUsername(userData.getUserName());
		baru.setUuid(userData.getUuid());
		this.authenticatedUsers.remove(userData);
		baru.setAuthorities(this.authorityTranslator.generateAuthorities(userData) );
		return baru ; 
				 
	} 
	
	
	private boolean isSameHost(String host1 , String host2  ) {
		if (isLocalHost(host1)&&isLocalHost(host2))
			return true ; 
		System.out.println("compare : " + host1 + " vs " + host2);
		return (host1+"").equalsIgnoreCase(host2);
	}
	
	
	private boolean isLocalHost(String host){
		if ( host==null||host.length()==0)
			return false ; 
		return host.contains("localhost")|| host.contains("127.0.0.1")||host.contains("0:0:0:0");
	}

	@Override
	public void pushRegisteredUser(UserLoginNotificationData notificationData) {
		this.authenticatedUsers.add(notificationData);
	}
	
	
	

	

}
