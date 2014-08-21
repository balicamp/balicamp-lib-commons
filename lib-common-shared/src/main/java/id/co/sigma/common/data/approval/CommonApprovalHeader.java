package id.co.sigma.common.data.approval;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *table header common approval. ini untuk penyeragaman approval
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@Entity
@Table(name="ct_common_approval")
public class CommonApprovalHeader implements Serializable , IJSONFriendlyObject<CommonApprovalHeader>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7785515848590336801L;

	/**
	* Primary key dari data<br/>
	* column :pk
	**/
	@Id
	@Column(name="pk", nullable=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	 
	/**
	* fqcn class POJO data<br/>
	* column :target_fqcn
	**/
	@Column(name="target_fqcn", nullable=true)
	private String targteObjectFQCN;
	 
	
	/**
	 * column : reff_no
	 * reference number dari data. ini untuk kemudahan mencari data
	 */
	@Column(name="reff_no" , length=16)
	private String referenceNumber ; 
	/**
	* primary key dari object, dalam notasi json untuk komposite key. di saran kan single<br/>
	* column :target_pk_asjson
	**/
	@Column(name="target_pk_asjson", nullable=true)
	private String targetObjectIdAsJSON;
	 
	/**
	* ID user yang request approval<br/>
	* column :requester_username
	**/
	@Column(name="requester_username", nullable=true)
	private String requestorUserName;
	 
	/**
	* date + time kapan data di request<br/>
	* column :request_time
	**/
	@Column(name="request_time", nullable=true)
	@Temporal( TemporalType.TIMESTAMP)
	private Date requestTime;
	 
	/**
	* catatan dalam request<br/>
	* column :approval_req_remark
	**/
	@Column(name="approval_req_remark", nullable=true)
	private String requestRemark;
	 
	/**
	* user yang approve data(kalau sudah di approve<br/>
	* column :approved_by_username
	**/
	@Column(name="approved_by_username", nullable=true)
	private String approverUserName;
	 
	/**
	* date + time data di approve<br/>
	* column :approved_time
	**/
	@Column(name="approved_time", nullable=true)
	@Temporal( TemporalType.TIMESTAMP)
	private Date approvedTime;
	 
	/**
	* ikut enum : id.co.sigma.common.data.approval.SimpleApprovalStatus<br/>
	* column :approval_status
	**/
	@Column(name="approval_status", nullable=true)
	private String approvalStatusCode;
	
	
	
	
	/**
	 * approval status code
	 **/
	@Transient
	private SimpleApprovalStatus approvalStatus  ; 
	 
	/**
	* catatan pada saat approval/reject<br/>
	* column :approval_remark
	**/
	@Column(name="approval_remark", nullable=true)
	private String approvalRemark;
	
	/**
	 * approval definition
	 * column : target_fqcn
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="target_fqcn", insertable=false, updatable=false)
	private CommonApprovalDefinition approvalDefinition;
	
	/**
	 * approval definition
	 * column : target_fqcn
	 */
	public CommonApprovalDefinition getApprovalDefinition() {
		return approvalDefinition;
	}
	
	/**
	 * approval definition
	 * column : target_fqcn
	 */
	public void setApprovalDefinition(
			CommonApprovalDefinition approvalDefinition) {
		this.approvalDefinition = approvalDefinition;
	}

	/**
	* Primary key dari data<br/>
	* column :pk
	**/
	public void setId(Long id){
	  this.id=id;
	}
	 
	/**
	* Primary key dari data<br/>
	* column :pk
	**/
	public Long getId(){
	    return this.id;
	}
	 
	/**
	* fqcn class POJO data<br/>
	* column :target_fqcn
	**/
	public void setTargteObjectFQCN(String targteObjectFQCN){
	  this.targteObjectFQCN=targteObjectFQCN;
	}
	 
	/**
	* fqcn class POJO data<br/>
	* column :target_fqcn
	**/
	public String getTargteObjectFQCN(){
	    return this.targteObjectFQCN;
	}
	 
	/**
	* primary key dari object, dalam notasi json untuk komposite key. di saran kan single<br/>
	* column :target_pk_asjson
	**/
	public void setTargetObjectIdAsJSON(String targetObjectIdAsJSON){
	  this.targetObjectIdAsJSON=targetObjectIdAsJSON;
	}
	 
	/**
	* primary key dari object, dalam notasi json untuk komposite key. di saran kan single<br/>
	* column :target_pk_asjson
	**/
	public String getTargetObjectIdAsJSON(){
	    return this.targetObjectIdAsJSON;
	}
	 
	/**
	* ID user yang request approval<br/>
	* column :requester_username
	**/
	public void setRequestorUserName(String requestorUserName){
	  this.requestorUserName=requestorUserName;
	}
	 
	/**
	* ID user yang request approval<br/>
	* column :requester_username
	**/
	public String getRequestorUserName(){
	    return this.requestorUserName;
	}
	 
	/**
	* date + time kapan data di request<br/>
	* column :request_time
	**/
	public void setRequestTime(Date requestTime){
	  this.requestTime=requestTime;
	}
	 
	/**
	* date + time kapan data di request<br/>
	* column :request_time
	**/
	public Date getRequestTime(){
	    return this.requestTime;
	}
	 
	/**
	* catatan dalam request<br/>
	* column :approval_req_remark
	**/
	public void setRequestRemark(String requestRemark){
	  this.requestRemark=requestRemark;
	}
	 
	/**
	* catatan dalam request<br/>
	* column :approval_req_remark
	**/
	public String getRequestRemark(){
	    return this.requestRemark;
	}
	 
	/**
	* user yang approve data(kalau sudah di approve<br/>
	* column :approved_by_username
	**/
	public void setApproverUserName(String approverUserName){
	  this.approverUserName=approverUserName;
	}
	 
	/**
	* user yang approve data(kalau sudah di approve<br/>
	* column :approved_by_username
	**/
	public String getApproverUserName(){
	    return this.approverUserName;
	}
	 
	/**
	* date + time data di approve<br/>
	* column :approved_time
	**/
	public void setApprovedTime(Date approvedTime){
	  this.approvedTime=approvedTime;
	}
	 
	/**
	* date + time data di approve<br/>
	* column :approved_time
	**/
	public Date getApprovedTime(){
	    return this.approvedTime;
	}
	 
	/**
	* ikut enum : {@link id.co.sigma.common.data.approval.SimpleApprovalStatus}<br/>
	* column :approval_status
	**/
	public void setApprovalStatusCode(String approvalStatusCode){
	  this.approvalStatusCode=approvalStatusCode;
	}
	 
	/**
	* ikut enum : {@link id.co.sigma.common.data.approval.SimpleApprovalStatus}<br/>
	* column :approval_status
	**/
	public String getApprovalStatusCode(){
	    return this.approvalStatusCode;
	}
	 
	/**
	* catatan pada saat approval/reject<br/>
	* column :approval_remark
	**/
	public void setApprovalRemark(String approvalRemark){
	  this.approvalRemark=approvalRemark;
	}
	 
	/**
	* catatan pada saat approval/reject<br/>
	* column :approval_remark
	**/
	public String getApprovalRemark(){
	    return this.approvalRemark;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("approvalRemark",getApprovalRemark());
		jsonContainer.put("approvalStatusCode",getApprovalStatusCode());
		jsonContainer.put("approvedTime",getApprovedTime());
		jsonContainer.put("approverUserName",getApproverUserName());
		jsonContainer.put("id",getId());
		jsonContainer.put("requestRemark",getRequestRemark());
		jsonContainer.put("requestTime",getRequestTime());
		jsonContainer.put("requestorUserName",getRequestorUserName());
		jsonContainer.put("targetObjectIdAsJSON",getTargetObjectIdAsJSON());
		jsonContainer.put("targteObjectFQCN",getTargteObjectFQCN());
		jsonContainer.put("approvalDefinition", getApprovalDefinition());
		jsonContainer.put("referenceNumber", referenceNumber);
	}
	@Override
	public CommonApprovalHeader instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		CommonApprovalHeader retval = new CommonApprovalHeader();
		retval.setApprovalRemark( (String)jsonContainer.get("approvalRemark" ,  String.class.getName()));
		retval.setApprovalStatusCode( (String)jsonContainer.get("approvalStatusCode" ,  String.class.getName()));
		retval.setApprovedTime( (Date)jsonContainer.get("approvedTime" ,  Date.class.getName()));
		retval.setApproverUserName( (String)jsonContainer.get("approverUserName" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setRequestRemark( (String)jsonContainer.get("requestRemark" ,  String.class.getName()));
		retval.setRequestTime( (Date)jsonContainer.get("requestTime" ,  Date.class.getName()));
		retval.setRequestorUserName( (String)jsonContainer.get("requestorUserName" ,  String.class.getName()));
		retval.setTargetObjectIdAsJSON( (String)jsonContainer.get("targetObjectIdAsJSON" ,  String.class.getName()));
		retval.setTargteObjectFQCN( (String)jsonContainer.get("targteObjectFQCN" ,  String.class.getName()));
		retval.approvalStatus = SimpleApprovalStatus.instantiateFromString(retval.getApprovalStatusCode());
		retval.approvalDefinition = jsonContainer.get("approvalDefinition", CommonApprovalDefinition.class.getName());
		retval.referenceNumber = jsonContainer.getAsString("referenceNumber");
		return retval; 
	}
	
	/**
	 * approval status code
	 **/
	public SimpleApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}
	/**
	 * approval status code
	 **/
	public void setApprovalStatus(SimpleApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
		this.approvalStatusCode = approvalStatus.toString();
	}
	
	
	@PostLoad
	protected void postLoad () {
		this.approvalStatus = SimpleApprovalStatus.instantiateFromString(this.approvalStatusCode);
	}

	/**
	 * column : reff_no
	 * reference number dari data. ini untuk kemudahan mencari data
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	/**
	 * column : reff_no
	 * reference number dari data. ini untuk kemudahan mencari data
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}
}
