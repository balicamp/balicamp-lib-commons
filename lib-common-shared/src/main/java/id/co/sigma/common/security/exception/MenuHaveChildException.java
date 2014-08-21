/**
 * 
 */
package id.co.sigma.common.security.exception;

import id.co.sigma.common.exception.BaseIsSerializableException;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * exception untuk operasi delete function (delete menu)
 * di throw jika menu memiliki sub menu (child)
 * @author <a href="mailto:gede.wibawa@sigma.co.id">Agus Gede Adipartha Wibawa</a>
 * @since Aug 15, 2013 12:42:30 PM
 */
public class MenuHaveChildException extends BaseIsSerializableException implements IJSONFriendlyObject<MenuHaveChildException> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3585433342341301961L;

	/**
	 * message error
	 */
	private String defaultFriendlyMessage;
	
	public MenuHaveChildException() {}
	
	public MenuHaveChildException(String defaultFriendlyMessage) {
		this.defaultFriendlyMessage = defaultFriendlyMessage;
	}
	
	public void setDefaultFriendlyMessage(String defaultFriendlyMessage) {
		this.defaultFriendlyMessage = defaultFriendlyMessage;
	}
	
	public String getDefaultFriendlyMessage() {
		return defaultFriendlyMessage;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("message", getMessage());
		jsonContainerData.put("fullStackTrace", getFullStackTrace());
		jsonContainerData.put("defaultFriendlyMessage", getDefaultFriendlyMessage());
	}

	@Override
	public MenuHaveChildException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		MenuHaveChildException retval = new MenuHaveChildException();
		retval.setMessage(jsonContainer.getAsString("message"));
		retval.setFullStackTrace(jsonContainer.getAsString("fullStackTrace"));
		retval.setFullStackTrace(jsonContainer.getAsString("defaultFriendlyMessage"));
		return retval;
	}
	
	public String getFullStackTrace() {
		return this.fullStackTrace;
	}
	
	public void setFullStackTrace(String fullStackTrace) {
		this.fullStackTrace = fullStackTrace;
	}
}
