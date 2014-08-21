package id.co.sigma.common.server.spreadsheet;

import id.co.sigma.common.data.InvalidSpreadSheetCellFormatException;
import id.co.sigma.common.data.InvalidSpreadSheetCellFormatExceptionWrapper;
import id.co.sigma.common.data.InvalidSpreadSheetRowException;
import id.co.sigma.common.exception.BadBulkUploadDataException;
import id.co.sigma.common.exception.DataDuplicationOnUploadedDataException;
import id.co.sigma.common.exception.DataValidationException;
import id.co.sigma.common.server.service.AbstractService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * <ol>
 * <li>dari POJO -&gt; spread sheet </li>
 * <li>dari spreadsheet -&gt;  POJO</li>
 * </ol>
 * @author Dode
 **/
public abstract class BaseSpreadsheetFileUtil<DATA> extends AbstractService implements ISpreadsheetFileUtil<DATA> , InitializingBean {
	
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseSpreadsheetFileUtil.class); 
	
	
	protected HSSFSheet targetSheet ; 
	
	
	protected int startingRowNumber ;
	
	@Resource(name="bulk.excel.date.format")
	private String bulkExcelDateFormat;
	
	@Autowired
	protected ISpreadsheetFileUtilManager spreadsheetFileUtilManager ; 
	
	
	public BaseSpreadsheetFileUtil() {}
	
	public BaseSpreadsheetFileUtil (HSSFSheet targetSheet ){
		this.targetSheet = targetSheet ;	
	}
	
	protected  abstract DATA instantiateSampleObject () ;  
	
	@Override
	public UploadedDataContainer<DATA> validateDataAndSplitData(
			List<DATA> uploadedData) throws BadBulkUploadDataException , DataDuplicationOnUploadedDataException, Exception {

		UploadedDataContainer<DATA> retval = new UploadedDataContainer<DATA>();
		
		if ( uploadedData!= null && !uploadedData.isEmpty()) {
			removeInvalidUploadedData(uploadedData);
			String [] msgs = checkForDataIntegrity(uploadedData); 
			if ( msgs!= null && msgs.length> 0 ){
				BadBulkUploadDataException baex = new BadBulkUploadDataException(); 
				baex.setMessage("gagal dalam proses validasi data terupload. Silakan cek pada detail");
				baex.setInvalidDataMessages(msgs);
				throw baex ; 
			}
			// pecah menjadi new data vs updated data
			ArrayList<DATA> dtBaru = new ArrayList<DATA>(); 
			ArrayList<DATA> dtUpdated = new ArrayList<DATA>();
			retval.setUploadedNewData(dtBaru);
			retval.setUpladedUpdatedExistingData(dtUpdated);
			for ( DATA scn : uploadedData ){
				if ( isNewlyAddedData(scn))
					dtBaru.add(scn); 
				else
					dtUpdated.add(scn); 
			}
			
			
		}
		return retval;
	}
	
	
	/**
	 * membaca dari excel dan mengembalikan list of POJO
	 * @param targetSheetToRead reference ke sheet yang perlu di baca
	 * @param startReadRowNumber data di baca dari row ke berapa. biasanya row 0 adalah header dari data. biasanya di mulai dari 1
	 **/
	public List<DATA> readData (HSSFSheet targetSheetToRead  , int startReadRowNumber ) {
		this.targetSheet = targetSheetToRead ; 
		
		this.startingRowNumber = startReadRowNumber ; 
		List<DATA> uploadedData = readData() ;
		
		
		
		
		
		
		return uploadedData ; 
		//return uploadedData ; 
		
	}

	
	
	/**
	 * ini untuk mengecek data posisi nya baru atau updated. 
	 * @return true = data baru di add
	 */
	protected abstract boolean isNewlyAddedData ( DATA uploadedData ) ;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DATA> readData () {
		 BaseSpreadSheetColumnDefinition<DATA, ?>[]  defs = getColumnDefinitions() ;
		 
		 for(BaseSpreadSheetColumnDefinition d : defs) {
			 if(d instanceof DateSpreadSheetColumnDefinition) {
				 ( (DateSpreadSheetColumnDefinition) d ).setDateFormat(bulkExcelDateFormat);
			 }
		 }
		 
		 this.targetSheet.iterator().next();
		 Row header = this.targetSheet.iterator().next();
		 
		 Iterator<Cell> cells = header.cellIterator();
		 
		 int index = 0;
		 while(cells.hasNext()) {
			 
			 Cell cell = cells.next();
			 String colHeader = cell.getStringCellValue();
			 
			 BaseSpreadSheetColumnDefinition<DATA, ?> cols = defs[index];
			 
			 if ( !cols.getColumnHeader().equals(colHeader)) {
				 throw new DataValidationException("Ada posisi kolom yang salah pada file excel yang diupload.\n"
				 		+ "Mohon bandingkan dengan excel file yang didownload.");
			 }
			 
			 index++;
		 }		 
		 
		 ArrayList<DATA> retval = new ArrayList<DATA>();
		 if ( defs != null ){
			 
			List<InvalidSpreadSheetRowException> allRowException = 
					new ArrayList<InvalidSpreadSheetRowException>();
			  
			 int i= 0 ;
			 
			 Iterator<Row> itrs =  this.targetSheet.iterator(); 
			 while ( itrs.hasNext()){
				 Row row =  itrs.next();
				 try{
					 
					 if ( i<startingRowNumber-1)
						 continue ;
					 DATA sample = instantiateSampleObject() ; 
					
					 
					 ArrayList<InvalidSpreadSheetCellFormatException> listException = new ArrayList<
							 InvalidSpreadSheetCellFormatException>();
					 for (BaseSpreadSheetColumnDefinition def : defs ){
						 
						 Cell cellToRead =  row.getCell(def.getColumnIndex() , Row.RETURN_BLANK_AS_NULL); 
						 
						 if ( cellToRead== null)
							 continue ; 
						 try {
							 def.setValue( sample ,  def.getValueFromCell(cellToRead));
							 
							 
						} catch (InvalidSpreadSheetCellFormatException invalidCellException) {
							
							invalidCellException.setColumn(cellToRead.getColumnIndex());
							invalidCellException.setRow(i);
							invalidCellException.setBusinessColumnName(def.getColumnHeader());
							
							listException.add(invalidCellException);
							
						} catch (Exception e) {
							throw e;
						}
						 
					 }
					 
					 
					 
					 if(!listException.isEmpty()) {

						 InvalidSpreadSheetRowException rowException =
								 new InvalidSpreadSheetRowException(listException, i);
						 allRowException.add(rowException);
					 }
						 
					 
					if ( !checkIsEmptyUploadedData(sample))
						 retval.add(sample);
				 }
				 finally{
					 i++ ; 
				 }
			 }
			 
			 
			 if(!allRowException.isEmpty()) {
				 throw new InvalidSpreadSheetCellFormatExceptionWrapper(allRowException);
			 }
			 
		 }
		 
		 return retval ; 
		
		
	}
	
	
	
	
	
	
	/**
	 * kosongkan data yang  dianggap tidak beres. misalnya kalau kode data kosong maka data tidak di sertakan
	 */
	protected void removeInvalidUploadedData (List<DATA> uploadedData ) {
	}
	
	//private final FieldLengthValidator fieldLengthValidator = new FieldLengthValidator();
	
	
	/**
	 * mengecek data integrity.ini mengembalikan apa-apa yang di langgar oleh data . ini bergantung pada object masing-masing.<br/> 
	 * pemeriksaan nya misal nya : 
	 * <ol>
	 * <li>tidak ada duplikasi</li>
	 * <li>reference ke parent data musti valid</li>
	 * <li>dan aturan lain nya</li>
	 * </ol>
	 */
	protected String[]  checkForDataIntegrity ( List<DATA> upladedDatas ){
		
	/*	List<String> exceptionMessages = new ArrayList<String>();
		
		int rowNumber = 1;
		
		for(DATA d : upladedDatas) {
			try {
				fieldLengthValidator.validate(d, rowNumber);
			} catch (DataValidationException e) {
				exceptionMessages.add(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			rowNumber++;
		}
		
		if(!exceptionMessages.isEmpty()) {
			String[] expArray = new String[exceptionMessages.size()];
			exceptionMessages.toArray(expArray);
			
			return expArray;
		}
		
		return new String[] {}; */
		return null;
	}
	
	/**
	 * render data ke dalam spreadsheet. data di kirimkan sebagai List of Object
	 * @param dataToWrite data yang akan di tulis ke excel
	 * @param targetSheetToWrite kemana data di tulis
	 * @param startWriteRowNumber di mulai di tulis dari index berapa
	 **/
	public void renderData (List<DATA> dataToWrite , HSSFSheet targetSheetToWrite  , int startWriteRowNumber) {
		this.startingRowNumber = startWriteRowNumber ;
		
		this.targetSheet = targetSheetToWrite ; 
		renderData(dataToWrite); 
		
	}
	
	
	public void renderData (List<DATA> dataToWrite) {
		
		
		 BaseSpreadSheetColumnDefinition<DATA, ?>[]  defs = getColumnDefinitions() ; 
		 if ( defs != null ){
			 int i = startingRowNumber ;
			 //buat header kolom
			 Row row =  targetSheet.createRow(i);
			/* CellStyle rowStyle = row.getRowStyle();
			 if (rowStyle != null) {
				 rowStyle.setAlignment(CellStyle.ALIGN_CENTER); 
			 }*/
			 for (BaseSpreadSheetColumnDefinition<DATA, ?> scn : defs) {
				 Cell cell =  row.createCell(scn.getColumnIndex());
				 cell.setCellValue(scn.getColumnHeader());
			 }
			 
			 for ( DATA data :   dataToWrite){
				 row =  targetSheet.createRow(++i);
				 for (  BaseSpreadSheetColumnDefinition<DATA, ?> scn : defs){
					 Object obj =  scn.getValue(data);
					 if ( obj== null){
						 continue ;
					 }
					 Cell cell =  row.createCell(scn.getColumnIndex());
					 
					 CellStyle cellStyle = scn.getCellStyle(cell);
					 if(cellStyle != null) {
						 cell.setCellStyle(cellStyle);
					 }
					 
					
					 if ( obj instanceof Number){
						 cell.setCellValue( ((Number)obj).doubleValue()); 
					 }else if (obj instanceof Boolean){
						 cell.setCellValue((Boolean) obj);
					 }else if ( obj instanceof String){
						 cell.setCellValue((String) obj);
					 } else if ( obj instanceof Date) {
						 cell.setCellValue((Date) obj);
					 }
				 } 
			 }
			 
			 
			 
		 }
		
	}

	
	protected abstract BaseSpreadSheetColumnDefinition<DATA, ?>[] getColumnDefinitions() ; 
	
	public void setTargetSheet(HSSFSheet targetSheet) {
		this.targetSheet = targetSheet;
	}
	
	public HSSFSheet getTargetSheet() {
		return targetSheet;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.spreadsheetFileUtilManager.register(this); 
		
	}
	
	public void setStartingRowNumber(int startingRowNumber) {
		this.startingRowNumber = startingRowNumber;
	}
	
	public int getStartingRowNumber() {
		return startingRowNumber;
	}
	
	
	
	/**
	 * ini untuk memeriksa apakah ini blank data. ini spesifik per modul. kalau data kosong sama sekali maka di abaikan dari data upload. ini spesifik per modul
	 */
	protected boolean checkIsEmptyUploadedData (DATA scannedData)  {
		return false ; 
	}
	

}
