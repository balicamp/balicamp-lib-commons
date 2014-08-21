package id.co.sigma.common.security.dto;

import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.security.domain.PageDefinition;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;



/**
 * DTO dari {@link PageDefinition}
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class PageDefinitionDTO implements SingleKeyEntityData<Long>, IJSONFriendlyObject<PageDefinitionDTO> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5241310552314948309L;


	private Long id;
    
    
    private String pageCode;
	
	
	
	private String pageUrl;
	
	
	private String additionalData;
	
	private String remark ; 
	
	
	
	public PageDefinitionDTO(){}
	public PageDefinitionDTO(PageDefinition page){
		this(); 
		this.id  = page.getId() ; 
		this.additionalData =page.getAdditionalData(); 
		this.pageCode = page.getPageCode() ; 
		this.pageUrl = page.getPageUrl() ;
		this.remark = page.getRemark() ; 
		
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPageCode() {
		return pageCode;
	}


	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}


	public String getPageUrl() {
		return pageUrl;
	}


	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}


	public String getAdditionalData() {
		return additionalData;
	}


	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("additionalData",getAdditionalData());
		jsonContainer.put("id",getId());
		jsonContainer.put("pageCode",getPageCode());
		jsonContainer.put("pageUrl",getPageUrl());
		jsonContainer.put("remark",getRemark());
	}
	
	@Override
	public PageDefinitionDTO instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		PageDefinitionDTO retval = new PageDefinitionDTO();
		retval.setAdditionalData( (String)jsonContainer.get("additionalData" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setPageCode( (String)jsonContainer.get("pageCode" ,  String.class.getName()));
		retval.setPageUrl( (String)jsonContainer.get("pageUrl" ,  String.class.getName()));
		retval.setRemark( (String)jsonContainer.get("remark" ,  String.class.getName()));
		return retval; 
	}
	

	

}

