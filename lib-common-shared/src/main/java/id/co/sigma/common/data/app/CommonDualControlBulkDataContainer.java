package id.co.sigma.common.data.app;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * table : m_dual_ctrl_bulk_data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@Entity
@Table(name="m_dual_ctrl_bulk_data")
public class CommonDualControlBulkDataContainer implements IJSONFriendlyObject<CommonDualControlBulkDataContainer>{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5360693838139721580L;


	/**
	 * column : PK 
	 * primary key dari data
	 */
	@Id
	@Column(name="pk")
	private Long id;
	
	
	/**
	 * reference ke table m_dual_control_table.
	 * column  : pk 
	 */
	@OneToOne(fetch=FetchType.EAGER )
	@JoinColumn(insertable=false , updatable=false , referencedColumnName="dual_ctrl_pk" , name="pk")
	private HeaderDataOnlyCommonDualControlContainerTable dualControlContainerTable ; 
	
	
	/**
	 * berapa line count yang ada dalam raw data. ini untuk speed access
	 * column : data_count
	 */
	@Column(name="data_count" , nullable=false, scale=5 , precision=0)
	private Integer dataCount ; 
	
	
	
	/**
	 * isi data bulk dalam representasi JSOn
	 * column : bulk_json_data
	 */
	@Column(name="bulk_json_data" , nullable=false)
	@Lob
	private String bulkDataAsJson ; 
	
	/**
	 * column : PK 
	 * primary key dari data
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 
	 * 
	 * column : PK 
	 * primary key dari data
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * reference ke table m_dual_control_table.
	 * column  : pk 
	 */
	public void setDualControlContainerTable(
			HeaderDataOnlyCommonDualControlContainerTable dualControlContainerTable) {
		this.dualControlContainerTable = dualControlContainerTable;
	}
	/**
	 * reference ke table m_dual_control_table.
	 * column  : pk 
	 */
	public HeaderDataOnlyCommonDualControlContainerTable getDualControlContainerTable() {
		return dualControlContainerTable;
	}
	/**
	 * berapa line count yang ada dalam raw data. ini untuk speed access
	 * column : data_count
	 */
	public Integer getDataCount() {
		return dataCount;
	}
	/**
	 * berapa line count yang ada dalam raw data. ini untuk speed access
	 * column : data_count
	 */
	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}
	
	/**
	 * isi data bulk dalam representasi JSOn
	 * column : bulk_json_data
	 */
	public void setBulkDataAsJson(String bulkDataAsJson) {
		this.bulkDataAsJson = bulkDataAsJson;
	}
	/**
	 * isi data bulk dalam representasi JSOn
	 * column : bulk_json_data
	 */
	public String getBulkDataAsJson() {
		return bulkDataAsJson;
	}
	
	
	@PrePersist
	protected void beforePersistTask (){
		this.id = dualControlContainerTable.getId(); 
	}
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("id", this.id);
		jsonContainer.put("bulkDataAsJson", this.bulkDataAsJson);
		jsonContainer.put("dataCount", this.dataCount);
		jsonContainer.put("dualControlContainerTable", this.getDualControlContainerTable());
		
	}
	@Override
	public CommonDualControlBulkDataContainer instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		CommonDualControlBulkDataContainer retval = new CommonDualControlBulkDataContainer(); 
		retval.id = (Long)jsonContainer.get("id" , Long.class.getName()); 
		retval.bulkDataAsJson = jsonContainer.getAsString("bulkDataAsJson"); 
		retval.dataCount = jsonContainer.getAsInteger("dataCount"); 
		retval.dualControlContainerTable = (HeaderDataOnlyCommonDualControlContainerTable) jsonContainer
				.get("dualControlContainerTable", HeaderDataOnlyCommonDualControlContainerTable.class.getName()); 
		return retval;
	}
}
