package id.co.sigma.commonlib.importengine.io;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import id.co.sigma.commonlib.importengine.data.SimpleFlatFileData;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;



/**
 * special jdbc setter. pekerjanaannya : mengeset JDBC param, dengan array object dalam class {@link SimpleFlatFileData#getData()}. 
 * sql statement parameter berbasis index (pakai ? index dari 1  )
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @deprecated tidak di pergunakan, sebab parameter data bisa tidak linear
 **/
@Deprecated
public class SimpleFlatFileDataReparedStatementSetter implements ItemPreparedStatementSetter<SimpleFlatFileData>{

	public void setValues(SimpleFlatFileData data, PreparedStatement statement)
			throws SQLException {
		if ( data==null)
			return ; 
		Object[] arrOfArg = data.getData();
		if ( arrOfArg==null||arrOfArg.length==0)
			return;
		for ( int i=1 ; i<=arrOfArg.length;i++){
			statement.setObject(i, arrOfArg[i-1]);
		}
		
	}

}
