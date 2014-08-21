package id.co.sigma.common.server.service.dualcontrol;

import java.util.List;

import id.co.sigma.common.data.app.DualControlEnabledData;
import id.co.sigma.common.exception.DataDuplicationOnUploadedDataException;

/**
 * vlaidator untuk data bulk. ini untuk memastikan tidak ada duplikasi data
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ICustomBulkDualControlValidator<DATA extends DualControlEnabledData<?, ?>> {
	
	
	
	/**
	 * @exception DataDuplicationOnUploadedDataException kalau ada duplikasi dalam data yang di modifikasi
	 */
	void validatedBulkData (List<DATA> newlyCreatedData  , List<DATA> modiefiedData, boolean isUpload) 
			throws DataDuplicationOnUploadedDataException,Exception ;

	
	
	/**
	 * class yang di valdasi
	 */
	Class<DATA> getHandledClass () ; 

	
	/**
	 * Nama kolom primary key (JPA) untuk table temporary.
	 * 
	 * @return 
	 */
	public abstract String getTmpTablePkColumnName();
}
