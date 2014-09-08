package id.co.sigma.commonlib.exportengine;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ExportEngineTestListener implements JobExecutionListener , InitializingBean{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("hore memulai job dengan ID : " + jobExecution.getId());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("hore job ID dengan ID : " + jobExecution.getId() + " selesai");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("siap grak!!!");
		
	}

}
