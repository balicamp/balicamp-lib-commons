package id.co.sigma.common.data.impl;


import id.co.sigma.common.data.DataConverter;



/**
 * converter ke long
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class LongDataConverter extends DataConverter<Long>{
	
	@Override
	public Long translateData(String stringRepresentation) {
		try {
			return Long.parseLong(stringRepresentation); 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
