package id.co.sigma.common.data;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TransportSimpleGridDataWrapper implements IsSerializable, IJSONFriendlyObject<TransportSimpleGridDataWrapper>{

	private String id ; 
	private String[] values ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(String[] values) {
		this.values = values;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("id",getId());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/
		jsonContainer.appendToArray("values",getValues());
	}
	
	@Override
	public TransportSimpleGridDataWrapper instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		TransportSimpleGridDataWrapper retval = new TransportSimpleGridDataWrapper();
		retval.setId( (String)jsonContainer.get("id" ,  String.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setValues( (String[])jsonContainer.get("values" ,  String[].class.getName()));
		return retval; 
	}
	
}
