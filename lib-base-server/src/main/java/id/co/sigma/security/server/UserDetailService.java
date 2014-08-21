package id.co.sigma.security.server;

import id.co.sigma.common.security.domain.Application;
import id.co.sigma.common.security.domain.User;
import id.co.sigma.common.security.domain.UserPassword;
import id.co.sigma.common.server.service.AbstractService;
import id.co.sigma.security.server.dao.IUserLoginDao;
import id.co.sigma.security.server.dao.impl.ApplicationDaoImpl;
import id.co.sigma.security.server.dao.impl.UserLoginDaoImpl;


import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * User Detail Service yg berfungsi untuk me-load user dari database
 * @author I Gede Mahendra
 * @since Jan 14, 2013, 3:42:25 PM
 * @version $Id
 */
public class UserDetailService extends AbstractService implements UserDetailsService{

	@Autowired
	private IUserLoginDao userDao;
	
	@Autowired
	private ApplicationDaoImpl applicationDao;
	
	@Autowired
	private UserLoginDaoImpl userPasswordDao;
	
	@Autowired
	private HttpServletRequest request ;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		CoreServerUserDetail userDetail = null;
		try {			
			String applicationId = request.getParameter("j_applicationId");			
			Application application = (Application) applicationDao.find(Application.class, applicationId);
			User userFromDb = userDao.getUserByUsername(username);						
			if(userFromDb != null){
				/*Pencocokan ke tabel sec_password_hs*/
				UserPassword userPassword = userPasswordDao.getPasswordAtHistory(userFromDb.getId());
				if(userPassword != null){
					if(userPassword.getCipherText().equals(userFromDb.getChipperText())){
						userDetail = new CoreServerUserDetail();
						userDetail.setIpAddress(getCurrentUserIpAddress());
						/*Set userDetail sesuai dg data yg diperlukan*/
						setUserDetailFromDb(userDetail, userFromDb, application);
					}else{
						throw new UsernameNotFoundException("password + history not password does not match with current password, password should be resetted");
					}
				}else{
					throw new UsernameNotFoundException("password + history not password does not match with current password, password should be resetted");
				}
			}
			return userDetail;
		} catch (Exception e) {
			return userDetail;
		}				
	}
	
	/**
	 * Set user detail from user database
	 * @param userDetail
	 * @param userFromDb
	 * @param application
	 */
	private void setUserDetailFromDb(CoreServerUserDetail userDetail, User userFromDb, Application application){
		String passwordNoEncript = request.getParameter("j_password");
		
		/*Variable untuk spring security*/
		userDetail.setUsername(userFromDb.getUserCode());
		userDetail.setPassword(userFromDb.getChipperText());
		userDetail.setAccountNonExpired(true);
		userDetail.setAccountNonLocked(true);
		userDetail.setCredentialsNonExpired(true);
		userDetail.setEmail(userFromDb.getEmail());
		if(userFromDb.getActiveFlag() != null){
			if(userFromDb.getActiveFlag().equalsIgnoreCase("A")){
				userDetail.setEnabled(true);
			}else{
				userDetail.setEnabled(false);
			}
		}else{
			userDetail.setEnabled(false);
		}
		
		/*Variable tambahan untuk keperluan aplikasi*/
		userDetail.setApplicationId(application.getId());
		userDetail.setApplicationName(application.getApplicationName());
		userDetail.setApplicationUrl(application.getApplicationUrl());
		userDetail.setApplicationLoginUrl(application.getAutentificationLoginUrl());
		userDetail.setFullNameUser(userFromDb.getRealName());
		userDetail.setPasswordNoHashing(passwordNoEncript);
		userDetail.setUserInternalId(userFromDb.getId());

		if ("Y".equals( userFromDb.getSuperAdmin())){										
			Collection<CoreServerUserAuthority> authority = new ArrayList<CoreServerUserAuthority>();
			authority.add(new CoreServerUserAuthority(CoreServerAuthorityEnum.SUPER_ADMIN));
			userDetail.setAuthorities(authority);					
		}
	}
}