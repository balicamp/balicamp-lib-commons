package id.co.sigma.commonlib.exportengine.io;

import java.lang.reflect.InvocationTargetException;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.BeansException;

import id.co.sigma.commonlib.base.IJobParameterSerilzable;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class NamedConverter implements IJobParameterSerilzable{
	
	
	
	private static final String PREFIX_FOR_NAMED_FIELD ="convertedFieldName";  
	
	
	
	private IObjectToStringConverter converter ; 
	private String columnName ; 
	
	private int sequenceNumber ;
	
	
	
	public NamedConverter(){
		
	}

	public NamedConverter( String columnName , IObjectToStringConverter converter) {
		super();
		this.converter = converter;
		this.columnName = columnName;
	}

	public IObjectToStringConverter getConverter() {
		return converter;
	}

	public void setConverter(IObjectToStringConverter converter) {
		this.converter = converter;
		this.converter.setSequenceNumber(sequenceNumber);
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
		if ( this.converter!= null)
			this.converter.setSequenceNumber(sequenceNumber);
	}

	@Override
	public void fetchParameterFromJobParameters(JobParameters params)
			throws IllegalArgumentException, IllegalAccessException,
			BeansException, InvocationTargetException {
		this.columnName = params.getString(PREFIX_FOR_NAMED_FIELD + sequenceNumber); 
		String type = params.getString( IObjectToStringConverter.CONVERTER_TYPE_KEY_PREFIX + sequenceNumber);
		this.converter = null ; 
		if ( OuputDataType.commonToString.toString().equals(type)){
			this.converter = new CommonToStringConverter(); 
		}
		else if ( OuputDataType.date.toString().equals(type)){
			this.converter = new DateToStringConverter(); 
		}
		else if ( OuputDataType.money.toString().equals(type)){
			this.converter = new CurrencyToStringConverter(); 
		}
		else if ( OuputDataType.moneyFixedLenth.toString().equals(type)){
			this.converter = new CurrencyToFixedStringConverter(); 
		}
		
		else if ( OuputDataType.string.toString().equals(type)){
			this.converter = new NoConverter(); 
		}
		converter.setSequenceNumber(sequenceNumber);
		converter.fetchParameterFromJobParameters(params);
	}

	@Override
	public void putParameterToParameterBuilder(
			JobParametersBuilder jobParameterBuilder) throws Exception {
		jobParameterBuilder.addString(PREFIX_FOR_NAMED_FIELD + sequenceNumber, columnName)  ;
		converter.putParameterToParameterBuilder(jobParameterBuilder);
		
	} 

}
