package id.co.sigma.common.server.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * Dao general purpose. di bikin generic agar tidak ada yang tight ke manapun
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @author Wayan Ari Agustina
 */
public interface IGeneralPurposeDao extends IBaseDao{
	
	
	
	/**
	 * update data masif dengan in statement. contoh nya : <br/>
	 * update person set a=1 where id in ( :PARAM1 , :PARAM2)<br/> 
	 * ini akan lebih efisien karena data di update dengan sql(via JPA)
	 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
	 * @param jpaClass class yang di update
	 * @param updatedFieldKeyByJPQField field yang hendak di update. key = nama field JPA , value = nilai yang hendak di set ke dalam data
	 * @param primaryKeyJpaFieldName nama field primary key. ini key untuk proses update
	 * @param primaryKeys primary keys dari object yang hendak di update
	 */
	public int updateBulkDataWithHQLStatement (Class<? extends Serializable> jpaClass , Map<String, Object> updatedFieldKeyByJPQField , 
			String primaryKeyJpaFieldName  , Collection<Serializable> primaryKeys )  ;

	public List<? extends Serializable> runSelectHQL(String hql); 
	
	
	/**
	 * hapus data dengan primary keys string
	 * @param clzz class JPA yang perlu di hapus
	 * @param primaryKeyFieldName primary key field name
	 * @param primaryKeys primary keys yang hendak di hapus 
	 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a> 
	 */
	public int delete ( Class<?> clzz ,String primaryKeyFieldName ,  String[] primaryKeys );
	

}
