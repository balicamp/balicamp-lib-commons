package id.co.sigma.common.data.app;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




/**
 * container table dual control
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
@Entity
@Table(name="m_dual_control_table")
public class CommonDualControlContainerTable implements Serializable, IJSONFriendlyObject<CommonDualControlContainerTable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6613741193170816007L;
	
	
	
	/**
	* primary key data<br/>
	* column :pk
	**/
	@Id
	@Column(name="pk", nullable=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	 
	/**
	* CREATE,UPDATE,DELETE<br/>
	* column :operation_code
	**/
	@Column(name="operation_code", nullable=true)
	protected String operationCode;
	 
	/**
	* full qualified class name dari data. ini untuk POJO dari object<br/>
	* column :target_fqcn
	**/
	@Column(name="target_fqcn", nullable=true)
	protected String targetObjectFQCN;
	 
	/**
	* approval status<br/>
	* column :approval_status
	**/
	@Column(name="approval_status", nullable=true)
	protected String approvalStatus;
	 
	/**
	* user creator<br/>
	* column :creator_user_id
	**/
	@Column(name="creator_user_id", nullable=true)
	protected String creatorUserId;
	 
	/**
	* timestamp create<br/>
	* column :created_time
	**/
	@Column(name="created_time", nullable=true)
	protected Date createdTime;
	 
	/**
	* user approver<br/>
	* column :approver_user_id
	**/
	@Column(name="approver_user_id", nullable=true)
	protected String approverUserId;
	 
	/**
	* user yang approve<br/>
	* column :approved_time
	**/
	@Column(name="approved_time", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date approvedTime;
	 
	/**
	* representasi data dalam json<br/>
	* column :object_json_data
	**/
	@Lob
	@Column(name="object_json_data", nullable=true)
	protected String jsonData;
	 
	/**
	* general purpose key1. user defined<br/>
	* column :key_1
	**/
	@Column(name="key_1", nullable=true)
	protected String key1;
	 
	/**
	* general purpose ke2. untuk indexing<br/>
	* column :key_2
	**/
	@Column(name="key_2", nullable=true)
	protected String key2;
	 
	
	/**
	 * column : latest_remark
	 * catatan terakhir dari data, ini dalam kasus reject dsb, revise
	 **/
	@Column(name="latest_remark" , length=256)
	protected String latestRemark ; 
	
	
	/**
	 * catatan dari approver user
	 * column : appr_remark
	 */
	@Column(name="appr_remark" , length=512) 
	private String approvalRemark ; 
	
	/**
	 * flag data multiple line atau tidak. ini bukan berarti 1 data. tp dari proses bulk atau dari proses normal
	 * column : single_data_flag
	 */
	@Column(name="single_data_flag" , length=1 , nullable=false, updatable=false)
	protected String singleLineDataTypeFlag = "Y";
	
	
	/**
	 * reff no dari data. column : reff_no
	 */
	@Column(name="reff_no" , length=32 )
	private String reffNo ; 
	
	
	
	/**
	 * nama file(kalau proses melalui upload)<br/>
	 * column : file_name
	 */
	@Column(name="file_name" , length=128)
	private String fileName ; 
	
	
	
	/**
	 * berapa line dalam data
	 */
	@Column(name="line_count" , precision=5 , scale=0)
	private Integer lineCount  =1 ;
	
	
	
	

	/**
	* primary key data<br/>
	* column :pk
	**/
	public void setId(Long id){
	  this.id=id;
	}
	 
	/**
	* primary key data<br/>
	* column :pk
	**/
	public Long getId(){
	    return this.id;
	}
	 
	/**
	* CREATE,UPDATE,DELETE<br/>
	* column :operation_code
	**/
	public void setOperationCode(String operationCode){
	  this.operationCode=operationCode;
	}
	 
	/**
	* CREATE,UPDATE,DELETE<br/>
	* column :operation_code
	**/
	public String getOperationCode(){
	    return this.operationCode;
	}
	 
	/**
	* full qualified class name dari data. ini untuk POJO dari object<br/>
	* column :target_fqcn
	**/
	public void setTargetObjectFQCN(String targetObjectFQCN){
	  this.targetObjectFQCN=targetObjectFQCN;
	}
	 
	/**
	* full qualified class name dari data. ini untuk POJO dari object<br/>
	* column :target_fqcn
	**/
	public String getTargetObjectFQCN(){
	    return this.targetObjectFQCN;
	}
	 
	/**
	* approval status<br/>
	* column :approval_status
	**/
	public void setApprovalStatus(String approvalStatus){
	  this.approvalStatus=approvalStatus;
	}
	 
	/**
	* approval status<br/>
	* column :approval_status
	**/
	public String getApprovalStatus(){
	    return this.approvalStatus;
	}
	 
	/**
	* user creator<br/>
	* column :creator_user_id
	**/
	public void setCreatorUserId(String creatorUserId){
	  this.creatorUserId=creatorUserId;
	}
	 
	/**
	* user creator<br/>
	* column :creator_user_id
	**/
	public String getCreatorUserId(){
	    return this.creatorUserId;
	}
	 
	/**
	* timestamp create<br/>
	* column :created_time
	**/
	public void setCreatedTime(Date createdTime){
	  this.createdTime=createdTime;
	}
	 
	/**
	* timestamp create<br/>
	* column :created_time
	**/
	public Date getCreatedTime(){
	    return this.createdTime;
	}
	 
	/**
	* user approver<br/>
	* column :approver_user_id
	**/
	public void setApproverUserId(String approverUserId){
	  this.approverUserId=approverUserId;
	}
	 
	/**
	* user approver<br/>
	* column :approver_user_id
	**/
	public String getApproverUserId(){
	    return this.approverUserId;
	}
	 
	/**
	* user yang approve<br/>
	* column :approved_time
	**/
	public void setApprovedTime(Date approvedTime){
	  this.approvedTime=approvedTime;
	}
	 
	/**
	* user yang approve<br/>
	* column :approved_time
	**/
	public Date getApprovedTime(){
	    return this.approvedTime;
	}
	 
	/**
	* representasi data dalam json<br/>
	* column :object_json_data
	**/
	public void setJsonData(String jsonData){
	  this.jsonData=jsonData;
	}
	 
	/**
	* representasi data dalam json<br/>
	* column :object_json_data
	**/
	public String getJsonData(){
	    return this.jsonData;
	}
	 
	/**
	* general purpose key1. user defined<br/>
	* column :key_1
	**/
	public void setKey1(String key1){
	  this.key1=key1;
	}
	 
	/**
	* general purpose key1. user defined<br/>
	* column :key_1
	**/
	public String getKey1(){
	    return this.key1;
	}
	 
	/**
	* general purpose ke2. untuk indexing<br/>
	* column :key_2
	**/
	public void setKey2(String key2){
	  this.key2=key2;
	}
	 
	/**
	* general purpose ke2. untuk indexing<br/>
	* column :key_2
	**/
	public String getKey2(){
	    return this.key2;
	}
	 
	/**
	 * column : latest_remark
	 * catatan terakhir dari data, ini dalam kasus reject dsb, revise
	 **/
	public void setLatestRemark(String latestRemark) {
		this.latestRemark = latestRemark;
	}
	/**
	 * column : latest_remark
	 * catatan terakhir dari data, ini dalam kasus reject dsb, revise
	 **/
	public String getLatestRemark() {
		return latestRemark;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("approvalStatus",getApprovalStatus());
		jsonContainer.put("approvedTime",getApprovedTime());
		jsonContainer.put("approverUserId",getApproverUserId());
		jsonContainer.put("createdTime",getCreatedTime());
		jsonContainer.put("creatorUserId",getCreatorUserId());
		jsonContainer.put("id",getId());
		jsonContainer.put("jsonData",getJsonData());
		jsonContainer.put("key1",getKey1());
		jsonContainer.put("key2",getKey2());
		jsonContainer.put("latestRemark",getLatestRemark());
		jsonContainer.put("operationCode",getOperationCode());
		jsonContainer.put("targetObjectFQCN",getTargetObjectFQCN());
		jsonContainer.put("singleLineDataTypeFlag", this.singleLineDataTypeFlag);
		jsonContainer.put("reffNo", reffNo);
		jsonContainer.put("fileName", fileName); 
		jsonContainer.put("lineCount", lineCount); 
		jsonContainer.put("approvalRemark", approvalRemark);
	}
	
	@Override
	public CommonDualControlContainerTable instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		CommonDualControlContainerTable retval = new CommonDualControlContainerTable();
		retval.setApprovalStatus( (String)jsonContainer.get("approvalStatus" ,  String.class.getName()));
		retval.setApprovedTime( (Date)jsonContainer.get("approvedTime" ,  Date.class.getName()));
		retval.setApproverUserId( (String)jsonContainer.get("approverUserId" ,  String.class.getName()));
		retval.setCreatedTime( (Date)jsonContainer.get("createdTime" ,  Date.class.getName()));
		retval.setCreatorUserId( (String)jsonContainer.get("creatorUserId" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setJsonData( (String)jsonContainer.get("jsonData" ,  String.class.getName()));
		retval.setKey1( (String)jsonContainer.get("key1" ,  String.class.getName()));
		retval.setKey2( (String)jsonContainer.get("key2" ,  String.class.getName()));
		retval.setLatestRemark( (String)jsonContainer.get("latestRemark" ,  String.class.getName()));
		retval.setOperationCode( (String)jsonContainer.get("operationCode" ,  String.class.getName()));
		retval.setTargetObjectFQCN( (String)jsonContainer.get("targetObjectFQCN" ,  String.class.getName()));
		retval.singleLineDataTypeFlag = jsonContainer.getAsString("singleLineDataTypeFlag") ; 
		retval.reffNo  = jsonContainer.getAsString("reffNo") ;
		retval.fileName = jsonContainer.getAsString("fileName"); 
		retval.lineCount = jsonContainer.getAsInteger( "lineCount") ;
		retval.approvalRemark = jsonContainer.getAsString("approvalRemark") ; 
		return retval; 
	}
	/**
	 * flag data multiple line atau tidak. ini bukan berarti 1 data. tp dari proses bulk atau dari proses normal
	 * column : single_data_flag
	 */
	public void setSingleLineDataTypeFlag(String singleLineDataTypeFlag) {
		this.singleLineDataTypeFlag = singleLineDataTypeFlag;
	}
	/**
	 * flag data multiple line atau tidak. ini bukan berarti 1 data. tp dari proses bulk atau dari proses normal
	 * column : single_data_flag
	 */
	public String getSingleLineDataTypeFlag() {
		return singleLineDataTypeFlag;
	}
	
	/**
	 * reff no dari data. column : reff_no
	 */
	public void setReffNo(String reffNo) {
		this.reffNo = reffNo;
	}
	/**
	 * reff no dari data. column : reff_no
	 */
	public String getReffNo() {
		return reffNo;
	}
	/**
	 * nama file(kalau proses melalui upload)<br/>
	 * column : file_name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * nama file(kalau proses melalui upload)<br/>
	 * column : file_name
	 */
	public String getFileName() {
		return fileName;
	}
	
	
	/**
	 * berapa line dalam data
	 */
	public void setLineCount(Integer lineCount) {
		this.lineCount = lineCount;
	}
	/**
	 * berapa line dalam data
	 */
	public Integer getLineCount() {
		return lineCount;
	}
	/**
	 * column : appr_remark
	 */
	public void setApprovalRemark(String approvalRemark) {
		this.approvalRemark = approvalRemark;
	}
	/**
	 * column : appr_remark
	 */
	public String getApprovalRemark() {
		return approvalRemark;
	}
	
}
