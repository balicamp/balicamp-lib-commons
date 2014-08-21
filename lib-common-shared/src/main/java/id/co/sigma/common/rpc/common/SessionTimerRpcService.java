package id.co.sigma.common.rpc.common;

import id.co.sigma.common.rpc.JSONSerializedRemoteService;

public interface SessionTimerRpcService extends JSONSerializedRemoteService {
	
	/**
	 * method ini membaca definisi session timeout dari properties file "http.session.timeout". 
	 * 
	 * @return Session timeout dalam milliseconds.
	 */
	public Long getSessionTimeoutLength();

}
