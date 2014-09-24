package id.co.sigma.commonlib.journal.engine;

import id.co.sigma.commonlib.journal.metadata.JournalRuleHeader;



/**
 * ada batasan spt ini dalam spring batch : 
 * <ol>
 * <li>Spring batch bekerja dengan model proses asynchronous, jadinya agak sulit kalaukita mengeset variable ke dalam <i>spring bean</i> yang menangani proses  </li>
 * <li></li>
 * <li></li>
 * </ol>
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface IJournalRuleProvider {
	
	
	
	
	
	/**
	 * membaca konfigurasi 
	 **/
	public JournalRuleHeader getJournalRuleHeader(Long configurationId); 

}
