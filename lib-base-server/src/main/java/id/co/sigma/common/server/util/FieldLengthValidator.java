package id.co.sigma.common.server.util;

import id.co.sigma.common.annotation.StringValidator;
import id.co.sigma.common.exception.DataValidationException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldLengthValidator {
	
	
	
	static Map<Class<?>, Field[]> CACHED_FIELDS = new HashMap<Class<?>, Field[]>() ; 
	
	
	private Field[] listFieldWithValidator(Class<?> clazz) throws Exception {
		if ( CACHED_FIELDS.containsKey(clazz))
			return CACHED_FIELDS.get(clazz);
		List<Field> fieldsWithVaidator = new ArrayList<Field>();
		
		fetchAnnotatedFields(clazz, fieldsWithVaidator); 
		Field[] retval = new Field[fieldsWithVaidator.size()];
		fieldsWithVaidator.toArray(retval);
		return retval;
	}
	
	
	
	public void fetchAnnotatedFields (Class<?> clazz ,List<Field> containers) {
		Class<?> clsParent =   clazz.getSuperclass();
		if ( clsParent!= null){
			fetchAnnotatedFields(clsParent, containers);
		}
		
		Field[] fields = clazz.getDeclaredFields();
		boolean annotationFound = false;
		
		for(Field f : fields) {
			annotationFound = f.isAnnotationPresent(StringValidator.class);
			
			if(annotationFound) {
				containers.add(f);
			}
			
		}
		 
		
	}
	
	/**
	 * Validasi lebar data dari sebuah field (sesuai dengan lebar data pada database).
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void validate(Object data, int rowNuber) throws Exception {
		Class<?> clazz = data.getClass();
		Field[] fields = listFieldWithValidator(clazz);
		
		String exceptionMessage = new String();
		
		for(Field f : fields) {
			if(!f.isAccessible())
				f.setAccessible(true);
			
			if(f.isAnnotationPresent(StringValidator.class)) {
				
				StringValidator metadata = f.getAnnotation(StringValidator.class);
				
				
				int maxFieldLength = metadata.length();
				String businessColumnName = metadata.businessColumnName();
				boolean mandatory = metadata.mandatory();
				
				String fieldValue = (String) f.get(data);
				
				if(mandatory && (fieldValue == null || fieldValue.isEmpty())) {
					exceptionMessage += "Baris "+rowNuber+", Kolom "+businessColumnName+" kosong. Kolom ini mandatory.\n";
				} else {
					
					if(fieldValue.length() > maxFieldLength) {
						exceptionMessage += "Baris "+rowNuber+", Kolom "+businessColumnName+", panjang data = "
								+fieldValue.length()+", maximal panjang data adalah "+maxFieldLength+"\n";
					}
				}
				
				
				
			}
			
		}
		
		if(!exceptionMessage.isEmpty())
			throw new DataValidationException(exceptionMessage);
		
	}

}
