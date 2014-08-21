package id.co.sigma.common.security.domain.lov;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * primary key table lookup details
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
@Embeddable
public class LookupDetailPK implements Serializable ,IsSerializable, IJSONFriendlyObject<LookupDetailPK>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8108979261805242175L;
	/**
	 * column : LOV_ID
	 **/
	@Column(name="LOV_ID")
	private String lovID ;
	/**
	 * column : i18_CODE
	 **/
	@Column(name="i18_CODE")
	private String i18Code ;
	/**
	 * column :DETAIL_CODE 
	 **/
	@Column(name="DETAIL_CODE")
	private String lovDetailId ;
	/**
	 * column : LOV_ID
	 **/
	public String getLovID() {
		return lovID;
	}
	/**
	 * column : LOV_ID
	 **/
	public void setLovID(String lovID) {
		this.lovID = lovID;
	}
	/**
	 * column : i18_CODE
	 **/
	public String getI18Code() {
		return i18Code;
	}
	/**
	 * column : i18_CODE
	 **/
	public void setI18Code(String i18Code) {
		this.i18Code = i18Code;
	}
	/**
	 * column :DETAIL_CODE 
	 **/
	public String getLovDetailId() {
		return lovDetailId;
	}
	/**
	 * column :DETAIL_CODE 
	 **/
	public void setLovDetailId(String lovDetailId) {
		this.lovDetailId = lovDetailId;
	}
	@Override
	public String toString() {
		return "LookupDetailPK [lovID=" + lovID + ", i18Code=" + i18Code
				+ ", lovDetailId=" + lovDetailId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 73;
		int result = 1;
		result = prime * result + ((i18Code == null) ? 0 : i18Code.hashCode());
		result = prime * result
				+ ((lovDetailId == null) ? 0 : lovDetailId.hashCode());
		result = prime * result + ((lovID == null) ? 0 : lovID.hashCode());
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
		LookupDetailPK other = (LookupDetailPK) obj;
		if (i18Code == null) {
			if (other.i18Code != null)
				return false;
		} else if (!i18Code.equals(other.i18Code))
			return false;
		if (lovDetailId == null) {
			if (other.lovDetailId != null)
				return false;
		} else if (!lovDetailId.equals(other.lovDetailId))
			return false;
		if (lovID == null) {
			if (other.lovID != null)
				return false;
		} else if (!lovID.equals(other.lovID))
			return false;
		return true;
	} 

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("i18Code",getI18Code());
		jsonContainer.put("lovDetailId",getLovDetailId());
		jsonContainer.put("lovID",getLovID());
	}
	
	@Override
	public LookupDetailPK instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		LookupDetailPK retval = new LookupDetailPK();
		retval.setI18Code( (String)jsonContainer.get("i18Code" ,  String.class.getName()));
		retval.setLovDetailId( (String)jsonContainer.get("lovDetailId" ,  String.class.getName()));
		retval.setLovID( (String)jsonContainer.get("lovID" ,  String.class.getName()));
		return retval; 
	}
	
}
