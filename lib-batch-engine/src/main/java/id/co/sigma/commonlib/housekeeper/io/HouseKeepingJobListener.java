package id.co.sigma.commonlib.housekeeper.io;


import id.co.sigma.commonlib.base.IJobCompleteHandler;
import id.co.sigma.commonlib.housekeeper.HouseKeeperJobParameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * listener house keeping job handler
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class HouseKeepingJobListener implements JobExecutionListener, ApplicationContextAware{

	private static Logger logger = LoggerFactory.getLogger(HouseKeepingJobListener.class);
	
	private ApplicationContext applicationContext; 
	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		HouseKeeperJobParameters params = new HouseKeeperJobParameters(); 
		try {
			params.fetchParameterFromJobParameters(jobExecution.getJobParameters());
			String beanNames =  params.getHouseKeeperAfterJobHandlerBeanName();
			if ( beanNames!= null && !beanNames.isEmpty()){
				
					if (applicationContext.containsBean(beanNames)){
						Object obj =  applicationContext.getBean(beanNames);
						if(! ( obj== null || !(obj instanceof IJobCompleteHandler))){
							IJobCompleteHandler h = ( IJobCompleteHandler)obj; 
							h.afterJob(jobExecution);
						}	 
						else{
							logger.warn("bean id /name : " +beanNames + " tidak di temukan atau bukan instance of : " + IJobCompleteHandler.class.getName()  +",proses notifikasi tidak di lakukan");
						}
					}
						 
					
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	@Override
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
		applicationContext = appContext ; 
		
	}

}
