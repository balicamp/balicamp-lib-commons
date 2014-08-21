package id.co.sigma.common.exception;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * ini di pergunakan kalau ada duplikasi data dalam data yang di upload. ini berisi informasi antara lain : 
 * <ol>
 * <li>Field yang bentrol</li>
 * <li>value yang duplikasi</li>
 * 
 * </ol>
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class DataDuplicationOnUploadedDataException extends BaseIsSerializableException implements IJSONFriendlyObject<DataDuplicationOnUploadedDataException>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5132894299699438951L;

	/**
	 * field yang di cek apakah ada duplikasi atau tidak
	 */
	private String duplicatedCheckedField  ; 
	
	/**
	 * nilai-nilai yang duplikat
	 */
	private List<String> duplicatedValues ;
	
	
	public DataDuplicationOnUploadedDataException() {
		super() ; 
	}
	
	public DataDuplicationOnUploadedDataException(String message) {
		super(message) ; 
	}
	
	public   DataDuplicationOnUploadedDataException( String message , String checkedField , List<String> duplicatedValues ) {
		super(message); 
		this.duplicatedCheckedField = checkedField ; 
		this.duplicatedValues = duplicatedValues ; 
	}
	/**
	 * nilai-nilai yang duplikat
	 */
	public List<String> getDuplicatedValues() {
		return duplicatedValues;
	}
	/**
	 * nilai-nilai yang duplikat
	 */
	public void setDuplicatedValues(List<String> duplicatedValues) {
		this.duplicatedValues = duplicatedValues;
	}
	/**
	 * field yang di cek apakah ada duplikasi atau tidak
	 */
	public void setDuplicatedCheckedField(String duplicatedCheckedField) {
		this.duplicatedCheckedField = duplicatedCheckedField;
	}
	/**
	 * field yang di cek apakah ada duplikasi atau tidak
	 */
	public String getDuplicatedCheckedField() {
		return duplicatedCheckedField;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("message", this.message);
		jsonContainer.put("fullStackTrace", this.fullStackTrace);
		jsonContainer.put("duplicatedCheckedField", this.duplicatedCheckedField);
		if ( duplicatedValues!= null && !duplicatedValues.isEmpty()){
			String[] arr = new String[duplicatedValues.size()] ;
			duplicatedValues.toArray(arr); 
			jsonContainer.appendToArray( "duplicatedValues",arr);
		}
		
	}

	@Override
	public DataDuplicationOnUploadedDataException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		DataDuplicationOnUploadedDataException retval = new DataDuplicationOnUploadedDataException(); 
		retval.message = jsonContainer.getAsString("message"); 
		retval.fullStackTrace = jsonContainer.getAsString("fullStackTrace"); 
		retval.duplicatedCheckedField = jsonContainer.getAsString("duplicatedCheckedField"); 
		String[] arr =  jsonContainer.getAsStringArray( "duplicatedValues");
		if ( arr!= null && arr.length> 0 ){
			retval.duplicatedValues = new ArrayList<String> () ; 
			for ( String scn : arr){
				retval.duplicatedValues.add(scn); 
			}
		}
		return retval;
	}

}
