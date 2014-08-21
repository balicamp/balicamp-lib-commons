/**
 * 
 */
package id.co.sigma.common.server.spreadsheet;

import id.co.sigma.common.data.InvalidSpreadSheetCellFormatException;
import id.co.sigma.common.data.SpreadSheetExceptionType;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;

/**
 * 
 * @author <a href="mailto:gede.wibawa@sigma.co.id">Agus Gede Adipartha Wibawa</a>
 * @since Sep 3, 2013 6:06:07 PM
 */
public abstract class DateSpreadSheetColumnDefinition<DATA> extends BaseSpreadSheetColumnDefinition<DATA, Date> {
	
	private static final SimpleDateFormat SDF = new SimpleDateFormat();
	
	private String dateFormat;
	
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public String getDateFormat() {
		return dateFormat;
	}

	@Override
	protected Date getValueFromCell(Cell cell) throws InvalidSpreadSheetCellFormatException {
		Date date = null;

		try {
			date = cell.getDateCellValue();
		} catch (Exception e) {
			
			String cellValue = getCellValueAsString(cell);
			
			/*if ( cellValue!= null && cellValue.length()!=10){
				throw new InvalidSpreadSheetCellFormatException("Format tanggal maximal 10 karakter.");
			}*/
			
			if(cellValue != null) {
				cellValue = cellValue.replaceAll("\'|\"", "");
			}

			try {
				SDF.applyPattern(dateFormat);
				date = SDF.parse(cellValue);
			} catch (Exception e2) {
				throw new InvalidSpreadSheetCellFormatException(
						SpreadSheetExceptionType.DATE_EXCEPTION, 
						cellValue);
			}
			

		}

		return date;
	}


}
