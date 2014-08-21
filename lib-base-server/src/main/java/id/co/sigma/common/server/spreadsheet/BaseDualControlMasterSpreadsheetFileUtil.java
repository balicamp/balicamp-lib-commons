package id.co.sigma.common.server.spreadsheet;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import id.co.sigma.common.data.app.DualControlApprovalStatusCode;
import id.co.sigma.common.data.app.SimpleDualControlData;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;

/**
 * class ini dedicated untuk handle dual control data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseDualControlMasterSpreadsheetFileUtil<DATA extends SimpleDualControlData<?>> extends BaseSpreadsheetFileUtil<DATA> {

	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseDualControlMasterSpreadsheetFileUtil.class); 
	@Autowired
	protected IGeneralPurposeDao generalPurposeDao ;
	
	
	
	
	public BaseDualControlMasterSpreadsheetFileUtil(){
		super(); 
	}
	
	public BaseDualControlMasterSpreadsheetFileUtil (HSSFSheet targetSheet ){
		super(targetSheet); 	
	}
	
	
	
	@Override
	public List<DATA> readData() {
		List<DATA> swap = super.readData() ;
		checkReadedSpreadsheetDataToExistingDBData(swap);
		return swap;
	}
	
	/**
	 * method ini di pergunakan untuk membind data hasil dari upload dengan existing data. tujuan nya agar kalau data sudah ada dalam existing data, bisa di kategorikan sebagai update, atau add new. ini memanfaatkan business key dari masing-masing data
	 */
	protected void checkReadedSpreadsheetDataToExistingDBData (List<DATA> readedData ) {
		if ( readedData== null || readedData.isEmpty())
			return ;
		List<DATA> dbData = getExistingDataOnDB(readedData); 
		String usr = getCurrentUserName(); 
		String ipAddr = getCurrentUserIpAddress() ; 
		if ( dbData== null){
			for ( DATA scn : readedData){
				
				scn.setApprovalStatus(DualControlApprovalStatusCode.WAITING_APPROVE_CREATE);
				scn.setActiveFlag("A");
				scn.setCreatedBy(usr);
				scn.setCreatedOn(new Date());
				scn.setCreatorIPAddress(ipAddr);
			}
		}else {
			for ( DATA scn : readedData){
				DATA matchData = null ; 
				for ( DATA scnDB : dbData){
					if ( isSameDataAccordingToBusiness(scn, scnDB)){
						matchData = scnDB ;
						scn.setApprovalStatus(DualControlApprovalStatusCode.WAITING_APPROVE_UPDATE);
						scn.setModifiedBy(usr);
						scn.setModifiedOn(new Date());
						scn.setModifiedByIPAddress(ipAddr);
						break ; 
					}
				}
				if ( matchData!= null){
					dbData.remove(matchData) ; 
					continue ; 
				}
				scn.setApprovalStatus(DualControlApprovalStatusCode.WAITING_APPROVE_CREATE);
				scn.setActiveFlag("A");
				scn.setCreatedBy(usr);
				scn.setCreatedOn(new Date());
				scn.setCreatorIPAddress(ipAddr);
				
			}
		}
		
	}
	
	
	
	/**
	 * mengecek apakah object secara bisnis ama atau tidak
	 */
	protected abstract boolean isSameDataAccordingToBusiness( DATA uploadedData , DATA dbData  ) ; 
	
	
	
	/**
	 * method ini untuk mencari data yang existing dalam database
	 */
	protected    List<DATA> getExistingDataOnDB ( List<DATA> uploadedNewData ) {
		if ( uploadedNewData== null || uploadedNewData.isEmpty())
			return null ; 
		ArrayList<Long > ids = new ArrayList<Long>() ;
		for ( DATA scn : uploadedNewData){
			ids.add(scn.getPrimaryKey());
		}
		try {
			return  generalPurposeDao.loadDataByGenericKeys(getHandledClass(), uploadedNewData.get(0).getPrimaryKeyJPAName(), ids);
		} catch (Exception e) {
			LOGGER.error("gagal membaca data existing. error di laporkan : " + e.getMessage() , e);
			return null ; 
		}
	}
	
	@Override
	protected boolean isNewlyAddedData(DATA uploadedData) {
		return uploadedData.getPrimaryKey()== null ;
	}
	
	
	
	
}
