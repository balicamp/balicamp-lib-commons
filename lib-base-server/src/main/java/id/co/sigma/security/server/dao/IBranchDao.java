package id.co.sigma.security.server.dao;

import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.security.domain.Branch;
import id.co.sigma.common.security.domain.BranchAssignment;


import java.util.Collection;
import java.util.List;

/**
 * Interface Branch Dao
 * @author I Gede Mahendra
 * @since Jan 30, 2013, 3:44:06 PM
 * @version $Id
 */
public interface IBranchDao {
	
	/**
	 * get semua data branch
	 * @param filters filters untuk querynya
	 * @return list data user
	 * @throws Exception
	 */
	public List<Branch> getDataByFilters(SimpleQueryFilter[] filters, int pagePosition, int pageSize) throws Exception;
	
	/**
	 * count data branch
	 * @param filters
	 * @return
	 * @throws Exception
	 */
	public Integer countDataByFilters(SimpleQueryFilter[] filters) throws Exception;
	
	/**
	 * Mendapatkan branch assignment berdasarkan user id
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<BranchAssignment> getBranchAssignmentByUserId(Long userId) throws Exception;
	
	
	/**
	 * membaca data cabang dengan daftar kode cabang
	 * @param branchCodes list berisi daftar kode cabang yang perlu di baca
	 */
	public List<Branch> getBranchByCodes ( Collection<String> branchCodes ) ; 
	
	public List<Branch> getBranchComboboxByUserLogin(Long defaultBranch,
			SimpleQueryFilter[] additionalFilters, SimpleSortArgument[] sorts) throws Exception; 
}