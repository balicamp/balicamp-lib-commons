package id.co.sigma.common.data;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

public class InvalidSpreadSheetCellFormatException extends RuntimeException implements IJSONFriendlyObject<InvalidSpreadSheetCellFormatException> {
	private static final long serialVersionUID = -4150348205079322911L;
	
	
	private int row;
	
	private int column;
	
	private SpreadSheetExceptionType exceptionType;
	
	private Object exceptionValue;
	
	private String businessColumnName;
	
	public InvalidSpreadSheetCellFormatException() {
	}
	
	public InvalidSpreadSheetCellFormatException(String message) {
		super(message);
	}
	
	public InvalidSpreadSheetCellFormatException(SpreadSheetExceptionType exceptionType, Object exceptionValue) {
		super();
		this.exceptionType = exceptionType;
		this.exceptionValue = exceptionValue;
	}

	public InvalidSpreadSheetCellFormatException(int row, int column, SpreadSheetExceptionType exceptionType,
			String message) {
		super(message);
		this.row = row;
		this.column = column;
		this.exceptionType  = exceptionType;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}	

	public SpreadSheetExceptionType getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(SpreadSheetExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}
	
	public Object getExceptionValue() {
		return exceptionValue;
	}
	
	public void setExceptionValue(Object exceptionValue) {
		this.exceptionValue = exceptionValue;
	}

	public String getBusinessColumnName() {
		return businessColumnName;
	}

	public void setBusinessColumnName(String businessColumnName) {
		this.businessColumnName = businessColumnName;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("row", row);
		jsonContainerData.put("column", column);
		jsonContainerData.put("exceptionType", exceptionType.toString());
	}

	@Override
	public InvalidSpreadSheetCellFormatException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		
		int row = jsonContainer.getAsInteger("row");
		int column = jsonContainer.getAsInteger("column");
		String exceptionType = jsonContainer.getAsString("exceptionType");
		
		return new InvalidSpreadSheetCellFormatException(row, 
				column, 
				SpreadSheetExceptionType.instantiateFromString(exceptionType),
				null);
	}

}
