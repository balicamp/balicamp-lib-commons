package id.co.sigma.common.server.service.datagrid.impl;





import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import id.co.sigma.common.data.query.BaseGridDataProviderNativeSql;



/**
 * common , reuseable class provider native data grid provider. ini bisa di ganti-ganti {@link BaseGridDataProviderNativeSql} , dengan setter nya. Sehingga bean instance nya bisa di reuse bersama class-class lain nya
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
@Service
@Qualifier(value="common-native-sql-jsondatagrid-dao")
public class CommonNativeSqlJSONDataGridDaoLayer extends BaseNativeSqlJSONDataGridDaoLayer{
	
	
	
	
	private BaseGridDataProviderNativeSql queryProvider ; 
	
	
	
	public void setQueryProvider(BaseGridDataProviderNativeSql queryProvider) {
		this.queryProvider = queryProvider;
	}
	
	@Override
	protected BaseGridDataProviderNativeSql getQueryProvider() {
		return queryProvider;
	}
	

}
