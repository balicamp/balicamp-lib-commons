package id.co.sigma.common.data.lov;



import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 
 * versi ini untuk LOV header yang merupakan child dari LOV lain nya. jadinya data nested. data ini merupakan sub dari LOV lain nya
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public class Common2ndLevelLOVHeader   implements IsSerializable, IJSONFriendlyObject<Common2ndLevelLOVHeader>{
	
	
	
	
	protected String lovId ; 
	
	
	/**
	 * internalization code
	 **/
	protected String i18Key ; 
	protected String lovRemark ; 
	protected String version ;
	
	protected boolean cacheable ; 
	
	/**
	 * value dari parent Lookup
	 **/
	protected String parentLOVValue  ;
	
	
	/**
	 * lookup values sesuai dengan parent lov value yang di masukan dalam {@link #parentLOVValue}
	 **/
	private List<Common2ndLevelLOV> lookupValues ; 
	
	/**
	 * value dari parent Lookup
	 **/
	public void setParentLOVValue(String parentLOVValue) {
		this.parentLOVValue = parentLOVValue;
	}
	/**
	 * value dari parent Lookup
	 **/
	public String getParentLOVValue() {
		return parentLOVValue;
	}

	
	/**
	 * internalization code
	 **/
	public void setI18Key(String i18Key) {
		this.i18Key = i18Key;
	}
	/**
	 * internalization code
	 **/
	public String getI18Key() {
		return i18Key;
	}
	
	public String getLovId() {
		return lovId;
	}
	public void setLovId(String lovId) {
		this.lovId = lovId;
	}
	
	public String getLovRemark() {
		return lovRemark;
	}
	public void setLovRemark(String lovRemark) {
		this.lovRemark = lovRemark;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	public boolean isCacheable() {
		return cacheable;
	}
	
	
	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}
	
	
	

	/**
	 * lookup values sesuai dengan parent lov value yang di masukan dalam {@link #parentLOVValue}
	 **/
	public void setLookupValues(List<Common2ndLevelLOV> lookupValues) {
		this.lookupValues = lookupValues;
	}
	/**
	 * lookup values sesuai dengan parent lov value yang di masukan dalam {@link #parentLOVValue}
	 **/
	public List<Common2ndLevelLOV> getLookupValues() {
		return lookupValues;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("cacheable",isCacheable());
		jsonContainer.put("i18Key",getI18Key());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		 
		  List<id.co.sigma.common.data.lov.Common2ndLevelLOV> param4 = getLookupValues() ; 
		 if (  param4 != null && !param4.isEmpty()){ 
			for ( id.co.sigma.common.data.lov.Common2ndLevelLOV scn : param4){
				//1. Ok tampung dulu variable
//2. null kan variable 
// 3 taruh ke json

					jsonContainer.appendToArray("lookupValues", scn);
				//4. restore lagi 

				}
		}
		jsonContainer.put("lovId",getLovId());
		jsonContainer.put("lovRemark",getLovRemark());
		jsonContainer.put("parentLOVValue",getParentLOVValue());
		jsonContainer.put("version",getVersion());
	}
	
	@Override
	public Common2ndLevelLOVHeader instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		Common2ndLevelLOVHeader retval = new Common2ndLevelLOVHeader();
		Boolean cacheableJson = jsonContainer.get("cacheable" ,  boolean.class.getName());
		retval.setCacheable(cacheableJson == null ? false : cacheableJson );
		retval.setI18Key( (String)jsonContainer.get("i18Key" ,  String.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setLovId( (String)jsonContainer.get("lovId" ,  String.class.getName()));
		retval.setLovRemark( (String)jsonContainer.get("lovRemark" ,  String.class.getName()));
		retval.setParentLOVValue( (String)jsonContainer.get("parentLOVValue" ,  String.class.getName()));
		retval.setVersion( (String)jsonContainer.get("version" ,  String.class.getName()));
		return retval; 
	}
	
}
