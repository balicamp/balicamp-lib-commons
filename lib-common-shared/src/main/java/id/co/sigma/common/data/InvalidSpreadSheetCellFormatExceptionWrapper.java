package id.co.sigma.common.data;

import java.util.List;

public class InvalidSpreadSheetCellFormatExceptionWrapper extends RuntimeException  {
	private static final long serialVersionUID = -4290568167081717116L;
	
	
	private List<InvalidSpreadSheetRowException> exceptions;


	public List<InvalidSpreadSheetRowException> getExceptions() {
		return exceptions;
	}


	public void setExceptions(List<InvalidSpreadSheetRowException> exceptions) {
		this.exceptions = exceptions;
	}


	public InvalidSpreadSheetCellFormatExceptionWrapper(
			List<InvalidSpreadSheetRowException> exceptions) {
		super();
		this.exceptions = exceptions;
	}
	
	
	

}
