package id.co.sigma.common.server.spreadsheet;

import id.co.sigma.common.data.InvalidSpreadSheetCellFormatException;

import org.apache.poi.ss.usermodel.Cell;

public abstract class BooleanSpreadSheetColumnDefinition<DATA> extends BaseSpreadSheetColumnDefinition<DATA, Boolean> {

	@Override
	protected Boolean getValueFromCell(Cell cell) throws InvalidSpreadSheetCellFormatException {
		return cell.getBooleanCellValue();
	}
}
