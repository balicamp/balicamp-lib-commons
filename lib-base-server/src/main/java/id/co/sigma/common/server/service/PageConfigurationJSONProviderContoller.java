package id.co.sigma.common.server.service;

import java.util.List;




import javax.servlet.http.HttpServletRequest;

import id.co.sigma.common.data.CommonLibraryConstant;
import id.co.sigma.common.data.entity.FormElementConfiguration;
import id.co.sigma.common.data.entity.I18NTextGroup;
import id.co.sigma.common.data.entity.I18Text;
import id.co.sigma.common.server.controllers.BaseController;
import id.co.sigma.common.server.dao.system.ApplicationConfigurationDao;
import id.co.sigma.common.server.dao.system.IApplicationConfigFieldControl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * controller page. ini memprovide JSON application configuration untuk applikasi
 **/
@Controller
public class PageConfigurationJSONProviderContoller extends BaseController{
	
	
	
	/**
	 * template json untuk form elemen configurations
	 **/
	protected static final String FORM_ELEMENT_CONFIG_JSON_TEMPLATE ="{\"formId\":\"%s\"," + 
		"\"elementId\":\"%s\"," +
		"\"hintI18NKey\":\"%s\"," +
		"\"placeHolderI18NKey\":\"%s\"," +
		"\"mandatory\":%s," +
		"\"maxLength\":%s," +
		"\"minValue\":\"%s\"," +
		"\"maxValue\":\"%s\"," +
		"\"groupId\":\"%s\"}";
	
	
	protected static final String LOAD_FORM_CONFIG_SCRIPT_TEMPLATE ="$.ajax({ " + 	
				" type: 'GET', "+	
				" global: true, "+	
				" url: \"%s" +CommonLibraryConstant.COMMON_CONTROL_CONFIGURATION_URL +"/db-driven/%s-form-config.json\", "+	
				" async: false, 	"+
				" dataType: 'script' });";
	@org.springframework.beans.factory.annotation.Autowired
	private ApplicationConfigurationDao  applicationConfigurationDao ; 
	
	@org.springframework.beans.factory.annotation.Autowired
	private IApplicationConfigFieldControl applicationConfigFieldDao ; 
	
	
	
	
	@RequestMapping(value="/{i18Code}/i18-groups.json")
	public @ResponseBody String getInternalizationTextGroup(@PathVariable String i18Code){
		List<I18NTextGroup> groups =  applicationConfigurationDao.getTextGroups();
		 
		String baseUrl = getBaseAppUrl() + CommonLibraryConstant.COMMON_CONTROL_CONFIGURATION_URL ;
		StringBuffer buffer = new StringBuffer();
		if ( groups!=null&&!groups.isEmpty()){
			for ( I18NTextGroup scn : groups){
				
				buffer.append("$.ajax({ " + 
						"	type: \"GET\", "+
						"	global: true, " +
						"	url: \"" +baseUrl+ "/" + i18Code+"/" + scn.getId() + "-texts.json\", " +
						"	async: false, " +
						"	dataType: \"script\""+
						" });");
				
			}
			
		}
		
		return buffer.toString() ; 
	}
	
	
	
	/**
	 * ini merender json untuk 1 group. misal kalau code = security, panel konfig apa saja akan di kirim dalam format json ke client
	 * 
	 **/
	@RequestMapping(value= CommonLibraryConstant.COMMON_CONTROL_CONFIGURATION_URL +  "/{groupCode}/panel-config-by-group-id.json")
	public String getPanelConfigurationByGroupId (@PathVariable String groupCode ){
		return null ; 
	}
	
	
	/**
	 * menormal kan string json, remove " dengan \" dan modifikasi minor lain nya
	 **/
	private String normalizeJsonString (String theString) {
		if ( theString==null||theString.length()==0)
			return "" ; 
		return theString.replaceAll("\\\"", "\\\\" +  "\"");
		
	}
	
	@RequestMapping(value= CommonLibraryConstant.COMMON_CONTROL_CONFIGURATION_URL + "/{i18Code}/{group}-texts.json", produces="application/json")
	public @ResponseBody String getInternalizationTexts(@PathVariable String i18Code,@PathVariable String group ){
		List<I18Text> texts = this.applicationConfigurationDao.getTextsOnGroup(group, i18Code);
		StringBuffer retval = new StringBuffer(); 
		retval.append("var id=id||{};id.co=id.co||{};id.co.sigma=id.co.sigma||{};id.co.sigma.languages=id.co.sigma.languages||{};");
		retval.append("var swap" +group+"=id.co.sigma.languages;"); 
		if ( texts!=null && !texts.isEmpty()){
			for ( I18Text scn : texts){
				retval.append("swap" +group + "[\"" + scn.getId().getTextKey() + "\"]=" + "\"" + scn.getLabel() + "\";") ;
			}
		}
		return retval.toString() ; 
	}
	
	
	
	
	
	
	
	/**
	 * ini untuk menyediakan script json config untuk form configuration
	 **/
	@RequestMapping(value= CommonLibraryConstant.COMMON_CONTROL_CONFIGURATION_URL + "/db-driven/{groupId}-form-config.json", produces="application/json")
	public @ResponseBody String getFormElementConfigurationJson (@PathVariable String groupId ) {
		StringBuffer retval = new StringBuffer(); 
		retval.append("var id=id||{};id.co=id.co||{};id.co.sigma=id.co.sigma||{};id.co.sigma.formConfigs=id.co.sigma.formConfigs||{};");
		retval.append("var swap" +groupId+"=id.co.sigma.formConfigs;"); 
		List<FormElementConfiguration> configs = applicationConfigFieldDao.getFormElementConfigurationsData(groupId);
		if ( configs!=null&& !configs.isEmpty()){
			for ( FormElementConfiguration scn : configs){
				retval.append("swap" +groupId+"[\"" + FormElementConfiguration.generateDataKey(scn.getId().getFormId(), scn.getId().getElementId()) +"\"]=");
				retval.append(String.format(FORM_ELEMENT_CONFIG_JSON_TEMPLATE, 
						normalizeJsonString(scn.getId().getFormId()) , 
						normalizeJsonString(scn.getId().getElementId()), 
						normalizeJsonString(scn.getHintI18NKey()) , 
						normalizeJsonString(scn.getPlaceHolderI18NKey()) , 
						( scn.getMandatory()==null?"null":scn.getMandatory().booleanValue() +"" ) , 
						(scn.getMaxLength()==null?"null" :scn.getMaxLength()  ) , 
						normalizeJsonString(scn.getMinValue()) , 
						normalizeJsonString(scn.getMaxValue() ) , 
						normalizeJsonString(scn.getGroupId())
							)); 
				retval.append(";\n");
				
				
			}
			
		}
		return retval.toString() ;
		
	}
	
	
	
	
	
	
	
	/**
	 * load form configurations all
	 * @param request request param
	 **/
	@RequestMapping(value= CommonLibraryConstant.COMMON_CONTROL_CONFIGURATION_URL +   "/db-driven/load-all-form-config.json", produces="application/json")
	public @ResponseBody String getLoadAllFormConfigurationJs (HttpServletRequest request  ){
		List<String> groups = applicationConfigFieldDao.getFormConfigurationGroups();
		
		StringBuffer retval = new StringBuffer(); 
		if ( groups!=null&&!groups.isEmpty()){
			String baseUrl = getBaseAppUrl();
			for ( String grp : groups){
				retval.append(String.format(LOAD_FORM_CONFIG_SCRIPT_TEMPLATE,baseUrl ,   grp));
			}
		}
		return retval.toString();
	}
	
	
	
	
	
	
	
	
	
	
	

}
