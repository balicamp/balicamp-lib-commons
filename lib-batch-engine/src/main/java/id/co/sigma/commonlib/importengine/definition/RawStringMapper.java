package id.co.sigma.commonlib.importengine.definition;



/**
 * interface translator dari string ke X. ini dalam kasus field yang lookup
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @param <RESULT> data hasil translasi
 **/
public interface RawStringMapper<RESULT> {
	
	
	/**
	 * translate dari string ke tipe yang di perlukan. translasi berasal dari database
	 * @param rawData field yang perlu di translate
	 **/
	public RESULT translate(String rawData);

}
