package id.co.sigma.finance.report.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import id.co.sigma.finance.report.domain.BaseReportDefinitionGroup;
import id.co.sigma.finance.report.domain.SimpleReportDefinition;
import id.co.sigma.finance.report.domain.ReportExecutionResult;
import id.co.sigma.finance.report.domain.SimpleReportDefinitionGroup;
import id.co.sigma.finance.report.service.IFinanceReportEngineService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */

@ContextConfiguration(locations={
		"classpath*:META-INF/spring/appContext-datasourcetest-finreport-context.xml" ,  
		"classpath*:META-INF/spring/finance-report-context.xml"
		})
@RunWith(value=SpringJUnit4ClassRunner.class)
public class ReportQueryInvokerTest extends BaseTestUtil{
	
	
	
	
	@Autowired
	IFinanceReportEngineService  financeReportEngineService; 
	@Test
	public void sample () {
		System.out.println("OK saya siap");
	}

	
	//@Test
	public void runJDBCTools () {
		SimpleReportDefinitionGroup g = generateSampleDefinition() ; 
		try {
			List<ReportExecutionResult> res =  financeReportEngineService.invokeReportQuery(g);
			for (ReportExecutionResult scn : res ){
				System.out.println(scn.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void runMakeXMLTools () {
		SimpleReportDefinitionGroup g = generateSampleDefinition() ;
		try {
			File tmp = File.createTempFile("RPT-ENGINE", ".xml"); 
			financeReportEngineService.generateReportXMLFile(g, tmp.getAbsolutePath());
			System.out.println("selesai menggerate dengan XML --> " + tmp.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	
	protected SimpleReportDefinitionGroup generateSampleDefinition () {
		SimpleReportDefinitionGroup g = new SimpleReportDefinitionGroup(); 
		g.setRootXmlPath("report/sample/dummy");
		SimpleReportDefinition d1 = new SimpleReportDefinition(); 
		SimpleReportDefinition d2 = new SimpleReportDefinition() ; 
		d1.setSqlStatement(readFromClassPathAsString("sample-summary-1.sql"));
		d2.setSqlStatement(readFromClassPathAsString("sample-summary-2.sql"));
		g.setReportDefinitions(new ArrayList<SimpleReportDefinition>());
		g.getReportDefinitions().add(d1) ; 
		g.getReportDefinitions().add(d2) ; 
		
		
		g.setXmlPathForAmount("nilai");
		g.setXmlPathForLabel("DataLabel");
		g.setXmlPathForDataCode("DataCode");
		g.setDetailDataRootTag("data");
		
		g.setColumnNameForAmount("ttl");
		g.setColumnNameForCode("data_code");
		g.setColumnNameForLabel("label");
		return g ; 
	}
		
}
