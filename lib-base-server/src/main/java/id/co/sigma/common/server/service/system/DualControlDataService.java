package id.co.sigma.common.server.service.system;
 
import id.co.sigma.common.data.AppConfigurationDrivenDetaiResultHolder;
import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.SystemParamDrivenClass;
import id.co.sigma.common.data.app.CommonDualControlContainerTable;
import id.co.sigma.common.data.app.DualControlDefinition;
import id.co.sigma.common.data.app.DualControlEnabledData;
import id.co.sigma.common.data.app.DualControlEnabledOperation;
import id.co.sigma.common.data.app.HeaderDataOnlyCommonDualControlContainerTable;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.exception.InvalidExcelFileException;
import id.co.sigma.common.server.spreadsheet.UploadedDataContainer;


import java.util.Date;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 * service untuk membantu dual control
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface DualControlDataService {
	
	
	
	/**
	 * worker untuk memproses data untuk proses approval
	 **/
	@Transactional(readOnly=false ,propagation=Propagation.REQUIRED)
	public CommonDualControlContainerTable submitDataForApproval(
			CommonDualControlContainerTable dualControlledData,
			DualControlEnabledOperation operation) throws Exception ; 
	 
	
	
	/**
	 * ini untuk request bulk approval. 
	 * @param uploadedData data di upload 
	 *  
	 */
	@Transactional(readOnly=false ,propagation=Propagation.REQUIRED)
	public HeaderDataOnlyCommonDualControlContainerTable submitBulkDataForApproval ( UploadedDataContainer<DualControlEnabledData<?, ?>> uploadedData , String uploadedFileName , String remark, String targetClassFQCN ) throws Exception ; 
 	
	/**
	 * apply data modification ke target data.ini adalah proses <strong>Approve</strong> .<br/>
	 * Kurang lebih ini akan menyalin data dari {@link CommonDualControlContainerTable} (data json) ke data tujuan
	 **/
	@Transactional(readOnly=false ,propagation=Propagation.REQUIRED)
	public void applyDataModification (Long dataApprovalId , String approvalRemark) throws Exception; 

	
	
	/**
	 * apply data bulk
	 */
	@Transactional(readOnly=false ,propagation=Propagation.REQUIRED)
	public void approveAndApplyBulkData(Long bulkDataId) throws Exception ; 
	
	/**
	 * reject data
	 **/
	@Transactional(readOnly=false ,propagation=Propagation.REQUIRED)
	public void rejectData(Long dataId, String rejectReason)
			throws Exception ; 
	
	
	/**
	 * ini untuk reject proses approval bulk
	 * @param dataId ID dari data
	 * @param rejectReason alasan penolakan
	 *  
	 */
	@Transactional(readOnly=false ,propagation=Propagation.REQUIRED)
	public void rejectBulkData(Long dataId, String rejectReason)
			throws Exception ; 
	
	
	
	/**
	 * yang ini untuk membaca data dengan berdasar id yang di "pack" menjadi 1 string
	 **/
	public DualControlEnabledData<?, ?> findTargetData(String classFQCN , String dataAsCompacteId) throws Exception  ;
	
	
	 
	/**
	 * akses data mentahan. return simple paged result holder
	 **/
	public PagedResultHolder<? extends DualControlEnabledData<?, ?>> listDataRaw( Class<? extends DualControlEnabledData<?, ?>> entCls,
			int page, int pageSize, SimpleQueryFilter[] filters,
			SimpleSortArgument[] sortArguments) throws Exception ; 
	
	 
	
	
	/**
	 * akses ke data dual control. return paged result holder
	 **/
	public PagedResultHolder<? extends DualControlEnabledData<?,?>> getDataForEditList(String objectFQCN,
			SimpleQueryFilter[] filters,
			SimpleSortArgument[] sortArguments, int pageSize, int page)
			throws Exception ;  
			
 
	
	/**
	 * ini unutk membaca data data detail dari bulk approval. data di simpan dalam json array. method ini akan mereverse balik data menjadi object dan mengembalikan data ke client kembali. Sementara tidak ada filter yang di mungkinkan. Hanya paging data yang di sertakan. karena detail dari data tidak di simpan dalam database
	 * @param approvalDataId id dari approval data. ini refer ke table : m_dual_control_table
	 * @param pageSize ukuran page per pembacaan data
	 * @param page page berapa yang akan di baca
	 */
	public <DATA extends DualControlEnabledData<?,?>> PagedResultHolder<DATA> getBulkApprovalDataDetails ( Long approvalDataId , int pageSize, int page) throws Exception ; 
 	
	
	/**
	 * membaca definisi dual control data based on FQCN 
	 */
	public DualControlDefinition getDualControlDefinition ( String fqcn ) ; 
	
	
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
			 ) throws InvalidExcelFileException , Exception ;
	
	
	
	/**
	 * reject semua data yang masih waiting for approval
	 */
	public void rejectAllWaitingApprovalData(Date latestDateToFetch,
			String remarkForAllRejected) throws Exception  ;  
	
	
	
	/**
	 * mengecek apakah ada data yang waiting approval
	 * @param fqcn fqcn dari data yang hendak di cek apakah ada waiting approval atau tidak
	 */
	public boolean haveWaitingApprovalData (  String fqcn  ); 
	
	
	
	/**
	 * hanya berlaku untuk data dengan single approval
	 * @param fqcn fqcn dari data yang hendak di load
	 */
	public CommonDualControlContainerTable getLatestWaitingApprovalData ( String fqcn ) throws Exception;
	
	/**
	 * membaca data konfigurasi berbasis sistem 
	 * @param fqcn fqcn dari data
	 */
	public <D extends SystemParamDrivenClass<?, ?>>  AppConfigurationDrivenDetaiResultHolder<D> getSytemConfigurationDrivenData (String fqcn ) throws Exception ;
	
			
}
