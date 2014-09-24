package id.co.sigma.commonlib.journal;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={
		"classpath*:/launch-context.xml" , 
		"classpath*:/appContext-test-context.xml" , 
		"classpath*:META-INF/spring/journal-engine-context.xml"  , 
		"classpath*:META-INF/spring/journal-engine-infrastructure-context.xml"})
@RunWith(value=SpringJUnit4ClassRunner.class)
public class MetadataReader {
	
	/**
	 * Membaca header file yg diupload user.
	 * 
	 * @param input File yang diupload oleh user;
	 * @return List dari header file.
	 */
	public Metadata readHeaders(File input) {
		Metadata metadata = new Metadata();
		List<String> lines = new ArrayList<>();
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(input));
			int headerCounter = 0;
			while((line = reader.readLine()) != null) {
				if(line.matches("0[1-3][|]\\w+")) {
					lines.add(line);
				}
				if(line != null && !line.trim().equals(""))
					headerCounter++;
			}
			metadata.setTotalLineActual(headerCounter-3);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(String lineHeader : lines) {
			String value = lineHeader.split("[|]")[1];
			if(lineHeader.matches("01[|]\\w+")) {
				metadata.setKodeTipeFile(value);
			} else if(lineHeader.matches("02[|]\\w+")) {
				metadata.setKodeUnit(value);
			} else if(lineHeader.matches("03[|]\\w+")) {
				metadata.setTotalBarisData(Integer.valueOf(value));
			}
		}
		return metadata;
	}
	
	public boolean validateLineNumber(Metadata metadata)  {
		if(!metadata.isTotalBarisDataValid()) {
			System.err.println("\r\n\r\nTOTAL BARIS DATA TIDAK VALID.\r\n" +
					"Baris yg diharapkan\t\t: "+metadata.getTotalBarisData()+"\r\n" +
					"Baris yg terdapat pada file\t: "+metadata.getTotalLineActual());
			return false;
		}
		return true;
	}
	
	@Test
	public void test() {
		MetadataReader reader = new MetadataReader();
		Metadata metadata = reader.readHeaders(new File("/tmp/upload1.txt"));
		System.err.println("Kode Tipe File\t\t\t: "+metadata.getKodeTipeFile());
		System.err.println("Kode Unit\t\t\t: "+metadata.getKodeUnit());
		System.err.println("Total Baris Data\t\t: "+metadata.getTotalBarisData());
		validateLineNumber(metadata);
	}

}
