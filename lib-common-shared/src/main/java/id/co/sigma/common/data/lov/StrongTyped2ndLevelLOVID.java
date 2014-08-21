package id.co.sigma.common.data.lov;

import id.co.sigma.common.util.json.IJSONFriendlyObject;

/**
 * ini interface(untuk enum), Lookup value level 2. setipe dengan {@link StrongTypedCustomLOVID}
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public interface StrongTyped2ndLevelLOVID<T> extends IJSONFriendlyObject<T> {
	
	/**
	 * id LOV
	 **/
	public String getId(); 
	
	/**
	 * id group modul
	 **/
	public String getModulGroupId (); 

}
