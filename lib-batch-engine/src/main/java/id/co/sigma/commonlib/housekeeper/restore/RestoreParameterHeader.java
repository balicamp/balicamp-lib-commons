package id.co.sigma.commonlib.housekeeper.restore;

import java.io.Serializable;


/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class RestoreParameterHeader implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3390040323759037561L;




	/**
	 * key untuk menaruh current restore parameter
	 **/
	public static final String CURRENT_RESTORE_PARAM_KEY ="currentRestoreParam" ; 
	
	
	
	
	/**
	 * parameter untuk restore. ini untuk menaruh RestoreParameterHeader ke dalam job exception
	 **/
	public static final String RESTORE_PARAM_KEY ="restoreParam" ;
	
	
	
	/**
	 * curent index dalam proses restore. ini untuk decider
	 **/
	public static final String CURRENT_RESTORE_PARAM_CURRENT_INDEX_KEY ="currentRestoreParamIndex" ;
	
	
	
	
	/**
	 * status proses restore done
	 **/
	public static final String ALL_RESTORE_DONE_STATUS_CODE ="done";
	
	
	/**
	 * ini kalau pekerjaan harus di lakukan lagi. jadinya ada yang belum selesai di load
	 **/
	public static final String ALL_RESTORE_NOT_DONE_STATUS_CODE ="repeat";
	
	/**
	 * details dari restore parameter
	 **/
	private RestoreParameterDetail [] details; 
	
	/**
	 * details dari restore parameter
	 **/
	public RestoreParameterDetail[] getDetails() {
		return details;
	}
	/**
	 * details dari restore parameter
	 **/
	public void setDetails(RestoreParameterDetail[] details) {
		this.details = details;
	}

}
