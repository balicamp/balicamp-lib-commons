package id.co.sigma.common.server.dao.base;

import java.util.ArrayList;
import java.util.List;

import id.co.sigma.common.data.PagedResultHolder;

import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 * 
 * base class untuk menampilkan / membuat list pagging JPA
 * @author Andri Krisharyadi
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a> 
 * @param <DATAENTITY> data entity actual yang di select. ini yang di petakan dalam JPA
 * @param <DTO> class DTO yang di harapkan sebagai return value. kalau misal nya tidak mempergunakan DTO, set DTO=DATAENTITY
 **/
public abstract class BasedPagedSelectExecutor<DATAENTITY   , DTO > {

	protected EntityManager entityManager; 
	
	
	public BasedPagedSelectExecutor(EntityManager entityManager ){
		this.entityManager= entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public PagedResultHolder<DTO> readData( int page, int pageSize){
		// count
		// sample : select count(a.noRahn) from RahnCore a where a.statusKredit='KR'
		
		Integer totalData= Integer.parseInt(""+runSelectCount());
		PagedResultHolder<DTO> retval = instantiateQueryDataResult();
		retval.setPage(page); 
		retval.setPageSize(pageSize); 
		
		retval.setTotalData(totalData);// kalkulasi total page count
		
		retval.adjustPagination(); 
		
		
		List<DATAENTITY> entityList= runQueryData( retval.getPageSize(), retval.getFirstRowPosition());
		if ( !isUseDTO())
			retval.setHoldedData((List<DTO>)entityList);
		else{
			if ( entityList !=null && !entityList.isEmpty()){
				
				List<DTO> swap = new ArrayList<DTO>();
				retval.setHoldedData(swap);
				
				for(DATAENTITY  scn : entityList){
					swap.add(transformToDTO(scn));
				}
				
			}
			
		}
		return retval ;
	}
	
	
	
	
	/**
	 * worker utnuk select count data
	 **/
	protected Long runSelectCount(){
		String countSmt = "select count(" + getAliasForPrimaryEntityData() + "."+getPrimaryKeyField()+") from " + generateFromAndWhereStatement( getAliasForPrimaryEntityData());
		Query qCountAwal = entityManager.createQuery(countSmt);
		Query qCount = generateAndputQueryArgument(qCountAwal);
		
		return (Long) qCount.getSingleResult();
	}
	
	
	
	
	/**
	 * worker untuk query data(paged)
	 * @param pageSize ukuran page per pemanggilan data
	 * @param firstRowPosition posisi row pertama yang akan di baca
	 **/
	@SuppressWarnings("unchecked")
	protected List<DATAENTITY> runQueryData ( int pageSize , int firstRowPosition){
		
		String queryNormal = "select " + getAliasForPrimaryEntityData() + " from " +
				generateFromAndWhereStatement( getAliasForPrimaryEntityData());
		Query qNormalAwal = entityManager.createQuery(queryNormal);
		Query qNormal = generateAndputQueryArgument(qNormalAwal);
		return qNormal.setMaxResults(pageSize).setFirstResult(firstRowPosition).getResultList();
	}
	
	
	/**
	 * generate from and where statement untuk select. ini tanpa from
	 * sample : jpaql -> select a from RahnCore a where a.statusKredit='KR' maka ini musti makai spt ini : 
	 * <code>
	 *  "RahnCore "+aliasForPrimaryEntity+" where "+aliasForPrimaryEntity+".statusKredit=:PARAM1";<br/>
	 *  
	 * </code>
	 * RahnCore : nama entity class
	 * aliasForPrimaryEntity : alias untuk primary table. ini di pergunakan untuk mengenerate select statement. where sebaiknya concat dengan ini. misal : Loan-> alias = "a" nanti nya menjadi where a.id di bikin nya dengan aliasForPrimaryEntity+"aaa"
	 * @param aliasForPrimaryEntity alias untuk primary table yang di select  
	 **/
	protected abstract String generateFromAndWhereStatement( String aliasForPrimaryEntity);
	
	
	/**
	 * set jpa argument
	 * sample : 
	 * <code>
				if(queryArgument.getNoRahn()!=null){
					queryAwal=queryAwal.setParameter ("NO_RAHN" , filter.getNoRahn()); 
			 
				}
				if(queryArgument.getNamaRahn()!=null){
					queryAwal=queryAwal.setParameter ("NAMA" , "%"+filter.getNamaRahn()+"%");
				}
				
				return queryAwal;
	 * </code>
	 * yang di set berpasangan dengan {@link #generateFromAndWhereStatement(String)}. apa saja param yang anda set di sana<br/>
	 * variable untuk di set ke dalam query object harus anda tempatkan sebagai member variable dalam subclass anda.
	 **/
	protected abstract Query generateAndputQueryArgument(Query queryAwal);
	
	
	/**
	 * default alias untuk primary entity
	 **/
	protected String getAliasForPrimaryEntityData (){
		return "a";
	}
	
	/**
	 * transformer dari JPA entity ke DTO
	 * jika ternyata returnnya berupa DTO, override method ini untuk melakuan copy dari entity ke DTO
	 **/
	protected DTO transformToDTO(DATAENTITY originalData){
		return null ;
	}
	
	
	/**
	 * data yang di harapkan DTO atau JPA murni
	 **/
	protected boolean isUseDTO(){
		return true ;
	}
	
	/**
	 * nama field/atribut pd entity yg dijadikan primary key 
	 * defaultnya : id 
	**/
	protected String getPrimaryKeyField(){
		return "id";
	}
	
	/**
	 * override ini kalau perlu type subset of {@link PagedResultHolder}
	 **/
	protected PagedResultHolder<DTO> instantiateQueryDataResult(){
		PagedResultHolder<DTO> retval = new PagedResultHolder<DTO>();
		return retval;
	}
}
