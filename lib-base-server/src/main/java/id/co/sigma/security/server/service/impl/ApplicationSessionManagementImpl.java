package id.co.sigma.security.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.security.ApplicationSessionRegistry;
import id.co.sigma.security.server.CoreServerUserDetail;
import id.co.sigma.security.server.service.IApplicationSessionManagement;

/**
 * handler session management. untuk list, dan kick user
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ApplicationSessionManagementImpl implements IApplicationSessionManagement{
	
	
	protected class SessionAndUserCombiner {
		public String sessionId ; 
		public CoreServerUserDetail user ; 
	}
	
	
	@Autowired
	private SessionRegistry sessionRegistry;

	@Override
	public PagedResultHolder<ApplicationSessionRegistry> getCurrentlyLogedInUser(
			String usernameWildCard, String realNameWildCard, String email,
			int pageSize, int page) {
		
		
		
		
		if ( page<0)
			page = 0 ; 
		
		 
		List<Object> allSessions=sessionRegistry.getAllPrincipals();
		ArrayList<SessionAndUserCombiner> allMatchFoundExcludePagingUsers = new ArrayList<SessionAndUserCombiner>();
		
		if ( allSessions== null||allSessions.isEmpty())
			return null ;
		
		 
		 
		PagedResultHolder<ApplicationSessionRegistry> retval = new PagedResultHolder<ApplicationSessionRegistry>();
		retval.setHoldedData(new ArrayList<ApplicationSessionRegistry>()); 
		
		
		for ( Object scn : allSessions){
			CoreServerUserDetail userData = (CoreServerUserDetail)scn;
			
			List<SessionInformation> infs =  sessionRegistry.getAllSessions(scn, false);
			boolean chk =  isMatch(usernameWildCard, realNameWildCard, email, userData);
			if ( infs== null|| infs.isEmpty())
				continue ; 
			for ( SessionInformation inf  : infs){
				String sessionId = inf.getSessionId();//FIXME: in perlu di ganti dengan real code
				if ( !chk)
					continue ;
				SessionAndUserCombiner a = new SessionAndUserCombiner(); 
				a.user  = userData; 
				a.sessionId =sessionId;
				allMatchFoundExcludePagingUsers.add(a);
			}	 
			 
		}
		if ( allMatchFoundExcludePagingUsers.isEmpty())
			return null; 
		retval.adjustPagination(page, pageSize, allMatchFoundExcludePagingUsers.size());
		int max = retval.getFirstRowPosition() + pageSize; 
		if ( max>  allMatchFoundExcludePagingUsers.size())
			max = allMatchFoundExcludePagingUsers.size(); 
		for ( int i = retval.getFirstRowPosition() ; i< max ; i++){
			SessionAndUserCombiner a = allMatchFoundExcludePagingUsers.get(i);
			retval.getHoldedData().add(translate(a.user	, a.sessionId));
			 
			
		}
		allMatchFoundExcludePagingUsers.clear(); 
		return retval;
	} 
	
	
	
	private ApplicationSessionRegistry translate( CoreServerUserDetail userDetail, String sessionId ){
		ApplicationSessionRegistry retval = new ApplicationSessionRegistry(); 
		retval.setEmail(userDetail.getEmail()); 
		retval.setIpAddress(userDetail.getIpAddress());
		retval.setLoginTime(userDetail.getLoginTime());
		retval.setRealName(userDetail.getFullNameUser());
		retval.setSessionId(sessionId);
		retval.setUserName(userDetail.getUsername());
		return retval ; 
	}
	
	
	/**
	 * ngecek match ndak dengan wild card nya
	 */
	private boolean isMatch ( String usernameWildCard, String realNameWildCard, String email , CoreServerUserDetail userData ){
		if ( (usernameWildCard== null|| usernameWildCard.isEmpty()) &&
				 (realNameWildCard== null|| realNameWildCard.isEmpty())&&
				 (email== null|| email.isEmpty())) 
			return true ; 
		if ( (usernameWildCard!= null&& !usernameWildCard.isEmpty()) ){
			if ( userData.getUsername()== null)
				return false; 
			if ( !userData.getUsername().contains(usernameWildCard))
				return false; 
		}
		
		if ( (realNameWildCard!= null&& !realNameWildCard.isEmpty()) ){
			if ( userData.getFullNameUser()== null)
				return false; 
			if (!userData.getFullNameUser().contains(realNameWildCard)){
				return false; 
			}
		}
		
		if ( (realNameWildCard!= null&& !realNameWildCard.isEmpty()) ){
			if ( userData.getEmail()== null)
				return false; 
			if (! userData.getEmail().equals(email))
				return false; 
		}
		
		return true; 
	}

}
