package id.co.sigma.common.data;


/**
 * interface untuk provide data dengan format custom. sesuai dengan keperluan
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 5 Aug 2012
 **/
public interface CustomDataFormatter<DATA> {


	/**
	 * return data sesuai dengan format yang di perlukan. 
	 * @param data data yang di render jadi string sesuai format yang di perlukan
	 **/
	public String getFormattedData (DATA data); 
	
	
	
	/**
	 * yang di pakai untuk value apa
	 **/
	public String getStringForValue(DATA data) ; 
}
