package id.co.sigma.common.security.dto;

import id.co.sigma.common.security.domain.ApplicationMenu;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;


import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO untuk wrapper application menu
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ApplicationMenuDTO implements IsSerializable, IJSONFriendlyObject<ApplicationMenuDTO>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7954154563614086527L;
	private Long id ; 
	private Long parentId  ; 
	private Long pageId ; 
	private Integer order ; 
	private String code ; 
	private String label ;
	private Integer treeLevel ; 
	private String menuTreeCode ; 
	private String status ; 
	
	private String createdBy;
	private String modifiedBy;
	private String createdIpAddr;
	private String modifiedIpAddr;
	private Date createdDate;
	private Date modifiedDate;
	
	public ApplicationMenuDTO(){}
	
	public ApplicationMenuDTO(ApplicationMenu f ){
		this.id = f.getId(); 
		this.code = f.getFunctionCode() ; 
		this.label = f.getFunctionLabel() ; 
		this.menuTreeCode = f.getMenuTreeCode(); 
		this.order = f.getSiblingOrder() ; 
		this.pageId = f.getPageId() ; 
		this.parentId = f.getFunctionIdParent() ; 
		this.status = f.getStatus() ; 
		this.treeLevel = f.getTreeLevelPosition() ; 
		if ( f.getPageDefinition()!= null){
			this.pageDetail = new PageDefinitionDTO(); 
		}
	}
	 
	/**
	 * sub menus. di susun di client
	 **/
	private ArrayList<ApplicationMenuDTO> subMenus ; 
	private PageDefinitionDTO pageDetail ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

	public String getMenuTreeCode() {
		return menuTreeCode;
	}

	public void setMenuTreeCode(String menuTreeCode) {
		this.menuTreeCode = menuTreeCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PageDefinitionDTO getPageDetail() {
		return pageDetail;
	}

	public void setPageDetail(PageDefinitionDTO pageDetail) {
		this.pageDetail = pageDetail;
	} 
	
	/**
	 * sub menus. di susun di client
	 **/
	public void setSubMenus(ArrayList<ApplicationMenuDTO> subMenus) {
		this.subMenus = subMenus;
	}
	/**
	 * sub menus. di susun di client
	 **/
	public ArrayList<ApplicationMenuDTO> getSubMenus() {
		return subMenus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCreatedIpAddr() {
		return createdIpAddr;
	}

	public void setCreatedIpAddr(String createdIpAddr) {
		this.createdIpAddr = createdIpAddr;
	}

	public String getModifiedIpAddr() {
		return modifiedIpAddr;
	}

	public void setModifiedIpAddr(String modifiedIpAddr) {
		this.modifiedIpAddr = modifiedIpAddr;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("code",getCode());
		jsonContainer.put("createdBy",getCreatedBy());
		jsonContainer.put("createdDate",getCreatedDate());
		jsonContainer.put("createdIpAddr",getCreatedIpAddr());
		jsonContainer.put("id",getId());
		jsonContainer.put("label",getLabel());
		jsonContainer.put("menuTreeCode",getMenuTreeCode());
		jsonContainer.put("modifiedBy",getModifiedBy());
		jsonContainer.put("modifiedDate",getModifiedDate());
		jsonContainer.put("modifiedIpAddr",getModifiedIpAddr());
		jsonContainer.put("order",getOrder());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		jsonContainer.put("pageDetail",getPageDetail());
		jsonContainer.put("pageId",getPageId());
		jsonContainer.put("parentId",getParentId());
		jsonContainer.put("status",getStatus());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		jsonContainer.put("subMenus",getSubMenus());
		jsonContainer.put("treeLevel",getTreeLevel());
	}
	
	@Override
	public ApplicationMenuDTO instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		ApplicationMenuDTO retval = new ApplicationMenuDTO();
		retval.setCode( (String)jsonContainer.get("code" ,  String.class.getName()));
		retval.setCreatedBy( (String)jsonContainer.get("createdBy" ,  String.class.getName()));
		retval.setCreatedDate( (Date)jsonContainer.get("createdDate" ,  Date.class.getName()));
		retval.setCreatedIpAddr( (String)jsonContainer.get("createdIpAddr" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setLabel( (String)jsonContainer.get("label" ,  String.class.getName()));
		retval.setMenuTreeCode( (String)jsonContainer.get("menuTreeCode" ,  String.class.getName()));
		retval.setModifiedBy( (String)jsonContainer.get("modifiedBy" ,  String.class.getName()));
		retval.setModifiedDate( (Date)jsonContainer.get("modifiedDate" ,  Date.class.getName()));
		retval.setModifiedIpAddr( (String)jsonContainer.get("modifiedIpAddr" ,  String.class.getName()));
		retval.setOrder( (Integer)jsonContainer.get("order" ,  Integer.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setPageDetail( (PageDefinitionDTO)jsonContainer.get("pageDetail" ,  PageDefinitionDTO.class.getName()));
		retval.setPageId( (Long)jsonContainer.get("pageId" ,  Long.class.getName()));
		retval.setParentId( (Long)jsonContainer.get("parentId" ,  Long.class.getName()));
		retval.setStatus( (String)jsonContainer.get("status" ,  String.class.getName()));
		retval.subMenus =  jsonContainer.getAsArraylist("subMenus", ApplicationMenuDTO.class.getName());
		
		retval.setTreeLevel( (Integer)jsonContainer.get("treeLevel" ,  Integer.class.getName()));
		return retval; 
	}
}

