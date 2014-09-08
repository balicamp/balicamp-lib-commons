/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.server.service.approval;

import id.co.sigma.common.data.approval.CommonApprovalHeader;
import id.co.sigma.common.data.approval.ISimpleApprovableObject;
import java.math.BigInteger;

/**
 * simple approval interface
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface SimpleApprovalService {
    
    
    
    /**
     * submit data untuk proses approval. ini di desain untuk di panggil sebelum data <i>dataToSubmit</i> , di simpan ke dalam database 
     * @param  dataToSubmit  data yang akan di approve 
     * @param approvalRequestRemark catatan dari request persetujuan data. kenapa data ini di submit. untuk keperluan apa
     * 
    */
    public CommonApprovalHeader submitDataForApproval (ISimpleApprovableObject<?> dataToSubmit, String approvalRequestRemark , String referenceNumber )  throws Exception ; 
    
    
    
    
    /**
     * approve item. ini juga di desain untuk di panggil sebelum data di simpan ke database
    */
    public void approve ( BigInteger approvalHeaderid , String approvalRemark) throws Exception ;
    
    
    /**
     * reject data
     */
    public void reject ( BigInteger approvalHeaderid , String rejectRemark) throws Exception ;
    
    
}
