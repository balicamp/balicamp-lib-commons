package id.co.sigma.common.util.json;

/**
 * container array of json
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface ParsedJSONArrayContainer {
	
	/**
	 * mengambil data array dengan index tertentu
	 **/
	public ParsedJSONContainer get(int index);
	
	
	
	
	/**
	 * membaca object, as string
	 **/
	public String getAsString (int index ) ; 
	
	
	
	
	/**
	 * membaca data as double
	 **/
	public Double getAsNumber (int index) ;
	
	
	/**
	 * membaca data as boolean
	 **/
	public Boolean getAsBoolean (int index) ;
	
	/**
	 * berapa size dari array json
	 **/
	public int length (); 
	
	
	
	
	/**
	 * set FQCN dari data
	 **/
	public void setDataTypeFQCN(String dataTypeFQCN); 
	
	
	/**
	 * tipe data dalam array
	 **/
	public String getDataTypeFQCN(); 

	
	
	/**
	 * memasukan item ke dalam json array 
	 * @param jsonData refernce json object yang di masukan ke dalam container
	 */
	public void appendToArray ( ParsedJSONContainer  jsonData ) ;
	
	
	
	/**
	 * membaca representasi json dari data	
	 */
	public String getJSONString () ; 
	
}
