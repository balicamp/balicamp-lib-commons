package id.co.sigma.common.server.dao.base;

import id.co.sigma.common.data.CommonLibraryConstant;
import id.co.sigma.common.data.ModificationDataContainer;
import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleQueryFilterOperator;
import id.co.sigma.common.server.dao.IBaseDao;
import id.co.sigma.common.server.util.ExtendedBeanUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;


/**
 * item untuk berbagi antara hibernate vs JPA
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class SharedPartBaseDao implements IBaseDao{
	
	/**
	 * formatter untuk date (digunakan dalam casting filter ke type data aslinya)
	 **/
	protected static SimpleDateFormat dateFormatter = new SimpleDateFormat(CommonLibraryConstant.DATE_TO_STRING_SERIALIZATION_PATTERN);
	
	protected static SimpleDateFormat DATE_FORMATTER_SHORT = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * ukuran maks insert batch
	 **/
	public static final int MAX_BATCH_INSERT_SIZE = 100; 
	
	/**
	 * Prefix untuk automated named param. nantinya di gabung dengan nomor urut
	 **/
	protected static final String AUTO_PARAM_PREFIX ="SIGMA_PARAM";
	
	/**
	 * membaca statement sql dari resource file. ini di cari dalam package yang sama dengan class
	 */
	public String readSQLTemplateFromResourceWithSamePackage(String fileNameWithoutPackageName)
			throws IOException {
		String pkgName = getClass().getPackage().getName(); 
		
		pkgName = "/" + pkgName.replaceAll("\\.", "/") + "/" + fileNameWithoutPackageName ; 
 		InputStream is = getClass().getResourceAsStream(pkgName);
		String sqlStemplatte = IOUtils.toString(is);
		is.close();
		return sqlStemplatte;
	}
	
	
	
	
	/**
	 * generate in statement. agar aman terhadap JPA 1 vs JPA 2.0. ini akan membuild -> (:PARAM0,:PARAM1)
	 * @param prefixParam prefixParam untuk query jadinya 
	 * @param size ukuran param 
	 * @return sample hasil -> :PARAM0,:PARAM1,:PARAM2,:PARAM3 dst. tanpa tanda ()
	 * 
	 **/
	protected String genarateInStatement(String prefixParam ,int size ){
		String retval ="";
		for ( int i=0;i< size ;i++){
			retval +=":" + prefixParam + i;
			if ( i!=size-1)
				retval +=", ";
		}
		return retval ;
	}
	
	
	/**
	 * simpan modifisi
	 **/
	public<KEY extends Serializable, DATA extends SingleKeyEntityData<KEY>> void saveModificationData ( Class<? extends DATA> objectClass ,   ModificationDataContainer<DATA> modificationDataContainer, String[] modifableFIelds ) throws Exception{
		if ( modificationDataContainer==null)
			return ;
		
		// insert
		if ( modificationDataContainer.getNewlyAppendedData()!=null&& !modificationDataContainer.getNewlyAppendedData().isEmpty()){
			this.inserts(modificationDataContainer.getNewlyAppendedData());
		}
		
		// update
		updateDataWOrker(objectClass, modificationDataContainer.getEditedData(), modifableFIelds);
		
		
		// delete
		if( modificationDataContainer.getErasedData()!=null&&! modificationDataContainer.getErasedData().isEmpty()){
			List<Serializable> keys = new ArrayList<>();
			@SuppressWarnings("rawtypes")
			Class sampleCls = modificationDataContainer.getErasedData().get(0).getClass(); 
			for ( DATA scn : modificationDataContainer.getErasedData()){
				keys.add(scn.getId()); 
			}
			deleteByIds(sampleCls, keys, "id"); 
			//deleteByParentId (sampleCls, parentId, parentFieldName)
			//deleteObjects(modificationDataContainer.getErasedData());
		}
		
		
		
	}
	

	@SuppressWarnings("unchecked")
	protected<KEY , DATA extends SingleKeyEntityData<KEY>> void updateDataWOrker (  Class<? extends DATA> objectClass   , Collection<DATA > modifieds , String[] updateableFields) throws Exception {
		if ( modifieds==null||modifieds.isEmpty())
			return ; 
		
		// semua di masukan ke dalam hasmap
		Map<KEY, DATA> indexer = new  HashMap<KEY, DATA>();
		ArrayList<Serializable> keys = new ArrayList<Serializable>() ; 
		for (DATA scn : modifieds) {
			indexer.put(scn.getId(), scn);
			keys.add((Serializable) scn.getId()); 
		}
		
		// select data yang di update
		
		/*
		String hqlSMt ="SELECT a FROM " + objectClass.getName().toString() + " a WHERE a.id in ( " + this.genarateInStatement("ID", modifieds.size()) + " )";
		Query q =  entityManager.createQuery(hqlSMt);
		q= this.fillInParams(q, "ID"  ,indexer.keySet() );
		List<DATA> dataToUpdates = q.getResultList() ; */
		List<DATA> dataToUpdates = this.loadDataByKeys(objectClass, "id", keys); 
		
		
		
		//merge perubahan
		if ( dataToUpdates!=null&& !dataToUpdates.isEmpty()){
			for ( DATA dbData :dataToUpdates ){
				DATA dataFromCLient = indexer.get(dbData.getId());
				ExtendedBeanUtils.getInstance().copyPropertiesWithSpecifiedItemOnly(dataFromCLient, dbData, updateableFields); 
				
			}
			this.updateObjects(dataToUpdates);
		}
	}
	
	/**
	 * worker untuk build smt HQL dari field-field yang di minta dari client. ini di revisi agar bisa di pergunakan untuk invoke statment update dengan parameter paramter sederhana
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
		
		
		String tableAliasWithDot = tableAlias!= null && tableAlias.length()> 0 ? tableAlias +"." : "" ;
		
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
				String templateReplaceField = templateStringYear.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_DATE_TO_REPLACE, tableAliasWithDot+ scn.getField());
				String templateReplaceParam = templateReplaceField.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_TO_REPLACE, AUTO_PARAM_PREFIX + i++);
				retval+= " AND " + templateReplaceParam;
			}else if(SimpleQueryFilterOperator.monthEqual.equals(scn.getOperator())){
				String templateMonthYear = SimpleQueryFilterOperator.monthEqual.toString();
				String templateReplaceField = templateMonthYear.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_DATE_TO_REPLACE, tableAliasWithDot + scn.getField());				
				String templateReplaceParam = templateReplaceField.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_TO_REPLACE, AUTO_PARAM_PREFIX + i++);
				retval+= " AND " + templateReplaceParam;
			}else if(SimpleQueryFilterOperator.dayEqual.equals(scn.getOperator())){
				String templateDayYear = SimpleQueryFilterOperator.dayEqual.toString();
				String templateReplaceField = templateDayYear.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_DATE_TO_REPLACE, tableAliasWithDot+ scn.getField());
				String templateReplaceParam = templateReplaceField.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_TO_REPLACE, AUTO_PARAM_PREFIX + i++);
				retval+= " AND " + templateReplaceParam;
			}else if(SimpleQueryFilterOperator.dayGreaterEqual.equals(scn.getOperator()) || SimpleQueryFilterOperator.dayLessEqual.equals(scn.getOperator()) ||
					SimpleQueryFilterOperator.monthGreaterEqual.equals(scn.getOperator()) || SimpleQueryFilterOperator.monthLessEqual.equals(scn.getOperator()) ||
					SimpleQueryFilterOperator.yearGreaterEqual.equals(scn.getOperator()) || SimpleQueryFilterOperator.yearLessEqual.equals(scn.getOperator()) ||
					SimpleQueryFilterOperator.hourGreaterEqual.equals(scn.getOperator()) || SimpleQueryFilterOperator.minuteGreaterEqual.equals(scn.getOperator()) ||
					SimpleQueryFilterOperator.hourLessEqual.equals(scn.getOperator()) || SimpleQueryFilterOperator.minuteLessEqual.equals(scn.getOperator())){
				String templateOperator = scn.getOperator().toString();
				String templateReplaceField = templateOperator.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_DATE_TO_REPLACE,tableAliasWithDot + scn.getField());
				String templateReplaceParam = templateReplaceField.replaceAll(SimpleQueryFilterOperator.STRING_PARAM_TO_REPLACE, AUTO_PARAM_PREFIX + i++);
				retval+= " AND " + templateReplaceParam;
			}else if (SimpleQueryFilterOperator.fieldIn.equals(scn.getOperator()) || SimpleQueryFilterOperator.fieldNotIn.equals(scn.getOperator())) {
				retval+=  " AND " +tableAliasWithDot + scn.getField() + buildWhereStatement(i++, scn.getOperator()) + "(" + scn.getFilter() + ")";
			} else if (SimpleQueryFilterOperator.dateBetween.equals(scn.getOperator())) {
				String[] betweenParam = scn.getFilter().split("dateseparator");
				retval+=  " AND " + "DATE("+tableAliasWithDot + scn.getField()+")" + buildWhereStatement(i++, scn.getOperator()) + "'" + betweenParam[0] + "' AND '" + betweenParam[1] +"'";
			} else if (SimpleQueryFilterOperator.hourIn.equals(scn.getOperator())) {
				String templateOperator = scn.getOperator().toString();
				templateOperator = templateOperator.replace(SimpleQueryFilterOperator.STRING_PARAM_DATE_TO_REPLACE, tableAliasWithDot + scn.getField());
				retval += " AND " + templateOperator + " (" + scn.getFilter() + ")";
			}
			else if ( SimpleQueryFilterOperator.fieldLengthEqual.equals(scn.getOperator())){
				retval += " AND length(" +   tableAliasWithDot + scn.getField() + ")= :" + AUTO_PARAM_PREFIX + i ;
				i++; 
			}
			else{
				retval+=  " AND " + tableAliasWithDot + scn.getField() + buildWhereStatement(i++, scn.getOperator());
			}						
		}		
		return retval ; 
	}
	
	
	protected String  buildWhereStatementOriginal(String tableAlias ,  SimpleQueryFilter[] filters) {
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
	 * worker untuk generate update statmenet. filter query di sediakan, field yang di update di sediakan 
	 * @param entityClass entity class yang perlu di update
	 * @param updatedFields field-field yang di update
	 * @param filters filters untuk proses update data
	 */
	protected String generateUpdateStatement ( Class<?> entityClass , SimpleKeyValueParameter[] updatedFields , SimpleQueryFilter [] filters  ) {
		StringBuffer fieldUpdateStatment = new StringBuffer(); 
		for ( SimpleKeyValueParameter scn :  updatedFields ){
			if ( fieldUpdateStatment.length()>0)
				fieldUpdateStatment.append(","); 
			fieldUpdateStatment.append( scn.getFieldName()  ); 
			fieldUpdateStatment.append("=:") ; 
			fieldUpdateStatment.append(scn.getFieldName().toUpperCase());
		} 
		String whereStatment = buildWhereStatement("", filters);
		String updateSmt = "UPDATE " + entityClass.getName() + " SET " + fieldUpdateStatment + " WHERE 1=1 "  + whereStatment ; 
		return updateSmt ; 
		
	}
	
	
	
}
