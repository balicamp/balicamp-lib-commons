package id.co.sigma.commonlib.base;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;

/**
 * reader JDBC dengan reader berparameter dari job parameter
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class JobParameterQueryParameterizeJDBCItemReader<DATA> extends BaseSimpleParameterizedJDBCItemReader<DATA> {

	
	
	private SQLParameterHolderArray sqlParameterHolder ;
	
	
	
	@BeforeStep
	public final void beforeStepHandler( StepExecution stepExecution ) throws Exception {
		
		actualBeforeStepHandler(stepExecution);
		
	}
	
	
	
	/**
	 * actual before step handler
	 */
	protected void actualBeforeStepHandler (StepExecution stepExecution)throws Exception {
		sqlParameterHolder = new SQLParameterHolderArray(); 
		sqlParameterHolder.fetchParameterFromJobParameters(stepExecution.getJobParameters());
		this.sourceDataPreparedStatementSetter.setIndexedStatementParam(sqlParameterHolder);
		setPreparedStatementSetter(sourceDataPreparedStatementSetter);
	}
}
