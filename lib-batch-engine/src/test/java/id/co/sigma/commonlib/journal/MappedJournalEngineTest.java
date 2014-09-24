package id.co.sigma.commonlib.journal;

import id.co.sigma.commonlib.journal.engine.JournalConstant;
import id.co.sigma.commonlib.journal.engine.io.reader.ParameterKey;
import id.co.sigma.commonlib.util.IQueryParameterHolder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
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
		"classpath*:/appContext-journal-test-context.xml" , 
		"classpath*:META-INF/spring/journal-engine-context.xml"  , 
		"classpath*:META-INF/spring/batch-engine-infrastructure-context.xml" , 
		"classpath*:/appContext-datasourcetest-context.xml"
		})
@RunWith(value=SpringJUnit4ClassRunner.class)
public class MappedJournalEngineTest {
	
	
	@Autowired
	@Qualifier(value="batchEngineJobLauncher")
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier(value="run_journaling_rule_batch")
	private Job job;
	
	
	@Autowired
	private IQueryParameterHolder queryParameterHolder;
	
	@Test
	public void journalTesterNested(){
		
		
		ParameterKey groupKey =  queryParameterHolder.generateKey();
		queryParameterHolder.put(groupKey, "ID_BATCH", 1L); 
		queryParameterHolder.put(groupKey, "KODE_UNIT", "0361");
		
		JobParametersBuilder paramBld = new JobParametersBuilder(); 
		paramBld.addLong(JournalConstant.JOURNAL_CONFIGURATION_ID_KEY_NAME	, 8L); 
		paramBld.addString(JournalConstant.QUERY_PARAM_GROUP_KEY, groupKey.getInternalKey());
		
		
		try {
			JobExecution jobex = jobLauncher.run(job ,  paramBld.toJobParameters());
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//paramBld.addLong(key, parameter)
	
	}


}
