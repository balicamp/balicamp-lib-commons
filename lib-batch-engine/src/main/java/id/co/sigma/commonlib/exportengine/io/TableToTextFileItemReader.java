package id.co.sigma.commonlib.exportengine.io;

import id.co.sigma.commonlib.base.BaseParameterizedJDBCItemReader;
import id.co.sigma.commonlib.base.ExportEngineConstant;
import id.co.sigma.commonlib.exportengine.data.ExportToTextFileMetadata;
import id.co.sigma.commonlib.exportengine.data.IExportToTextFileMetadataProvider;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;



/**
 * class reader untuk meta data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class TableToTextFileItemReader extends BaseParameterizedJDBCItemReader<Object[]> {

	
	private int colCount = 0 ; 
	

	
	
	/**
	 * logger instance
	 **/
	private static final Logger logger = LoggerFactory.getLogger(TableToTextFileItemReader.class); 
	
	/**
	 * id configuration dari file export
	 **/
	private String configurationId ; 
	
	
	@Autowired
	private IExportToTextFileMetadataProvider exportToTextFileMetadataProvider ; 
	
	
	
	
	
	
	
	
	
	
	private static final String DATE_DATA_TYPES[] = {
		Date.class.getName() , 
		java.sql.Date.class.getName()  , 
		Timestamp.class.getName() 
};
	/**
	 * Job context, ini untuk menaruh data yang di share dengan worker lain, misal : proccessor atau writer
	 **/
	private ExecutionContext jobContext ; 
	
	
	
	public TableToTextFileItemReader(){
		super(); 
		//setPreparedStatementSetter(preparedStatementSetter);
	}
	
	
	
	/**
	 * di run pertama kali dalam batch. di inject pada saat konfigurasi berubah
	 **/
	private RowMapper<Object[]> getInitalWorkerMapper () {
		return new RowMapper<Object[]>() {
			
			@Override
			public Object[] mapRow(ResultSet rs, int rowIndex) throws SQLException {
				
				ResultSetMetaData rsMeta =  rs.getMetaData();
				
				colCount =  rsMeta.getColumnCount() ;
				Object[] retval = new Object[colCount];
				analizeQueryResultMetaData(rsMeta, metaData.getDecimalColumns()); 
				setRowMapper(getNormalWorkerMapper());
				for ( int i=0 ; i < colCount;i++){
					retval[i] = rs.getObject(i+1);
				}
				return retval;
			}
		};
	}
	
	
	
	private ExportToTextFileMetadata  metaData  ;



	
	
	/**
	 * mapper normal. ini di pergunakan kalau meta data sudah di dapatkan. Metadata di baca oleh mapper ini {@link #getInitalWorkerMapper()}
	 * 
	 **/
	private RowMapper<Object[]> getNormalWorkerMapper () {
		return new RowMapper<Object[]>() {
			
			@Override
			public Object[] mapRow(ResultSet rs, int rowIndex) throws SQLException {
				Object[] retval = new Object[colCount];
				for ( int i=0 ; i < colCount;i++){
					retval[i] = rs.getObject(i+1);
				}
				return retval;
			}
		}; 
	}
	
	/**
	 * id configuration dari file export
	 **/
	public void setConfigurationId(String configurationId) {
		this.configurationId = configurationId;
		setRowMapper(getInitalWorkerMapper());
		metaData  =  this.exportToTextFileMetadataProvider.getMetaData(this.configurationId); 
		setSql(metaData.getSelectStatement()); 
		
		
		
		
		 
		
	}


	@Override
	protected Object[] readCursor(ResultSet rs, int currentRow)
			throws SQLException {
		// TODO Auto-generated method stub
		return super.readCursor(rs, currentRow);
	}

	@Override
	protected RowMapper<Object[]> generateRowMapper() {
		
		return getInitalWorkerMapper();
	}
	
	
	@BeforeStep
	public void getMyData(StepExecution stepExecution) {
		JobExecution jobExecution = stepExecution.getJobExecution();
		jobContext = jobExecution.getExecutionContext();
		
	}
	
	
	
	/**
	 * request untuk analyze resultset metadata
	 **/
	public void analizeQueryResultMetaData(ResultSetMetaData  rsMetaData, String[] decimalColumns){
		ArrayList<Integer> decimalColumnResultsetIndexes  = new ArrayList<Integer>();
		//if ( decimalColumns==null||decimalColumns.length==0)
		//	return ; 
		try {
			
			
			//IObjectToStringConverter[] converters  = new IObjectToStringConverter[rsMetaData.getColumnCount()] ;
			OuputDataType[] dataTypes = new OuputDataType[rsMetaData.getColumnCount()] ;
			jobContext.put(ExportEngineConstant.COLUMN_CONVERTER_KEY, dataTypes);
			for ( int i=1 ; i <= rsMetaData.getColumnCount(); i++){
				boolean isDecimal = false ; 
				String nama =  rsMetaData.getColumnName(i).toUpperCase();
				if ( decimalColumns!=null){
					for ( String scn  : decimalColumns){
						if ( nama.equalsIgnoreCase(scn)){
							decimalColumnResultsetIndexes.add(i-1);
							isDecimal = true ;
							dataTypes[i-1] = OuputDataType.money; 
							break ; 
						}
					}
				}
				
				if( !isDecimal){
					String tipeCol =  rsMetaData.getColumnClassName(i) ;
					boolean isDate = false ; 
					for ( String scn  : DATE_DATA_TYPES){
						if ( tipeCol.equals(scn)){
							dataTypes[i-1] = OuputDataType.date; 
							isDate = true ; 
							break ; 
						}
					}
					if  ( !isDate){
						if (String.class.getName().equals(tipeCol)){
							dataTypes[i-1] = OuputDataType.string;
						}
						else{
							dataTypes[i-1] = OuputDataType.commonToString;
						}
						
					}
						 
					
				}
			}
			
			for ( OuputDataType scn : dataTypes ){
				System.out.println("converter : " + scn);
			}
			
		} catch (Exception e) {
			logger.error("gagal membaca data column unutk membuat converter object array. error message :" + e.getMessage() , e); 
		}
		
		
	}
}
