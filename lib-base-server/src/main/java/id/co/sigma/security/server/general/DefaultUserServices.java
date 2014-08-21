package id.co.sigma.security.server.general;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import id.co.sigma.common.security.domain.Signon;
import id.co.sigma.common.security.domain.User;
import id.co.sigma.security.server.CoreServerUserAuthority;
import id.co.sigma.security.server.CoreServerUserDetail;
import id.co.sigma.security.server.dao.IUserLoginDao;
import id.co.sigma.security.server.service.BaseSecurityService;

/**
 * Default proses pengecekan username yg login. Mekanisme defaultnya adalah :
 * <ul>
 * <li>Cek ke tabel sec_user berdasarkan username yg dimasukkan</li>
 * <li>Cek apakah user dalam status aktif atau tidak</li>
 * <li>Cek apakah password yg dimasukkan sudah match dg yg di database</li>
 * <li>Jika 3 kriteria diatas terpenuhi maka tinggal set data yg diperlukan ke dalam object SigmaUserDetail untuk keperluan SpringSecurity</li>
 * </ul>
 * @author I Gede Mahendra
 * @since Apr 22, 2013, 11:35:25 AM
 * @version $Id
 */
public class DefaultUserServices extends BaseSecurityService implements UserDetailsService{

	
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultUserServices.class); 
	@Autowired
	private IUserLoginDao userDao;

	@Autowired
	private HttpServletRequest request ;
		
	@Autowired
	@Qualifier("core-server-PasswordEncoder")
	private MessageDigestPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CoreServerUserDetail userDetail = null;
		try {								 			
			username = username.toUpperCase();
			User userFromDb = userDao.getUserByUsername(username);
			System.out.println("DEBUG USER NAME SECURITY SERVER >>>> username : " + username);
			System.out.println("DEBUG USER NAME SECURITY SERVER >>> user from db : " + userFromDb);
			if(userFromDb != null){
				if("A".equalsIgnoreCase( userFromDb.getActiveFlag() )){					
					String passwordDb = encoder.encodePassword(request.getParameter("j_password"), null);
					System.out.println("DEBUG PASSWORD FROM UI SECURITY SERVER >>> md5 password from ui : " + passwordDb);
					System.out.println("DEBUG PASSWORD DB SECURITY SERVER >>> password from db : " + userFromDb.getChipperText());
					if(passwordDb.equals(userFromDb.getChipperText())){
						userDetail = new CoreServerUserDetail();
						userDetail.setIpAddress(getCurrentUserIpAddress());
						setUserDetailFromDb(userDetail, userFromDb);
						System.out.println("DEBUG USER DETAIL SECURITY SERVER >>> user detail yang di return : " + userDetail);
						return userDetail;
					}else{
						throw new UsernameNotFoundException("Password yang anda masukkan masih salah. Ulangi lagi!!!");
					}
				}else{
					throw new UsernameNotFoundException("Maaf, username : " + username.toUpperCase() + " dalam keadaan tidak aktif");
				}				
			}else{
				throw new UsernameNotFoundException("Maaf, username : " + username.toUpperCase() + " tidak ditemukan atau password anda salah");
			}						
		} catch (Exception e) {
			logger.error("gagal membac adata user : " + username  + ", error : " + e.getMessage() , e);
			throw new UsernameNotFoundException(e.getMessage());
		}			
	}
	
	/**
	 * Set user detail from user database
	 * @param userDetail
	 * @param userFromDb
	 * @param application
	 */
	protected void setUserDetailFromDb(CoreServerUserDetail userDetail, User userFromDb) throws Exception{
		String passwordNoEncript = request.getParameter("j_password");
		
		/*Variable untuk spring security*/
		userDetail.setUsername(userFromDb.getUserCode());
		userDetail.setPassword(userFromDb.getChipperText());
		userDetail.setAccountNonExpired(true);
		userDetail.setAccountNonLocked(true);
		userDetail.setCredentialsNonExpired(true);
		userDetail.setEmail(userFromDb.getEmail());
		userDetail.setCurrentBranchCode(userFromDb.getDefaultBranchCode());
		if(userFromDb.getActiveFlag() != null){
			if("A".equalsIgnoreCase( userFromDb.getActiveFlag()) ){
				userDetail.setEnabled(true);
			}else{
				userDetail.setEnabled(false);
			}
		}else{
			userDetail.setEnabled(false);
		}
		
		/*Variable tambahan untuk keperluan aplikasi*/		
		userDetail.setFullNameUser(userFromDb.getRealName());
		userDetail.setPasswordNoHashing(passwordNoEncript);
		userDetail.setUserInternalId(userFromDb.getId());
		if ( userFromDb.getDefaultApplicationId()!= null)
			userDetail.setApplicationId(userFromDb.getDefaultApplicationId().longValue());		
		
		//set last login ke dalam user detail DTO			
		User user = userDao.getUserByUsername(userFromDb.getUserCode());			
		Signon signonLastLogin = userDao.getLastLoginTime(user.getId());
		Date lastLDate = null;
		if(signonLastLogin != null){
			lastLDate = signonLastLogin.getLogonTime();
		}else{
			lastLDate = new Date();
		}
		
		//set additional data ke dalam DTO
		userDetail.setLastLogin(lastLDate);
		userDetail.setUuid(UUID.randomUUID().toString());
										
		Collection<CoreServerUserAuthority> authority = new ArrayList<CoreServerUserAuthority>();
		CoreServerUserAuthority authorityRoleUser = new CoreServerUserAuthority();
		authorityRoleUser.setAuthority("ROLE_USER");
		authority.add(authorityRoleUser);
		
		userDetail.setAuthorities(authority);
	}
}