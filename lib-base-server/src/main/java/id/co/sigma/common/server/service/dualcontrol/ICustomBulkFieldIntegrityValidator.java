package id.co.sigma.common.server.service.dualcontrol;

import id.co.sigma.common.exception.DataDuplicationOnUploadedDataException;

import java.util.List;

/**
 * Validasi untuk nilai field yg belum ada di database.
 * 
 * @author wayan
 *
 * @param <T>
 */
public interface ICustomBulkFieldIntegrityValidator<T> {
	
	public void validateFields(List<T> updatedData, List<T> newData)
	 throws DataDuplicationOnUploadedDataException, Exception;
	
	public Class<T> getHandledClass();
	
	public boolean isAfterClone();

}
