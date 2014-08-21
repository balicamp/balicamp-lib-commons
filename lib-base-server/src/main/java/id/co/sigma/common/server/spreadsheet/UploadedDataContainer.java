package id.co.sigma.common.server.spreadsheet;

import java.util.List;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class UploadedDataContainer<DATA> {
	
	/**
	 * data yang category nya new data
	 * 
	 */
	private List<DATA> uploadedNewData;
	
	/**
	 * data yang category nya update
	 */
	private List<DATA> upladedUpdatedExistingData;
	/**
	 * data yang category nya new data
	 * 
	 */
	public List<DATA> getUploadedNewData() {
		return uploadedNewData;
	}
	/**
	 * data yang category nya new data
	 * 
	 */
	public void setUploadedNewData(List<DATA> uploadedNewData) {
		this.uploadedNewData = uploadedNewData;
	}
	/**
	 * data yang category nya update
	 */
	public void setUpladedUpdatedExistingData(
			List<DATA> upladedUpdatedExistingData) {
		this.upladedUpdatedExistingData = upladedUpdatedExistingData;
	}
	/**
	 * data yang category nya update
	 */
	public List<DATA> getUpladedUpdatedExistingData() {
		return upladedUpdatedExistingData;
	}

	
	
	
}
