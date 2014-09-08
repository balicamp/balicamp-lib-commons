package id.co.sigma.commonlib.housekeeper.restore;

import java.io.File;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ZipDataExtractor {

	
	
	/**
	 * extract zip file dan membaca metadata dari file
	 **/
	public RestoreParameterHeader extractAndReadMetadata ( File zipFile, File extractDestinationFolder ) throws Exception ;  
	
	

}
