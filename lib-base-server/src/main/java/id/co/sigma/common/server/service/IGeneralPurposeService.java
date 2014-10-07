package id.co.sigma.common.server.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

/**
 * service geenral purpose. wrapper transaction
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public interface IGeneralPurposeService extends IBaseService{
	
	/**
	 * hapus object dari dalam database
	 *@param object object(entity object) yang di hapus
	 **/
	@Transactional(readOnly=false )
	public void insert(Serializable object)throws Exception;
	
	/**
	 * hapus object dari dalam database
	 *@param object object(entity object) yang di hapus
	 **/
	@Transactional(readOnly=false )
	public void delete (Serializable object) throws Exception; 
	
	
	/**
	 * update object
	 */
	@Transactional(readOnly=false )
	public void update(Serializable object)throws Exception;
	
	
	/**
	 * hapus object dengan PK + class
	 * @param objectClass class dari object yang di hapus
	 * @param pk primary key dari data
	 */
	@Transactional
	public Integer delete (Class<?> objectClass, Serializable pk , String pkFieldName); 
	
	@Transactional(readOnly=false)
	public Serializable merge(Serializable object);

}
