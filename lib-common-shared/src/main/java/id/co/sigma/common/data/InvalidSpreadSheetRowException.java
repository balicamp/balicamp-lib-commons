package id.co.sigma.common.data;

import java.util.List;

public class InvalidSpreadSheetRowException extends RuntimeException {
	private static final long serialVersionUID = -6300838243763409542L;
	
	private List<InvalidSpreadSheetCellFormatException> exceptionList;
	
	private int rowNumber;

	public InvalidSpreadSheetRowException(
			List<InvalidSpreadSheetCellFormatException> exceptionList,
			int rowNumber) {
		super();
		this.exceptionList = exceptionList;
		this.rowNumber = rowNumber;
	}

	public List<InvalidSpreadSheetCellFormatException> getExceptionList() {
		return exceptionList;
	}

	public void setExceptionList(
			List<InvalidSpreadSheetCellFormatException> exceptionList) {
		this.exceptionList = exceptionList;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	
}
