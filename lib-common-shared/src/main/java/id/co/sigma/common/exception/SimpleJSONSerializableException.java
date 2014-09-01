/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.exception;


import id.co.sigma.common.util.ObjectGeneratorManager;
import id.co.sigma.common.util.SimpleDebugerWriter;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;
import id.co.sigma.common.util.json.SharedServerClientLogicManager;

/**
 *
 * @author gps
 */
public class SimpleJSONSerializableException extends  AbstractJSONSerializableException implements IJSONFriendlyObject<SimpleJSONSerializableException>{
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5234391795611235360L;
	
	
	
	private Exception actualException ; 
	/**
     * message dari string
     */
    
    public SimpleJSONSerializableException(){
    	super("no Message");
    }
    
    public SimpleJSONSerializableException(String message ) {
        super(message); 
        
    }
    public SimpleJSONSerializableException(Exception baseException ) {
        super(baseException.getMessage());
        this.actualException = baseException ; 
    }
    
    
    

   

    @Override
    public void translateToJSON(ParsedJSONContainer jsonContainerData) {
        jsonContainerData.put("message", getMessage());
        jsonContainerData.put("stackTraceAsString", getStackTraceAsString());
        if ( actualException!= null && actualException instanceof IJSONFriendlyObject){
        	jsonContainerData.put("actualException" , (IJSONFriendlyObject)actualException);
        	jsonContainerData.put("actualException_FQCN" , actualException.getClass().getName());
        }
    }

    @Override
    public SimpleJSONSerializableException instantiateFromJSON(ParsedJSONContainer jsonContainer) {
    	
    	SimpleDebugerWriter w =  SharedServerClientLogicManager.getInstance().getDebugWritter(this.getClass());
    	
        SimpleJSONSerializableException retval = new SimpleJSONSerializableException(jsonContainer.getAsString("message")); 
        retval.setStackTraceAsString(jsonContainer.getAsString("stackTraceAsString"));
        if ( jsonContainer.contain("actualException")){
//        	w.debug( "actualException di dapatkan dari json");
        	String fqcn = jsonContainer.getAsString(  "actualException_FQCN");
        	IJSONFriendlyObject sample =  ObjectGeneratorManager.getInstance().instantiateSampleObject(fqcn);
//        	w.debug( "fqcn actual exception : " + fqcn + ", sample : " + sample);
        	if ( sample!= null){
        		retval.actualException = (Exception)  jsonContainer.getAsSubJSONObject("actualException", sample); 
        	}
        	/*else{
        		w.debug("sample object null.class :" + fqcn +", spt nya tidak di daftarkan dalam generator");
        	}*/
        }
        return  retval ; 
                 
    }
    
    public Exception getActualException() {
		return actualException;
	}

	@Override
	public String toString() {
		return "SimpleJSONSerializableException [actualException="
				+ actualException + "]";
	}
    
}
