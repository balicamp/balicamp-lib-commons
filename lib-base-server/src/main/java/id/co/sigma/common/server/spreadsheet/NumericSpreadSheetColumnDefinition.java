/**
 * 
 */
package id.co.sigma.common.server.spreadsheet;

import id.co.sigma.common.data.InvalidSpreadSheetCellFormatException;
import id.co.sigma.common.data.SpreadSheetExceptionType;

import org.apache.poi.ss.usermodel.Cell;

/**
 * 
 * @author <a href="mailto:gede.wibawa@sigma.co.id">Agus Gede Adipartha Wibawa</a>
 * @since Sep 3, 2013 6:03:50 PM
 */
public abstract class NumericSpreadSheetColumnDefinition<DATA> extends BaseSpreadSheetColumnDefinition<DATA, Number> {

	@Override
	protected Number getValueFromCell(Cell cell) throws InvalidSpreadSheetCellFormatException {
		double val = 0 ;
		
		try {
			val = cell.getNumericCellValue();
		} catch (Exception e) {
			
			String a = null;
			
			try {
				a =getCellValueAsString(cell);
				val = new Double(a);
			} catch (Exception e2) {
				throw new InvalidSpreadSheetCellFormatException(SpreadSheetExceptionType.NUMERIC_EXCEPTION, 
						a);
			}

		}

		return val;
	}
}
