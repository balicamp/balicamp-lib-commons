package id.co.sigma.commonlib.exportengine.io;



public class CommonToStringConverter extends BaseObjectConverter{

	@Override
	public String transform(Object rawObject) {
		if ( rawObject==null)
			return null ; 
		return rawObject.toString();
	}

	@Override
	public OuputDataType getConverterTypeCode() {
		return OuputDataType.commonToString;
	}

	

}
