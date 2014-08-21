package id.co.sigma.common.server.util.strings.fixedlength;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public final class FixedLengthUtils {

	
	private static FixedLengthUtils instance ;
	
	
	protected class FixedLengthFieldDef {
		
		  Field field ; 
		  CustomFixedLengthSerializer serializerObject ;
		
		
		/**
		 * urutan field dalam fixed length. di posisi ke berapa
		 */
		int index  ; 
		
		
		
		/**
		 * berapa panjang dari string yang di inginkan
		 */
		int stringMaxLength;  
		
		
		 
		
		
		
	 
	}
	
	
	
	private Map<Class<?>, ArrayList<FixedLengthFieldDef>> indexedDefs ; 
	
	
	
	private Map<Class<? extends CustomFixedLengthSerializer>, CustomFixedLengthSerializer> cachedSerializer ; 
	
	private FixedLengthUtils(){
		indexedDefs = new HashMap<Class<?>, ArrayList<FixedLengthFieldDef>>() ; 
		cachedSerializer = new HashMap<Class<? extends CustomFixedLengthSerializer>, CustomFixedLengthSerializer>() ; 
		
	}
	
	public static FixedLengthUtils getInstance() {
		if ( instance == null)
			instance = new FixedLengthUtils(); 
		return instance;
	}
	
	
	
	
	public String generateFixedLengthString ( Object targetObject ) throws Exception{
		if ( targetObject== null)
			return null ; 
		if ( !indexedDefs.containsKey(targetObject.getClass())){
			buildAndCacheMetadata(targetObject);
		}
		StringBuffer buffer = new StringBuffer();
		
		for ( FixedLengthFieldDef scn : indexedDefs.get(targetObject.getClass())){
			if ( scn.field.isAccessible()){
				Object fieldVal = scn.field.get(targetObject);
				buffer.append(  scn.serializerObject.serialize(fieldVal, scn.stringMaxLength)); 
			}else{
				scn.field.setAccessible(true);
				Object fieldVal = scn.field.get(targetObject);
				buffer.append(  scn.serializerObject.serialize(fieldVal, scn.stringMaxLength)); 
				
				scn.field.setAccessible(false);
			}
			
		}
		return buffer.toString(); 
	}
	
	
	protected void buildAndCacheMetadata ( Object targetObject ){
		
		ArrayList<FixedLengthFieldDef> defs = new ArrayList<FixedLengthUtils.FixedLengthFieldDef>(); 
		
		
		Class<?> current = targetObject.getClass() ; 
		indexedDefs.put(current, defs);
		while ( current != null &&  !Object.class.equals( current)){
			readClassMetadata(defs , current );
			current = current.getSuperclass(); 
		}
		Collections.sort(  defs , new Comparator<FixedLengthFieldDef>() {
			@Override
			public int compare(FixedLengthFieldDef o1, FixedLengthFieldDef o2) {
				return o1.index < o2.index ? -1
				         : o1.index > o2.index ? 1
				         : 0;
			}
		}  );
		 
	}
	
	
	protected void readClassMetadata ( ArrayList<FixedLengthFieldDef> targetCache , Class<?> classToRead  ){
		Field[] allFields =  classToRead.getDeclaredFields();
		for ( Field scn :  allFields){
			if ( scn.isAnnotationPresent(FixedLengthField.class)){
				FixedLengthField t = scn.getAnnotation(FixedLengthField.class) ;
				FixedLengthFieldDef def = new FixedLengthFieldDef(); 
				 
				def.field = scn ; 
				def.index = t.index() ; 
				def.stringMaxLength = t.stringMaxLength();
				targetCache.add(def);
				Class<? extends CustomFixedLengthSerializer> srlsCls =  t.serializer(); 
				if ( cachedSerializer.containsKey(srlsCls))
					def.serializerObject = cachedSerializer.get(srlsCls); 
				else{
					CustomFixedLengthSerializer s = BeanUtils.instantiate(srlsCls); 
					cachedSerializer.put(srlsCls, s); 
					def.serializerObject = s ; 
				}
				
			}
		}
	}
	
	
}
