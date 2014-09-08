package id.co.sigma.commonlib.housekeeper.io;

import id.co.sigma.commonlib.housekeeper.HouseKeepingConstant;

import java.io.File;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ArchiveProcessor implements ItemProcessor<Object[], File>{
	
	
	
	private String baseDirectory ; 

	@Override
	public File process(Object[] item) throws Exception {
		
		File f = new File(baseDirectory + File.separator + item[0] +".json"); 
		
		return f.exists()? f : null  ;
	}

	
	
	@BeforeStep
	protected void beforeStepHandler( StepExecution stepExecution ) throws Exception {
		baseDirectory = stepExecution.getJobParameters().getString(HouseKeepingConstant.OUTPUT_FILE_DIRECTORY_PATH);
	}
	
	
	
	public static void main ( String[ ] args){
		System.out.println(File.separator);
	}
}
