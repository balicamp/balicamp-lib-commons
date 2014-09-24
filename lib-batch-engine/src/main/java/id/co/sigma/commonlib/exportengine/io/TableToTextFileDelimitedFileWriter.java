package id.co.sigma.commonlib.exportengine.io;


import java.util.Map;

import id.co.sigma.commonlib.exportengine.data.ExportToTextFileMetadata;
import id.co.sigma.commonlib.exportengine.data.IExportToTextFileMetadataProvider;
import id.co.sigma.commonlib.util.IQueryParameterHolder;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.PassThroughFieldExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;




/**
 * writer untuk menulis ke dalam flat file. di drive oleh konfigurasi nya 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class TableToTextFileDelimitedFileWriter extends FlatFileItemWriter<String[]>{

	
	private DelimitedLineAggregator<String[]> lineAggregator ; 
	private PassThroughFieldExtractor<String[]> fieldExtractor ; 
	
	
	
	/**
	 * provider konfigurasi
	 **/
	@Autowired
	private IExportToTextFileMetadataProvider exportToTextFileMetadataProvider ;
	
	
	
	@Autowired
	private ITargetFileLocationGenerator targetFileLocationGenerator; 
	
	/**
	 * id configuration dari file export
	 **/
	private String configurationId ; 
	
	
	
	/**
	 * parameter holder
	 **/
	@Autowired
	@Qualifier(value="batch_engine_reader_param_holder")
	protected IQueryParameterHolder queryParameterHolder ; 
	
	public TableToTextFileDelimitedFileWriter() {
		super(); 
		lineAggregator = new DelimitedLineAggregator<String[]>(); 
		fieldExtractor = new PassThroughFieldExtractor<String[]>();
		setLineAggregator(lineAggregator);
		lineAggregator.setFieldExtractor(fieldExtractor);
	}
	
	
	
	private ExportToTextFileMetadata metaData ; 
	
	public void setConfigurationId(String configurationId) {
		this.configurationId = configurationId;
		metaData =  exportToTextFileMetadataProvider.getMetaData(this.configurationId);
		lineAggregator.setDelimiter(  metaData.getTargetFileDelimiter()); 
		
	}
	
	public void setSelectStatementParameterGroupKey(String key ){
		Map<String, Object> swap =  this.queryParameterHolder.get(key);
		Resource swapRsc =targetFileLocationGenerator.generateTargetForWritingOutput(metaData, swap) ;
		try {
			String absPath =  swapRsc.getFile().getAbsolutePath();
			queryParameterHolder.putWithRawStringKey(key, IQueryParameterHolder.OUTPUT_FILE_KEY	, absPath);
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		setResource( swapRsc  ); 
	}
	
	
	
	
}
