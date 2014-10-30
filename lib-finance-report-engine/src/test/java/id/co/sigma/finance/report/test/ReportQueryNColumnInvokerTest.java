package id.co.sigma.finance.report.test;



import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import id.co.sigma.finance.report.domain.NColumnReportDefinitionGroup;
import id.co.sigma.finance.report.domain.NColumnsReportExecutionResult;
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
public class ReportQueryNColumnInvokerTest extends BaseTestUtil{
	
	
	
	
	@Autowired
	IFinanceReportEngineService  financeReportEngineService; 
	
	//@Test
	public void invokeJDBCTest () {
		try {
			List<NColumnsReportExecutionResult> hsl =  financeReportEngineService.invokeReportQuery(generateSampleParameter());
			if ( hsl== null) {
				System.out.println();
				return ; 
			}
			for ( NColumnsReportExecutionResult scn : hsl ) {
				StringBuffer amt = new StringBuffer(); 
				for ( BigDecimal am : scn.getAmounts()) {
					amt.append("[" + am +"]"); 
				}
				System.out.println("Kode :"+scn.getDataCode()+" ,label    "  +scn.getDataLabel() +",amts :" + amt.toString() );
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	@Test
	public void invokeXMLTest () {
		NColumnReportDefinitionGroup g = generateSampleParameter() ; 
		try {
			File tmp = File.createTempFile("RPT-ENGINE-BULK", ".xml"); 
			financeReportEngineService.generateReportXMLFile(g, tmp.getAbsolutePath());
			System.out.println("selesai menggerate dengan XML --> " + tmp.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	protected NColumnReportDefinitionGroup generateSampleParameter () {
		NColumnReportDefinitionGroup r = new NColumnReportDefinitionGroup(); 
		r.appendColumnDef("label" , "data_code", "ttl" , "amount1"); //setColumnNamesForCode(new String[]{"data_code" , "data_code" ,"data_code"});
		r.appendColumnDef("label" , "data_code", "ttl" ,"amount2");
		r.appendColumnDef("label" , "data_code", "ttl","amount3");
		
		r.setDetailDataRootTag("cplxdtl");
		r.setXmlPathForDataCode("sample/complex");
		r.setRootXmlPath("sample/complex");
		
		r.setXmlPathForLabel("label");
		r.setXmlPathForDataCode("code");
		
		
		String sql1=readFromClassPathAsString("sample-summary-cplx-1.sql");
		String sql2=readFromClassPathAsString("sample-summary-cplx-2.sql");
		String sql3=readFromClassPathAsString("sample-summary-cplx-3.sql");
		r.appendSql(new String[]{
				sql1 , sql2 , sql3
		});
		
		r.appendSql(new String[]{
				readFromClassPathAsString("sample-summary-cplx-1.2.sql") , readFromClassPathAsString("sample-summary-cplx-2.2.sql") , readFromClassPathAsString("sample-summary-cplx-3.2.sql")
		});
		
		return r ; 
		
		
	}
	
		
}
