package id.co.sigma.common.server.util.strings.fixedlength;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface CustomFixedLengthSerializer {

	/**
	 * konversi object menjadi fixed length string 
	 * @param fieldData nilai dari field
	 * @param lengthOfString panjang string yang di perlukan
	 */
	public String serialize ( Object fieldData , int lengthOfString ); 
}
