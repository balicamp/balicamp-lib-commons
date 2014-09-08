package id.co.sigma.common.data.serializer;

import id.co.sigma.common.data.ObjectSerializer;

/**
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class StringSerializer implements ObjectSerializer<String> {

	@Override
	public String serialize(String object) {
		return object;
	}

	@Override
	public String deserialize(String stringRepresentation) {
		return stringRepresentation;
	}
	
	
	private static String[] CLS = new String[]{
		String.class.getName()
	};

	@Override
	public String[] acceptedClassFQCNS() {
		return CLS;
	}

}
