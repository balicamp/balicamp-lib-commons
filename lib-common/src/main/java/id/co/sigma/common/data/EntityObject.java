package id.co.sigma.common.data;

import java.io.Serializable;



/**
 * entity object
 **/
public abstract class EntityObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5701187593423680262L;

	@Override
	public abstract String toString();
	
	@Override
	public abstract boolean equals(Object comparedOBject);
	
	@Override
	public abstract int hashCode() ;

}
