package id.co.sigma.commonlib.importengine.data;



/**
 * wrapper untuk memindahkan data dari database staging ke database target
 **/
public class StagingDataToTargetDBDataWrapper {
	
	
	/**
	 * data dari pembacaan database staging
	 **/
	private Object [] fieldsData ; 
	
	/**
	 * nomor urutan data dalam row di database
	 **/
	private int rowNumber ;

	
	/**
	 * data dari pembacaan database staging
	 **/
	public Object[] getFieldsData() {
		return fieldsData;
	}
	/**
	 * data dari pembacaan database staging
	 **/
	public void setFieldsData(Object[] fieldsData) {
		this.fieldsData = fieldsData;
	}

	/**
	 * nomor urutan data dalam row di database
	 **/
	public int getRowNumber() {
		return rowNumber;
	}

	/**
	 * nomor urutan data dalam row di database
	 **/
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	} 

}
