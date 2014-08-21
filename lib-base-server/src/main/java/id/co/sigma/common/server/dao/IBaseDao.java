package id.co.sigma.common.server.dao;

import id.co.sigma.common.data.ModificationDataContainer;
import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.server.dao.base.SimpleKeyValueParameter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;



/**
 * interface dao
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface IBaseDao {

	/**
	 * load data dengan primary keys
	 * @param classType tipe class entity
	 * @param primaryKeyPropertyName nama field prmary key
	 * @param keys list of primary keys
	 **/
	public <DOMAIN> List<DOMAIN> loadDataByKeys(  Class<? >  classType  , String primaryKeyPropertyName ,  List<Serializable> keys) throws Exception;
	
	
	/**
	 * hapus bulk
	 **/
	public void deleteObjects ( Collection<? extends Serializable> objects) throws Exception ; 
	
	public <DOMAIN> List<DOMAIN> loadDataByGenericKeys(  Class<? >  classType  , String primaryKeyPropertyName ,  List<? extends Serializable> keys) throws Exception ; 
	/**
	 * hapus object dari dalam database
	 *@param object object(entity object) yang di hapus
	 **/
	
	public void insert(Serializable object)throws Exception;
	
	/**
	 * insert 1 data dalam database. isue flush command
	 * @param object object yang di insert
	 **/
	public void insertAndFlush(Serializable object)throws Exception ;
	
	
	
	/**
	 * hapus object dari dalam database
	 *@param object object(entity object) yang di hapus
	 **/
	public void delete (Serializable object) throws Exception;
	
	
	
	
	 
	
	
	/**
	 * hapus object dengan PK + class
	 * @param objectClass class dari object yang di hapus
	 * @param pk primary key dari data
	 */
	public Integer delete (Class<?> objectClass, Serializable pk , String pkFieldName); 
	
	
	/**
	 * hapus data dengan Id dari parent. bulk
	 * @param objectClass class di hapus
	 * @param pks list of pk dari object yang di hapus
	 * @param pkFieldName field primary key. tidak ada cerita nya menebak-nebak PK
	 */
	public Integer deleteByIds (Class<?> objectClass,Collection< Serializable> pks , String pkFieldName);
	/**
	 * hapus data dengan ID header
	 * @param classToDelete  
	 */
	public Integer deleteByParentId ( Class<?> classToDelete , Long parentId, String parentFieldName );
	
	
	
	/**
	 * delete dengan parent ID string. ini tidak harus dengan reference ke parent. field apapun yang match akan di hapus asal kriteria terpenuho
	 * @param classToDelete class di hapus
	 * @param parentId id parent
	 * @param parentFieldName nama field untuk erase data
	 *  
	 */
	public Integer deleteByParentId ( Class<?> classToDelete , String parentId, String parentFieldName );
	
	/**
	 * hapus data dengan ID header
	 * 
	 * @param classToDelete
	 * @param parentId id parent(big integer)
	 * @param parentFieldName nama jpa dari field parent  
	 */
	public Integer deleteByParentId ( Class<?> classToDelete , BigInteger parentId, String parentFieldName );
	
	
	
	/**
	 * delete  bulk dengan n data pada Collection  
	 * 
	 * @param objects
	 * @throws Exception
	 */
	public void delete(Collection<? extends Serializable> objects )throws Exception ;
	/**
	 * bulk insert data 
	 **/
	public <DATA extends Serializable> void inserts(Collection<DATA> objects )throws Exception;
	/**
	 * update 1 data
	 * @param object object yang 
	 **/
	public void update(Serializable object)throws Exception;
	
	/**
	 * update 1 data
	 * @param object object yang 
	 **/
	public void updateAndFlush(Serializable object)throws Exception ; 
	
	
	/**
	 * bulk update. ini regular flush friendly
	 **/
	public<DATA extends Serializable> void updates(Collection<DATA> objects) throws Exception;
	
	
	/**
	 * 
	 * simpan bulk, dengan data container
	 * @param objectClass class dari object yang di perasikan
	 * @param modificationDataContainer container data
	 * @param modifableFIelds fields yang boleh di update
	 **/
	public<KEY extends Serializable , DATA extends SingleKeyEntityData<KEY>> void saveModificationData ( Class<? extends DATA> objectClass ,   ModificationDataContainer<DATA> modificationDataContainer, String[] modifableFIelds ) throws Exception ;
	
	
	/**
	 * membaca data dengan simple filter
	 **/
	public <DATA> List<DATA> list(Class<? extends DATA> entityClass ,SimpleSortArgument[] sortArguments);
	
	
	/**
	 * versi ini list data dengan model di kirimkan join statment + alias dari primary table name
	 * @param tableAndJoinStatment join statement. misal Person a inner join fetch a.address 
	 * @param primaryTableAliasName sederhana nya kasi saja a
	 **/
	public <DATA> List<DATA> list(String tableAndJoinStatment  , String primaryTableAliasName ,SimpleSortArgument[] sortArguments);
	
	
	

	/**
	 * membaca data dengan simple filter. tidak ada paging di sini
	 * @param entityClass entity class yang di baca
	 * @param filters filter. simple filter, operator adalah AND antara filter
	 * @param sortArguments sort order dari data 
	 * 
	 **/
	public <DATA> List<DATA> list(Class<? extends DATA> entityClass , SimpleQueryFilter[] filters, SimpleSortArgument[] sortArguments ) throws Exception ; 
			
			
	
	
	/**
	 * membaca data dengan simple filter
	 **/
	public <DATA> List<DATA> list(Class<? extends DATA> entityClass , SimpleQueryFilter[] filters, SimpleSortArgument[] sortArguments,  int pageSize , int firstRowPosition) throws Exception ;
	
	
	/**
	 * membaca data dengan simple filter
	 **/
	public <DATA> List<DATA> list( String tableNameAndJoinArgument , String primaryTableNameAlias , SimpleQueryFilter[] filters, SimpleSortArgument[] sortArguments,  int pageSize , int firstRowPosition) throws Exception;
	
	
	/**
	 * ini untuk count berapa data yang nemu 
	 **/
	public Long count(Class<?> entityClass , SimpleQueryFilter[] filters);
	
	
	/**
	 * count data. yang di kirimkan ke sini adalah join statement + nama alias untuk primary table. 
	 * @param tableAndJoinStatment join statement. misal Person a inner join fetch a.address 
	 * @param primaryTableAliasName sederhana nya kasi saja a
	 **/
	public Long count(String tableAndJoinStatment  , String primaryTableAliasName , SimpleQueryFilter[] filters);
	
	/**
	 * get data by primary key 
	 **/
	public <DOMAIN> DOMAIN get(  Class<? >  classType  ,   Serializable key) throws Exception ; 
	
	
	public void updateObjects( Collection<? extends Serializable> objects)throws Exception; 
	
	/**
	 * update data dengan entity class +  filter query. filter hanya bisa bekerja untuk field yang simple. field yang berada dalam table itu. 
	 * sebab tidak semua database engine support update join. mohon berhati-hati dalam mempergunakan method ini 
	 * @param entityClass entity class yang hendak di update
	 */
	public int updateData ( Class<?> entityClass , SimpleKeyValueParameter[] updatedFields , SimpleQueryFilter [] filters )  ;
}
