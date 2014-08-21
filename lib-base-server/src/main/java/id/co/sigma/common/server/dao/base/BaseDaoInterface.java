package id.co.sigma.common.server.dao.base;


import java.io.Serializable;
import java.util.Collection;


/**
 * base interface dao
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 **/
public interface BaseDaoInterface {
	/**
	 * hapus object dari dalam database
	 *@param object object(entity object) yang di hapus
	 **/
	public void delete (Serializable object) throws Exception; 
	
	/**
	 * menghapus bulk n data pada Collection dengan flush setiap x data 
	 * @param objects collection of data
	 * @throws Exception
	 */
	public void delete(Collection<? extends Serializable> objects )throws Exception;
	
	/**
	 * insert 1 data dalam database
	 * @param object object yang di insert
	 **/
	public void insert(Serializable object)throws Exception; 
	
	/**
	 * insert 1 data dalam database. isue flush command
	 * @param object object yang di insert
	 **/
	public void insertAndFlush(Serializable object)throws Exception;
	
	
	/**
	 * update 1 data
	 * @param object object yang 
	 **/
	public void update(Serializable object)throws Exception; 
	
	/**
	 * update multiple data
	 * @param object object yang 
	 **/
	public<DATA extends Serializable> void updates(Collection<DATA> object)throws Exception; 
	
	
	/**
	 * update 1 data
	 * @param object object yang 
	 **/
	public void updateAndFlush(Serializable object)throws Exception; 
	/**
	 * bulk insert data 
	 * @param objects list of object yang di insert ke dalam db
	 **/
	public<DATA extends Serializable> void inserts(Collection<DATA> objects )throws Exception; 

}
