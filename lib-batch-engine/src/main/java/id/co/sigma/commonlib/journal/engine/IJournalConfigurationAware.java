package id.co.sigma.commonlib.journal.engine;



/**
 * interface untuk semua object yang menerima perbuahan journal configuration
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface IJournalConfigurationAware {
	
	/**
	 * assign journal configuration. ini di inject dalam scope step oleh spring bath
	 * @param journalConfigurationId id dari journal configuration yang akan di assign 
	 *  
	 **/
	public void setJournalConfigurationId(Long journalConfigurationId); 

}
