package id.co.sigma.commonlib.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.batch.item.ItemWriter;

/**
 * batch writer tar.gz . worker untuk archiving data
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class TarGzWriter implements ItemWriter<File>{
	
	
	/**
	 * output file path
	 **/
	private String outputFilePath ; 

	@Override
	public void write(List<? extends File> items) throws Exception {
		
		if ( items== null||items.isEmpty())
			return ; 
		FileOutputStream fOut =null; 
		BufferedOutputStream bOut = null ; 
		GzipCompressorOutputStream gzOut = null ; 
		TarArchiveOutputStream tOut = null  ;
		 
		 try{
            
             fOut = new FileOutputStream(new File(outputFilePath));
             bOut = new BufferedOutputStream(fOut);
             gzOut = new GzipCompressorOutputStream(bOut);
             tOut = new TarArchiveOutputStream(gzOut);
             for ( File scn : items){
            	 addFileToTarGz(tOut, scn, ""); 
             }
             tOut.closeArchiveEntry();
         } finally {
             tOut.finish();
             tOut.close();
             gzOut.close();
             bOut.close();
             fOut.close();
         }
		 
		
	}
	
	
	
	
	
	
	
	
	 public void addFileToTarGz(TarArchiveOutputStream tOut,File fileToAdd, String base) throws IOException {
         
         System.out.println(fileToAdd.exists());
         String entryName = base + fileToAdd.getName();
         TarArchiveEntry tarEntry = new TarArchiveEntry(fileToAdd, entryName);
         tOut.putArchiveEntry(tarEntry);
         IOUtils.copy(new FileInputStream(fileToAdd), tOut);
         
         
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
