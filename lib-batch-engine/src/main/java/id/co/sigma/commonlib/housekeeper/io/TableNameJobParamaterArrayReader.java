package id.co.sigma.commonlib.housekeeper.io;

import id.co.sigma.commonlib.base.BatchParameterType;
import id.co.sigma.commonlib.base.JobParamaterArrayReader;
import id.co.sigma.commonlib.housekeeper.HouseKeepingConstant;

/**
 * reader table name. table name akan equivalen dengan nama file untuk di zip
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class TableNameJobParamaterArrayReader extends JobParamaterArrayReader{

	
	
	private static final BatchParameterType[] PARAMETER_TYPES = {BatchParameterType.STRING};
	private static final String[] PARAMETER_NAMES={HouseKeepingConstant.TABLE_NAME_KEY} ; 
	
	@Override
	protected BatchParameterType[] getParameterTypes() {
		return PARAMETER_TYPES;
	}

	@Override
	protected String[] getParameterNames() {
		return PARAMETER_NAMES;
	}

}
