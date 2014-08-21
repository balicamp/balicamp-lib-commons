package id.co.sigma.commonlib.housekeeper.io;

import id.co.sigma.commonlib.base.BatchParameterType;
import id.co.sigma.commonlib.base.JobParamaterArrayReaderReverseOrder;
import id.co.sigma.commonlib.housekeeper.HouseKeepingConstant;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ArchiveMetadataJobParamaterReader extends JobParamaterArrayReaderReverseOrder{

	
	
	private static final String[] PARAMETERS ={HouseKeepingConstant.TABLE_NAME_KEY}; 
	
	
	private static  BatchParameterType[] BATCH_PARAM_TYPES = {BatchParameterType.STRING} ;  
	
	
	@Override
	protected BatchParameterType[] getParameterTypes() {
		return BATCH_PARAM_TYPES;
	}

	@Override
	protected String[] getParameterNames() {
		return PARAMETERS;
	}

}
