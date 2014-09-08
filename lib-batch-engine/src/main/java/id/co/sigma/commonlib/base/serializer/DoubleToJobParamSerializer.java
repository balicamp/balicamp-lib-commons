package id.co.sigma.commonlib.base.serializer;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class DoubleToJobParamSerializer implements ObjectToJobParamSerializer<Double>{

	@Override
	public void putToParam(Double data, JobParametersBuilder paramBuilder,
			String key) {
		paramBuilder.addDouble( key, data);
		
	}

	@Override
	public Double get(JobParameters parameters, String key) {
		return parameters.getDouble( key);
	}

}
