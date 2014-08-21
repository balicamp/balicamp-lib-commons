package id.co.sigma.security.server.dao;

import id.co.sigma.common.security.domain.ApplicationMenuAssignment;
import id.co.sigma.common.security.domain.UserGroupAssignment;
import id.co.sigma.common.server.dao.IBaseDao;


import java.util.List;

/**
 * Interface untuk function assignment
 * @author I Gede Mahendra
 * @since Dec 13, 2012, 3:42:34 PM
 * @version $Id
 */
public interface IFunctionAssignmentDao extends IBaseDao {
	
	/**
	 * Delete function assignment berdasarkan group id
	 * @param groupId
	 * @throws Exception
	 */
	public void deleteFunctionByGroupId(Long groupId) throws Exception;
	
	
	
	/**
	 * menghapus data dari {@link UserGroupAssignment}. ini jadinya semua user yang mendapat group sesuai ID akan di hapus
	 */
	public void deleteUserGroupAssigmentByGroupId(Long groupId) throws Exception;
	
	
	/**
	 * add by dode
	 * get function id on function assignment by group id
	 * @param groupIds list of group id
	 * @return list of function id
	 * @throws Exception
	 */
	public List<Long> getFunctionIdByGroupId(List<Long> groupIds) throws Exception;
	
	
	/**
	 * membaca group assignment berdasarkan ID dari group
	 * @param groupId Id dari group yang hendak di baca
	 */
	public List<ApplicationMenuAssignment> getFunctionAssigmentByGroupId ( Long groupId  ) ; 
	
	/**
	 * add by dode
	 * get function assignment data by id and group id
	 * @param data list of function assignment
	 * @return list of function assignment
	 * @throws Exception
	 */
	public List<ApplicationMenuAssignment> getFunctionAssignmentByIdAndGroupId(List<ApplicationMenuAssignment> data) throws Exception;
	
	/**
	 * add by dode
	 * delete function assigment by function id
	 * @param functionId function id
	 * @throws Exception
	 */
	public void deleteFunctionAssigmentByFunctionId(Long functionId) throws Exception;
}