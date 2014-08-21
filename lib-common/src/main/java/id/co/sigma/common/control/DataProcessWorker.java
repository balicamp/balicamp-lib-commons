package id.co.sigma.common.control;



/**
 * interface untuk run task tertentu dengan argument data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @since 24-sept-2012
 * @version $Id
 **/
public interface DataProcessWorker<DATA> {

	
	/**
	 * worker untuk memproses data
	 **/
	public void runProccess (DATA data); 
}
