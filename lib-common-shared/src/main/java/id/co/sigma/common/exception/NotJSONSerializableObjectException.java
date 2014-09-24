/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.exception;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 *
 * @author gps
 */
public class NotJSONSerializableObjectException extends  AbstractJSONSerializableException implements IJSONFriendlyObject<NotJSONSerializableObjectException>{
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -491645911508445788L;
	/**
     * FQCN dari object yang gagal di serialize
     */
    private String fqcn ; 
    
    public NotJSONSerializableObjectException(Class<?> nonSerializableClass){
    	 this(nonSerializableClass.getName());
        
    }
    
    public NotJSONSerializableObjectException(String fqcn){
    	super("Class : " +fqcn + ", bukan merupakan class instance dari : " + IJSONFriendlyObject.class.getName() +", dan juga bukan object yang serializable via simple serialization. mohon perbaiki code ini");
    }
    /**
     * FQCN dari object yang gagal di serialize
     */
    public void setFqcn(String fqcn) {
        this.fqcn = fqcn;
    }
    
    
    /**
     * FQCN dari object yang gagal di serialize
     */
    public String getFqcn() {
        return fqcn;
    }
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		putMemberVariableToJson(jsonContainerData);
		jsonContainerData.put("fqcn", fqcn);
	}
	@Override
	public NotJSONSerializableObjectException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		NotJSONSerializableObjectException retval = new NotJSONSerializableObjectException(jsonContainer.getAsString("fqcn"));
		this.fetchVariableFromJson(retval, jsonContainer);
		return retval;
	}
	
	
	
    
    
}
