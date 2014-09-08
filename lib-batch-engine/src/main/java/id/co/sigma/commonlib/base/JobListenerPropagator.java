package id.co.sigma.commonlib.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * bridge untuk meneruskan job listener ke user. kalau user dari library memerlukan pekerjaan tambahan before + after
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class JobListenerPropagator implements JobExecutionListener , ApplicationContextAware{
	
	
	/**
	 * ini untuk menaruh bean handler 
	*/
	public static final String JOB_LISTENER_BEAN_NAME_KEY="jobListenerBeanName" ; 
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(JobListenerPropagator.class); 
	
	private ApplicationContext appContext; 

	@Override
	public void beforeJob(JobExecution jobExecution) {
		ICustomJobExecutionListener job =  getActualBeanHandler(jobExecution);
		if ( job== null)
			return ; 
		job.beforeJob(jobExecution);
		
	}
	
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		ICustomJobExecutionListener job =  getActualBeanHandler(jobExecution);
		if ( job== null)
			return ; 
		int writedCount = 0  ; 
		try {
			writedCount =  jobExecution.getExecutionContext().getInt(ICustomJobExecutionListener.OUTPUT_WRITED_COUNT_KEY);
		} catch (Exception e) {
			logger.error("gagal membaca key  : " + ICustomJobExecutionListener.OUTPUT_WRITED_COUNT_KEY + ", error messgae : " + e.getMessage() );
			writedCount = 0 ; 
		} 
		job.afterJob(writedCount, jobExecution);
	}
	
	
	private ICustomJobExecutionListener getActualBeanHandler (JobExecution jobExecution){
		String beanName = jobExecution.getJobParameters().getString(JOB_LISTENER_BEAN_NAME_KEY); 
		if ( beanName== null|| beanName.isEmpty())
			return null ;
		if ( !appContext.containsBean(beanName))
			return null ;
		Object swap = appContext.getBean(beanName);
		if ( swap == null || !(swap instanceof ICustomJobExecutionListener))
			return null; 
		return (ICustomJobExecutionListener) swap ; 
	}
	
	

	

	@Override
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
		this.appContext = appContext; 
	}

}
