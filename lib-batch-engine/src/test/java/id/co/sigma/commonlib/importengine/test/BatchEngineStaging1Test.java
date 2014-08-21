package id.co.sigma.commonlib.importengine.test;

import static org.junit.Assert.assertNotNull;
import id.co.sigma.commonlib.importengine.service.IUploadFileDefinitionService;


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
		"classpath*:/appContext-importenginetest-context.xml",
		"classpath*:/import-engine-context.xml",
		"classpath*:META-INF/spring/batch-engine-infrastructure-context.xml" ,
		"classpath*:META-INF/spring/import-engine-context.xml",
		"classpath*:/appContext-datasourcetest-context.xml"
		})
@RunWith(value=SpringJUnit4ClassRunner.class)
public class BatchEngineStaging1Test {
	
	


	@Autowired
	@Qualifier(value="batchEngineJobLauncher")
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier(value="sigma_read_and_writestaging1")
	private Job job;
	
	/**
	 * upload file column definition
	 **/
	@Autowired
	private IUploadFileDefinitionService uploadFileDefinitionService ;  
	
	
	@Test
	public void testConfig(){
		assertNotNull(job);
	}
	
	
	
	
	@Test
	public void readTester(){
		
		
		
		//Gson gson = new Gson();
		//uploadFileDefinitionService.getFileGroupDefinition("1");
		
		JobParametersBuilder jb = new JobParametersBuilder();
		jb.addString("fileConfigurationId", "1");
		jb.addLong("lineToSkip", 0L);
		jb.addString("absoluteFilePath", "/home/gps/Documents/projects/self-development/shared-libs/svn-checkout/lib-upload-engine/sample-data/batch1.txt");
		//jb.addString("dataDelimiter","|");
		//jb.addString("namesOnDelimitedFile", "NAMA,EMAIL,TAHUN,TGL_GABUNG,POSISI,POSISI_INT");
		jb.addString("insertSqlStatement", "insert into batch1(nama  , email  , tahun   , tgl_gabung  , posisi  ,posisi_int ) " +
				" values(:col1 ,:col2,:col3,:col4,:col5,:col6)");
		
		//jb.addString("jsonFormattedColumnDefs", gson.toJson(columnDefinitions));
		try {
			
			JobExecution jobex  =  jobLauncher.run(job, jb.toJobParameters());
			
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
