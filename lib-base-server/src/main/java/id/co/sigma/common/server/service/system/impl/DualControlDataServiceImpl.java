package id.co.sigma.common.server.service.system.impl;

import id.co.sigma.common.data.AppConfigurationDrivenDetaiResultHolder;
import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.SystemParamDrivenClass;
import id.co.sigma.common.data.app.CommonDualControlBulkDataContainer;
import id.co.sigma.common.data.app.CommonDualControlContainerTable;
import id.co.sigma.common.data.app.DualControlApprovalStatusCode;
import id.co.sigma.common.data.app.DualControlDefinition;
import id.co.sigma.common.data.app.DualControlEnabledData;
import id.co.sigma.common.data.app.DualControlEnabledOperation;
import id.co.sigma.common.data.app.HeaderDataOnlyCommonDualControlContainerTable;
import id.co.sigma.common.data.app.exception.InvalidDualControlStateException;
import id.co.sigma.common.data.app.exception.UnsupportedDualControlDataKeyType;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.data.query.SimpleQueryFilterOperator;
import id.co.sigma.common.exception.DataNotFoundException;
import id.co.sigma.common.exception.DataValidationException;
import id.co.sigma.common.exception.InvalidExcelFileException;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.dao.util.ServerSideParsedJSONArrayContainer;
import id.co.sigma.common.server.dao.util.ServerSideParsedJSONContainer;
import id.co.sigma.common.server.service.AbstractService;
import id.co.sigma.common.server.service.BaseCustomTargetDataLoader;
import id.co.sigma.common.server.service.ICustomBulkDataAdditionalPropertyBinder;
import id.co.sigma.common.server.service.ICustomBulkDataAdditionalPropertyBinderManager;
import id.co.sigma.common.server.service.ICustomTargetDataLoader;
import id.co.sigma.common.server.service.ICustomTargetDataLoaderContainer;
import id.co.sigma.common.server.service.dualcontrol.ICustomBulkDualControlAfterApproveTask;
import id.co.sigma.common.server.service.dualcontrol.ICustomBulkDualControlValidator;
import id.co.sigma.common.server.service.dualcontrol.ICustomBulkDualControlValidatorManager;
import id.co.sigma.common.server.service.dualcontrol.ICustomDualControlAfterApproveTask;
import id.co.sigma.common.server.service.dualcontrol.ICustomDualControlAfterApproveTaskManager;
import id.co.sigma.common.server.service.dualcontrol.ICustomDualControlSubmitValidator;
import id.co.sigma.common.server.service.system.BaseDualControlAdditionalDataBinder;
import id.co.sigma.common.server.service.system.DualControlDataService;
import id.co.sigma.common.server.service.system.ICommonSystemService;
import id.co.sigma.common.server.service.system.IDualControlAdditionalDataBinderManager;
import id.co.sigma.common.server.spreadsheet.ISpreadsheetFileUtil;
import id.co.sigma.common.server.spreadsheet.ISpreadsheetFileUtilManager;
import id.co.sigma.common.server.spreadsheet.UploadedDataContainer;
import id.co.sigma.common.server.util.ExtendedBeanUtils;
import id.co.sigma.common.util.ObjectGeneratorManager;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONArrayContainer;
import id.co.sigma.common.util.json.ParsedJSONContainer;
import id.co.sigma.common.util.json.SharedServerClientLogicManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


/**
 * layer service untuk handle dual controlled data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/

public class DualControlDataServiceImpl extends AbstractService 
	implements DualControlDataService  , ICustomTargetDataLoaderContainer  , 
		IDualControlAdditionalDataBinderManager  , ICustomBulkDualControlValidatorManager , 
		ICustomBulkDataAdditionalPropertyBinderManager , 
		ICustomDualControlAfterApproveTaskManager , InitializingBean{

	
	
	
	private static final Logger logger = LoggerFactory.getLogger(DualControlDataServiceImpl.class); 
	 
	
	private Map<String, ICustomTargetDataLoader<? extends DualControlEnabledData<?,?>>> indexedCustomGenerator =new HashMap<String, ICustomTargetDataLoader<? extends DualControlEnabledData<?,?>>>();
	
	
	
	
	private Map<String, ICustomBulkDataAdditionalPropertyBinder<?>> additionalDataBinders = new  HashMap<String, ICustomBulkDataAdditionalPropertyBinder<?>>() ; 
	
	/**
	 * task after approve
	 */
	private Map<String, ICustomDualControlAfterApproveTask<  ?> > indexedCustomAfterApproveTask = new HashMap<String, ICustomDualControlAfterApproveTask<  ?>>(); 
	
	/**
	 * task after approve
	 */
	private Map<String, ICustomBulkDualControlAfterApproveTask<?>  > indexedBulkCustomAfterApproveTask = new HashMap<String, ICustomBulkDualControlAfterApproveTask<?> >(); 
	
	
	
	
	
	/**
	 * key : fqcn 
	 * value : bean ref yang bertugas membind additional data. 
	 **/
	private Map<String, BaseDualControlAdditionalDataBinder<?>> indexedDataBinder = new HashMap<String, BaseDualControlAdditionalDataBinder<?>>(); 
	
	
	private Map<String, ICustomBulkDualControlValidator<?>> indexedBulkValidators = new HashMap<String, ICustomBulkDualControlValidator<?>>() ; 
	
	
	@Autowired
	private IGeneralPurposeDao generalPurposeDao ;
	
	@Autowired
	private ISpreadsheetFileUtilManager spreadsheetFileUtilManager ; 
	

	
	/**
	 * state yang bisa buat approval
	 **/
	private static final DualControlApprovalStatusCode[] APPROVAL_STATES = {
		DualControlApprovalStatusCode.WAITING_APPROVE_CREATE ,
		DualControlApprovalStatusCode.WAITING_APPROVE_DELETE , 
		DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE
		
	};
	
	
	private static final Map<String, DualControlApprovalStatusCode> REJECT_PATH = new HashMap<String, DualControlApprovalStatusCode>();
	static {
		REJECT_PATH.put(DualControlApprovalStatusCode.WAITING_APPROVE_CREATE.toString(), DualControlApprovalStatusCode.REJECTED_CREATE_DATA);
		REJECT_PATH.put(DualControlApprovalStatusCode.WAITING_APPROVE_DELETE.toString(), DualControlApprovalStatusCode.REJECTED_DELETE_DATA);
		REJECT_PATH.put(DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.toString(), DualControlApprovalStatusCode.REJECTED_UPDATE_DATA);
	}

	
	
	
	static SimpleSortArgument[] SORT_DUAL_CONTROL = new SimpleSortArgument[]{
		new SimpleSortArgument("createdTime" , false )
	};
	
	
	@Autowired
	private ICommonSystemService commonSystemService ;
	
	
	
	
	@Override
	public <DATA extends DualControlEnabledData<?, ?>> void registerValidatorBulk(
			ICustomBulkDualControlValidator<DATA> validator) {
		indexedBulkValidators.put(validator.getHandledClass().getName(), validator) ; 
	}
	
	@Override
	public CommonDualControlContainerTable submitDataForApproval(
			CommonDualControlContainerTable dualControlledData,
			DualControlEnabledOperation operation) throws Exception {
		
		CommonDualControlContainerTable actualRetval = null ; 
		
		// custom handler masuk di sini
		if ( indexedValidators.containsKey(dualControlledData.getTargetObjectFQCN()) &&  !DualControlEnabledOperation.DELETE.equals( operation)){
			indexedValidators.get(dualControlledData.getTargetObjectFQCN()).validateApprovalRequest(dualControlledData, operation); 
		}
		
		CommonDualControlContainerTable dbData = null ; 
		
		if ( dualControlledData.getId()!= null){
			dbData = generalPurposeDao.get(CommonDualControlContainerTable.class, dualControlledData.getId()); 
		}
		
		//FIXME : masukan audit trail di sini
		@SuppressWarnings("unchecked")
		Class<DualControlEnabledData<?,?>> targetPojo = (Class<DualControlEnabledData<?,?>>) Class.forName(dualControlledData.getTargetObjectFQCN()); 
		
		DualControlEnabledData<?,?> sampleObject =  BeanUtils.instantiate(targetPojo);
		DualControlEnabledData<?,?> passedData = (DualControlEnabledData<?,?>) sampleObject.instantiateFromJSON(  SharedServerClientLogicManager.getInstance().getJSONParser().parseJSON( 
				dualControlledData.getJsonData())); 
		
		DualControlEnabledData<?, ?> originalData = null ; 
		
		if (!DualControlEnabledOperation.INSERT.equals(operation) ){
			// select exiting
			if ( this.indexedCustomAfterApproveTask.containsKey(dualControlledData.getTargetObjectFQCN())){
				
				originalData =  this.indexedCustomAfterApproveTask.get(dualControlledData.getTargetObjectFQCN()).loadExistingData(  passedData.getPrimaryKey()); 
			}else{
				originalData = generalPurposeDao.get( targetPojo , passedData.getPrimaryKey());
			}
			  
		}
		
		DualControlApprovalStatusCode nextApprovalStatus = getNextApprovalStatusByOperationCode(operation); 		// next approval status
		
		
		Long retval = null ; 
		if ( dbData!= null && !DualControlApprovalStatusCode.APPLIED.toString().equals( dbData.getApprovalStatus())){
			// berarti dari draft dulu. jadinya proses nya update
			dbData.setJsonData(dualControlledData.getJsonData());
			dbData.setCreatorUserId(getCurrentUserName()); 
			dbData.setCreatedTime(new Date()); 
			generalPurposeDao.update(dbData); 
			retval = dbData.getId(); 
			actualRetval = dbData ; 
			generalPurposeDao.update(dbData);
			dualControlledData.setApprovalStatus(nextApprovalStatus.toString()) ; 
			
			
			
		}else{
			dualControlledData.setReffNo( generateReffNumber(dualControlledData.getTargetObjectFQCN()));
			dualControlledData.setCreatorUserId(getCurrentUserName()); 
			dualControlledData.setCreatedTime(new Date());
			dualControlledData.setOperationCode(operation.toString());
			dualControlledData.setApprovalStatus(nextApprovalStatus.toString());
			
			dualControlledData.setId(null);
			generalPurposeDao.insert(dualControlledData);
			// ini sakit kepala. musti cari object target dan taruh id di object target
			if ( !DualControlEnabledOperation.INSERT.equals(operation) && originalData!= null){
				originalData.setCurrentCommonDualControlId(dualControlledData.getId());
			}
			retval = dualControlledData.getId();
			actualRetval = dualControlledData ; 
		}
		if (  originalData!= null){
			originalData.setApprovalStatus(
					DualControlEnabledOperation.INSERT.equals(operation) ?
					DualControlApprovalStatusCode.WAITING_APPROVE_CREATE : (DualControlEnabledOperation.UPDATE.equals(operation)?DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE :DualControlApprovalStatusCode.WAITING_APPROVE_DELETE ) );
			originalData.setApprovalStatus(nextApprovalStatus);
			if ( this.indexedCustomAfterApproveTask.containsKey(dualControlledData.getTargetObjectFQCN())){
				
				this.indexedCustomAfterApproveTask.get(dualControlledData.getTargetObjectFQCN()).saveDataModification( originalData); 
			}else{
				generalPurposeDao.update(originalData);
			}
			
		}
		
		
		return actualRetval;
	}
	
	
	
	/**
	 * membaca next approval status berdasarkan operation
	 */
	private DualControlApprovalStatusCode getNextApprovalStatusByOperationCode ( DualControlEnabledOperation operation){
		if ( DualControlEnabledOperation.INSERT.equals(operation))
			return DualControlApprovalStatusCode.WAITING_APPROVE_CREATE ;  
		else if (( DualControlEnabledOperation.UPDATE.equals(operation)))
			return DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE ;
		else if (( DualControlEnabledOperation.DELETE.equals(operation)))
			return DualControlApprovalStatusCode.WAITING_APPROVE_DELETE ;  
		return null ; 
	}
	
	
	
	
	Map<String, DualControlDefinition> indexedDualControlDefinition = new HashMap<String, DualControlDefinition>(); 
	
	/**
	 * generate reff number untuk dual control def
	 */
	protected String generateReffNumber ( String className ) {
		DualControlDefinition def = this.indexedDualControlDefinition.get(className); 
		String prefix = def.getReffNumberPrefix() ; 
		if ( prefix== null )
			prefix = "" ;
		return commonSystemService.generateRefNumber(def.getReffNoSequenceRemark(), prefix, def.getReffNumberLength() - prefix.length(), def.getReffNoSequenceRemark()); 
	}
	@Override
	public void approveAndApplyBulkData(Long dataApprovalId) throws Exception {
		if ( dataApprovalId==null){
			throw new DataNotFoundException("No data id supplied, canceling approval now");
		}
			
		CommonDualControlContainerTable headerDBData = generalPurposeDao.get(CommonDualControlContainerTable.class, dataApprovalId);
		if ( headerDBData==null)
			throw new DataNotFoundException("Data not found for dual controlled data with id:" + dataApprovalId);
		
		String approvalStatusCode = headerDBData.getApprovalStatus(); 
		if (! DualControlApprovalStatusCode.WAITING_APPROVE_BULK.toString().equals(approvalStatusCode)){
			throw new InvalidDualControlStateException("Data currently not for approval, somebody else probably already approve this data",  APPROVAL_STATES,  DualControlApprovalStatusCode.generateFromRawString(approvalStatusCode));
		}
		Class<?  > cls = Class.forName(headerDBData.getTargetObjectFQCN());
		DualControlEnabledData<?, ?> sample  =(DualControlEnabledData<?, ?>) BeanUtils.instantiate(cls);
		// cari dulu dari 
		CommonDualControlBulkDataContainer blk =  generalPurposeDao.get(CommonDualControlBulkDataContainer.class, dataApprovalId);
		String jsonData =  blk.getBulkDataAsJson();
		ParsedJSONArrayContainer arrContainer =  SharedServerClientLogicManager.getInstance().getJSONParser().parseJSONArray(jsonData);
		int len =  arrContainer.length();
		
		String approverUserId = getCurrentUserName(); 
		
		// validasi data
		if ( indexedBulkValidators.containsKey(headerDBData.getTargetObjectFQCN())){
			ArrayList<DualControlEnabledData<?, ?>> createds = new ArrayList<DualControlEnabledData<?,?>>(); 
			ArrayList<DualControlEnabledData<?, ?>> modifs = new ArrayList<DualControlEnabledData<?,?>>();
			for ( int i=0 ; i< len  ; i++){
				ParsedJSONContainer jsActual = arrContainer.get(i);
				DualControlEnabledData<?, ?> atomicJsonData = sample.instantiateFromJSON(jsActual);
				if ( atomicJsonData.getApprovalStatus()== null)
					continue ; 
				if ( DualControlApprovalStatusCode.WAITING_APPROVE_CREATE.equals(atomicJsonData.getApprovalStatus())){
					createds.add(atomicJsonData); 
				}else if ( DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.equals(atomicJsonData.getApprovalStatus())){
					modifs.add(atomicJsonData);
				}
			}
			ICustomBulkDualControlValidator validatorK =  indexedBulkValidators.get(headerDBData.getTargetObjectFQCN());
			validatorK.validatedBulkData(createds, modifs, false);
		}
		
		
		// save
		
		if ( this.indexedBulkCustomAfterApproveTask.containsKey(headerDBData.getTargetObjectFQCN())){
			ICustomBulkDualControlAfterApproveTask h = indexedBulkCustomAfterApproveTask.get(headerDBData.getTargetObjectFQCN());
			for ( int i=0 ; i< len  ; i++){
				ParsedJSONContainer jsActual = arrContainer.get(i);
				DualControlEnabledData<?, ?> atomicJsonData = sample.instantiateFromJSON(jsActual);
				if ( atomicJsonData.getApprovalStatus()== null)
					break ; 
				if ( DualControlApprovalStatusCode.WAITING_APPROVE_CREATE.equals(atomicJsonData.getApprovalStatus())){
					h.approveCreateNewData( atomicJsonData, approverUserId);
				}else if ( DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.equals(atomicJsonData.getApprovalStatus())){
					h.approveEditData(atomicJsonData, approverUserId);
				}
			}
		}else {
			
			Map<Serializable, DualControlEnabledData<?, ?>> indexedModifiedDta = new HashMap<Serializable, DualControlEnabledData<?,?>>(); 
			ArrayList<Serializable> idOnlyData = new ArrayList<Serializable>();  
					
 			for ( int i=0 ; i< len  ; i++){
				ParsedJSONContainer jsActual = arrContainer.get(i);
				DualControlEnabledData<?, ?> atomicJsonData = sample.instantiateFromJSON(jsActual);
				if ( atomicJsonData.getApprovalStatus()== null)
					break ; 
				if ( DualControlApprovalStatusCode.WAITING_APPROVE_CREATE.equals(atomicJsonData.getApprovalStatus())){
					atomicJsonData.setApprovalStatus(DualControlApprovalStatusCode.APPLIED);
					touchCreateTimestamp(atomicJsonData);
					atomicJsonData.setApprovalStatus(DualControlApprovalStatusCode.APPLIED);
					atomicJsonData.setActiveFlag(DualControlEnabledData.DualDataActiveStatusFlag.ACTIVE.toString()) ;
					//add by dode
					//manambahkan set current dual control id di data actualnya
					atomicJsonData.setCurrentCommonDualControlId(null);
					generalPurposeDao.insert(atomicJsonData);
				}else if ( DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.equals(atomicJsonData.getApprovalStatus())){
					Serializable  key = atomicJsonData.getPrimaryKey(); 
					indexedModifiedDta.put(atomicJsonData.getPrimaryKey()	, atomicJsonData); 
					idOnlyData.add(key); 
				}
			}
			if ( !indexedModifiedDta.isEmpty()){
				List<DualControlEnabledData<?, ?>> dbDataList =  generalPurposeDao.loadDataByGenericKeys(cls
						, sample.getPrimaryKeyJPAName(), idOnlyData);
				if ( dbDataList!= null && !dbDataList.isEmpty()){
					for ( DualControlEnabledData<?, ?> scn : dbDataList){
						if ( !indexedModifiedDta.containsKey(scn.getPrimaryKey()))
							continue ; 
						
						DualControlEnabledData<?, ?> mdfData = indexedModifiedDta.get(scn.getPrimaryKey()); 
						scn.setApprovalStatus(DualControlApprovalStatusCode.APPLIED);
						ExtendedBeanUtils.getInstance().copyPropertiesWithSpecifiedItemOnly(mdfData, scn, mdfData.retrieveModifableFields());
						touchModifyTimestamp(scn);
					}
					generalPurposeDao.updates(dbDataList);
				}
			}
			
		}
		// sudah kelar, update header nya
		headerDBData.setApprovalStatus(DualControlApprovalStatusCode.APPLIED.toString());
		headerDBData.setApprovedTime(new Date());
		headerDBData.setApproverUserId(approverUserId);
		this.generalPurposeDao.update(headerDBData);
		
	}
	
	
	
	@Override
	public void applyDataModification(Long dataApprovalId, String approvalRemark)
			throws Exception {
		if ( dataApprovalId==null){
			throw new DataNotFoundException("No data id supplied, canceling approval now");
		}
			
		CommonDualControlContainerTable dbData = generalPurposeDao.get(CommonDualControlContainerTable.class, dataApprovalId);
		if ( dbData==null)
			throw new DataNotFoundException("Data not found for dual controlled data with id:" + dataApprovalId);
		
		String approvalStatusCode = dbData.getApprovalStatus(); 
		if (!( DualControlApprovalStatusCode.WAITING_APPROVE_CREATE.toString().equals(approvalStatusCode) 
				||DualControlApprovalStatusCode.WAITING_APPROVE_DELETE.toString().equals(approvalStatusCode)
				||DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.toString().equals(approvalStatusCode))
				){
			throw new InvalidDualControlStateException("Data currently not for approval, somebody else probably already approve this data",  APPROVAL_STATES,  DualControlApprovalStatusCode.generateFromRawString(approvalStatusCode));
		}
		
		
		
		// cari original data
		Class<?  > cls = Class.forName(dbData.getTargetObjectFQCN());
		DualControlEnabledData<?, ?> sample  =(DualControlEnabledData<?, ?>) BeanUtils.instantiate(cls);
		DualControlEnabledData<?, ?>  fromJsonData =  (DualControlEnabledData<?, ?>)sample.instantiateFromJSON(  SharedServerClientLogicManager.getInstance().getJSONParser().parseJSON( 
				dbData.getJsonData()));  
		
		// flag data applied dan set audi trail
		dbData.setApprovalStatus(DualControlApprovalStatusCode.APPLIED.toString());
		dbData.setApprovedTime(new Date());
		dbData.setApproverUserId(getCurrentUserName()) ; 
		// update table m_dual_control
		dbData.setApprovalRemark(approvalRemark);
		generalPurposeDao.update(dbData);
		
		
		boolean virtualTarget =false;// table virtual atau bukan. refer ke id.co.sigma.common.server.service.dualcontrol.ICustomDualControlAfterApproveTask.isUseVirtualTable() 
		if ( indexedCustomAfterApproveTask.containsKey(dbData.getTargetObjectFQCN())){
			virtualTarget = indexedCustomAfterApproveTask.get(dbData.getTargetObjectFQCN()).isUseVirtualTable();
		}
		
		 
			
		//fixme : masukan data audit trail
		if ( DualControlApprovalStatusCode.WAITING_APPROVE_CREATE.toString().equals(approvalStatusCode)){
			
			//FIXME: mungkin akan di perlukan algoritma untuk generate primary key nya
			fromJsonData.setApprovalStatus(DualControlApprovalStatusCode.APPLIED);
			touchCreateTimestamp(fromJsonData);
			fromJsonData.setApprovalStatus(DualControlApprovalStatusCode.APPLIED);
			fromJsonData.setActiveFlag(DualControlEnabledData.DualDataActiveStatusFlag.ACTIVE.toString()) ;
			
			//add by dode
			//manambahkan set current dual control id di data actualnya
			runValidationBeforeInsertData(dbData, cls, approvalStatusCode, fromJsonData);
			fromJsonData.setCurrentCommonDualControlId(dbData.getId());
			if ( !virtualTarget)
				generalPurposeDao.insert(fromJsonData);
		 
			//add by dode
			//update db data -> json data, set id nya dengan id yang di dapat dari save
			dbData.setJsonData(fromJsonData.generateJSONString());
			generalPurposeDao.update(dbData);
			fireAdditionalApprovalTask(dbData , cls, approvalStatusCode, fromJsonData);
		}
		else{
			DualControlEnabledData<?, ?>  targetDb = null ; 
			if ( !virtualTarget) {
				targetDb =  findTargetData(dbData);
			}
			else{
				targetDb = fromJsonData;
			}
			
			if (DualControlApprovalStatusCode.WAITING_APPROVE_DELETE.toString().equals(approvalStatusCode) ){ // edit existing
				fireAdditionalApprovalTask(dbData , cls, approvalStatusCode, targetDb);
				
				if ( sample.isEraseDataOnApproveErase() ){
					if ( !virtualTarget) { // hanya update ke legacy kalau non virtual target
						generalPurposeDao.delete(targetDb);
					}
				}
				else{
					targetDb.setActiveFlag(DualControlEnabledData.DualDataActiveStatusFlag.NOT_ACTIVE.toString());
					touchModifyTimestamp(targetDb);
					targetDb.setApprovalStatus(DualControlApprovalStatusCode.APPLIED);
					if ( !virtualTarget) {// hanya update ke legacy kalau non virtual target
						generalPurposeDao.update(targetDb);
					}
				}
			}
			else { // update existing approval
				
				if (!virtualTarget){ // cek dulu, ini virtual target atau bukan, kalau virtual maka di abaikan saja
					if ( targetDb!= null){
						runValidationBeforeUpdateExistingData(dbData, cls, approvalStatusCode, fromJsonData);
						targetDb.setApprovalStatus(DualControlApprovalStatusCode.APPLIED);
						ExtendedBeanUtils.getInstance().copyPropertiesWithSpecifiedItemOnly(fromJsonData, targetDb, fromJsonData.retrieveModifableFields());
						generalPurposeDao.update(targetDb);
					}
				}
				fireAdditionalApprovalTask(dbData , cls, approvalStatusCode, targetDb);
			}
		}
		// custom approval task
	
		
	}

	
	
	
	/**
	 * memasukan validasi. task ini di run sebelum validasi sebelum data di masukan ke dalam database
	 * @param rawData data mentahan 
	 * @param cls  fqcn dari class yang di validasi insert
	 * @param approvalStatusCode approval status
	 * @param dataThatApproved data yang akan di approve
	 */
	@SuppressWarnings("unchecked")
	protected void runValidationBeforeInsertData (CommonDualControlContainerTable  rawData ,  Class<?> cls , String approvalStatusCode ,  DualControlEnabledData<?, ?> dataThatApproved) throws DataValidationException{
		if (!indexedCustomAfterApproveTask.containsKey(cls.getName())){
			return ; 
		}
		@SuppressWarnings("rawtypes")
		ICustomDualControlAfterApproveTask rawValidator = indexedCustomAfterApproveTask.get(cls.getName());
		rawValidator.runValidationBeforeAddNewData(rawData, dataThatApproved);
	}
	
	
	
	
	
	/**
	 * run validation sebelum update
	 */
	protected void runValidationBeforeUpdateExistingData (CommonDualControlContainerTable  rawData ,  Class<?> cls , String approvalStatusCode ,  DualControlEnabledData<?, ?> dataThatApproved) throws DataValidationException{
		if (!indexedCustomAfterApproveTask.containsKey(cls.getName())){
			return ; 
		}
		@SuppressWarnings("rawtypes")
		ICustomDualControlAfterApproveTask rawValidator = indexedCustomAfterApproveTask.get(cls.getName());
		rawValidator.runValidationBeforeEditExistingData(rawData, dataThatApproved);
	}
	
	/**
	 * run task additional. untuk create + edit after, untuk delete --> before actual approve 
	 */
	@SuppressWarnings("unchecked")
	protected void fireAdditionalApprovalTask (CommonDualControlContainerTable  rawData ,  Class<?> cls , String approvalStatusCode ,  DualControlEnabledData<?, ?> dataThatApproved) throws Exception{
		if ( indexedCustomAfterApproveTask.containsKey(cls.getName())){
			@SuppressWarnings("rawtypes")
			ICustomDualControlAfterApproveTask handler =  this.indexedCustomAfterApproveTask.get(cls.getName());
			if ( DualControlApprovalStatusCode.WAITING_APPROVE_CREATE.toString().equals(approvalStatusCode)){
				handler.afterApproveCreateNewData( rawData ,  dataThatApproved, getCurrentUserName());
			}
			else if (DualControlApprovalStatusCode.WAITING_APPROVE_DELETE.toString().equals(approvalStatusCode) ){
				handler.afterApproveDeleteData(rawData , dataThatApproved, getCurrentUserName());
			}
			else {
				handler.afterApproveEditData(rawData , dataThatApproved, getCurrentUserName());
			}
		}
	}
	
	
	
	
	private DualControlEnabledData<?, ?> findTargetData (CommonDualControlContainerTable approvalData)  throws Exception{
		
		
		Class<?  > cls = Class.forName(approvalData.getTargetObjectFQCN());
		DualControlEnabledData<?, ?> sample  =(DualControlEnabledData<?, ?>) BeanUtils.instantiate(cls);
		DualControlEnabledData<?, ?>  fromJsonData =  (DualControlEnabledData<?, ?>)sample.instantiateFromJSON(  SharedServerClientLogicManager.getInstance().getJSONParser().parseJSON( 
				approvalData.getJsonData()));  
		Serializable pkTarget =  fromJsonData.getPrimaryKey();
		return generalPurposeDao.get(cls, pkTarget);
		 
	}
	
	
	
	
	
	public DualControlEnabledData<?, ?> findTargetData(final String classFQCN ,final String dataAsCompacteId) throws Exception {
		
		
		return getOrCreateCustomDataLoader(classFQCN).loadDataByPackedId(dataAsCompacteId);
	}
	
	
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ICustomTargetDataLoader<? extends DualControlEnabledData<?,?>> getOrCreateCustomDataLoader ( String fqcn ) throws Exception{
		if ( indexedCustomGenerator.containsKey(fqcn)){
			return indexedCustomGenerator.get(fqcn);
		}
		
		
		final Class<DualControlEnabledData<?, ?>>  cls = (Class<DualControlEnabledData<?, ?>>)Class.forName(fqcn);  
		DualControlEnabledData<?, ?> sampleData = BeanUtils.instantiate(cls);
		Class clsPK =  sampleData.getPrimaryKeyClassType();
		ICustomTargetDataLoader<? extends DualControlEnabledData<?,?>> baru  = null ; 
		if (String.class.equals(  clsPK)  ){
			baru =   new BaseCustomTargetDataLoader() {
			 
				@Override
				public Class getTargetClass() {
					return cls;
				}
				
				@Override
				public PagedResultHolder listDataRaw(int page, int pageSize,
						SimpleQueryFilter[] filters,
						SimpleSortArgument[] sortArguments)
						throws Exception {
					return genericlistDataRaw(this.getTargetClass(), page, pageSize, filters, sortArguments);
				}
			};
			
		}else if (BigInteger.class.equals(clsPK) ){
			baru =   new BaseCustomTargetDataLoader () {
				@Override
				public Class getTargetClass() {
					return cls;
				}
					
				@Override
				public PagedResultHolder listDataRaw(int page, int pageSize,
						SimpleQueryFilter[] filters,
						SimpleSortArgument[] sortArguments)
						throws Exception {
					return genericlistDataRaw(this.getTargetClass(), page, pageSize, filters, sortArguments);
				}
			};
		}else if (Long.class.equals(clsPK) ){
			baru =   new BaseCustomTargetDataLoader () {
				@Override
				public Class getTargetClass() {
					return cls;
				}
					
				@Override
				public PagedResultHolder listDataRaw(int page, int pageSize,
						SimpleQueryFilter[] filters,
						SimpleSortArgument[] sortArguments)
						throws Exception {
					return genericlistDataRaw(this.getTargetClass(), page, pageSize, filters, sortArguments);
				}
			};
		}
		else if (Integer.class.equals(clsPK) ){
			baru =   new BaseCustomTargetDataLoader () {
				@Override
				public Class getTargetClass() {
					return cls;
				}
				@Override
				public DualControlEnabledData loadDataByPackedId(String packedId) throws Exception {
					return generalPurposeDao.get(cls, Integer.parseInt( packedId));
				}@Override
				public PagedResultHolder listDataRaw(int page, int pageSize,
						SimpleQueryFilter[] filters,
						SimpleSortArgument[] sortArguments)
						throws Exception {
					return genericlistDataRaw(this.getTargetClass(), page, pageSize, filters, sortArguments);
				}
			};
		}
		if ( baru==null){
			new UnsupportedDualControlDataKeyType("Built in support hanya di sediakan untuk Integer, Long, BigInteger, String.\nCustom Loader juga tidak ditemukan untuk object : "  + cls.getName() +"(primary key : " + clsPK.getName() +").\nData ini tidak bisa di baca. Mohon sampaikan ke tim development anda" , sampleData );
		}
		indexedCustomGenerator.put(cls.getName(), baru);
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(baru); 
		return baru ; 
		
	}
	
	public static void main (String []arg){
		DualControlDataServiceImpl sample = new DualControlDataServiceImpl(); 
                sample.renderReadMeFile( );
	}
	
	
	
	@Override
	public void rejectBulkData(Long dataId, String rejectReason)
			throws Exception {
		CommonDualControlContainerTable dbData =  this.generalPurposeDao.get(CommonDualControlContainerTable.class, dataId);
		if ( dbData ==null){
			throw new DataNotFoundException("Unable to find approval data on table :m_dual_control_table with ID :" + dataId +", reject cannot be done" );
		}
		String approvalStatusCode =  dbData.getApprovalStatus(); 
		if (!DualControlApprovalStatusCode.WAITING_APPROVE_BULK.toString().equals(approvalStatusCode)){
		
			throw new InvalidDualControlStateException("Data currently not for approval, somebody else probably already approve this data",  APPROVAL_STATES,  DualControlApprovalStatusCode.generateFromRawString(approvalStatusCode));
		}
		
		
		
		dbData.setApprovalStatus(DualControlApprovalStatusCode.REJECTED_BULK_DATA.toString()); 
		dbData.setApprovedTime(new Date()); 
		dbData.setApproverUserId(getCurrentUserName());
		dbData.setApprovalRemark(rejectReason);
		generalPurposeDao.update(dbData); 
		// restore data 
		CommonDualControlBulkDataContainer blk =  generalPurposeDao.get(CommonDualControlBulkDataContainer.class, dataId);
		String jsonData =  blk.getBulkDataAsJson();
		ParsedJSONArrayContainer arrContainer =  SharedServerClientLogicManager.getInstance().getJSONParser().parseJSONArray(jsonData);
		int len = arrContainer.length();
		
		Class<?  > cls = Class.forName(dbData.getTargetObjectFQCN());
		DualControlEnabledData<?, ?> sample  =(DualControlEnabledData<?, ?>) BeanUtils.instantiate(cls);
		ArrayList<DualControlEnabledData<?, ?>> ids = new ArrayList<DualControlEnabledData<?, ?>>();
		for ( int i=0 ; i< len  ; i++){
			ParsedJSONContainer jsActual = arrContainer.get(i);
			DualControlEnabledData<?, ?> atomicJsonData = sample.instantiateFromJSON(jsActual);
			if ( atomicJsonData.getApprovalStatus()== null)
				break ; 
			 if ( DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.equals(atomicJsonData.getApprovalStatus())){
				ids.add(atomicJsonData); 
			}
		}
		markBulkUpladedApprovalStatus(ids, DualControlApprovalStatusCode.APPLIED); 
	}
	
	@Override
	public void rejectData(Long dataId, String rejectReason)
			throws Exception {
		// cari dulu data
		CommonDualControlContainerTable dbData =  this.generalPurposeDao.get(CommonDualControlContainerTable.class, dataId);
		if ( dbData ==null){
			throw new DataNotFoundException("Unable to find approval data on table :m_dual_control_table with ID :" + dataId +", reject cannot be done" );
		}
		String approvalStatusCode =  dbData.getApprovalStatus(); 
		if (!( DualControlApprovalStatusCode.WAITING_APPROVE_CREATE.toString().equals(approvalStatusCode) 
				||DualControlApprovalStatusCode.WAITING_APPROVE_DELETE.toString().equals(approvalStatusCode)
				||DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.toString().equals(approvalStatusCode))
				||DualControlApprovalStatusCode.WAITING_APPROVE_BULK.toString().equals(approvalStatusCode)
				){
			throw new InvalidDualControlStateException("Data currently not for approval, somebody else probably already approve this data",  APPROVAL_STATES,  DualControlApprovalStatusCode.generateFromRawString(approvalStatusCode));
		}
		
		dbData.setApprovalStatus(REJECT_PATH.get(approvalStatusCode).toString()); 
		dbData.setApprovedTime(new Date()); 
		dbData.setApproverUserId(getCurrentUserName());
		dbData.setApprovalRemark(rejectReason);
		generalPurposeDao.update(dbData); 
		
		boolean virtualTarget =false;// table virtual atau bukan. refer ke id.co.sigma.common.server.service.dualcontrol.ICustomDualControlAfterApproveTask.isUseVirtualTable() 
		if ( indexedCustomAfterApproveTask.containsKey(dbData.getTargetObjectFQCN())){
			virtualTarget = indexedCustomAfterApproveTask.get(dbData.getTargetObjectFQCN()).isUseVirtualTable();
		}  
		
		if (! virtualTarget){
			// 2 cari data target nya, kalau bukan create
			if (DualControlApprovalStatusCode.WAITING_APPROVE_DELETE.toString().equals(approvalStatusCode)
					||DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.toString().equals(approvalStatusCode) ){
				DualControlEnabledData<?, ?>  targetData = findTargetData(dbData);
				if  ( targetData != null ){
					if (DualControlApprovalStatusCode.WAITING_APPROVE_DELETE.toString().equals(approvalStatusCode) ){
						targetData.setApprovalStatus(DualControlApprovalStatusCode.APPLIED);
						targetData.setCurrentCommonDualControlId(null);
						generalPurposeDao.update(targetData);
					}else{
						targetData.setApprovalStatus(DualControlApprovalStatusCode.APPLIED);
						targetData.setCurrentCommonDualControlId(null);
						generalPurposeDao.update(targetData);
					}
				}
			}
		}
		
		
	}
	@Override
	public void register(ICustomTargetDataLoader<?> loader) {
		this.indexedCustomGenerator.put(loader.getTargetClass().getName()	, loader);
		
	}
	
	
	 
	
	
	
	
	
	 
	
	
	
	
	/**
	 * predefined argument list data yang bisa di edit. ini ambil berdasarkan approval status flag + active status seharusnya
	 **/
	private Map<String, SimpleQueryFilter[]> indexedPredefinedEditListQueryFilter = new HashMap<String, SimpleQueryFilter[]>();
	
	
	
	private Map<String, DualControlEnabledData<?, ?>> indexedSampleObject = new HashMap<String, DualControlEnabledData<?,?>>();
	
	
	
	static final String[] EDITABLE_STATUS_CODES ={
		DualControlApprovalStatusCode.APPLIED.toString() , 
		DualControlApprovalStatusCode.WAITING_APPROVE_DELETE.toString() , 
		DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.toString(),
		DualControlApprovalStatusCode.WAITING_APPROVE_BULK.toString()
	}; 
	
	
	@SuppressWarnings("unchecked")
	@Override
	public PagedResultHolder<? extends DualControlEnabledData<?,?>> getDataForEditList(String objectFQCN,
			SimpleQueryFilter[] filters,
			SimpleSortArgument[] sortArguments, int pageSize, int page)
			throws Exception {
		if ( !indexedPredefinedEditListQueryFilter.containsKey(objectFQCN)){
			Class<?> dataClass = Class.forName(objectFQCN);
			DualControlEnabledData<?, ?> sampleData = (DualControlEnabledData<?, ?>) BeanUtils.instantiate(dataClass);
			indexedSampleObject.put(objectFQCN, sampleData); 
			SimpleQueryFilter[] preDef= null ;  
			
			SimpleQueryFilter inSMt = new SimpleQueryFilter();
			inSMt.setField(sampleData.getApprovalStatusJPAAnnotatedField());
			inSMt.setFilter(EDITABLE_STATUS_CODES);
			
					
			if ( 		sampleData.isEraseDataOnApproveErase() ){
					// erase on approve. jadinya ndak ada yang status nya di hapus
				
				 
				preDef = new SimpleQueryFilter[]{inSMt} ; 
			}
			else{			// di flag. berarti anda harus menambah kan flag 
				preDef = 	new SimpleQueryFilter[]{
						inSMt, 
						new SimpleQueryFilter(sampleData.getActiveFlagJPAAnnotatedField(), SimpleQueryFilterOperator.equal, DualControlEnabledData.DualDataActiveStatusFlag.ACTIVE.toString() )
					}	; 
			}
			indexedPredefinedEditListQueryFilter.put(objectFQCN, preDef);
			indexedSampleObject.put(objectFQCN, sampleData);
		}
		
		
		SimpleQueryFilter[] passedFilter = null ; 
		if ( filters==null){
			passedFilter = indexedPredefinedEditListQueryFilter.get(objectFQCN); 
		}else{
			SimpleQueryFilter[] arr = indexedPredefinedEditListQueryFilter.get(objectFQCN);
			passedFilter = new SimpleQueryFilter[filters.length + arr.length];
			 
			for ( int i = 0   ; i< filters.length ; i++){
				passedFilter[i] = filters[i];
			}
			for ( int i = 0 ;  i< arr.length ; i++){
				passedFilter[filters.length+i] = arr[i];
			}	
		}
		
		return listDataRaw( (Class<? extends DualControlEnabledData<?, ?>>) indexedSampleObject.get(objectFQCN).getClass(), page, pageSize, passedFilter, sortArguments);
	}
	@Override
	public void register(BaseDualControlAdditionalDataBinder<?> dataBinder) {
		indexedDataBinder.put(dataBinder.getHandledClass().getName()	, dataBinder);
		
	}
	/**
	 * akses data mentahan
	 **/
	public PagedResultHolder<? extends DualControlEnabledData<?, ?>> listDataRaw( Class<? extends DualControlEnabledData<?, ?>> entCls,
			int page, int pageSize, SimpleQueryFilter[] filters,
			SimpleSortArgument[] sortArguments) throws Exception  {
		
		return this.getOrCreateCustomDataLoader(entCls.getName()).listDataRaw(page, pageSize, filters, sortArguments); 
	}
	
	
	 
	@Override
	public void register(ICustomDualControlAfterApproveTask<    ?> handler) {
		this.indexedCustomAfterApproveTask.put(handler.getHandledClass().getName(), handler); 
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public <DATA extends DualControlEnabledData<?, ?>> PagedResultHolder<DATA> getBulkApprovalDataDetails(
			Long approvalDataId, int pageSize, int page) throws Exception {
		
		CommonDualControlBulkDataContainer c = this.generalPurposeDao.get(CommonDualControlBulkDataContainer.class, approvalDataId);
		//FIXME: pembacaan json perlu enhancment, kalau data jumlah nya terlalu banyak
		String jsonString = c.getBulkDataAsJson(); 
		logger.debug("bulk data untuk approval id : " + approvalDataId + " >" + jsonString);
		
		ParsedJSONArrayContainer arrayContainer = SharedServerClientLogicManager.getInstance().getJSONParser().parseJSONArray(   jsonString );
		IJSONFriendlyObject<?> sampleObject =  ObjectGeneratorManager.getInstance().instantiateSampleObject(  c.getDualControlContainerTable().getTargetObjectFQCN());
		
		
		
		
		PagedResultHolder<DATA> retval = new PagedResultHolder<DATA>(); 
		retval.setHoldedData(new ArrayList<DATA>());
		retval.adjustPagination(page, pageSize, c.getDataCount());
		int start =  retval.getFirstRowPosition();
		int max = start+ pageSize ;
		if ( max>arrayContainer.length())
			max = arrayContainer.length(); 
		for ( int i = start ; i< max; i++){
			ParsedJSONContainer elem =  arrayContainer.get(i) ; 
			retval.getHoldedData().add( (DATA) sampleObject.instantiateFromJSON(elem )); 
		}
		if ( indexedDataBinder.containsKey(c.getDualControlContainerTable().getTargetObjectFQCN())){
			BaseDualControlAdditionalDataBinder indexed = indexedDataBinder.get(c.getDualControlContainerTable().getTargetObjectFQCN());  
			indexed.bindAdditionalData( retval.getHoldedData());
		}
		return retval;
	}
	
	
	
	private Map<String, ICustomDualControlSubmitValidator<?>> indexedValidators = new  HashMap<String, ICustomDualControlSubmitValidator<?>>() ; 
	@Override
	public void register(ICustomDualControlSubmitValidator<?> validator) {
		indexedValidators.put(validator.getValidatedType().getName(), validator);
	}

	@Override
	public void register(ICustomBulkDualControlAfterApproveTask<?> handler) {
		this.indexedBulkCustomAfterApproveTask.put(handler.getHandledClass().getName(), handler); 
	}



	@SuppressWarnings("unchecked")
	@Override
	public HeaderDataOnlyCommonDualControlContainerTable submitBulkDataForApproval(
			UploadedDataContainer<DualControlEnabledData<?, ?>> uploadedData , String uploadedFileName, String remark, String targetClassFQCN )
			throws Exception {
		
		if ( this.indexedBulkValidators.containsKey(targetClassFQCN)){
			//this.indexedBulkValidators.get(targetClassFQCN).validatedBulkData(uploadedData.getUploadedNewData(), uploadedData.getUpladedUpdatedExistingData());
			ICustomBulkDualControlValidator val = this.indexedBulkValidators.get(targetClassFQCN);
			val.validatedBulkData(uploadedData.getUploadedNewData(), uploadedData.getUpladedUpdatedExistingData(), true);
		}
		
		int totalData = uploadedData.getUpladedUpdatedExistingData().size() + uploadedData.getUploadedNewData().size() ; 
		HeaderDataOnlyCommonDualControlContainerTable h = new HeaderDataOnlyCommonDualControlContainerTable(); 
		h.setFileName(uploadedFileName);
		h.setApprovalStatus(DualControlApprovalStatusCode.WAITING_APPROVE_BULK.toString());
		h.setLineCount(totalData);
		h.setTargetObjectFQCN(targetClassFQCN);
		h.setCreatorUserId(getCurrentUserName());
		h.setCreatedTime(new Date());
		h.setOperationCode(DualControlEnabledOperation.INSERT.toString()); 
		h.setReffNo( generateReffNumber(targetClassFQCN));
		h.setSingleLineDataTypeFlag("N");
		h.setLatestRemark(remark);
		this.generalPurposeDao.insert(h);
		
		ArrayList<DualControlEnabledData<?, ?>> allUploaded = new ArrayList<DualControlEnabledData<?,?>>() ; 
		if (! uploadedData.getUploadedNewData().isEmpty()){
			allUploaded.addAll(uploadedData.getUploadedNewData()) ;
		}
		if ( !uploadedData.getUpladedUpdatedExistingData().isEmpty()){
			allUploaded.addAll(uploadedData.getUpladedUpdatedExistingData());	
			// mark data menjadi waiting approval
			markBulkUpladedApprovalStatus(uploadedData.getUpladedUpdatedExistingData(), DualControlApprovalStatusCode.WAITING_APPROVE_BULK); 
		}
		CommonDualControlBulkDataContainer blkData = new CommonDualControlBulkDataContainer() ;
		
		blkData.setDualControlContainerTable(h);
		blkData.setId(h.getId());
		blkData.setDataCount(totalData);
		
		
		
		ServerSideParsedJSONContainer prsr = new ServerSideParsedJSONContainer(); 
		blkData.translateToJSON(prsr);
		prsr.put("bulkDataAsJson", allUploaded);
		
		
		ParsedJSONArrayContainer arr = new ServerSideParsedJSONArrayContainer();
		for ( DualControlEnabledData<?, ?>  scn : allUploaded){
			ServerSideParsedJSONContainer c = new ServerSideParsedJSONContainer() ; 
			scn.translateToJSON(c);
			arr.appendToArray(c);
			
		}
		
		
		
		blkData.setBulkDataAsJson(arr.getJSONString()); 
		this.generalPurposeDao.insert(blkData); 
		return h;
	}

	
	
	
	/**
	 * menandai data dalam excel bulk, (bagian approval status). ini akan mencegah data di edit, atau di pergunakan pada saat reject data. data perlu di restore menjadi applied, agar bisa di edit
	 * @param editedDatas data yang di edit
	 * @param approvalStatus data approval status yang di set ke dalam data
	 */
	protected int markBulkUpladedApprovalStatus ( List<  DualControlEnabledData<?, ?>> editedDatas  , DualControlApprovalStatusCode approvalStatus) {
		if ( editedDatas== null || editedDatas.isEmpty())
			return 0 ; 
		
		
		DualControlEnabledData<?, ?> sample = editedDatas.get(0); 
		
		
		ArrayList<Serializable> ids = new ArrayList<Serializable>(); 
		
		
		for ( DualControlEnabledData<?, ?> scn : editedDatas){
			ids.add(scn.getPrimaryKey()); 
		}
		HashMap<String, Object> upd = new HashMap<String, Object>();
		upd.put("approvalStatusCode", approvalStatus.toString()); 
		return generalPurposeDao.updateBulkDataWithHQLStatement(sample.getClass(), upd, sample.getPrimaryKeyJPAName(), ids); 
		
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		List<DualControlDefinition> defs =  generalPurposeDao.list(DualControlDefinition.class, null );
		if ( defs != null && !defs.isEmpty()){
			for ( DualControlDefinition scn : defs){
				this.indexedDualControlDefinition.put(scn.getId(), scn); 
			}
		}
		
	}

        
	/**
	 * membaca definisi dual control data based on FQCN 
	 */
	public DualControlDefinition getDualControlDefinition ( String fqcn ) {
		return indexedDualControlDefinition.get(fqcn); 
	}
	
	@Autowired
	HttpServletRequest httpServletRequest  ; 
	
	
	
	
	
	/**
	 * handler upload bulk upload
	 * @param uploadedDataKey key untuk file upload. ini unutk mencari file dalam session
	 * @param targetClassFQCN fqcn dari item yang di handle
	 * @param remark catatan dari data
	 *  
	 */
	@Transactional(readOnly=false)
	public  HeaderDataOnlyCommonDualControlContainerTable handleUploadedMasterFile(
			  String  uploadedDataKey,
			   String targetClassFQCN ,
			  String remark 
			 ) throws InvalidExcelFileException , Exception {
		
		String fileAbsPath = (String) httpServletRequest.getSession().getAttribute(uploadedDataKey);
		String fileName =  (String) httpServletRequest.getSession().getAttribute(uploadedDataKey + "__FILENAME");
		ISpreadsheetFileUtil<DualControlEnabledData<?, ?>> handler = (ISpreadsheetFileUtil<DualControlEnabledData<?, ?>>) spreadsheetFileUtilManager.getHandler( targetClassFQCN );
			
		HSSFWorkbook workBook =isValidExcelFile(fileAbsPath); 
		if ( workBook== null)
			throw new InvalidExcelFileException("gagal membaca data file upload :" + fileName, "unknown"); 
			
		List<DualControlEnabledData<?,?>> uploadedData  =  (List<DualControlEnabledData<?,?>>)handler.readData(workBook.getSheetAt(0), 2);
		HeaderDataOnlyCommonDualControlContainerTable hsl = swapWorkerUpload(uploadedData, handler, fileName, remark );
		return hsl ; 
		
	}
	/**
	 * Validasi tipe data excel 
	 * 
	 * @return
	 * @throws Exception
	 */
	public HSSFWorkbook isValidExcelFile(String filePath) {
		
		try {
			
			HSSFWorkbook xlsWorkbook = new HSSFWorkbook(new FileInputStream(new File(filePath)));
			return xlsWorkbook ; 
		} catch (Exception e) {
			logger.error("Gagal membaca file " + filePath + " , error :  " + e.getMessage(), e);
			return null ; 
		}
		
	}
	
	
	
	
	/**
	 * pindah worker untuk proses submit for approval , ini di pergunakan agar proses exception handling bisa seragam
	 */
	protected HeaderDataOnlyCommonDualControlContainerTable swapWorkerUpload (List<DualControlEnabledData<?,?>> uploadedData , ISpreadsheetFileUtil<DualControlEnabledData<?, ?>> handler ,  
			String uploadedFileName , String remark 
			) throws Exception{
		if ( uploadedData== null ||  uploadedData.isEmpty())
			return null; 
		
		for ( DualControlEnabledData<?, ?> scn : uploadedData){
				System.out.println(scn.toString());
		}
			
		 
		UploadedDataContainer<DualControlEnabledData<?,?>> container =  handler.validateDataAndSplitData(  uploadedData);
		if ( container.getUpladedUpdatedExistingData()!= null )
			applyDualControlOperationFlag(container);
		 
		return submitBulkDataForApproval(container, uploadedFileName, remark, handler.getHandledClass().getName()); 
	}
	
	
	private void applyDualControlOperationFlag (UploadedDataContainer<DualControlEnabledData<?,?>> container ) {
		if ( container.getUpladedUpdatedExistingData()!= null && !container.getUpladedUpdatedExistingData().isEmpty()) {
			for ( DualControlEnabledData<?,?> scn :container.getUpladedUpdatedExistingData() ){
				scn.setApprovalStatus(DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE);
			}
		
		}
		if ( container.getUploadedNewData()  != null && !container.getUploadedNewData().isEmpty()){
			for ( DualControlEnabledData<?,?> scn :container.getUploadedNewData() ){
				scn.setApprovalStatus(DualControlApprovalStatusCode.WAITING_APPROVE_CREATE);
			}
		}
		
	}
         
	
	
	@Override
	public void registerBulkDataBinder(
			ICustomBulkDataAdditionalPropertyBinder<?> binder) {
		additionalDataBinders.put(binder.getHandledClass().getName(), binder); 
		
	}

	@Override
	@Transactional(readOnly=false)
	public void rejectAllWaitingApprovalData(Date latestDateToFetch,
			String remarkForAllRejected) throws Exception {
		
		SimpleQueryFilter inFilter = new SimpleQueryFilter(); 
		inFilter.setField("approvalStatus");
		inFilter.setFilter(new String[]{
				DualControlApprovalStatusCode.WAITING_APPROVE_CREATE.toString() , 
				DualControlApprovalStatusCode.WAITING_APPROVE_DELETE.toString() ,
				DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.toString()  , 
				DualControlApprovalStatusCode.WAITING_APPROVE_BULK.toString()  ,
		});
		inFilter.setOperator(SimpleQueryFilterOperator.fieldIn);
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(latestDateToFetch);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND	, 59);
		
		SimpleQueryFilter waktu =  new SimpleQueryFilter("createdTime" , SimpleQueryFilterOperator.lessEqual , cal.getTime());
		
		
		SimpleQueryFilter[] filterTungal = new SimpleQueryFilter[]{
				inFilter , 
				new SimpleQueryFilter("singleLineDataTypeFlag" , SimpleQueryFilterOperator.equal , "Y") , 
				waktu
		}; 
		
		
		SimpleQueryFilter[] filterMultiple = new SimpleQueryFilter[]{
				inFilter , 
				new SimpleQueryFilter("singleLineDataTypeFlag" , SimpleQueryFilterOperator.equal , "N") , 
				waktu
		};
		
		List<CommonDualControlContainerTable> singleContainers = generalPurposeDao.list(CommonDualControlContainerTable.class, filterTungal, null); 
		if ( singleContainers!= null && !singleContainers.isEmpty()){
			for ( CommonDualControlContainerTable scn : singleContainers){
				rejectData(scn.getId(), remarkForAllRejected);
			}
		}
		//
		List<CommonDualControlContainerTable> multipleContainers = generalPurposeDao.list(CommonDualControlContainerTable.class, filterMultiple, null); 
		if ( multipleContainers!= null && !multipleContainers.isEmpty()){
			for ( CommonDualControlContainerTable scn : multipleContainers){
				rejectBulkData(scn.getId(), remarkForAllRejected);
			}
		}
		
	}

	@Override
	public boolean haveWaitingApprovalData(String fqcn) {
		SimpleQueryFilter sttsCodeFlt = new SimpleQueryFilter(); 
		sttsCodeFlt.setField("approvalStatus");
		sttsCodeFlt.setFilter( new String[]{
				DualControlApprovalStatusCode.WAITING_APPROVE_CREATE.toString() , 
				DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.toString() ,
				DualControlApprovalStatusCode.WAITING_APPROVE_DELETE.toString() ,
		});
		sttsCodeFlt.setOperator(SimpleQueryFilterOperator.fieldIn);
		SimpleQueryFilter [] flt = new SimpleQueryFilter[]{
			new SimpleQueryFilter("targetObjectFQCN", SimpleQueryFilterOperator.equal , fqcn) , 
			sttsCodeFlt
		}; 
		Long cnt =  generalPurposeDao.count(CommonDualControlContainerTable.class, flt);
		return cnt!= null && cnt.longValue() > 0 ;
	}
	
	
	
	
	
	
	@Override
	public CommonDualControlContainerTable getLatestWaitingApprovalData(
			String fqcn) throws Exception{
		SimpleQueryFilter sttsCodeFlt = new SimpleQueryFilter(); 
		sttsCodeFlt.setField("approvalStatus");
		sttsCodeFlt.setFilter( new String[]{
				DualControlApprovalStatusCode.WAITING_APPROVE_CREATE.toString() , 
				DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE.toString() ,
				DualControlApprovalStatusCode.WAITING_APPROVE_DELETE.toString() ,
		});
		sttsCodeFlt.setOperator(SimpleQueryFilterOperator.fieldIn);
		SimpleQueryFilter [] flt = new SimpleQueryFilter[]{
			new SimpleQueryFilter("targetObjectFQCN", SimpleQueryFilterOperator.equal , fqcn) , 
			sttsCodeFlt
		}; 
		List<CommonDualControlContainerTable> rs =generalPurposeDao.list(CommonDualControlContainerTable.class, flt , SORT_DUAL_CONTROL , 1 , 0);  
		if ( rs== null || rs.isEmpty())
			return null ; 
		
		return  rs.get(0); 
	}

	@Override
	@SuppressWarnings(value={  "unchecked" , "rawtypes"})
	public <D extends SystemParamDrivenClass<?, ?>> AppConfigurationDrivenDetaiResultHolder<D> getSytemConfigurationDrivenData(
			String fqcn) throws Exception {
		Class<D> cls = (Class<D>) Class.forName(fqcn);
		AppConfigurationDrivenDetaiResultHolder<D> retval = new AppConfigurationDrivenDetaiResultHolder<D>();
		SystemParamDrivenClass cnf =  commonSystemService.loadConfiguration(cls); 
		retval.setConfigurationData((D) cnf);
		retval.setLatestWaitingApprovalData(getLatestWaitingApprovalData(fqcn));
		return retval;
	}
}
