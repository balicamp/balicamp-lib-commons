package id.co.sigma.commonlib.exportengine;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import id.co.sigma.commonlib.base.SQLParameterHolder;
import id.co.sigma.commonlib.exportengine.data.IExportToTextFileMetadataProvider;
import id.co.sigma.commonlib.exportengine.io.CurrencyToFixedStringConverter;
import id.co.sigma.commonlib.exportengine.io.DateToStringConverter;
import id.co.sigma.commonlib.exportengine.io.NamedConverter;
import id.co.sigma.commonlib.exportengine.io.SingleExportJobParameter;
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
		"classpath*:META-INF/spring/export-engine-parameterized-context.xml",
		"classpath*:/appContext-datasourcetest-context.xml", 
		"classpath*:/appContext-exportenginetest-parameterized-context.xml"
		})
@RunWith(value=SpringJUnit4ClassRunner.class)
public class ExportEngineTestParameterized {
	
	
	
	
	@Autowired
	private IExportJobLauncher exportJobLauncher ; 
	
	
	@Test
	public void runTest() throws Exception {
		
		
		File f = File.createTempFile("exp_engine_test-", ".csv"); 
		
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
		
		SingleExportJobParameter param = new SingleExportJobParameter(); 
		param.setDefaultDateFormatterPattern("yyyy-MM-dd");
		param.setConverters(new NamedConverter[]{
				new NamedConverter( "trx_datetime" , new DateToStringConverter("yyyy-MM-dd hh:mm:ss")) , 
				new NamedConverter( "trx_acct_date" , new DateToStringConverter("yyyy-MM-dd")) ,
				new NamedConverter( "payment_amt", new CurrencyToFixedStringConverter("###.##", true, 16))
				
		});
		param.setDelimiter(";");
		param.setJobListenerBeanName("test-listen-export-engine");
		param.setSelectSqlStatment(
				"select :KODE_BANK KODE_BANK, tx.trx_datetime , tx.trx_acct_date , tx.billing_no , " +  
				" tx.ntb_no , tx.ntpn_no , tx.payment_amt , tx.curr_code , lmp.no_sakti  " +
				" from  " +
				" 	tx_setor_consolidation tx join " + 
				" 	tx_pelimpahan lmp on tx.id_pelimpahan = lmp.pk " +
				" where " +
				" 	lmp.trx_acct_date =:DT_FILTER	 ");
		param.setTargetOutputPath(f.getAbsolutePath());
		
		param.setSqlParameters(new SQLParameterHolder[]{
			new SQLParameterHolder("KODE_BANK" , "523011000990"), 
			new SQLParameterHolder("DT_FILTER", fmt.parse("2013-08-02"))
				
		});
		
		
		exportJobLauncher.launchSimpleExport(param); 
		System.out.println("silakan di cek di path : " + f.getAbsolutePath());
		
		
		
	}
	
	

}
