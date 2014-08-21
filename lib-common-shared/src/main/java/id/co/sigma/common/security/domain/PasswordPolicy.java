/**
 * 
 */
package id.co.sigma.common.security.domain;

import id.co.sigma.common.security.domain.audit.BaseAuditedObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * pojo untuk tabel : sec_password_policy
 * @author Dode
 * @version $Id
 * @since Jan 2, 2013, 3:25:24 PM
 */
@Entity
@Table(name="sec_password_policy")
public class PasswordPolicy extends BaseAuditedObject implements IJSONFriendlyObject<PasswordPolicy> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1829190901868877179L;
	
	/**
	* id password policy<br/>
	* column :PASSWORD_POLICY_ID
	**/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PASSWORD_POLICY_ID")
	private Long id;
	
	/**
	* minimum panjang password<br/>
	* column :MINIMUM_LENGTH
	**/
	@Column(name="MINIMUM_LENGTH")
	private Short minimumLength;
	
	/**
	* minimum alphabet di password<br/>
	* column :MINIMUM_ALPHABETS
	**/
	@Column(name="MINIMUM_ALPHABETS")
	private Short minimumAlphabet;
	
	/**
	* minimum angka di password<br/>
	* column :MINIMUM_NUMERICS
	**/
	@Column(name="MINIMUM_NUMERICS")
	private Short minimumNumeric;
	
	/**
	* regular expression password<br/>
	* column :REGULAR_EXPRESSION
	**/
	@Column(name="REGULAR_EXPRESSION", length=512)
	private String regularExpression;
	
	/**
	* maximum percobaan login<br/>
	* column :MAXIMUM_LOGIN_ATTEMPTS
	**/
	@Column(name="MAXIMUM_LOGIN_ATTEMPTS")
	private Short maximumLoginAttempt;
	
	/**
	* umur password<br/>
	* column :PASSWORD_AGE
	**/
	@Column(name="PASSWORD_AGE")
	private Short passwordAge;
	
	/**
	* <br/>
	* column :OLD_PASSWORD_AGE
	**/
	@Column(name="OLD_PASSWORD_AGE")
	private Short oldPasswordAge;
	
	/**
	* <br/>
	* column :INACTIVE_LIMIT
	**/
	@Column(name="INACTIVE_LIMIT")
	private Short inactiveLimit;
	
	/**
	* <br/>
	* column :DISABLED_LIMIT
	**/
	@Column(name="DISABLED_LIMIT")
	private Short disabledLimit;
	
	/**
	* <br/>
	* column :OLD_PASSWORD_BASE
	**/
	@Column(name="OLD_PASSWORD_BASE")
	private String oldPasswordBase;
	
	/**
	* keterangan regular expression<br/>
	* column :REGEX_DESC
	**/
	@Column(name="REGEX_DESC")
	private String regexDesc;
	
	/**
	* id password policy<br/>
	* column :PASSWORD_POLICY_ID
	**/
	public void setId(Long id){
	  this.id=id;
	}
	
	/**
	* id password policy<br/>
	* column :PASSWORD_POLICY_ID
	**/
	public Long getId(){
	    return this.id;
	}
	
	/**
	* minimum panjang password<br/>
	* column :MINIMUM_LENGTH
	**/
	public void setMinimumLength(Short minimumLength){
	  this.minimumLength=minimumLength;
	}
	
	/**
	* minimum panjang password<br/>
	* column :MINIMUM_LENGTH
	**/
	public Short getMinimumLength(){
	    return this.minimumLength;
	}
	
	/**
	* minimum alphabet di password<br/>
	* column :MINIMUM_ALPHABETS
	**/
	public void setMinimumAlphabet(Short minimumAlphabet){
	  this.minimumAlphabet=minimumAlphabet;
	}
	
	/**
	* minimum alphabet di password<br/>
	* column :MINIMUM_ALPHABETS
	**/
	public Short getMinimumAlphabet(){
	    return this.minimumAlphabet;
	}
	
	/**
	* minimum angka di password<br/>
	* column :MINIMUM_NUMERICS
	**/
	public void setMinimumNumeric(Short minimumNumeric){
	  this.minimumNumeric=minimumNumeric;
	}
	
	/**
	* minimum angka di password<br/>
	* column :MINIMUM_NUMERICS
	**/
	public Short getMinimumNumeric(){
	    return this.minimumNumeric;
	}
	
	/**
	* regular expression password<br/>
	* column :REGULAR_EXPRESSION
	**/
	public void setRegularExpression(String regularExpression){
	  this.regularExpression=regularExpression;
	}
	
	/**
	* regular expression password<br/>
	* column :REGULAR_EXPRESSION
	**/
	public String getRegularExpression(){
	    return this.regularExpression;
	}
	
	/**
	* maximum percobaan login<br/>
	* column :MAXIMUM_LOGIN_ATTEMPTS
	**/
	public void setMaximumLoginAttempt(Short maximumLoginAttempt){
	  this.maximumLoginAttempt=maximumLoginAttempt;
	}
	
	/**
	* maximum percobaan login<br/>
	* column :MAXIMUM_LOGIN_ATTEMPTS
	**/
	public Short getMaximumLoginAttempt(){
	    return this.maximumLoginAttempt;
	}
	
	/**
	* umur password<br/>
	* column :PASSWORD_AGE
	**/
	public void setPasswordAge(Short passwordAge){
	  this.passwordAge=passwordAge;
	}
	
	/**
	* umur password<br/>
	* column :PASSWORD_AGE
	**/
	public Short getPasswordAge(){
	    return this.passwordAge;
	}
	
	/**
	* <br/>
	* column :OLD_PASSWORD_AGE
	**/
	public void setOldPasswordAge(Short oldPasswordAge){
	  this.oldPasswordAge=oldPasswordAge;
	}
	
	/**
	* <br/>
	* column :OLD_PASSWORD_AGE
	**/
	public Short getOldPasswordAge(){
	    return this.oldPasswordAge;
	}
	
	/**
	* <br/>
	* column :INACTIVE_LIMIT
	**/
	public void setInactiveLimit(Short inactiveLimit){
	  this.inactiveLimit=inactiveLimit;
	}
	
	/**
	* <br/>
	* column :INACTIVE_LIMIT
	**/
	public Short getInactiveLimit(){
	    return this.inactiveLimit;
	}
	
	/**
	* <br/>
	* column :DISABLED_LIMIT
	**/
	public void setDisabledLimit(Short disabledLimit){
	  this.disabledLimit=disabledLimit;
	}
	
	/**
	* <br/>
	* column :DISABLED_LIMIT
	**/
	public Short getDisabledLimit(){
	    return this.disabledLimit;
	}
	
	/**
	* <br/>
	* column :OLD_PASSWORD_BASE
	**/
	public void setOldPasswordBase(String oldPasswordBase){
	  this.oldPasswordBase=oldPasswordBase;
	}
	
	/**
	* <br/>
	* column :OLD_PASSWORD_BASE
	**/
	public String getOldPasswordBase(){
	    return this.oldPasswordBase;
	}
	
	/**
	* keterangan regular expression<br/>
	* column :REGEX_DESC
	**/
	public void setRegexDesc(String regexDesc){
	  this.regexDesc=regexDesc;
	}
	
	/**
	* keterangan regular expression<br/>
	* column :REGEX_DESC
	**/
	public String getRegexDesc(){
	    return this.regexDesc;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("createdBy",getCreatedBy());
		jsonContainer.put("createdOn",getCreatedOn());
		jsonContainer.put("creatorIPAddress",getCreatorIPAddress());
		jsonContainer.put("disabledLimit",getDisabledLimit());
		jsonContainer.put("id",getId());
		jsonContainer.put("inactiveLimit",getInactiveLimit());
		jsonContainer.put("maximumLoginAttempt",getMaximumLoginAttempt());
		jsonContainer.put("minimumAlphabet",getMinimumAlphabet());
		jsonContainer.put("minimumLength",getMinimumLength());
		jsonContainer.put("minimumNumeric",getMinimumNumeric());
		jsonContainer.put("modifiedBy",getModifiedBy());
		jsonContainer.put("modifiedByIPAddress",getModifiedByIPAddress());
		jsonContainer.put("modifiedOn",getModifiedOn());
		jsonContainer.put("oldPasswordAge",getOldPasswordAge());
		jsonContainer.put("oldPasswordBase",getOldPasswordBase());
		jsonContainer.put("passwordAge",getPasswordAge());
		jsonContainer.put("regexDesc",getRegexDesc());
		jsonContainer.put("regularExpression",getRegularExpression());
	}
	
	@Override
	public PasswordPolicy instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		PasswordPolicy retval = new PasswordPolicy();
		retval.setCreatedBy( (String)jsonContainer.get("createdBy" ,  String.class.getName()));
		retval.setCreatedOn( (Date)jsonContainer.get("createdOn" ,  Date.class.getName()));
		retval.setCreatorIPAddress( (String)jsonContainer.get("creatorIPAddress" ,  String.class.getName()));
		retval.setDisabledLimit( (Short)jsonContainer.get("disabledLimit" ,  Short.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setInactiveLimit( (Short)jsonContainer.get("inactiveLimit" ,  Short.class.getName()));
		retval.setMaximumLoginAttempt( (Short)jsonContainer.get("maximumLoginAttempt" ,  Short.class.getName()));
		retval.setMinimumAlphabet( (Short)jsonContainer.get("minimumAlphabet" ,  Short.class.getName()));
		retval.setMinimumLength( (Short)jsonContainer.get("minimumLength" ,  Short.class.getName()));
		retval.setMinimumNumeric( (Short)jsonContainer.get("minimumNumeric" ,  Short.class.getName()));
		retval.setModifiedBy( (String)jsonContainer.get("modifiedBy" ,  String.class.getName()));
		retval.setModifiedByIPAddress( (String)jsonContainer.get("modifiedByIPAddress" ,  String.class.getName()));
		retval.setModifiedOn( (Date)jsonContainer.get("modifiedOn" ,  Date.class.getName()));
		retval.setOldPasswordAge( (Short)jsonContainer.get("oldPasswordAge" ,  Short.class.getName()));
		retval.setOldPasswordBase( (String)jsonContainer.get("oldPasswordBase" ,  String.class.getName()));
		retval.setPasswordAge( (Short)jsonContainer.get("passwordAge" ,  Short.class.getName()));
		retval.setRegexDesc( (String)jsonContainer.get("regexDesc" ,  String.class.getName()));
		retval.setRegularExpression( (String)jsonContainer.get("regularExpression" ,  String.class.getName()));
		return retval; 
	}
}
