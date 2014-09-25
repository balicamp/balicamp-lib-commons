package id.co.sigma.common.server.dao.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
















import org.hibernate.Query;
import org.hibernate.Session;

import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.data.query.SimpleQueryFilterOperator;
import id.co.sigma.common.server.dao.IBaseDao;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public  abstract class BaseHibernateDao extends SharedPartBaseDao implements IBaseDao{
	
	 
	
	
	
	/**
	 * membaca session factory. ini tanggung jawab dari project masing2
	 */
	public abstract  Session getCurrentSession () ; 
	
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public <DOMAIN> List<DOMAIN> loadDataByKeys(Class<?> classType,
			String primaryKeyPropertyName, List<Serializable> keys)
			throws Exception {
		if ( keys== null|| keys.isEmpty())
			return null ;
		Session sess =  getCurrentSession() ;
		String hql = "select a from " + classType.getName() + " a where a."  + primaryKeyPropertyName + " in ( :KEYS) ";  
		return sess.createQuery(hql).setParameter("KEYS", keys).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <DOMAIN> List<DOMAIN> loadDataByGenericKeys(Class<?> classType,
			String primaryKeyPropertyName, List<? extends Serializable> keys)
			throws Exception {
		if ( keys== null|| keys.isEmpty())
			return null ;
		Session sess =  getCurrentSession() ;
		String hql = "select a from " + classType.getName() + " a where a."  + primaryKeyPropertyName + " in ( :KEYS) ";  
		return sess.createQuery(hql).setParameter("KEYS", keys).list();
	}

	@Override
	public void insert(Serializable object) throws Exception {
		Session sess =  getCurrentSession() ;
		sess.save(object); 
		
	}

	@Override
	public void insertAndFlush(Serializable object) throws Exception {
		Session sess =  getCurrentSession() ;
		sess.save(object); 
		sess.flush();
	}

	@Override
	public void delete(Serializable object) throws Exception {
		Session sess =  getCurrentSession() ;
		sess.delete(object); 
		sess.flush();
		
	}

	@Override
	public Integer delete(Class<?> objectClass, Serializable pk,
			String pkFieldName) {
		String delSmt = "delete from " + objectClass.getName() + " where " + pkFieldName + "=:PK";
		Session sess =  getCurrentSession();
		return sess.createQuery(delSmt).setParameter("PK", pk).executeUpdate();
	}

	@Override
	public Integer deleteByParentId(Class<?> classToDelete, Long parentId,
			String parentFieldName) {
		String delSmt = "delete from " + classToDelete.getName() + " where " + parentFieldName + "=:PK";
		Session sess =  getCurrentSession();
		return sess.createQuery(delSmt).setParameter("PK", parentId).executeUpdate();
	}

	
	@Override
	public Integer deleteByParentId(Class<?> classToDelete, String parentId,
			String parentFieldName) {
		String delSmt = "delete from " + classToDelete.getName() + " where " + parentFieldName + "=:PK";
		Session sess =  getCurrentSession();
		return sess.createQuery(delSmt).setParameter("PK", parentId).executeUpdate();
	}
	@Override
	public Integer deleteByParentId(Class<?> classToDelete,
			BigInteger parentId, String parentFieldName) {
		String delSmt = "delete from " + classToDelete.getName() + " where " + parentFieldName + "=:PK";
		Session sess =  getCurrentSession();
		return sess.createQuery(delSmt).setParameter("PK", parentId).executeUpdate();
	}
	
	@Override
	public Integer deleteByParentId(Class<?> classToDelete, Integer parentId,
			String parentFieldName) {
		String delSmt = "delete from " + classToDelete.getName() + " where " + parentFieldName + "=:PK";
		Session sess =  getCurrentSession();
		return sess.createQuery(delSmt).setParameter("PK", parentId).executeUpdate();
	}

	@Override
	public <DATA extends Serializable> void inserts(Collection<DATA> objects)
			throws Exception {
		if ( objects== null || objects.isEmpty())
			return ; 
		Session sess =  getCurrentSession();
		int i = 0 ;
		boolean justFlush = false ; 
		for ( DATA scn : objects ){
			
			sess.save(scn);
			justFlush = false ; 
			if ( i%MAX_BATCH_INSERT_SIZE==0 ){
				sess.flush(); 
				justFlush = true  ;
				
			}
			i++ ; 
		}
		if ( !justFlush)
			sess.flush(); 
		
	}

	@Override
	public void update(Serializable object) throws Exception {
		getCurrentSession().update(object);
		
	}

	@Override
	public void updateAndFlush(Serializable object) throws Exception {
		Session sess =  getCurrentSession();
		sess.update(object);
		sess.flush();
		
	}

	@Override
	public <DATA extends Serializable> void updates(Collection<DATA> objects)
			throws Exception {
		if ( objects== null || objects.isEmpty())
			return ; 
		Session sess =  getCurrentSession();
		int i = 0 ;
		boolean justFlush = false ; 
		for ( DATA scn : objects ){
			
			sess.update(scn);
			justFlush = false ; 
			if ( i%MAX_BATCH_INSERT_SIZE==0 ){
				sess.flush(); 
				justFlush = true  ;
				
			}
			i++ ; 
		}
		if ( !justFlush)
			sess.flush(); 
		
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public <DATA> List<DATA> list(Class<? extends DATA> entityClass , SimpleSortArgument[] sortArguments) {
		String hqlSmt ="SELECT a FROM " + entityClass.getName() +" a " + buildOrderByStatement("a", sortArguments);
		return getCurrentSession().createQuery(hqlSmt).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <DATA> List<DATA> list(String tableAndJoinStatment,
			String primaryTableAliasName,
			SimpleSortArgument[] sortArguments) {
		String hqlSmt ="SELECT "+primaryTableAliasName+" FROM " + tableAndJoinStatment +  buildOrderByStatement(primaryTableAliasName, sortArguments);
		return getCurrentSession().createQuery(hqlSmt).list();
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
		Query q =  getCurrentSession().createQuery(hql);
		q = putQueryArguments(q, filters); 
		return q.list(); 
		
		
	}
	
	/**
	 * membaca data dengan simple filter
	 **/
	@SuppressWarnings("unchecked")
	public <DATA> List<DATA> list(Class<? extends DATA> entityClass , 
			SimpleQueryFilter[] filters, 
			SimpleSortArgument[] sortArguments,  int pageSize , int firstRowPosition) throws Exception{
		String countSmt = "SELECT a  from " + entityClass.getName() + " a where 1=1  " + buildWhereStatement("a", filters) + buildOrderByStatement("a", sortArguments); 
		Query q =  getCurrentSession().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters).setMaxResults(pageSize).setFirstResult(firstRowPosition);
		return q.list();
		
	}

	

	/**
	 * membaca data dengan simple filter
	 **/
	@SuppressWarnings("unchecked")
	public <DATA> List<DATA> list( String tableNameAndJoinArgument , String primaryTableNameAlias , SimpleQueryFilter[] filters, SimpleSortArgument[] sortArguments,  int pageSize , int firstRowPosition) throws Exception{
		String countSmt = "SELECT  "+primaryTableNameAlias+"  from " + tableNameAndJoinArgument + "  where 1=1  " + buildWhereStatement(primaryTableNameAlias, filters) + buildOrderByStatement(primaryTableNameAlias, sortArguments); 
		Query q =  getCurrentSession().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters).setMaxResults(pageSize).setFirstResult(firstRowPosition);
		List<DATA> retval =  q.list();
		return retval ; 
		
	}
	
	
	@Override
	public <DATA> List<DATA> list(String tableNameAndJoinArgument,
			String primaryTableNameAlias, SimpleQueryFilter[] filters,
			SimpleSortArgument[] sortArguments) throws Exception {
		String countSmt = "SELECT  "+primaryTableNameAlias+"  from " + tableNameAndJoinArgument + "  where 1=1  " + buildWhereStatement(primaryTableNameAlias, filters) + buildOrderByStatement(primaryTableNameAlias, sortArguments); 
		Query q =  getCurrentSession().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters);
		List<DATA> retval =  q.list();
		return retval  ; 
	}

	/**
	 * ini untuk count berapa data yang nemu 
	 **/
	public Long count(Class<?> entityClass , SimpleQueryFilter[] filters){
		String countSmt = "SELECT count(*)  from " + entityClass.getName() + " a where 1=1  " + buildWhereStatement("a", filters); 
		Query q =  getCurrentSession().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters);
		try {
			return (Long) q.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
		 
	}

	@Override
	public Long count(String tableAndJoinStatment,
			String primaryTableAliasName, SimpleQueryFilter[] filters) {
		String countSmt = "SELECT count(*)  from " + tableAndJoinStatment + " where 1=1  " + buildWhereStatement(primaryTableAliasName, filters); 
		Query q =  getCurrentSession().createQuery(countSmt) ;
		q = this.putQueryArguments(q, filters);
		try {
			return (Long) q.uniqueResult();
		} catch (Exception e) {
			return 0L;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <DOMAIN> DOMAIN get(Class<?> classType, Serializable key)
			throws Exception {
		getCurrentSession().createQuery(""); 
		return (DOMAIN) getCurrentSession().load(classType,  key);
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
	 * worker untuk build smt HQL dari field-field yang di minta dari client
	 * @param tableAlias table alias. ini untuk membangun statement, nama table alias[dot] nama field. tipikal nya a
	 * @param filters filter query
	 * @return contoh hasil : <br/> <code>AND a.name=:SIGMAPARAM1 AND a.username=:SIGMAPARAM2
	 * </code> , jadinya parameter di automate dengan jumlah pada argument filters
	 **/
	protected String  buildWhereStatement(String tableAlias ,  SimpleQueryFilter[] filters) {
		String retval="" ; 
		if ( filters==null||filters.length==0)
			return "" ;
		int i=0;
		for ( SimpleQueryFilter scn : filters){
			if ( (scn.getFilter()==null||scn.getFilter().length()==0)&& (			
					SimpleQueryFilterOperator.likeBothSide.equals(  scn.getOperator())||
					SimpleQueryFilterOperator.likeFrontOnly.equals(  scn.getOperator())||
					SimpleQueryFilterOperator.likeTailOnly.equals(  scn.getOperator())					
				)){
				System.err.println("warning filter :" + scn.getField() + ", mempergunakan like, tapi empty atau blank");
				continue ;
			}
						
			 if(SimpleQueryFilterOperator.yearEqual.equals(scn.getOperator())){
				String templateStringYear = SimpleQueryFilterOperator.yearEqual.toString();
				String templateReplaceField = templateStringYear.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_DATE_TO_REPLACE, tableAlias + "." + scn.getField());
				String templateReplaceParam = templateReplaceField.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_TO_REPLACE, AUTO_PARAM_PREFIX + i++);
				retval+= " AND " + templateReplaceParam;
			}else if(SimpleQueryFilterOperator.monthEqual.equals(scn.getOperator())){
				String templateMonthYear = SimpleQueryFilterOperator.monthEqual.toString();
				String templateReplaceField = templateMonthYear.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_DATE_TO_REPLACE, tableAlias + "." + scn.getField());				
				String templateReplaceParam = templateReplaceField.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_TO_REPLACE, AUTO_PARAM_PREFIX + i++);
				retval+= " AND " + templateReplaceParam;
			}else if(SimpleQueryFilterOperator.dayEqual.equals(scn.getOperator())){
				String templateDayYear = SimpleQueryFilterOperator.dayEqual.toString();
				String templateReplaceField = templateDayYear.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_DATE_TO_REPLACE, tableAlias + "." + scn.getField());
				String templateReplaceParam = templateReplaceField.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_TO_REPLACE, AUTO_PARAM_PREFIX + i++);
				retval+= " AND " + templateReplaceParam;
			}else if(SimpleQueryFilterOperator.dayGreaterEqual.equals(scn.getOperator()) || SimpleQueryFilterOperator.dayLessEqual.equals(scn.getOperator()) ||
					SimpleQueryFilterOperator.monthGreaterEqual.equals(scn.getOperator()) || SimpleQueryFilterOperator.monthLessEqual.equals(scn.getOperator()) ||
					SimpleQueryFilterOperator.yearGreaterEqual.equals(scn.getOperator()) || SimpleQueryFilterOperator.yearLessEqual.equals(scn.getOperator()) ||
					SimpleQueryFilterOperator.hourGreaterEqual.equals(scn.getOperator()) || SimpleQueryFilterOperator.minuteGreaterEqual.equals(scn.getOperator()) ||
					SimpleQueryFilterOperator.hourLessEqual.equals(scn.getOperator()) || SimpleQueryFilterOperator.minuteLessEqual.equals(scn.getOperator())){
				String templateOperator = scn.getOperator().toString();
				String templateReplaceField = templateOperator.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_DATE_TO_REPLACE, tableAlias + "." + scn.getField());
				String templateReplaceParam = templateReplaceField.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_TO_REPLACE, AUTO_PARAM_PREFIX + i++);
				retval+= " AND " + templateReplaceParam;
			}else if (SimpleQueryFilterOperator.fieldIn.equals(scn.getOperator()) || SimpleQueryFilterOperator.fieldNotIn.equals(scn.getOperator())) {
				retval+=  " AND " + tableAlias + "." + scn.getField() + buildWhereStatement(i++, scn.getOperator()) + "(" + scn.getFilter() + ")";
			} else if (SimpleQueryFilterOperator.dateBetween.equals(scn.getOperator())) {
				String[] betweenParam = scn.getFilter().split("dateseparator");
				retval+=  " AND " + tableAlias + "." + scn.getField() + buildWhereStatement(i++, scn.getOperator()) + "'" + betweenParam[0] + "' AND '" + betweenParam[1] +"'";
			} else if (SimpleQueryFilterOperator.hourIn.equals(scn.getOperator())) {
				String templateOperator = scn.getOperator().toString();
				templateOperator = templateOperator.replace(SimpleQueryFilterOperator.STRING_PARAM_DATE_TO_REPLACE, tableAlias + "." + scn.getField());
				retval += " AND " + templateOperator + " (" + scn.getFilter() + ")";
			}
			else{
				retval+=  " AND " + tableAlias + "." + scn.getField() + buildWhereStatement(i++, scn.getOperator());
			}						
		}		
		return retval ; 
	}
	
	
	/**
	 * simpler helper worker. ini akan menggenerate where statement untuk operator yang di kirim user
	 * @param autoParamIndex index parameter
	 * @param operator operator 
	 *  
	 **/
	protected String buildWhereStatement (  int autoParamIndex ,     SimpleQueryFilterOperator operator ) {
		if (SimpleQueryFilterOperator.likeBothSide.equals(operator)|| SimpleQueryFilterOperator.likeFrontOnly.equals(operator)||
				SimpleQueryFilterOperator.likeTailOnly.equals(operator)){
			
			return " like :" + AUTO_PARAM_PREFIX + autoParamIndex ; 
		}
		if (SimpleQueryFilterOperator.fieldIsNull.equals(operator) ){
			return " is null ";
		}
		if (SimpleQueryFilterOperator.fieldIsNotNull.equals(operator) ){
			return " is not null ";
		}
		if (SimpleQueryFilterOperator.fieldIn.equals(operator) || SimpleQueryFilterOperator.fieldNotIn.equals(operator) ||
				SimpleQueryFilterOperator.dateBetween.equals(operator)) {
			return " " + operator.toString() + " ";
		}
		String retval =  operator.toString();		
		return retval.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_TO_REPLACE,  AUTO_PARAM_PREFIX + autoParamIndex);  		
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
							param = (Date) dateFormatter.parse(scn.getFilter());
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else {
						param = scn.getFilter();
					} 
					query = query.setParameter(AUTO_PARAM_PREFIX + i,   param);
				}
				i++;
			}
			
		}
		return query ; 
	}





	@Override
	public void deleteObjects(Collection<? extends Serializable> objects)
			throws Exception {
		if ( objects == null || objects.isEmpty())
			return ;
		Session sess = getCurrentSession()  ;
		int i = 0 ;
		boolean justFlush = false ; 
		for ( Serializable scn : objects ){
			
			sess.delete(scn);
			justFlush = false ; 
			if ( i%MAX_BATCH_INSERT_SIZE==0 ){
				sess.flush(); 
				justFlush = true  ;
				
			}
			i++ ; 
		}
		if ( !justFlush)
			sess.flush(); 
			
		
	}





	@Override
	public void updateObjects(Collection<? extends Serializable> objects)
			throws Exception {
		if ( objects == null || objects.isEmpty())
			return ;
		Session sess = getCurrentSession()  ;
		int i = 0 ;
		boolean justFlush = false ; 
		for ( Serializable scn : objects ){
			
			sess.update(scn);
			justFlush = false ; 
			if ( i%MAX_BATCH_INSERT_SIZE==0 ){
				sess.flush(); 
				justFlush = true  ;
				
			}
			i++ ; 
		}
		if ( !justFlush)
			sess.flush(); 
			
		
	}
	

	

	/**
	 * update data dengan entity class +  filter query. filter hanya bisa bekerja untuk field yang simple. field yang berada dalam table itu. 
	 * sebab tidak semua database engine support update join. mohon berhati-hati dalam mempergunakan method ini 
	 * @param entityClass entity class yang hendak di update
	 */
	public int updateData ( Class<?> entityClass , SimpleKeyValueParameter[] updatedFields , SimpleQueryFilter [] filters ){
		Query updQuery =  getCurrentSession().createQuery(generateUpdateStatement(entityClass, updatedFields, filters));
		updQuery = putQueryArguments(updQuery, filters); 
		for ( SimpleKeyValueParameter scn  : updatedFields){
			updQuery = updQuery.setParameter(scn.getFieldName().toUpperCase(), scn.getParameter()); 
		}
		return updQuery.executeUpdate(); 
		
	}
	@Override
	public Integer deleteByIds(Class<?> objectClass,
			Collection<Serializable> pks, String pkFieldName) {
		if ( pks == null || pks.isEmpty())
			return 0 ; 
		Session sess = getCurrentSession() ;
		String delSmt = "delete from " + objectClass.getName() + " where " + pkFieldName  + " in (" + genarateInStatement("PARAM", pks.size()) + ")";
		Query q = sess.createQuery(delSmt);
		q =  fillInParams(q, "PARAM", pks); 
		return q.executeUpdate();
	}
	
	
	
}
