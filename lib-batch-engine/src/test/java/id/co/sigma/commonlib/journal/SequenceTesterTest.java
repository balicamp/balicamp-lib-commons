package id.co.sigma.commonlib.journal;


import id.co.sigma.commonlib.journal.engine.IDataPrimaryKeyProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@ContextConfiguration(locations={
		"classpath*:/launch-context.xml" , 
		"classpath*:/appContext-test-context.xml" , 
		"classpath*:META-INF/spring/journal-engine-context.xml"  , 
		"classpath*:META-INF/spring/journal-engine-infrastructure-context.xml"})
@RunWith(value=SpringJUnit4ClassRunner.class)
public class SequenceTesterTest {
	
	
	@Autowired
	private IDataPrimaryKeyProvider dataPrimaryKeyProvider ; 
	
	@Test
	public void testSequence(){
		Long get1 =  dataPrimaryKeyProvider.getDataKey("m_upld_txt_file_col", 1);
		Long get11 =  dataPrimaryKeyProvider.getDataKey("m_upld_txt_file_col", 11);
		System.out.println("get1 :" + get1 +  ", get 11 :" + get11);
		
	
	
	}


}
