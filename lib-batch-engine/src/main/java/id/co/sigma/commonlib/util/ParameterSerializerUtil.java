package id.co.sigma.commonlib.util;

import id.co.sigma.commonlib.base.IJobParameterSerilzable;
import id.co.sigma.commonlib.base.serializer.AutoDetectToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.BigIntegerToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.DateToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.DoubleToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.LongToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.ObjectToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.SendToParameterField;
import id.co.sigma.commonlib.base.serializer.StringToJobParamSerializer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

/**
 * <i>Singleton class </i> <br/>
 * util untuk transfer dari POJO ke -&gt; {@link JobParameters} dan sebaliknya sebaliknya dari {@link JobParameters} -&gt; POJO 
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public final class ParameterSerializerUtil {
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(ParameterSerializerUtil.class); 
	
	@SuppressWarnings("rawtypes")
	protected static Map<String, ObjectToJobParamSerializer> PREDEFINED_SERIALIZER = new HashMap<String, ObjectToJobParamSerializer>();
	
	
	
	/**
	 * array of object kosong
	 **/
	private static final Object[] BLANK_ARRAY={}; 
	
	static {
		PREDEFINED_SERIALIZER.put(String.class.getName(), new StringToJobParamSerializer()); 
		
		PREDEFINED_SERIALIZER.put(long.class.getName(), new LongToJobParamSerializer());
		PREDEFINED_SERIALIZER.put(Long.class.getName(), new LongToJobParamSerializer());
		
		PREDEFINED_SERIALIZER.put(Double.class.getName(), new DoubleToJobParamSerializer());
		PREDEFINED_SERIALIZER.put(double.class.getName(), new DoubleToJobParamSerializer());
		
		PREDEFINED_SERIALIZER.put(BigInteger.class.getName(), new BigIntegerToJobParamSerializer());
		
		PREDEFINED_SERIALIZER.put(Date.class.getName(), new DateToJobParamSerializer());
		
	}
	
	@SuppressWarnings("rawtypes")
	protected static Map<String, ObjectToJobParamSerializer> INDEXED_CONVERTER = new HashMap<String, ObjectToJobParamSerializer>();
	
	private ParameterSerializerUtil(){
		
		
		
	}
	
	
	
	/**
	 * singleton instance
	 **/
	private static ParameterSerializerUtil instance ; 
	
	
	
	
	public static ParameterSerializerUtil getInstance() {
		if ( instance== null)
			instance = new ParameterSerializerUtil(); 
		return instance;
	}
	
	/**
	 * membaca data dari job parameters. variable di salin ke dalam POJO
	 * @param index index. kalau null, berarti single data
	 **/
	public void fetchParameterFromJobParameters (IJobParameterSerilzable batchDataWrapper, Integer index  ,  JobParameters params)throws IllegalArgumentException, IllegalAccessException, BeansException, InvocationTargetException{
		fetchParameterFromJobParametersRecursiveWorker(batchDataWrapper, batchDataWrapper.getClass(), index, params);
		
	}
	
	
	
	protected void fetchParameterFromJobParametersRecursiveWorker (IJobParameterSerilzable batchDataWrapper ,
			Class<?> currentClass  , Integer index  ,  JobParameters params)throws IllegalArgumentException, IllegalAccessException, BeansException, InvocationTargetException{
		
		Field [] allFields =  currentClass.getDeclaredFields(); 
		 
		
		String suffix = index==null? "" : (index +"") ; 
		
		if ( allFields!= null && allFields.length>0){
			for ( Field scn : allFields){
				
				if ( scn.isAnnotationPresent(SendToParameterField.class)){
					SendToParameterField ann = scn.getAnnotation(SendToParameterField.class) ; 
					String key =   ann.key() + suffix;
					
					Class<? extends ObjectToJobParamSerializer<?>>  cls =  ann.converter();
					if ( cls.getName().equals(AutoDetectToJobParamSerializer.class.getName())){
						String fldType =scn.getType().getName() ; 
						if (  !PREDEFINED_SERIALIZER.containsKey(fldType))
							throw new IllegalArgumentException("anda tidak menyiapkan field serializer untuk field   " + scn.getName() +"(class:" + currentClass.getName() +"), tipe field :" + scn.getType().getName() +" tidak di serial kan secara otomatis");
						
						//logger
						
						ObjectToJobParamSerializer ser = PREDEFINED_SERIALIZER.get(fldType) ;
						Object swp =  ser.get(params, key); 
						
						logger.debug("menaruh : " + swp + " untuk key : " + key + " , serializer  : " + ser.getClass().getName());
						
						BeanUtils.getPropertyDescriptor(currentClass, scn.getName()).getWriteMethod().invoke(batchDataWrapper, new Object[]{
								
								swp
						});
						
					}else{
						if (! INDEXED_CONVERTER.containsKey(cls.getName())){
							ObjectToJobParamSerializer init =  BeanUtils.instantiate(cls);
							INDEXED_CONVERTER.put(cls.getName(), init); 
						}
						BeanUtils.getPropertyDescriptor(currentClass, scn.getName()).getWriteMethod().invoke(batchDataWrapper, new Object[]{
								
								INDEXED_CONVERTER.get(cls.getName()).get( params,  key)
						});
						 
					}
					
				}
			}
		}
		Class<? > bapak =  currentClass.getSuperclass();
		if ( bapak!= null)
			fetchParameterFromJobParametersRecursiveWorker(batchDataWrapper, bapak, index, params);
		
	}
	
	/**
	 * ini untuk  append parameter ke dalam {@link JobParametersBuilder}. Kalau as array ini di put dengan mengincrement parameter 
	 * @param batchDataWrapper data dari kemana dari mana data di ambil untuk di taruh ke dalam batch parameter
	 *  
	 **/
	public void putParameterToParameterBuilder ( IJobParameterSerilzable batchDataWrapper, Integer index ,    JobParametersBuilder jobParameterBuilder)  throws Exception{
		
		putCurrentLevelParam(batchDataWrapper, batchDataWrapper.getClass(), index, jobParameterBuilder);
	}
	
	
	
	
	/**
	 * ini akan menarik field dalam 1 level. karena java.lang.Class.getDeclaredFields() hanya bisa membaca current class(parent class ndak ikut)
	 */
	protected void putCurrentLevelParam (     IJobParameterSerilzable batchDataWrapper ,Class<?> currentClass  , Integer index ,    JobParametersBuilder jobParameterBuilder)  throws Exception{
		Field [] allFields =  currentClass .getDeclaredFields(); 
		 
		if ( allFields!= null && allFields.length>0){
			for ( Field scn : allFields){
				if ( scn.isAnnotationPresent(SendToParameterField.class)){
					SendToParameterField ann = scn.getAnnotation(SendToParameterField.class) ; 
					String key =   ann.key()   + ( index== null ? "" : (index+"")  ) ;
					Class<? extends ObjectToJobParamSerializer<?>>  cls =  ann.converter();
					
					Object rslt =  BeanUtils.getPropertyDescriptor(batchDataWrapper.getClass(), scn.getName()).getReadMethod().invoke(batchDataWrapper, BLANK_ARRAY);
					
					
					logger.debug("menaruh :  " + rslt + ", untuk key : " + key);
					
					
					if ( cls.getName().equals(AutoDetectToJobParamSerializer.class.getName())){
						String fldType =scn.getType().getName() ; 
						if (  !PREDEFINED_SERIALIZER.containsKey(fldType))
							throw new IllegalArgumentException("anda tidak menyiapkan field serializer untuk field   " + scn.getName() +"(class:" + currentClass.getName() +"), tipe field :" + scn.getType().getName() +" tidak di serial kan secara otomatis");
						
						PREDEFINED_SERIALIZER.get(fldType).putToParam(rslt, jobParameterBuilder, key);
					}else{
						if (! INDEXED_CONVERTER.containsKey(cls.getName())){
							ObjectToJobParamSerializer init =  BeanUtils.instantiate(cls);
							INDEXED_CONVERTER.put(cls.getName(), init); 
						}
						
						INDEXED_CONVERTER.get(cls.getName()).putToParam(rslt, jobParameterBuilder, key);
					}
				}
			}
		}
		Class<?> bapak =  currentClass.getSuperclass();
		if ( bapak!= null)
			putCurrentLevelParam(batchDataWrapper, bapak, index, jobParameterBuilder);
	}
	
	
	

}
