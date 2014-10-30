package id.co.sigma.finance.report.service;


import id.co.sigma.finance.report.domain.NColumnReportDefinitionGroup;
import id.co.sigma.finance.report.domain.NColumnsReportExecutionResult;
import id.co.sigma.finance.report.domain.ReportExecutionResult;
import id.co.sigma.finance.report.domain.SimpleReportDefinitionGroup;

import java.util.List;

/**
 * engine report finance
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public interface IFinanceReportEngineService {

	
	
	
	/**
	 * Trigger report query. query di sini asumsi nya sudah dalam bentuk string. tidak ada paramter yang di bainding atau lain nya. 
	 * proses binding parameter di lakukan oleh pemanggilan sebelumnya
	 * 
	 */
	public List<ReportExecutionResult> invokeReportQuery(
			SimpleReportDefinitionGroup definition) throws Exception;  
	
	
	
	
	/**
	 * invoke data dengan hasil multiple column
	 */
	public List<NColumnsReportExecutionResult> invokeReportQuery (NColumnReportDefinitionGroup definition)  throws Exception ;  
	
	
	
	public void generateReportXMLFile (SimpleReportDefinitionGroup definition, String fileAbsolutePath) throws Exception  ;
	
	
	
	
	
	/**
	 * membuat report dengan group of definition
	 */
	public void generateReportXMLFile (NColumnReportDefinitionGroup definition, String fileAbsolutePath) throws Exception  ;
	
	
	
	
}
