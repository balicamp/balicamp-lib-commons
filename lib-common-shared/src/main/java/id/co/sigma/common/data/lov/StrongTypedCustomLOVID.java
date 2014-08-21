package id.co.sigma.common.data.lov;

import id.co.sigma.common.util.json.IJSONFriendlyObject;


/**
 * Memaksa agar LOV bisa strong typed. jadinya lookup enabled control bisa tidak perlu mengetik ID langsung dari LOV, namun tipe enum yang di ambil
 * @see CommonListOfValueID
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface StrongTypedCustomLOVID<T> extends IJSONFriendlyObject<T> {

	/**
	 * id LOV
	 **/
	public String getId(); 
	
	/**
	 * id group modul
	 **/
	public String getModulGroupId (); 
}
