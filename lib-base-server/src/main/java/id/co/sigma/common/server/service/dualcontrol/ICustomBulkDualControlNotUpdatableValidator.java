package id.co.sigma.common.server.service.dualcontrol;

import id.co.sigma.common.exception.DataDuplicationOnUploadedDataException;



/**
 * interface untuk validasi field yang tidak boleh di update
 */
public interface ICustomBulkDualControlNotUpdatableValidator<T> {
	
	
	
	/**
	 * pembandingan antara field di database vs field data yang di upload
	 * @param dbData data di database
	 * @param uploadedData data yang di upload
	 */
	public void validateNotUpdatableField(T dbData ,T uploadedData) throws DataDuplicationOnUploadedDataException, Exception;
	
	
	/**
	 * class yang di proses
	 * 
	 */
	public Class<T> getHandledClass();
}
