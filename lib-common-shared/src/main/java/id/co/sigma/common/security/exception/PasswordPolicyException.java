package id.co.sigma.common.security.exception;


import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dode
 * @version $Id
 * @since Dec 26, 2012, 3:10:25 PM
 */
public class PasswordPolicyException extends Exception/*BaseIsSerializableException*/ implements IJSONFriendlyObject<PasswordPolicyException> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8716655525241692467L;
	
	/**
	 * message error
	 */
	private String defaultFriendlyMessage;
	
	/**
	 * internationalization friendly message key
	 */
	private String i18nKeyFriendlyMessage;
	
	/**
	 * list of detail message
	 */
	private List<String> detailMessages;
	
	/**
	 * i18n key of detail message
	 */
	private List<String> i18nKeyDetailMessages;
	
	private List<String> passValues;
	
	public PasswordPolicyException() {}
	
	public PasswordPolicyException(String defaultFriendlyMessage) {
		this.defaultFriendlyMessage = defaultFriendlyMessage;
	}
	
	public PasswordPolicyException(String i18nKeyFriendlyMessage, String friendlyMessage) {
		this.defaultFriendlyMessage = friendlyMessage;
		this.i18nKeyFriendlyMessage = i18nKeyFriendlyMessage;
	}
	
	public PasswordPolicyException(String i18nKeyFriendlyMessage, String friendlyMessage, List<String> i18nKeyDetailMessages, List<String> detailMessages) {
		this.defaultFriendlyMessage = friendlyMessage;
		this.i18nKeyFriendlyMessage = i18nKeyFriendlyMessage;
		this.i18nKeyDetailMessages = i18nKeyDetailMessages;
		this.detailMessages = detailMessages;
	}
	
	public PasswordPolicyException(String i18nKeyFriendlyMessage, String friendlyMessage, List<String> i18nKeyDetailMessages, List<String> detailMessages, List<String> passValues) {
		this.defaultFriendlyMessage = friendlyMessage;
		this.i18nKeyFriendlyMessage = i18nKeyFriendlyMessage;
		this.i18nKeyDetailMessages = i18nKeyDetailMessages;
		this.detailMessages = detailMessages;
		this.passValues = passValues;
	}
	
	public String getDefaultFriendlyMessage() {
		return defaultFriendlyMessage;
	}
	
	public void setDefaultFriendlyMessage(String friendlyMessage) {
		this.defaultFriendlyMessage = friendlyMessage;
	}
	
	public void setI18nKeyFriendlyMessage(String i18nKeyFriendlyMessage) {
		this.i18nKeyFriendlyMessage = i18nKeyFriendlyMessage;
	}
	
	public String getI18nKeyFriendlyMessage() {
		return i18nKeyFriendlyMessage;
	}
	
	public void setDetailMessages(List<String> detailMessages) {
		this.detailMessages = detailMessages;
	}
	
	public List<String> getDetailMessages() {
		return detailMessages;
	}
	
	public void setPassValues(List<String> passValues) {
		this.passValues = passValues;
	}
	
	public List<String> getPassValues() {
		return passValues;
	}
	
	public String constructPrintedDetailMessage() {
		String printedDetailMessage = "Detail Message : \n";
		int number = 0;
		for (int index = 0; index < detailMessages.size(); index++) {
			if (detailMessages.get(index) != null){
				printedDetailMessage += i18nKeyDetailMessages.get(index) ; 
				//printedDetailMessage += (++number) + ". "+ I18Utilities.getInstance().getInternalitionalizeText(i18nKeyDetailMessages.get(index), detailMessages.get(index) + passValues.get(index)) + "\n";
			}
		}
		return printedDetailMessage;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
//		jsonContainerData.put("message", getMessage());
//		jsonContainerData.put("fullStackTrace", getFullStackTrace());
		jsonContainerData.put("defaultFriendlyMessage", getDefaultFriendlyMessage());
		jsonContainerData.put("i18nKeyFriendlyMessage", getI18nKeyFriendlyMessage());
		List<String> param0 = getDetailMessages();
		if (param0 != null && !param0.isEmpty()) {
			String[] arrDetailMsg = new String[param0.size()];
			param0.toArray(arrDetailMsg);
			jsonContainerData.appendToArray("detailMessages", arrDetailMsg);
		}
		List<String> param1 = getI18nKeyDetailMessages();
		if (param1 != null && !param1.isEmpty()) {
			String[] arrDetailMsg = new String[param1.size()];
			param1.toArray(arrDetailMsg);
			jsonContainerData.appendToArray("i18nKeyDetailMessages", arrDetailMsg);
		}
		List<String> param2 = getPassValues();
		if (param2 != null && !param2.isEmpty()) {
			String[] arrDetailMsg = new String[param2.size()];
			param2.toArray(arrDetailMsg);
			jsonContainerData.appendToArray("passValues", arrDetailMsg);
		}
	}

	@Override
	public PasswordPolicyException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		PasswordPolicyException retval = new PasswordPolicyException();
		retval.setDefaultFriendlyMessage(jsonContainer.getAsString("defaultFriendlyMessage"));
//		retval.setFullStackTrace(jsonContainer.getAsString("fullStackTrace"));
		retval.setI18nKeyFriendlyMessage(jsonContainer.getAsString("i18nKeyFriendlyMessage"));
		String[] arrDetailMsg = jsonContainer.getAsStringArray("detailMessages");
		if (arrDetailMsg != null && arrDetailMsg.length > 0) {
			List<String> detailMsgList = Arrays.asList(arrDetailMsg);
			retval.setDetailMessages(detailMsgList);
		}
		String[] arrI18nKeyDetailMessages = jsonContainer.getAsStringArray("i18nKeyDetailMessages");
		if (arrI18nKeyDetailMessages != null && arrI18nKeyDetailMessages.length > 0) {
			List<String> i18nKeyDetailMessageList = Arrays.asList(arrI18nKeyDetailMessages);
			retval.setDetailMessages(i18nKeyDetailMessageList);
		}
		String[] arrPassValues = jsonContainer.getAsStringArray("passValues");
		if (arrPassValues != null && arrPassValues.length > 0) {
			List<String> passValueList = Arrays.asList(arrPassValues);
			retval.setDetailMessages(passValueList);
		}
		return retval;
	}
	
	/*public String getFullStackTrace() {
		return this.fullStackTrace;
	}
	
	public void setFullStackTrace(String fullStackTrace) {
		this.fullStackTrace = fullStackTrace;
	}*/
	
	public void setI18nKeyDetailMessages(List<String> i18nKeyDetailMessages) {
		this.i18nKeyDetailMessages = i18nKeyDetailMessages;
	}
	
	public List<String> getI18nKeyDetailMessages() {
		return i18nKeyDetailMessages;
	}
}
