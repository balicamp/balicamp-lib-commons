package id.co.sigma.commonlib.exportengine.io;

import java.lang.reflect.InvocationTargetException;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.BeansException;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseObjectConverter implements IObjectToStringConverter{
	
	
	
	
	/**
	 * nomor urut dari converter dalam array
	 */
	private int sequenceNumber ;
	
	/**
	 * nomor urut dari converter dalam array
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	
	/**
	 * nomor urut dari converter dalam array
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	@Override
	public void fetchParameterFromJobParameters(JobParameters params)
			throws IllegalArgumentException, IllegalAccessException,
			BeansException, InvocationTargetException {
		 
		
	}

	@Override
	public void putParameterToParameterBuilder(
			JobParametersBuilder jobParameterBuilder) throws Exception {
		jobParameterBuilder.addString(CONVERTER_TYPE_KEY_PREFIX + sequenceNumber, getConverterTypeCode().toString()); 
		
	}

}
