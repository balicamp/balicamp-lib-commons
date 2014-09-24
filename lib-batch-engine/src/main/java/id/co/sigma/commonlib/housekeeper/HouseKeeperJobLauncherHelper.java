package id.co.sigma.commonlib.housekeeper;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import id.co.sigma.commonlib.base.BaseJobLauncherHelper;

/**
 *
 * launcher house keeper
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class HouseKeeperJobLauncherHelper  {

	
	
	@Autowired
	@Qualifier(value="batchEngineJobLauncher")
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier(value="extract-insert-into-data")
	private Job job ; 
	
	private BaseJobLauncherHelper<HouseKeeperJobParameter> jobLauncherWrapper = new BaseJobLauncherHelper<HouseKeeperJobParameter>(){

		@Override
		protected Class<HouseKeeperJobParameter> getParameterTypeClass() {
			return HouseKeeperJobParameter.class;
		}

		@Override
		protected Job getJob() {
			return job;
		}

		@Override
		protected JobLauncher getJobLauncher() {
			return jobLauncher;
		}

		 
	}; 
	
	
	
	
	/**
	 * memulai job house keeper
	 **/
	public JobExecution launchJob ( HouseKeeperJobParameters params) throws Exception{
		return jobLauncherWrapper.launchJob(params);
	}
	 
	 

}
