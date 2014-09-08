package id.co.sigma.commonlib.housekeeper;

import id.co.sigma.commonlib.base.BaseJobLauncherHelper;
import id.co.sigma.commonlib.housekeeper.restore.RestoreJobParameter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class RestoreEngineJobLauncherHelper {
	
	
	@Autowired
	@Qualifier(value="batchEngineJobLauncher")
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier(value="restore-archiveddb-job")
	private Job job ; 
	
	
	
	private BaseJobLauncherHelper<RestoreJobParameter> jobLauncherWrapper = new BaseJobLauncherHelper<RestoreJobParameter>(){

		@Override
		protected Class<RestoreJobParameter> getParameterTypeClass() {
			return RestoreJobParameter.class;
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
	
	
	
	public JobExecution launchJob( String zippedFile, String destinationFolder ) throws Exception{
		RestoreJobParameter j = new RestoreJobParameter(); 
		j.setTargetFolderAbsPath(destinationFolder);
		j.setZippedFileAbsolutePath(zippedFile);
		return jobLauncherWrapper.launchJob(j); 
		
	}
	
	

}
