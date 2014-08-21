package id.co.sigma.common.security.menu;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Wrapper user domain dg mode paging
 * @author I Gede Mahendra
 * @since Nov 29, 2012, 5:16:21 PM
 * @version $Id
 */
public class UserDomainPaging implements Serializable,IsSerializable, IJSONFriendlyObject<UserDomainPaging>{

	private static final long serialVersionUID = 2057869636359582112L;
	
	private Integer pagePosition;
	private List<UserDomain> userDomains;
	private Integer totalData;
		
	public Integer getPagePosition() {
		return pagePosition;
	}
	public void setPagePosition(Integer pageId) {
		this.pagePosition = pageId;
	}
	public List<UserDomain> getUserDomains() {
		return userDomains;
	}
	public void setUserDomains(List<UserDomain> userDomains) {
		this.userDomains = userDomains;
	}
	public Integer getTotalData() {
		return totalData;
	}
	public void setTotalData(Integer totalData) {
		this.totalData = totalData;
	}	
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("pagePosition",getPagePosition());
		jsonContainer.put("totalData",getTotalData());
		  List<id.co.sigma.common.security.menu.UserDomain> param4 = getUserDomains() ; 
		 if (  param4 != null && !param4.isEmpty()){ 
			for ( id.co.sigma.common.security.menu.UserDomain scn : param4){
				//1. Ok tampung dulu variable
//2. null kan variable 
// 3 taruh ke json

					jsonContainer.appendToArray("userDomains", scn);
				//4. restore lagi 

				}
		}
	}
	
	@Override
	public UserDomainPaging instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		UserDomainPaging retval = new UserDomainPaging();
		retval.setPagePosition( (Integer)jsonContainer.get("pagePosition" ,  Integer.class.getName()));
		retval.setTotalData( (Integer)jsonContainer.get("totalData" ,  Integer.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		return retval; 
	}
}