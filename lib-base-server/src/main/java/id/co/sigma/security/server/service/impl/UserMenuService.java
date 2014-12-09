package id.co.sigma.security.server.service.impl;


import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleQueryFilterOperator;
import id.co.sigma.common.security.domain.ApplicationMenu;
import id.co.sigma.common.security.domain.ApplicationMenuAssignment;
import id.co.sigma.common.security.domain.PageDefinition;
import id.co.sigma.common.security.domain.Signon;
import id.co.sigma.common.security.domain.UserDelegation;
import id.co.sigma.common.security.domain.UserDelegationGroup;
import id.co.sigma.common.security.domain.UserGroupAssignment;
import id.co.sigma.common.security.menu.ApplicationMenuSecurity;
import id.co.sigma.common.server.service.AbstractService;
import id.co.sigma.security.server.dao.IFunctionDao;
import id.co.sigma.security.server.dao.impl.UserMenuDaoImpl;
import id.co.sigma.security.server.service.IUserMenuService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

/**
 * Service menu
 * @author I Gede Mahendra
 * @since Nov 19, 2012, 11:58:06 AM
 * @version $Id
 */

public class UserMenuService extends AbstractService implements IUserMenuService{

	@Autowired
	private UserMenuDaoImpl userDao;
	
	@Autowired
	IFunctionDao functionDao ; 
	
	private Gson gson = new Gson();
	
	@Transactional(readOnly=true, propagation=Propagation.REQUIRES_NEW)
	@Override
	public String createJsonApplicationMenu(Signon parameter) throws Exception {		
		String resultJson = "";
		try {
			List<ApplicationMenuSecurity> menu = getMenuApplication(parameter);
			if(menu != null){
				resultJson = gson.toJson(menu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return resultJson;
	}
	
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	@Override
	public List<ApplicationMenuSecurity> getMenuApplication(Signon parameter) throws Exception {
		List<ApplicationMenuSecurity> result = null;
		List<ApplicationMenu> resultFunction = getFunctionMenu(parameter);
						
		//Jika menu ada sesuai dg userId yg ditemukan
		if(resultFunction != null){
			result = convertToApplicationMenu(resultFunction);
		}		
		return result;
	}
	
	
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	@Override
	public List<ApplicationMenuSecurity> getMenuApplication(Long userId) throws Exception {
		List<ApplicationMenuSecurity> result = null;
		List<ApplicationMenu> resultFunction = getAllowedMenusByUserId(userId);
		if(resultFunction != null){
			plugPage(resultFunction);
			result = convertToApplicationMenu(resultFunction);
		}		
		return result;
		
		
	}
	
	
	
	private void plugPage (List<ApplicationMenu>  menus ) {
		Map<Long, ApplicationMenu> indexedFunctions = new HashMap<Long, ApplicationMenu>() ;
		for ( ApplicationMenu scn : menus) {
			if ( scn.getPageId() == null)
				continue ; 
			indexedFunctions.put(scn.getPageId(), scn); 
		}
		if ( indexedFunctions.isEmpty())
			return ; 
		List<PageDefinition> pgs =  functionDao.getPageDefinitionByPageIds(indexedFunctions.keySet());
		if ( pgs!= null ){
			for ( PageDefinition scn : pgs) {
				if ( indexedFunctions.containsKey(scn.getId()))
					indexedFunctions.get(scn.getId()).setPageDefinition(scn);
			}
		}
	}
	
	/**
	 * Get function menu
	 * @param parameter - Object signon
	 * @return List of Function
	 * @throws Exception
	 */
	private List<ApplicationMenu> getFunctionMenu(Signon parameter) throws Exception{
		
		
		Signon resultSignon = userDao.getDataSignonByParam(parameter); //Get user_id
		if(resultSignon != null){
			return getAllowedMenusByUserId(resultSignon.getUserId());			
		}//end if
		return null;
	}
	
	
	/**
	 * mencari menu apa saja yang di miliki oleh user. ini di cari dengan ID dari user. <br/>
	 * apa yang di lakukan : 
	 * <ol>
	 * <li>cari group user dari table sec_user_assignment</li>
	 * <li>dari group user, locate function apa saja si user dapat nya</li>
	 * </ol>
	 * @param userId id dari user
	 **/
	public List<ApplicationMenu> getAllowedMenusByUserId (Long userId)throws Exception  {
		List<ApplicationMenu> resultFunction = null;
		
		List<UserDelegationGroup> delegateGroups = null;
		
		UserDelegation ud = getUserDelegation(userId);
		
		List<UserGroupAssignment> resultGroupAssignment = userDao.getGroupAssigmentByParam(userId); //Get group_id
		
		if(ud != null) {
			delegateGroups = getUserGroupDelegation(ud);
		}
		
		if(resultGroupAssignment != null){
			List<Long> listGroupId = new ArrayList<Long>();
			for (UserGroupAssignment groupAssignment : resultGroupAssignment) {
				if("A".equals(groupAssignment.getUserGroup().getActiveFlag())){
					listGroupId.add(groupAssignment.getGroupId());
				}
			}//end for
			
			if(delegateGroups != null) {
				for(UserDelegationGroup delegateGroup : delegateGroups) {
					if("A".equals(delegateGroup.getUserGroup().getActiveFlag())) {
						if(!listGroupId.contains(delegateGroup.getGroupId())) {
							listGroupId.add(delegateGroup.getGroupId());
						}
					}
				}
			}
			
			List<ApplicationMenuAssignment> resultFunctionAssignment = userDao.getFunctionAssignmentByGroupId(listGroupId); //get function_assignment
			if(resultFunctionAssignment != null){
				List<Long> listFunctionId = new ArrayList<Long>();
				for (ApplicationMenuAssignment functionAssignment : resultFunctionAssignment) {
					if (!listFunctionId.contains(functionAssignment.getFunctionId()))
						listFunctionId.add(functionAssignment.getFunctionId());
				}//end for
				
				resultFunction = userDao.getFunctionMenuByFunctionId(listFunctionId); //get function
			}//end if				
		}//end if
		return resultFunction ; 
	}
	
	
	private List<UserDelegationGroup> getUserGroupDelegation(UserDelegation ud) {
		try {

			
			List <UserDelegationGroup> groupAss = userDao.list(UserDelegationGroup.class, new SimpleQueryFilter[]{
				new SimpleQueryFilter("userDelegateId", SimpleQueryFilterOperator.equal, ud.getId())},
					null);
			
			return groupAss;
			
		} catch(Exception e) {}
		return null;
	}
	
	
	private UserDelegation getUserDelegation(Long userId) {
		Date cDate =  Calendar.getInstance().getTime();
		SimpleQueryFilter[] delegationFilter = new SimpleQueryFilter[] {
				new SimpleQueryFilter("destUserId", SimpleQueryFilterOperator.equal, userId),
				new SimpleQueryFilter("dataStatus", SimpleQueryFilterOperator.equal, "A"),
				new SimpleQueryFilter("startDate", SimpleQueryFilterOperator.lessEqual, cDate),
				new SimpleQueryFilter("endDate", SimpleQueryFilterOperator.greaterEqual, cDate)
		};
		try {
			
			List <UserDelegation> userDelegations = userDao.list(UserDelegation.class, delegationFilter, null);
			
			if(userDelegations != null && !userDelegations.isEmpty()) {
				return userDelegations.get(0);
			}
			
		} catch(Exception e) {}
		return null;
	}
	
	
	
	/**
	 * Konversi dari object function ke ApplicationMenuSecurity
	 * @param data
	 * @return
	 */
	private List<ApplicationMenuSecurity> convertToApplicationMenu(List<ApplicationMenu> data){
		List<ApplicationMenuSecurity> result = new ArrayList<ApplicationMenuSecurity>();
		try {			
			for (ApplicationMenu function : data) {
				ApplicationMenuSecurity appMenu = new ApplicationMenuSecurity();
				if(function.getPageDefinition() != null){
					if ( function.getPageDefinition().getPageUrl()== null)
						appMenu.setActionCommand(function.getPageDefinition().getPageCode());
					else
						appMenu.setActionCommand(function.getPageDefinition().getPageUrl());
				}							
				if(function.getPageId() == null){
					appMenu.setHaveChildren(true);
				}else{
					appMenu.setHaveChildren(false);
				}				
				appMenu.setLabel(function.getFunctionLabel());
				if ( function.getPageDefinition()!= null){
					appMenu.setMenuCode(function.getPageDefinition().getPageCode());
				}else{
					appMenu.setMenuCode(function.getFunctionCode());
				}
				
				
				appMenu.setMenuId(function.getId());
				appMenu.setParentId(function.getFunctionIdParent());
				appMenu.setLevel(function.getTreeLevelPosition());
				appMenu.setMenuTreeCode(function.getMenuTreeCode());
				result.add(appMenu);
				appMenu.setSequence(function.getSiblingOrder());
			}//end for			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
}