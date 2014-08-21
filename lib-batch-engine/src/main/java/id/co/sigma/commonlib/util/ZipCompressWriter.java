package id.co.sigma.commonlib.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.batch.item.ItemWriter;

/**
 * batch writer tar.gz . worker untuk archiving data
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ZipCompressWriter implements ItemWriter<File>{
	
	
	/**
	 * output file path
	 **/
	private String outputFilePath ; 

	@Override
	public void write(List<? extends File> items) throws Exception {
		
		if ( items== null||items.isEmpty())
			return ; 
		FileOutputStream fOut =null; 
		ArchiveOutputStream logical_zip = null; 
		
		
		BufferedOutputStream bOut = null ; 
		 
		
		 
		 try{
            
             fOut = new FileOutputStream(new File(outputFilePath));
             logical_zip = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP
            		 , fOut); 
             
             
             bOut = new BufferedOutputStream(fOut);
            
             for ( File scn : items){
            	 addFileToTarGz(logical_zip, scn, ""); 
             }
             
         } finally {
        	 
        	 logical_zip.finish();
        	 logical_zip.close();
              
             bOut.close();
             fOut.close();
         }
		 
		
	}
	
	
	
	
	
	
	
	
	 public void addFileToTarGz(ArchiveOutputStream logicalZip,File fileToAdd, String base) throws IOException {
         
         System.out.println("archiving:" + fileToAdd.getAbsolutePath());
         String entryName = base + fileToAdd.getName();
         ZipArchiveEntry tarEntry = new ZipArchiveEntry(fileToAdd, entryName);
         logicalZip.putArchiveEntry(tarEntry);
         IOUtils.copy(new FileInputStream(fileToAdd), logicalZip);
         logicalZip.closeArchiveEntry();
         
     }
	 
	 
	 
	 
	 
	 
	 /**
		 * output file path
		 **/
	 public String getOutputFilePath() {
		return outputFilePath;
	}
	 /**
		 * output file path
		 **/
	 public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}
	 
	 
	 

}
