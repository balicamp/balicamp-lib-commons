package id.co.sigma.commonlib.exportengine.io;



/**
 * no converter, karena object sudah merupakan string
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class NoConverter extends BaseObjectConverter{

	@Override
	public String transform(Object rawObject) {
		
		return (String)rawObject;
	}

	@Override
	public OuputDataType getConverterTypeCode() {
		return OuputDataType.string;
	}

	
}
