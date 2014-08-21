package id.co.sigma.common.server.util;

import org.apache.log4j.Logger;

/**
 * 1 thread logger holder. ini menympan data logger dalam 1  thread, model nya di awal class tertentu akan mengeset logger berdasarkan user tertentu 
 * 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public final class LoggerHolder {
	
	private static LoggerHolder instance ; 
	
	
	/**
	 * our logger. ini di inject di awal 
	 **/
	private Logger logger ; 
	
	private LoggerHolder(){
		
	}
	
	
	/**
	 * our singleton instance
	 **/
	public static LoggerHolder getInstance() {
		if(instance==null){
			instance=new LoggerHolder();
		}
		return instance;
	}
	
	
	/**
	 * our logger. ini di inject di awal 
	 **/
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	/**
	 * our logger. ini di inject di awal 
	 **/
	public Logger getLogger() {
		return logger;
	}

}
