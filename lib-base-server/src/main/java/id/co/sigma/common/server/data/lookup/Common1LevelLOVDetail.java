package id.co.sigma.common.server.data.lookup;

import id.co.sigma.common.data.lov.ILookupDetail;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * mapper table : m_lookup_details 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 */
@Entity
@Table(name="m_lookup_details")
public class Common1LevelLOVDetail implements Serializable, ILookupDetail{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6601769294590832841L;


	/**
	 * ID data
	 **/
	@EmbeddedId
	private Common1LevelLOVDetailPK id ;
	
	/**
	 * id parent LOV
	 * column : LOV_ID
	 **/
	@Column(name="LOV_ID", insertable=false, updatable=false)
	private  String parentId;
	
	
	/**
	 * column : i18_CODE
	 * kode bahasa
	 **/
	@Column(name="i18_CODE" , insertable=false, updatable=false)
	private String languageCode ; 
	/**
	 * value dari parameter
	 * column : DETAIL_CODE
	 **/
	@Column(name="DETAIL_CODE", insertable=false, updatable=false)
	private  String dataValue;
	
	/**
	 * data tambahan 2
	 * column : VAL_2
	 **/
	@Column(name="VAL_2")
	private  String additionalData2;

	/**
	 * extended data 1
	 * column  : VAL_1
	 **/
	@Column(name="VAL_1")
	private  String additionalData1;

	

	/**
	 * label parameter. visible bagi user
	 * column : LOV_LABEL
	 **/
	@Column(name="LOV_LABEL")
	private  String label; 
	
	
	/**
	 * column : SEQ_NO
	 * nomor urut sequence dalam data
	 **/
	@Column(name="SEQ_NO" ,nullable=false)
	private Integer  sequence ; 
	
	
	/**
	 * ID data
	 **/
	public Common1LevelLOVDetailPK getId() {
		return id;
	}
	/**
	 * ID data
	 **/
	public void setId(Common1LevelLOVDetailPK id) {
		this.id = id;
	}
	
	/**
	 * label parameter. visible bagi user
	 * column : LOV_LABEL
	 **/
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * label parameter. visible bagi user
	 * column : LOV_LABEL
	 **/
	public String getLabel() {
		return label;
	}
	/**
	 * extended data 1
	 * column  : VAL_1
	 **/
	public String getAdditionalData1() {
		return additionalData1;
	}
	/**
	 * extended data 1
	 * column  : VAL_1
	 **/
	public void setAdditionalData1(String additionalData1) {
		this.additionalData1 = additionalData1;
	}
	/**
	 * data tambahan 2
	 * column : VAL_2
	 **/
	public void setAdditionalData2(String additionalData2) {
		this.additionalData2 = additionalData2;
	}
	/**
	 * data tambahan 2
	 * column : VAL_2
	 **/
	public String getAdditionalData2() {
		return additionalData2;
	}
	
	/**
	 * id parent LOV
	 * column : LOV_ID
	 **/
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * id parent LOV
	 * column : LOV_ID
	 **/
	public String getParentId() {
		return parentId;
	}

	/**
	 * column : i18_CODE
	 * kode bahasa
	 **/
	public String getLanguageCode() {
		return languageCode;
	}
	/**
	 * column : i18_CODE
	 * kode bahasa
	 **/
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
	/**
	 * value dari parameter
	 * column : DETAIL_CODE
	 **/
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
	/**
	 * value dari parameter
	 * column : DETAIL_CODE
	 **/
	public String getDataValue() {
		return dataValue;
	}
	
	/**
	 * column : SEQ_NO
	 * nomor urut sequence dalam data
	 **/
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	/**
	 * column : SEQ_NO
	 * nomor urut sequence dalam data
	 **/
	public Integer getSequence() {
		return sequence;
	}
	@Override
	public String toString() {
		return "Common1LevelLOVDetail [id=" + id + ", parentId=" + parentId
				+ ", languageCode=" + languageCode + ", dataValue=" + dataValue
				+ ", additionalData2=" + additionalData2 + ", additionalData1="
				+ additionalData1 + ", label=" + label + ", sequence="
				+ sequence + "]";
	}
	 
}


