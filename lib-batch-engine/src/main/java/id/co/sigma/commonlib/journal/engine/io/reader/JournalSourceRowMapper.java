package id.co.sigma.commonlib.journal.engine.io.reader;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import id.co.sigma.commonlib.journal.engine.data.SourceJournalRawData;

import org.springframework.jdbc.core.RowMapper;

public class JournalSourceRowMapper implements RowMapper<SourceJournalRawData>{

	@Override
	public SourceJournalRawData mapRow(ResultSet resultSet, int rowIndex)
			throws SQLException {
		SourceJournalRawData retval = new SourceJournalRawData(); 
		
		ResultSetMetaData mt =   resultSet.getMetaData();
		
		int cnt =  mt.getColumnCount()  ;
		for ( int i=1; i<=cnt ; i++){
			retval.put(mt.getColumnName(i)  ,  resultSet.getObject(i));
		}
		return retval;
	}

}
