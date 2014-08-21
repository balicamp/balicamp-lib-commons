package id.co.sigma.common.data.serializer.json;

import java.util.ArrayList;
import java.util.List;

import id.co.sigma.common.data.reflection.ClientReflectableClass;
import id.co.sigma.common.data.serializer.IJSONWrapperObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;

/**
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
@ClientReflectableClass
public class ListDataWrapperJSONFriendlyType implements IJSONFriendlyObject<ListDataWrapperJSONFriendlyType> , IJSONWrapperObject<List<IJSONFriendlyObject<?>>>{
	
	
	/**
	 * data yang di taruh dalam container
	 **/
	private List<IJSONFriendlyObject<?>> containedData ;
	
	private String fqcn ; 
	
	
	public ListDataWrapperJSONFriendlyType(){}
	
	
	
	
	/**
	 * konstruktor dengan array of data
	 **/
	public ListDataWrapperJSONFriendlyType(List<IJSONFriendlyObject<?>> data){
		this.containedData = data ; 
		if ( containedData!= null ){
			for ( IJSONFriendlyObject<?> scn : data){
				if ( scn!= null){
					fqcn = scn.getClass().getName() ; 
					break ; 
				}
			}
		}
	}
	
	
	
	/**
	 * data yang di contain. Arraylist
	 **/
	public List<IJSONFriendlyObject<?>> getContainedData() {
		return containedData;
	}
	
	
	public ListDataWrapperJSONFriendlyType instantiateFromJSON(id.co.sigma.common.util.json.ParsedJSONContainer jsonContainer) {
		String fqcn = jsonContainer.getAsString(FQCN_MARKER_KEY); 
		@SuppressWarnings("rawtypes")
		ArrayList swapData = jsonContainer.getAsArraylist(ACTUAL_ARRAY_DATA_KEY , fqcn);
		@SuppressWarnings("unchecked")
		ListDataWrapperJSONFriendlyType retval = new ListDataWrapperJSONFriendlyType(swapData) ; 
		
		
		
		return retval ; 
		
		
	};
	
	public void translateToJSON(id.co.sigma.common.util.json.ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put(FQCN_MARKER_KEY, fqcn);
		jsonContainerData.put(IS_ARRAYLIST_MARKER_PROPERTY, true);
		if ( this.containedData!= null){
			IJSONFriendlyObject<?>[] arr = new IJSONFriendlyObject[this.containedData.size()]; 
			this.containedData.toArray(arr);
			jsonContainerData.appendToArray(ACTUAL_ARRAY_DATA_KEY, arr); 
		}
		
		
	};
	@Override
	public List<IJSONFriendlyObject<?>> getActualData() {
		
		return this.containedData;
	}

}
