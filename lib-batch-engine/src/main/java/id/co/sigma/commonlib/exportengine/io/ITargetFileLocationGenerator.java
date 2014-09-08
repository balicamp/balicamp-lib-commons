package id.co.sigma.commonlib.exportengine.io;

import java.util.Map;

import id.co.sigma.commonlib.exportengine.data.ExportToTextFileMetadata;

import org.springframework.core.io.Resource;



/**
 * interface bean untuk generate target kemana file akan di tulis
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface ITargetFileLocationGenerator {
	
	
	
	/**
	 * parameter dalam proses start job. di sini bean implementer harus membuat resource kemana file output akan di taruh. anda bisa mempergunakan metadata dan jobstartparameter
	 * @param metaData metadata output
	 * @param jobStartParameter job start parameter
	 **/
	public Resource generateTargetForWritingOutput (ExportToTextFileMetadata metaData ,  Map<String, Object> jobStartParameter) ; 

}
