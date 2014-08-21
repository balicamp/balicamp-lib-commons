package id.co.sigma.commonlib.importengine.definition.validator.comparator;

import id.co.sigma.commonlib.importengine.definition.validator.CompareResult;


public class StringComparator implements ISimpleComparator<String>{

	
	
	
	private String comparatorData ; 
	
	
	public StringComparator(String dt){
		setComparatorData(dt);
	}
	
	
	@Override
	public void setComparatorData(String data) {
		this.comparatorData = data  ; 
		
	}
	
	@Override
	public CompareResult compare(String data1) {
		if ( data1==null&&comparatorData==null)
			return CompareResult.bothObjectNull ; 
		else if ( data1==null)
			return CompareResult.object1Null; 
		else if ( comparatorData == null)
			return CompareResult.object2Null;
		CompareResult hsl = data1.equals(comparatorData)? CompareResult.equal : CompareResult.notEqualOtherNotApplicable; 
		return hsl;
	}

}
