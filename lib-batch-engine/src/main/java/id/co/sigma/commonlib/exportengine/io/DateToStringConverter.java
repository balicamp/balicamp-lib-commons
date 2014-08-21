package id.co.sigma.commonlib.exportengine.io;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.BeansException;

public class DateToStringConverter extends BaseObjectConverter{

	
	private SimpleDateFormat dateFormatter ; 
	
	
	private final static String DATE_PATTERN_PREFIX_KEY ="datePattern";  
	
	
	
	private String dateFormatterPattern ; 
	
	
	public DateToStringConverter(){}
	
	
	public DateToStringConverter(String dateFormatterPattern) {
		super();
		setDateFormatterPattern(dateFormatterPattern);
	}


	@Override
	public String transform(Object rawObject) {
		if ( rawObject==null || !(rawObject instanceof Date))
			return null ; 
		return dateFormatter.format((Date)rawObject);
	}


	@Override
	public OuputDataType getConverterTypeCode() {
		return OuputDataType.date;
	}


	 
	
	


	public String getDateFormatterPattern() {
		return dateFormatterPattern;
	}


	public void setDateFormatterPattern(String dateFormatterPattern) {
		this.dateFormatterPattern = dateFormatterPattern; 
		this.dateFormatter  =new SimpleDateFormat(dateFormatterPattern);
	}
	

	@Override
	public void putParameterToParameterBuilder(
			JobParametersBuilder jobParameterBuilder) throws Exception {
		super.putParameterToParameterBuilder(jobParameterBuilder);
		jobParameterBuilder.addString(DATE_PATTERN_PREFIX_KEY + getSequenceNumber(), dateFormatterPattern);
	}
	
	@Override
	public void fetchParameterFromJobParameters(JobParameters params)
			throws IllegalArgumentException, IllegalAccessException,
			BeansException, InvocationTargetException {
		super.fetchParameterFromJobParameters(params);
		String pattern  = params.getString(DATE_PATTERN_PREFIX_KEY + getSequenceNumber()); 
		this.setDateFormatterPattern(pattern);
	}
	 
}
