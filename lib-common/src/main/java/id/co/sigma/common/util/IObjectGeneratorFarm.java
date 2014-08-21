package id.co.sigma.common.util;

/**
 *
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IObjectGeneratorFarm extends IObjectGenerator{
	
	
	/**
	 * array of class yang di proses oleh generator
	 **/
	public String [] acceptedFQCN () ; 

}
