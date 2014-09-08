package id.co.sigma.common.data.impl;



import id.co.sigma.common.data.DataConverter;



/**
 * konverter data dari string ke double
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class DoubleDataConverter extends DataConverter<Double>{
	
	
	
	 
	@Override
	public Double translateData(String stringRepresentation) {
		try {
			return Double.parseDouble(stringRepresentation) ; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}  
		
	}

}
