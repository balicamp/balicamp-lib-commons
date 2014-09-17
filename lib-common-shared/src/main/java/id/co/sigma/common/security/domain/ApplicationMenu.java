/**
 * File Name : Function.java
 * Package   : id.co.sigma.arium.security.shared.domain
 * Project   : security-data
 */
package id.co.sigma.common.security.domain;

import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.security.domain.audit.BaseAuditedObject;
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
 * Entiti untuk tabel : sec_function
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 1:13:29 PM
 * @version $Id
 */
@Entity
@Table(name="sec_menu")
public class ApplicationMenu extends BaseAuditedObject implements IJSONFriendlyObject<ApplicationMenu> , SingleKeyEntityData<Long> {
	
	private static final long serialVersionUID = -264680357828308703L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pk")
	private Long id;
	
	@Column(name="parent_id")
	private Long functionIdParent;
	
	@Column(name="order_no")
	private Integer siblingOrder;
	
	@Column(name="app_id")
	private Long applicationId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="app_id", insertable=false, updatable=false)
	private Application application;
	
	@Column(name="menu_code")
	private String functionCode;
	
	@Column(name="menu_label")
	private String functionLabel;
	
	@Column(name="page_id")
	private Long pageId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="page_id", insertable=false, updatable=false)
	private PageDefinition pageDefinition;
	
	@Column(name="tree_level_position")
	private Integer treeLevelPosition;
	
	@Column(name="menu_tree_code")
	private String menuTreeCode;
	
	@Column(name="data_status",length=1)
	private String status;

	/**
	 * Function ID<br>
	 * COLUMN : FUNCTION_ID
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Function ID<br>
	 * COLUMN : FUNCTION_ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Function Id Parent<br>
	 * COLUMN : FUNCTION_ID_PARENT
	 * @return functionIdParent
	 */
	public Long getFunctionIdParent() {
		return functionIdParent;
	}

	/**
	 * Function Id Parent<br>
	 * COLUMN : FUNCTION_ID_PARENT
	 * @param functionIdParent
	 */
	public void setFunctionIdParent(Long functionIdParent) {
		this.functionIdParent = functionIdParent;
	}

	/**
	 * nomor urut dalam 1 level<br>
	 * COLUMN : SIBLING_ORDER
	 * @return siblingOrder
	 */
	public Integer getSiblingOrder() {
		return siblingOrder;
	}

	/**
	 * nomor urut dalam 1 level<br>
	 * COLUMN : SIBLING_ORDER
	 * @param siblingOrder
	 */
	public void setSiblingOrder(Integer siblingOrder) {
		this.siblingOrder = siblingOrder;
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
	public Application getApplication() {
		return application;
	}

	/**
	 * reference ke object Application {@link id.co.sigma.common.security.domain.Application}
	 * @param application
	 */
	public void setApplication(Application application) {
		this.application = application;
	}

	/**
	 * function code<br>
	 * COLUMN : FUNCTION_CODE
	 * @return functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * function code<br>
	 * COLUMN : FUNCTION_CODE
	 * @param functionCode
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * label yg di munculkan pada menu<br>
	 * COLUMN : FUNCTION_LABEL
	 * @return functionLabel
	 */
	public String getFunctionLabel() {
		return functionLabel;
	}

	/**
	 * label yg di munculkan pada menu<br>
	 * COLUMN : FUNCTION_LABEL
	 * @param functionLabel
	 */
	public void setFunctionLabel(String functionLabel) {
		this.functionLabel = functionLabel;
	}

	/**
	 * Ini kalau node menu merupakan leave - point pada page /panel<br>
	 * COLUMN : PAGE_ID
	 * @return pageId
	 */
	public Long getPageId() {
		return pageId;
	}

	/**
	 * Ini kalau node menu merupakan leave - point pada page /panel<br>
	 * COLUMN : PAGE_ID
	 * @param pageId
	 */
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	/**
	 * reference ke object PageDefinition {@link id.co.sigma.common.security.domain.PageDefinition}
	 * @return pageDefinition
	 */
	public PageDefinition getPageDefinition() {
		return pageDefinition;
	}

	/**
	 * reference ke object PageDefinition {@link id.co.sigma.common.security.domain.PageDefinition}
	 * @param pageDefinition
	 */
	public void setPageDefinition(PageDefinition pageDefinition) {
		this.pageDefinition = pageDefinition;
	}

	/**
	 * level dalam tree<br>
	 * COLUMN : TREE_LEVEL_POSITION
	 * @return treeLevelPosition
	 */
	public Integer getTreeLevelPosition() {
		return treeLevelPosition;
	}

	/**
	 * level dalam tree<br>
	 * COLUMN : TREE_LEVEL_POSITION
	 * @param treeLevelPosition
	 */
	public void setTreeLevelPosition(Integer treeLevelPosition) {
		this.treeLevelPosition = treeLevelPosition;
	}

	/**
	 * kode menu untuk helper susun tree, di buat dengan pemisah .(dot)<br>
	 * COLUMN : MENU_TREE_CODE
	 * @return menuTreeCode
	 */
	public String getMenuTreeCode() {
		return menuTreeCode;
	}

	/**
	 * kode menu untuk helper susun tree, di buat dengan pemisah .(dot)<br>
	 * COLUMN : MENU_TREE_CODE
	 * @param menuTreeCode
	 */
	public void setMenuTreeCode(String menuTreeCode) {
		this.menuTreeCode = menuTreeCode;
	}

	/**
	 * status<br>
	 * COLUMN : STATUS
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * status<br>
	 * COLUMN : STATUS
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((application == null) ? 0 : application.hashCode());
		result = prime * result
				+ ((applicationId == null) ? 0 : applicationId.hashCode());
		result = prime * result
				+ ((functionCode == null) ? 0 : functionCode.hashCode());
		result = prime
				* result
				+ ((functionIdParent == null) ? 0 : functionIdParent.hashCode());
		result = prime * result
				+ ((functionLabel == null) ? 0 : functionLabel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((menuTreeCode == null) ? 0 : menuTreeCode.hashCode());
		result = prime * result
				+ ((pageDefinition == null) ? 0 : pageDefinition.hashCode());
		result = prime * result + ((pageId == null) ? 0 : pageId.hashCode());
		result = prime * result
				+ ((siblingOrder == null) ? 0 : siblingOrder.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime
				* result
				+ ((treeLevelPosition == null) ? 0 : treeLevelPosition
						.hashCode());
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
		ApplicationMenu other = (ApplicationMenu) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (applicationId == null) {
			if (other.applicationId != null)
				return false;
		} else if (!applicationId.equals(other.applicationId))
			return false;
		if (functionCode == null) {
			if (other.functionCode != null)
				return false;
		} else if (!functionCode.equals(other.functionCode))
			return false;
		if (functionIdParent == null) {
			if (other.functionIdParent != null)
				return false;
		} else if (!functionIdParent.equals(other.functionIdParent))
			return false;
		if (functionLabel == null) {
			if (other.functionLabel != null)
				return false;
		} else if (!functionLabel.equals(other.functionLabel))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (menuTreeCode == null) {
			if (other.menuTreeCode != null)
				return false;
		} else if (!menuTreeCode.equals(other.menuTreeCode))
			return false;
		if (pageDefinition == null) {
			if (other.pageDefinition != null)
				return false;
		} else if (!pageDefinition.equals(other.pageDefinition))
			return false;
		if (pageId == null) {
			if (other.pageId != null)
				return false;
		} else if (!pageId.equals(other.pageId))
			return false;
		if (siblingOrder == null) {
			if (other.siblingOrder != null)
				return false;
		} else if (!siblingOrder.equals(other.siblingOrder))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (treeLevelPosition == null) {
			if (other.treeLevelPosition != null)
				return false;
		} else if (!treeLevelPosition.equals(other.treeLevelPosition))
			return false;
		return true;
	}

	
	@Override	
	public String toString() {
		return "Function [id=" + id + ", functionIdParent=" + functionIdParent
				+ ", siblingOrder=" + siblingOrder + ", applicationId="
				+ applicationId + ", application=" + application
				+ ", functionCode=" + functionCode + ", functionLabel="
				+ functionLabel + ", pageId=" + pageId + ", pageDefinition="
				+ pageDefinition + ", treeLevelPosition=" + treeLevelPosition
				+ ", menuTreeCode=" + menuTreeCode + ", status=" + status + "]";
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		  
		 Application param1 = getApplication();   
		 if ( param1 != null){ 
		
 //1. Ok tampung dulu variable
//2. null kan variable 
// 3 taruh ke json
			jsonContainer.put("application", param1);
//4. restore lagi 
		}
		jsonContainer.put("application",getApplication());
		jsonContainer.put("applicationId",getApplicationId());
		jsonContainer.put("createdBy",getCreatedBy());
		jsonContainer.put("createdOn",getCreatedOn());
		jsonContainer.put("creatorIPAddress",getCreatorIPAddress());
		jsonContainer.put("functionCode",getFunctionCode());
		jsonContainer.put("functionIdParent",getFunctionIdParent());
		jsonContainer.put("functionLabel",getFunctionLabel());
		jsonContainer.put("id",getId());
		jsonContainer.put("menuTreeCode",getMenuTreeCode());
		jsonContainer.put("modifiedBy",getModifiedBy());
		jsonContainer.put("modifiedByIPAddress",getModifiedByIPAddress());
		jsonContainer.put("modifiedOn",getModifiedOn());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		jsonContainer.put("pageDefinition",getPageDefinition());
		jsonContainer.put("pageId",getPageId());
		jsonContainer.put("siblingOrder",getSiblingOrder());
		jsonContainer.put("status",getStatus());
		jsonContainer.put("treeLevelPosition",getTreeLevelPosition());
	}
	
	@Override
	public ApplicationMenu instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		ApplicationMenu retval = new ApplicationMenu();
		  
		retval.setApplication( (Application)jsonContainer.get("application" ,  Application.class.getName()));
		retval.setApplicationId( (Long)jsonContainer.get("applicationId" ,  Long.class.getName()));
		retval.setCreatedBy( (String)jsonContainer.get("createdBy" ,  String.class.getName()));
		retval.setCreatedOn( (Date)jsonContainer.get("createdOn" ,  Date.class.getName()));
		retval.setCreatorIPAddress( (String)jsonContainer.get("creatorIPAddress" ,  String.class.getName()));
		retval.setFunctionCode( (String)jsonContainer.get("functionCode" ,  String.class.getName()));
		retval.setFunctionIdParent( (Long)jsonContainer.get("functionIdParent" ,  Long.class.getName()));
		retval.setFunctionLabel( (String)jsonContainer.get("functionLabel" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setMenuTreeCode( (String)jsonContainer.get("menuTreeCode" ,  String.class.getName()));
		retval.setModifiedBy( (String)jsonContainer.get("modifiedBy" ,  String.class.getName()));
		retval.setModifiedByIPAddress( (String)jsonContainer.get("modifiedByIPAddress" ,  String.class.getName()));
		retval.setModifiedOn( (Date)jsonContainer.get("modifiedOn" ,  Date.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setPageDefinition( (PageDefinition)jsonContainer.get("pageDefinition" ,  PageDefinition.class.getName()));
		retval.setPageId( (Long)jsonContainer.get("pageId" ,  Long.class.getName()));
		retval.setSiblingOrder( (Integer)jsonContainer.get("siblingOrder" ,  Integer.class.getName()));
		retval.setStatus( (String)jsonContainer.get("status" ,  String.class.getName()));
		retval.setTreeLevelPosition( (Integer)jsonContainer.get("treeLevelPosition" ,  Integer.class.getName()));
		return retval; 
	}
}