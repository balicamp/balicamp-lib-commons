package id.co.sigma.common.data.lov;


import id.co.sigma.common.data.reflection.ClientReflectableClass;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;


/**
 * common parameter. yang isinya key + value. common type dari combo dan isian lainnya
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
@ClientReflectableClass
public class CommonLOV implements Serializable , ILookupDetail , IJSONFriendlyObject<CommonLOV>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4439385467074968444L;
	
	
	/**
	 * id parent LOV
	 **/
	protected String parentId ;
	/**
	 * value dari parameter
	 **/
	protected String dataValue ; 
	/**
	 * label parameter. visible bagi user
	 **/
	protected String label ;
	
	/**
	 * extended data 1
	 **/
	protected String additionalData1; 
	
	
	/**
	 * data tambahan 2
	 **/
	protected String additionalData2;
	
	
	
	
	
	public CommonLOV(){}
	public CommonLOV(CommonLOV lovData){
		this.parentId = lovData.parentId;
		this.additionalData1=lovData.additionalData1;
		this.additionalData2=lovData.additionalData2;
		this.dataValue=lovData.dataValue;
		this.label=lovData.label;
		
	}
	
	/**
	 * data tambahan 2
	 **/
	public void setAdditionalData2(String additionalData2) {
		this.additionalData2 = additionalData2;
	}
	/* (non-Javadoc)
	 * @see id.co.sigma.common.data.lov.ILookupDetail#getAdditionalData2()
	 */
	@Override
	public String getAdditionalData2() {
		return additionalData2;
	}
	
	/* (non-Javadoc)
	 * @see id.co.sigma.common.data.lov.ILookupDetail#getAdditionalData1()
	 */
	@Override
	public String getAdditionalData1() {
		return additionalData1;
	}
	/**
	 * extended data 1
	 **/
	public void setAdditionalData1(String additionalData1) {
		this.additionalData1 = additionalData1;
	}
	/* (non-Javadoc)
	 * @see id.co.sigma.common.data.lov.ILookupDetail#getDataValue()
	 */
	@Override
	public String getDataValue() {
		
		return dataValue;
	}
	/**
	 * value dari parameter
	 **/
	public void setDataValue(String value) {
		this.dataValue = value;
	}
	/* (non-Javadoc)
	 * @see id.co.sigma.common.data.lov.ILookupDetail#getLabel()
	 */
	@Override
	public String getLabel() {
		return label;
	}
	/**
	 * label parameter. visible bagi user
	 **/
	public void setLabel(String label) {
		this.label = label;
	} 
	
	/**
	 * id parent LOV
	 **/
	public String getParentId() {
		return parentId;
	}
	/**
	 * id parent LOV
	 **/
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getValue() {
		return this.dataValue;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("additionalData1", getAdditionalData1()); 
		jsonContainerData.put("additionalData2", getAdditionalData2()); 
		jsonContainerData.put("dataValue", getDataValue());
		jsonContainerData.put("value", getValue());
		jsonContainerData.put("label", getLabel());
		jsonContainerData.put("parentId", getParentId());
	}
	
	@Override
	public CommonLOV instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		CommonLOV retval = new CommonLOV(); 
		
		retval.setAdditionalData1(jsonContainer.getAsString("additionalData1")); 
		retval.setAdditionalData2(jsonContainer.getAsString("additionalData2"));
		retval.setDataValue(jsonContainer.getAsString("dataValue")); 
		retval.setLabel(jsonContainer.getAsString("label")); 
		retval.setParentId(jsonContainer.getAsString("parentId"));
		return retval;
	}
	
	@Override
	public String toString() {
		return "CommonLOV [parentId=" + parentId + ", dataValue=" + dataValue
				+ ", label=" + label + ", additionalData1=" + additionalData1
				+ ", additionalData2=" + additionalData2 + "]";
	}
	
	
	
}
