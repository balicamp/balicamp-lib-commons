/**
 * 
 */
package id.co.sigma.security.server.service.impl;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.security.domain.Application;
import id.co.sigma.common.security.dto.ApplicationDTO;
import id.co.sigma.security.server.dao.impl.ApplicationDaoImpl;
import id.co.sigma.security.server.service.IApplicationService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dode
 * @version $Id
 * @since Dec 19, 2012, 3:03:57 PM
 */

public class ApplicationServiceImpl implements IApplicationService , InitializingBean , ApplicationContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class); 
	
	public ApplicationServiceImpl(){
		System.out.println("selamat datang, saya adalah class : " + getClass().getName()  );
		System.out.println("saya memerlukan ini : security.securityApplicationId, anda harus mendefine id dari app ini dalam *.properties file anda. dalam kasus 1 app untuk table sec_applications anda cukup men set = 1 ") ; 
		
		
	}

	@Autowired
	private ApplicationDaoImpl applicationDao;
	
	
	
	
	
	private String currentApplicationIdAsString ="1" ;
	/**
	 * Id app current, refer ke {@link #currentApplicationIdAsString}
	 **/
	private Long currentApplicationId = 1L;
	@Override
	public List<Application> getApplicationList() throws Exception {
		return applicationDao.getApplicationList();
	}

	@Transactional(readOnly=true)
	@Override
	public PagedResultHolder<ApplicationDTO> getApplicationList(int pagePosition, int pageSize) throws Exception {
		PagedResultHolder<ApplicationDTO> retval = new PagedResultHolder<ApplicationDTO>();
		List<Application> actualData = new ArrayList<Application>();
		Integer count = 0;
		
		actualData = applicationDao.getApplicationList(pagePosition, pageSize);
		if (actualData != null){
			 count = actualData.size();
		}
		
		List<ApplicationDTO> holderData = new ArrayList<ApplicationDTO>();
		if(count == null || count == 0){
			return null;
		}
		
		for (Application app : actualData) {
			ApplicationDTO dto = new ApplicationDTO();
			dto.setId(app.getId());
			dto.setApplicationCode(app.getApplicationCode());
			dto.setApplicationName(app.getApplicationName());
			dto.setApplicationLoginUrl(app.getAutentificationLoginUrl());
			
			dto.setApplicationUrl(app.getApplicationUrl());			
			if(app.getStatus().equals("A")){
				dto.setIsActive(true);
			}else{
				dto.setIsActive(false);
			}			
			
			
			holderData.add(dto);
		}
		
		retval.setHoldedData(holderData);
		retval.setPage(pagePosition);
		retval.setPageSize(pageSize);
		retval.setTotalData(count);
		retval.adjustPagination();
		
		return retval;
	}

	@Transactional(readOnly=false)
	@Override
	public void saveOrUpdate(ApplicationDTO data) throws Exception {
		Application application = new Application();
		
		if(data.getId() != null){
			application = (Application) applicationDao.find(Application.class, data.getId());
			application.setModifiedBy(data.getUserId());
			application.setModifiedOn(new Date());
			
			/*Set status*/
			if(data.getIsActive()){
				application.setStatus("A");
			}else{
				application.setStatus("N");
			}
		}else{
			application.setCreatedBy(data.getUserId());
			application.setCreatedOn(new Date());
			
			/*Set Default Value*/
			
			application.setStatus("A");
		}
		
		application.setApplicationCode(data.getApplicationCode());
		application.setApplicationName(data.getApplicationName());
		application.setApplicationUrl(data.getApplicationUrl());
		application.setAutentificationLoginUrl(data.getApplicationLoginUrl());
		
		
		
		if(application.getId() != null){
			applicationDao.update(application);			
		}else{
			applicationDao.insert(application);
		}
	}

	@Override
	public Application getCurrentAppDetailData() {
		Long id = new Long(this.currentApplicationIdAsString); 
		try {
			return this.applicationDao.get(Application.class, id);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null ; 
		} 
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			this.currentApplicationId = new Long(currentApplicationIdAsString);
		} catch (Exception e) {
			logger.error("gagal konversi id app , error message:" + e.getMessage(), e); 
		}
		
		
		
	}
	/**
	 * Id app current, refer ke {@link #currentApplicationIdAsString}
	 **/
	public Long getCurrentApplicationId() {
		return currentApplicationId;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		try {
			this.currentApplicationIdAsString = (String) ctx.getBean("security.securityApplicationId"); 
		} catch (Exception e) {
			logger.error("gagal membaca key :security.securityApplicationId dari resource. error : " + e.getMessage() , e );
			this.currentApplicationIdAsString = "1"; 
		}
		
		if ( this.currentApplicationIdAsString == null || this.currentApplicationIdAsString.isEmpty()) 
			this.currentApplicationIdAsString = "1"; 
		
	}
}