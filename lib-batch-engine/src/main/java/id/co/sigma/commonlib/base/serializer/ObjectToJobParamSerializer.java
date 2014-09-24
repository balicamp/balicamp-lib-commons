package id.co.sigma.commonlib.base.serializer;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ObjectToJobParamSerializer<DATA > {
	
	
	
	/**
	 * menaruh data dalam job builder
	 **/
	void putToParam(   DATA data ,  JobParametersBuilder paramBuilder , String key );  
	
	
	/**
	 * membaca data dari jobparameters
	 **/
	public DATA get ( JobParameters parameters ,  String key);
	

}
