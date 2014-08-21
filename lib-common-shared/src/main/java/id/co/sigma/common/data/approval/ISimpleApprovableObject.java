/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.data.approval;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import java.io.Serializable;

/**
 *
 * interface object yang approvable
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ISimpleApprovableObject<T> extends  IJSONFriendlyObject<T>, Serializable{
    
    
    /**
     * membuat object key as simple json. ini untuk di simpan dalam table master approval
     */
    public String generatePrimaryKeyAsSimpleJSON () ; 
    
    
    /**
     * konversi balik, dari JSON di konversikan menjadi object. ini dipergunakan untuk proses select
     * @param  json  json dari database, ini yang di konversikan menjadi object
     */
    public Serializable generatePrimaryKeyFromJSONString ( String json);
    
    
    /**
     * di rekomendasikan di setiap table item yang approvable, di sertakan ID dari approval. ID dari approval refer ke object CommonApprovalHeader
    */
    public void setApprovalId(Long approvalId ) ; 
    
    
    /**
     * menaruh status approval ke dalam POJO, ini di trigger pada saat anda memanggil simple approval. di sarankan anda menaruh approval status dalam tbale anda
     * @param approvalStatus approval status dari data
     **/
    public void setApprovalStatus (SimpleApprovalStatus approvalStatus) ; 
    
}
