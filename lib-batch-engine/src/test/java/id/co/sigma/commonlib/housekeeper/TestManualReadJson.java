package id.co.sigma.commonlib.housekeeper;

import java.io.FileReader;

import org.junit.Test;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class TestManualReadJson {

	
	
	 
	public static void main (String [] args ) throws Exception{
		String fileJson = "/tmp/restore-engine/sample-json.json"; 
		JsonReader reader = new JsonReader(new FileReader(fileJson));
		try{
			reader.beginObject();
			try {
				System.out.println(    reader.nextName());
				System.out.println(reader.nextString());
			} catch (IllegalStateException e) {
				// TODO: handle exception
				System.out.println("yek null");
				e.printStackTrace(); 
				reader.nextNull();
				System.out.println("Ok berhasil check");
			}
			catch (  Exception e2) {
				System.out.println("error : " + e2.getMessage());
				e2.printStackTrace(); 
			} 
			 
			JsonToken t =  reader.peek(); 
			
				System.out.println(t.name());
			
			System.out.println(    reader.nextName());
			reader.nextNull(); 
			 System.out.println("hore berhasil");
			
			
		}finally{
			reader.close(); 
		}
		
	}
}
