package id.co.sigma.commonlib.base;



import id.co.sigma.commonlib.util.IQueryParameterHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;



/**
 * base class untuk JDBC driven item reader. 
 * yang di sediakan di sini adalah : 
 * <ol>
 * <li>proses normalisasi query yang pakai parameter <i>:PARAM</i> menjadi notasi jdbc (?)</li>
 * <li>parameter setter</li>
 * </ol>
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @category spring-batch:reader
 **/
public abstract class BaseParameterizedJDBCItemReader<DATA> extends BaseSimpleParameterizedJDBCItemReader<DATA> {

	
	
	private int counterSetStatementParameter = 1 ; 
	/**
	 * parameter holder
	 **/
	@Autowired
	@Qualifier(value="batch_engine_reader_param_holder")
	protected IQueryParameterHolder queryParameterHolder ; 
	
	/**
	 * variable pada scope step, ini untuk mengambil variable parameter untuk select statement yang mentrigger statement select proses pembacaan data jurnal
	 **/
	protected String selectStatementParameterGroupKey ; 
	
	
	
	 
	
	
	public BaseParameterizedJDBCItemReader() {
		super() ; 
		setPreparedStatementSetter(sourceDataPreparedStatementSetter);
		setRowMapper(generateRowMapper()); 
	}
	
	/**
	 * variable pada scope step, ini untuk mengambil variable parameter untuk select statement yang mentrigger statement select proses pembacaan data jurnal
	 **/
	public void setSelectStatementParameterGroupKey(
			String selectStatementParameterGroupKey) {
		System.out.println("set parameter denggan index counter :" + counterSetStatementParameter );
		counterSetStatementParameter++; 
		this.selectStatementParameterGroupKey = selectStatementParameterGroupKey;
		this.sourceDataPreparedStatementSetter.setSelectStatementParameterGroupKey(selectStatementParameterGroupKey);
		this.sourceDataPreparedStatementSetter.setValueDataTypes(this.queryParameterHolder.getParamTypeMap(selectStatementParameterGroupKey));
		this.sourceDataPreparedStatementSetter.setIndexedStatementParam(this.queryParameterHolder.get(this.selectStatementParameterGroupKey));
	}
	
	
	
	/**
	 * row mapper. pls provide this. ini untuk translasi data menjadi yang di perlukan dari resultset
	 **/
	protected abstract RowMapper<DATA> generateRowMapper () ; 
	
	
	
	
	/**
	 * Set SQL. ini otomatis juga akan me run normalisasi data
	 **/
	@Override
	public void setSql(String sql) {
		this.readSelectStatementMetaData =  normalizeSqlStatement(sql); 
		this.sourceDataPreparedStatementSetter.setNamedParameters(readSelectStatementMetaData.getNamedParameterArray());
		String sqlSmt =readSelectStatementMetaData.getNormalizeStatement() ;
		System.out.println("sql : " + sqlSmt);
		super.setSql(sqlSmt);
		
	}
	
}
