/**
 * 
 */
package id.co.sigma.common.util;

/**
 * class untuk memformat string menjadi format string yang diinginkan
 * ini dibuat untuk memformat Nomor Objek Pajak, dan NPWP
 * @author <a href="mailto:gede.wibawa@sigma.co.id">Agus Gede Adipartha Wibawa</a>
 * @since Aug 19, 2013 1:53:29 PM
 */
public class StringFormatingUtils {

	private static StringFormatingUtils instance;
	
	private StringFormatingUtils() {}
	
	public static StringFormatingUtils getInstance() {
		if (instance == null) {
			instance = new StringFormatingUtils();
		}
		return instance;
	}
	
	/**
	 * format string ke spesifik format
	 * ini jika panjang tiap group dari string format tidak sama
	 * misalnya untuk memformat nomor objek pajak 
	 * contohnya memformat dari 000991231312314 menjadi 00099/123/13/123/14
	 * @param stringToFormat string yang akan di format
	 * @param eachLength panjang masing-masing digitnya (ini harus sampai digit yang terbelakang)
	 * @param delimiter delimiter formatnya
	 * @return string yang sudah di format
	 */
	public String formatStringToSpecifiedFormat(String stringToFormat, Integer[] eachLength, String delimiter) {
		String retval = "";
		for (Integer length : eachLength) {
			if (stringToFormat.length() <= length) {
				break ;
			}
			retval += stringToFormat.substring(0, length) + delimiter;
			stringToFormat = stringToFormat.substring(length);
		}
		return retval + (stringToFormat.equals("") ? "" : stringToFormat);
	}
	
	/**
	 * 
	 * @param stringToFormat
	 * @param groupLength
	 * @param delimiter
	 * @return
	 */
	public String formatStringToFixedLengthFormat(String stringToFormat, Integer groupLength, String delimiter) {
		if (stringToFormat == null || delimiter == null) {
			return stringToFormat;
		}
		String retval = stringToFormat.replaceAll("(.{"+groupLength+"})", "$1" + delimiter);
		return retval;
	}
}
