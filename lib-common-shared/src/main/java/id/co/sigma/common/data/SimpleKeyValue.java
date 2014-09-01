package id.co.sigma.common.data;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * 
 * 
 * variable sederhana , key + value
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class SimpleKeyValue implements IJSONFriendlyObject<SimpleKeyValue> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3993735831272322659L;


	/**
	 * key dari data
	 */
	private String key ; 
	
	
	/**
	 * nilai data( dalam bentuk string)
	 */
	private String value ; 
	
	/**
	 * key dari data
	 */
	public String getKey() {
		return key;
	}
	/**
	 * key dari data
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * nilai data( dalam bentuk string)
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * nilai data( dalam bentuk string)
	 */
	public String getValue() {
		return value;
	}
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("key", key); 
		jsonContainer.put("value", value); 
		
	}
	@Override
	public SimpleKeyValue instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		SimpleKeyValue retval = new SimpleKeyValue();
		retval.key = jsonContainer.getAsString("key"); 
		retval.value = jsonContainer.getAsString("value"); 
		return retval;
	}

}
