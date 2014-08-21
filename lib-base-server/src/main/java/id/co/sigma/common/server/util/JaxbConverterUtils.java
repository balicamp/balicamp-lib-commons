package id.co.sigma.common.server.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Jaxb converter utils untuk convert xml->pojo atau pojo->xml
 * @author I Gede Mahendra
 * @since Sept 13, 2012
 * @version $Id
 */
public class JaxbConverterUtils {
	
	private static JaxbConverterUtils instance;
	
	/**
	 * get instance singleton
	 * @return instance
	 */
	public static JaxbConverterUtils getInstance() {
		if(instance == null){
			instance = new JaxbConverterUtils();
		}//end if
		return instance;
	}
	
	/**
	 * Convert POJO to XML
	 * @param <DATA>
	 * @param source - Object POJO
	 * @param type - class pojo
	 * @return String xml
	 */
	public <DATA> String convertPojoToXml(DATA source, Class<? extends DATA> type){
		String result;
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(source, sw);
            result = sw.toString();
        } catch (JAXBException e) {
        	e.printStackTrace();
            result = "";
        }//end try
        return result;
	}
	
	/**
	 * Extract element XML to POJO
	 * @param <POJO>
	 * @param xml - xml source
     * @param pojo - class pojo
     * @return object as POJO
	 */
	@SuppressWarnings("unchecked")
	public <POJO> POJO convertXmlToPojo(String xml,Class<? extends POJO> pojo){   
    	JAXBElement<POJO> jaxbElement = null;
    	try {
    		InputStream is = new ByteArrayInputStream(xml.getBytes());
    		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    		Document document = docBuilder.parse(is);
    		Element varElement = document.getDocumentElement();
    		JAXBContext context = JAXBContext.newInstance(pojo);
            Unmarshaller unmarshaller = context.createUnmarshaller();                      
            jaxbElement = (JAXBElement<POJO>) unmarshaller.unmarshal(varElement, pojo);
		} catch (Exception e) {
			return null;			
		}//end try
    	return jaxbElement.getValue();
    }
	
	/**
	 * Convert POJO to XML
	 * @param <DATA>
	 * @param source - Collection of Object POJO
	 * @param type - class pojo
	 * @return String xml
	 */
	public <DATA> String convertPojosToXml(Collection<DATA> source, Class<? extends DATA> type){
		if(!source.isEmpty()){
			String result = "";
			try {
				for (DATA data : source) {
					result += JaxbConverterUtils.getInstance().convertPojoToXml(data, type);
					result += "|";
				}//end for				
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return result;
			}//end try
		}else{
			return "";
		}//end if
	}
	
	/**
	 * Extract element XML to POJO
	 * @param <POJO>
	 * @param xml - xml source
     * @param pojo - class pojo
     * @return collection of POJO
	 */
	@SuppressWarnings("unchecked")
	public <POJO> Collection<POJO> convertXmlToPojos(String xml, Class<? extends POJO> pojo){
		List<Object> result = new ArrayList<Object>();
		String[] xmlParse = xml.split("\\|");				
		if(xmlParse.length > 0){
			for (String string : xmlParse) {
				Object obj = convertXmlToPojo(string, pojo);
				result.add(obj);
			}//end for
		}//end if				
		return (Collection<POJO>) result;
	}
	
	/**
	 * Read Pojo form Object Element<br>	
	 * @param <POJO>
	 * @param element
	 * @param pojo
	 * @return POJO
	 */
	@SuppressWarnings("unchecked")
	public <POJO> POJO readJaxbElementFormPojo(Element element, Class<? extends POJO> pojo){
		try {
			JAXBElement<POJO> jaxbElement = null;
			Element varElement = element;
			JAXBContext context = JAXBContext.newInstance(pojo);
	        Unmarshaller unmarshaller = context.createUnmarshaller();                      
	        jaxbElement = (JAXBElement<POJO>) unmarshaller.unmarshal(varElement, pojo);
			return jaxbElement.getValue();
		} catch (Exception e) {
			return null;
		}//end try
	}
}