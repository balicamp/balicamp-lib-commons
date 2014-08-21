/**
 * 
 */
package id.co.sigma.common.security.domain;

import id.co.sigma.common.data.serializer.AbstractObjectGenerator;

/**
 * object generator dari security data
 * @author Dode
 *
 */
public class SecurityDataObjectGenerator extends AbstractObjectGenerator {

	private static final Class<?> [] CLS ={
		id.co.sigma.common.security.domain.internalization.I18Code.class,
		id.co.sigma.common.security.domain.internalization.I18NTextGroup.class,
		id.co.sigma.common.security.domain.internalization.I18Text.class,
		id.co.sigma.common.security.domain.internalization.I18TextPK.class,
		id.co.sigma.common.security.domain.lov.LookupDetail.class,
		id.co.sigma.common.security.domain.lov.LookupDetailPK.class,
		id.co.sigma.common.security.domain.lov.LookupHeader.class,
		id.co.sigma.common.security.domain.Application.class,
		id.co.sigma.common.security.domain.ApplicationUser.class,
		id.co.sigma.common.security.domain.ApplicationUserKey.class,
		id.co.sigma.common.security.domain.Branch.class,
		id.co.sigma.common.security.domain.BranchAssignment.class,
		id.co.sigma.common.security.domain.ApplicationMenu.class,
		id.co.sigma.common.security.domain.ApplicationMenuAssignment.class,
		id.co.sigma.common.security.domain.PageDefinition.class,
		id.co.sigma.common.security.domain.PasswordPolicy.class,
		id.co.sigma.common.security.domain.Signon.class,
		id.co.sigma.common.security.domain.SignonHistory.class,
		id.co.sigma.common.security.domain.User.class,
		id.co.sigma.common.security.domain.UserGroup.class,
		id.co.sigma.common.security.domain.UserGroupAssignment.class,
		id.co.sigma.common.security.domain.UserPassword.class
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T instantiateSampleObject(String objectFQCN) {
		if (id.co.sigma.common.security.domain.internalization.I18Code.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.internalization.I18Code();
		if (id.co.sigma.common.security.domain.internalization.I18NTextGroup.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.internalization.I18NTextGroup();
		if (id.co.sigma.common.security.domain.internalization.I18Text.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.internalization.I18Text();
		if (id.co.sigma.common.security.domain.internalization.I18TextPK.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.internalization.I18TextPK();
		if (id.co.sigma.common.security.domain.lov.LookupDetail.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.lov.LookupDetail();
		if (id.co.sigma.common.security.domain.lov.LookupDetailPK.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.lov.LookupDetailPK();
		if (id.co.sigma.common.security.domain.lov.LookupHeader.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.lov.LookupHeader();
		if (id.co.sigma.common.security.domain.Application.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.Application();
		if (id.co.sigma.common.security.domain.ApplicationUser.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.ApplicationUser();
		if (id.co.sigma.common.security.domain.ApplicationUserKey.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.ApplicationUserKey();
		if (id.co.sigma.common.security.domain.Branch.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.Branch();
		if (id.co.sigma.common.security.domain.BranchAssignment.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.BranchAssignment();
		if (id.co.sigma.common.security.domain.ApplicationMenu.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.ApplicationMenu();
		if (id.co.sigma.common.security.domain.ApplicationMenuAssignment.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.ApplicationMenuAssignment();
		if (id.co.sigma.common.security.domain.PageDefinition.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.PageDefinition();
		if (id.co.sigma.common.security.domain.PasswordPolicy.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.PasswordPolicy();
		if (id.co.sigma.common.security.domain.Signon.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.Signon();
		if (id.co.sigma.common.security.domain.SignonHistory.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.SignonHistory();
		if (id.co.sigma.common.security.domain.User.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.User();
		if (id.co.sigma.common.security.domain.UserGroup.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.UserGroup();
		if (id.co.sigma.common.security.domain.UserGroupAssignment.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.UserGroupAssignment();
		if (id.co.sigma.common.security.domain.UserPassword.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.security.domain.UserPassword();
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] instantiateArray(String objectFQCN, int size) {
		if (id.co.sigma.common.security.domain.internalization.I18Code.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.internalization.I18Code[size];
		if (id.co.sigma.common.security.domain.internalization.I18NTextGroup.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.internalization.I18NTextGroup[size];
		if (id.co.sigma.common.security.domain.internalization.I18Text.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.internalization.I18Text[size];
		if (id.co.sigma.common.security.domain.internalization.I18TextPK.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.internalization.I18TextPK[size];
		if (id.co.sigma.common.security.domain.lov.LookupDetail.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.lov.LookupDetail[size];
		if (id.co.sigma.common.security.domain.lov.LookupDetailPK.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.lov.LookupDetailPK[size];
		if (id.co.sigma.common.security.domain.lov.LookupHeader.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.lov.LookupHeader[size];
		if (id.co.sigma.common.security.domain.Application.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.Application[size];
		if (id.co.sigma.common.security.domain.ApplicationUser.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.ApplicationUser[size];
		if (id.co.sigma.common.security.domain.ApplicationUserKey.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.ApplicationUserKey[size];
		if (id.co.sigma.common.security.domain.Branch.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.Branch[size];
		if (id.co.sigma.common.security.domain.BranchAssignment.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.BranchAssignment[size];
		if (id.co.sigma.common.security.domain.ApplicationMenu.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.ApplicationMenu[size];
		if (id.co.sigma.common.security.domain.ApplicationMenuAssignment.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.ApplicationMenuAssignment[size];
		if (id.co.sigma.common.security.domain.PageDefinition.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.PageDefinition[size];
		if (id.co.sigma.common.security.domain.PasswordPolicy.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.PasswordPolicy[size];
		if (id.co.sigma.common.security.domain.Signon.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.Signon[size];
		if (id.co.sigma.common.security.domain.SignonHistory.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.SignonHistory[size];
		if (id.co.sigma.common.security.domain.User.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.User[size];
		if (id.co.sigma.common.security.domain.UserGroup.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.UserGroup[size];
		if (id.co.sigma.common.security.domain.UserGroupAssignment.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.UserGroupAssignment[size];
		if (id.co.sigma.common.security.domain.UserPassword.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.security.domain.UserPassword[size];
		return null;
	}

	@Override
	public Class<?>[] generatedClass() {
		return CLS;
	}

}
