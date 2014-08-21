package id.co.sigma.commonlib.housekeeper;

import static org.junit.Assert.*;
import id.co.sigma.commonlib.util.ZipCompressWriter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class CompressTarGzTest {
	
	
	
	private static final String CONTENT_FILE1 ="ini isi file 1, ndak ada yang lain";
	private static final String CONTENT_FILE2 ="ini isi file 2, ndak ada yang lain";
	private static final String CONTENT_FILE3 ="ini isi file 3, ndak ada yang lain";
	@Test
	public void testArchiving() throws Exception {
		
		File outFile = File.createTempFile("_rslt", ".zip"); 
		
		
		File file1 = File.createTempFile("_arcv_content", ".txt"); 
		File file2 = File.createTempFile("_arcv_content", ".txt");
		File file3 = File.createTempFile("_arcv_content", ".txt");
		
		
		Files.write(Paths.get(file1.getAbsolutePath()), CONTENT_FILE1.getBytes() ); 
		Files.write(Paths.get(file2.getAbsolutePath()), CONTENT_FILE2.getBytes() );
		Files.write(Paths.get(file3.getAbsolutePath()), CONTENT_FILE3.getBytes() );
		
		ArrayList<File> files  = new ArrayList<>(); 
		files.add(file1); 
		files.add(file2);
		files.add(file3);
		
		ZipCompressWriter w = new ZipCompressWriter(); 
		w.setOutputFilePath(outFile.getAbsolutePath());
		System.out.println(outFile.getAbsolutePath());
		w.write(files);
	}
	


}
