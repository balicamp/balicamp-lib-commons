package id.co.sigma.common.data;

import id.co.sigma.common.data.entity.I18RelatedClassLister;

public class LibCommonClassListerGroup implements JPAClassListerGroup{

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends JPAClassLister>[] getListerClasses() {
		return new Class[]{
				I18RelatedClassLister.class	
		} ;
	}

}
