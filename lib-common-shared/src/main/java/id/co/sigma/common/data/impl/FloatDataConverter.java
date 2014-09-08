package id.co.sigma.common.data.impl;




import id.co.sigma.common.data.DataConverter;



/**
 * String to double converter
 **/
public class FloatDataConverter extends DataConverter<Float> {

	
	
	@Override
	public Float translateData(String stringRepresentation) {
		try {
			return Float.parseFloat(stringRepresentation); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}
		
	}

}
