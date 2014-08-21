package id.co.sigma.common.server.spreadsheet.impl;

import java.util.HashMap;
import java.util.Map;

import id.co.sigma.common.server.spreadsheet.ISpreadsheetFileUtil;
import id.co.sigma.common.server.spreadsheet.ISpreadsheetFileUtilManager;


/**
 * manager spreadsheet utils
 * @author Dode
 **/
public class SpreadsheetFileUtilManagerImpl implements ISpreadsheetFileUtilManager{
	
	
	public Map<String, ISpreadsheetFileUtil<?>> indexedHandler = new HashMap<String, ISpreadsheetFileUtil<?>>(); 
 
	@Override
	public void register(ISpreadsheetFileUtil<?> handler) {
		indexedHandler.put(handler.getHandledClass().getName(), handler); 
		
	}

	@Override
	public ISpreadsheetFileUtil<?> getHandler(String fqcn) {
		return indexedHandler.get(fqcn);
	}

}
