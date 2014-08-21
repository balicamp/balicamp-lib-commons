package id.co.sigma.common.server.data.lookup;

import id.co.sigma.common.data.lov.ILookupHeader;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



/**
 * 
 * mapper default ke m_lookup_header
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
@Entity
@Table(name="m_lookup_header")
public class Common1LevelLOVHeader implements Serializable ,ILookupHeader {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6663697790569386261L;

	
	/**
	 * ID dari lookup header
	 **/
	@EmbeddedId
	private Common1LevelLOVHeaderPK id  ; 


	/**
	 * column : LOV_ID<br/>
	 * Id dari lookup
	 **/
	@Column(name="LOV_ID" , insertable=false, updatable=false)
	private  String lovId;

	
	
	/**
	 * column : i18_CODE<br/>
	 * kode i18 dari Lookup
	 **/
	@Column(name="i18_CODE", insertable=false, updatable=false)
	private  String i18Key;
	
	
	
	/**
	 * column : LOV_REMARK
	 * catatan dari Lookup
	 **/
	@Column(name="LOV_REMARK")
	private  String lovRemark;

	/**
	 * Lookup version 
	 * column : LOV_VERSION
	 **/
	@Column(name="LOV_VERSION")
	private  String version;

	/**
	 * column : IS_CACHEABLE
	 **/
	@Column(name="IS_CACHEABLE")
	private  String  cacheableFlag ="Y";	
	
	
	
	
	/**
	 * column : LOV_ID<br/>
	 * Id dari lookup
	 **/
	public String getLovId() {
		return lovId;
	}
	/**
	 * column : LOV_ID<br/>
	 * Id dari lookup
	 **/
	public void setLovId(String lovId) {
		this.lovId = lovId;
	}
	/**
	 * column : LOV_REMARK
	 * catatan dari Lookup
	 **/
	public String getLovRemark() {
		return lovRemark;
	}
	/**
	 * column : LOV_REMARK
	 * catatan dari Lookup
	 **/
	public void setLovRemark(String lovRemark) {
		this.lovRemark = lovRemark;
	}
	/**
	 * Lookup version 
	 * column : LOV_VERSION
	 **/
	public String getVersion() {
		return version;
	}
	/**
	 * Lookup version 
	 * column : LOV_VERSION
	 **/
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isCacheable() {
		return "Y".equals( cacheableFlag);
	}
	
	
	public void setCacheable(boolean isCacheable) {
		this.cacheableFlag = isCacheable?"Y" :"N";
	}
	/**
	 * column : i18_CODE<br/>
	 * kode i18 dari Lookup
	 **/
	public String getI18Key() {
		return i18Key;
	}
	/**
	 * column : i18_CODE<br/>
	 * kode i18 dari Lookup
	 **/
	public void setI18Key(String i18Key) {
		this.i18Key = i18Key;
	}
	
	/**
	 * ID dari lookup header
	 **/
	public void setId(Common1LevelLOVHeaderPK id) {
		this.id = id;
	}
	/**
	 * ID dari lookup header
	 **/
	public Common1LevelLOVHeaderPK getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Common1LevelLOVHeader [id=" + id + ", lovId=" + lovId
				+ ", i18Key=" + i18Key + ", lovRemark=" + lovRemark
				+ ", version=" + version + ", cacheableFlag=" + cacheableFlag
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cacheableFlag == null) ? 0 : cacheableFlag.hashCode());
		result = prime * result + ((i18Key == null) ? 0 : i18Key.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lovId == null) ? 0 : lovId.hashCode());
		result = prime * result
				+ ((lovRemark == null) ? 0 : lovRemark.hashCode());
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
		Common1LevelLOVHeader other = (Common1LevelLOVHeader) obj;
		if (cacheableFlag == null) {
			if (other.cacheableFlag != null)
				return false;
		} else if (!cacheableFlag.equals(other.cacheableFlag))
			return false;
		if (i18Key == null) {
			if (other.i18Key != null)
				return false;
		} else if (!i18Key.equals(other.i18Key))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lovId == null) {
			if (other.lovId != null)
				return false;
		} else if (!lovId.equals(other.lovId))
			return false;
		if (lovRemark == null) {
			if (other.lovRemark != null)
				return false;
		} else if (!lovRemark.equals(other.lovRemark))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	
	
}
