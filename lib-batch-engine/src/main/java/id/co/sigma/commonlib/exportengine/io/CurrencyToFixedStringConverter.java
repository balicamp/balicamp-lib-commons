package id.co.sigma.commonlib.exportengine.io;

import java.lang.reflect.InvocationTargetException;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.BeansException;


/**
 * currency formatter. menambahkan 0 atau spasi di depan dari string number
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class CurrencyToFixedStringConverter extends CurrencyToStringConverter{
	
	
	
	
	
	
	private static final String SPACE_FILLER_KEY="spaceFiller"; 
	
	private static final String TOTAL_LENGTH_KEY="totalLength";
	
	

	/**
	 * total panjang dari String
	 */
	private int lengthTotalOfString ;
	
	
	/**
	 * yang di pakai untuk mengisi agar panjang lengkap
	 */
	private char spaceFiller ='0';
	
	 
	public CurrencyToFixedStringConverter(){
		super(); 
	}
	
	public CurrencyToFixedStringConverter(String currencyFormatterPattern,
			boolean useDotDecimalSeparator, int lengthTotalOfString  ) {
		super(currencyFormatterPattern, useDotDecimalSeparator);
		this.lengthTotalOfString = lengthTotalOfString ; 
	}
	
	
	public CurrencyToFixedStringConverter(String currencyFormatterPattern,
			boolean useDotDecimalSeparator, int lengthTotalOfString , char spaceFiller  ) {
		super(currencyFormatterPattern, useDotDecimalSeparator);
		this.lengthTotalOfString = lengthTotalOfString ; 
		this.spaceFiller = spaceFiller ; 
		
	}
	
	
	
	
	@Override
	public String transform(Object rawObject) {
		String swap =  super.transform(rawObject);
		
		if ( swap.length()< lengthTotalOfString){
			for ( int i= swap.length() ; i< lengthTotalOfString ; i++){
				swap = spaceFiller + swap ; 
			}
		}
		return swap;
	}
	
	
	
	/**
	 * total panjang dari String
	 */
	public int getLengthTotalOfString() {
		return lengthTotalOfString;
	}
	/**
	 * total panjang dari String
	 */
	public void setLengthTotalOfString(int lengthTotalOfString) {
		this.lengthTotalOfString = lengthTotalOfString;
		
	}
	/**
	 * yang di pakai untuk mengisi agar panjang lengkap
	 */
	public void setSpaceFiller(char spaceFiller) {
		this.spaceFiller = spaceFiller;
		
	}
	/**
	 * yang di pakai untuk mengisi agar panjang lengkap
	 */
	public char getSpaceFiller() {
		return spaceFiller;
	}
	
	
	
	

	@Override
	public OuputDataType getConverterTypeCode() {
		return OuputDataType.moneyFixedLenth;
	}
	

	@Override
	public void putParameterToParameterBuilder(
			JobParametersBuilder jobParameterBuilder) throws Exception {
		super.putParameterToParameterBuilder(jobParameterBuilder);
		jobParameterBuilder.addString(SPACE_FILLER_KEY + getSequenceNumber(),  spaceFiller +"");
		jobParameterBuilder.addLong(TOTAL_LENGTH_KEY + getSequenceNumber(),  (long)lengthTotalOfString) ; 
		
	}
	
	
	@Override
	public void fetchParameterFromJobParameters(JobParameters params)
			throws IllegalArgumentException, IllegalAccessException,
			BeansException, InvocationTargetException {
		super.fetchParameterFromJobParameters(params);
		this.spaceFiller = params.getString(SPACE_FILLER_KEY + getSequenceNumber()).charAt(0); 
		this.lengthTotalOfString =((Long) params.getLong(TOTAL_LENGTH_KEY + getSequenceNumber())).intValue() ;
		
	}
}
