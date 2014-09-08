package id.co.sigma.commonlib.exportengine.data;




/**
 * provider meta data download
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface IExportToTextFileMetadataProvider {
	
	
	/**
	 * membaca metadata untuk proses download. data di cari dengan ID 
	 * @param id id dari meta data export file definition
	 **/
	ExportToTextFileMetadata getMetaData (String id ) ; 

}
