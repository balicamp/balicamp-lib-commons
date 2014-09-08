package id.co.sigma.commonlib.exportengine;

import id.co.sigma.commonlib.exportengine.io.SingleExportJobParameter;

import org.springframework.batch.core.JobExecution;

/**
 *interface service untuk export job
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IExportJobLauncher {
	
	
	/**
	 *  meluncurkan export 1 table ke 1 file
	 */
	public JobExecution launchSimpleExport ( SingleExportJobParameter parameter) throws Exception; 

}
