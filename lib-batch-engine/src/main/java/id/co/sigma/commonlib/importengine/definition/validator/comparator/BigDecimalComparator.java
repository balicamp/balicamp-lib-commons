package id.co.sigma.commonlib.importengine.definition.validator.comparator;

import id.co.sigma.commonlib.importengine.definition.validator.CompareResult;

import java.math.BigDecimal;

public class BigDecimalComparator implements ISimpleComparator<BigDecimal>{

	private BigDecimal comparatorData; 
	
	
	
	
	
	public BigDecimalComparator(BigDecimal comparatorData){
		this.comparatorData = comparatorData ; 
	}
	@Override
	public void setComparatorData(BigDecimal data) {
		this.comparatorData = data ; 
		
	}
	

	@Override
	public CompareResult compare(BigDecimal data1) {
		if ( data1==null&&comparatorData==null)
			return CompareResult.bothObjectNull ; 
		else if ( data1==null)
			return CompareResult.object1Null; 
		else if ( comparatorData == null)
			return CompareResult.object2Null;
		int vl =  data1.compareTo(comparatorData); 
		CompareResult retval = vl==0? CompareResult.equal : (vl<0? CompareResult.less : CompareResult.greater);
		return retval;
	}
}
