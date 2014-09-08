package id.co.sigma.commonlib.base.serializer;


import java.math.BigInteger;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class BigDecimalToJobParamSerializer implements ObjectToJobParamSerializer<BigInteger>{

	@Override
	public void putToParam(BigInteger data, JobParametersBuilder paramBuilder,
			String key) {
		paramBuilder.addString(   key, data != null ? data.toString() : "");
		
	}

	@Override
	public BigInteger get(JobParameters parameters, String key) {
		String swap = parameters.getString(key); 
		if ( swap== null|| swap.isEmpty())
			return null ; 
		return new BigInteger(swap);
	}

}
