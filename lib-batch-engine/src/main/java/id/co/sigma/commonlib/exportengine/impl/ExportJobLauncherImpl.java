package id.co.sigma.commonlib.exportengine.impl;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import id.co.sigma.commonlib.exportengine.IExportJobLauncher;
import id.co.sigma.commonlib.exportengine.io.SingleExportJobParameter;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ExportJobLauncherImpl implements IExportJobLauncher{
	
	
	
	@Autowired
	@Qualifier(value="batchEngineJobLauncher")
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier(value="table_to_textfile_job_job_param_passed")
	private Job job;
	

	@Override
	public JobExecution launchSimpleExport(SingleExportJobParameter parameter)  throws Exception {
		JobParameters param =  parameter.buildJobParameter();
		return jobLauncher.run(job ,  param);
	}

}
