package id.co.sigma.common.data;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

public enum SpreadSheetExceptionType implements IJSONFriendlyObject<SpreadSheetExceptionType> {
	
	/**
	 * constant untuk exception file upload excel.
	 */
	NUMERIC_EXCEPTION ( -1),
	DATE_EXCEPTION ( -2 ),
	STRING_EXCEPTION ( -3 ),
	BOOLEAN_EXCEPTION ( -4 ),
	UNKNOWN_EXCEPTION ( -5 );
	

	private int internalValue ; 
	
	private SpreadSheetExceptionType(int num ) {
		internalValue = num ; 
	}
	
	@Override
	public String toString() { 
		return internalValue +"";
	}
	

	public int getInternalValue() {
		return internalValue;
	}
	
	public static SpreadSheetExceptionType instantiateFromString (String stringVal ) {
		int a = Integer.parseInt(stringVal);
		for ( SpreadSheetExceptionType scn : SpreadSheetExceptionType.values()) {
			if ( scn.internalValue == a)
				return scn ;
		}
		return null ;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("internalValue", internalValue);
	}

	@Override
	public SpreadSheetExceptionType instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		
		int val = jsonContainer.getAsInteger("internalValue");
		return instantiateFromString(val+"");
	}

}
