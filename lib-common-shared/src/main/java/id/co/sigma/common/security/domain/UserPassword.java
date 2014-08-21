package id.co.sigma.common.security.domain;

import id.co.sigma.common.security.domain.audit.BaseCreatedObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entiti untuk tabel : sec_password_hs
 * @author I Gede Mahendra
 * @since Nov 19, 2012, 2:38:18 PM
 * @version $Id
 */
@Entity
@Table(name="sec_password_hs")
public class UserPassword extends BaseCreatedObject implements IJSONFriendlyObject<UserPassword>{

	private static final long serialVersionUID = 1140422460020887761L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pk")
	private Long id;
	
	@Column(name="user_id")
	private Long userId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="effective_date")
	private Date effectiveDate;
	
	@Column(name="chipper_text")
	private String cipherText;

	/**
	 * password id<br>Column : PASSWORD_ID
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * password id<br>Column : PASSWORD_ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * user id<br>Column : USER_ID
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * user id<br>Column : USER_ID
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * reference object User
	 * @return user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * reference object User
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Tanggal Efektif<br>Column : EFFECTIVE_DATE
	 * @return effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Tanggal Efektif<br>Column : EFFECTIVE_DATE
	 * @param effectiveDate
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Cipher text<br>Column : CIPHER_TEXT
	 * @return cipherText
	 */
	public String getCipherText() {
		return cipherText;
	}
	
	/**
	 * Cipher text<br>Column : CIPHER_TEXT
	 * @param cipherText
	 */
	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("cipherText",getCipherText());
		jsonContainer.put("createdBy",getCreatedBy());
		jsonContainer.put("createdOn",getCreatedOn());
		jsonContainer.put("creatorIPAddress",getCreatorIPAddress());
		jsonContainer.put("effectiveDate",getEffectiveDate());
		jsonContainer.put("id",getId());
		  
		 User param8 = getUser();   
		 if ( param8 != null){ 
		
 //1. Ok tampung dulu variable
			Application param8_defaultApplication_tmp = param8.getDefaultApplication();
//2. null kan variable 
			param8.setDefaultApplication(null);
// 3 taruh ke json
			jsonContainer.put("user", param8);
//4. restore lagi 
			param8.setDefaultApplication(param8_defaultApplication_tmp);
		}
		jsonContainer.put("user",getUser());
		jsonContainer.put("userId",getUserId());
	}
	
	@Override
	public UserPassword instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		UserPassword retval = new UserPassword();
		retval.setCipherText( (String)jsonContainer.get("cipherText" ,  String.class.getName()));
		retval.setCreatedBy( (String)jsonContainer.get("createdBy" ,  String.class.getName()));
		retval.setCreatedOn( (Date)jsonContainer.get("createdOn" ,  Date.class.getName()));
		retval.setCreatorIPAddress( (String)jsonContainer.get("creatorIPAddress" ,  String.class.getName()));
		retval.setEffectiveDate( (Date)jsonContainer.get("effectiveDate" ,  Date.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		  
		retval.setUser( (User)jsonContainer.get("user" ,  User.class.getName()));
		retval.setUserId( (Long)jsonContainer.get("userId" ,  Long.class.getName()));
		return retval; 
	}
}