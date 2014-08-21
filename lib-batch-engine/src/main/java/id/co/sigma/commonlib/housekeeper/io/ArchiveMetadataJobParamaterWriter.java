package id.co.sigma.commonlib.housekeeper.io;

import id.co.sigma.commonlib.housekeeper.HouseKeepingConstant;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.google.gson.stream.JsonWriter;

/**
 * ini menulis metadata dari table apa saja yang di archive
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ArchiveMetadataJobParamaterWriter implements ItemWriter<Object[]>{
	
	
	
	
	 
	
	
	
	/**
	 * ini folder. di mana akan di tulis
	 **/
	private String targetFolderAbsolutePath ; 

	@Override
	public void write(List<? extends Object[]> items) throws Exception {
		JsonWriter writer;
		writer = new JsonWriter(new FileWriter(targetFolderAbsolutePath + File.separator + HouseKeepingConstant.ARCHIVED_FOLDER_LIST_FILE_NAME_ON_ARCHIVE));
		writer.beginArray();
		if ( items!= null && !items.isEmpty()){
			 
			for ( Object[] scn : items){
				if ( scn== null || scn.length==0||scn[0]== null)
					continue ; 
				if (!( scn[0] instanceof String))
					continue ; 
				writer.value((String)scn[0]); 
			}
			 
		}
		writer.endArray();
		writer.close(); 
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
