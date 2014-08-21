package id.co.sigma.commonlib.housekeeper;


import java.util.ArrayList;

import id.co.sigma.commonlib.base.ArraySpringBatchJobParameterWrapper;
import id.co.sigma.commonlib.base.serializer.SendToParameterField;

/**
 *
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class HouseKeeperJobParameters extends ArraySpringBatchJobParameterWrapper<HouseKeeperJobParameter>{
	
	
	/**
	 * folder temporary untuk output. di amana file .json akan di taruh
	 **/
	@SendToParameterField(key=HouseKeepingConstant.OUTPUT_FILE_DIRECTORY_PATH)
	private String temporaryOutputFolder ; 
	
	/**
	 * nama bean handler setelah job selesai di lakukan. anda perlu memasukan id dari bean dengan tipe : {@link id.co.sigma.commonlib.base.IJobCompleteHandler}
	 */
	@SendToParameterField(key=HouseKeepingConstant.AFTER_JOB_HANDLER_BEAN)
	private String houseKeeperAfterJobHandlerBeanName ; 
	
	
	/**
	 * path file dalam posisi ke zip, di mana akan di taruh
	 **/
	@SendToParameterField(key=HouseKeepingConstant.OUTPUT_FILE_ABSOULTE_PATH)
	private String finalCompressedFilePath ; 
	
	
	public HouseKeeperJobParameters(){
		super(); 
		setAfterRepeatedStepDoneExecutedStepId(HouseKeepingConstant.ARCHIVE_TO_COMPRESED_FILE_STEP);
		setRepeatedStepId(HouseKeepingConstant.REPETAED_EXTRACT_SQL_STATEMENT_STEP);
	}
	
	
	
	/**
	 * 
	 **/
	public HouseKeeperJobParameters(ArrayList<HouseKeeperJobParameter> tableParams ){
		this() ; 
		setSubParameters(tableParams);
		
	}

	@Override
	public HouseKeeperJobParameter instantiateBlankObject() {
		return new HouseKeeperJobParameter();
	}
	/**
	 * folder temporary untuk output. di amana file .json akan di taruh
	 **/
	public String getTemporaryOutputFolder() {
		return temporaryOutputFolder;
	}
	/**
	 * folder temporary untuk output. di amana file .json akan di taruh
	 **/
	public void setTemporaryOutputFolder(String temporaryOutputFolder) {
		this.temporaryOutputFolder = temporaryOutputFolder;
	}
	/**
	 * path file dalam posisi ke zip, di mana akan di taruh
	 **/
	public String getFinalCompressedFilePath() {
		return finalCompressedFilePath;
	}
	/**
	 * path file dalam posisi ke zip, di mana akan di taruh
	 **/
	public void setFinalCompressedFilePath(String finalCompressedFilePath) {
		this.finalCompressedFilePath = finalCompressedFilePath;
	}



	/**
	 * nama bean handler setelah job selesai di lakukan. anda perlu memasukan id dari bean dengan tipe : {@link id.co.sigma.commonlib.base.IJobCompleteHandler}
	 */
	public String getHouseKeeperAfterJobHandlerBeanName() {
		return houseKeeperAfterJobHandlerBeanName;
	}



	/**
	 * nama bean handler setelah job selesai di lakukan. anda perlu memasukan id dari bean dengan tipe : {@link id.co.sigma.commonlib.base.IJobCompleteHandler}
	 */
	public void setHouseKeeperAfterJobHandlerBeanName(
			String houseKeeperAfterJobHandlerBeanName) {
		this.houseKeeperAfterJobHandlerBeanName = houseKeeperAfterJobHandlerBeanName;
	}
	
	

}
