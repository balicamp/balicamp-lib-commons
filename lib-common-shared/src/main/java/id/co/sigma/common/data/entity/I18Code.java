package id.co.sigma.common.data.entity;

import id.co.sigma.common.data.reflection.ClientReflectableClass;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * table : m_app_i18_codes
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
@ClientReflectableClass
@Entity
@Table(name="m_app_i18_codes")
public class I18Code implements IsSerializable , Serializable, IJSONFriendlyObject<I18Code>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5999828611756780434L;


	/**
	 * id dari i18
	 **/
	@Id
	@Column(name="locale_code")
	private String code ; 
	
	
	/**
	 * keterangan remark
	 **/
	@Column(name="locale_remark")
	private String description ;
	
	
	
	/**
	 * pemisah decimal, ( <i>,</i> atau <i>.</i>   tergantung pada localziation) 
	 * column : decimal_separator
	 **/
	@Column(name="decimal_separator")
	private String decimalSeparator ; 

	/**
	 * id dari i18
	 **/
	public String getCode() {
		return code;
	}

	/**
	 * id dari i18
	 **/
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * keterangan remark
	 **/
	public String getDescription() {
		return description;
	}
	/**
	 * keterangan remark
	 **/
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
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
		I18Code other = (I18Code) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		return "I18Code [code=" + code + ", description=" + description
				+ ", decimalSeparator=" + decimalSeparator + "]";
	}

	public String getDecimalSeparator() {
		return decimalSeparator;
	}
	
	
	public void setDecimalSeparator(String decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("code",getCode());
		jsonContainer.put("decimalSeparator",getDecimalSeparator());
		jsonContainer.put("description",getDescription());
	}
	
	@Override
	public I18Code instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		I18Code retval = new I18Code();
		retval.setCode( (String)jsonContainer.get("code" ,  String.class.getName()));
		retval.setDecimalSeparator( (String)jsonContainer.get("decimalSeparator" ,  String.class.getName()));
		retval.setDescription( (String)jsonContainer.get("description" ,  String.class.getName()));
		return retval; 
	}
}
