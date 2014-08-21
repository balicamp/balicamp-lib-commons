package id.co.sigma.common.server.data.lookup;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MASUKAN COMMENT FILE MU DI SINI YAH 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 */
@Embeddable
public class Common1LevelLOVDetailPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5934626621979221910L;
	
	/**
	 * id parent LOV
	 * column : LOV_ID
	 **/
	@Column(name="LOV_ID", updatable = false)
	private  String parentId;
	
	
	/**
	 * column : i18_CODE
	 * kode bahasa
	 **/
	@Column(name="i18_CODE" , updatable=false)
	private String languageCode ; 
	/**
	 * value dari parameter
	 * column : DETAIL_CODE
	 **/
	@Column(name="DETAIL_CODE",updatable=false)
	private  String dataValue;

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
	@Override
	public String toString() {
		return "Common1LevelLOVDetailPK [parentId=" + parentId
				+ ", languageCode=" + languageCode + ", dataValue=" + dataValue
				+ "]";
	}
	
	

}


