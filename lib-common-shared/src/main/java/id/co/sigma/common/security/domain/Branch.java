/**
 * File Name : Branch.java
 * Package   : id.co.sigma.arium.security.shared.domain
 * Project   : security-data
 */
package id.co.sigma.common.security.domain;




import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.security.domain.audit.BaseAuditedObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entitiy untuk tabel : sec_branch
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 2:37:05 PM
 * @version $Id
 */
@Entity
@Table(name="sec_branch")
public class Branch extends BaseAuditedObject implements SingleKeyEntityData<Long>, IJSONFriendlyObject<Branch> {

	private static final long serialVersionUID = 1430766844084002850L;
	
	/**
	* branch id<br/>
	* column :pk
	**/
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pk")
	private Long id;
	/**
	* reference ke parent<br/>
	* column :parent_branch_id
	**/
	@Column(name="parent_branch_id")
	private Long branchParendId;
	/**
	* kode cabang<br/>
	* column :BRANCH_CODE
	**/
	@Column(name="branch_code", length=16)
	private String branchCode;
	/**
	* nama cabang<br/>
	* column :BRANCH_NAME
	**/
	@Column(name="branch_name", length=32)
	private String branchName;
	/**
	* alamat cabang<br/>
	* column :BRANCH_ADDRESS
	**/
	@Column(name="branch_address", length=128)
	private String branchAddress;
	
	/**
	* deskripsi<br/>
	* column :DESCRIPTION
	**/
	@Column(name="description", length=200)
	private String description;

	/**
	 * Default Constructor
	 */
	public Branch() {
		super();
	}
	
	/**
	 * Additional Branch
	 * @param idBranch
	 */
	public Branch(Long idBranch){
		this.id = idBranch;
	}
	
	/**
	* branch id<br/>
	* column :BRANCH_ID
	**/
	public void setId(Long id){
	  this.id=id;
	}
	/**
	* branch id<br/>
	* column :BRANCH_ID
	**/
	public Long getId(){
	    return this.id;
	}
	/**
	* reference ke parent<br/>
	* column :BRANCH_ID_PARENT
	**/
	public void setBranchParendId(Long branchParendId){
	  this.branchParendId=branchParendId;
	}
	/**
	* reference ke parent<br/>
	* column :BRANCH_ID_PARENT
	**/
	public Long getBranchParendId(){
	    return this.branchParendId;
	}
	/**
	* kode cabang<br/>
	* column :BRANCH_CODE
	**/
	public void setBranchCode(String branchCode){
	  this.branchCode=branchCode;
	}
	/**
	* kode cabang<br/>
	* column :BRANCH_CODE
	**/
	public String getBranchCode(){
	    return this.branchCode;
	}
	/**
	* nama cabang<br/>
	* column :BRANCH_NAME
	**/
	public void setBranchName(String branchName){
	  this.branchName=branchName;
	}
	/**
	* nama cabang<br/>
	* column :BRANCH_NAME
	**/
	public String getBranchName(){
	    return this.branchName;
	}
	/**
	* alamat cabang<br/>
	* column :BRANCH_ADDRESS
	**/
	public void setBranchAddress(String branchAddress){
	  this.branchAddress=branchAddress;
	}
	/**
	* alamat cabang<br/>
	* column :BRANCH_ADDRESS
	**/
	public String getBranchAddress(){
	    return this.branchAddress;
	}
	
	/**
	* deskripsi<br/>
	* column :DESCRIPTION
	**/
	public void setDescription(String description){
	  this.description=description;
	}
	/**
	* deskripsi<br/>
	* column :DESCRIPTION
	**/
	public String getDescription(){
	    return this.description;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((branchAddress == null) ? 0 : branchAddress.hashCode());
		result = prime * result
				+ ((branchCode == null) ? 0 : branchCode.hashCode());
		result = prime * result
				+ ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result
				+ ((branchParendId == null) ? 0 : branchParendId.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		if (branchAddress == null) {
			if (other.branchAddress != null)
				return false;
		} else if (!branchAddress.equals(other.branchAddress))
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
		if (branchParendId == null) {
			if (other.branchParendId != null)
				return false;
		} else if (!branchParendId.equals(other.branchParendId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	/*
	@Override
	public String toString() {
		return "Branch [id=" + id + ", branchParendId=" + branchParendId
				+ ", branchCode=" + branchCode + ", branchName=" + branchName
				+ ", branchAddress=" + branchAddress 
				+ ", description=" + description + "]";
	}*/
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("branchAddress",getBranchAddress());
		jsonContainer.put("branchCode",getBranchCode());
		jsonContainer.put("branchName",getBranchName());
		jsonContainer.put("branchParendId",getBranchParendId());
		jsonContainer.put("description",getDescription());
		jsonContainer.put("id",getId());
		
	}
	
	
	
	public static final String[] MODIFABLE_FIELDS = {
		"branchAddress" , "branchCode", "branchName" , "branchParendId"  , "description" , "status"
	};

	@Override
	public Branch instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		Branch retval =new Branch();
		retval.setBranchAddress( (String)jsonContainer.get("branchAddress" ,  String.class.getName()));
		retval.setBranchCode( (String)jsonContainer.get("branchCode" ,  String.class.getName()));
		retval.setBranchName( (String)jsonContainer.get("branchName" ,  String.class.getName()));
		retval.setBranchParendId( (Long)jsonContainer.get("branchParendId" ,  Long.class.getName()));
		retval.setDescription( (String)jsonContainer.get("description" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		return retval;
	} 

	/*@Override
	public String[] retrieveModifableFields() {
		return MODIFABLE_FIELDS;
	}

	@Override
	public void setActiveFlag(String activeFlag) {
		setDataStatusCode(activeFlag);
		
	}

	@Override
	public String getActiveFlag() {
		return getDataStatusCode();
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
		return null;
	}

	@Override
	public String getKey2AsString() {
		// TODO Auto-generated method stub
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

	@Override
	protected void extractDataFromJSON(Branch targetObject,
			ParsedJSONContainer jsonContainer) {
		Branch retval =targetObject;
		retval.setBranchAddress( (String)jsonContainer.get("branchAddress" ,  String.class.getName()));
		retval.setBranchCode( (String)jsonContainer.get("branchCode" ,  String.class.getName()));
		retval.setBranchName( (String)jsonContainer.get("branchName" ,  String.class.getName()));
		retval.setBranchParendId( (Long)jsonContainer.get("branchParendId" ,  Long.class.getName()));
		retval.setDescription( (String)jsonContainer.get("description" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		
		
	}*/
}