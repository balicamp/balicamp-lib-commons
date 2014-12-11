package id.co.sigma.common.server.dao.base;


import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.data.query.SimpleQueryFilterOperator;
import id.co.sigma.common.server.dao.IBaseDao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;


/**
 * base class untuk dao JPA
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa(gede.sutarsa@gmail.com)</a>
 *@version $Id; 
 **/
public abstract class BaseJPADao extends SharedPartBaseDao implements IBaseDao{
	
	
	
	
	/**
	 * entity manager
	 **/
	@PersistenceContext
	@Qualifier(value="entityManagerFactory")
	private EntityManager entityManager; 
	
	
	
	
	
	
	
	
	/**
	 * entity manager
	 **/
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	
	
	
	/**
	 * get data by primary key 
	 **/
	@SuppressWarnings("unchecked")
	public <DOMAIN> DOMAIN get(  Class<? >  classType  ,   Serializable key) throws Exception{
		try {
			return (DOMAIN) getEntityManager().find(classType, key); 
		} 
		catch ( NoResultException noExc ){
			return null ; // 0 record bukan nya error mustinya nya aneh sekali
		}
		catch (Exception e) {
			throw e ; 
		}
		
	}
	
	
	
	
	
	/**
	 * load data dengan primary keys
	 * @param classType tipe class entity
	 * @param primaryKeyPropertyName nama field prmary key
	 * @param keys list of primary keys
	 **/
	@SuppressWarnings("unchecked")
	public <DOMAIN> List<DOMAIN> loadDataByKeys(  Class<? >  classType  , String primaryKeyPropertyName ,  List<Serializable> keys) throws Exception{
		return  loadDataByGenericKeys(classType, primaryKeyPropertyName, keys); 
	} 
	
	
	
	public <DOMAIN> List<DOMAIN> loadDataByGenericKeys(  Class<? >  classType  , String primaryKeyPropertyName ,  List<? extends Serializable> keys) throws Exception{
		if ( keys==null || keys.isEmpty())
			return null ;
		String querytext  = "";
		int max = keys.size();  
		for ( int i=0; i< max ; i++){
			if ( querytext.length()>0)
				querytext +=" or "; 
			querytext +=" (a." + primaryKeyPropertyName +"=:PARAM" + i + ") ";
		}
		Query q = getEntityManager().createQuery(" SELECT a from " + classType.getName()  +" a where " +  querytext) ;
		int i=0; 
		for ( Serializable key : keys){
			q= q.setParameter("PARAM" + i, key); 
			i++; 
		}
		
		return q.getResultList() ; 
	} 
	
	
	
	
	
	
	
	
	
	/**
	 * hapus object dari dalam database
	 *@param object object(entity object) yang di hapus
	 **/
	public void delete (Serializable object) throws Exception{
		entityManager.remove(object);
	}
	
	
	
	/**
	 * hapus bulk
	 **/
	public void deleteObjects ( Collection<? extends Serializable> objects) throws Exception{
		if ( objects==null||objects.isEmpty())
			return ; 
		for (Serializable scn : objects){
			entityManager.remove(scn);
		}
	}
	
	
	@Override
	public Integer delete(Class<?> objectClass, Serializable pk, String pkFieldName) {
		String deleteSmt = "delete from "  + objectClass.getName() + " where " + pkFieldName +"=:PK";
		return getEntityManager().createQuery(deleteSmt).setParameter("PK", pk).executeUpdate(); 
		
	}
	
	
	
	
	/**
	 * insert 1 data dalam database
	 * @param object object yang di insert
	 **/
	public void insert(Serializable object)throws Exception{
		entityManager.merge(object);		
		/**
		 * 
		 */
	}
	
	
	/**
	 * insert 1 data dalam database. isue flush command
	 * @param object object yang di insert
	 **/
	public void insertAndFlush(Serializable object)throws Exception{
		entityManager.merge(object);
		entityManager.flush(); 
		System.out.println("xxx");
	}
	
	/**
	 * delete  bulk dengan n data pada Collection  
	 * 
	 * @param objects
	 * @throws Exception
	 */
	public void delete(Collection<? extends Serializable> objects )throws Exception{
		if ( objects==null||objects.isEmpty())
			return ; 
		int counter = 1; 
		
		boolean justFlush = true ; 
		for  ( Object scn : objects){
			justFlush= false; 
			getEntityManager().remove(scn);
			counter++; 
			if ( counter==MAX_BATCH_INSERT_SIZE){
				getEntityManager().flush();
				counter=1; 
				justFlush=true; 
			}
		}
		if ( !justFlush)
			getEntityManager().flush(); 
	} 
	
	@Override
	public Integer deleteByParentId(Class<?> classToDelete, BigInteger parentId,
			String parentFieldName) {
		String delSmt ="delete from " + classToDelete.getName() + " where " + parentFieldName + "= :PARENT_ID "; 
		return getEntityManager()
					.createQuery(delSmt)
					.setParameter("PARENT_ID", parentId)
					.executeUpdate(); 
	}
	
	
	@Override
	public Integer deleteByParentId(Class<?> classToDelete, Integer parentId,
			String parentFieldName) {
		String delSmt ="delete from " + classToDelete.getName() + " where " + parentFieldName + "= :PARENT_ID "; 
		return getEntityManager()
					.createQuery(delSmt)
					.setParameter("PARENT_ID", parentId)
					.executeUpdate(); 
	
	}
	
	
	
	@Override
	public Integer deleteByParentId(Class<?> classToDelete, Long parentId,
			String parentFieldName) {
		String delSmt ="delete from " + classToDelete.getName() + " where " + parentFieldName + "= :PARENT_ID "; 
		return getEntityManager()
					.createQuery(delSmt)
					.setParameter("PARENT_ID", parentId)
					.executeUpdate();
		
	}
	
	
	@Override
	public Integer deleteByParentId(Class<?> classToDelete, String parentId,
			String parentFieldName) {
		String delSmt ="delete from " + classToDelete.getName() + " where " + parentFieldName + "= :PARENT_ID "; 
		return getEntityManager()
					.createQuery(delSmt)
					.setParameter("PARENT_ID", parentId)
					.executeUpdate();
		
	}
	/**
	 * update 1 data
	 * @param object object yang 
	 **/
	
	
	/**
	 * bulk insert data 
	 **/
	public <DATA extends Serializable> void inserts(Collection<DATA> objects )throws Exception{
		if ( objects==null||objects.isEmpty())
			return ; 
		int counter = 1; 
		boolean justFlush = true ; 
		for  ( Object scn : objects){
			justFlush= false; 
			getEntityManager().persist(scn);
			counter++; 
			if ( counter==MAX_BATCH_INSERT_SIZE){
				getEntityManager().flush();
				counter=1; 
				justFlush=true; 
			}
		}
		if ( !justFlush)
			getEntityManager().flush(); 
	} 
	
	/**
	 * update 1 data
	 * @param object object yang 
	 **/
	public void update(Serializable object)throws Exception{
		entityManager.merge(object);
	}
	
	public void updateObjects( Collection<? extends Serializable> objects)throws Exception{
		if ( objects==null||objects.isEmpty())
			return ; 
		for ( Serializable scn :objects ){
			entityManager.merge(scn);	
		}
		entityManager.flush();
		
	}
	
	
	
	/**
	 * update data dengan entity class +  filter query. filter hanya bisa bekerja untuk field yang simple. field yang berada dalam table itu. 
	 * sebab tidak semua database engine support update join. mohon berhati-hati dalam mempergunakan method ini 
	 * @param entityClass entity class yang hendak di update
	 */
	public int updateData ( Class<?> entityClass , SimpleKeyValueParameter[] updatedFields , SimpleQueryFilter [] filters ){
		Query updQuery =  getEntityManager().createQuery(generateUpdateStatement(entityClass, updatedFields, filters));
		updQuery = putQueryArguments(updQuery, filters); 
		for ( SimpleKeyValueParameter scn  : updatedFields){
			updQuery = updQuery.setParameter(scn.getFieldName().toUpperCase(), scn.getParameter()); 
		}
		return updQuery.executeUpdate(); 
		
	}
	
	
	
	
	
	
	
	/**
	 * update 1 data
	 * @param object object yang 
	 **/
	public void updateAndFlush(Serializable object)throws Exception{
		entityManager.merge(object);
		entityManager.flush(); 
	}
	
	
	
	/**
	 * bulk update. ini regular flush friendly
	 **/
	public<DATA extends Serializable> void updates(Collection<DATA> objects) throws Exception{
		if ( objects==null||objects.isEmpty())
			return ; 
		int counter = 1; 
		boolean justFlush = true ; 
		for ( DATA scn : objects){
			justFlush= false;
			getEntityManager().merge(scn); 
			counter++; 
			if ( counter==MAX_BATCH_INSERT_SIZE){
				getEntityManager().flush();
				counter=1; 
				justFlush=true; 
			}
		}
		if ( !justFlush)
			getEntityManager().flush();
	}
	
	
	
	

	/**
	 * put parameter pada query object
	 * @param query query object yang di set parameter nya
	 * @param paramPrefix prefix parameter cenderung jadi (:PARAM0,:PARAM1)-> paramPrefix=PARAM
	 * @param params parameters yang akan di set ke dalam query 
	 **/
	protected Query fillInParams(Query query, String paramPrefix , Collection<?> params) { 
		if (params==null||params.isEmpty())
			return query; 
		int i=0;
		String param = "";
		for ( Object obj : params){
			param = paramPrefix+(i++);
			query.setParameter(param, obj);
		}
		return query;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * worker untuk build order by statement
	 * @param tableAlias nama table alias
	 * @param sortArgs array or sort arguments
	 *  
	 *  
	 **/
	protected String buildOrderByStatement(String tableAlias ,SimpleSortArgument[] sortArgs  ) {
		if ( sortArgs==null||sortArgs.length==0)
			return "" ; 
		String builder = "" ; 
		for ( SimpleSortArgument scn : sortArgs){
			builder += tableAlias + "." + scn.getSortField() + " " + (  scn.isAscendingSort()? " asc " : "desc");
			builder += ",";
		}
		builder =  builder.substring(0, builder.length()-1);
		return " ORDER BY " + builder ; 
	}

	
	
	/**
	 * inquery filter dengan berdasar pada fiedld filter(ini sesuai dengan entry fields)
	 * @param query query, ini untuk di set value nya
	 * @param filters data filters
	 * @see {@link SimpleQueryFilter#getFilterTypeClass()} --> sebelum di set ke query param tipe data harus di rubah dulu 
	 **/
	protected Query putQueryArguments(Query query , SimpleQueryFilter[] filters){
		if ( filters==null||filters.length==0)
			return query;
		
		int i=0;
		for ( SimpleQueryFilter scn : filters){
			if ( (scn.getFilter()==null||scn.getFilter().length()==0)&& (
					
					SimpleQueryFilterOperator.likeBothSide.equals(  scn.getOperator())||
					SimpleQueryFilterOperator.likeFrontOnly.equals(  scn.getOperator())||
					SimpleQueryFilterOperator.likeTailOnly.equals(  scn.getOperator())
					
				))
				continue ;// ini di abaikan
			if ( scn.getFilter()!=null&& scn.getFilter().length()>0){
				if ( SimpleQueryFilterOperator.likeBothSide.equals(   scn.getOperator())){
					query = query.setParameter(AUTO_PARAM_PREFIX + i, "%" + scn.getFilter().toString() + "%");
				}
				else if (SimpleQueryFilterOperator.likeFrontOnly.equals(scn.getOperator())){
					query = query.setParameter(AUTO_PARAM_PREFIX + i, "%" + scn.getFilter().toString() );
				}
				else if ( SimpleQueryFilterOperator.likeTailOnly.equals(scn.getOperator())){
					query = query.setParameter(AUTO_PARAM_PREFIX + i,   scn.getFilter().toString() +"%");
				}
				else if (SimpleQueryFilterOperator.fieldIsNull.equals(scn.getOperator()) || SimpleQueryFilterOperator.fieldIsNotNull.equals(scn.getOperator()) 
						|| SimpleQueryFilterOperator.fieldIn.equals(scn.getOperator()) || SimpleQueryFilterOperator.fieldNotIn.equals(scn.getOperator())
						|| SimpleQueryFilterOperator.dateBetween.equals(scn.getOperator()) || SimpleQueryFilterOperator.hourIn.equals(scn.getOperator())) {
					//SING perlu ngudiang ngudiang
				}
				else{
					Object param =null ;
					if (BigInteger.class.getCanonicalName().equals(scn.getFilterTypeClass()))
						param=new BigInteger(scn.getFilter());
					else if (Integer.class.getCanonicalName().equals(scn.getFilterTypeClass()))
						param=new Integer(scn.getFilter());
					else if (Long.class.getCanonicalName().equals(scn.getFilterTypeClass()))
						param=new Long(scn.getFilter());
					else if (BigDecimal.class.getCanonicalName().equals(scn.getFilterTypeClass()))
						param=new BigDecimal(scn.getFilter());
					else if (Double.class.getCanonicalName().equals(scn.getFilterTypeClass()))
						param=new Double(scn.getFilter());
					else if (Float.class.getCanonicalName().equals(scn.getFilterTypeClass()))
						param=new Float(scn.getFilter());
					else if (Boolean.class.getCanonicalName().equals(scn.getFilterTypeClass())) {
						if (scn.getFilter().equalsIgnoreCase("true"))
							param = "Y";
						else
							param = "N";
					} else if (Date.class.getCanonicalName().equals(scn.getFilterTypeClass())) {
						try {
							
							String dt = scn.getFilter() ; 
							if ( dt.length()==10)
								param = (Date) DATE_FORMATTER_SHORT.parse(dt); 
							else
								param = (Date) dateFormatter.parse(scn.getFilter());
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else {
						param = scn.getFilter();
					}
					//FIXME : ganti tipe data sesuai dengan @see {@link SimpleQueryFilter#getFilterTypeClass()} 
					query = query.setParameter(AUTO_PARAM_PREFIX + i,   param);
				}
				i++;
			}
			
		}
		return query ; 
	}
	
	
	
	

	
	
	
	@SuppressWarnings("unchecked")
	public <DBDATA> List<Object[]> list(
			Class<? extends DBDATA> dataObjectClass, String[] fetchedFields,
			SimpleQueryFilter[] filters,
			SimpleSortArgument[] sortArguments, int firstRowPosition,
			int pageSize, String currentUserId ) throws Exception {
		String slct = "A.id" ; 
		for( String scn : fetchedFields){
			
			slct+=",A." + scn ;
		}
		
		
		String hql  = "SELECT " +slct+"  from " + dataObjectClass.getName() + " A WHERE 1=1 "  +  buildWhereStatement("A", filters) + " " + buildOrderByStatement("A", sortArguments) ;
		Query q =  getEntityManager().createQuery(hql);
		
		q= putQueryArguments(q, filters);
		q = q.setFirstResult(firstRowPosition).setMaxResults(pageSize);
		try {
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	
	/**
	 * membaca data dengan simple filter. tidak ada paging di sini
	 * @param entityClass entity class yang di baca
	 * @param filters filter. simple filter, operator adalah AND antara filter
	 * @param sortArguments sort order dari data 
	 * 
	 **/
	@SuppressWarnings("unchecked")
	public <DATA> List<DATA> list(Class<? extends DATA> entityClass , SimpleQueryFilter[] filters, SimpleSortArgument[] sortArguments ) throws Exception {
		String hql = "select a from " + entityClass.getName() + " a where 1=1 " + buildWhereStatement("a", filters) + buildOrderByStatement("a", sortArguments); 
		Query q =  getEntityManager().createQuery(hql);
		q = putQueryArguments(q, filters); 
		return q.getResultList(); 
		
		
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * membaca data dengan simple filter
	 **/
	@SuppressWarnings("unchecked")
	public <DATA> List<DATA> list(Class<? extends DATA> entityClass , SimpleQueryFilter[] filters, SimpleSortArgument[] sortArguments,  int pageSize , int firstRowPosition) throws Exception{
		String countSmt = "SELECT a  from " + entityClass.getName() + " a where 1=1  " + buildWhereStatement("a", filters) + buildOrderByStatement("a", sortArguments); 
		Query q =  getEntityManager().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters).setMaxResults(pageSize).setFirstResult(firstRowPosition);
		return q.getResultList();
		
	}
	
	
	
	
	
	
	/**
	 * membaca data dengan simple filter
	 **/
	@SuppressWarnings("unchecked")
	public <DATA> List<DATA> list( String tableNameAndJoinArgument , String primaryTableNameAlias , SimpleQueryFilter[] filters, SimpleSortArgument[] sortArguments,  int pageSize , int firstRowPosition) throws Exception{
		String countSmt = "SELECT  "+primaryTableNameAlias+"  from " + tableNameAndJoinArgument + "  where 1=1  " + buildWhereStatement(primaryTableNameAlias, filters) + buildOrderByStatement(primaryTableNameAlias, sortArguments); 
		Query q =  getEntityManager().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters).setMaxResults(pageSize).setFirstResult(firstRowPosition);
		List<DATA> retval =  q.getResultList();
		return retval ; 
		
	}
	
	
	@Override
	public <DATA> List<DATA> list(String tableNameAndJoinArgument,
			String primaryTableNameAlias, String predefinedWhere,
			SimpleQueryFilter[] filters, SimpleSortArgument[] sortArguments)
			throws Exception {
		String countSmt = "SELECT  "+primaryTableNameAlias+"  from " + tableNameAndJoinArgument + "  where 1=1  AND (" +  predefinedWhere+  ")" + buildWhereStatement(primaryTableNameAlias, filters) + buildOrderByStatement(primaryTableNameAlias, sortArguments); 
		Query q =  getEntityManager().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters); 
		List<DATA> retval =  q.getResultList();
		return retval ; 
	}
	
	
	@Override
	public <DATA> List<DATA> list(String tableNameAndJoinArgument,
			String primaryTableNameAlias, String predefinedWhere,
			SimpleQueryFilter[] filters, SimpleSortArgument[] sortArguments,
			int pageSize, int firstRowPosition) throws Exception {
		if ( predefinedWhere== null || predefinedWhere.isEmpty())
			predefinedWhere =" 1=1 " ; 
		String countSmt = "SELECT  "+primaryTableNameAlias+"  from " + tableNameAndJoinArgument + "  where 1=1  AND (" + predefinedWhere +") " + buildWhereStatement(primaryTableNameAlias, filters) + buildOrderByStatement(primaryTableNameAlias, sortArguments); 
		Query q =  getEntityManager().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters) ;
		@SuppressWarnings("unchecked")
		List<DATA> retval =  q.getResultList();
		return retval ;
	}
	@Override
	public <DATA> List<DATA> list(String tableNameAndJoinArgument,
			String primaryTableNameAlias, SimpleQueryFilter[] filters,
			SimpleSortArgument[] sortArguments) throws Exception {
		String countSmt = "SELECT  "+primaryTableNameAlias+"  from " + tableNameAndJoinArgument + "  where 1=1  " + buildWhereStatement(primaryTableNameAlias, filters) + buildOrderByStatement(primaryTableNameAlias, sortArguments); 
		Query q =  getEntityManager().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters) ;
		@SuppressWarnings("unchecked")
		List<DATA> retval =  q.getResultList();
		return retval ;
	}
	
	
	
	/**
	 * ini untuk count berapa data yang nemu 
	 **/
	public Long count(Class<?> entityClass , SimpleQueryFilter[] filters){
		String countSmt = "SELECT count(*)  from " + entityClass.getName() + " a where 1=1  " + buildWhereStatement("a", filters); 
		Query q =  getEntityManager().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters);
		try {
			return (Long) q.getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
		 
	}
	
	@Override
	public Long count(String tableAndJoinStatment,
			String primaryTableAliasName, SimpleQueryFilter[] filters) {
		String countSmt = "SELECT count(*)  from " + tableAndJoinStatment + " where 1=1  " + buildWhereStatement(primaryTableAliasName, filters); 
		Query q =  getEntityManager().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters);
		try {
			return (Long) q.getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}
	
	@Override
	public Long count(String tableAndJoinStatment,
			String primaryTableAliasName, String primaryTableAliasPK, String predefinedWhere,
			SimpleQueryFilter[] filters) {
		String countSmt = "SELECT count("+primaryTableAliasName +"."+ primaryTableAliasPK +")  from " + tableAndJoinStatment + " where 1=1  AND ( " + predefinedWhere + ") "  + buildWhereStatement(primaryTableAliasName, filters);
		Query q =  getEntityManager().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters);
		try {
			return (Long) q.getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <DATA> List<DATA> list(Class<? extends DATA> entityClass , SimpleSortArgument[] sortArguments) {
		String hqlSmt ="SELECT a FROM " + entityClass.getName() +" a " + buildOrderByStatement("a", sortArguments);
		return getEntityManager().createQuery(hqlSmt).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <DATA> List<DATA> list(String tableAndJoinStatment,
			String primaryTableAliasName,
			SimpleSortArgument[] sortArguments) {
		String hqlSmt ="SELECT "+primaryTableAliasName+" FROM " + tableAndJoinStatment +  buildOrderByStatement(primaryTableAliasName, sortArguments);
		return getEntityManager().createQuery(hqlSmt).getResultList();
	}

	
	@Override
	public Integer deleteByIds(Class<?> objectClass,
			Collection<Serializable> pks, String pkFieldName) {
		if ( pks == null || pks.isEmpty())
			return 0 ; 
		String delSmt = "delete from " + objectClass.getName() + " where " + pkFieldName  + " in (" + genarateInStatement("PARAM", pks.size()) + ")";
		Query q =  getEntityManager().createQuery( delSmt);
		q =  fillInParams(q, "PARAM", pks); 
		return q.executeUpdate();
	}
}
