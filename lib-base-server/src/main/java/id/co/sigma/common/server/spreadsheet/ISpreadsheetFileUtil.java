package id.co.sigma.common.server.spreadsheet;

import id.co.sigma.common.exception.BadBulkUploadDataException;
import id.co.sigma.common.exception.DataDuplicationOnUploadedDataException;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;



/**
 * interface converter POJO to spreadsheet dan sebaliknya
 * @author Dode
 **/
public interface ISpreadsheetFileUtil <DATA> {
	
	/**
	 * membaca dari excel dan mengembalikan list of POJO
	 * @param targetSheetToRead reference ke sheet yang perlu di baca
	 * @param startReadRowNumber data di baca dari row ke berapa. biasanya row 0 adalah header dari data. biasanya di mulai dari 1
	 **/
	public List<DATA> readData (HSSFSheet targetSheetToRead  , int startReadRowNumber ); 
	
	
	
	/**
	 * validasi dan pisah data menjadi : new data , updated data 
	 * full stack yang di lakukan di sini : 
	 * <ol>
	 * <li>validasi reference ke parent</li>
	 * <li>cek duplikasi data</li>
	 * <li>pecah data menjadi updated vs add new</li>
	 * </ol>
	 * @param uploadedData data yang di upload
	 * @exception BadBulkUploadDataException kalau ada yang violate validas
	 */
	
	public UploadedDataContainer<DATA> validateDataAndSplitData ( List<DATA > uploadedData) 
		throws BadBulkUploadDataException , DataDuplicationOnUploadedDataException, Exception; 
	
	
	
	
	/**
	 * render data ke dalam spreadsheet. data di kirimkan sebagai List of Object
	 * @param dataToWrite data yang akan di tulis ke excel
	 * @param targetSheetToWrite kemana data di tulis
	 * @param startWriteRowNumber di mulai di tulis dari index berapa
	 **/
	public void renderData (List<DATA> dataToWrite , HSSFSheet targetSheetToWrite  , int startWriteRowNumber)  ;
	
	
	/**
	 * class yang di render util class ini
	 **/
	public Class<DATA> getHandledClass () ;
	
	/**
	 * nama untuk excel file
	 * nama file hanya boleh angka, huruf dan garis bawah
	 * @return nama file
	 */
	public abstract String getFileName();

}
