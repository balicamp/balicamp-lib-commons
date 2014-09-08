package id.co.sigma.common.server.service.datagrid;




/**
 * wrapper data row
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a> 
 **/
public class SimplifiedGridDataRowWrapper {
	
	/**
	 * ID data di string kan
	 **/
	private String idAsString ;
	
	
	/**
	 * value per column 
	 **/
	private Object[] rowData ;
	
	/**
	 * ID data di string kan
	 **/
	public String getIdAsString() {
		return idAsString;
	}
	
	/**
	 * ID data di string kan
	 **/
	public void setIdAsString(String idAsString) {
		this.idAsString = idAsString;
	}
	
	/**
	 * value per column 
	 **/
	public void setRowData(Object[] rowData) {
		this.rowData = rowData;
	}
	/**
	 * value per column 
	 **/
	public Object[] getRowData() {
		return rowData;
	}
}
