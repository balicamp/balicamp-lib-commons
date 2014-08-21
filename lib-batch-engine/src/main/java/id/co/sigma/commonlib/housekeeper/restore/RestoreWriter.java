package id.co.sigma.commonlib.housekeeper.restore;



import id.co.sigma.commonlib.base.ReadSelectStatementMetaData;
import id.co.sigma.commonlib.util.CommonBatchUtil;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

/**
 * writer 
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class RestoreWriter extends JdbcBatchItemWriter<Map< String, Object>> {
	
	
	 
	
	
	/**
	 * metadata insert into statment
	 **/
	private ReadSelectStatementMetaData insertIntoMetadata ; 
	
	
	
	public RestoreWriter(){
		super() ; 
		
		setSql("insert dummy");
		setItemPreparedStatementSetter(new ItemPreparedStatementSetter<Map<String,Object>>() {
			
			@Override
			public void setValues(Map<String, Object> item, PreparedStatement ps)
					throws SQLException {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	@BeforeStep
	protected void beforeStepHandler( StepExecution stepExecution ) throws Exception {
		final RestoreParameterDetail  p = (RestoreParameterDetail)stepExecution.getJobExecution().getExecutionContext().get(RestoreParameterHeader.CURRENT_RESTORE_PARAM_KEY);
		String sqlStatement =  p.getInsertIntoStatement();
		insertIntoMetadata = CommonBatchUtil.getInstance().normalizeSqlStatement(sqlStatement);
		
		setSql(insertIntoMetadata.getNormalizeStatement());
		System.out.println("insert statment is : " + insertIntoMetadata.getNormalizeStatement());
		
		
		final String[] parameterNames = insertIntoMetadata.getNamedParameterArray(); 
		final int lebar = parameterNames.length; 
		setItemPreparedStatementSetter(new ItemPreparedStatementSetter<Map<String,Object>>() {
			
			@Override
			public void setValues(Map<String, Object> item, PreparedStatement ps)
					throws SQLException {
				 
				for ( int i= 1; i<= lebar;i++){
					String key = parameterNames[i-1]; 
					Object val = item.get(key);
					if ( val!= null  ){
						if ( val instanceof BigInteger){
							val = ((BigInteger)val).longValue();
						}
						else if ( val instanceof Date){
							val = new java.sql.Date( ((Date)val).getTime());
						}
						
					}
					Integer tipeSql = p.getDataTypeFields().get(key);
					ps.setObject(i, val , tipeSql);
				}
				
			}
		});
		
	}
	

}
