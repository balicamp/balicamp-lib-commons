package id.co.sigma.commonlib.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.BeansException;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class SQLParameterHolder implements Serializable , IJobParameterSerilzable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1495757182066480065L;
	
	
	
	 
	
	/**
	 * prefix key untuk named parameter. FQCN dari object
	 */
	public static final String SQL_PARAMETER_PREFIX_FQCN="QUERY_PARAMETER_FQCN_";
	
	
	
	
	
	/**
	 * prefix key untuk named parameter. parameter name
	 */
	public static final String SQL_PARAMETER_PREFIX_NAME="QUERY_PARAMETER_NAME_";
	
	/**
	 * prefix key untuk named parameter. value dari object
	 */
	public static final String SQL_PARAMETER_PREFIX_VALUE="QUERY_PARAMETER_VALUE_";
	
	public SQLParameterHolder(){}
	
	
	
	private String paramFqcn ; 
	
	/**
	 * nama parameter. apa nama parameter nya
	 **/
	private String paramName ; 
	
	/**
	 * parameter yang di taruh
	 **/
	private Object parameter ;
	
	
	/**
	 * urutan data dalam array.ini untuk menaruh dalam array of parameter
	 */
	private Integer jobParameterIndexNumber ;
	
	public SQLParameterHolder(String key  , Long value ){
		this.parameter = value ; 
		this.paramName = key ; 
		paramFqcn = Long.class.getName(); 
	}
	
	public SQLParameterHolder(String key  , Date value ){
		this.parameter = value ; 
		this.paramName = key ; 
		paramFqcn = Date.class.getName(); 
	}
	
	
	public SQLParameterHolder(String key  , String value ){
		this.parameter = value ; 
		this.paramName = key ; 
		paramFqcn = String.class.getName(); 
		 
	}
	
	public SQLParameterHolder(String key  , Double value ){
		this.parameter = value ; 
		this.paramName = key ; 
		paramFqcn = Double.class.getName();
	}
	
	

	
	
	
	/**
	 * parameter yang di taruh
	 **/
	public Object getParameter() {
		return parameter;
	}
	
	/**
	 * parameter yang di taruh
	 **/
	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}
	/**
	 * nama parameter. apa nama parameter nya
	 **/
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	/**
	 * nama parameter. apa nama parameter nya
	 **/
	public String getParamName() {
		return paramName;
	}

	
	
	public String getParamFqcn() {
		return paramFqcn;
	}
	
	public void setParamFqcn(String paramFqcn) {
		this.paramFqcn = paramFqcn;
	}

	 
 
	
	
	/**
	 * urutan data dalam array.ini untuk menaruh dalam array of parameter
	 */
	public void setJobParameterIndexNumber(Integer jobParameterIndexNumber) {
		this.jobParameterIndexNumber = jobParameterIndexNumber;
	}
	
	/**
	 * urutan data dalam array.ini untuk menaruh dalam array of parameter
	 */
	public Integer getJobParameterIndexNumber() {
		return jobParameterIndexNumber;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((jobParameterIndexNumber == null) ? 0
						: jobParameterIndexNumber.hashCode());
		result = prime * result
				+ ((paramFqcn == null) ? 0 : paramFqcn.hashCode());
		result = prime * result
				+ ((paramName == null) ? 0 : paramName.hashCode());
		result = prime * result
				+ ((parameter == null) ? 0 : parameter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SQLParameterHolder other = (SQLParameterHolder) obj;
		if (jobParameterIndexNumber == null) {
			if (other.jobParameterIndexNumber != null)
				return false;
		} else if (!jobParameterIndexNumber
				.equals(other.jobParameterIndexNumber))
			return false;
		if (paramFqcn == null) {
			if (other.paramFqcn != null)
				return false;
		} else if (!paramFqcn.equals(other.paramFqcn))
			return false;
		if (paramName == null) {
			if (other.paramName != null)
				return false;
		} else if (!paramName.equals(other.paramName))
			return false;
		if (parameter == null) {
			if (other.parameter != null)
				return false;
		} else if (!parameter.equals(other.parameter))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SQLParameterHolder [paramFqcn=" + paramFqcn + ", paramName="
				+ paramName + ", parameter=" + parameter
				+ ", jobParameterIndexNumber=" + jobParameterIndexNumber + "]";
	}

	@Override
	public void fetchParameterFromJobParameters(JobParameters params)
			throws IllegalArgumentException, IllegalAccessException,
			BeansException, InvocationTargetException {
		this.paramFqcn = params.getString(SQL_PARAMETER_PREFIX_FQCN + this.jobParameterIndexNumber); 
		this.paramName =  params.getString(SQL_PARAMETER_PREFIX_NAME + this.jobParameterIndexNumber);
		if ( Long.class.getName().equals(paramFqcn)){
			this.parameter  = params.getLong(SQL_PARAMETER_PREFIX_VALUE + this.jobParameterIndexNumber);
		}
		else if ( String.class.getName().equals(paramFqcn)){
			this.parameter  = params.getString(SQL_PARAMETER_PREFIX_VALUE + this.jobParameterIndexNumber );
		}
		else if ( Date.class.getName().equals(paramFqcn)){
			this.parameter  = params.getDate(SQL_PARAMETER_PREFIX_VALUE + this.jobParameterIndexNumber );
		}
		else if ( java.sql.Date.class.getName().equals(paramFqcn)){
			this.parameter  = params.getDate(SQL_PARAMETER_PREFIX_VALUE + this.jobParameterIndexNumber );
		}
		else if ( Double.class.getName().equals(paramFqcn)){
			this.parameter  = params.getDouble(SQL_PARAMETER_PREFIX_VALUE + this.jobParameterIndexNumber );
		}
		
		
	}

	@Override
	public void putParameterToParameterBuilder(
			JobParametersBuilder jobParameterBuilder) throws Exception {
		jobParameterBuilder.addString(SQL_PARAMETER_PREFIX_FQCN + this.jobParameterIndexNumber, paramFqcn  );
		jobParameterBuilder.addString(SQL_PARAMETER_PREFIX_NAME + this.jobParameterIndexNumber, paramName  );
		if ( Long.class.getName().equals(paramFqcn)){
			jobParameterBuilder.addLong(SQL_PARAMETER_PREFIX_VALUE + this.jobParameterIndexNumber, (Long)parameter);
		}
		else if ( String.class.getName().equals(paramFqcn)){
			jobParameterBuilder.addString(SQL_PARAMETER_PREFIX_VALUE + this.jobParameterIndexNumber, (String)parameter);
		}
		else if ( Date.class.getName().equals(paramFqcn)){
			jobParameterBuilder.addDate(SQL_PARAMETER_PREFIX_VALUE + this.jobParameterIndexNumber, (Date)parameter);
		}
		else if ( java.sql.Date.class.getName().equals(paramFqcn)){
			jobParameterBuilder.addDate(SQL_PARAMETER_PREFIX_VALUE + this.jobParameterIndexNumber, (Date)parameter);
		}
		else if ( Double.class.getName().equals(paramFqcn)){
			jobParameterBuilder.addDouble(SQL_PARAMETER_PREFIX_VALUE + this.jobParameterIndexNumber, (Double)parameter);
		}
	}
}
