package id.co.sigma.common.server.data.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



/**
 * data yang di pakai untuk authentikasi
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class SimpleUserData implements UserDetails{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4713198240291569186L;


	private String username ; 
	
	/**
	 * full name dari current user
	 * 
	 **/
	private String fullName ;
	
	/**
	 * email dari user
	 **/
	private String email ;
	/**
	 * kode unit kerja
	 **/
	private String branchCode ;
	/**
	 * nama unit kerja
	 **/
	private String branchName ; 

	
	/**
	 * UUID dari user. ini untuk request menu ke user
	 **/
	private String uuid ; 
	
	
	
	/**
	 * authorities
	 **/
	private Collection<CoreComponentAuthority> authorities; 
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Collection<CoreComponentAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		
		return null;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getUsername() {
		return username;
	}
	
	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * UUID dari user. ini untuk request menu ke user
	 **/
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * UUID dari user. ini untuk request menu ke user
	 **/
	public String getUuid() {
		return uuid;
	}
	/**
	 * full name dari current user
	 * 
	 **/
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * full name dari current user
	 * 
	 **/
	public String getFullName() {
		return fullName;
	}
	
	
	/**
	 * email dari user
	 **/
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * email dari user
	 **/
	public String getEmail() {
		return email;
	}
	
	/**
	 * kode unit kerja
	 **/
	public String getBranchCode() {
		return branchCode;
	}
	/**
	 * kode unit kerja
	 **/
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	
	/**
	 * nama unit kerja
	 **/
	public String getBranchName() {
		return branchName;
	}
	/**
	 * nama unit kerja
	 **/
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result
				+ ((branchCode == null) ? 0 : branchCode.hashCode());
		result = prime * result
				+ ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		SimpleUserData other = (SimpleUserData) obj;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (branchCode == null) {
			if (other.branchCode != null)
				return false;
		} else if (!branchCode.equals(other.branchCode))
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SigmaSimpleUserData [username=" + username + ", fullName="
				+ fullName + ", email=" + email + ", branchCode=" + branchCode
				+ ", branchName=" + branchName + ", uuid=" + uuid
				+ ", authorities=" + authorities + "]";
	}
}
