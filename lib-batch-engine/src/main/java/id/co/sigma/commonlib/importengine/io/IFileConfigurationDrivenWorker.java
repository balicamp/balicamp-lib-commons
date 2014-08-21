package id.co.sigma.commonlib.importengine.io;




/**
 * interface bean worker . interface ini seragam menerima parameter <i>fileConfigurationId</i>
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface IFileConfigurationDrivenWorker {
	/**
	 * id file configuration. ini untuk membaca definisi batch
	 * @param fileConfigurationId id dari file konfigurasi 
	 **/
	public void setFileConfigurationId(String fileConfigurationId); 

}
