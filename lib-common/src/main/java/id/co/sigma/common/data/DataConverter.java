package id.co.sigma.common.data;



/**
 * worker untuk translate data dari string ke object tertentu
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public abstract class DataConverter<DATA> {

	
	
	
	/**
	 * transalate dari string ke actual data. ini untuk kasus data yang di serialize ke dalam actual nya
	 **/
	public abstract DATA translateData (String stringRepresentation) ; 
	
	
	
	/**
	 * salin dari data ke string representation. ini kebalikan dari {@link #translateData(String)}
	 **/
	public String toString(DATA data){
		if ( data==null)
			return null ;
		return data.toString(); 
	}
}
