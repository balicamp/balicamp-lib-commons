package id.co.sigma.commonlib.housekeeper.restore.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import id.co.sigma.commonlib.housekeeper.restore.RestoreParameterDetail;
import id.co.sigma.commonlib.housekeeper.restore.RestoreParameterHeader;
import id.co.sigma.commonlib.housekeeper.restore.ZipDataExtractor;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ZipDataExtractorImpl implements ZipDataExtractor{
	
	
	
	public static final String METADATA_FILE_NAME ="archived-tables.json"; 

	
	
	private static final Logger LOG = LoggerFactory.getLogger(ZipDataExtractorImpl.class);
	@Override
	public RestoreParameterHeader extractAndReadMetadata(File zipFile,
			File extractDestinationFolder) throws Exception {
		if ( !extractDestinationFolder.exists() || !extractDestinationFolder.isDirectory()){
			throw new IllegalArgumentException("extractDestinationFolder harus sudah ada dan merupakan folder");
		}
		unzipFile(zipFile, extractDestinationFolder);
		File metadataPath = new File(extractDestinationFolder.getAbsolutePath() + File.separator + METADATA_FILE_NAME);  
		String [] tableNames = getTableNamesToRestore(metadataPath);
		
		RestoreParameterHeader retval = new RestoreParameterHeader(); 
		ArrayList<  RestoreParameterDetail> dets = new ArrayList<RestoreParameterDetail>();
		for ( String scn :tableNames){
			
			
			
			File f = new File(extractDestinationFolder , scn + ".json"); 
			dets.add(new RestoreParameterDetail(f.getAbsolutePath()));
		}
		RestoreParameterDetail[] arrDets = new RestoreParameterDetail[dets.size()];
		dets.toArray(arrDets);
		retval.setDetails(arrDets);
		
		return retval;
	}
	
	
	
	
	
	
	
	
	/**
	 * membaca metadata dari file
	 * <ol>
	 * <li>insertIntoStatement</li>
	 * <li>fieldTypes</li>
	 * <li>fieldMapper</li>
	 * </ol>
	 **/
	protected RestoreParameterDetail readMetadata (File jsonFile) throws Exception {
		
		JsonReader rJson = new JsonReader(new FileReader(jsonFile)); 
		rJson.beginObject();
		//  insertIntoStatement
		rJson.nextName() ;
		String insertIntoStatement =  rJson.nextString();
		
		//fieldTypes
		rJson.nextName() ;
		rJson.beginArray();
		ArrayList<Integer> typeList = new ArrayList<Integer>();
		ArrayList<String[]> fieldMapArray = new ArrayList<String[]>();
		while (rJson.hasNext()) {
			typeList.add(  rJson.nextInt());
		}
		rJson.endArray();
		
		//fieldMapper
		rJson.nextName() ;
		rJson.beginArray();
		
		while (rJson.hasNext()) {
			rJson.beginObject();
			String key = rJson.nextName(); 
			String value = rJson.nextString(); 
			fieldMapArray.add(new String[]{key , value});
			rJson.endObject();
		}
		rJson.endArray();
		
		
		
		rJson.close();
		RestoreParameterDetail retval = new RestoreParameterDetail(jsonFile.getAbsolutePath());
		retval.setInsertIntoStatement(insertIntoStatement);
		retval.setFilePathToLoad(jsonFile.getAbsolutePath());
		 
		
		return retval ;
	}
	
	
	/**
	 * ini membaca file : archived-tables.json
	 **/
	protected String [] getTableNamesToRestore ( File metadataPath )throws Exception{
		JsonParser p = new JsonParser() ; 
		JsonElement e =  p.parse(new JsonReader(new FileReader( metadataPath)));
		JsonArray arr =  e.getAsJsonArray();
		if ( arr== null || arr.size()==0)
			return null ; 
		ArrayList<String> retval = new ArrayList<String>();
		for ( int i = 0 ; i < arr.size(); i++){
			retval.add(  arr.get(i).getAsString()); 
		}
		String[] arrRetval = new String[retval.size()]; 
		retval.toArray(arrRetval); 
		return arrRetval; 
		
	}
	
	
	
	public static void main ( String[] args){
		ZipDataExtractorImpl a = new ZipDataExtractorImpl();
		/*
		
		File f = new File("/tmp/" + (new Date()).getTime() ); 
		f.mkdir(); 
		try {
			a.extractAndReadMetadata(new File("/private/var/folders/4k/7dlhny1n5qz0rssvv8ympq8m0000gp/T/_final-7872485982044063556-balicamp_.zip"), 
					f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("mohon cek :" + f.getAbsolutePath());
		try {
			String [] tabelnames = a.getTableNamesToRestore(new File(f.getAbsoluteFile() + File.separator + METADATA_FILE_NAME));
			if ( tabelnames!= null){
				for ( String scn : tabelnames ){
					System.out.println(scn);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		File t = new File("/private/tmp/restore-engine/tes_house_keeping_detail.json");
		 try {
			RestoreParameterDetail d = new RestoreParameterDetail(t.getAbsolutePath());
			System.err.println(d.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	 
	
	/**
	 * ini untuk extract zip file ke dalam folder yang di minta
	 **/
	protected void unzipFile(File archiveFile ,File unzipDestFolder ) { 
 
        try {
            
            String[] zipRootFolder = new String[]{null};
            unzipFolder(archiveFile, archiveFile.length(), unzipDestFolder, zipRootFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	 /**
     * Unzips a zip file into the given destination directory.
     *
     * The archive file MUST have a unique "root" folder. This root folder is 
     * skipped when unarchiving.
     * 
     * @return true if folder is unzipped correctly.
     */
    private   boolean unzipFolder(File archiveFile,
            long compressedSize,
            File zipDestinationFolder,
            String[] outputZipRootFolder) {
 
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(archiveFile);
            byte[] buf = new byte[65536];
 
            Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
            while (entries.hasMoreElements()) {
                ZipArchiveEntry zipEntry = entries.nextElement();
                String name = zipEntry.getName();
                name = name.replace('\\', '/');
                int i = name.indexOf('/');
                if (i > 0) {
                        outputZipRootFolder[0] = name.substring(0, i);
                }
               name = name.substring(i + 1);
                
                File destinationFile = new File(zipDestinationFolder, name);
                if (name.endsWith("/")) {
                    if (!destinationFile.isDirectory() && !destinationFile.mkdirs()) {
                    	LOG.error( "Error creating temp directory:" + destinationFile.getPath());
                        return false;
                    }
                    continue;
                } else if (name.indexOf('/') != -1) {
                    // Create the the parent directory if it doesn't exist
                    File parentFolder = destinationFile.getParentFile();
                    if (!parentFolder.isDirectory()) {
                        if (!parentFolder.mkdirs()) {
                        	LOG.error("Error creating temp directory:" + parentFolder.getPath());
                            return false;
                        }
                    }
                }
                
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(destinationFile);
                    int n;
                    InputStream entryContent = zipFile.getInputStream(zipEntry);
                    while ((n = entryContent.read(buf)) != -1) {
                        if (n > 0) {
                            fos.write(buf, 0, n);
                        }
                    }
                } finally {
                    if (fos != null) {
                        fos.close();
                    }
                }
            }
            return true;
 
        } catch (IOException e) {
        	LOG.error("Unzip failed:" + e.getMessage());
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                	LOG.error("Error closing zip file");
                }
            }
        }
 
        return false;
    }

}
