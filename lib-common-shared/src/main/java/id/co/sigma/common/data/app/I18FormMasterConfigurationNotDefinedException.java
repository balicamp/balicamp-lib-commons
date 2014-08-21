package id.co.sigma.common.data.app;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * ini di raise kalau konfigurasi i18 (form vs label nya) belum di definisikan. ini akan mentriger agar form mengirimkan kembali daftar i18 enabled labels
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 **/
public class I18FormMasterConfigurationNotDefinedException extends Exception implements IsSerializable, IJSONFriendlyObject<I18FormMasterConfigurationNotDefinedException>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2184396866124729910L;
	
	
	private String formId ; 
	
	
	private String message ;
	
	public I18FormMasterConfigurationNotDefinedException(){}
	public I18FormMasterConfigurationNotDefinedException(String formId, String message){
		this.formId = formId ; 
		this.message = message ; 
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	} 
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("formId", getFormId());
		jsonContainerData.put("message", getMessage());
	}
	
	@Override
	public I18FormMasterConfigurationNotDefinedException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		I18FormMasterConfigurationNotDefinedException retval = new I18FormMasterConfigurationNotDefinedException();
		retval.setFormId(jsonContainer.getAsString("formId"));
		retval.setMessage(jsonContainer.getAsString("message"));
		return retval;
	}
	

}
