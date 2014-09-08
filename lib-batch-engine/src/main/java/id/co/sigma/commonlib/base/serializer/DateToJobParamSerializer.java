package id.co.sigma.commonlib.base.serializer;

import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class DateToJobParamSerializer implements ObjectToJobParamSerializer<Date>{

	@Override
	public void putToParam(Date data, JobParametersBuilder paramBuilder,
			String key) {
		paramBuilder.addDate(  key, data);
		
	}

	@Override
	public Date get(JobParameters parameters, String key) {
		return parameters.getDate(  key);
	}

}
