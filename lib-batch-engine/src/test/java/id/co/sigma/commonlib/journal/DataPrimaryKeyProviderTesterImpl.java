package id.co.sigma.commonlib.journal;


import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import id.co.sigma.commonlib.journal.engine.IDataPrimaryKeyProvider;


public class DataPrimaryKeyProviderTesterImpl implements IDataPrimaryKeyProvider{

	
	
	
	@Autowired
	private DataSource datasource ; 
	
	@Override
	public Long getDataKey(String tableName, int numberOfRequeiredKey) {
	
			CallableStatement upperProc =null ; 
		 try {
		
			upperProc = datasource.getConnection().prepareCall("{ ? = call sequence_increment( ? , ?) }");
			upperProc.registerOutParameter(1, Types.INTEGER);
			upperProc.setString(2, tableName.toLowerCase() +"_seq"); 
			upperProc.setInt(3, numberOfRequeiredKey); 
			upperProc.execute();
			Integer swap = upperProc.getInt(1); 
			return new Long(swap); 
			
		} catch (Exception e) {
			e.printStackTrace(); 
			return null ; 
		}
		finally {
			if ( upperProc!=null){
				try {
					upperProc.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		//JdbcTemplate tmpl = new JdbcTemplate(datasource); 
		/*
		 SimpleJdbcCall proc = new SimpleJdbcCall(datasource).withFunctionName("sequence_increment"); 
		SqlParameterSource in = new MapSqlParameterSource()
        	.addValue("1", tableName + "_seq")
        	.addValue("2", numberOfRequeiredKey);
		
		return proc.executeFunction(Long.class, in);  
		*/
		
	}

}
