package id.co.sigma.commonlib.housekeeper.restore;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * Decider untuk decider, proses restore sudah selesai atau tidak
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class RestoreDecider implements JobExecutionDecider{

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution,
			StepExecution stepExecution) {
		
		Integer currIndex  =  (Integer)jobExecution.getExecutionContext().get(  RestoreParameterHeader.CURRENT_RESTORE_PARAM_CURRENT_INDEX_KEY);
		RestoreParameterHeader h = ( RestoreParameterHeader)jobExecution.getExecutionContext().get(RestoreParameterHeader.RESTORE_PARAM_KEY);
		if ( currIndex== null)
			currIndex = 0 ;
		
		
		
		
		RestoreParameterDetail nextObject  = null ; 
		
		
		try{
			
			if (  currIndex+1>= h.getDetails().length){
				return new FlowExecutionStatus(RestoreParameterHeader.ALL_RESTORE_DONE_STATUS_CODE); 
			}
			
			nextObject = h.getDetails()[currIndex+1]; 
			return new FlowExecutionStatus(RestoreParameterHeader.ALL_RESTORE_NOT_DONE_STATUS_CODE);
			
			
			
		}finally {
			jobExecution.getExecutionContext().put( RestoreParameterHeader.CURRENT_RESTORE_PARAM_CURRENT_INDEX_KEY, currIndex+1);
			jobExecution.getExecutionContext().put( RestoreParameterHeader.CURRENT_RESTORE_PARAM_KEY,  nextObject) ;
		}
		 
		
		 
		
	}

}
