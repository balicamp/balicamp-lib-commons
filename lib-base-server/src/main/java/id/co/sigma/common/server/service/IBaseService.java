package id.co.sigma.common.server.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

/**
 * base interface untuk service
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IBaseService {
	/**
	 * membaca IP dari current login
	 **/
	public String getCurrentUserIpAddress (); 
	/**
	 * current user yang login, ini ikut dengan spring security
	 **/
	public String getCurrentUserName () ;
	
	/**
	 * kode cabang dari user
	 */
	public String getCurrentBranchCode (); 
	
	
	/**
	 * insert object baru ke dalam database
	 * @param newData data yang perlu di insert
	 */
	@Transactional(readOnly=false)
	public <DATA extends Serializable> DATA insertNewData (DATA newData  ) throws Exception ;
	
	
	
	
	/**
	 * worker untuk update data. data musti di sediakan PK dari data
	 * @param updatedData data yang perlu di update
	 * @param pkFieldName nama field primary key. biasanya id
	 * @param modifiedField field yang di update
	 */
	@Transactional(readOnly=false)
	public <DATA extends Serializable> void updateData (DATA updatedData , String pkFieldName , String[] modifiedField ) throws Exception ;
	
	
	/**
	 * update data . versi ini menyertakan field-field yang tidak di update
	 * @param updatedData data yang di update
	 * @param pkFieldName PK column name
	 * @param excludedField field-field yang tidak di update
	 */
	@Transactional(readOnly=false)
	public <DATA extends Serializable> void updateDataWithExcludedFields (DATA updatedData , String pkFieldName , String[] excludedField ) throws Exception ;
	
}
