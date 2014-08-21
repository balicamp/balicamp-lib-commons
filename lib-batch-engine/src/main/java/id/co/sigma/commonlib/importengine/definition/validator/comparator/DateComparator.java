package id.co.sigma.commonlib.importengine.definition.validator.comparator;

import id.co.sigma.commonlib.importengine.definition.validator.CompareResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateComparator implements ISimpleComparator<Date>{

	private static final SimpleDateFormat SIMPLE_DATE_FORMATTER = new SimpleDateFormat("yyyyMMdd");
	
	
	private Integer dataComparatorAsInteger ; 
	private int dataComparatorAsIntegerPrimitive ; 
	
	
	public DateComparator(Date dt){
		setComparatorData(dt);
	}
	
	
	@Override
	public void setComparatorData(Date data) {
		if ( data==null){
			dataComparatorAsInteger = null ; 
		}else{
			dataComparatorAsInteger = Integer.parseInt(  SIMPLE_DATE_FORMATTER.format(data));
			dataComparatorAsIntegerPrimitive = dataComparatorAsInteger.intValue(); 
		}
		
	}
	@Override
	public CompareResult compare(Date dataToCompare ) {
		if ( dataToCompare==null&&dataComparatorAsInteger==null)
			return CompareResult.bothObjectNull ; 
		else if ( dataToCompare==null)
			return CompareResult.object1Null; 
		else if ( dataComparatorAsInteger == null)
			return CompareResult.object2Null;
		
		int angka1 = Integer.parseInt(  SIMPLE_DATE_FORMATTER.format(dataToCompare));
		
		CompareResult retval = angka1==dataComparatorAsInteger.intValue()? CompareResult.equal : (
					(angka1<dataComparatorAsIntegerPrimitive?  CompareResult.less : CompareResult.greater)
				);
		return retval;
	}

}
