package id.co.sigma.common.data.lov;


import id.co.sigma.common.data.reflection.ClientReflectableClass;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONArrayContainer;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;




/**
 * Header LOV + data detail
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 **/
@ClientReflectableClass
public class CommonLOVHeader implements Serializable , ILookupHeader, IJSONFriendlyObject<CommonLOVHeader>{
	
	protected static final Logger logger = Logger.getLogger(CommonLOVHeader.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = -854610058474637832L;
	protected String lovId ; 
	
	
	/**
	 * internalization code
	 **/
	protected String i18Key ; 
	protected String lovRemark ; 
	protected String version ;
	
	protected boolean cacheable ; 
	
	protected LOVSource source ; 
	protected List<CommonLOV> details ;
	
	
	
	
	public CommonLOVHeader(){
		
	}
	
	public CommonLOVHeader(CommonLOVHeader sample){
		cloneDetails(sample);
		this.cacheable= sample.cacheable ;
		this.i18Key = sample.i18Key; 
		this.lovRemark= sample.lovRemark;
		this.source=sample.source;
		this.version = sample.version ;
		this.lovId = sample.lovId ; 
				
	}
	
	
	private void  cloneDetails (CommonLOVHeader sample){

		if ( sample.details!=null&& !sample.details.isEmpty()){
			this.details = new ArrayList<CommonLOV>(); 
			for ( CommonLOV scn : sample.details){
				this.details.add(new CommonLOV(scn));
			}
		}
	}
	/**
	 * internalization code
	 **/
	public void setI18Key(String i18Key) {
		this.i18Key = i18Key;
	}
	/**
	 * internalization code
	 **/
	public String getI18Key() {
		return i18Key;
	}
	/* (non-Javadoc)
	 * @see id.co.sigma.common.data.lov.ILookupHeader#getLovId()
	 */
	@Override
	public String getLovId() {
		return lovId;
	}
	public void setLovId(String lovId) {
		this.lovId = lovId;
	}
	/* (non-Javadoc)
	 * @see id.co.sigma.common.data.lov.ILookupHeader#getLovRemark()
	 */
	@Override
	public String getLovRemark() {
		return lovRemark;
	}
	public void setLovRemark(String lovRemark) {
		this.lovRemark = lovRemark;
	}
	/* (non-Javadoc)
	 * @see id.co.sigma.common.data.lov.ILookupHeader#getVersion()
	 */
	@Override
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<CommonLOV> getDetails() {
		return details;
	}
	public void setDetails(List<CommonLOV> details) {
		this.details = details;
	}
	
	
	/* (non-Javadoc)
	 * @see id.co.sigma.common.data.lov.ILookupHeader#isCacheable()
	 */
	@Override
	public boolean isCacheable() {
		return cacheable;
	}
	
	
	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}
	
	
	
	public LOVSource getSource() {
		return source;
	}
	
	public void setSource(LOVSource source) {
		this.source = source;
	}
	
	
	
	/**
	 * mencari LOV by id
	 **/
	public CommonLOV findById (String id){
		if (lovId==null||lovId.length()==0||details ==null||details.isEmpty())
			return null ; 
		for ( CommonLOV scn : details){
			if( id.equals(  scn.getDataValue()))
				return scn ;
		}
		GWT.log("no match untuk LOV:" + lovId +",untuk value :" + id);
		return null ; 
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		// header only data
		jsonContainerData.put("lovId", getLovId());
		jsonContainerData.put("i18Key", getI18Key());
		jsonContainerData.put("lovRemark", getLovRemark()); 
		jsonContainerData.put("version", getVersion());
		jsonContainerData.put("cacheable", cacheable);
		jsonContainerData.put("source", getSource().toString());
		// DETAILS goes here
		jsonContainerData.put("details", getDetails());
		
		
	}

	@Override
	public CommonLOVHeader instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		CommonLOVHeader retval = new CommonLOVHeader(); 
		retval.setLovId(jsonContainer.getAsString("lovId"));
		retval.setI18Key(jsonContainer.getAsString("i18Key"));
		retval.setLovRemark(jsonContainer.getAsString("lovRemark"));
		retval.setVersion(jsonContainer.getAsString("version"));
		retval.setCacheable(jsonContainer.getAsBoolean("cacheable"));
		String codeEnum =  jsonContainer.getAsString("source"); 
		retval.setSource(LOVSource.instantiateFromString(codeEnum));
		ParsedJSONArrayContainer arrayCnt =  jsonContainer.getAsArray("details");
		CommonLOV sample = new CommonLOV();
		if ( arrayCnt!= null){
			if ( arrayCnt.length()>0){
				ArrayList<CommonLOV> lovs =new ArrayList<CommonLOV>(); 
				retval.setDetails(lovs);
				for ( int i = 0 ; i < arrayCnt.length();i++){
					ParsedJSONContainer prsr =  arrayCnt.get(i);
					lovs.add(sample.instantiateFromJSON(prsr));
				}
			}
		}
		
		return retval;
	}

	@Override
	public String toString() {
		return "CommonLOVHeader [lovId=" + lovId + ", i18Key=" + i18Key
				+ ", lovRemark=" + lovRemark + ", version=" + version
				+ ", cacheable=" + cacheable + ", source=" + source + "]";
	}
	
	
	

}
