package id.co.sigma.common.server.dao.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Query;

/**
 * generic dao. ini asumsi nya entity class masih mempergunakan pendekatan JPA 1.0(tidak ada class dengan annotation {@link Id} lebih dari 1 buah dalam 1 class) 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa(gede.sutarsa@gmail.com)</a>
 * @version $Id
 * @param <PK> primary key dari object entity
 * @param <DOMAIN> domain object yang di handle generic clas
 **/
public abstract class BaseGenericDao<  PK extends Serializable , DOMAIN extends Serializable> extends BaseJPADao {


	
	/**
	 * load data dengan primary keys
	 * @param keys daftar primary key yang hendak di load
	 **/
	@SuppressWarnings("unchecked")
	public List<DOMAIN> loadDataByKeys(List<PK> keys) throws Exception{
		return super.loadDataByKeys(getEntityClass(), getPrimaryKeyPropertyName(), (List<Serializable>) keys); 
	} 
	
	
	
	
	
	
	/**
	 * hapus list of object
	 * @param keys list object key yang akandi hapus
	 * @return total row yang terhapus
	 **/
	public int deleteByIds(List<PK> keys) throws Exception{
		if ( keys==null|| keys.isEmpty())
			return 0; 
		
		String eraseQuery = "";
		String pkPropName = getPrimaryKeyPropertyName(); 
		int max = keys.size(); 
		for ( int i= 0 ; i<max;i++){
			if (eraseQuery.length()>0)
				eraseQuery+=" OR ";
			eraseQuery +="( " + pkPropName + "=:PARAM" + i +")"; 
		}
		Query q=  getEntityManager().createQuery("DELETE from " + getEntityClass().getName()  + " WHERE " +  eraseQuery);
		int i = 0 ; 
		for ( PK scn : keys){
			q.setParameter("PARAM" + i++, scn); 
		}
		return q.executeUpdate(); 
	}
	 
	
	/**
	 * hapus data dengan primary key dari object
	 **/
	public int deleteById(PK key) throws Exception{
		return getEntityManager().createQuery("delete from " + getEntityClass().getName() + " where " + getPrimaryKeyPropertyName() +"=:PARAM")
		.setParameter("PARAM", key).executeUpdate(); 
	}
	
	
	
	/**
	 * entity class yang di handle
	 **/
	protected abstract Class<DOMAIN> getEntityClass();
	

	/**
	 * nama primary key property
	 **/
	protected  String getPrimaryKeyPropertyName (){
		return "id"; 
	} 
}
