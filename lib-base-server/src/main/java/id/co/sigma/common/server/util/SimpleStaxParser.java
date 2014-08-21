package id.co.sigma.common.server.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * parser XML dengan mempergunakan STAX
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class SimpleStaxParser {
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(SimpleStaxParser.class) ; 
	
	public static void main (String[] args){
		
		SimpleStaxParser prsr = new SimpleStaxParser(); 
		
		
		
		try {
			Map<String, String> mp = prsr.parseXML(new File("C:\\Users\\gede\\workspace\\mpn-root\\endpoint-caw01\\pom.xml"));
			if ( mp!= null){
				int i = 0 ; 
				for ( String scn  : mp.keySet()){
					System.out.println(i +":" + scn +  ":" + mp.get(scn));
					i++ ; 
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		System.err.println("------------------ sekarang dengan plain string ---------------------");
		
		String sampleStupitXML = "<root>"
				+ "	<a>"
				+ "		<b>Ini isi b</b>"
				+ "		<c>Ini isi nya C</c>"
				+ "</a><z>Z10</z></root>"; 
		try {
			Map<String, String> mp = prsr.parseXML(sampleStupitXML);
			if ( mp!= null){
				int i = 0 ; 
				for ( String scn  : mp.keySet()){
					System.out.println(i +":" + scn +  ":" + mp.get(scn));
					i++ ; 
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	

	/**
	 * baca dari string. ouput map string of string
	 * @param rawXMLString xml contnt yang perlu di proses
	 *  
	 */
	public Map<String, String> parseXML (  File xmlFile ) throws Exception {
		if ( xmlFile== null || !xmlFile.exists())
			return null ; 
		return parseXMLWorker(new FileInputStream(xmlFile)); 
	}
	
	
	
	/**
	 * baca dari string. ouput map string of string
	 * @param rawXMLString xml contnt yang perlu di proses
	 *  
	 */
	public Map<String, String> parseXML ( String rawXMLString ) throws Exception {
		if ( rawXMLString== null||rawXMLString.isEmpty())
			return null ; 
		rawXMLString = rawXMLString.replaceAll("\n", "");
		rawXMLString = rawXMLString.replaceAll("\r", "");
		return parseXMLWorker(new ByteArrayInputStream( rawXMLString.getBytes())); 
	}
	
	
	/**
	 * baca tag, dengan umpan tag dari yang di perlukan
	 * @param rawXMLString xml contnt yang perlu di proses
	 * @param tag contoh : root.a.b
	 *  
	 */
	public String parseValueByTag ( String rawXMLString , String tag) throws Exception {
		if ( rawXMLString== null||rawXMLString.isEmpty())
			return null ; 
		rawXMLString = rawXMLString.replaceAll("\n", "");
		rawXMLString = rawXMLString.replaceAll("\r", "");
		return this.findDataByPath(new ByteArrayInputStream( rawXMLString.getBytes()), tag); 
	}
	
	
	
	protected Map<String, String> parseXMLWorker  (InputStream inputStream )  throws Exception {
		 
		Map<String, String> retval = new HashMap<String, String>() ; 
		this.parseXMLWorker(inputStream, retval);
		return retval ; 
		 
		
		
	}
	
	
	
	
	/**
	 * mencari value berdasarkan tag
	 */
	protected  String findDataByPath   (InputStream inputStream , String path )  throws Exception {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream); 
		int event = xmlStreamReader.getEventType();
		
		try{
			ArrayList<String > stack = new ArrayList<String>(); 
			
			
			while ( true ){
				 switch(event) {
					 case XMLStreamConstants.START_ELEMENT:
						
						 String latestPath= stack.isEmpty()? "": stack.get(stack.size()-1) + "." ; 
						 String curPath =  latestPath+ xmlStreamReader.getLocalName(); 
						 stack.add(curPath); 
						 break  ; 
					 case XMLStreamConstants.CHARACTERS:
						 
						 String key = stack.get(stack.size()-1) ; 
						 if (path.equals(key) )
							 return xmlStreamReader.getText(); 
						 break  ;
					 case XMLStreamConstants.END_ELEMENT:
						 if (! stack.isEmpty()){
							 stack.remove(stack.size()-1); 
						 }
							 
						 break ; 
					 }
				 if (!xmlStreamReader.hasNext())
	                 break;
				 event = xmlStreamReader.next();
			}
			return null ; 
		}
		finally {
			xmlStreamReader.close(); 
		}
		
		
	}
	
	
	
	protected void parseXMLWorker  (InputStream inputStream , Map<String, String> target  )  throws Exception {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream); 
		int event = xmlStreamReader.getEventType();
		Map<String, String> retval =target ; 
		
		
		ArrayList<String > stack = new ArrayList<String>(); 
		
		try{
			while ( true ){
				 switch(event) {
					 case XMLStreamConstants.START_ELEMENT:
						
						 String latestPath= stack.isEmpty()? "": stack.get(stack.size()-1) + "." ; 
						 String curPath =  latestPath+ xmlStreamReader.getLocalName(); 
						 stack.add(curPath); 
						 break  ; 
					 case XMLStreamConstants.CHARACTERS:
						 retval.put(stack.get(stack.size()-1), xmlStreamReader.getText());
						 break  ;
					 case XMLStreamConstants.END_ELEMENT:
						 if (! stack.isEmpty()){
							 stack.remove(stack.size()-1); 
						 }
							 
						 break ; 
					 }
				 if (!xmlStreamReader.hasNext())
	                 break;
				 event = xmlStreamReader.next();
			} 
		}finally{
			try {
				xmlStreamReader.close(); 
				inputStream.close();
			} catch (Exception e) {
				logger.error("gagal menutup XML resource. erorr : " + e.getMessage() , e);
			}
			 
		}
		
		
	}

}
