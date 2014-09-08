/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.server.service.approval.impl;

import id.co.sigma.common.data.approval.CommonApprovalHeader;
import id.co.sigma.common.data.approval.ISimpleApprovableObject;
import id.co.sigma.common.data.approval.SimpleApprovalStatus;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.service.AbstractService;
import id.co.sigma.common.server.service.approval.SimpleApprovalService;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sutarsa
 */
public class SimpleApprovalServiceImpl extends  AbstractService implements  SimpleApprovalService{

    
    
    @Autowired
    private IGeneralPurposeDao generalPurposeDao ; 
    
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED )
    @Override
    public CommonApprovalHeader submitDataForApproval(ISimpleApprovableObject<?> dataToSubmit, String approvalRemark, String referenceNumber) throws Exception {
        
        
        String fqcn = dataToSubmit.getClass().getName(); 
        
        
        CommonApprovalHeader h = new CommonApprovalHeader(); 
        h.setRequestRemark(approvalRemark);
        h.setReferenceNumber(referenceNumber);
        h.setApprovalStatus(SimpleApprovalStatus.WAITING_APPROVAL);
        h.setRequestTime(new Date());
        h.setRequestorUserName(getCurrentUserName());
        h.setTargetObjectIdAsJSON(dataToSubmit.generatePrimaryKeyAsSimpleJSON());
        h.setTargteObjectFQCN(fqcn);
        generalPurposeDao.insert(h);
        dataToSubmit.setApprovalId(h.getId());
        dataToSubmit.setApprovalStatus(SimpleApprovalStatus.WAITING_APPROVAL);
        return h; 
                
        
    }

    @Override
    public void approve(BigInteger approvalHeaderid, String approvalRemark) throws Exception {
        CommonApprovalHeader h = generalPurposeDao.get(CommonApprovalHeader.class, approvalHeaderid);
        h.setApprovalStatus(SimpleApprovalStatus.APPROVED);
        h.setApproverUserName(getCurrentUserName());
        h.setApprovedTime(new Date());
        h.setApprovalRemark(approvalRemark);
        generalPurposeDao.update(h);
    }

    @Override
    public void reject(BigInteger approvalHeaderid, String rejectRemark) throws Exception {
         CommonApprovalHeader h = generalPurposeDao.get(CommonApprovalHeader.class, approvalHeaderid);
        h.setApprovalStatus(SimpleApprovalStatus.REJECTED);
        h.setApproverUserName(getCurrentUserName());
        h.setApprovedTime(new Date());
        h.setApprovalRemark(rejectRemark);
        generalPurposeDao.update(h);
    }
    
}
