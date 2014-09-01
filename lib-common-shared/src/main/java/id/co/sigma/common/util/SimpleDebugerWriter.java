package id.co.sigma.common.util;



/**
 * 
 * 
 * debug + error writer, cross antara server vs client
 **/
public interface SimpleDebugerWriter {

	/**
	 *  write debug
	 **/
	public void debug( String message );
	
	
	/**
	 *  write error
	 **/
	public void error( String message );
	
	
}
