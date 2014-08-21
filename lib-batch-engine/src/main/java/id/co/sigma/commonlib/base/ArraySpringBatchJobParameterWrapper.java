package id.co.sigma.commonlib.base;

import id.co.sigma.commonlib.util.ParameterSerializerUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.BeansException;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract  class ArraySpringBatchJobParameterWrapper<T extends BaseSpringBatchJobParameterWrapper> implements IJobParameterSerilzable {
	
	
	
	/**
	 * karena tipe nya adalah array of job, maka perlu di tambahkan indexer. ini untuk menandai index ke berapa yang perlu kita ambil
	 * ini di taruh dalam job context
	 *  
	 **/
	public static final String JOB_SEQUENCE_ONARRAY_OF_JOB_KEY="currentJobSequenceIndex";
	
	
	/**
	 * berapa total job yang perlu di trigger dalam job ini
	 **/
	public static final String NUMBER_OF_JOB_TO_EXECUTE_KEY="numberOfJobArray";
	
	
	
	/**
	 * key untuk menaruh repeated job
	 **/
	public static final String REPEATED_STEP_ID_KEY="repatedJobId"; 
	
	/**
	 * id dari step yang perlu di eksekusi setelah repated job done
	 **/
	public static final String AFTER_REPEATED_STEP_DONE_TO_EXECUTE_ID_KEY="afterRepeatedJobId";
	
	
	
	
	
	public ArraySpringBatchJobParameterWrapper(){}
	
	
	
	
	/**
	 * id step yang berulang di eksekusi
	 **/
	private String repeatedStepId ; 
	
	/**
	 * kalau sudah abis, apa yang di eksekusi
	 */
	private String afterRepeatedStepDoneExecutedStepId;


	 
	
	/**
	 * sub parameters
	 **/
	private Collection< T> subParameters ; 
	 
	
	
	/**
	 * 
	 * ini di pergunakan untuk launch job. dengan tipe Collection 
	 * @param repeatedStepId id step yang akan berulang di eksekusi , kalau decider menyatakan belum semua di eksekusi
	 * @param afterRepeatedStepDoneExecutedStepId id dari step, apa yang di eksekusi kalau sudah habis isi array
	 **/
	public ArraySpringBatchJobParameterWrapper(Collection< T> array, String repeatedStepId , String afterRepeatedStepDoneExecutedStepId){
		this() ; 
		this.repeatedStepId = repeatedStepId ;
		this.afterRepeatedStepDoneExecutedStepId = afterRepeatedStepDoneExecutedStepId;
		this.subParameters = array ; 
	}
	
	
	/**
	 * instantiate data dengan index
	 **/
	public T instantiateParameter ( StepExecution stepExecution  ) throws Exception{
		T t = instantiateBlankObject(); 
		
		int swap = 0 ; 
		if (stepExecution.getJobExecution().getExecutionContext().containsKey(JOB_SEQUENCE_ONARRAY_OF_JOB_KEY) )
			swap = stepExecution.getJobExecution().getExecutionContext().getInt(JOB_SEQUENCE_ONARRAY_OF_JOB_KEY)  ; 
		t.setSequenceNumber(swap);
		t.fetchParameterFromJobParameters(stepExecution.getJobParameters());
		return t ; 
	}
	
	 
	
	
	 
	
	
	
	/**
	 * menaruh common array of job parameter ke dalam job parameter
	 **/
	protected void putCommonArrayOfJobParameters (JobParametersBuilder bld  , int totalOfJob ) {
		
		bld.addLong( NUMBER_OF_JOB_TO_EXECUTE_KEY, (long)totalOfJob);
		bld.addString(AFTER_REPEATED_STEP_DONE_TO_EXECUTE_ID_KEY, afterRepeatedStepDoneExecutedStepId) ; 
		bld.addString(REPEATED_STEP_ID_KEY, repeatedStepId); 
		
	}
	
	
	
	
	
	/**
	 * membuat blank object
	 **/
	public abstract T instantiateBlankObject () ;
	
	/**
	 * id step yang berulang di eksekusi
	 **/
	public String getRepeatedStepId() {
		return repeatedStepId;
	}
	/**
	 * id step yang berulang di eksekusi
	 **/
	public void setRepeatedStepId(String repeatedStepId) {
		this.repeatedStepId = repeatedStepId;
	}
	
	/**
	 * kalau sudah abis, apa yang di eksekusi
	 */
	public String getAfterRepeatedStepDoneExecutedStepId() {
		return afterRepeatedStepDoneExecutedStepId;
	}
	/**
	 * kalau sudah abis, apa yang di eksekusi
	 */
	public void setAfterRepeatedStepDoneExecutedStepId(
			String afterRepeatedStepDoneExecutedStepId) {
		this.afterRepeatedStepDoneExecutedStepId = afterRepeatedStepDoneExecutedStepId;
	}
	
	@Override
	public void putParameterToParameterBuilder(
			JobParametersBuilder jobParameterBuilder) throws Exception {
		
		ParameterSerializerUtil.getInstance().putParameterToParameterBuilder(this, null, jobParameterBuilder);
		if ( subParameters!= null && subParameters.size()>0){
			int i = 0 ; 
			for ( T scn : subParameters){
				scn.setSequenceNumber(i++);
				scn.putParameterToParameterBuilder(jobParameterBuilder);
			}
			putCommonArrayOfJobParameters(jobParameterBuilder, subParameters.size());
			  
		}
		else{
			putCommonArrayOfJobParameters(jobParameterBuilder, 0);
			
		}
		
	}
	
	
	/**
	 * menaruh semua field ke dalam parameter
	 **/
	public JobParameters buildJobParameter () throws Exception{
		JobParametersBuilder bld = new JobParametersBuilder();
		
		putParameterToParameterBuilder(bld);
		
		
		return bld.toJobParameters(); 
	}
	
	
	@Override
	public void fetchParameterFromJobParameters(JobParameters params)
			throws IllegalArgumentException, IllegalAccessException,
			BeansException, InvocationTargetException {
		ParameterSerializerUtil.getInstance().fetchParameterFromJobParameters(this, null, params);
		int size = params.getLong(NUMBER_OF_JOB_TO_EXECUTE_KEY).intValue();
		
		this.subParameters = new ArrayList<T>(); 
		for ( int i = 0 ; i < size ; i++){
			T sample = instantiateBlankObject(); 
			 sample.fetchParameterFromJobParameters(params);
			 subParameters.add(sample);
		}
	}

	/**
	 * sub parameters
	 **/
	public Collection<T> getSubParameters() {
		return subParameters;
	}
	
	/**
	 * sub parameters
	 **/
	public void setSubParameters(Collection<T> subParameters) {
		this.subParameters = subParameters;
	}
}
