/**
 * 
 */
package id.co.sigma.common.server.spreadsheet;

import id.co.sigma.common.data.InvalidSpreadSheetCellFormatException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 
 * @author <a href="mailto:gede.wibawa@sigma.co.id">Agus Gede Adipartha Wibawa</a>
 * @since Oct 7, 2013 6:00:59 PM
 */
public abstract class BaseSpreadSheetColumnDefinition<DATA, CELLTYPE> {
	
	
	
	
	/**
	 * index data yang di render pada saat ini . ini di-inject oleh writer
	 */
	private Integer currentRowIndex ; 
	
	
	
	/**
	 * data akan di render pada index berapa pada spreadsheet
	 */
	private Integer actualCellRowIndex ; 
	
	
	

	public abstract CELLTYPE getValue (DATA data ) ; 


	public abstract void setValue ( DATA targetData, CELLTYPE value );

	/**
	 * data data column index
	 **/
	public abstract int getColumnIndex () ; 


	/**
	 * 
	 **/
	protected abstract CELLTYPE getValueFromCell ( Cell cell  ) throws InvalidSpreadSheetCellFormatException;

	/**
	 * header untuk kolom
	 * @return
	 */
	public abstract String getColumnHeader();

	public CellStyle getCellStyle(Cell cell) {
		return null;
	}

	public String getCellValueAsString(Cell cell) {
		
		String value = null;
		
		try {
			value = cell.getStringCellValue();
		} catch (Exception e) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				double d = cell.getNumericCellValue();
				value = d+"";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = new Boolean(cell.getBooleanCellValue()).toString();
				break;
			}

		}
		
		return value;
	}
	
	/**
	 * index data yang di render pada saat ini . ini di-inject oleh writer
	 */
	public void setCurrentRowIndex(Integer currentRowIndex) {
		this.currentRowIndex = currentRowIndex;
	}
	/**
	 * index data yang di render pada saat ini . ini di-inject oleh writer
	 */
	public Integer getCurrentRowIndex() {
		return currentRowIndex;
	}
	
	/**
	 * data akan di render pada index berapa pada spreadsheet
	 */
	public Integer getActualCellRowIndex() {
		return actualCellRowIndex;
	}
	/**
	 * data akan di render pada index berapa pada spreadsheet
	 */
	public void setActualCellRowIndex(Integer actualCellRowIndex) {
		this.actualCellRowIndex = actualCellRowIndex;
	}

}
