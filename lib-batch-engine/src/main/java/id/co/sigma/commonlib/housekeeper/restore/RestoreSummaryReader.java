package id.co.sigma.commonlib.housekeeper.restore;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class RestoreSummaryReader implements ItemReader<RestoreParameterHeader>{

	
	
	RestoreParameterHeader parameterHeader; 
	
	
	@Override
	public RestoreParameterHeader read() throws Exception,
			UnexpectedInputException, ParseException,
			NonTransientResourceException {
		// TODO Auto-generated method stub
		
		RestoreParameterHeader swap = parameterHeader  ; 
		parameterHeader = null; 
		return swap;
	}
	
	@BeforeStep
	protected void beforeStepHandler( StepExecution stepExecution ) throws Exception {
		
		parameterHeader = (RestoreParameterHeader)stepExecution.getJobExecution().getExecutionContext().get(RestoreParameterHeader.RESTORE_PARAM_KEY);
	}

}
