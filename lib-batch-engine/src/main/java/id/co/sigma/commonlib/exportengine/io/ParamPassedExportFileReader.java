package id.co.sigma.commonlib.exportengine.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.jdbc.core.RowMapper;

import id.co.sigma.commonlib.base.JobParameterQueryParameterizeJDBCItemReader;
import id.co.sigma.commonlib.base.SQLParameterHolderArray;

/**
 * reader untuk yang pass dari job parameter
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ParamPassedExportFileReader extends JobParameterQueryParameterizeJDBCItemReader<Object[]> implements IHaveConverter{
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(ParamPassedExportFileReader.class); 

	private int colCount = 0 ; 
	
	
	
	private SingleExportJobParameter exportJobParameter ;  
	
	private Map<String, IObjectToStringConverter> indexedPassedConverter ; 
	
	
	
	
	/**
	 * default date converter
	 */
	private DateToStringConverter defaultDateConverter  ;
	
	private NoConverter noConverter = new NoConverter(); 
	private CommonToStringConverter commonToStringConverter = new CommonToStringConverter(); 
	
	 
	
	private static final String DATE_DATA_TYPES[] = {
		Date.class.getName() , 
		java.sql.Date.class.getName()  , 
		Timestamp.class.getName() 
};
	
	
	/**
	 * converters
	 **/
	private IObjectToStringConverter [] converters ; 
	
	
	public ParamPassedExportFileReader(){
		super() ; 
		setSql("select 1 ");
		setRowMapper(getInitalWorkerMapper());
	}
	
	

	
	
	@Override
	protected void actualBeforeStepHandler(StepExecution stepExecution)
			throws Exception {
		// TODO Auto-generated method stub
		super.actualBeforeStepHandler(stepExecution);
		exportJobParameter = new SingleExportJobParameter(); 
		exportJobParameter.fetchParameterFromJobParameters(stepExecution.getJobParameters());
		indexedPassedConverter = new HashMap<String, IObjectToStringConverter>() ; 
		for  ( NamedConverter scn :   exportJobParameter.getConverters() ){
			indexedPassedConverter.put(scn.getColumnName().toUpperCase(), scn.getConverter()); 
		}
		if ( exportJobParameter.getSqlParameters()!= null){
			SQLParameterHolderArray paramQuerys = new SQLParameterHolderArray(); 
			paramQuerys.putParameters(exportJobParameter.getSqlParameters());
			this.sourceDataPreparedStatementSetter.setIndexedStatementParam(paramQuerys);
		}
		setRowMapper(getInitalWorkerMapper());
		defaultDateConverter = new DateToStringConverter(exportJobParameter.getDefaultDateFormatterPattern()); 
		setSql(exportJobParameter.getSelectSqlStatment());
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
				try {
					analizeQueryResultMetaData(rsMeta );
				} catch (Exception e) {
					logger.error("gagal menganalisa metadata, error di laporkan : " + e.getMessage());
					//FIXME: paksa job berhenti di sini
					e.printStackTrace();
				} 
				setRowMapper(getNormalWorkerMapper());
				for ( int i=0 ; i < colCount;i++){
					retval[i] = rs.getObject(i+1);
				}
				return retval;
			}
		};
	}
	
	
	
	
	
	/**
	 * analisa metadata
	 */
	private void analizeQueryResultMetaData(ResultSetMetaData rsMetaData) throws Exception{
		
		int size = rsMetaData.getColumnCount(); 
		converters = new IObjectToStringConverter[size]; 
		for ( int i=1 ; i <= size; i++){
			String nama=  rsMetaData.getColumnName(i).toUpperCase(); 
			if ( indexedPassedConverter.containsKey(nama)){
				converters[i-1] = indexedPassedConverter.get(nama); 
			}else{
				String fqcn = rsMetaData.getColumnClassName(i);
				boolean isDate = false; 
				for (String scn : DATE_DATA_TYPES){
					if ( scn.equals(fqcn)){
						isDate = true ; 
						break  ; 
					}
				}
				if ( isDate){
					converters[i-1] = defaultDateConverter;
				}
				else if ( String.class.getName().equals(fqcn)){
					converters[i-1] = noConverter ;
				}
				else
					converters[i-1] = commonToStringConverter; 
			}
		}
		
	}
	
	
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
	 * converters
	 **/
	public IObjectToStringConverter[] getConverters() {
		return converters;
	}

}
