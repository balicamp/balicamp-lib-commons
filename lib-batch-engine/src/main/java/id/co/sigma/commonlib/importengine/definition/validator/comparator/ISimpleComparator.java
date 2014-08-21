package id.co.sigma.commonlib.importengine.definition.validator.comparator;

import id.co.sigma.commonlib.importengine.definition.validator.CompareResult;



/**
 * interface comparator
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface ISimpleComparator<DATA> {
	
	
	
	
	
	public void setComparatorData (DATA data) ; 
	
	/**
	 * membandingkan 2 data
	 * @param dataToCompare data yang akan di compare. di compare vs data dalam {@link #setComparatorData(Object)}
	 **/
	public CompareResult compare(DATA dataToCompare); 

}
