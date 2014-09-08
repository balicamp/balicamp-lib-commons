package id.co.sigma.common.server.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.dao.base.BaseJPADao;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class GeneralPurposeDaoImpl extends BaseJPADao implements IGeneralPurposeDao{

	
	static final String PREFIX_IN_PARAM ="INPRM"; 
	
	
	@Override
	public int updateBulkDataWithHQLStatement(
			Class<? extends Serializable> jpaClass,
			Map<String, Object> updatedFieldKeyByJPQField,
			String primaryKeyJpaFieldName, Collection<Serializable> primaryKeys) {
		if ( primaryKeys== null || primaryKeys.isEmpty() || updatedFieldKeyByJPQField == null || updatedFieldKeyByJPQField.isEmpty())
			return 0 ; 
		StringBuffer buff = new StringBuffer("UPDATE " + jpaClass.getName() + " SET ");
		boolean first = true ; 
		for (String key : updatedFieldKeyByJPQField.keySet()){
			if ( !first)
				buff.append(","); 
			buff.append(key);
			buff.append("="); 
			buff.append(":" + key.toUpperCase());
			first= false ; 
		}
		buff.append(" WHERE " + primaryKeyJpaFieldName +" IN ( ");
		
		boolean firstIn = true ;
		for ( int i = 0 ; i < primaryKeys.size() ; i++){
			if ( !firstIn)
				buff.append(",");
			buff.append(":" + PREFIX_IN_PARAM + i +", ");
			
			first= false ;
		}
		Query q = getEntityManager().createQuery(buff.substring(0, buff.length()-2)+")");
		
		
		for (String key : updatedFieldKeyByJPQField.keySet()){
			q = q.setParameter(key.toUpperCase()  , updatedFieldKeyByJPQField.get(key));
		}
		
		int i = 0 ;
		for (Serializable s :  primaryKeys  ){
			q = q.setParameter(PREFIX_IN_PARAM + i  , s);
			i++  ;
		}
		return q.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Serializable> runSelectHQL(String hql) {
		Query query  = getEntityManager().createQuery(hql);
		
		return query.getResultList();
	}

	@Override
	public int delete(Class<?> clzz, String primaryKeyFieldName,
			String[] primaryKeys) {
		if ( primaryKeys== null||primaryKeys.length==0)
			return 0 ;
		String delSmt = "delete from " + clzz.getName() + " where " + primaryKeyFieldName + " in ("  ;
		for ( int j = 0 ; j< primaryKeys.length ; j++){
			if ( j> 0)
				delSmt +=",";
			delSmt+= ":PRM" + j ;
		}
		delSmt +=" ) ";
		
		
		int i = 0 ; 
		Query q = getEntityManager().createQuery(delSmt);
		i = 0 ; 
		for ( String scn : primaryKeys) {
			q = q.setParameter("PRM" + i, scn); 
			i++ ;
		}
		return q.executeUpdate();
	}

	
}
