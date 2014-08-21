package id.co.sigma.common.data;




/**
 * kelompok class lister. dalam group sendiri
 **/
public interface JPAClassListerGroup {
	
	
	/**
	 * class lister worker
	 * 
	 **/
	public Class<? extends JPAClassLister>[] getListerClasses() ; 

}
