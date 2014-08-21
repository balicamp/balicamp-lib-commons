/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.data.serializer.json;


import id.co.sigma.common.data.reflection.ClientReflectableClass;
import id.co.sigma.common.exception.SimpleJSONSerializableException;
import id.co.sigma.common.util.ObjectGeneratorManager;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 *
 * @author gps
 */
@ClientReflectableClass
public class RPCResponseWrapper implements  IJSONFriendlyObject<RPCResponseWrapper>{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -4976450014896706542L;

	


	/**
     * flag ada error atau tidak dalam operasi. kalau ada error maka variable akan di isi berisi dengan 
     */
    private boolean haveError ;
    
    
    
    /**
     * fqcn dari error
     */
    private String errorFQCN ; 
    
    private SimpleJSONSerializableException exception ; 
    
    
    
    /**
     * data object FQCN. fqcn di pergunakan untuk isntantiate sample object
     */
    private String dataFQCN ; 
    private IJSONFriendlyObject data ; 
    
    
    public RPCResponseWrapper(){}
    
    public RPCResponseWrapper(IJSONFriendlyObject data ){
        setData(data);
    }

    public RPCResponseWrapper(SimpleJSONSerializableException error ){
        setException(error);
    }
    
    /**
     * data actual
     */
    public final void setData(IJSONFriendlyObject data) {
        this.data = data;
        if ( data!= null)
            this.dataFQCN =     data.getClass().getName(); 
    }

    public final void setException(SimpleJSONSerializableException exception) {
        this.exception = exception;
        if ( exception != null){
            this.haveError = true ; 
            this.errorFQCN = exception.getClass().getName() ; 
        }
        
        
    }
    
    

    public boolean isHaveError() {
        return haveError;
    }

   
    
    
    
    
    public IJSONFriendlyObject getData() {
        return data;
    }
    
    
    

    @Override
    public void translateToJSON(ParsedJSONContainer jsonContainerData) {
        jsonContainerData.put("haveError", haveError);
        jsonContainerData.put("exception", exception);
        jsonContainerData.put("data", data);
        jsonContainerData.put("dataFQCN", dataFQCN);
        jsonContainerData.put("errorFQCN", errorFQCN);
        
                
    }
    

    @Override
    public RPCResponseWrapper instantiateFromJSON(ParsedJSONContainer jsonContainer) {
        RPCResponseWrapper retval = new RPCResponseWrapper(); 
        retval.haveError = jsonContainer.getAsBoolean("haveError");
        retval.dataFQCN =   jsonContainer.getAsString("dataFQCN" );
        retval.errorFQCN =  jsonContainer.getAsString("errorFQCN");
        if ( retval.dataFQCN== null || retval.dataFQCN.isEmpty()){
        	System.out.println(  "FQCN object tidak di temukan, kemungkinan object null . atau anda tidak menyertakan  (pls cek key : " +id.co.sigma.common.util.json.IJSONFriendlyObject.FQCN_MARKER_KEY +")\nJSON String :" + jsonContainer.getJSONString() );
        }
        retval.data = jsonContainer.getAsSubJSONObject("data", (IJSONFriendlyObject)ObjectGeneratorManager.getInstance().instantiateSampleObject(retval.dataFQCN)); 
        retval.exception = jsonContainer.getAsSubJSONObject("exception"  ,(SimpleJSONSerializableException)ObjectGeneratorManager.getInstance().instantiateSampleObject(retval.errorFQCN)); 
        return retval ; 
        
    }
    
    public SimpleJSONSerializableException getException() {
		return exception;
	}

    
    
}
