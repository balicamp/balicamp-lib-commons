package id.co.sigma.common.data.serializer.json;





import id.co.sigma.common.data.serializer.IJSONWrapperObject;
import id.co.sigma.common.util.ObjectGeneratorManager;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONArrayContainer;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * sample json <br/>
 *{<br/>
 * "fqcn" :  "full qualified class name dari array. ini agar data bisa di guest" ,    // constant nbisa di cek dari --> id.co.sigma.common.util.json.IJSONFriendlyObject.IS_FQCN_MARKER_PROPERTY <br/>
 *"actualData":[ini array of object] // variable untuk actualData di ambil dari <i>id.co.sigma.common.util.json.IJSONFriendlyObject.ACTUAL_ARRAY_DATA_KEY</i<br/> 
 
 *}<br/>
 *ini untuk me-wrap json yang berupa array
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class NonPrimitiveArrayDataWrapper<T extends IJSONFriendlyObject<T>> implements IJSONFriendlyObject<NonPrimitiveArrayDataWrapper<T>> , IJSONWrapperObject<T[]>{

	
	/**
	 * fqcn dari data
	 **/
	private String objectFQCN ;
	
	
	/**
	 * object actual
	 **/
	private T[] actualObject ;
	
	
	
	public NonPrimitiveArrayDataWrapper() {
		
	}
	
	public NonPrimitiveArrayDataWrapper(T[] actualObject) {
		this.actualObject = actualObject ; 
		if ( actualObject!= null){
			for ( T scn : actualObject){
				if ( scn != null){
					objectFQCN = scn.getClass().getName() ; 
					break ; 
				}
			}
		}
		
	}
	 
	 
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put(id.co.sigma.common.util.json.IJSONFriendlyObject.FQCN_MARKER_KEY, objectFQCN); 
		if ( actualObject!= null &&  actualObject.length>0){
			jsonContainerData.appendToArray(ACTUAL_ARRAY_DATA_KEY, actualObject); 
		}
		
	}

	@Override
	public NonPrimitiveArrayDataWrapper<T> instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		NonPrimitiveArrayDataWrapper<T> retval = new NonPrimitiveArrayDataWrapper<T>(); 
		retval.objectFQCN = jsonContainer.getAsString(id.co.sigma.common.util.json.IJSONFriendlyObject.FQCN_MARKER_KEY);
		if ( retval.objectFQCN!= null &&  !retval.objectFQCN.isEmpty()){
			T baru =  ObjectGeneratorManager.getInstance().instantiateSampleObject(retval.objectFQCN);
			if ( baru== null){
				System.err.println("tidak bisa membuat sample object utnuk :" + objectFQCN + " , proses relecting object  tidak berhasil");
			}
				
			ParsedJSONArrayContainer arr =  jsonContainer.getAsArray(ACTUAL_ARRAY_DATA_KEY);
			if ( arr!= null && arr.length()>0){
				
				T[] swapArr = ObjectGeneratorManager.getInstance().instantiateArray(baru.getClass().getName(), arr.length());
				
				retval.actualObject= swapArr ;
						
				for ( int i = 0 ; i < arr.length() ; i++){
					ParsedJSONContainer c =  arr.get(i);
					swapArr[i] =  baru.instantiateFromJSON(c); 
				}
				
			}
			 
		}
		else{
			 System.out.println(  "FQCN object tidak di temukan, kemungkinan object null . atau anda tidak menyertakan  (pls cek key : " +id.co.sigma.common.util.json.IJSONFriendlyObject.FQCN_MARKER_KEY +")\nJSON String :" + jsonContainer.getJSONString() ); 
		}
		return retval;
	}
	
	
	
	
	
	/**
	 * object actual
	 **/
	public T[] getActualObject() {
		return actualObject;
	}

	@Override
	public T[] getActualData() {
		return actualObject;
	}

	
	
	

}
