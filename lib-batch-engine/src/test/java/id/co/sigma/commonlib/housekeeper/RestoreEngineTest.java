package id.co.sigma.commonlib.housekeeper;

import id.co.sigma.commonlib.housekeeper.restore.RestoreJobParameter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
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
		"classpath*:META-INF/spring/restoredb-engine-context.xml",
		"classpath*:appContext-datasourcetest-restore-context.xml"
})
@RunWith(value=SpringJUnit4ClassRunner.class)
public class RestoreEngineTest {
	

	
	
	 
	@Autowired
	private RestoreEngineJobLauncherHelper engineJobLauncherHelper ; 
	
	
	
	
	
	
	@Test
	public void test() throws Exception{
		 
		engineJobLauncherHelper.launchJob( "/Users/macbookpro/Documents/projects/shared-lib/gwt-component-joined/lib-batch-engine/sample-files/import.zip" ,  "/tmp/restore-engine2");
		
		
		
	}
	 
	 

}
