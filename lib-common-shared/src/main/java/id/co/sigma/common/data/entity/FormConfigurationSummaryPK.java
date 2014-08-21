package id.co.sigma.common.data.entity;

import id.co.sigma.common.data.EntityObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.Embeddable;




/**
 * class primary key table 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 **/
@Embeddable
public class FormConfigurationSummaryPK extends EntityObject implements IJSONFriendlyObject<FormConfigurationSummaryPK>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2138613341323010808L;



	/**
	 * column : form_code<br/>
	 * nama form
	 **/
	@Column(name="form_code")
	private String formCode ;
	
	
	
	/**
	 * column : locale_code
	 * localization code. language apa yang di pakai
	 **/
	@Column(name="locale_code")
	private String localeCode; 
	
	/**
	 * column : locale_code
	 * localization code. language apa yang di pakai
	 **/
	public String getLocaleCode() {
		return localeCode;
	}
	/**
	 * column : locale_code
	 * localization code. language apa yang di pakai
	 **/
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
	
	/**
	 * column : form_code<br/>
	 * nama form
	 **/
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	/**
	 * column : form_code<br/>
	 * nama form
	 **/
	public String getFormCode() {
		return formCode;
	}
	@Override
	public String toString() {
		return "FormConfigurationSummaryPK [formCode=" + formCode
				+ ", localeCode=" + localeCode + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 7;
		int result = 1;
		result = prime * result
				+ ((formCode == null) ? 0 : formCode.hashCode());
		result = prime * result
				+ ((localeCode == null) ? 0 : localeCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormConfigurationSummaryPK other = (FormConfigurationSummaryPK) obj;
		if (formCode == null) {
			if (other.formCode != null)
				return false;
		} else if (!formCode.equals(other.formCode))
			return false;
		if (localeCode == null) {
			if (other.localeCode != null)
				return false;
		} else if (!localeCode.equals(other.localeCode))
			return false;
		return true;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("formCode",getFormCode());
		jsonContainer.put("localeCode",getLocaleCode());
	}
	
	@Override
	public FormConfigurationSummaryPK instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		FormConfigurationSummaryPK retval = new FormConfigurationSummaryPK();
		retval.setFormCode( (String)jsonContainer.get("formCode" ,  String.class.getName()));
		retval.setLocaleCode( (String)jsonContainer.get("localeCode" ,  String.class.getName()));
		return retval; 
	}
}
