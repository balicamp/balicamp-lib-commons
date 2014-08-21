package id.co.sigma.common.data.query;



/**
 * formatter untuk number
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class NumberDataFormatDefinition extends BaseDataFormatDefinition{
	
	private int numberOfDecimal  = 2;
	private boolean formatTousandSeparator =true;
	
	private boolean useDotDecimalSeparator =false; 
	public boolean isUseDotDecimalSeparator() {
		return useDotDecimalSeparator;
	}
	public void setUseDotDecimalSeparator(boolean useDotDecimalSeparator) {
		this.useDotDecimalSeparator = useDotDecimalSeparator;
	}
	public int getNumberOfDecimal() {
		return numberOfDecimal;
	}
	public void setNumberOfDecimal(int numberOfDecimal) {
		this.numberOfDecimal = numberOfDecimal;
	}
	public boolean isFormatTousandSeparator() {
		return formatTousandSeparator;
	}
	public void setFormatTousandSeparator(boolean formatTousandSeparator) {
		this.formatTousandSeparator = formatTousandSeparator;
	}
	
	
	

}
