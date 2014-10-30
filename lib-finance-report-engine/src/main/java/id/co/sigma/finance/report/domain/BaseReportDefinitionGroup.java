package id.co.sigma.finance.report.domain;

/**
 *
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class BaseReportDefinitionGroup {


	/**
	 * segment yang di pegunakan untuk membaca data. 2 s.d. 8 
	 */
	private int glAccountSegmentNo = 2   ; 
	
	
	
	
	/**
	 * nama tag xml untuk detial . untuk repeating data di namai apa
	 */
	private String detailDataRootTag ; 
	
	/**
	 * XML path . di mana data ini harus di taruh
	 */
	private String rootXmlPath ; 
	
	
	
	
	/**
	 * xml path untuk label. relative thdp root
	 */
	private String xmlPathForLabel ;
	
	
	
	/**
	 * xml path utnuk data code
	 */
	private String xmlPathForDataCode ;
	
	/**
	 * XML path . di mana data ini harus di taruh
	 */
	public void setRootXmlPath(String rootXmlPath) {
		this.rootXmlPath = rootXmlPath;
	}
	
	/**
	 * XML path . di mana data ini harus di taruh
	 */
	public String getRootXmlPath() {
		return rootXmlPath;
	}
	
	/**
	 * segment yang di pegunakan untuk membaca data. 2 s.d. 8 
	 */
	public void setGlAccountSegmentNo(int glAccountSegmentNo) {
		this.glAccountSegmentNo = glAccountSegmentNo;
	}
	/**
	 * segment yang di pegunakan untuk membaca data. 2 s.d. 8 
	 */
	public int getGlAccountSegmentNo() {
		return glAccountSegmentNo;
	}
	
	/**
	 * xml path untuk label. relative thdp root
	 */
	public void setXmlPathForLabel(String labelXmlPath) {
		this.xmlPathForLabel = labelXmlPath;
	}
	/**
	 * xml path untuk label. relative thdp root
	 */
	public String getXmlPathForLabel() {
		return xmlPathForLabel;
	}
	/**
	 * xml path utnuk data code
	 */
	public void setXmlPathForDataCode(String dataCodeXmlPath) {
		this.xmlPathForDataCode = dataCodeXmlPath;
	}
	/**
	 * xml path utnuk data code
	 */
	public String getXmlPathForDataCode() {
		return xmlPathForDataCode;
	}
	
	/**
	 * nama tag xml untuk detail . untuk repeating data di namai apa
	 */
	public void setDetailDataRootTag(String detailDataRootTag) {
		this.detailDataRootTag = detailDataRootTag;
	}
	/**
	 * nama tag xml untuk detial . untuk repeating data di namai apa
	 */
	public String getDetailDataRootTag() {
		return detailDataRootTag;
	}
}
