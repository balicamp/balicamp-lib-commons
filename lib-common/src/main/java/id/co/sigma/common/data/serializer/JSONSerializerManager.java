package id.co.sigma.common.data.serializer;

import java.util.List;

import id.co.sigma.common.data.serializer.json.ListDataWrapperJSONFriendlyType;
import id.co.sigma.common.data.serializer.json.ListDataWrapperPrimitiveType;
import id.co.sigma.common.data.serializer.json.NonPrimitiveArrayDataWrapper;
import id.co.sigma.common.data.serializer.json.ObjectSerializerManager;
import id.co.sigma.common.data.serializer.json.PrimitiveArrayDataWrapper;
import id.co.sigma.common.data.serializer.json.SimpleObjectWrapper;
import id.co.sigma.common.util.json.IJSONFriendlyObject;

/**
 * serialzer json. untuk proses konversi dari object ke json dan sebaliknya 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class JSONSerializerManager {
	
	private static JSONSerializerManager instance  ; 
	
	
	public static JSONSerializerManager getInstance() {
		if ( instance == null)
			instance = new JSONSerializerManager(); 
		return instance;
	}
	
	
	
	 
	
	
	/**
	 * konversi dari object menjadi raw json
	 **/
	public IJSONFriendlyObject<?> transalateToFriendlyObject (  Object object) {
		if ( object == null)
			return null ;
		
		//1. string, integer, atau objecy sederhana
		if ( ObjectSerializerManager.isSimpleObject(object.getClass().getName())) 
			return new SimpleObjectWrapper(object);
		//2. array of simple object
		if ( ObjectSerializerManager.getInstance().isArrayofSimpleObject(object.getClass().getName())){
			return new PrimitiveArrayDataWrapper((Object[])object);
		}
		//3. json convertable object
		if ( object instanceof IJSONFriendlyObject<?>) // json friendly data. 1 data
			return (IJSONFriendlyObject<?> ) object ; 
		//4. array of json friendlu object
		if ( object instanceof IJSONFriendlyObject<?>[]){
			IJSONFriendlyObject<?>[] arr = (IJSONFriendlyObject<?>[])object ; 
			return new NonPrimitiveArrayDataWrapper(arr);
		}
		//java.util.List
		if ( object instanceof List<?>){
			List<?> objects = (List<?>)object ;
			
			
			boolean simpleType  = false; 
			 boolean match = false ; 
			
			
			if ( objects== null|| objects.isEmpty())
				return null ;
			
			for ( Object scn : objects){
				if ( scn == null)
					continue ; 
				
				String fqcn = scn.getClass().getName(); 
				if ( ObjectSerializerManager.isSimpleObject(fqcn)){
					// simple array
					simpleType = true ; 
					match = true ; 
					break ; 
				}
				else if (  (scn instanceof IJSONFriendlyObject<?>)){
					match = true ;
					break ; 
				}
				else{
					throw new RuntimeException("object   tidak bisa di serialize kan. argument RPC hanya di ijinkan : primitive, array of primitive, IJSONFriendlyObject atau List of IJSONFriendlyObject. di luar itu akan di reject" );
				}
			}
			if (! match){
				throw new RuntimeException("object   tidak bisa di serialize kan. argument RPC hanya di ijinkan : primitive, array of primitive, IJSONFriendlyObject atau List of IJSONFriendlyObject. di luar itu akan di reject" );
			}
			if ( simpleType){
				return new ListDataWrapperPrimitiveType(objects);
			}else{
				return new ListDataWrapperJSONFriendlyType((List<IJSONFriendlyObject<?>>)objects); 
			}
			
			
		}
		throw new RuntimeException("object   tidak bisa di serialize kan. argument RPC hanya di ijinkan : primitive, array of primitive, IJSONFriendlyObject atau List of IJSONFriendlyObject. di luar itu akan di reject" );
		
	} 
	

}
