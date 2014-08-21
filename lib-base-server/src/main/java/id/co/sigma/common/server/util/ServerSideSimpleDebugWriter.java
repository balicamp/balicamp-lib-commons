package id.co.sigma.common.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import id.co.sigma.common.util.SimpleDebugerWriter;

public class ServerSideSimpleDebugWriter implements SimpleDebugerWriter{
	
	
	private Logger logger ;
	public ServerSideSimpleDebugWriter(String debugerName){
		logger = LoggerFactory.getLogger(debugerName);
	}
	@Override
	public void debug(String message) {
		logger.debug(message);
		
	}
	@Override
	public void error(String message) {
		logger.error(message);
		
	}
	

}
