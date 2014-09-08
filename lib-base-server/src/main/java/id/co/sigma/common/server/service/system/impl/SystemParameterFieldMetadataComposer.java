package id.co.sigma.common.server.service.system.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import id.co.sigma.common.annotation.SystemParamDrivenField;
import id.co.sigma.common.data.SimpleSytemParameterDrivenValue;
import id.co.sigma.common.data.SystemParamDrivenClass;
import id.co.sigma.common.data.app.SystemSimpleParameter;
import id.co.sigma.common.server.service.system.ISimpleParameterEncryption;


/**
 * composite dari SystemParameterFieldMetadata
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class SystemParameterFieldMetadataComposer<D extends SystemParamDrivenClass<?,?>> {


	
	
	private static final Logger logger = LoggerFactory.getLogger(SystemParameterFieldMetadataComposer.class); 
	
	
	
	/**
	 * meta data di index by java field name
	 * key : field name
	 */
	private Map<String, SystemParameterFieldMetadata> indexedMetadataByFieldName = new HashMap<String, SystemParameterFieldMetadata>() ;
	
	
	
	/**
	 * key of : id.co.sigma.common.data.app.SystemSimpleParameter yang di pergunakan dalm clzz ini
	 */
	private String [] systemParamKeys ; 
	
	
	
	/**
	 * class yang di handle composer ini
	 */
	private Class<D> handledClass ; 
	
	/**
	 * key : key pada id.co.sigma.common.data.app.SystemSimpleParameter
	 * value : nama java field yang memakai key tsb
	 */
	private Map<String,  String> indexedFieldBySystemParamKey = new HashMap<String,   String>(); 
	
	/**
	 * simple encryptor
	 */
	private ISimpleParameterEncryption simpleParameterEncryption ; 
	/**
	 * compose class
	 */
	public SystemParameterFieldMetadataComposer( Class<D> clzz){
		handledClass = clzz ; 
		readThisLevelmetaData(clzz);
	
	}
	
	
	
	
	
	private void readThisLevelmetaData (Class<? extends SystemParamDrivenClass<?,?>> clzz  ){
		Field[] allFields =  clzz.getDeclaredFields();
		@SuppressWarnings("rawtypes")
		SimpleSytemParameterDrivenValue smpl =  BeanUtils.instantiate(clzz).instantiateProvider(); 
		for ( Field scn : allFields ){
			if (!scn.isAnnotationPresent(SystemParamDrivenField.class)){
				continue ; 
			}
			SystemParamDrivenField f =  scn.getAnnotation(SystemParamDrivenField.class);
			String key = f.key();
			if (indexedMetadataByFieldName.containsKey(key) ){
				logger.error("untuk class : " + clzz.getName() +", db key : " + key + " sudah di pergunakan dalam field :" + indexedMetadataByFieldName.get(key) +", ini duplikasi dengan field : " + scn.getName() +", field : " + scn.getName()  + " tidak akan di map");
				continue ; 
			}
			SimpleSytemParameterDrivenValue<?> d =   smpl.instantiateFromString(f.key()); 
			if ( d== null){
				logger.error("dari class : " + clzz.getClass().getName() +", tidak bisa instantiate dari key : " + f.key() +", silakan cek enum anda. apakah key tersebut sudah tersedia atau tidak");
				continue ; 
			}
			
			SystemParameterFieldMetadata s = new SystemParameterFieldMetadata(); 
			s.setFieldName(scn.getName());
			s.setParameterDefinition(d);
			indexedMetadataByFieldName.put(scn.getName() , s);
			indexedFieldBySystemParamKey.put(d.getParameterKey(), scn.getName()); 
			//ind
		}
		systemParamKeys = new String[indexedFieldBySystemParamKey.size()];
		indexedFieldBySystemParamKey.keySet().toArray(systemParamKeys); 
		
	}
	
	
	public String[] getSystemParamKeys() {
		return systemParamKeys;
	}
	
	
	/**
	 * menaruh SystemSimpleParameter ke dalam object
	 */
	public void putValueToObject ( D target , Collection<SystemSimpleParameter> params )  throws Exception{
		if ( target == null || params== null || params.isEmpty())
			return ; 
		for (SystemSimpleParameter scn : params   ) {
			String fieldName =  indexedFieldBySystemParamKey.get(scn.getId());  
			SystemParameterFieldMetadata md = indexedMetadataByFieldName.get(fieldName); 
			
			String rawValue =scn.getValueRaw() ;  
			if ( md.getParameterDefinition().isEncrypted() && simpleParameterEncryption!= null){
				rawValue = simpleParameterEncryption.decrypt(rawValue); 
			}
			BeanUtils.getPropertyDescriptor(handledClass, fieldName).getWriteMethod().invoke(target, new Object[]{
					rawValue 
			}); 
		}
	}
	
	
	
	/**
	 * membaca data dari parameter yang di pass
	 */
	public List<SystemSimpleParameter> extractParameter( D valueHolder)  throws Exception{
		ArrayList<SystemSimpleParameter> retval = new ArrayList<SystemSimpleParameter>();
		Object[] emptyArr = {} ; 
		for ( String scn  : indexedMetadataByFieldName.keySet()){
			SystemParameterFieldMetadata def = indexedMetadataByFieldName.get(scn);
			
			
			
			
			String s =(String) BeanUtils.getPropertyDescriptor(handledClass, scn).getReadMethod().invoke(valueHolder, emptyArr);
			
			if ( def.getParameterDefinition().isEncrypted() && simpleParameterEncryption!= null){
				s  = simpleParameterEncryption.encrypt( s); 
			}
			SystemSimpleParameter prm = new SystemSimpleParameter();
			prm.setParamType(String.class.getName());
			prm.setId(def.getParameterDefinition().getParameterKey());
			prm.setRemark(def.getParameterDefinition().getParameterDesc());
			prm.setEditableFlag(  def.getParameterDefinition().isEditable()?"Y":"N");
			
			prm.setValueRaw(s);
			retval.add(prm); 
		}
		return retval ; 
	}
	
	
	
	/**
	 * class yang di handle composer ini
	 */
	public Class<? extends SystemParamDrivenClass<?,?>> getHandledClass() {
		return handledClass;
	}
	
	/**
	 * simple encryptor
	 */
	public ISimpleParameterEncryption getSimpleParameterEncryption() {
		return simpleParameterEncryption;
	}
	/**
	 * simple encryptor
	 */
	public void setSimpleParameterEncryption(
			ISimpleParameterEncryption simpleParameterEncryption) {
		this.simpleParameterEncryption = simpleParameterEncryption;
	}
}
