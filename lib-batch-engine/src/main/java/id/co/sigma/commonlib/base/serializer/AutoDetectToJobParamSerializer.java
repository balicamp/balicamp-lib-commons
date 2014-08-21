package id.co.sigma.commonlib.base.serializer;


import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

/**
 * do nothing. cuma space holder saja
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class AutoDetectToJobParamSerializer implements ObjectToJobParamSerializer<Object>{

	@Override
	public void putToParam(Object data, JobParametersBuilder paramBuilder,
			String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(JobParameters parameters, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
