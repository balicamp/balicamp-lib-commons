package id.co.sigma.common.server.controllers.impl;

import id.co.sigma.common.data.app.DualControlApprovalStatusCode;
import id.co.sigma.common.data.app.DualControlEnabledData;
import id.co.sigma.common.data.app.HeaderDataOnlyCommonDualControlContainerTable;
import id.co.sigma.common.data.serializer.json.RPCResponseWrapper;
import id.co.sigma.common.exception.InvalidExcelFileException;
import id.co.sigma.common.exception.SimpleJSONSerializableException;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.dao.util.ServerSideParsedJSONContainer;
import id.co.sigma.common.server.service.system.DualControlDataService;
import id.co.sigma.common.server.spreadsheet.ISpreadsheetFileUtil;
import id.co.sigma.common.server.spreadsheet.ISpreadsheetFileUtilManager;
import id.co.sigma.common.server.spreadsheet.UploadedDataContainer;


import java.util.List;


import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * controller untuk helper master data. ini di pergunakan untuk upload file master
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@Controller
public class DualControlledMasterDataController {
	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DualControlledMasterDataController.class); 

	@Autowired
	private ISpreadsheetFileUtilManager spreadsheetFileUtilManager ; 
	
	
	@Autowired
	private IGeneralPurposeDao generalPurposeDao  ; 
	
	@Autowired
	DualControlDataService dualControlDataService  ; 
	
	/**
	 * Validasi tipe data excel 
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isValidExcelFile(MultipartFile excelFile) {
		boolean valid = false;
		
		try {
			HSSFWorkbook xlsWorkbook = new HSSFWorkbook(excelFile.getInputStream());
			valid = true;
		} catch (Exception e) {
			LOGGER.error("Gagal membaca file "+excelFile.getName(), e);
		}
		
		
		return valid;
	}
	
	
	




	
	
	/**
	 * ini method untuk menerima file excel file yang di upload user
	 */
	@RequestMapping(value={
			"/master-data/bulk-upload-dual-control-data.jsp"
	}, 
	produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String uploadExcelFileHandler(
			 @RequestParam(value="uploadedFile") MultipartFile uploadedFile,
			 @RequestParam(value="fqcn")  String targetClassFQCN ,
			 @RequestParam(value="remark") String remark , 
			 HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		ISpreadsheetFileUtil<DualControlEnabledData<?, ?>> handler = (ISpreadsheetFileUtil<DualControlEnabledData<?, ?>>) spreadsheetFileUtilManager.getHandler( targetClassFQCN );
		HSSFWorkbook workBook = new HSSFWorkbook(uploadedFile.getInputStream()); 
		
		
		List<DualControlEnabledData<?,?>> uploadedData  =  (List<DualControlEnabledData<?,?>>)handler.readData(workBook.getSheetAt(0), 2);  
		RPCResponseWrapper w = null ;
		
		ServerSideParsedJSONContainer c = new ServerSideParsedJSONContainer();
		
		if(!isValidExcelFile(uploadedFile)) {
				w = new RPCResponseWrapper(  new SimpleJSONSerializableException( new InvalidExcelFileException("File excel tidak valid.", 
						uploadedFile.getContentType()) ));  
		} else {
			 
			
			try {
				HeaderDataOnlyCommonDualControlContainerTable hsl = swapWorkerUpload(uploadedData, handler, uploadedFile.getOriginalFilename(), remark );
				if ( hsl != null){
					w = new RPCResponseWrapper(hsl); 
				}
			} catch (Exception e) {
				LOGGER.error("gagal memproses data upload. error : " + e.getMessage() , e );
				//if ( e instanceof SimpleJSONSerializableException)
				w = new RPCResponseWrapper(  new SimpleJSONSerializableException( e));  
			}
			
	       
		}
		
		
		 if ( w!= null){
	            
	           w.translateToJSON(c);
	           String json =  c.getJSONString() ;
	           return json ;      
	            
	        }
		
		
        
		return "{\"data\":null}" ;  
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
		 
		return dualControlDataService.submitBulkDataForApproval(container, uploadedFileName, remark, handler.getHandledClass().getName()); 
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

}
