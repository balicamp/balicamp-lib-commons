package id.co.sigma.common.data.entity;

import id.co.sigma.common.data.EntityObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.Embeddable;




/**
 * PK i18 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 * 
 **/
@Embeddable
public class I18TextPK extends EntityObject implements IJSONFriendlyObject<I18TextPK>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3383515050889043303L;


	@Column(name="locale_code")
	private String localeCode ;
	
	
	@Column(name="text_key")
	private String textKey ;


	public String getLocaleCode() {
		return localeCode;
	}


	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}


	public String getTextKey() {
		return textKey;
	}


	public void setTextKey(String textKey) {
		this.textKey = textKey;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((localeCode == null) ? 0 : localeCode.hashCode());
		result = prime * result + ((textKey == null) ? 0 : textKey.hashCode());
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
		I18TextPK other = (I18TextPK) obj;
		if (localeCode == null) {
			if (other.localeCode != null)
				return false;
		} else if (!localeCode.equals(other.localeCode))
			return false;
		if (textKey == null) {
			if (other.textKey != null)
				return false;
		} else if (!textKey.equals(other.textKey))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "I18TextPK [localeCode=" + localeCode + ", textKey=" + textKey
				+ "]";
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("localeCode",getLocaleCode());
		jsonContainer.put("textKey",getTextKey());
	}
	
	@Override
	public I18TextPK instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		I18TextPK retval = new I18TextPK();
		retval.setLocaleCode( (String)jsonContainer.get("localeCode" ,  String.class.getName()));
		retval.setTextKey( (String)jsonContainer.get("textKey" ,  String.class.getName()));
		return retval; 
	}
}
