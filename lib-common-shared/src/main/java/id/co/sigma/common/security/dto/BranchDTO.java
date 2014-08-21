package id.co.sigma.common.security.dto;

import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Branch DTO
 * @author I Gede Mahendra
 * @since Jan 30, 2013, 5:25:22 PM
 * @version $Id
 */
public class BranchDTO implements SingleKeyEntityData<Long>,IsSerializable,Serializable, IJSONFriendlyObject<BranchDTO>{

	private static final long serialVersionUID = -6677691800656625680L;
	
	private Long id;
	private Long idParent;
	private String branchParentCode;
	private String branchCode;
	private String branchName;
	private String branchAddress;
	private String branchDescription;
	private String branchStatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdParent() {
		return idParent;
	}
	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}	
	public String getBranchParentCode() {
		return branchParentCode;
	}
	public void setBranchParentCode(String branchParentCode) {
		this.branchParentCode = branchParentCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public String getBranchDescription() {
		return branchDescription;
	}
	public void setBranchDescription(String branchDescription) {
		this.branchDescription = branchDescription;
	}
	public String getBranchStatus() {
		return branchStatus;
	}
	public void setBranchStatus(String branchStatus) {
		this.branchStatus = branchStatus;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("branchAddress",getBranchAddress());
		jsonContainer.put("branchCode",getBranchCode());
		jsonContainer.put("branchDescription",getBranchDescription());
		jsonContainer.put("branchName",getBranchName());
		jsonContainer.put("branchParentCode",getBranchParentCode());
		jsonContainer.put("branchStatus",getBranchStatus());
		jsonContainer.put("id",getId());
		jsonContainer.put("idParent",getIdParent());
	}
	
	@Override
	public BranchDTO instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		BranchDTO retval = new BranchDTO();
		retval.setBranchAddress( (String)jsonContainer.get("branchAddress" ,  String.class.getName()));
		retval.setBranchCode( (String)jsonContainer.get("branchCode" ,  String.class.getName()));
		retval.setBranchDescription( (String)jsonContainer.get("branchDescription" ,  String.class.getName()));
		retval.setBranchName( (String)jsonContainer.get("branchName" ,  String.class.getName()));
		retval.setBranchParentCode( (String)jsonContainer.get("branchParentCode" ,  String.class.getName()));
		retval.setBranchStatus( (String)jsonContainer.get("branchStatus" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setIdParent( (Long)jsonContainer.get("idParent" ,  Long.class.getName()));
		return retval; 
	}
}