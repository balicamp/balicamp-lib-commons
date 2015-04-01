package id.co.sigma.common.data.app;

import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * definisi table dual control
 * table : m_dual_control_definition
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@Entity
@Table(name="zzz_m_dual_control_definition")
public class DualControlDefinition implements Serializable ,SingleKeyEntityData<String>, IJSONFriendlyObject<DualControlDefinition>{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4494189626844027125L;
	/**
	 * FQCN dari dual control data. ini untuk locate data dalam database<br/>
	 * column : fqcn 
	 **/
	@Id
	@Column(name="fqcn" , length=512)
	private String id ;
	
	
	/**
	 * nama yang visible<br/>
	 * column : dual_control_data_name
	 **/
	@Column(name="dual_control_data_name", length=256)
	private String name ; 
	
	
	/**
	 * catatan tambahan. ini untuk kemudahan maintenence<br/>
	 * column : dual_control_remark
	 **/
	@Column(name="dual_control_remark", length=512)
	private String remark ;  
	
	/**
	 * dual controlled atau tidak. ini untuk on off - Y/N
	 * column : is_dual_controlled
	 **/
	@Column(name="is_dual_controlled" , length=1)
	private String dualControlledOnFlag ; 
	
	
	/**
	 * boolean flag, dual control strick atau tidak. strick -> user tidak bisa approve data sendiri
	 * column  : is_strick
	 **/
	@Column(name="is_strick" , length=1)
	private String strickDualControlFlag ; 
	
	
	/**
	 * hapus data dari database, on erase. default N
	 * column : is_remove_row_on_erase
	 **/
	@Column(name="is_remove_row_on_erase", length=1)
	private String removePhisicalDataOnEraseFlag ; 
	
	
	
	/**
	 * column : reff_no_length
	 * 
	 */
	@Column(name="reff_no_length")
	private Integer reffNumberLength ; 
	
	
	
	/**
	 * prefix dari reff number
	 * column : reffno_prefix
	 */
	@Column(name="reffno_prefix" , length=8)
	private String reffNumberPrefix ; 
	
	/**
	 * column : reff_no_seq_key
	 * key untuk sequence key
	 */
	@Column(name="reff_no_seq_key" , length=64)
	private String reffNoSequenceKey ; 
	
	
	
	/**
	 * column : reff_no_seq_remark
	 * remark untuk sequence
	 */
	@Column(name="reff_no_seq_remark" , length=128)
	private String reffNoSequenceRemark ;
	
	/**
	 * FQCN dari dual control data. ini untuk locate data dalam database<br/>
	 * column : fqcn 
	 **/
	@Override
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * FQCN dari dual control data. ini untuk locate data dalam database<br/>
	 * column : fqcn 
	 **/
	@Override
	public String getId() {
		return id;
	}
	/**
	 * catatan tambahan. ini untuk kemudahan maintenence<br/>
	 * column : dual_control_remark
	 **/
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * catatan tambahan. ini untuk kemudahan maintenence<br/>
	 * column : dual_control_remark
	 **/
	public String getRemark() {
		return remark;
	}
	/**
	 * nama yang visible<br/>
	 * column : dual_control_data_name
	 **/
	public String getName() {
		return name;
	}
	/**
	 * nama yang visible<br/>
	 * column : dual_control_data_name
	 **/
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * dual controlled atau tidak. ini untuk on off - Y/N
	 * column : is_dual_controlled
	 **/
	public void setDualControlledOnFlag(String dualControlledOnFlag) {
		this.dualControlledOnFlag = dualControlledOnFlag;
	}
	/**
	 * dual controlled atau tidak. ini untuk on off - Y/N
	 * column : is_dual_controlled
	 **/
	public String getDualControlledOnFlag() {
		return dualControlledOnFlag;
	}
	/**
	 * boolean flag, dual control strick atau tidak. strick -> user tidak bisa approve data sendiri
	 * column  : is_strick
	 **/
	public void setStrickDualControlFlag(String strickDualControlFlag) {
		this.strickDualControlFlag = strickDualControlFlag;
	}
	/**
	 * boolean flag, dual control strick atau tidak. strick -> user tidak bisa approve data sendiri
	 * column  : is_strick
	 **/
	public String getStrickDualControlFlag() {
		return strickDualControlFlag;
	}
	/**
	 * hapus data dari database, on erase. default N
	 * column : is_remove_row_on_erase
	 **/
	public void setRemovePhisicalDataOnEraseFlag(
			String removePhisicalDataOnEraseFlag) {
		this.removePhisicalDataOnEraseFlag = removePhisicalDataOnEraseFlag;
	}
	/**
	 * hapus data dari database, on erase. default N
	 * column : is_remove_row_on_erase
	 **/
	public String getRemovePhisicalDataOnEraseFlag() {
		return removePhisicalDataOnEraseFlag;
	}
	
	/**
	 * column : reff_no_length
	 * 
	 */
	public void setReffNumberLength(Integer reffNumberLength) {
		this.reffNumberLength = reffNumberLength;
	}
	/**
	 * column : reff_no_length
	 * 
	 */
	public Integer getReffNumberLength() {
		return reffNumberLength;
	}
	
	/**
	 * prefix dari reff number
	 * column : reffno_prefix
	 */
	public void setReffNumberPrefix(String reffNumberPrefix) {
		this.reffNumberPrefix = reffNumberPrefix;
	}
	/**
	 * prefix dari reff number
	 * column : reffno_prefix
	 */
	public String getReffNumberPrefix() {
		return reffNumberPrefix;
	}
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("id", this.id);
		jsonContainer.put("name", this.name);
		jsonContainer.put("remark", this.remark);
		jsonContainer.put("dualControlledOnFlag", dualControlledOnFlag);
		jsonContainer.put("strickDualControlFlag", strickDualControlFlag);
		jsonContainer.put("removePhisicalDataOnEraseFlag", removePhisicalDataOnEraseFlag);
		jsonContainer.put("reffNumberPrefix", reffNumberPrefix);
		jsonContainer.put("reffNumberLength", reffNumberLength);
		jsonContainer.put("reffNoSequenceKey", reffNoSequenceKey);
		jsonContainer.put("reffNoSequenceRemark", reffNoSequenceRemark); 
		
	}
	@Override
	public DualControlDefinition instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		DualControlDefinition retval = new DualControlDefinition();
		retval.id = jsonContainer.getAsString("id");
		retval.dualControlledOnFlag = jsonContainer.getAsString("dualControlledOnFlag");
		retval.name = jsonContainer.getAsString("name");
		retval.remark = jsonContainer.getAsString("remark");
		retval.removePhisicalDataOnEraseFlag = jsonContainer.getAsString("removePhisicalDataOnEraseFlag");
		retval.strickDualControlFlag  = jsonContainer.getAsString("strickDualControlFlag");
		retval.reffNumberLength = jsonContainer.getAsInteger("reffNumberLength") ; 
		retval.reffNumberPrefix = jsonContainer.getAsString("reffNumberPrefix") ; 
		retval.reffNoSequenceKey = jsonContainer.getAsString("reffNoSequenceKey"); 
		retval.reffNoSequenceRemark = jsonContainer.getAsString("reffNoSequenceRemark"); 
		
		return retval;
	}
	
	/**
	 * column : reff_no_seq_remark
	 * remark untuk sequence
	 */
	public void setReffNoSequenceRemark(String reffNoSequenceRemark) {
		this.reffNoSequenceRemark = reffNoSequenceRemark;
	}
	/**
	 * column : reff_no_seq_remark
	 * remark untuk sequence
	 */
	public String getReffNoSequenceRemark() {
		return reffNoSequenceRemark;
	}
	/**
	 * column : reff_no_seq_key
	 * key untuk sequence key
	 */
	public void setReffNoSequenceKey(String reffNoSequenceKey) {
		this.reffNoSequenceKey = reffNoSequenceKey;
	}
	/**
	 * column : reff_no_seq_key
	 * key untuk sequence key
	 */
	public String getReffNoSequenceKey() {
		return reffNoSequenceKey;
	}
}

