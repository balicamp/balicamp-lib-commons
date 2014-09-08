package id.co.sigma.commonlib.base.serializer;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class StringToJobParamSerializer implements ObjectToJobParamSerializer<String>{

	@Override
	public void putToParam(String data, JobParametersBuilder paramBuilder,
			String key) {
		paramBuilder.addString(key, data); 
		
	}

	@Override
	public String get(JobParameters parameters, String key) {
		return parameters.getString(key);
	}

}
