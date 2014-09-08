package id.co.sigma.commonlib.base;

import java.sql.ResultSetMetaData;

/**
 *interface object yang menerima metata
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IRecieveJDBCMetadataField {
	
	
	
	
	/**
	 * ini dalam kasus metadata di terima. yang berkepentingan harus melakukan sesuatu dengan metadata ini. misalnya : membangun definisi output dsb
	 **/
	public void onRecieveMetadata(ResultSetMetaData resultsetMetata ) throws Exception; 

}
