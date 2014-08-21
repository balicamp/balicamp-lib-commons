package id.co.sigma.commonlib.journal.engine.io.reader;




import id.co.sigma.commonlib.base.BaseParameterizedJDBCItemReader;
import id.co.sigma.commonlib.journal.engine.IJournalConfigurationAware;
import id.co.sigma.commonlib.journal.engine.IJournalRuleProvider;
import id.co.sigma.commonlib.journal.engine.data.SourceJournalRawData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;




/**
 * Reader data, basis nya Spring JDBC, class ini menerima konfigurasi dari database dan bertugas membaca data sumber 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @category spring-batch:reader
 **/
public class JournalSourceDataReader extends BaseParameterizedJDBCItemReader<SourceJournalRawData> implements IJournalConfigurationAware{
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(JournalSourceDataReader.class); 
	
	
	
	private int counterJournalConfigurationSetting =1 ; 
	

	private Long journalConfigurationId ; 
	/**
	 * provider journal rule
	 **/
	@Autowired
	private IJournalRuleProvider journalRuleProvider ; 
	
	

	@Override
	public void setJournalConfigurationId(Long journalConfigurationId) {
		System.out.println("counterJournalConfigurationSetting :" + counterJournalConfigurationSetting);
		counterJournalConfigurationSetting++;
		try {
			this.journalConfigurationId = journalConfigurationId ;
			setSql(journalRuleProvider.getJournalRuleHeader(this.journalConfigurationId).getSelectStatement());
			
			
		} catch (Exception e) {
			logger.error("error set journal id dengan id {1}, error message {2}",  new Object[]{journalConfigurationId , e.getMessage()});
			e.printStackTrace(); 
		}
		
		
	}
	
	
	@Override
	protected RowMapper<SourceJournalRawData> generateRowMapper() {
		return new JournalSourceRowMapper();
	}
	

	
}
