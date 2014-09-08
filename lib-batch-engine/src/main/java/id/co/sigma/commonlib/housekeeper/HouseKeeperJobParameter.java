package id.co.sigma.commonlib.housekeeper;


import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import id.co.sigma.commonlib.base.BaseSpringBatchJobParameterWrapper;
import id.co.sigma.commonlib.base.SQLParameterHolder;
import id.co.sigma.commonlib.base.serializer.SendToParameterField;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */

public class HouseKeeperJobParameter extends BaseSpringBatchJobParameterWrapper{
	
	
	
	private static final String PK_COL_KEY="pkColumns";
	
	private static final String SQL_PARAM_VALUE_KEY="sqlParamValueKey";
	
	private static final String SQL_PARAM_NAME_KEY="sqlParamNameKey";
	
	private static final String SQL_PARAM_DATA_TYPE_KEY="sqlDataTypeColumns";
	
	
	private static final String COUNT_SQL_PARAMFLAG_KEY="countSQLParamKey";
	
	
	
	public HouseKeeperJobParameter(){
		super();
		
	}
	
	
	public HouseKeeperJobParameter(JobParameters param) throws Exception{
		super(param);
	}
	
	
	/**
	 * select statement. ini apa yang di insert
	 **/
	@SendToParameterField(key=HouseKeepingConstant.SELECT_STATEMENT_KEY )
	private String selectStatment; 
	
	
	
	@SendToParameterField(key=HouseKeepingConstant.INSERT_STATEMENT_KEY)
	private String insertIntoStatment;
	
	
	
	/**
	 * nama table yang di archive
	 **/
	@SendToParameterField(key=HouseKeepingConstant.TABLE_NAME_KEY)
	private String tableName ; 
	
	
	
	
	
	/**
	 * parameter query 
	 **/
	private SQLParameterHolder[] queryParamaters ; 
	
	
	/**
	 * nama columns yang jadi primary key
	 **/
	private String[] primaryKeyColumns ; 
	
	
	public String getSelectStatment() {
		return selectStatment;
	}


	public void setSelectStatment(String selectStatment) {
		this.selectStatment = selectStatment;
	}


	public String getInsertIntoStatment() {
		return insertIntoStatment;
	}


	public void setInsertIntoStatment(String insertIntoStatment) {
		this.insertIntoStatment = insertIntoStatment;
	}

	 


	/**
	 * nama columns yang jadi primary key
	 **/
	public String[] getPrimaryKeyColumns() {
		return primaryKeyColumns;
	}
	/**
	 * nama columns yang jadi primary key
	 **/
	public void setPrimaryKeyColumns(String[] primaryKeyColumns) {
		this.primaryKeyColumns = primaryKeyColumns;
	}
	
	/**
	 * nama table yang di archive
	 **/
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * nama table yang di archive
	 **/
	public String getTableName() {
		return tableName;
	}
	@Override
	public void putAdditionaFields(JobParametersBuilder jobParameterBuilder)  throws Exception{
		super.putAdditionaFields(jobParameterBuilder);
		StringBuffer bfr = new StringBuffer(); 
		if ( primaryKeyColumns!= null && primaryKeyColumns.length>0){
			for ( String scn :primaryKeyColumns){
				if ( bfr.length()>0)
					bfr.append(",");
				bfr.append(scn);
			}
			jobParameterBuilder.addString(PK_COL_KEY + getSequenceNumber(), bfr.toString());
		}
		else{
			throw new Exception("anda tidak mengirimkan priamry key dari table dalamparameter anda. kami tidak bisa melanjutkan proses archive"); 
		}
		if ( this.queryParamaters!= null && this.queryParamaters.length>0){
			jobParameterBuilder.addLong(COUNT_SQL_PARAMFLAG_KEY,(long) queryParamaters.length); 
			for ( int i = 0 ; i < this.queryParamaters.length; i++){
				SQLParameterHolder dt = queryParamaters[i];
				 
				String paramName =  dt.getParamName() ; 
				String key = SQL_PARAM_NAME_KEY + getSequenceNumber() + i; 
				 jobParameterBuilder.addString(key,paramName); 
				switch ( dt.getParamFqcn()){
					case "java.lang.Long":
						jobParameterBuilder.addLong(SQL_PARAM_VALUE_KEY + getSequenceNumber() + i ,(Long) dt.getParameter());
						jobParameterBuilder.addString(SQL_PARAM_DATA_TYPE_KEY + getSequenceNumber() + i , dt.getParamFqcn());
						break ;  
					case 	"java.util.Date":
						jobParameterBuilder.addDate(SQL_PARAM_VALUE_KEY + getSequenceNumber() + i ,(Date) dt.getParameter());
						jobParameterBuilder.addString(SQL_PARAM_DATA_TYPE_KEY + getSequenceNumber() + i , dt.getParamFqcn());
						break ; 
					case "java.lang.String":
						jobParameterBuilder.addString(SQL_PARAM_VALUE_KEY + getSequenceNumber() + i ,(String) dt.getParameter());
						jobParameterBuilder.addString(SQL_PARAM_DATA_TYPE_KEY + getSequenceNumber() + i , dt.getParamFqcn());
						break ; 
					case "java.lang.Double":
						jobParameterBuilder.addDouble(SQL_PARAM_VALUE_KEY + getSequenceNumber() + i ,(Double) dt.getParameter());
						jobParameterBuilder.addString(SQL_PARAM_DATA_TYPE_KEY + getSequenceNumber() + i , dt.getParamFqcn());
						break ; 
					
				}
			}
		}
		else{
			jobParameterBuilder.addLong(COUNT_SQL_PARAMFLAG_KEY,(long) 0); 
		}
		
		
		
	}
	@Override
	public void fetchAdditionalData(JobParameters params) {
		super.fetchAdditionalData(params);
		String s = params.getString(PK_COL_KEY + getSequenceNumber());
		if (s!= null && s.length()>0){
			primaryKeyColumns=  s.split(",");
		}
		long cnt = params.getLong(COUNT_SQL_PARAMFLAG_KEY); 
		if ( cnt> 0 ){
			Integer seq = getSequenceNumber();
			if ( seq== null)
				seq = 0 ; 
			int len = (int)cnt; 
			this.queryParamaters = new SQLParameterHolder[len]; 
			for ( int i=0 ; i< len  ; i++){
				 String paramKey = SQL_PARAM_VALUE_KEY + seq + i ; 
				 String fqcnKey = SQL_PARAM_DATA_TYPE_KEY + seq + i ; 
				 String paramNameKey = SQL_PARAM_NAME_KEY + seq + i ; 
				 
				 
				 String fqcn = params.getString(fqcnKey); 
				 String paramName = params.getString(paramNameKey); 
				 
				 queryParamaters[i]  = new SQLParameterHolder(); 
				 queryParamaters[i].setParamName(paramName);
				 queryParamaters[i].setParamFqcn(fqcn);
				 
				 switch ( fqcn){
					case "java.lang.Long":
						queryParamaters[i].setParameter(params.getLong(paramKey));
						break ;  
					case 	"java.util.Date":
						queryParamaters[i].setParameter(params.getDate(paramKey));
						break ; 
					case "java.lang.String":
						queryParamaters[i].setParameter(params.getString(paramKey));
						break ; 
					case "java.lang.Double":
						queryParamaters[i].setParameter(params.getDouble(paramKey));
						break ; 
					
				}
				 
			}
		}
		System.out.println(this.toString());
	}
	
	
	/**
	 * parameter query 
	 **/
	public SQLParameterHolder[] getQueryParamaters() {
		return queryParamaters;
	}
	/**
	 * parameter query 
	 **/
	public void setQueryParamaters(SQLParameterHolder[] queryParamaters) {
		this.queryParamaters = queryParamaters;
	}

}
