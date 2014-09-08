package id.co.sigma.commonlib.base;

import org.springframework.batch.core.JobExecution;

/**
 * task yang akan di eksekusi kalau pekerjaan selesai di lakukan. ini di perlukan kalau kita memerlukan custom task .
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IJobCompleteHandler {
	
	
	
	/**
	 * di trigger kalau Job selesai di lakukan. yang di kirimkan adalah id dari Job yang selesai di lakukan
	 * 
	 */
	public void afterJob(JobExecution jobExecution); 

}
