package id.co.sigma.commonlib.housekeeper.restore;

import java.io.File;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class RestoreJobListener implements JobExecutionListener , ApplicationContextAware{
	
	
	
	private ApplicationContext applicationContext ; 
	
	/**
	 * unzipper
	 **/
	@Autowired
	private ZipDataExtractor dataExtractor ; 

	@Override
	public void beforeJob(JobExecution jobExecution) {
		//1.  cari dulu path zip + folder tujuan unzip dari job parameter 
		try {
			RestoreJobParameter p = new RestoreJobParameter(jobExecution.getJobParameters());
			File zipped = new File( p.getZippedFileAbsolutePath());
			File targetFolder = new File(p.getTargetFolderAbsPath()); 
			RestoreParameterHeader h =  dataExtractor.extractAndReadMetadata(zipped, targetFolder);
			jobExecution.getExecutionContext().put(RestoreParameterHeader.RESTORE_PARAM_KEY, h);
			jobExecution.getExecutionContext().put(RestoreParameterHeader.CURRENT_RESTORE_PARAM_CURRENT_INDEX_KEY, 0);
			jobExecution.getExecutionContext().put(RestoreParameterHeader.CURRENT_RESTORE_PARAM_KEY, h.getDetails()[0]);
			
			
		} catch (Exception e) {
			//FIXME: batalkan saja
			e.printStackTrace();
		} 
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		
		
	}

	@Override
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
		this.applicationContext = appContext ; 
		
	}

}
