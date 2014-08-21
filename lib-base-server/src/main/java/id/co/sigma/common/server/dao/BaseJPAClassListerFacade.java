package id.co.sigma.common.server.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import id.co.sigma.common.data.JPAClassLister;
import id.co.sigma.common.data.JPAClassListerGroup;

/**
 * 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public abstract class BaseJPAClassListerFacade {
	
	
	
	
	/**
	 * array of group class lister
	 **/
	protected abstract JPAClassListerGroup[] getListerGroups () ; 
	
	
	private Set<Class<?> > jpaClasses ; 
	/**
	 * data dari {@link #getListerGroups()} , di srink up menjadi array of class
	 **/
	protected Set<Class<?>> mergeClassList() {
		if ( jpaClasses!=null)
			return jpaClasses ; 
		
		jpaClasses= new HashSet<Class<?>>(); 
		JPAClassListerGroup[] grpArray = getListerGroups(); 
		for ( JPAClassListerGroup grp : grpArray){
			Class<?>[] arr =  grp.getListerClasses();
			for ( Class<?> cls : arr){
				try {
					JPAClassLister i = (JPAClassLister)BeanUtils.instantiate(cls);
					Class<?> [] actualEntityClass =  i.getAnnotatedClasses();
					for ( Class<?> ec  : actualEntityClass){
						jpaClasses.add(ec);
					}
					
				} catch (Exception e) {
					e.printStackTrace(); 
				}
				
			}
			
		}
		return jpaClasses ; 
	}
	
	
	/**
	 * class JPA classes
	 **/
	public Set<Class<?>> getJpaClasses() {
		if ( jpaClasses==null)
			return mergeClassList() ; 
		return jpaClasses;
	}

}
