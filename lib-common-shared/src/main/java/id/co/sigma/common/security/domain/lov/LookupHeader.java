package id.co.sigma.common.security.domain.lov;

import static javax.persistence.LockModeType.READ;
import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.data.lov.ILookupHeader;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;




/**
 * table : m_lookup_header
 * 
 **/
@Entity
@Table(name="m_lookup_header" )
@NamedQuery(name = "sample", lockMode = READ, query = "Select A FROm  LookupHeader A")
public class LookupHeader implements Serializable , ILookupHeader, IJSONFriendlyObject<LookupHeader>, SingleKeyEntityData<String>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5678244192624522346L;


	@Id
	@Column(name="LOV_ID" , updatable=false)
	private String id ;
	
	
	/**
	 * kode internalization
	 * column : i18_CODE. 
	 **/
	@Column(name="i18_CODE", updatable=false )
	private String i18Key ; 
	
	
	public String getI18Key() {
		return i18Key;
	}

	public void setI18Key(String i18Key) {
		this.i18Key = i18Key;
	}

	@Column(name="LOV_REMARK")
	private String remark ; 
	
	@Column(name="LOV_VERSION")
	private String version ;
	
	
	
	 
	
	
	/**
	 * field : IS_CACHEABLE
	 * flag LOV cacheable atau tidak
	 **/
	@Column(name="IS_CACHEABLE")
	private String cacheableFlag ; 
	
	
	
	/**
	 * lreference ke lookup details
	 **/
	@OneToMany(fetch=FetchType.LAZY  , 
				targetEntity=LookupDetail.class , 
				cascade={CascadeType.ALL} )
	@OrderBy(value="sequence asc")
	@JoinColumns(
			value={
					@JoinColumn(name="LOV_ID" , updatable=false , referencedColumnName="LOV_ID"),
					@JoinColumn(name="i18_CODE" , updatable=false , referencedColumnName="i18_CODE")
			})
	private List<LookupDetail> details ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	} 
	
	
	/**
	 * field : IS_CACHEABLE
	 * flag LOV cacheable atau tidak
	 **/
	public void setCacheableFlag(String cacheableFlag) {
		this.cacheableFlag = cacheableFlag;
	}
	/**
	 * field : IS_CACHEABLE
	 * flag LOV cacheable atau tidak
	 **/
	public String getCacheableFlag() {
		return cacheableFlag;
	}
	
	
	public void setCacheable (boolean cacheable) {
		this.cacheableFlag = cacheable? "Y":"N";
	}
	
	public boolean isCacheable () {
		return "Y".equalsIgnoreCase(cacheableFlag);
	}

	public List<LookupDetail> getDetails() {
		return details;
	}

	public void setDetails(List<LookupDetail> details) {
		this.details = details;
	}

	@Override
	public String getLovId() {
		return getId();
	}

	@Override
	public String getLovRemark() {
		return getRemark();
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cacheableFlag == null) ? 0 : cacheableFlag.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		LookupHeader other = (LookupHeader) obj;
		if (cacheableFlag == null) {
			if (other.cacheableFlag != null)
				return false;
		} else if (!cacheableFlag.equals(other.cacheableFlag))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("cacheable",isCacheable());
		jsonContainer.put("cacheableFlag",getCacheableFlag());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		 
		  List<id.co.sigma.common.security.domain.lov.LookupDetail> param4 = getDetails() ; 
		 if (  param4 != null && !param4.isEmpty()){ 
			for ( id.co.sigma.common.security.domain.lov.LookupDetail scn : param4){
				//1. Ok tampung dulu variable
//2. null kan variable 
// 3 taruh ke json

					jsonContainer.appendToArray("details", scn);
				//4. restore lagi 

				}
		}
		jsonContainer.put("id",getId());
		jsonContainer.put("remark",getRemark());
		jsonContainer.put("version",getVersion());
	}
	
	@Override
	public LookupHeader instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		LookupHeader retval = new LookupHeader();
		Boolean tmp = (Boolean)jsonContainer.get("cacheable" ,  Boolean.class.getName() ); 
		retval.setCacheable( tmp!=null ? tmp.booleanValue() : false);
		
		retval.setCacheableFlag( (String)jsonContainer.get("cacheableFlag" ,  String.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setId( (String)jsonContainer.get("id" ,  String.class.getName()));
		retval.setRemark( (String)jsonContainer.get("remark" ,  String.class.getName()));
		retval.setVersion( (String)jsonContainer.get("version" ,  String.class.getName()));
		return retval; 
	}
}
