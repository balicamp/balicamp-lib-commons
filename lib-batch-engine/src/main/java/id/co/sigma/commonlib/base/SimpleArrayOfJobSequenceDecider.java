package id.co.sigma.commonlib.base;

import id.co.sigma.commonlib.util.BatchLibConstant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * decider job yang tipe nya array of job, item yang musti di trigger sequential
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class SimpleArrayOfJobSequenceDecider implements JobExecutionDecider{
	
	
	/**
	 * ini kalau array of step musti di ulangi. blm abis
	 **/
	public static final String REPAT_ARRAYSTEP_RESULT_STATUS ="repeat-array"; 
	
	
	/**
	 * ini kalau array of step musti di ulangi. blm abis
	 **/
	public static final String END_OF_ARRAYSTEP_RESULT_STATUS ="array-done";
	
	
	private static final Logger logger = LoggerFactory.getLogger(SimpleArrayOfJobSequenceDecider.class);

	@Override
	public FlowExecutionStatus decide(JobExecution jobExc, StepExecution stepExc) {
		StringBuffer loggerMessage = new StringBuffer();
		
		
		Long numberOfJob = jobExc.getJobParameters().getLong(ArraySpringBatchJobParameterWrapper.NUMBER_OF_JOB_TO_EXECUTE_KEY);
		
		
		loggerMessage.append("numberOfJob:" + numberOfJob + ".");
		
		int currentIndex = 0 ; 
		if ( jobExc.getExecutionContext().containsKey(ArraySpringBatchJobParameterWrapper.JOB_SEQUENCE_ONARRAY_OF_JOB_KEY)) 
			currentIndex= jobExc.getExecutionContext().getInt(ArraySpringBatchJobParameterWrapper.JOB_SEQUENCE_ONARRAY_OF_JOB_KEY)   ;
		 
		jobExc.getExecutionContext().putInt( ArraySpringBatchJobParameterWrapper.JOB_SEQUENCE_ONARRAY_OF_JOB_KEY, currentIndex+1);
		loggerMessage.append("currentIndex:" + currentIndex + ".");
		try{
			if ( numberOfJob<= ( currentIndex+1)){// suda selesai yah, 
				return new FlowExecutionStatus(END_OF_ARRAYSTEP_RESULT_STATUS );
			}
			else{
				return new FlowExecutionStatus(REPAT_ARRAYSTEP_RESULT_STATUS );
			}
		}finally{
			logger.debug(loggerMessage.toString());
			System.out.println("SimpleArrayOfJobSequenceDecider>>" + loggerMessage);
		}
		
		
		
	}

}
