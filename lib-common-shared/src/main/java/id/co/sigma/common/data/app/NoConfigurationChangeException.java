package id.co.sigma.common.data.app;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * exception app configuration. ini untuk menandai kalau tidak ada perubahan konfigurasi. untuk efisiensi, di kirim sebagai exception
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 **/
public class NoConfigurationChangeException extends Exception implements IsSerializable, IJSONFriendlyObject<NoConfigurationChangeException>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6137634004942446852L;
	private String message ; 
	
	
	public NoConfigurationChangeException(){}
	
	
	public NoConfigurationChangeException(String message ){
		super( message);
		setMessage(message); 
		
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message ; 
	};

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("message", getMessage());
	}
	
	@Override
	public NoConfigurationChangeException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		NoConfigurationChangeException retval = new NoConfigurationChangeException();
		retval.setMessage(jsonContainer.getAsString("message"));
		return retval;
	}
}
