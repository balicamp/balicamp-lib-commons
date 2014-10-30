package id.co.sigma.finance.report.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import id.co.sigma.finance.report.domain.NColumnReportDefinitionGroup;
import id.co.sigma.finance.report.domain.NColumnsReportExecutionResult;
import id.co.sigma.finance.report.domain.SimpleReportDefinition;
import id.co.sigma.finance.report.domain.ReportExecutionResult;
import id.co.sigma.finance.report.domain.SimpleReportDefinitionGroup;
import id.co.sigma.finance.report.service.IFinanceReportEngineService;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class FinanceReportEngineServiceImpl implements IFinanceReportEngineService{
	
	/**
	 * data source untuk eksekusi
	 */
	private DataSource dataSource ;
	
	
	
	DecimalFormat decimalFormatter = new DecimalFormat("####.00"); 
	
	
	
	
	public static void main(String[] args) {
		String swap =  "a/b/c/d"; 
		String[] arr = swap.split("/"); 
		for ( String scn  : arr) {
			System.out.println(scn);
		}
	}
	

	@Override
	public List<ReportExecutionResult> invokeReportQuery(
			SimpleReportDefinitionGroup definition) throws Exception{
		if ( definition.getReportDefinitions() == null || definition.getReportDefinitions().isEmpty())
			return null ; 
		
		StringBuffer allSql = new StringBuffer(); 
		for ( SimpleReportDefinition scn :definition.getReportDefinitions() ){
			if ( allSql.length()>0)
				allSql.append(" union all ") ; 
			allSql.append(scn.getSqlStatement()); 
		}
		
		
		return invodeQuery(allSql.toString(), definition.getColumnNameForAmount(), definition.getColumnNameForCode() , definition.getColumnNameForLabel());
	}
	
	@Override
	public List<NColumnsReportExecutionResult> invokeReportQuery(
			NColumnReportDefinitionGroup definition) throws Exception {
		if ( definition.getSqlStatements() == null || definition.getSqlStatements().isEmpty())
			return null ;  
		Map<String, NColumnsReportExecutionResult> indexededParam = new HashMap<String, NColumnsReportExecutionResult>() ; 
		ArrayList<NColumnsReportExecutionResult> retval = new ArrayList<>(); 
		for ( int i = 0 ; i< definition.getNumberOfColumn() ; i++) {
			
			
			List<ReportExecutionResult> swaps = invodeQuery(definition.generateSql(i), definition.getColumnNamesForAmount()[i] , definition.getColumnNamesForCode()[i] , definition.getColumnNamesForLabel()[i]) ;
			procesNColumnData(i, definition.getNumberOfColumn(), swaps, indexededParam, retval);
		}
		
		return retval;
	}
	
	
	
	
	private void procesNColumnData (int dataIndex , int totalNumberOfAllColumn  , 
			List<ReportExecutionResult> invokeResults  ,
			Map<String, NColumnsReportExecutionResult> indexededResult , ArrayList<NColumnsReportExecutionResult> plainContainer) {
		if (invokeResults== null || invokeResults.isEmpty()  )
			return ; 
		for (ReportExecutionResult scn : invokeResults  ) {
			if ( !indexededResult.containsKey(scn.getDataCode())){
				NColumnsReportExecutionResult baru = new NColumnsReportExecutionResult(); 
				indexededResult.put(scn.getDataCode(), baru); 
				plainContainer.add(baru);
				baru.setDataCode(scn.getDataCode());
				baru.setDataLabel(scn.getDataLabel());
				baru.setAmounts(new BigDecimal[totalNumberOfAllColumn]);
			}
			NColumnsReportExecutionResult d = indexededResult.get(scn.getDataCode());
			BigDecimal[] ar=  d.getAmounts() ; 
			ar[dataIndex] = scn.getAmount(); 
		}
	}
	

	
	@Override
	public void generateReportXMLFile(SimpleReportDefinitionGroup definition,
			String fileAbsolutePath) throws Exception {
		List<ReportExecutionResult> rslt = invokeReportQuery(definition);
		
		
		
		
	
		
		
		
		
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		Element rootElement = null ; 
		String rootPath =  definition.getRootXmlPath();
		
		
		for ( String scn : rootPath.split("/")) {
			Element current  = doc.createElement(scn);
			if ( rootElement== null){
				doc.appendChild(current);
			}else {
				rootElement.appendChild(current);
			}
			
			
			rootElement = current ; 
		}
		
		
		 
		generateXML(doc, rootElement,definition.getDetailDataRootTag() ,  definition.getXmlPathForLabel(), definition.getXmlPathForAmount()  ,  definition.getXmlPathForDataCode() , rslt);
		generateXMLFile(doc, fileAbsolutePath);
	}
	
	
	@Override
	public void generateReportXMLFile(NColumnReportDefinitionGroup definition,
			String fileAbsolutePath) throws Exception {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		Element rootElement = null ; 
		String rootPath =  definition.getRootXmlPath();
		
		List<NColumnsReportExecutionResult> rslt = invokeReportQuery(definition); 
		for ( String scn : rootPath.split("/")) {
			Element current  = doc.createElement(scn);
			if ( rootElement== null){
				doc.appendChild(current);
			}else {
				rootElement.appendChild(current);
			}
			
			
			rootElement = current ; 
		}
		
		generateXML(doc, rootElement,definition.getDetailDataRootTag() ,  definition.getXmlPathForLabel(),  definition.getXmlPathForDataCode() , definition.getXmlTagNameForAmounts()  , rslt);
		
		generateXMLFile(doc, fileAbsolutePath);
	}
	
	/**
	 * worker untuk execute 
	 */
	protected List<ReportExecutionResult> invodeQuery ( String sqlStatement ,final String amountColumnName ,
			final String dataCodeColumnName   , final String dataLabelColumnName) throws Exception{
		JdbcTemplate tmpl = new JdbcTemplate(dataSource); 
		return tmpl.query(sqlStatement, new RowMapper<ReportExecutionResult>(){
			@Override
			public ReportExecutionResult mapRow(ResultSet rs, int rowIndex)
					throws SQLException {
				ReportExecutionResult rtval = new ReportExecutionResult(); 
				rtval.setAmount(rs.getBigDecimal(amountColumnName));
				rtval.setDataCode(rs.getString(dataCodeColumnName));
				rtval.setDataLabel(rs.getString(dataLabelColumnName));
				return rtval;
			}
		})  ;  
	}
	
	
	
	/**
	 * worker untuk mentransform menjadi xml file dari doc. ini setelah semua aktivitas dom selesai di lakukan
	 */
	protected void generateXMLFile ( Document doc  , String xmlAbsolute) throws Exception{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(xmlAbsolute));
		transformer.transform(source, result);
		System.out.println("File :"+xmlAbsolute+" saved!");
	}
	
	
	
	/**
	 * generate element dari root data
	 */
	private void generateXML (Document doc , Element currentDatarootElement, String detailXmlTagName , String labelTagName  , String dataCodeTagName , String[] amountTagNames  , List<NColumnsReportExecutionResult> data) {
		int maxColumn = amountTagNames.length ; 
		for ( NColumnsReportExecutionResult scn : data){
			Element dta  =  doc.createElement(detailXmlTagName);
			currentDatarootElement.appendChild(dta);
			
			
			
			Element labelWrapper = doc.createElement(labelTagName);
			//dta.setAttribute(labelTagName, scn.getDataLabel());
			labelWrapper.appendChild(doc.createTextNode(scn.getDataLabel())); 
			dta.appendChild(labelWrapper);
			
			
			//code
			Element codeWrapper = doc.createElement(dataCodeTagName);
			codeWrapper.appendChild(doc.createTextNode(scn.getDataCode())); 
			dta.appendChild(codeWrapper); 
			
			//amount
			for ( int i=0 ; i< maxColumn ; i++) {
				Element amtWrapper = doc.createElement(amountTagNames[i]); 
				BigDecimal amt = scn.getAmounts()[i] ; 
				String formatted = amt == null ? "0" : decimalFormatter.format(amt)  ;
				amtWrapper.appendChild( doc.createTextNode( formatted   )); 
				dta.appendChild(amtWrapper);	
			}
			
			 
		}
		
	}
	
	
	/**
	 * generate element dari root data
	 */
	private void generateXML (Document doc , Element currentDatarootElement , String detailXmlTagName , String labelTagName , String amountTagName , String dataCodeTagName  , List<ReportExecutionResult> data) {
		for ( ReportExecutionResult scn : data){
			Element dta  =  doc.createElement(detailXmlTagName);
			currentDatarootElement.appendChild(dta);
			
			
			
			Element labelWrapper = doc.createElement(labelTagName);
			//dta.setAttribute(labelTagName, scn.getDataLabel());
			labelWrapper.appendChild(doc.createTextNode(scn.getDataLabel())); 
			dta.appendChild(labelWrapper);
			
			
			//code
			Element codeWrapper = doc.createElement(dataCodeTagName);
			codeWrapper.appendChild(doc.createTextNode(scn.getDataCode())); 
			dta.appendChild(codeWrapper); 
			
			//amount
			Element amtWrapper = doc.createElement(amountTagName); 
			amtWrapper.appendChild( doc.createTextNode( decimalFormatter.format(scn.getAmount()))); 
			dta.appendChild(amtWrapper);
			 
		}
	}
	
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	
	

}
