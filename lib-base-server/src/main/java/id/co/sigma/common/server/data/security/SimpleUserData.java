package id.co.sigma.common.server.data.security;

import java.util.Collection;
import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SimpleUserData that = (SimpleUserData) o;
		return Objects.equals(username, that.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public String toString() {
		return "SigmaSimpleUserData [username=" + username + ", fullName="
				+ fullName + ", email=" + email + ", branchCode=" + branchCode
				+ ", branchName=" + branchName + ", uuid=" + uuid
				+ ", authorities=" + authorities + "]";
	}
}
