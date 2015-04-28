package id.co.sigma.common.server.util;

import id.co.sigma.common.util.IBeanObjectDefinitionProvider;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * bean utils dengan tambahan method
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 * @since 7 aug 2012
 **/
public class ExtendedBeanUtils extends BeanUtils implements IBeanObjectDefinitionProvider{
	private static ExtendedBeanUtils instance;
	
	private static Logger logger = LoggerFactory.getLogger(ExtendedBeanUtils.class);
	
	/**
	 * copy property dari bean 1 ke bean lain, di batasi hanya pada property yang di assign saja
	 **/
	public  void copyPropertiesWithSpecifiedItemOnly(Object source, Object target, String[] copiedProperties){
		if ( source==null|| target==null)
			return ; 
		PropertyDescriptor [] desc =  BeanUtils.getPropertyDescriptors(source.getClass());
		
		Map<String, Integer> dummyIndexer = new HashMap<String, Integer>(); 
		for ( String p : copiedProperties){
			dummyIndexer.put(p, 1) ; 
		
		}
		List<String> ignored = new ArrayList<String>(); 
		for ( PropertyDescriptor scn : desc){
			//System.out.println(" prop : "+scn.getName());
			if ( !dummyIndexer.containsKey(scn.getName())){ 
				ignored.add(scn.getName()); 
				
			}
		}
		String [] ignoredProps = new String[ignored.size()]; 
		ignored.toArray(ignoredProps) ;
		
		copyProperties(source, target, ignoredProps); 
	}
	
	/**
	 * copy bean antara 2 List<BEAN>
	 * @param source ; list asal
	 * @param target ; list tujuan
	 * @param cl ; class dari target(tujuan)
	 * @param copiedProperties
	 */
	public <SOURCE,DEST> void copyListPropertiesWithSpecifiedItemOnly(List<SOURCE> source, List<DEST> target, DEST classTarget, String[] copiedProperties){
		
		for(SOURCE obj : source ){
			
			@SuppressWarnings("unchecked")
			DEST ds = (DEST) BeanUtils.instantiate(classTarget.getClass());
			copyPropertiesWithSpecifiedItemOnly(obj, ds, copiedProperties);
			target.add(ds);
		}

	}
	
	
	
	
	/**
	 * method ini membaca meta data class , di batasi dengan field yang di kirimkan
	 **/
	public ArrayList<Class<?>>  readPropertyTypeAsArray(Class<?>  source , String[] fields ){
		if ( source==null||fields==null||fields.length==0)
			return null ;
		ArrayList<Class<?>> retval = new ArrayList<Class<?>>();
		for ( int i=0; i<fields.length;i++){
			String scn =  fields[i];
			if ( scn.contains(".")){
				Class<?>  current = source ; 
				String[] arr =  scn.split("\\.");
				try {
					for ( String chld : arr){
						PropertyDescriptor prp =  BeanUtils.getPropertyDescriptor(current, chld);
						current = prp.getPropertyType();
					}
					retval.add(current);
					
				} catch (Exception e) {
					retval.add(null);
				}
			}else{
				PropertyDescriptor prp =  BeanUtils.getPropertyDescriptor(source, scn);
				if ( prp!=null)
					retval.add(prp.getPropertyType());
				else// not found
					retval.add(null);
			}
		}
		return retval ; 
	}
	
	
	
	
	/**
	 * set property dari java object. object di set dengan akses ke bean. 
	 * @param targetToSet object yang hendak diset 
	 * @param valueToSet value yang di set ke dalam object
	 * @param fieldToSet field yang hendak di set
	 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
	 */
	public void setProperty (Object targetToSet , Object valueToSet  , String fieldToSet)throws Exception  {
		if ( targetToSet== null){
			logger.debug("object di pass null, tidak ada tindakan yang di lakukan");
		}
		Object actualTarget  = targetToSet ;
		
		String actuaLField = fieldToSet ; 
		
		if ( fieldToSet.contains(".")){
			String[] pathParents =  fieldToSet.split("\\.");
			actuaLField = pathParents[pathParents.length-1]; 
			for ( int i = 0 ; i < pathParents.length-1 ; i++){
				actualTarget = getProperty(actualTarget, pathParents[i]); 
				if ( actualTarget== null)
					break ; 
			}
		}
		
		if ( actualTarget== null){
			logger.error("gagal untuk menset object :" + targetToSet.getClass() +", path :"  + fieldToSet +".path null. unutk nested object, anda perlu memastikan parent tidak null terlebih dahulu");
		}
		Method writeMethod = null ; 
		
		try {
			writeMethod =  getPropertyDescriptor(actualTarget.getClass(), actuaLField).getWriteMethod();
		} catch (Exception e) {
			logger.error("gagal membaca property desc untuk class : " +targetToSet.getClass().getName() + ", property :" + fieldToSet +",error : " + e.getMessage() /*, e  */); 
			throw e  ; 
		}
		
		Class<?>[] params = writeMethod.getParameterTypes();
		try {
			if(params != null && params.length == 1) {
				Class<?> type = params[0];
				if(type.getName().equals(BigInteger.class.getName()) && !(valueToSet instanceof BigInteger)) {
					valueToSet = new BigInteger(valueToSet.toString());
				} else if(type.getName().equals(BigDecimal.class.getName()) && !(valueToSet instanceof BigDecimal)) {
					valueToSet = new BigDecimal(valueToSet.toString());
				} else if((type.getName().equals(Long.class.getName()) || type.getName().equals(long.class.getName()))&& !(valueToSet instanceof Long)) {
					valueToSet = Long.valueOf(valueToSet.toString());
				} else if((type.getName().equals(Float.class.getName()) || type.getName().equals(float.class.getName())) && !(valueToSet instanceof Float)) {
					valueToSet = Float.valueOf(valueToSet.toString());
				} else if((type.getName().equals(Integer.class.getName()) || type.getName().equals(int.class.getName())) && !(valueToSet instanceof Integer)) {
					valueToSet = Integer.valueOf(valueToSet.toString());
				} else if((type.getName().equals(Double.class.getName()) || type.getName().equals(double.class.getName())) && !(valueToSet instanceof Double)) {
					valueToSet = Double.valueOf(valueToSet.toString());
				} else if((type.getName().equals(Short.class.getName()) || type.getName().equals(short.class.getName())) && !(valueToSet instanceof Short)) {
					valueToSet = Short.valueOf(valueToSet.toString());
				} else if((type.getName().equals(Boolean.class.getName()) || type.getName().equals(boolean.class.getName())) && !(valueToSet instanceof Boolean)) {
					String sbool = valueToSet.toString();
					valueToSet = Boolean.valueOf(("0".equals(sbool) || "".equals(sbool)) ? "false" : "true");					
				} else if(type.isEnum()) {
					if(valueToSet instanceof String) {
						try {
							int ordinal = Integer.valueOf((String)valueToSet);
							valueToSet = type.getEnumConstants()[ordinal];
						} catch (Exception e) {
							Object val = null;
							for(Object econs : type.getEnumConstants()) {
								if((valueToSet != null) && econs.toString().equals(valueToSet.toString())) {
									val = econs;
									break;
								}
							}
							valueToSet = val;
						}
					} else if(valueToSet instanceof Number) {
						int ordinal = ((Number)valueToSet).intValue();
						valueToSet = type.getEnumConstants()[ordinal];
					} else {
						valueToSet = null;
					}
				}
			}
		} catch(Exception e){
			logger.warn(e.getMessage());
		}
		getPropertyDescriptor(actualTarget.getClass(), actuaLField).getWriteMethod().invoke(actualTarget, new Object[]{
				valueToSet
		}); 
		
	}
	
	
	/**
	 * method ini akan men-null kan beberapa fields. tujuan nua menghindari LIE, dan mengirim path yang terlalu dalam ke client
	 **/
	public void setPropertiesToNull(Object  source , String[] fields ){
		
		if ( source==null||fields==null||fields.length==0)
			return  ;
		for ( int i=0; i<fields.length;i++){
			String scn =  fields[i];
			if ( scn.contains(".")){
				//bukan simple
				String[] arr =  scn.split("\\.");
				Object current = source ; 
				try {
					// kompleks property use DOT
					PropertyDescriptor latestDescrptor =null; 
					Object oneLevelBefore = source ; 
					for ( String propCurr  : arr){
						oneLevelBefore=current;
						latestDescrptor = BeanUtils.getPropertyDescriptor(current.getClass(), propCurr) ;
						current = latestDescrptor.getReadMethod().invoke(current); 
					}
					if ( latestDescrptor!=null &&oneLevelBefore!=null){
						if (latestDescrptor.getReadMethod().getReturnType().isPrimitive())
							continue ;
						latestDescrptor.getWriteMethod().invoke(oneLevelBefore,new Object[]{ null});
					} 
				} catch (Exception e) {
					
				}
				
			}else{
				try {
					PropertyDescriptor a = BeanUtils.getPropertyDescriptor(source.getClass(), scn) ;
					if (a.getReadMethod().getReturnType().isPrimitive())
						continue ;
					a.getWriteMethod().invoke(source,new Object[]{ null});
				} catch (Exception e) {
					logger.error("gagal membangun DTO. source class : " + source.getClass() + " , error di laporkan :"  + e.getMessage() , e);
				}
				
			}
			 
			
			
		}
		 
	}
	
	/**
	 * Method ini akan me-null kan semua field selain field yang dikirimkan sebagai parameter (fields) 
	 * Tujuannya untuk menghindari LIE, dan mengirim path yang terlalu dalam ke client
	 **/
	public void setPropertiesToNullInversed(Object  source , String[] fields ){
		
		PropertyDescriptor[] arr = getPropertyDescriptors(source.getClass());
		List<String> tobeNulledList = new ArrayList<String>();
		boolean isTobeNulled = true;
		
		for (PropertyDescriptor scn : arr){
			for ( int i = 0; i < fields.length; i++){
				if(scn.getName().equalsIgnoreCase(fields[i])){
					i = fields.length;
					isTobeNulled = false;
				}
			}
			
			if(isTobeNulled){
				tobeNulledList.add(scn.getName());
			}
			
			isTobeNulled = true;
		}
		
		setPropertiesToNull(source, tobeNulledList.toArray(new String[tobeNulledList.size()]));
		
	}
	
	
	
		
	/**
	 * method ini membaca isi dari object ke dalam array of object. 
	 * @param source dari mana data di baca
	 * @param fields field-field yang di baca. ini sesuai dengan setter/getter saja
	 **/
	public Object[] readPropertyAsArray(Object  source , String[] fields ){
		if ( source==null||fields==null||fields.length==0)
			return null ;
		 
		
		Object[] values = new Object[fields.length];
		
		for ( int i=0; i<fields.length;i++){
			String scn =  fields[i];
			if ( scn.contains(".")){
				values[i] = readComplexProperty(source, scn);
			}else{
				try {
					PropertyDescriptor a = BeanUtils.getPropertyDescriptor(source.getClass(), scn) ;
					values[i] = a.getReadMethod().invoke(source);
				} catch (Exception e) {
					logger.error("gagal membangun DTO. source class : " + source.getClass() + " , error di laporkan :"  + e.getMessage() , e);
				}
				
			}
			 
			
			
		}
		 
		return values ; 
		
	} 
	
	
	
	/**
	 * membaca property complex. property complex dalam arti : hirarki object yang di baca nested, dalem posisi nya
	 * @param object object yang di baca property nya
	 * @param complextPropertyArg property yang hendak di baca
	 **/
	public Object readComplexProperty (Object object , String complextPropertyArg){
		String[] arr =  complextPropertyArg.split("\\.");
		Object current = object ; 
		try {
			// kompleks property use DOT
			for ( String propCurr  : arr){
				PropertyDescriptor a = BeanUtils.getPropertyDescriptor(current.getClass(), propCurr) ;
				current = a.getReadMethod().invoke(current);
				if ( current==null)
					return null ; 
			}
			return current ;  
		} catch (Exception e) {
			return null ;  
		}
	}
	
	
	
	
	
	
	/**
	 * replace property pada object menjadi actual. kasusnnya dalam dunia JPA, class nya mnejadi java_assist. Object musti di set menjadi real class nya agar tidak ada error
	 * @param target object yang mau di replace
	 * @param propertyName nama getter/setter yang mau di replace
	 * 
	 **/
	public void makeRealObjectOnSpecifiedProperty (Object target , String propertyName) throws Exception{
		PropertyDescriptor desc =  getPropertyDescriptor(target.getClass(), propertyName);
		Object swap = desc.getReadMethod().getReturnType().getClass().newInstance();
		copyProperties(desc.getReadMethod().invoke(target), swap); 
		desc.getWriteMethod().invoke(target, swap);
	}
	
	
	
	
	/**
	 * 
	 * versi smart. bisa membaca kalau misalnya ada .(dot) maka akan memakai{@link #readComplexProperty(Object, String)} 
	 */
	public Object getPropertyBlind (Object beanObject , String propertyName ) {
		if ( propertyName.contains(".")){
			return readComplexProperty(beanObject, propertyName); 
		}else {
			return getProperty(beanObject, propertyName); 
		}
			
	}
	
	
	/**
	 * membaca property dari bean object. versi ini cuma bisa membaca 1 level
	 */
	public Object getProperty (Object beanObject , String propertyName ) {
		if ( beanObject== null)
			return null ; 
		try{
			PropertyDescriptor desc =  getPropertyDescriptor(beanObject.getClass(), propertyName);
			return desc.getReadMethod().invoke(beanObject, new Object[]{});
		}catch ( Exception exc) {
			logger.error("gagal membaca property :" + propertyName + ", error : "  + exc.getMessage() , exc);
			return null ; 
		}
		 
	}
	
//	public static <T,H> void getLast(T[] array) {
//		T
//        int length = array.length;
//        //return array[length - 1];
//    }
	
	/**
	 * get instance ; singleton
	 **/
	public static ExtendedBeanUtils getInstance() {
		if ( instance==null)
			instance=new ExtendedBeanUtils();
		return instance;
	}
}
