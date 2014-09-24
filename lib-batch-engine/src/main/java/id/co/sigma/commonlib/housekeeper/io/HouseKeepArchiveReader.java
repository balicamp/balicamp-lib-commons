package id.co.sigma.commonlib.housekeeper.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;

import id.co.sigma.commonlib.base.IRecieveJDBCMetadataField;
import id.co.sigma.commonlib.base.ReadSelectStatementMetaData;
import id.co.sigma.commonlib.base.SQLParameterHolder;
import id.co.sigma.commonlib.base.SourceDataPreparedStatementSetter;
import id.co.sigma.commonlib.housekeeper.HouseKeeperJobParameter;
import id.co.sigma.commonlib.housekeeper.HouseKeeperJobParameters;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class HouseKeepArchiveReader extends JdbcCursorItemReader<Object[]>{

	
	private static final Logger logger = LoggerFactory.getLogger(HouseKeepArchiveReader.class); 
	/**
	 * count berapa column dalam column 
	 **/
	protected int colCount; 
	
	
	
	
	
	
	/**
	 * reciever JDBC metadata. ini di propagate metadata change
	 **/
	private List<IRecieveJDBCMetadataField> recieveJDBCMetadataFields; 
	
	
	
	private ReadSelectStatementMetaData normalizedStatement ; 
	
	
	
	
	public HouseKeepArchiveReader(){
		super() ; 
		setSql("select 1");
		setRowMapper(getInitalWorkerMapper());
		
	}
	
	
	
	
	
	 
	
	
	/**
	 * di run pertama kali dalam batch. di inject pada saat konfigurasi berubah. pada saat siap yang di pergunakan adalah hasil dari {@link #generateNormalMapper()}
	 **/
	private RowMapper<Object[]> getInitalWorkerMapper () {
		return new RowMapper<Object[]>() {
			
			@Override
			public Object[] mapRow(ResultSet rs, int rowIndex) throws SQLException {
				colCount = 0 ; 
				ResultSetMetaData rsMeta =  rs.getMetaData();
				
				colCount =  rsMeta.getColumnCount() ;
				Object[] retval = new Object[colCount];
				 if ( recieveJDBCMetadataFields!= null){
					 for(IRecieveJDBCMetadataField scn : recieveJDBCMetadataFields){
						 try {
							scn.onRecieveMetadata(rsMeta);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 }
				 }
			
				for ( int i=0 ; i < colCount;i++){
					retval[i] = rs.getObject(i+1);
				}
				setRowMapper(   generateNormalMapper ());
				return retval;
			}
		};
	}
	
	private RowMapper<Object[]> generateNormalMapper () {
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
	
	
	static Map<String, Class<?> > indexedTypes = new HashMap<String, Class<?>>(); 
	static {
		indexedTypes.put(String.class.getName(), String.class) ; 
		indexedTypes.put(Long.class.getName(), Long.class) ;
		indexedTypes.put(Double.class.getName(), Double.class) ;
		indexedTypes.put(Date.class.getName(), Date.class) ;
		
		
	}
	
	
	@BeforeStep
	public void beforeStepHandler( StepExecution stepExecution ) throws Exception {
		setRowMapper(getInitalWorkerMapper());
		colCount = 0 ; 
		//ini perlu set sql statment
		HouseKeeperJobParameter param = (new HouseKeeperJobParameters()).instantiateParameter(stepExecution);
		String sql =param.getSelectStatment();  
		
		
		
		
		normalizedStatement = this.normalizeSqlStatement(sql); 
		

		  SourceDataPreparedStatementSetter  dataPreparedStatementSetter = new SourceDataPreparedStatementSetter();
		
		dataPreparedStatementSetter.setNamedParameters(normalizedStatement.getNamedParameterArray());
		
		Map<String, Object> paramValueMap = new HashMap<String, Object>();
		Map<String, Class<?>> paramTypeMap = new HashMap<String, Class<?>>();
		
		
		
		
		// set parameter + value
		if ( normalizedStatement.getNamedParameterArray()!= null && normalizedStatement.getNamedParameterArray().length>0){
			if ( param.getQueryParamaters()== null||param.getQueryParamaters().length==0){
				logger.error("parameter name dalam sql ndak null, sementara parameter null, ini akan gagal");
			}
			
			
			for ( String scn  : normalizedStatement.getNamedParameterArray()){
				if ( scn.equals("DATE")){
					System.out.println("ini dia biang kerok");
				}
				if ( param.getQueryParamaters()!= null){
					for ( SQLParameterHolder prmSql : param.getQueryParamaters()){
						if ( scn.equals(prmSql.getParamName())){
							
							Object paramData =  prmSql.getParameter() ; 
							Class cls = indexedTypes.get(prmSql.getParamFqcn()) ;
							
							System.out.println("param value : " + paramData +", fqcn : " + cls + ",key :" + scn);
							paramValueMap.put(scn,paramData); 
							paramTypeMap.put(scn,cls );
							break ; 
						}
					}
				}
				
			}
		}
		
		dataPreparedStatementSetter.setIndexedStatementParam(paramValueMap);
		dataPreparedStatementSetter.setValueDataTypes(paramTypeMap);
		setPreparedStatementSetter(dataPreparedStatementSetter);
		//normalize dulu
		String sqlStatement = normalizedStatement.getNormalizeStatement();  
		System.out.println(sqlStatement);
		//FIXME: ini musti di balikan ke parameterized
		setSql(sqlStatement);
	}

	
	
	/**
	 * regex work, di sini select statement di bongkar , di ganti named parameter dengan ?
	 **/
	protected ReadSelectStatementMetaData normalizeSqlStatement(String selectStatment) {
		Pattern pattern = Pattern.compile("\\:[a-zA-Z0-9_]+");
		Matcher matcher = pattern.matcher(selectStatment);
		StringBuffer result = new StringBuffer();
		ArrayList<String>   namedParamArray = new ArrayList<String>(); 
		while (matcher.find()) {
			namedParamArray.add(matcher.group().substring(1));  
			matcher.appendReplacement(result, "?"); 
		}
		matcher.appendTail(result); 
		ReadSelectStatementMetaData retval = new ReadSelectStatementMetaData();
		retval.setNormalizeStatement(result.toString());
		String[] arr = new String[namedParamArray.size()];
		namedParamArray.toArray(arr); 
		retval.setNamedParameterArray(arr); 
		 
		
		return retval ; 
	}


	/**
	 * reciever JDBC metadata. ini di propagate metadata change
	 **/
	public List<IRecieveJDBCMetadataField> getRecieveJDBCMetadataFields() {
		return recieveJDBCMetadataFields;
	}



	/**
	 * reciever JDBC metadata. ini di propagate metadata change
	 **/
	public void setRecieveJDBCMetadataFields(
			List<IRecieveJDBCMetadataField> recieveJDBCMetadataFields) {
		this.recieveJDBCMetadataFields = recieveJDBCMetadataFields;
	}

}
