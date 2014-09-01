package id.co.sigma.security.server.service.impl;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleQueryFilterOperator;
import id.co.sigma.common.security.domain.Branch;
import id.co.sigma.common.security.domain.BranchAssignment;
import id.co.sigma.common.security.dto.BranchDTO;
import id.co.sigma.security.server.dao.impl.BranchDaoImpl;
import id.co.sigma.security.server.service.IBranchService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Branch Service
 * @author I Gede Mahendra
 * @since Jan 30, 2013, 4:19:55 PM
 * @version $Id
 */
@Service
public class BranchServiceImpl implements IBranchService{

	@Autowired
	private BranchDaoImpl branchDao;	
	
	@Transactional(readOnly=true)
	@Override
	public PagedResultHolder<BranchDTO> getUserByParameter(SimpleQueryFilter[] filter, int pagePosition, int pageSize) throws Exception {
		Integer count = branchDao.countDataByFilters(filter);
		if(count == null){
			return null;
		}
		
		List<Branch> actualData = branchDao.getDataByFilters(filter, pagePosition, pageSize);
				
		PagedResultHolder<BranchDTO> retval = new PagedResultHolder<BranchDTO>();		
		List<BranchDTO> actualDataDTO = new ArrayList<BranchDTO>();
		for (Branch branch : actualData) {
			BranchDTO dto = new BranchDTO();
			dto.setId(branch.getId());
			dto.setIdParent(branch.getBranchParendId());		
			dto.setBranchParentCode(getBranchCode(branch.getBranchParendId()));
			dto.setBranchCode(branch.getBranchCode());
			dto.setBranchName(branch.getBranchName());
			dto.setBranchAddress(branch.getBranchAddress());
			dto.setBranchDescription(branch.getDescription());
			dto.setBranchStatus(branch.getDataStatusCode());
			actualDataDTO.add(dto);
		}
		
		retval.setHoldedData(actualDataDTO);
		retval.setPage(pagePosition);
		retval.setPageSize(pageSize);
		retval.setTotalData(count);
		retval.adjustPagination();
		return retval;
	}
	
	@Transactional(readOnly=false)
	@Override
	public void saveOrUpdate(Branch data) throws Exception {
		if(data.getId() == null){ //insert
			data.setCreatedOn(new Date());
			branchDao.insert(data);
		}else{ //update
			Branch temp = (Branch) branchDao.find(Branch.class, data.getId());
			temp.setBranchAddress(data.getBranchAddress());
			temp.setBranchCode(data.getBranchCode());
			temp.setBranchName(data.getBranchName());
			temp.setBranchParendId(data.getBranchParendId());
			temp.setDescription(data.getDescription());
			temp.setDataStatusCode(data.getDataStatusCode());
			temp.setModifiedOn(new Date());			
			branchDao.update(temp);
		}
	}
	
	@Transactional(readOnly=false)
	@Override
	public void remove(Long id) throws Exception {
		Branch result = (Branch) branchDao.find(Branch.class, id);
		branchDao.delete(result);
	}
	
	/**
	 * Get branch code for branch parent code
	 * @param id
	 * @return String - branch code
	 */
	private String getBranchCode(Long id){
		try {
			SimpleQueryFilter tempFilter = new SimpleQueryFilter();
			tempFilter.setField("id");
			tempFilter.setFilter(id);
			tempFilter.setOperator(SimpleQueryFilterOperator.equal);
			
			List<Branch> data = branchDao.getDataByFilters(new SimpleQueryFilter[]{tempFilter}, 0, 1);			
			return data.get(0).getBranchCode();
		} catch (Exception e) {			
			return "";
		}				
	}

	@Transactional(readOnly=true)
	@Override
	public List<BranchDTO> getBranchAssignmentByUserId(Long userId) throws Exception {
		List<BranchDTO> result = null;
		List<BranchAssignment> tempResult = branchDao.getBranchAssignmentByUserId(userId);
		if(!tempResult.isEmpty()){
			result = new ArrayList<BranchDTO>();
			for (BranchAssignment data : tempResult) {
				BranchDTO dto = new BranchDTO();				
				dto.setId(data.getBranch().getId());
				dto.setBranchCode(data.getBranch().getBranchCode());
				dto.setBranchName(data.getBranch().getBranchName());
				result.add(dto);
			}//end for
		}
		return result;
	}	
}