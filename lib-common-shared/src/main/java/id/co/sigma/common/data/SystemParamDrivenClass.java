package id.co.sigma.common.data;

import id.co.sigma.common.util.json.IJSONFriendlyObject;



/**
 * ini marker untuk class yang di load dari system parameter
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @param <ENTITY> diri nya sendiri. ini untuk di pakai dalam ijson friendly object
 * @param <D> enum untuk SimpleSytemParameterDrivenValue
 */

public interface SystemParamDrivenClass<ENTITY ,  D extends SimpleSytemParameterDrivenValue<?>> extends IJSONFriendlyObject<ENTITY> {

	/**
	 * enum class. ini untuk menyiapkan attribute lain untuk proses data ke system parameter
	 */
	public D instantiateProvider () ; 
	
	
	
	/**
	 * flag apakah ada data yang menunggu approval
	 */
	public boolean isHaveWaitingApprovalData () ; 
	
	
	
	/**
	 * pasangan {@link #isHaveWaitingApprovalData()} untuk memflag data ada waiting approval atau tidak
	 */
	public void setHaveWaitingApprovalData (boolean haveWaitingAppr) ;
	
}
