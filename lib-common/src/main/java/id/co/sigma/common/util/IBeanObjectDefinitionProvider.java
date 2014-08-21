package id.co.sigma.common.util;

import java.util.ArrayList;

/**
 * 
 * interface tools untuk membreak down isi dari 1 class, di baca berdasarkan field nya. class x isi nya apa begitu kurang lebih metode nya
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public interface IBeanObjectDefinitionProvider {
	
	/**
	 * method ini membaca meta data class , di batasi dengan field yang di kirimkan. 
	 * jadinya class X, untuk fields dalam parameter <i>fields</i> class type nya apa saja. 
	 **/
	public ArrayList<Class<?>>  readPropertyTypeAsArray(Class<?>  source , String[] fields );  

}
