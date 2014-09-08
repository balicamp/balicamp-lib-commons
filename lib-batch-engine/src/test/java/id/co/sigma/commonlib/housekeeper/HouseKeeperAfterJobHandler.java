package id.co.sigma.commonlib.housekeeper;

import org.springframework.batch.core.JobExecution;

import id.co.sigma.commonlib.base.IJobCompleteHandler;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class HouseKeeperAfterJobHandler implements IJobCompleteHandler{

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("Hore job id :" + jobExecution.getId() + ",end time : " + jobExecution.getEndTime());
		
	}

	

}
