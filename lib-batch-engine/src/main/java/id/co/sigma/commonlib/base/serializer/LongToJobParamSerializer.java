package id.co.sigma.commonlib.base.serializer;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class LongToJobParamSerializer implements ObjectToJobParamSerializer<Long>{

	@Override
	public void putToParam(Long data, JobParametersBuilder paramBuilder,
			String key) {
		paramBuilder.addLong(key, data);
		
	}

	@Override
	public Long get(JobParameters parameters, String key) {
		return parameters.getLong(key);
	}

}
