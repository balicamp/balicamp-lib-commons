package id.co.sigma.commonlib.base;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.JobParameters;
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
public abstract class JobParamaterArrayReader implements ItemReader<Object[]> , ItemReadListener<Object[]>{

	
	
	
	
	
	
	/**
	 * reference ke job params
	 **/
	private JobParameters jobParameters; 
	 
	
	/**
	 * parameter reader
	 **/
	protected class ParamReader {
		
		
		private BatchParameterType paramType ; 
		 
		
		
		public ParamReader(BatchParameterType paramType){
			this.paramType =paramType; 
		}
		
		public Object read ( String key  ){
			if (BatchParameterType.DATE.equals(paramType))
				return getJobParameters().getDate(key); 
			else if ( BatchParameterType.DOUBLE.equals(paramType))
				return getJobParameters().getDouble(key); 
			else if ( BatchParameterType.LONG.equals(paramType))
				return getJobParameters().getLong( key);
			
			return getJobParameters().getString(  key);
		}
		
	}
	
	
	/**
	 * job parameters, di cache
	 **/
	private JobParameters getJobParameters(){
		return jobParameters ; 
	}
	
	private long currentIndex ; 
	
	
	
	private long countDataToRead ; 
	
	
	
	 
	
	private String[] dataKeys ; 
	
	
	private ParamReader[] paramReaders ; 
	@Override
	public void beforeRead() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void afterRead(Object[] afterRead) {
		currentIndex = currentIndex+1 ; 
		
	}
	
	@Override
	public void onReadError(Exception fault) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object[] read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		
		Object[] retval =new Object[  paramReaders.length];
		if ( currentIndex>=countDataToRead )
			return null ;
		int i = 0  ; 
		for ( ParamReader scn : paramReaders){
			String key = dataKeys[i]  + currentIndex ; 
			retval[i] = scn.read(key); 
			i++; 
		}
		return retval;
	}
	
	
	/**
	 * tipe parameter. ini simetris dengan 
	 **/
	protected abstract BatchParameterType[] getParameterTypes() ;
	
	
	
	/**
	 * nama parameter
	 **/
	protected abstract String[] getParameterNames() ;
	
	
	@BeforeStep
	protected void beforeStepHandler( StepExecution stepExecution ) throws Exception {
		jobParameters = stepExecution.getJobParameters(); 
		currentIndex = 0 ; 
		this.countDataToRead = stepExecution.getJobParameters().getLong(ArraySpringBatchJobParameterWrapper.NUMBER_OF_JOB_TO_EXECUTE_KEY); 
		this.dataKeys = getParameterNames(); 
		BatchParameterType[] swap = getParameterTypes(); 
		if ( dataKeys == null || dataKeys.length== 0)
			throw new IllegalArgumentException("data keys null atau 0 length tidak di ijinkan untuk job parameter reader");
		if ( swap== null || swap.length==0 )
			throw new IllegalArgumentException("data type  null atau 0 length tidak di ijinkan untuk job parameter reader");
		if ( swap.length!= dataKeys.length)
			throw new IllegalArgumentException("data keys harus sama dengan data type. ini tidak di ijinkan"); 
		paramReaders = new ParamReader[dataKeys.length];
		for ( int i = 0 ; i < dataKeys.length; i++){
			paramReaders[i] = new ParamReader(swap[i]); 
			
		}
	}
}


