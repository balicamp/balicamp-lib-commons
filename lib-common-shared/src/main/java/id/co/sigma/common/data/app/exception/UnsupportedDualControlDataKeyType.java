package id.co.sigma.common.data.app.exception;

import java.math.BigInteger;

import id.co.sigma.common.data.app.DualControlEnabledData;
import id.co.sigma.common.exception.BaseIsSerializableException;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * ini dalam kasus tipe data tidak di kenali. jadinya perlu di siapkan custom handler 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class UnsupportedDualControlDataKeyType extends BaseIsSerializableException implements IJSONFriendlyObject<UnsupportedDualControlDataKeyType>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7956791145526606174L;
	
	/**
	 * tipe key dari class. yang built in support : {@link String} , {@link Long} , {@link Integer} , {@link BigInteger}
	 **/
	private String unsupportedDataKeyTypeFQCN  ;
	/**
	 * class owner dari data
	 **/
	private String ownerDataType  ;
	
	
	public UnsupportedDualControlDataKeyType(){
		super();
		
	}
	public UnsupportedDualControlDataKeyType(String message){
		super(message);
		
	}
	
	public UnsupportedDualControlDataKeyType(String message, Class<?> keyDataType , Class<?> ownerDataType){
		super(message);
		this.unsupportedDataKeyTypeFQCN = keyDataType.getName() ; 
		this.ownerDataType = ownerDataType.getName();
	}
	
	
	
	/**
	 * konstruksi data dengan sample object
	 **/
	public UnsupportedDualControlDataKeyType(String message, @SuppressWarnings("rawtypes") DualControlEnabledData sampleData){
		this(message , sampleData.getPrimaryKeyClassType() , sampleData.getClass());
	}
	
	/**
	 * tipe key dari class. yang built in support : {@link String} , {@link Long} , {@link Integer} , {@link BigInteger}
	 **/
	public String getUnsupportedDataKeyTypeFQCN() {
		return unsupportedDataKeyTypeFQCN;
	}
	/**
	 * tipe key dari class. yang built in support : {@link String} , {@link Long} , {@link Integer} , {@link BigInteger}
	 **/
	public void setUnsupportedDataKeyTypeFQCN(String unsupportedDataKeyTypeFQCN) {
		this.unsupportedDataKeyTypeFQCN = unsupportedDataKeyTypeFQCN;
	}
	
	/**
	 * class owner dari data
	 **/
	public void setOwnerDataType(String ownerDataType) {
		this.ownerDataType = ownerDataType;
	}
	/**
	 * class owner dari data
	 **/
	public String getOwnerDataType() {
		return ownerDataType;
	}
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("message", getMessage());
		jsonContainerData.put("fullStackTrace", getFullStackTrace());
		jsonContainerData.put("ownerDataType", getOwnerDataType());
		jsonContainerData.put("unsupportedDataKeyTypeFQCN", getUnsupportedDataKeyTypeFQCN());
	}
	
	@Override
	public UnsupportedDualControlDataKeyType instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		UnsupportedDualControlDataKeyType retval = new UnsupportedDualControlDataKeyType();
		retval.setFullStackTrace(jsonContainer.getAsString("fullStackTrace"));
		retval.setMessage(jsonContainer.getAsString("message"));
		retval.setOwnerDataType(jsonContainer.getAsString("ownerDataType"));
		retval.setUnsupportedDataKeyTypeFQCN(jsonContainer.getAsString("unsupportedDataKeyTypeFQCN"));
		return retval;
	}

}
