package id.co.sigma.commonlib.housekeeper;

import id.co.sigma.commonlib.base.SQLParameterHolder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@ContextConfiguration(locations={
		"classpath*:META-INF/spring/batch-engine-infrastructure-context.xml", 
		"classpath*:META-INF/spring/housekeeper-engine-context.xml",
		"classpath*:appContext-datasourcetest-housekeeper-context.xml"
})
@RunWith(value=SpringJUnit4ClassRunner.class)
public class HouseKeeperTest {
	@Autowired
	@Qualifier(value="batchEngineJobLauncher")
	private JobLauncher jobLauncher;

	
	
	
	@Autowired
	@Qualifier(value="extract-insert-into-data")
	private Job job ; 
	
	
	 
	@Autowired
	private HouseKeeperJobLauncherHelper houseKeeperJobLauncherHelper; 
	
	
	
	
	/**
	 * test generate delete statement
	 * @throws Exception 
	 **/
	@Test
	public void makeInsertIntoStatment ( ) throws Exception{
		
		HouseKeeperJobParameter paramParent = new HouseKeeperJobParameter(); 
		
		paramParent.setInsertIntoStatment("insert into test_house_keeping(pk , str_field  ,date_field   ,  timestamp_field ) values (:PK , :STR_FIELD , :DATE_FIELD,:TIMESTAMP_FIELD);");
		paramParent.setTableName("test_house_keeping");
		paramParent.setPrimaryKeyColumns(new String[]{"PK"});
		paramParent.setSelectStatment("SELECT pk , str_field  ,date_field   ,  timestamp_field FROM test_house_keeping where date_field<=:DATE"); //
		paramParent.setQueryParamaters(new SQLParameterHolder[]{
				new SQLParameterHolder("DATE", new Date())
				
		});
		
		HouseKeeperJobParameter paramChild = new HouseKeeperJobParameter();
		paramChild.setInsertIntoStatment("insert into  tes_house_keeping_detail ( pk , parent_id , email ) values (:PK, :PARENT_ID , :EMAIL); ");
		paramChild.setTableName("tes_house_keeping_detail");
		paramChild.setPrimaryKeyColumns(new String[]{"PK"});
		paramChild.setSelectStatment("select   pk , parent_id , email   FROM tes_house_keeping_detail  where email=:EMAIL"); // 
		paramChild.setQueryParamaters(new SQLParameterHolder[]{
				new SQLParameterHolder("EMAIL", "email32@outlook.com")
				
		});
		//
		
		Path tmpDir =  Files.createTempDirectory("_hk_folder");
		final String path = File.createTempFile("_final-", "-balicamp_.zip").getAbsolutePath() ;
		
		ArrayList<HouseKeeperJobParameter> wrapperArray = new ArrayList<HouseKeeperJobParameter>();
		wrapperArray.add(paramChild);
		wrapperArray.add(paramParent);
		
		
		HouseKeeperJobParameters params = new HouseKeeperJobParameters( wrapperArray);
		params.setHouseKeeperAfterJobHandlerBeanName(
				"tes-handler-house-keep"
				);
		params.setFinalCompressedFilePath(path);
		String folderPath = tmpDir.toFile().getAbsolutePath();
		params.setTemporaryOutputFolder(folderPath );
		
		
		
		
		try {
			
			
			System.out.println(  paramParent.toString());
			System.out.println("silakan cek hasil di : " + folderPath);
			JobExecution jobex = houseKeeperJobLauncherHelper.launchJob(params);
			System.err.println("Job ID : " + jobex.getId());
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
