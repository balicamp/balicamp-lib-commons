package id.co.sigma.common.server.util;

/**
 *interface untuk membuat DTO dari original object
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IDTOGenerator<SOURCE , DTO> {
	
	
	
	/**
	 * produce DTO dari object
	 **/
	public DTO generateDTO (SOURCE sourceData) ; 

}
