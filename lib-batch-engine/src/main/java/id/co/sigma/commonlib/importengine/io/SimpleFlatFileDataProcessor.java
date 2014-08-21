package id.co.sigma.commonlib.importengine.io;

import java.util.List;

import id.co.sigma.commonlib.importengine.data.SimpleFlatFileData;
import id.co.sigma.commonlib.importengine.definition.UploadFileDefinition;
import id.co.sigma.commonlib.importengine.definition.src.BaseFileColumnDefinition;
import id.co.sigma.commonlib.importengine.exception.UploadFieldConvertionException;
import id.co.sigma.commonlib.importengine.exception.UploadFieldValidationException;
import id.co.sigma.commonlib.importengine.factory.IUploadColumnDefinitionFactory;
import id.co.sigma.commonlib.importengine.service.IUploadFileDefinitionService;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * class processor. method ini mentranslasikan dari 
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
@Service(value="sigma-uploadEngine::stringArrayToObjectArrayProccessorBean")
public class SimpleFlatFileDataProcessor implements ItemProcessor<String[], SimpleFlatFileData> ,IFileConfigurationDrivenWorker{

	
	
	
	private List<BaseFileColumnDefinition<?>> columnDefinitions ; 
	
	
	/**
	 * error di toleransi atau tidak. use casenya : kalau gagal 1 gagal semua berarti ini = false<br/>
	 * default : false
	 **/
	protected boolean tolarateError = false ; 
	
	/**
	 * ini di inject pada level step, ID dari file apa, ini di pergunakan untuk membaca konfigurasi. di inject pada application level
	 **/
	private String fileConfigurationId ; 
	
	
	
	
	
	/**
	 * column definition service
	 **/
	@Autowired
	private IUploadFileDefinitionService  uploadFileDefinitionService;
	
	/**
	 * worker untuk prses data
	 **/
	public SimpleFlatFileData process(String[] readedData) throws Exception {
		if ( readedData==null||readedData.length==0)
			return null ;
		SimpleFlatFileData retval = new SimpleFlatFileData();
		Object [] holder = new Object[columnDefinitions.size()];
		retval.setData(holder);
		
		for ( int i=0;i<columnDefinitions.size();i++){
			try {
				Object swapData = columnDefinitions.get(i).extractData(readedData[i]);
				holder[i]= swapData ; 
				columnDefinitions.get(i).validateSimpleFieldValidation(swapData);
				
			} catch (UploadFieldConvertionException exc) {
				retval.pushExceptions(exc);
				if ( !tolarateError)
					throw exc ; 
			}
			catch ( UploadFieldValidationException valExc){
				retval.pushExceptions(valExc);
				if ( !tolarateError)
					throw valExc ;
			}
			
		}
		return retval;
	}


	

	
	
	/**
	 * ini di inject pada level step, ID dari file apa, ini di pergunakan untuk membaca konfigurasi. di inject pada application level
	 **/
	public String getFileConfigurationId() {
		return fileConfigurationId;
	}
	
	/**
	 * ini di inject pada level step, ID dari file apa, ini di pergunakan untuk membaca konfigurasi. di inject pada application level
	 **/
	public void setFileConfigurationId(String fileConfigurationId) {
		try {
			this.fileConfigurationId = fileConfigurationId;
			UploadFileDefinition def =  uploadFileDefinitionService.getColumnDefiniton(fileConfigurationId); 
			this.columnDefinitions = def.getColumns();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * error di toleransi atau tidak. use casenya : kalau gagal 1 gagal semua berarti ini = false<br/>
	 * default : false
	 **/
	public void setTolarateError(boolean tolarateError) {
		this.tolarateError = tolarateError;
	}
	/**
	 * error di toleransi atau tidak. use casenya : kalau gagal 1 gagal semua berarti ini = false<br/>
	 * default : false
	 **/
	public boolean isTolarateError() {
		return tolarateError;
	}
	
	

}
