/**
 * 
 */
package id.co.sigma.common.security;

import java.util.logging.Level;
import java.util.logging.Logger;

import id.co.sigma.common.data.serializer.AbstractObjectGenerator;

/**
 * object generator untuk security
 * @author Dode
 *
 */
public class SecuritySharedObjectGenerator extends AbstractObjectGenerator {
	
	
	
	private static final Logger logger = Logger.getLogger(SecuritySharedObjectGenerator.class.getName()); 

	private static final Class<?> [] CLS ={
		id.co.sigma.common.security.dto.ApplicationDTO.class,
		id.co.sigma.common.security.dto.ApplicationMenuDTO.class,
		id.co.sigma.common.security.dto.BranchDTO.class,
		id.co.sigma.common.security.dto.FunctionDTO.class,
		id.co.sigma.common.security.dto.PageDefinitionDTO.class,
		id.co.sigma.common.security.dto.UserDetailDTO.class,
		id.co.sigma.common.security.dto.UserDTO.class,
		id.co.sigma.common.security.dto.UserGroupAssignmentDTO.class,
		id.co.sigma.common.security.dto.UserGroupDTO.class,
		id.co.sigma.common.security.exception.MenuHaveChildException.class,
		id.co.sigma.common.security.exception.PasswordPolicyException.class,
		id.co.sigma.common.security.menu.ApplicationMenuSecurity.class,
		id.co.sigma.common.security.menu.UserDomain.class,
		id.co.sigma.common.security.menu.UserDomainPaging.class,
		id.co.sigma.common.security.LoginParameter.class,
		id.co.sigma.common.security.LoginResultData.class, 
		 
		id.co.sigma.common.security.ApplicationSessionRegistry.class  , 
		id.co.sigma.common.security.exception.UserNotExistException.class
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T instantiateSampleObject(String objectFQCN) {
		if (id.co.sigma.common.security.dto.ApplicationDTO.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.dto.ApplicationDTO();
		if (id.co.sigma.common.security.dto.ApplicationMenuDTO.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.dto.ApplicationMenuDTO();
		if (id.co.sigma.common.security.dto.BranchDTO.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.dto.BranchDTO();
		if (id.co.sigma.common.security.dto.FunctionDTO.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.dto.FunctionDTO();
		if (id.co.sigma.common.security.dto.PageDefinitionDTO.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.dto.PageDefinitionDTO();
		if (id.co.sigma.common.security.dto.UserDetailDTO.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.dto.UserDetailDTO();
		if (id.co.sigma.common.security.dto.UserDTO.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.dto.UserDTO();
		if (id.co.sigma.common.security.dto.UserGroupAssignmentDTO.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.dto.UserGroupAssignmentDTO();
		if (id.co.sigma.common.security.dto.UserGroupDTO.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.dto.UserGroupDTO();
		if (id.co.sigma.common.security.exception.MenuHaveChildException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.exception.MenuHaveChildException();
		if (id.co.sigma.common.security.exception.PasswordPolicyException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.exception.PasswordPolicyException();
		if (id.co.sigma.common.security.menu.ApplicationMenuSecurity.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.menu.ApplicationMenuSecurity();
		if (id.co.sigma.common.security.menu.UserDomain.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.menu.UserDomain();
		if (id.co.sigma.common.security.menu.UserDomainPaging.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.menu.UserDomainPaging();
		if (id.co.sigma.common.security.LoginParameter.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.LoginParameter();
		if (id.co.sigma.common.security.LoginResultData.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.LoginResultData();
		
		if ( id.co.sigma.common.security.ApplicationSessionRegistry.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.ApplicationSessionRegistry();
		if ( id.co.sigma.common.security.exception.UserNotExistException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.exception.UserNotExistException(); 
		logger.log(Level.SEVERE , "FQCN :" + objectFQCN +", tidak di temukan dalam class :" + this.getClass().getName()  + ",menyerah sekarang, di lempar ke next chain");
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] instantiateArray(String objectFQCN, int size) {
		if (id.co.sigma.common.security.dto.ApplicationDTO.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.dto.ApplicationDTO[size];
		if (id.co.sigma.common.security.dto.ApplicationMenuDTO.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.dto.ApplicationMenuDTO[size];
		if (id.co.sigma.common.security.dto.BranchDTO.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.dto.BranchDTO[size];
		if (id.co.sigma.common.security.dto.FunctionDTO.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.dto.FunctionDTO[size];
		if (id.co.sigma.common.security.dto.PageDefinitionDTO.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.dto.PageDefinitionDTO[size];
		if (id.co.sigma.common.security.dto.UserDetailDTO.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.dto.UserDetailDTO[size];
		if (id.co.sigma.common.security.dto.UserDTO.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.dto.UserDTO[size];
		if (id.co.sigma.common.security.dto.UserGroupAssignmentDTO.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.dto.UserGroupAssignmentDTO[size];
		if (id.co.sigma.common.security.dto.UserGroupDTO.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.dto.UserGroupDTO[size];
		if (id.co.sigma.common.security.exception.MenuHaveChildException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.exception.MenuHaveChildException[size];
		if (id.co.sigma.common.security.exception.PasswordPolicyException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.exception.PasswordPolicyException[size];
		if (id.co.sigma.common.security.menu.ApplicationMenuSecurity.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.menu.ApplicationMenuSecurity[size];
		if (id.co.sigma.common.security.menu.UserDomain.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.menu.UserDomain[size];
		if (id.co.sigma.common.security.menu.UserDomainPaging.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.menu.UserDomainPaging[size];
		if (id.co.sigma.common.security.LoginParameter.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.LoginParameter[size];
		if (id.co.sigma.common.security.LoginResultData.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.LoginResultData[size];
		
		if( id.co.sigma.common.security.ApplicationSessionRegistry.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.ApplicationSessionRegistry[size];
		if ( id.co.sigma.common.security.exception.UserNotExistException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.exception.UserNotExistException[size];
		return null;
	}

	@Override
	public Class<?>[] generatedClass() {
		return CLS;
	}

}
