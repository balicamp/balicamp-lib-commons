package id.co.sigma.common.data;


/**
 * class yang me list class yang JPA Related. Scan kadang tidak bekerja di beberapa server
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface JPAClassLister {
	
	
	public Class<?>[] getAnnotatedClasses () ; 

}
