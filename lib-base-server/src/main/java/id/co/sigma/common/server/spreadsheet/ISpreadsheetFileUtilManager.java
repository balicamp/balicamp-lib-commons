package id.co.sigma.common.server.spreadsheet;



/**
 * bean spooler spreadsheet util
 * @author Dode
 **/
public interface ISpreadsheetFileUtilManager {
	
	
	
	/**
	 * register
	 **/
	public void register ( ISpreadsheetFileUtil<?> handler ); 
	
	
	
	/**
	 * membaca handler dengan FQCN dari class
	 * @param fqcn fqcn dari class
	 **/
	public  ISpreadsheetFileUtil<?> getHandler ( String fqcn ); 
	

}
