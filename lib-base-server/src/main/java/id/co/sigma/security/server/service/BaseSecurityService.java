package id.co.sigma.security.server.service;

import id.co.sigma.common.security.dto.UserDetailDTO;
import id.co.sigma.common.server.service.AbstractService;
import id.co.sigma.security.server.CoreServerUserDetail;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Base Security Service
 * @author I Gede Mahendra
 * @since Jan 16, 2013, 1:04:05 PM
 * @version $Id
 */
public class BaseSecurityService extends AbstractService {
	
	/**
	 * Get user detail DTO dari spring security context
	 * @return UserDetailDTO
	 */
	public UserDetailDTO getUserDetailDTOFromSecurityContext(){									
		CoreServerUserDetail user = (CoreServerUserDetail) getSigmaUserDetailFromSecurityContext();
		UserDetailDTO userDetail = null;
		if(user != null){
			userDetail = new UserDetailDTO();
			userDetail.setApplicationId(user.getApplicationId());
			userDetail.setApplicationLoginUrl(user.getApplicationLoginUrl());
			userDetail.setApplicationName(user.getApplicationName());
			userDetail.setApplicationUrl(user.getApplicationUrl());
			userDetail.setFullNameUser(user.getFullNameUser());
			userDetail.setUsername(user.getUsername());
			userDetail.setPassword(user.getPassword());		
			userDetail.setPasswordNoHashing(user.getPasswordNoHashing());
			
			userDetail.setLastLogin(user.getLastLogin());
			userDetail.setUuid(user.getUuid());
			userDetail.setUserId(user.getUserInternalId());		
			userDetail.setCurrentBranch(user.getCurrentBranchCode());
		}		
		return userDetail; 		
	}
	
	/**
	 * Get Sigma user detail dari spring security context
	 * @return SigmaUserDetail
	 */
	public CoreServerUserDetail getSigmaUserDetailFromSecurityContext(){	
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		if ( auth==null)
			return null ; 
		Object swap =  auth.getPrincipal();  
		if (swap==null){
			return null;
		}			
		if (!( swap instanceof CoreServerUserDetail)){
			return null ;
		}
		return (CoreServerUserDetail) swap;		
	}
	
	/**
	 * Get object spring authentification
	 * @return Authentication
	 */
	public Authentication getAuthentificationContext(){
		return SecurityContextHolder.getContext().getAuthentication();
	}
}