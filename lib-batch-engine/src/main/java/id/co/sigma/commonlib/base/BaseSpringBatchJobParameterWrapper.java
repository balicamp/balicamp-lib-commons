package id.co.sigma.commonlib.base;



import id.co.sigma.commonlib.base.serializer.ObjectToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.SendToParameterField;
import id.co.sigma.commonlib.util.ParameterSerializerUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.BeansException;

/**
 *wrapper job parameters. ini agar kita bisa mendapat parameter job dalam POJO manner. ini menjebatani konversi dari map -> POJO dan sebaliknya dengan simple.
 *serialisasi memanfaatkan annotation {@link SendToParameterField} 
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseSpringBatchJobParameterWrapper implements IJobParameterSerilzable{
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	protected static Map<String, ObjectToJobParamSerializer> INDEXED_CONVERTER = new HashMap<String, ObjectToJobParamSerializer>();  
	
	
	
	protected static final Object[] BLANK_ARRAY ={} ; 
	
	
	
	
	
	
	/**
	 * nomor sequence dari parameter. kalau null, berarti parameter tunggal
	 **/
	private Integer sequenceNumber ; 
	
	
	public BaseSpringBatchJobParameterWrapper(){}
	
	
	
	
	
	
	public BaseSpringBatchJobParameterWrapper(JobParameters params ) throws IllegalArgumentException, IllegalAccessException, BeansException, InvocationTargetException{
		fetchParameterFromJobParameters(params);
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
		ParameterSerializerUtil.getInstance().fetchParameterFromJobParameters(this, sequenceNumber, params);
		fetchAdditionalData(params);
	}
	
	@Override
	public void putParameterToParameterBuilder(
			JobParametersBuilder jobParameterBuilder) throws Exception {
		ParameterSerializerUtil.getInstance().putParameterToParameterBuilder(this, sequenceNumber, jobParameterBuilder);
		putAdditionaFields(jobParameterBuilder);
	}
	
	/**
	 * method ini di trigger alau misalnya di perlukan task lain dalam proses menaruh dari job POJO ke job parameter. Override ini kalau di perlukan pekerjaan tambahan
	 * 
	 **/
	public void putAdditionaFields (JobParametersBuilder jobParameterBuilder/*jobParameterBuilder*/)  throws Exception{
		
	}
	
	
	
	
	/**
	 * pekerjaan anada di sini adalah mengambil field-field custom dari parameters ke dalam member variable dari class
	 **/
	public void  fetchAdditionalData ( JobParameters params) {
		
	}
	
	
	/**
	 * nomor sequence dari parameter. kalau null, berarti parameter tunggal
	 **/
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * nomor sequence dari parameter. kalau null, berarti parameter tunggal
	 **/
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	

}
