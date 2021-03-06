/**
 * File Name : User.java
 * Package   : id.co.sigma.arium.security.shared.domain
 * Project   : security-data
 */
package id.co.sigma.common.security.domain;

import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.data.app.SimpleDualControlData;
import id.co.sigma.common.util.json.ParsedJSONContainer;



import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entiti untuk tabel : sec_user
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 2:12:17 PM
 * @version $Id
 */
@Entity
@Table(name="sec_user")
public class User extends SimpleDualControlData<User> implements SingleKeyEntityData<Long> {

	private static final long serialVersionUID = -6026281510605996562L;
	
	/**
	* id<br/>
	* column :USER_ID
	**/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pk")
	private Long id;
	/**
	* kode user<br/>
	* column :USER_CODE
	**/
	@Column(name="user_name" , length=32, nullable=false)
	private String userCode;
	/**
	* tanggal expire<br/>
	* column :EXPIRED_DATE
	**/
	@Column(name="expired_date")
	private Date  expiredDate;
	/**
	* tanggal lahir<br/>
	* column :BIRTHDATE
	**/
	@Column(name="birth_date")
	private Date  birthDate;
	
	/**
	* tanggal update password terakhir<br/>
	* column :last_change_pass
	**/
	@Column(name="last_change_pass")
	private Date  lastChangePassword;
	
	/**
	* email<br/>
	* column :EMAIL
	**/
	@Column(name="email",length= 128)
	private String email;
	
	
	/**
	* failed login attemps<br/>
	* column :FAILED_LOGIN_ATTEMPTS
	**/
	@Column(name="failed_login_attemps")
	private Integer failedLoginAttempts;
	/**
	* real name<br/>
	* column :REAL_NAME
	**/
	@Column(name="real_name" , length=128, nullable=false)
	private String realName;
	/**
	* status<br/>
	* column :STATUS
	**/
	@Column(name="status_code", length=1)
	private String status;
	/**
	* locale<br/>
	* column :LOCALE
	**/
	@Column(name="locale", length=6)
	private String locale;
	/**
	* default application id<br/>
	* column :DEFAULT_APPLICATION_ID
	**/
	@Column(name="default_app_id")
	private Integer defaultApplicationId;
	/**
	* active status<br/>
	* column :ACTIVE_STATUS
	**/
	@Column(name="active_status" , length=1)
	private String activeFlag;
	
	@Column(name="emp_no")
	private String employeeNumber;
	
	/**
	 * add by raka.sanjaya
	 * employeeId type transient, temporary store employee id 
	 */
	@Transient
	private String employeeId;
		
	/**
	 * add by dode
	 * default application
	 * column :DEFAULT_APPLICATION_ID
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="default_app_id", insertable=false, updatable=false)
	private Application defaultApplication;
	
	/**
	* password user<br/>
	* column :CHIPPER_TEXT
	**/
	@Column(name="chipper_text", length=64)
	private String chipperText;
		
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeNumber() {
	    return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
	    this.employeeNumber = employeeNumber;
	}
	/**
	 * kode cabang default user, dia berada di mana <br/>
	 * column : default_branch_code
	 */
	@Column(name="default_branch_code", length=16)
	private String defaultBranchCode; 
	
	
	@ManyToOne(targetEntity=Branch.class , fetch=FetchType.LAZY  )
	@JoinColumns( 
				value={
						@JoinColumn(name="default_branch_code" , updatable=false , insertable=false , referencedColumnName="branch_code") 
				} 
			)
	private Branch branch;
	
	public User(){}
	
	
	
	public User(String userCode, String email, String realName, String locale) {
		super();
		this.userCode = userCode;
		this.email = email;
		this.realName = realName;
		this.locale = locale;
		this.activeFlag="A"; 
		this.defaultApplicationId = 1 ; 
		this.createdOn = new Date(); 
		this.createdBy ="APP"; 
		this.creatorIPAddress="127.0.0.1"; 
		
	}



	public Date getLastChangePassword() {
		return lastChangePassword;
	}

	public void setLastChangePassword(Date lastChangePassword) {
		this.lastChangePassword = lastChangePassword;
	}

	public Branch getBranch() {
		return branch;
	}



	public void setBranch(Branch branch) {
		this.branch = branch;
	}



	/**
	* id<br/>
	* column :USER_ID
	**/
	public void setId(Long id){
	  this.id=id;
	}
	/**
	* id<br/>
	* column :USER_ID
	**/
	public Long getId(){
	    return this.id;
	}
	/**
	* kode user<br/>
	* column :USER_CODE
	**/
	public void setUserCode(String userCode){
	  this.userCode=userCode;
	}
	/**
	* kode user<br/>
	* column :USER_CODE
	**/
	public String getUserCode(){
	    return this.userCode;
	}
	/**
	* tanggal expire<br/>
	* column :EXPIRED_DATE
	**/
	public void setExpiredDate(Date  expiredDate){
	  this.expiredDate=expiredDate;
	}
	/**
	* tanggal expire<br/>
	* column :EXPIRED_DATE
	**/
	public Date getExpiredDate(){
	    return this.expiredDate;
	}
	/**
	* tanggal lahir<br/>
	* column :BIRTHDATE
	**/
	public void setBirthDate(Date  birthDate){
	  this.birthDate=birthDate;
	}
	/**
	* tanggal lahir<br/>
	* column :BIRTHDATE
	**/
	public Date getBirthDate(){
	    return this.birthDate;
	}
	/**
	 * flag super admin atau bukan
	 **/
	@Column(name="is_super_admin" , length=1)		
	private String superAdmin; 
	
	
	
	/**
	* email<br/>
	* column :EMAIL
	**/
	public void setEmail(String email){
	  this.email=email;
	}
	/**
	* email<br/>
	* column :EMAIL
	**/
	public String getEmail(){
	    return this.email;
	}
	
	
	/**
	* failed login attemps<br/>
	* column :FAILED_LOGIN_ATTEMPTS
	**/
	public void setFailedLoginAttempts(Integer failedLoginAttempts){
	  this.failedLoginAttempts=failedLoginAttempts;
	}
	/**
	* failed login attemps<br/>
	* column :FAILED_LOGIN_ATTEMPTS
	**/
	public Integer getFailedLoginAttempts(){
	    return this.failedLoginAttempts;
	}
	/**
	* real name<br/>
	* column :REAL_NAME
	**/
	public void setRealName(String realName){
	  this.realName=realName;
	}
	/**
	* real name<br/>
	* column :REAL_NAME
	**/
	public String getRealName(){
	    return this.realName;
	}
	/**
	* status<br/>
	* column :STATUS
	**/
	public void setStatus(String status){
	  this.status=status;
	}
	/**
	* status<br/>
	* column :STATUS
	**/
	public String getStatus(){
	    return this.status;
	}
	/**
	* locale<br/>
	* column :LOCALE
	**/
	public void setLocale(String locale){
	  this.locale=locale;
	}
	/**
	* locale<br/>
	* column :LOCALE
	**/
	public String getLocale(){
	    return this.locale;
	}
	/**
	* default application id<br/>
	* column :DEFAULT_APPLICATION_ID
	**/
	public void setDefaultApplicationId(Integer defaultApplicationId){
	  this.defaultApplicationId=defaultApplicationId;
	}
	/**
	* default application id<br/>
	* column :DEFAULT_APPLICATION_ID
	**/
	public Integer getDefaultApplicationId(){
	    return this.defaultApplicationId;
	}
	
	
	/**
	 * Reference object UserPassword
	 * @return password
	 */
	/*public UserPassword getPassword() {
		return password;
	}*/
	/**
	 * Reference object UserPassword
	 * @param password
	 */
	/*public void setPassword(UserPassword password) {
		this.password = password;
	}*/
	
	/**
	 * add by dode
	 * default application
	 * column :DEFAULT_APPLICATION_ID
	 */
	public void setDefaultApplication(Application defaultApplication) {
		this.defaultApplication = defaultApplication;
	}
	
	/**
	 * add by dode
	 * default application
	 * column :DEFAULT_APPLICATION_ID
	 */
	public Application getDefaultApplication() {
		return defaultApplication;
	}
	
	/**
	 * password<br/>
	 * column :CHIPPER_TEXT
	 */
	public String getChipperText() {
		return chipperText;
	}
	/**
	 * password<br/>
	 * column :CHIPPER_TEXT
	 */
	public void setChipperText(String chipperText) {
		this.chipperText = chipperText;
	}
	
	
	
	 
	
	/**
	 * kode cabang default user, dia berada di mana <br/>
	 * column : default_branch_code
	 */
	public void setDefaultBranchCode(String defaultBranchCode) {
		this.defaultBranchCode = defaultBranchCode;
	}
	/**
	 * kode cabang default user, dia berada di mana <br/>
	 * column : default_branch_code
	 */
	public String getDefaultBranchCode() {
		return defaultBranchCode;
	}
	@Override
	public String[] retrieveModifableFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * column : column :ACTIVE_STATUS
	 */
	@Override
	public void setActiveFlag(String activeFlag) {
		this.activeFlag  = activeFlag ; 
		
	}
	@Override
	public String getActiveFlag() {
		return activeFlag;
	}
	@Override
	public Long getPrimaryKey() {
		return id;
	}
	@Override
	public Class<Long> getPrimaryKeyClassType() {
		return Long.class;
	}
	@Override
	public String getKey1AsString() {
		return userCode;
	}
	@Override
	public String getKey2AsString() {
		return null;
	}
	@Override
	public boolean isEraseDataOnApproveErase() {
		return true;
	}
	@Override
	public String getPrimaryKeyJPAName() {
		return "id";
	}
	
	/**
	 * flag super admin atau bukan
	 **/
	public String getSuperAdmin() {
		return superAdmin;		
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(userCode, user.userCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userCode);
	}

	/**
	 * flag super admin atau bukan
	 **/
	public void setSuperAdmin(String superAdmin) {
		this.superAdmin = superAdmin;
	}
	
	
	@Override
	protected void extractDataFromJSON(User targetObject,
			ParsedJSONContainer jsonContainer) {
		targetObject.setActiveFlag(  (String)jsonContainer.get("activeFlag" ,  String.class.getName()));
		targetObject.setBirthDate( (Date)jsonContainer.get("birthDate" ,  Date.class.getName()));
		targetObject.setChipperText( (String)jsonContainer.get("chipperText" ,  String.class.getName()));
		targetObject.setDefaultApplication( (Application)jsonContainer.get("defaultApplication" ,  Application.class.getName()));
		targetObject.setDefaultApplicationId( (Integer)jsonContainer.get("defaultApplicationId" ,  Integer.class.getName()));
		targetObject.setEmail( (String)jsonContainer.get("email" ,  String.class.getName()));
		targetObject.setExpiredDate( (Date)jsonContainer.get("expiredDate" ,  Date.class.getName()));
		targetObject.setFailedLoginAttempts( (Integer)jsonContainer.get("failedLoginAttempts" ,  Integer.class.getName()));
		targetObject.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		targetObject.setLocale( (String)jsonContainer.get("locale" ,  String.class.getName()));
		targetObject.setRealName( (String)jsonContainer.get("realName" ,  String.class.getName()));
		targetObject.setStatus( (String)jsonContainer.get("status" ,  String.class.getName()));
		targetObject.setUserCode( (String)jsonContainer.get("userCode" ,  String.class.getName()));
		targetObject.setDefaultBranchCode(jsonContainer.getAsString("defaultBranchCode")); 
		
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		super.translateToJSON(jsonContainerData);
		jsonContainerData.put("id", id);
		jsonContainerData.put("userCode", userCode);
		jsonContainerData.put("expiredDate", expiredDate);
		jsonContainerData.put("birthDate", birthDate);
		jsonContainerData.put("email", email);
		jsonContainerData.put("failedLoginAttempts", failedLoginAttempts);
		jsonContainerData.put("realName", realName);
		jsonContainerData.put("status", status);
		jsonContainerData.put("locale", locale);
		jsonContainerData.put("defaultApplicationId", defaultApplicationId);
		jsonContainerData.put("defaultApplication", defaultApplication);
		jsonContainerData.put("chipperText", chipperText);
		jsonContainerData.put("defaultBranchCode", defaultBranchCode);
	}



	@Override
	public String toString() {
		return realName;
	}
}