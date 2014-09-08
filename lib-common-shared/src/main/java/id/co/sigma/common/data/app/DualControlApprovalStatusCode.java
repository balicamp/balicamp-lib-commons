package id.co.sigma.common.data.app;

import com.google.gwt.user.client.rpc.IsSerializable;





/**
 * approval status code untuk dual control data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public enum DualControlApprovalStatusCode implements IsSerializable{
	
	
	
	/**
	 * status draft edit
	 **/
	DRAFT_EDIT , 
	
	/**
	 * di submit untuk di hapus
	 **/
	WAITING_APPROVE_DELETE , 
	/**
	 * di submit untuk add. jadi existing belum data data
	 **/
	WAITING_APPROVE_CREATE ,

	/**
	 * di submit untuk di update
	 **/
	WAITING_APPROVE_UPDATE ,
	
	
	
	/**
	 * approve sekaligus banyak data. ini dari proses upload
	 */
	WAITING_APPROVE_BULK , 
	
	/**
	 * ini kasus nya create data di reject
	 **/
	REJECTED_CREATE_DATA , 
	/**
	 * ini kasus nya kalau update di tolak
	 **/
	REJECTED_UPDATE_DATA , 
	/**
	 * ini kalau proses delete data di tolak
	 **/
	REJECTED_DELETE_DATA , 
	
	
	/**
	 * tipe bulk approval. proses di tolak
	 */
	REJECTED_BULK_DATA , 
	
	
	/**
	 * menunggu EOD mentrigger proses delete
	 **/
	WAITING_EOD_DELETE , 
	/**
	 * menunggu EOD untuk proses insert
	 **/
	WAITING_EOD_CREATE ,
	/**
	 * menunggu EOD untuk proses update
	 **/
	WAITING_EOD_UPDATE ,
	
	/**
	 * data applied
	 **/
	APPLIED;
	
	
	
	
	
	/**
	 * generate data dari raw string
	 **/
	public static DualControlApprovalStatusCode generateFromRawString (String dataAsString) {
		if ( dataAsString ==null)
			return null ; 
		for ( DualControlApprovalStatusCode scn : DualControlApprovalStatusCode.values()){
			if ( scn.toString().equals(dataAsString))
				return scn ; 
		}
		return null ; 
	}
	

}
