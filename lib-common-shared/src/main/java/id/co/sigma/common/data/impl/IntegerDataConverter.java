package id.co.sigma.common.data.impl;

 


import id.co.sigma.common.data.DataConverter;


/**
 * converter data string ke integer
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a> 
 **/
public class IntegerDataConverter extends DataConverter<Integer>{
	
	
	@Override
	public Integer translateData(String stringRepresentation) {
		try {
			return Integer.parseInt(stringRepresentation); 
		} catch (Exception e) {
			System.out.println( "gagal konversi dari : " +stringRepresentation  + " menjadi integer, error : " + e.getMessage()   );
			return null;
		}
		
	}

}
