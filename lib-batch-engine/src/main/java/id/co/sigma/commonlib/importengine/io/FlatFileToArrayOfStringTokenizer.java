package id.co.sigma.commonlib.importengine.io;

import id.co.sigma.commonlib.importengine.definition.UploadFileDefinition;
import id.co.sigma.commonlib.importengine.service.IUploadFileDefinitionService;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * worker untuk memindahkan dari text flat file ke array of string. class ini di
 * override agar class menerima info yang lebih sederhana. <br/> Konfigurasi di
 * siapkan oleh class lain nya(tidak mungkin di kirimkan semua dalam job
 * context, karena ini akan menyebabkan file size overflow) . jadinya dengan
 * membaca id tertentu, semua yang di perlukan dalam tokenizer ini akan di baca
 * dari provider ini
 *
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 *
 */
@Scope(value = "step")
@Service(value = "sigma::upload-engine-reader::flat-file-to-array-tokenizer")
public class FlatFileToArrayOfStringTokenizer extends DelimitedLineTokenizer implements IFileConfigurationDrivenWorker{

    /**
     * column definition service
	 *
     */
    @Autowired
    private IUploadFileDefinitionService uploadFileDefinitionService;
    /**
     * file id, di inject dari jobs parameter
     */
    private String fileConfigurationId;

    /**
     * file id, di inject dari jobs parameter
     */
    public String getFileConfigurationId() {
        return fileConfigurationId;
    }

    /**
     * file id, di inject dari jobs parameter
     */
    public void setFileConfigurationId(String fileConfigurationId) {
    	try {
    		 this.fileConfigurationId = fileConfigurationId;
    	        UploadFileDefinition columnDefiniton = uploadFileDefinitionService.getColumnDefiniton(fileConfigurationId);
    	        
    	        this.setDelimiter(columnDefiniton.getDelimiter() +"");
    	        setNames(columnDefiniton.retrieveColumnNamesFromFile());
		} catch (Exception e) {
			e.printStackTrace(); 
		}
       

    }
}
