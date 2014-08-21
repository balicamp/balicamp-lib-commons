package id.co.sigma.common.data.app.security;

import id.co.sigma.common.security.domain.ApplicationMenu;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;


import java.util.ArrayList;
import java.util.List;


/**
 * data untuk menu editing
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class MenuEditingData implements IJSONFriendlyObject<MenuEditingData>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2422219585825944379L;



	/**
	 * data functions yang available dalam 1 application
	 */
	private List<ApplicationMenu> allMenus ; 
	
	
	
	/**
	 * menu yang kondisi nya selected
	 */
	private List<Long> allSelectedIds ; 
	
	/**
	 * data functions yang available dalam 1 application
	 */
	public void setAllMenus(List<ApplicationMenu> allMenus) {
		this.allMenus = allMenus;
	}
	/**
	 * data functions yang available dalam 1 application
	 */
	public List<ApplicationMenu> getAllMenus() {
		return allMenus;
	}
	
	/**
	 * menu yang kondisi nya selected
	 */
	public List<Long> getAllSelectedIds() {
		return allSelectedIds;
	}
	/**
	 * menu yang kondisi nya selected
	 */
	public void setAllSelectedIds(List<Long> allSelectedIds) {
		this.allSelectedIds = allSelectedIds;
	}
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("allMenus", allMenus);
		if ( allSelectedIds!= null && allSelectedIds.isEmpty()){
			Long[] ids = new Long[allSelectedIds.size()];
			int i= 0 ; 
			for ( Long scn : allSelectedIds){
				ids[i++] = scn  ;
			}
			jsonContainer.appendToArray("allSelectedIds", ids);
		}
	}
	@Override
	public MenuEditingData instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		MenuEditingData retval = new MenuEditingData(); 
		retval.allMenus = jsonContainer.getAsArraylist("allMenus", ApplicationMenu.class.getName()); 
		Long[] selectedIdsJson =  jsonContainer.getAsArrayOfLongs("allSelectedIds" );
		if ( selectedIdsJson!= null && selectedIdsJson.length>0){
			retval.allSelectedIds = new ArrayList<Long>(); 
			for ( Long scn : selectedIdsJson) {
				retval.allSelectedIds.add(scn); 
			}
		}
		return retval;
	}

}
