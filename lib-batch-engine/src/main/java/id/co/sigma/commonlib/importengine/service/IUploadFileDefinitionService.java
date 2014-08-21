package id.co.sigma.commonlib.importengine.service;

import id.co.sigma.commonlib.importengine.definition.UploadFileDefinition;
import id.co.sigma.commonlib.importengine.definition.UploadFileGroupDefinition;




/**
 * provider upload file definition
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface IUploadFileDefinitionService {
	
	
	/**
	 * akses file definition dengan id dari file. id dari file di jadikan string. kalaupun misalnya integer di jadikan string<br/>
	 * Sebagai catatan, method ini hanya valid kalau anda sudah memanggil {@link #getFileGroupDefinition(String)}, jadinya group nya sudah di panggil
	 * @param fileDefinitionIdAsString id dari file definition as string
	 **/
	public UploadFileDefinition getColumnDefiniton(String fileDefinitionIdAsString); 
	
	
	
	
	

}
