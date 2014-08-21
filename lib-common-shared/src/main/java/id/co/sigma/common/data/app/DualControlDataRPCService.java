package id.co.sigma.common.data.app;


import id.co.sigma.common.annotation.DoubleSubmitSecuredMethod;
import id.co.sigma.common.data.AppConfigurationDrivenDetaiResultHolder;
import id.co.sigma.common.data.DataWithToken;
import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.SystemParamDrivenClass;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.exception.InvalidExcelFileException;
import id.co.sigma.common.rpc.JSONSerializedRemoteService;


import java.util.Date;
import java.util.List;




/**
 * RPC untuk handle data dual control
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface DualControlDataRPCService extends JSONSerializedRemoteService{
	
	
	
	
	
	
	/**
	 *  versi ini dengan mengirimkan data token kembali ke client. jadinya round trip data bisa di kurangi
	 * @param id ID pada table m_dual_control_table. data yang memerlukan approval
	 */
	public DataWithToken<CommonDualControlContainerTable> getWaitApprovalDataWithToken (Long id ) throws Exception ; 
	
	/**
	 * membaca data sebagai JSON String. data di baca dengan class FQCN dan ID dari data. id dari data di compact menjadi 1. Kalau id dari data composite, data di concat dengan .
	 **/
	public String getTargetDataAsJSonString ( String classFQCN , String dataIdAsCompactedString) throws Exception ; 
	
	
	/**
	 * ini untuk operasi dual control, untuk permintaan : <ol>
	 * <li>membuat data baru</li>
	 * <li>hapus data</li>
	 * <li>edit data</li>
	 * </ol>
	 * dari sini data akan muncul pada screen approval
	 * @param dualControlledData data dual control yang di submit untuk proses approval
	 * @param operation operasi yang di lakukan. cretae/ update delete
	 *  
	 * 
	 * @return id dari object yang di create dalam database
	 **/
	
	public Long submitDataForApproval (  CommonDualControlContainerTable dualControlledData , DualControlEnabledOperation operation) throws Exception ;
	
	
	
	
	
	/**
	 * perbaikan dari {@link #submitDataForApproval(CommonDualControlContainerTable, DualControlEnabledOperation)} , ini dengan return yang lebih lengkap
	 */
	@DoubleSubmitSecuredMethod
	public SimpleMasterDataDualControlApprovalResult submitMasterDataDataForApproval (  CommonDualControlContainerTable dualControlledData , DualControlEnabledOperation operation) throws Exception ;
	
	/**
	 * approve data dan apply langsung ke table target
	 **/
	@DoubleSubmitSecuredMethod
	public void approveAndApplyData ( CommonDualControlContainerTable dualControlledData) throws Exception; 
	
	
	
	
	/**
	 * approve data bulk. yang di kirim cuma Id dari approval data
	 */
	public void approveAndApplyBulkData ( Long bulkDataId ) throws Exception ;
	
	
	
	/**
	 * reject data bulk
	 * @param bulkDataId id dari data bulk
	 * @param rejectReason alasan penolakan
	 */
	public void rejectBulkData ( Long bulkDataId , String rejectReason) throws Exception ;
	
	
	/**
	 * reject semua data. ini di manfaatkan dalam timer untuk reject semua data yang masih waiting for approval. ini menjamin tidak ada data yang terlewat
	 * data di cari dengan pembatas tanggal yang di kirimkan dalam parameter. jadinya data yang sama atau lebih kecil akan di reject semua
	 */
	public void rejectAllWaitingApprovalData ( Date latestDateToFetch ,   String remarkForAllRejected ) throws Exception ;   
	
	/**
	 * @param dataId id data yang di reject
	 * @param rejectReason alasan data di tolak oleh user
	 **/
	public void rejectData (Long dataId , String rejectReason) throws Exception ; 
	
	
	
	/**
	 * membaca data object untuk di approve
	 * @param objectFQCN full qualified class name dari object yang perlu di baca
	 * @param filters query filters. dalam kasus ini, yang di pakai adalah key 1 dan key2 
	 * @param sortArguments argument untuk proses sorting data
	 **/
	public PagedResultHolder<CommonDualControlContainerTable> getDataRequiredApproval(String objectFQCN , SimpleQueryFilter[] filters , SimpleSortArgument[] sortArguments , int pageSize , int page )throws Exception;
	
	
	
	/**
	 * get data untuk proses update
	 **/
	public PagedResultHolder<? extends DualControlEnabledData<?, ?>> getDataForEditList(
			String objectFQCN, SimpleQueryFilter[] filters,
			SimpleSortArgument[] sortArguments, int pageSize, int page   ) throws Exception ;
	
	
	/**
	 * ini unutk membaca data data detail dari bulk approval. data di simpan dalam json array. method ini akan mereverse balik data menjadi object dan mengembalikan data ke client kembali. Sementara tidak ada filter yang di mungkinkan. Hanya paging data yang di sertakan. karena detail dari data tidak di simpan dalam database
	 * @param approvalDataId id dari approval data. ini refer ke table : m_dual_control_table
	 * @param pageSize ukuran page per pembacaan data
	 * @param page page berapa yang akan di baca
	 */
	public <DATA extends DualControlEnabledData<?,?>> PagedResultHolder<DATA> getBulkApprovalDataDetails ( Long approvalDataId , int pageSize, int page) throws Exception ;
	
	
	/**
	 * membaca definisi dual control table
	 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
	 */
	public List<DualControlDefinition> getMasterDataDualControlDefinitions () ; 
	
	/**
	 * handler upload bulk upload
	 * @param uploadedDataKey key untuk file upload. ini unutk mencari file dalam session
	 * @param targetClassFQCN fqcn dari item yang di handle
	 * @param remark catatan dari data
	 *  
	 */
	public  HeaderDataOnlyCommonDualControlContainerTable handleUploadedMasterFile(
			  String  uploadedDataKey,
			   String targetClassFQCN ,
			  String remark 
			 ) throws InvalidExcelFileException , Exception; 
	
	
	
	
	/**
	 * membaca data konfigurasi berbasis sistem 
	 * @param fqcn fqcn dari data
	 */
	public <D extends SystemParamDrivenClass<?, ?>>  AppConfigurationDrivenDetaiResultHolder<D> getSytemConfigurationDrivenData (String fqcn ) throws Exception ; 

	
	
	
	
	/**
	 * membaca data reject list. data di cari dengan current user. jadinya data di tampilan untuk data yang match dengan : 
	 * creator = current user
	 * rejector = current user. 
	 * @param filters filter ini exclude user dan status. status akan di apply dari dao
	 */
	public PagedResultHolder<CommonDualControlContainerTable> getRejectListData (SimpleQueryFilter[] filters , SimpleSortArgument[] sorts , int page , int pageSize ) ; 

}
