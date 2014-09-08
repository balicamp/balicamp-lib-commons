package id.co.sigma.common.security.domain;

import id.co.sigma.common.data.app.SimpleDualControlData;
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

/**
 * table : sec_user
 * data user tanpa password. data user mungkin perlu di passed ke client, tanpa menyertakan password user
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@Entity
@Table(name="sec_user")
public class UserWithoutPassword  extends  SimpleDualControlData<UserWithoutPassword>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6224169371354095099L;
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
	
	
		
	/**
	 * add by dode
	 * default application
	 * column :DEFAULT_APPLICATION_ID
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="default_app_id", insertable=false, updatable=false)
	private Application defaultApplication;
	
	
	
	
	
	
	
	/**
	 * kode cabang default user, dia berada di mana <br/>
	 * column : default_branch_code
	 */
	@Column(name="default_branch_code", length=16)
	private String defaultBranchCode ;
	
	
	/**
	 * flag super admin atau bukan
	 **/
	@Column(name="is_super_admin" , length=1)		
	private String superAdmin; 
	
	public UserWithoutPassword() {
	}
	
	
	
	public UserWithoutPassword(String userCode, String email, String realName, String locale) {
		
		this.userCode = userCode;
		this.email = email;
		this.realName = realName;
		this.locale = locale;
		this.activeFlag="A"; 
		this.defaultApplicationId = 1 ; 
		setCreatedOn( new Date()); 
		setCreatedBy(   "APP"); 
		setCreatorIPAddress(  "127.0.0.1"); 
		
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
	
	
	/**
	 * flag super admin atau bukan
	 **/
	public String getSuperAdmin() {
		return superAdmin;		
	}
	
	/**
	 * flag super admin atau bukan
	 **/
	public void setSuperAdmin(String superAdmin) {
		this.superAdmin = superAdmin;
	}
	

	
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		
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
		jsonContainerData.put("defaultBranchCode", defaultBranchCode);
	}
	
	
	/**
	* active status<br/>
	* column :ACTIVE_STATUS
	**/
	public String getActiveFlag() {
		return activeFlag;
	}
	/**
	* active status<br/>
	* column :ACTIVE_STATUS
	**/
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}



	@Override
	public String[] retrieveModifableFields() {
		// TODO Auto-generated method stub
		return null;
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
		return id== null ? "" :  id +"";
	}



	@Override
	public boolean isEraseDataOnApproveErase() {
		return true;
	}



	@Override
	public String getPrimaryKeyJPAName() {
		return "id";
	}



	@Override
	protected void extractDataFromJSON(UserWithoutPassword targetObject,
			ParsedJSONContainer jsonContainer) {
		targetObject.activeFlag =  (String)jsonContainer.get("activeFlag" ,  String.class.getName());
		targetObject.setBirthDate( (Date)jsonContainer.get("birthDate" ,  Date.class.getName()));
		
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
}
