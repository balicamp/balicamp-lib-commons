package id.co.sigma.commonlib.importengine.definition.src;

import id.co.sigma.commonlib.importengine.exception.DataTypeViolationException;



/**
 * ini menghasilkan string tanpa translasi. jadinya di telan as is string nya
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class SimpleStringColumnDefinition extends BaseFileColumnDefinition<String>{

	@Override
	protected String translateStringToData(String uploadSrcData)
			throws DataTypeViolationException {
		return uploadSrcData;
	}

}
