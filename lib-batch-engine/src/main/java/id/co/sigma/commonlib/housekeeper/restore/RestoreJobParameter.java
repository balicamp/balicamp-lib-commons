package id.co.sigma.commonlib.housekeeper.restore;

import org.springframework.batch.core.JobParameters;

import id.co.sigma.commonlib.base.BaseSpringBatchJobParameterWrapper;
import id.co.sigma.commonlib.base.serializer.SendToParameterField;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class RestoreJobParameter extends BaseSpringBatchJobParameterWrapper{
	
	
	
	/**
	 * key untuk path absolute file yang di extract
	 **/
	public static final String ZIPPED_FILE_ABS_PATH="zippedFilePath" ; 
	
	
	
	
	/**
	 * path absolute , kemana file akan di extract
	 **/
	public static final String TARGET_FOLDER_ABS_PATH="extractToFolderPath" ;
	
	
	
	
	
	/**
	 * file yang perlu di extract
	 **/
	@SendToParameterField(key=ZIPPED_FILE_ABS_PATH)
	private String zippedFileAbsolutePath ;
	
	
	
	
	
	/**
	 * kemana file di extract
	 **/
	@SendToParameterField(key=TARGET_FOLDER_ABS_PATH)
	private String targetFolderAbsPath ;
	
	
	
	public RestoreJobParameter(){super();}
	public RestoreJobParameter(JobParameters params) throws Exception{
		super(params); 
	}
	
	/**
	 * file yang perlu di extract
	 **/
	public void setZippedFileAbsolutePath(String zippedFileAbsolutePath) {
		this.zippedFileAbsolutePath = zippedFileAbsolutePath;
	}
	
	/**
	 * file yang perlu di extract
	 **/
	public String getZippedFileAbsolutePath() {
		return zippedFileAbsolutePath;
	}
	
	/**
	 * kemana file di extract
	 **/
	public void setTargetFolderAbsPath(String targetFolderAbsPath) {
		this.targetFolderAbsPath = targetFolderAbsPath;
	}
	/**
	 * kemana file di extract
	 **/
	public String getTargetFolderAbsPath() {
		return targetFolderAbsPath;
	}

}
