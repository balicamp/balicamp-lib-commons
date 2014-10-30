package id.co.sigma.finance.report.domain;

/**
 * Definisi column report
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class SimpleReportDefinition {
	
	
	
	
	
	
	
	/**
	 * sql statement dari data. ini untuk di trigger oleh report engine
	 */
	private String sqlStatement ;
	
	
	
	/**
	 * sql statement dari data. ini untuk di trigger oleh report engine
	 */
	public void setSqlStatement(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}
	/**
	 * sql statement dari data. ini untuk di trigger oleh report engine
	 */
	public String getSqlStatement() {
		return sqlStatement;
	}
	

}
