package id.co.sigma.common.exception;

import id.co.sigma.common.data.app.exception.UnsupportedDualControlDataKeyType;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * ini class untuk menotifikasi client kalau resource still Sync dengan data di server
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public class CacheStillUpToDateException extends BaseIsSerializableException implements IJSONFriendlyObject<CacheStillUpToDateException>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6308030155112994243L;
	
	
	/**
	 * id cache yang masih sync
	 **/
	private String cacheId ; 
	
	public CacheStillUpToDateException() {
		super();
	}
	
	public CacheStillUpToDateException(String friendlyMessage , String cacheId){
		super(friendlyMessage);
		setMessage(friendlyMessage);
		setCacheId(cacheId);
	}
	
	/**
	 * id cache yang masih sync
	 **/
	public void setCacheId(String cacheId) {
		this.cacheId = cacheId;
	}
	/**
	 * id cache yang masih sync
	 **/
	public String getCacheId() {
		return cacheId;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("message", getMessage());
		jsonContainerData.put("fullStackTrace", getFullStackTrace());
		jsonContainerData.put("cacheId", getCacheId());
	}
	
	@Override
	public CacheStillUpToDateException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		CacheStillUpToDateException retval = new CacheStillUpToDateException();
		retval.setFullStackTrace(jsonContainer.getAsString("fullStackTrace"));
		retval.setMessage(jsonContainer.getAsString("message"));
		retval.setCacheId(jsonContainer.getAsString("cacheId"));
		return retval;
	}
}
