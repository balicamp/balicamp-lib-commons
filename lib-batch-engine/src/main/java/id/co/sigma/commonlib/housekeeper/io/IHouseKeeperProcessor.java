package id.co.sigma.commonlib.housekeeper.io;

import id.co.sigma.commonlib.housekeeper.HouseKeeperJobParameter;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IHouseKeeperProcessor {
	
	
	
	
	/**
	 * erase statement
	 **/
	public String getWhereStatement() ;
	
	
	
	
	
	
	/**
	 * tipe data untuk proses penghapusan data
	 **/
	public Object[] getEraseParameterDataTypes() ; 
	
	/**
	 * tipe tipe data parameter
	 **/
	public int[] getSqlDataTypeKey () ; 
	
	/**
	 * reset erase constructor
	 **/
	public void resetDeleteConstructor () ;
	
	
	
	/**
	 * house keeper parameter
	 **/
	public HouseKeeperJobParameter getHouseKeeperJobParameter(); 

}
