package id.co.sigma.common.data.app;

import id.co.sigma.common.util.ObjectGeneratorManager;
import id.co.sigma.common.util.json.ParsedJSONContainer;
import id.co.sigma.common.util.json.SharedServerClientLogicManager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;




/**
 * base class untuk simple data yang dual control
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
@MappedSuperclass
public abstract class SimpleDualControlData<POJO extends SimpleDualControlData<?>>   implements DualControlEnabledData<POJO, Long>{
	
	
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1870894998250762309L;




	/**
	 * kode approval status. ini untuk proses persistence
	 **/
	@Column(name="approval_status" , length=32)
	protected String approvalStatusCode ; 
	
	
	
	
	/**
	 * ID dari flow dual control
	 * column : curr_dual_ctr_id
	 **/
	@Column(name="curr_dual_ctr_id"  )
	protected Long currentCommonDualControlId ; 
	
	
	/**
	 * kode status data. A vs D. A= active, D = deactive
	 * column : data_status
	 **/
	@Column(name="data_status", length=1)
	protected String dataStatusCode ; 

	/**
	 * column : created_time, kapan data di create oleh user
	 **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	protected Date createdOn ; 
	
	
	
	/**
	 * column : creator_name username creator data
	 **/
	@Column(name="creator_name", length=32)
	protected String createdBy ; 
	
	
	
	/**
	 * column : ip address dari user creator. cover IP v 6
	 **/
	@Column(name="creator_ip" , length=19)
	protected String creatorIPAddress ;
	
	
	
	/**
	 * column : CREATED_ON
	 * timestamp modifikasi data
	 **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_modified_time")
	protected Date modifiedOn ;
	
	
	/**
	 * audit trail, modifier user id
	 * column : MODIFIED_BY
	 * 
	 **/
	@Column(name="last_modified_by", length=32)
	protected String modifiedBy; 
	
	

	/**
	 * last modified by IP address. modifikasi terakhir dari IP mana
	 * column : last_modified_ip
	 **/
	@Column(name="last_modified_ip" , length=15)
	protected String modifiedByIPAddress ; 

	@Transient
	protected DualControlApprovalStatusCode approvalStatusEnum  ; 
	
	
	
	/**
	 * column : created_time, kapan data di create oleh user
	 **/
	@Override
	public void setCreatedOn(Date createTime) {
		
		this.createdOn = createTime ; 
	}
	/**
	 * column : created_time, kapan data di create oleh user
	 **/
	public Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * column : creator_name username creator data
	 **/
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * column : creator_name username creator data
	 **/
	public void setCreatedBy(String creatorUserName) {
		this.createdBy = creatorUserName;
	}
	/**
	 * column : ip address dari user creator. cover IP v 6
	 **/
	public String getCreatorIPAddress() {
		return creatorIPAddress;
	}
	/**
	 * column : ip address dari user creator. cover IP v 6
	 **/
	public void setCreatorIPAddress(String creatorIPAddress) {
		this.creatorIPAddress = creatorIPAddress;
	}
	
	
	
	/**
	 * column : CREATED_ON
	 * timestamp modifikasi data
	 **/
	
	public Date getModifiedOn() {
		return modifiedOn;
	}

	 
	
	/**
	 * audit trail, modifier user id
	 * column : MODIFIED_BY
	 * 
	 **/
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	/**
	 * last modified by IP address. modifikasi terakhir dari IP mana
	 * column : last_modified_ip
	 **/
	public String getModifiedByIPAddress() {
		return modifiedByIPAddress;
	}
	
	
	/**
	 * last modified by IP address. modifikasi terakhir dari IP mana
	 * column : last_modified_ip
	 **/
	@Override
	public void setModifiedByIPAddress(String modificatorIPAddress) {
		this.modifiedByIPAddress = modificatorIPAddress;
	}
	 

	@Override
	public void setModifiedOn(Date mdofTime) {
		this.modifiedOn = mdofTime ; 
		
	}
	
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	
	
	
	@PostLoad
	protected void postLoadTask (){
		if ( approvalStatusCode!=null){
			for (DualControlApprovalStatusCode scn : DualControlApprovalStatusCode.values() ){
				if ( scn.toString().equals(approvalStatusCode) ){
					approvalStatusEnum = scn; 
					break ; 
				}
			}
		}
	}
	
	@Override
	public void setApprovalStatus(DualControlApprovalStatusCode approvalStatusRaw) {
		this.approvalStatusEnum = approvalStatusRaw ; 
		if ( approvalStatusRaw!= null)
			this.approvalStatusCode = approvalStatusRaw.toString();
		else
			this.approvalStatusCode  = null ; 
	}
	
	
	@Override
	public DualControlApprovalStatusCode getApprovalStatus() {
		return approvalStatusEnum;
	}
	
	/**
	 * ID dari flow dual control
	 * column : curr_dual_ctr_id
	 **/
	public Long getCurrentCommonDualControlId() {
		return currentCommonDualControlId;
	}
	/**
	 * ID dari flow dual control
	 * column : curr_dual_ctr_id
	 **/
	public void setCurrentCommonDualControlId(Long currentCommonDualControlId) {
		this.currentCommonDualControlId = currentCommonDualControlId;
	}


	/**
	 * kode status data. A vs D. A= active, D = deactive
	 * column : data_status
	 **/
	public void setDataStatusCode(String dataStatusCode) {
		this.dataStatusCode = dataStatusCode;
	}
	/**
	 * kode status data. A vs D. A= active, D = deactive
	 * column : data_status
	 **/
	public String getDataStatusCode() {
		return dataStatusCode;
	}
	
	
	
	public void setApprovalStatusCodeRaw(String approvalStatusCode) {
		this.approvalStatusCode = approvalStatusCode;
		this.approvalStatusEnum = DualControlApprovalStatusCode.generateFromRawString(approvalStatusCode); 
	}
	
	public String getApprovalStatusCodeRaw() {
		return approvalStatusCode;
	}
	
	/**
	 * generate big int. null safe
	 **/
	protected BigInteger makeBigInteger (Number number) {
		if ( number ==null)
			return null ; 
		return new BigInteger(number.intValue() +""); 
	}
	
	
	/**
	 * konstruksi big decimal. null safe method
	 **/
	protected BigDecimal makeBigDecimal (Number number) {
		if ( number ==null)
			return null ; 
		return new BigDecimal(number.toString()); 
	}
	
	
	
	
	@Transient
	private Double nullDouble = null ; 
	
	
	
	
	
	
	
	/**
	 * helper untuk menaruh object dalam json. null save
	 **/
	protected void putNumberOnJSONContainer (ParsedJSONContainer container , String key , Number value) {
		if ( value==null){
			container.put(key, nullDouble);
		}else{
			container.put(key, value.doubleValue());
		}
		
	}
	 
	@Override
	public String getApprovalStatusJPAAnnotatedField() {
		return "approvalStatusCode";
	}
	
	@Override
	public String getActiveFlagJPAAnnotatedField() {
		return "dataStatusCode";
	}
	
	
	@Override
	public final  String generateJSONString() {
		ParsedJSONContainer c = SharedServerClientLogicManager.getInstance().getJSONParser().createBlankObject(); 
		translateToJSON(c);
		return c.getJSONString();
	}
	
	 
	
	
	@Override
	public final POJO instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		POJO targetObject = ObjectGeneratorManager.getInstance().instantiateSampleObject(this.getClass().getName());
		targetObject.approvalStatusCode = jsonContainer.getAsString("approvalStatusCode");
		targetObject.approvalStatusEnum = DualControlApprovalStatusCode.generateFromRawString(targetObject.approvalStatusCode);
		targetObject.dataStatusCode = jsonContainer.getAsString("dataStatusCode");
		targetObject.currentCommonDualControlId = (Long)jsonContainer.get("currentCommonDualControlId" , Long.class.getName());
		
		targetObject.createdBy =   jsonContainer.getAsString( "getAsString" ) ;
		targetObject.createdOn =  jsonContainer.getAsDate("createdOn" ); 
		targetObject.creatorIPAddress =  jsonContainer.getAsString( "creatorIPAddress" );
		targetObject.modifiedBy =   jsonContainer.getAsString( "modifiedBy" ) ;
		targetObject.modifiedByIPAddress =  jsonContainer.getAsString("modifiedByIPAddress" ); 
		targetObject.modifiedOn =  jsonContainer.getAsDate( "modifiedOn" ); 
		
		extractDataFromJSON(targetObject, jsonContainer);
		
		return targetObject;
	}
	
	
	
	
	/**
	 * salin data dari json container
	 **/
	protected abstract void extractDataFromJSON (POJO  targetObject , ParsedJSONContainer jsonContainer); 
	 
	
	
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("approvalStatusCode", approvalStatusCode);
		jsonContainerData.put("dataStatusCode", dataStatusCode); 
		jsonContainerData.put("currentCommonDualControlId", currentCommonDualControlId);
		jsonContainerData.put("createdBy", createdBy);
		jsonContainerData.put("createdOn", createdOn);
		jsonContainerData.put("creatorIPAddress", creatorIPAddress);
		jsonContainerData.put("modifiedBy", modifiedBy);
		jsonContainerData.put("modifiedByIPAddress", modifiedByIPAddress); 
		jsonContainerData.put("modifiedOn", modifiedOn);
		
	}
	
	
}
