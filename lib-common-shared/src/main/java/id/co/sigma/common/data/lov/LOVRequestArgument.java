package id.co.sigma.common.data.lov;


import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * Request argument untuk LOV data. Mandatory untuk mengisikan ID LOV + source(Pls refer pada {@link LOVRequestArgument#id} dan {@link LOVRequestArgument#source})
 *  
 *  kalau misalnya data LOV sudah pernah di cache di lokal perlu menaruh versi cache dari lov. Field -> {@link LOVRequestArgument#cacheVersion} harus di isikan kalau misal nya di lokal sudah pernah di cache
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 **/
@SuppressWarnings("serial")
public class LOVRequestArgument implements IsSerializable, IJSONFriendlyObject<LOVRequestArgument> {

	
	/**
	 * id LOV
	 **/
	private String id ;
	
	
	
	
	
	
	
	/**
	 * source LOV
	 **/
	private LOVSource source ; 
	
	public LOVRequestArgument() {

	}

	public LOVRequestArgument(String id, LOVSource source) {
		this.id = id;
		this.source = source;
	}
	
	/**
	 * versi data yang di cche. kalau null berarti belum di cache. ambil versi langsung
	 **/
	private String cacheVersion; 
	
	/**
	 * source LOV
	 **/
	public LOVSource getSource() {
		return source;
	}
	/**
	 * source LOV
	 **/
	public void setSource(LOVSource source) {
		this.source = source;
	}
	/**
	 * id LOV
	 **/
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * id LOV
	 **/
	public String getId() {
		return id;
	}

	


	/**
	 * versi data yang di cche. kalau null berarti belum di cache. ambil versi langsung
	 **/
	public void setCacheVersion(String cacheVersion) {
		this.cacheVersion = cacheVersion;
	}
	/**
	 * versi data yang di cche. kalau null berarti belum di cache. ambil versi langsung
	 **/
	public String getCacheVersion() {
		return cacheVersion;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("cacheVersion",getCacheVersion());
		jsonContainer.put("id",getId());
		jsonContainer.put("source",getSource());
	}
	
	@Override
	public LOVRequestArgument instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		LOVRequestArgument retval = new LOVRequestArgument();
		retval.setCacheVersion( (String)jsonContainer.get("cacheVersion" ,  String.class.getName()));
		retval.setId( (String)jsonContainer.get("id" ,  String.class.getName()));
		retval.setSource( (LOVSource)jsonContainer.get("source" ,  LOVSource.class.getName()));
		return retval; 
	}

}
