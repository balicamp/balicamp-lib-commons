package id.co.sigma.common.data.app;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * system parameter. table : m_system_parameter. parameter tunggal. hanya berupa : key, description , value
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@Entity
@Table(name="m_system_parameter")
public class SystemSimpleParameter implements IJSONFriendlyObject<SystemSimpleParameter>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2401643524979058695L;

	/**
	* key dari parameter<br/>
	* column :param_key
	**/
	@Id
	@Column(name="param_key", nullable=true)
	private String id;
	 
	/**
	* value dari parameter<br/>
	* column :param_value
	**/
	@Column(name="param_value", nullable=true)
	private String valueRaw;
	
	
	
	/**
	 * flag value di encrypt atau tidak
	 */
	@Transient
	private String encryptedValueFlag ; 
	 
	/**
	* java fqcn :java.lang.String, java.lang.Integer, Long dan tipe sederhana lain nya<br/>
	* column :param_type
	**/
	@Column(name="param_type", nullable=true)
	private String paramType;
	 
	/**
	* catatan pengingat parameter<br/>
	* column :param_remark
	**/
	@Column(name="param_remark", nullable=true)
	private String remark;
	 
	/**
	* boolean flag, editable atau tidak<br/>
	* column :editable
	**/
	@Column(name="editable", nullable=true)
	private String editableFlag;
	 
	
	/**
	* key dari parameter<br/>
	* column :param_key
	**/
	public void setId(String id){
	  this.id=id;
	}
	 
	/**
	* key dari parameter<br/>
	* column :param_key
	**/
	public String getId(){
	    return this.id;
	}
	 
	/**
	* value dari parameter<br/>
	* column :param_value
	**/
	public void setValueRaw(String valueRaw){
	  this.valueRaw=valueRaw;
	}
	 
	/**
	* value dari parameter<br/>
	* column :param_value
	**/
	public String getValueRaw(){
	    return this.valueRaw;
	}
	 
	/**
	* java fqcn :java.lang.String, java.lang.Integer, Long dan tipe sederhana lain nya<br/>
	* column :param_type
	**/
	public void setParamType(String paramType){
	  this.paramType=paramType;
	}
	 
	/**
	* java fqcn :java.lang.String, java.lang.Integer, Long dan tipe sederhana lain nya<br/>
	* column :param_type
	**/
	public String getParamType(){
	    return this.paramType;
	}
	 
	/**
	* catatan pengingat parameter<br/>
	* column :param_remark
	**/
	public void setRemark(String remark){
	  this.remark=remark;
	}
	 
	/**
	* catatan pengingat parameter<br/>
	* column :param_remark
	**/
	public String getRemark(){
	    return this.remark;
	}
	 
	/**
	* boolean flag, editable atau tidak<br/>
	* column :editable
	**/
	public void setEditableFlag(String editableFlag){
	  this.editableFlag=editableFlag;
	}
	 
	/**
	* boolean flag, editable atau tidak<br/>
	* column :editable
	**/
	public String getEditableFlag(){
	    return this.editableFlag;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("id",id);
		jsonContainer.put("valueRaw",valueRaw);
		jsonContainer.put("paramType",paramType);
		jsonContainer.put("remark",remark);
		jsonContainer.put("editableFlag",editableFlag);
		jsonContainer.put("encryptedValueFlag", encryptedValueFlag);
		
	}

	@Override
	public SystemSimpleParameter instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		SystemSimpleParameter retval = new SystemSimpleParameter() ; 
		retval.id=(String)jsonContainer.get("id",String.class.getName());
		retval.valueRaw=(String)jsonContainer.get("valueRaw",String.class.getName());
		retval.paramType=(String)jsonContainer.get("paramType",String.class.getName());
		retval.remark=(String)jsonContainer.get("remark",String.class.getName());
		retval.editableFlag=(String)jsonContainer.get("editableFlag",String.class.getName());
		retval.encryptedValueFlag = jsonContainer.getAsString("encryptedValueFlag"); 
		return retval;
	}
	 
	/**
	 * flag value di encrypt atau tidak
	 */
	public void setEncryptedValueFlag(String encryptedValueFlag) {
		this.encryptedValueFlag = encryptedValueFlag;
	}
	/**
	 * flag value di encrypt atau tidak
	 */
	public String getEncryptedValueFlag() {
		return encryptedValueFlag;
	}


}
