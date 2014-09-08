package id.co.sigma.common.server.service.datagrid;


import id.co.sigma.common.data.PagedResultHolder;



/**
 * wrapper row data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class SimplifiedGridDataWrapper extends PagedResultHolder<SimplifiedGridDataRowWrapper> {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7404116887505026176L;
	/**
	 * tipe data dari column
	 **/
	private Class<?> [] columnDataTypes ; 
	
	
	
	
	
	 
	
	
	
	/**
	 * tipe data dari column
	 **/
	public void setColumnDataTypes(Class<?>[] columnDataTypes) {
		this.columnDataTypes = columnDataTypes;
	}
	/**
	 * tipe data dari column
	 **/
	public Class<?>[] getColumnDataTypes() {
		return columnDataTypes;
	}

}
