package id.co.sigma.common.server.dao;

import java.util.HashSet;
import java.util.Set;

import id.co.sigma.common.data.JPAClassLister;
import id.co.sigma.common.data.JPAClassListerGroup;


import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;

public class ClassListedPersistenceUnitManager extends DefaultPersistenceUnitManager{

	/**
	 * class annotated
	 **/
	private Class<? extends JPAClassListerGroup>[] annotatedClassListerGroups;
	
	
	
	
	/**
	 * class annotated
	 **/
	public Class<? extends JPAClassListerGroup>[] getAnnotatedClassListerGroups() {
		return annotatedClassListerGroups;
	}
	/**
	 * class annotated
	 **/
	public void setAnnotatedClassListerGroups(
			Class<? extends JPAClassListerGroup>[] annotatedClassListerGroups) {
		
		this.annotatedClassListerGroups = annotatedClassListerGroups;
	}
	
	
	
	@Override
	protected void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
		try {
			if ( annotatedClassListerGroups !=null&&annotatedClassListerGroups.length>0){
				Set<String> classNames = new HashSet<String>();
				for (Class<? extends JPAClassListerGroup> grp : annotatedClassListerGroups){
					Class<? extends JPAClassLister>[] listers =  grp.newInstance().getListerClasses(); 
					if (listers==null||listers.length== 0 )
						continue ; 
					for (Class<? extends JPAClassLister> actLister : listers){
						JPAClassLister lst = actLister.newInstance();
						Class<?>[] arrClassAnnotated =  lst.getAnnotatedClasses();
						if ( arrClassAnnotated==null|| arrClassAnnotated.length==0)
							continue ; 
						for ( Class<?> annClss : arrClassAnnotated){
							classNames.add(annClss.getName());
						}
					}
				}
				for ( String scn : classNames){
					pui.addManagedClassName(scn);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.postProcessPersistenceUnitInfo(pui);
	}
}
