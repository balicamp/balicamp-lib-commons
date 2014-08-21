/**
 * File Name : PageDefinition.java
 * Package   : id.co.sigma.arium.security.shared.domain
 * Project   : security-data
 */
package id.co.sigma.common.security.domain;

import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.security.domain.audit.BaseCreatedObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Entiti untuk tabel : sec_page_definition
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 12:34:50 PM
 * @version $Id
 */
@Entity
@Table(name="sec_page_definition")
public class PageDefinition extends BaseCreatedObject implements SingleKeyEntityData<Long>, IJSONFriendlyObject<PageDefinition>{

	private static final long serialVersionUID = -7013942142386812409L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pk")
	private Long id;
	
	@Column(name="page_code")
	private String pageCode;
	
	@Column(name="app_id")
	private Long applicationId;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="app_id", insertable=false, updatable=false)
	private Application application;
	
	@Column(name="page_url")
	private String pageUrl;
	
	@Column(name="additional_data")
	private String additionalData;

	
	
	/**
	 * catatan page
	 */
	@Column(name="page_remark" , length=256)
	private String remark ; 
	
	
	/**
	 * page id<br>
	 * COLUMN : PAGE_ID
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * page id<br>
	 * COLUMN : PAGE_ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * kode halaman, ini di maintain dengan konvensi. kode app + kode<br>
	 * COLUMN : PAGE_CODE
	 * @return pageCode
	 */
	public String getPageCode() {
		return pageCode;
	}

	/**
	 * kode halaman, ini di maintain dengan konvensi. kode app + kode<br>
	 * COLUMN : PAGE_CODE
	 * @param pageCode
	 */
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	/**
	 * reference ke applikasi<br>
	 * COLUMN : APPLICATION_ID
	 * @return applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}

	/**
	 * reference ke applikasi<br>
	 * COLUMN : APPLICATION_ID
	 * @param applicationId
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * reference ke object Application {@link id.co.sigma.common.security.domain.Application}
	 * @return application
	 */
	public Application getApplicationList() {
		return application;
	}

	/**
	 * reference ke object Application {@link id.co.sigma.common.security.domain.Application}
	 * @param application
	 */
	public void setApplicationList(Application application) {
		this.application = application;
	}

	/**
	 * Url/ command /panel ID dari URL<br>
	 * COLUMN : PAGE_URL
	 * @return pageUrl
	 */
	public String getPageUrl() {
		return pageUrl;
	}

	/**
	 * Url/ command /panel ID dari URL<br>
	 * COLUMN : PAGE_URL
	 * @param pageUrl
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	/**
	 * additional data(yg di mengerti oleh applikasi, misal meta data dalam JSON etc)<br>
	 * COLUMN : ADDITIONAL_DATA
	 * @return additionalData
	 */
	public String getAdditionalData() {
		return additionalData;
	}

	/**
	 * additional data(yg di mengerti oleh applikasi, misal meta data dalam JSON etc)<br>
	 * COLUMN : ADDITIONAL_DATA
	 * @param additionalData
	 */
	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((additionalData == null) ? 0 : additionalData.hashCode());
		
		result = prime * result
				+ ((applicationId == null) ? 0 : applicationId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((pageCode == null) ? 0 : pageCode.hashCode());
		result = prime * result + ((pageUrl == null) ? 0 : pageUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageDefinition other = (PageDefinition) obj;
		if (additionalData == null) {
			if (other.additionalData != null)
				return false;
		} else if (!additionalData.equals(other.additionalData))
			return false;
		else if (!applicationId.equals(other.applicationId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pageCode == null) {
			if (other.pageCode != null)
				return false;
		} else if (!pageCode.equals(other.pageCode))
			return false;
		if (pageUrl == null) {
			if (other.pageUrl != null)
				return false;
		} else if (!pageUrl.equals(other.pageUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PageDefinition [id=" + id + ", pageCode=" + pageCode
				+ ", applicationId=" + applicationId + ", application="
				+ application + ", pageUrl=" + pageUrl + ", additionalData="
				+ additionalData + "]";
	}
	
	/**
	 * column : page_remark
	 * catatan dari halaman, ini halaman apa
	 **/
	/*public void setRemark(String remark) {
		this.remark = remark;
	}
	*//**
	 * column : page_remark
	 * catatan dari halaman, ini halaman apa
	 **//*
	public String getRemark() {
		return remark;
	}*/
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("additionalData",getAdditionalData());
		jsonContainer.put("applicationId",getApplicationId());
		  
		 Application param3 = getApplicationList();   
		 if ( param3 != null){ 
		
 //1. Ok tampung dulu variable
//2. null kan variable 
// 3 taruh ke json
			jsonContainer.put("applicationList", param3);
//4. restore lagi 
		}
		jsonContainer.put("applicationList",getApplicationList());
		jsonContainer.put("createdBy",getCreatedBy());
		jsonContainer.put("createdOn",getCreatedOn());
		jsonContainer.put("creatorIPAddress",getCreatorIPAddress());
		jsonContainer.put("id",getId());
		jsonContainer.put("pageCode",getPageCode());
		jsonContainer.put("pageUrl",getPageUrl());
		jsonContainer.put("remark",getRemark());
	}
	
	@Override
	public PageDefinition instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		PageDefinition retval = new PageDefinition();
		retval.setAdditionalData( (String)jsonContainer.get("additionalData" ,  String.class.getName()));
		retval.setApplicationId( (Long)jsonContainer.get("applicationId" ,  Long.class.getName()));
		  
		retval.setApplicationList( (Application)jsonContainer.get("applicationList" ,  Application.class.getName()));
		retval.setCreatedBy( (String)jsonContainer.get("createdBy" ,  String.class.getName()));
		retval.setCreatedOn( (Date)jsonContainer.get("createdOn" ,  Date.class.getName()));
		retval.setCreatorIPAddress( (String)jsonContainer.get("creatorIPAddress" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setPageCode( (String)jsonContainer.get("pageCode" ,  String.class.getName()));
		retval.setPageUrl( (String)jsonContainer.get("pageUrl" ,  String.class.getName()));
		retval.setRemark( (String)jsonContainer.get("remark" ,  String.class.getName()));
		return retval; 
	}
	
	
	/**
	 * catatan page
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * catatan page
	 */
	public String getRemark() {
		return remark;
	}
}