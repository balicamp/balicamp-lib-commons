package id.co.sigma.commonlib.base;

import java.lang.reflect.InvocationTargetException;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.BeansException;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IJobParameterSerilzable {
	
	
	/**
	 * membaca data dari job parameters. variable di salin ke dalam POJO
	 **/
	public void fetchParameterFromJobParameters ( JobParameters params)throws IllegalArgumentException, IllegalAccessException, BeansException, InvocationTargetException; 
	
	/**
	 * ini untuk  append parameter ke dalam {@link JobParametersBuilder}. Kalau as array ini di put dengan mengincrement parameter 
	 **/
	public void putParameterToParameterBuilder ( JobParametersBuilder jobParameterBuilder)  throws Exception; 
	
	
	

}
