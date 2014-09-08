package id.co.sigma.commonlib.exportengine;

import id.co.sigma.commonlib.exportengine.data.IExportToTextFileMetadataProvider;
import id.co.sigma.commonlib.journal.engine.JournalConstant;
import id.co.sigma.commonlib.journal.engine.io.reader.ParameterKey;
import id.co.sigma.commonlib.util.IQueryParameterHolder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@ContextConfiguration(locations={
		"classpath*:/launch-context.xml" ,
		"classpath*:META-INF/spring/batch-engine-infrastructure-context.xml" ,
		"classpath*:/appContext-exportenginetest-context.xml",
		"classpath*:META-INF/spring/export-engine-context.xml",
		
		"classpath*:/appContext-datasourcetest-context.xml"
		})
@RunWith(value=SpringJUnit4ClassRunner.class)
public class ExportEngineTest {
	
	
	
	
	@Autowired
	private IExportToTextFileMetadataProvider exportToTextFileMetadataProvider ; 
	
	@Autowired
	@Qualifier(value="batchEngineJobLauncher")
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier(value="table_to_textfile_job")
	private Job job;
	
	
	@Autowired
	private IQueryParameterHolder queryParameterHolder;
	
	@Test
	public void runTest() {
		System.out.println("siap");
		
		
		ParameterKey groupKey =  queryParameterHolder.generateKey();
		queryParameterHolder.put(groupKey, "ID_BATCH", 1L); 
		queryParameterHolder.put(groupKey, "KODE_UNIT", "0361");
		
		
		JobParametersBuilder jb = new JobParametersBuilder();
		jb.addString("configurationId", "1");
		jb.addString(JournalConstant.QUERY_PARAM_GROUP_KEY, groupKey.getInternalKey());
		 
		

		try {
			JobParameters param =  jb.toJobParameters();
			
			System.out.println(  param.toString());
			JobExecution jobex = jobLauncher.run(job ,  param);
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
