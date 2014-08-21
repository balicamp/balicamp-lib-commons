package id.co.sigma.commonlib.exportengine.io;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.BeansException;

public class CurrencyToStringConverter extends BaseObjectConverter{
	
	
	
	private static final String USE_DOT_PREFIX ="useDot";
	
	private static final String DECIMAL_PATTERN_PREFIX ="decimalPattern";
	
	private DecimalFormat currencyFormatter;
	
	
	/**
	 * pemisah decimal, pakai . atau ,
	 */
	private boolean useDotDecimalSeparator;


	
	
	/**
	 * pattern currency
	 */
	private String currencyFormatterPattern; 
	
	public CurrencyToStringConverter(){}

	public CurrencyToStringConverter(String currencyFormatterPattern,
			boolean useDotDecimalSeparator) {
		super();
		this.currencyFormatterPattern = currencyFormatterPattern ;
		this.useDotDecimalSeparator = useDotDecimalSeparator;
	}


	@Override
	public String transform(Object rawObject) {
		if ( rawObject ==null || !(rawObject instanceof BigDecimal)  )
			return null ; 
		return formatCurrency((BigDecimal)rawObject);
	}

	
	private String formatCurrency (BigDecimal number ) {
		if ( number==null)
			return null ; 
		String swap = this.currencyFormatter.format(number); 
		if ( useDotDecimalSeparator)
			return swap ;
		if ( !swap.contains("."))
			return swap ; 
		String arr[] = swap.split("\\.");
		String dpn = arr[0].replaceAll("\\,", "."); 
		return dpn + "," + arr[1]; 
		
	}


	@Override
	public OuputDataType getConverterTypeCode() {
		return OuputDataType.money;
	}
	
	/**
	 * pattern currency
	 */
	public void setCurrencyFormatterPattern(String currencyFormatterPattern) {
		this.currencyFormatterPattern = currencyFormatterPattern;
		this.currencyFormatter = new DecimalFormat(this.currencyFormatterPattern); 
	}
	/**
	 * pattern currency
	 */
	public String getCurrencyFormatterPattern() {
		return currencyFormatterPattern;
	}
	
	
	/**
	 * pemisah decimal, pakai . atau ,
	 */
	public boolean isUseDotDecimalSeparator() {
		return useDotDecimalSeparator;
	}
	/**
	 * pemisah decimal, pakai . atau ,
	 */
	public void setUseDotDecimalSeparator(boolean useDotDecimalSeparator) {
		this.useDotDecimalSeparator = useDotDecimalSeparator;
	}
	
	
	@Override
	public void putParameterToParameterBuilder(
			JobParametersBuilder jobParameterBuilder) throws Exception {
		super.putParameterToParameterBuilder(jobParameterBuilder);
		jobParameterBuilder.addString(DECIMAL_PATTERN_PREFIX + getSequenceNumber(), this.currencyFormatterPattern); 
		jobParameterBuilder.addString(USE_DOT_PREFIX + getSequenceNumber() , useDotDecimalSeparator? "Y" : "N"); 
		
		
	}
	
	
	@Override
	public void fetchParameterFromJobParameters(JobParameters params)
			throws IllegalArgumentException, IllegalAccessException,
			BeansException, InvocationTargetException {
		super.fetchParameterFromJobParameters(params);
		this.useDotDecimalSeparator = "Y".equals(  params.getString(USE_DOT_PREFIX + getSequenceNumber() ));
		setCurrencyFormatterPattern(params.getString(DECIMAL_PATTERN_PREFIX + getSequenceNumber()));
		
	}
}
