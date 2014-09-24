/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.data.serializer.json;

import id.co.sigma.common.data.ObjectSerializer;
import id.co.sigma.common.data.serializer.IJSONWrapperObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 *
 * @author gps
 */
public class SimpleObjectWrapper implements  IJSONFriendlyObject<SimpleObjectWrapper> , IJSONWrapperObject<Object>{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8807369815434365759L;

	private String fqcn ; 
    
    private Object containedObject ; 
    
    public SimpleObjectWrapper(){
    }
            
    public SimpleObjectWrapper(Object sampleObject){
        
        if ( sampleObject==null)
            return ; 
        this.fqcn = sampleObject.getClass().getName(); 
        this.containedObject = sampleObject ; 
        
    }
    
    
    
    /**
     * menaruh object ke dalam contaoiner
     */
    public <T> T getContainedObject () {
        return (T)containedObject ; 
    }

    @Override
    public void translateToJSON(ParsedJSONContainer jsonContainerData) {
        jsonContainerData.put("fqcn", fqcn);
        
        
        if (! ObjectSerializerManager.isSimpleObject(fqcn)){
            throw new UnsupportedOperationException("hanya object sederhana yang bisa di konversikan ke dalam wrapper class :" + getClass().getName()  + ", class :" + fqcn +" tidak commply dengan ini"); 
        }
        ObjectSerializer sr =   ObjectSerializerManager.getInstance().getSerializer(fqcn); 
        String objectAsString = sr.serialize(containedObject); 
        
        jsonContainerData.put("objectAsString", objectAsString);
    }

    @Override
    public SimpleObjectWrapper instantiateFromJSON(ParsedJSONContainer jsonContainerData) {
        SimpleObjectWrapper retval = new SimpleObjectWrapper(); 
        retval.fqcn = jsonContainerData.getAsString("fqcn");
        String objectAsString = jsonContainerData.getAsString("objectAsString");
        if ( retval.fqcn == null || retval.fqcn.isEmpty() ||  objectAsString==null || ! ObjectSerializerManager.isSimpleObject(retval.fqcn)){
            retval.containedObject = null ; 
        }
        else{
           ObjectSerializer sr =  ObjectSerializerManager.getInstance().getSerializer(retval.fqcn); 
           retval.containedObject =  sr.deserialize( objectAsString); 
        }    
        
        
        return retval; 
        
    }

	@Override
	public Object getActualData() {
		return this.containedObject;
	}
    
    
}
