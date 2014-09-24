package id.co.sigma.commonlib.base;

import org.springframework.batch.core.JobExecution;

/**
 * custom jobhandler
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ICustomJobExecutionListener {
	
	
	/**
	 * berapa line yang di tulis ke output
	 */
	public static String OUTPUT_WRITED_COUNT_KEY="writedResultCount";  
	
	

	
	/**
	 * di trigger pada saat proses baru akan di mulai
	 */
	public void beforeJob(JobExecution jobExecution) ; 

	
	
	/**
	 * di trigger pada saat proses slesai di lakukan
	 * @param totalDataWritedCount total data yang di tulis ke output(ini valid kalau job nya 1, bukan nested)
	 * @param jobExecution execution context
	 */
	public void afterJob( int totalDataWritedCount ,  JobExecution jobExecution)  ; 


}
