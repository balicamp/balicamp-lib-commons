package id.co.sigma.commonlib.housekeeper.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import id.co.sigma.commonlib.housekeeper.HouseKeepingConstant;
import id.co.sigma.commonlib.util.ZipCompressWriter;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ArchiverWriter extends ZipCompressWriter {

	
	/**
	 * ini folder. di mana akan di tulis
	 **/
	private String targetFolderAbsolutePath ; 
	
	
	
	
	@Override
	public void write(List<? extends File> items) throws Exception {
		File f = new File(  targetFolderAbsolutePath  + File.separator + HouseKeepingConstant.ARCHIVED_FOLDER_LIST_FILE_NAME_ON_ARCHIVE);
		ArrayList<File>  swap = new ArrayList<File>();  ; 
		swap.add( f);
		if ( items!= null && !items.isEmpty())
			swap.addAll(items); 
		
		
		
		super.write(swap);
	}



	/**
	 * ini folder. di mana akan di tulis
	 **/
	public String getTargetFolderAbsolutePath() {
		return targetFolderAbsolutePath;
	}



	/**
	 * ini folder. di mana akan di tulis
	 **/
	public void setTargetFolderAbsolutePath(String targetFolderAbsolutePath) {
		this.targetFolderAbsolutePath = targetFolderAbsolutePath;
	}
}
