package id.co.sigma.commonlib.importengine.io;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.co.sigma.commonlib.importengine.data.SimpleFlatFileData;
import id.co.sigma.commonlib.importengine.definition.UploadFileDefinition;
import id.co.sigma.commonlib.importengine.definition.src.BaseFileColumnDefinition;
import id.co.sigma.commonlib.importengine.definition.target.EnvironmentProvidedTargetColumnDefinition;
import id.co.sigma.commonlib.importengine.service.IUploadFileDefinitionService;
import id.co.sigma.commonlib.util.IQueryParameterHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;


/**
 * worker untuk menulis data dari flat file ke staging 1, ini normal path
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * 
 **/
public class UploadDataJDBCBatchWriter extends JdbcBatchItemWriter<SimpleFlatFileData> implements IFileConfigurationDrivenWorker {

	
	
	
	
	
	
	/**
	 * id file configuration. ini untuk membaca definisi batch 
	 **/
	private String fileConfigurationId ; 
	
	
	/**
	 * logger instance
	 **/
	private static final Logger logger = LoggerFactory.getLogger(UploadDataJDBCBatchWriter.class); 
	
	  
	/**
	 * upload file column definition
	 **/
	@Autowired
	private IUploadFileDefinitionService uploadFileDefinitionService ;  
	
	
	
	/**
	 * ini untuk pass parameter ke dalam writer
	 **/
	@Autowired
	private IQueryParameterHolder queryParameterHolder ; 
	
	
	
	@Override
	public void write(List<? extends SimpleFlatFileData> data) throws Exception {
		super.write(data);
		
	}
	
	
	
	
	/**
	 * id file configuration. ini untuk membaca definisi batch 
	 **/
	public void setFileConfigurationId(String fileConfigurationId) {
		this.fileConfigurationId = fileConfigurationId;
		
	}
	
	/**
	 * variable pada scope step, ini untuk mengambil variable parameter untuk select statement yang mentrigger statement select proses pembacaan data jurnal
	 **/
	public void setSelectStatementParameterGroupKey(final String parameterGroupKey ){
		
		
		final UploadFileDefinition fileDef =  uploadFileDefinitionService.getColumnDefiniton(this.fileConfigurationId);
		String sqlSmt =fileDef.retrieveInsertStatement() ;
		logger.debug("sql smt :" + sqlSmt); 
		setSql(sqlSmt);
		
		 
		setItemSqlParameterSourceProvider(new ItemSqlParameterSourceProvider<SimpleFlatFileData>() {
			
			@Override
			public SqlParameterSource createSqlParameterSource(SimpleFlatFileData data) {
				HashMap<String, Object> swap = new HashMap<String, Object>();
				 Object arr[] = data.getData() ; 
				int i=0; 
				for (BaseFileColumnDefinition<?> scn  : fileDef.getColumns()  ){
					swap.put(scn.getColumnOnFileName().toUpperCase(),arr[i] );
					i++; 
				}
				// push environment variable di sini
				
				
				final Map<String, Object> environtmentParameter =  queryParameterHolder.get(parameterGroupKey);
				// taruh semua dengan prefix EnvironmentProvidedTargetColumnDefinition.PREFIX_FOR_NAMED_PARAM
				if ( environtmentParameter!=null&& !environtmentParameter.isEmpty()){
					 
					for ( String key : environtmentParameter.keySet()){
						String keyEnv =EnvironmentProvidedTargetColumnDefinition.PREFIX_FOR_NAMED_PARAM + key.toUpperCase();  
						Object prmSrc = environtmentParameter.get(key) ;
						
						swap.put(keyEnv, prmSrc instanceof BigInteger ? ( (BigInteger)prmSrc).longValue() : prmSrc );
						
					}
				}
				
				
				if ( logger.isDebugEnabled()){
					logger.debug("menaruh parameter : " + swap.toString() + ", size :" + swap.size());
				}
				
				/*
				StringBuffer msg = new StringBuffer();
				for ( String key : swap.keySet() ){
					String tipe =(swap.get(key)==null ) ? "??" : swap.get(key).getClass().getName();  
					msg.append("---[" + key+ ":" + swap.get(key) +",tipe:" +     tipe   + "]-- ,") ; 
				}
				System.out.println("val :" + msg.toString());
				*/
				return new MapSqlParameterSource(swap);
			}
		}); 
	}
}
