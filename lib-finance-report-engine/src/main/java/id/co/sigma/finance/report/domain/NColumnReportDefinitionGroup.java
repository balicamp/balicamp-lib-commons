package id.co.sigma.finance.report.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class NColumnReportDefinitionGroup extends BaseReportDefinitionGroup{

	
	
	/**
	 * nama column untuk amount . ini sejumlah paramter yang di pergunakan 
	 */
	private String[] columnNamesForAmount;
	/**
	 * nama column unutk label
	 */
	private String[] columnNamesForLabel;
	/**
	 * nama column[] untuk kode data.
	 */
	private String[] columnNamesForCode; 
	
	
	
	
	/**
	 * helper, berapa total column yang ada dalam report
	 */
	private int numberOfColumn = 0 ; 
	
	
	
	
	
	
	/**
	 * nama XML tags untuk amount
	 */
	private String[] xmlTagNameForAmounts ; 
	
	
	
	
	
	
	/**
	 * sql statements untuk data. jumlah dalam array dimensinya sama dalam 1 group
	 * jadinya misalnya ada 3 column report : data tahun lalu, data RKAP , data bulan skr, maka dalam 1 row terisi 3 sql statement
	 */
	private List<String[]> sqlStatements = new ArrayList<String[]>() ;
	
	
	public void appendSql (String[] sqlStatment ) {
		if ( sqlStatment == null || sqlStatment.length!= numberOfColumn) {
			throw new RuntimeException("total column yang terdefine dalam defisi ini harus anda masukan sebelum di tambahkan query. jumlah query yang di kirimkan harus sama jumlah column yang di pergunakan"); 
		}
		this.sqlStatements.add(sqlStatment); 
		 
	}
	
	
	/**
	 * sql statements untuk data. jumlah dalam array dimensinya sama dalam 1 group
	 * jadinya misalnya ada 3 column report : data tahun lalu, data RKAP , data bulan skr, maka dalam 1 row terisi 3 sql statement
	 */
	public List<String[]> getSqlStatements() {
		return sqlStatements;
	}
	
	/**
	 * nama column untuk amount . ini sejumlah paramter yang di pergunakan 
	 */
	public String[] getColumnNamesForAmount() {
		return columnNamesForAmount;
	}
	
	/**
	 * nama column unutk label
	 */
	public String[] getColumnNamesForLabel() {
		return columnNamesForLabel;
	}
	
	
	
	
	
	/**
	 * helper, berapa total column yang ada dalam report
	 */
	public int getNumberOfColumn() {
		return numberOfColumn;
	}
	
	
	
	
	/**
	 * generate sql statement untuk data. data di generate per index yang di minta
	 */
	public String generateSql ( int index ) {
		StringBuffer bfr = new StringBuffer(); 
		for ( String[] scn : sqlStatements) {
			if ( bfr.length()>0)
				bfr.append("union all "); 
			bfr.append(scn[index]); 
		}
		return bfr.toString(); 
	}
	
	public void appendColumnDef (String colummnNameForLabel , String columnNameForCode , String columnNameForAmount, String xmlTagForAmount ) {
		this.columnNamesForAmount =  expandAndCopyContent(this.columnNamesForAmount);
		this.columnNamesForAmount[columnNamesForAmount.length-1] = columnNameForAmount ; 
		this.columnNamesForCode = expandAndCopyContent(columnNamesForCode) ; 
		this.columnNamesForCode[columnNamesForCode.length-1] = columnNameForCode ; 
		this.columnNamesForLabel = expandAndCopyContent(columnNamesForLabel); 
		this.columnNamesForLabel [this.columnNamesForLabel.length-1] = colummnNameForLabel;
		this.xmlTagNameForAmounts = expandAndCopyContent(xmlTagNameForAmounts); 
		this.xmlTagNameForAmounts[this.xmlTagNameForAmounts.length-1] = xmlTagForAmount ; 
		this.numberOfColumn +=1 ; 
	}
	
	
	
	
	private String [] expandAndCopyContent (String[] arr ) {
		if ( arr== null || arr.length==0){
			return new String[1]; 
		}
		else {
			String [] rtvl = new String[arr.length+1] ;
			for ( int i =0 ; i< arr.length ; i++) {
				rtvl[i] = arr[i] ;
			}
			return rtvl ; 
		}
	}
	
	
	
	
	/**
	 * nama column[] untuk kode data.
	 */
	public String[] getColumnNamesForCode() {
		return columnNamesForCode;
	}
	
	
	/**
	 * nama XML tags untuk amount
	 */
	public String[] getXmlTagNameForAmounts() {
		return xmlTagNameForAmounts;
	}
}
