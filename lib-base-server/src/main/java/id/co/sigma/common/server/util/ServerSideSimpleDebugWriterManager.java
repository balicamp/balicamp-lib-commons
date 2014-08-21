package id.co.sigma.common.server.util;

import id.co.sigma.common.util.SimpleDebugerWriter;
import id.co.sigma.common.util.SimpleDebugerWriterManager;

public class ServerSideSimpleDebugWriterManager implements SimpleDebugerWriterManager{

	@Override
	public SimpleDebugerWriter getDebugWriter(String debuggerName) {
		return   new ServerSideSimpleDebugWriter(debuggerName);
	}
}
