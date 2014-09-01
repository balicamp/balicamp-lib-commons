package id.co.sigma.common.data.serializer.json;

import java.util.ArrayList;
import java.util.List;

import id.co.sigma.common.data.ObjectSerializer;
import id.co.sigma.common.data.serializer.IJSONWrapperObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class ListDataWrapperPrimitiveType implements IJSONFriendlyObject<ListDataWrapperPrimitiveType>,IJSONWrapperObject<List> {
	
	
	
	private String fqcn ; 
	
	
	
	private List  primitiveDataList ; 
	
	
	
	
	
	
	public ListDataWrapperPrimitiveType(){
		
	}
	
	
	
	/**
	 * set string array data
	 **/
	public ListDataWrapperPrimitiveType (List data) {
		this.primitiveDataList = data ; 
		if ( primitiveDataList!= null){
			for ( Object scn : primitiveDataList){
				if ( scn != null){
					this.fqcn = scn.getClass().getName(); 
					break  ; 
					
				}
					
			}
		}
	}
	
	
	
	
	public ListDataWrapperPrimitiveType(ArrayList<String> dataArray){
		this.fqcn = String.class.getName() ; 
		this.primitiveDataList = dataArray ; 
	}
	

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put(IJSONFriendlyObject.FQCN_MARKER_KEY, fqcn);
		jsonContainerData.put(IS_ARRAYLIST_MARKER_PROPERTY, true);
		if ( primitiveDataList!= null && !primitiveDataList.isEmpty()){
			ObjectSerializer<Object> ser =  (ObjectSerializer<Object>) ObjectSerializerManager.getInstance().getSerializer(fqcn);
			String[] serAsArray = new String[primitiveDataList.size()];
			int i = 0 ; 
			for ( Object scn : primitiveDataList){
				 String obj =  ser.serialize(scn);
				 serAsArray[i] = obj ; 
				 i++ ;
			}
			jsonContainerData.appendToArray(ACTUAL_ARRAY_DATA_KEY, serAsArray); 
		}
		
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ListDataWrapperPrimitiveType instantiateFromJSON(ParsedJSONContainer jsonContainerData) {
		ListDataWrapperPrimitiveType retval = new ListDataWrapperPrimitiveType(); 
		retval.fqcn = jsonContainerData.getAsString(FQCN_MARKER_KEY);
		retval.primitiveDataList = new ArrayList();
		String[] arrData =  jsonContainerData.getAsStringArray(ACTUAL_ARRAY_DATA_KEY);
		if ( arrData!= null && arrData.length>0){
			ObjectSerializer<?> ser =  (ObjectSerializer<?>) ObjectSerializerManager.getInstance().getSerializer(retval.fqcn);
			for ( String scn : arrData){
				retval.primitiveDataList.add(  ser.deserialize(scn)); 
			}
		}
		return retval;
	}
	
	
	
	/**
	 * data yang di hold oleh JSON container
	 **/
	public List getPrimitiveDataList() {
		return primitiveDataList;
	}
	
	@Override
	public List getActualData() {
		return this.primitiveDataList;
	}

}
