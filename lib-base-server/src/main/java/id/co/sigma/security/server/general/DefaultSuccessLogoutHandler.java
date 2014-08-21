package id.co.sigma.security.server.general;

import id.co.sigma.common.security.domain.Signon;
import id.co.sigma.security.server.CoreServerUserDetail;
import id.co.sigma.security.server.dao.impl.UserLoginDaoImpl;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

/**
 * Logout handler saat proses logout berhasil
 * @author I Gede Mahendra
 * @since Apr 22, 2013, 11:24:56 AM
 * @version $Id
 */
public class DefaultSuccessLogoutHandler implements LogoutSuccessHandler{

	@Autowired
	private UserLoginDaoImpl userLoginDao;
	
	@Resource(name="defaultApplicationLoginURL")
	private String loginUrl;	

	@Transactional(readOnly=false)
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse respone,Authentication auth) 
			throws IOException,ServletException {
		//respone.sendRedirect(loginUrl);	
		
		try {
			if ( auth== null)
				return ; 
			CoreServerUserDetail userDetail = (CoreServerUserDetail) auth.getPrincipal();
			if(userDetail != null){
				String jsession = userDetail.getUuid();			
				Signon data = userLoginDao.getSignonData(jsession);
				data.setLogoutTime(new Date());
				userLoginDao.update(data);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{	
			try {
				respone.sendRedirect(loginUrl);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
						
		}
	}
}