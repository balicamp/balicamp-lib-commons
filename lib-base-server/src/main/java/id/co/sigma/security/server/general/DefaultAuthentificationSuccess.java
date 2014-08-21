package id.co.sigma.security.server.general;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import id.co.sigma.common.security.domain.Signon;
import id.co.sigma.security.server.dao.impl.UserLoginDaoImpl;
import id.co.sigma.security.server.service.BaseSecurityService;

/**
 * Default authentifikasi saat login berhasil
 * @author I Gede Mahendra
 * @since Apr 22, 2013, 11:14:03 AM
 * @version $Id
 */
public class DefaultAuthentificationSuccess extends BaseSecurityService implements AuthenticationSuccessHandler{

	
	private static final Logger logger = LoggerFactory.getLogger(DefaultAuthentificationSuccess.class); 
	@Autowired
	private UserLoginDaoImpl userLoginDao;
	
	@Resource(name="defaultApplicationURL")
	private String singleApplicationURL;
	
	@Autowired
	@Qualifier(value="transactionManager")
	PlatformTransactionManager transactionManager ; 
	
	@Transactional(readOnly=false)
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse respon,final Authentication auth) throws IOException,ServletException {
		TransactionTemplate tmpl = new TransactionTemplate(this.transactionManager);
		tmpl.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus arg0) {
				Signon data = new Signon();
				data.setApplicationId(1L); //statis karena aplikasi hanya ada 1
				data.setLogonTime(new Date());
				data.setTerminal(request.getRemoteHost());
				data.setUserBrowser(request.getHeader("user-agent"));
				data.setUserId(getSigmaUserDetailFromSecurityContext().getUserInternalId());
				data.setUuid(getSigmaUserDetailFromSecurityContext().getUuid());
													
				try {
					userLoginDao.insert(data);
				} catch (Exception e) {
					logger.error("menulis data signon : " + e.getMessage() , e);
					
				}
				
				return 1;
			}
		});
	
		respon.sendRedirect(singleApplicationURL);
	}
}