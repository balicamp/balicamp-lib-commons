package id.co.sigma.commonlib.base;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.BeanUtils;

/**
 * wrapper untuk Job Launcher
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseJobLauncherHelper<T extends BaseSpringBatchJobParameterWrapper> {
	
	private ArraySpringBatchJobParameterWrapper<T> arrayWrapper = new ArraySpringBatchJobParameterWrapper<T>() {

		@SuppressWarnings("unchecked")
		@Override
		public T instantiateBlankObject() {
			Class<?> cls = getParameterTypeClass(); 
			try {
				return (T) BeanUtils.instantiate(cls); 
			} catch (Exception e) {
				throw new RuntimeException(e) ; 
			}
			
		}
		
	};
	
	/**
	 * launch single job
	 **/
	public JobExecution launchJob ( T parameter ) throws Exception {
		return getJobLauncher().run(getJob(), parameter.buildJobParameter());
	}
	
	
	
	/**
	 * lunch job dengan array of parameters
	 **/
	public   JobExecution launchJob (    ArraySpringBatchJobParameterWrapper<T> parameters ) throws Exception {
		
		return getJobLauncher().run(getJob(), parameters.buildJobParameter());
	}
	
	
	
	protected abstract Class<T> getParameterTypeClass () ; 
	
	
	/**
	 * job yang di pegunakan untuk launch job
	 **/
	protected abstract Job getJob (); 
	
	
	
	/**
	 * job launcher untuk proses ini
	 **/
	protected abstract JobLauncher getJobLauncher();
	
	


}
