package id.co.sigma.common.security.menu;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Wrapper untuk menampung menu yg akan dikirim ke client dlm bentuk JSON
 * @author I Gede Mahendra
 * @since Nov 12, 2012, 4:30:51 PM
 * @version $Id
 */
public class ApplicationMenuSecurity implements Serializable,IsSerializable, IJSONFriendlyObject<ApplicationMenuSecurity>{

	private static final long serialVersionUID = 6365548759511385695L;
	
	private Long menuId ;	
	private Long parentId ;
	private String menuCode;
	private String label;
	private String actionCommand;
	private Integer sequence;
	private boolean isHaveChildren = false;
	private Integer level;
	private String menuTreeCode;
	private ArrayList<ApplicationMenuSecurity> miscObject;
	
	private ApplicationMenuSecurity[] children;
	private List<ApplicationMenuSecurity> childrenList;
	
	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getActionCommand() {
		return actionCommand;
	}
	
	public void setActionCommand(String actionCommand) {
		this.actionCommand = actionCommand;
	}
	
	public Integer getSequence() {
		return sequence;
	}
	
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public ApplicationMenuSecurity[] getChildren() {
		return children;
	}

	public void setChildren(ApplicationMenuSecurity[] children) {
		this.children = children;
	}

	public boolean isHaveChildren() {
		return isHaveChildren;
	}

	public void setHaveChildren(boolean isHaveChildren) {
		this.isHaveChildren = isHaveChildren;
	}
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public List<ApplicationMenuSecurity> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<ApplicationMenuSecurity> childrenList) {
		this.childrenList = childrenList;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getMenuTreeCode() {
		return menuTreeCode;
	}

	public void setMenuTreeCode(String menuTreeCode) {
		this.menuTreeCode = menuTreeCode;
	}
		
	public ArrayList<ApplicationMenuSecurity> getMiscObject() {
		return miscObject;
	}

	public void setMiscObject(ArrayList<ApplicationMenuSecurity> miscObject) {
		this.miscObject = miscObject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actionCommand == null) ? 0 : actionCommand.hashCode());
		result = prime * result + Arrays.hashCode(children);
		result = prime * result
				+ ((childrenList == null) ? 0 : childrenList.hashCode());
		result = prime * result + (isHaveChildren ? 1231 : 1237);
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result
				+ ((menuCode == null) ? 0 : menuCode.hashCode());
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result
				+ ((menuTreeCode == null) ? 0 : menuTreeCode.hashCode());
		result = prime * result
				+ ((miscObject == null) ? 0 : miscObject.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result
				+ ((sequence == null) ? 0 : sequence.hashCode());
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
		ApplicationMenuSecurity other = (ApplicationMenuSecurity) obj;
		if (actionCommand == null) {
			if (other.actionCommand != null)
				return false;
		} else if (!actionCommand.equals(other.actionCommand))
			return false;
		if (!Arrays.equals(children, other.children))
			return false;
		if (childrenList == null) {
			if (other.childrenList != null)
				return false;
		} else if (!childrenList.equals(other.childrenList))
			return false;
		if (isHaveChildren != other.isHaveChildren)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (menuCode == null) {
			if (other.menuCode != null)
				return false;
		} else if (!menuCode.equals(other.menuCode))
			return false;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		if (menuTreeCode == null) {
			if (other.menuTreeCode != null)
				return false;
		} else if (!menuTreeCode.equals(other.menuTreeCode))
			return false;
		if (miscObject == null) {
			if (other.miscObject != null)
				return false;
		} else if (!miscObject.equals(other.miscObject))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (sequence == null) {
			if (other.sequence != null)
				return false;
		} else if (!sequence.equals(other.sequence))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApplicationMenuSecurity [menuId=" + menuId + ", parentId="
				+ parentId + ", menuCode=" + menuCode + ", label=" + label
				+ ", actionCommand=" + actionCommand + ", sequence=" + sequence
				+ ", isHaveChildren=" + isHaveChildren + ", level=" + level
				+ ", menuTreeCode=" + menuTreeCode + ", miscObject="
				+ miscObject + ", children=" + Arrays.toString(children)
				+ ", childrenList=" + childrenList + "]";
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("actionCommand",getActionCommand());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/
		ApplicationMenuSecurity[] param2 = getChildren();
		if (param2 != null && param2.length > 0) {
			for ( id.co.sigma.common.security.menu.ApplicationMenuSecurity scn : param2){
				//1. Ok tampung dulu variable
				ApplicationMenuSecurity[] scn_children_tmp = scn.getChildren();
				List<ApplicationMenuSecurity> scn_childrenList_tmp = scn.getChildrenList();
				ArrayList<ApplicationMenuSecurity> scn_miscObject_tmp = scn.getMiscObject();
				//2. null kan variable 
				scn.setChildren(null);
				scn.setChildrenList(null);
				scn.setMiscObject(null);
				// 3 taruh ke json
	
				jsonContainer.appendToArray("children", scn);
				//4. restore lagi 
				scn.setChildren(scn_children_tmp);
				scn.setChildrenList(scn_childrenList_tmp);
				scn.setMiscObject(scn_miscObject_tmp);

			}
		}
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		 
		  List<id.co.sigma.common.security.menu.ApplicationMenuSecurity> param3 = getChildrenList() ; 
		 if (  param3 != null && !param3.isEmpty()){ 
			for ( id.co.sigma.common.security.menu.ApplicationMenuSecurity scn : param3){
				//1. Ok tampung dulu variable
				ApplicationMenuSecurity[] scn_children_tmp = scn.getChildren();
				List<ApplicationMenuSecurity> scn_childrenList_tmp = scn.getChildrenList();
				ArrayList<ApplicationMenuSecurity> scn_miscObject_tmp = scn.getMiscObject();
				//2. null kan variable 
				scn.setChildren(null);
				scn.setChildrenList(null);
				scn.setMiscObject(null);
				// 3 taruh ke json
	
				jsonContainer.appendToArray("childrenList", scn);
				//4. restore lagi 
				scn.setChildren(scn_children_tmp);
				scn.setChildrenList(scn_childrenList_tmp);
				scn.setMiscObject(scn_miscObject_tmp);

			}
		}
		jsonContainer.put("haveChildren",isHaveChildren());
		jsonContainer.put("label",getLabel());
		jsonContainer.put("level",getLevel());
		jsonContainer.put("menuCode",getMenuCode());
		jsonContainer.put("menuId",getMenuId());
		jsonContainer.put("menuTreeCode",getMenuTreeCode());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		jsonContainer.put("miscObject",getMiscObject());
		jsonContainer.put("parentId",getParentId());
		jsonContainer.put("sequence",getSequence());
	}
	
	@Override
	public ApplicationMenuSecurity instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		ApplicationMenuSecurity retval = new ApplicationMenuSecurity();
		retval.setActionCommand( (String)jsonContainer.get("actionCommand" ,  String.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setChildren( (ApplicationMenuSecurity[])jsonContainer.get("children" ,  ApplicationMenuSecurity[].class.getName()));
		Boolean tmp = (Boolean)jsonContainer.get("haveChildren" ,  Boolean.class.getName() ); 
		retval.setHaveChildren( tmp!=null ? tmp.booleanValue() : false);
		retval.setLabel( (String)jsonContainer.get("label" ,  String.class.getName()));
		retval.setLevel( (Integer)jsonContainer.get("level" ,  Integer.class.getName()));
		retval.setMenuCode( (String)jsonContainer.get("menuCode" ,  String.class.getName()));
		retval.setMenuId( (Long)jsonContainer.get("menuId" ,  Long.class.getName()));
		retval.setMenuTreeCode( (String)jsonContainer.get("menuTreeCode" ,  String.class.getName()));
		retval.miscObject  = jsonContainer.getAsArraylist("miscObject", ApplicationMenuSecurity.class.getName()) ; 		
		
		retval.setParentId( (Long)jsonContainer.get("parentId" ,  Long.class.getName()));
		retval.setSequence( (Integer)jsonContainer.get("sequence" ,  Integer.class.getName()));
		return retval; 
	}
}