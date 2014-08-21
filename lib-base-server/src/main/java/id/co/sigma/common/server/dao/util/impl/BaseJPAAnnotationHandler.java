package id.co.sigma.common.server.dao.util.impl;

import java.lang.annotation.Annotation;

import id.co.sigma.common.server.dao.util.JPAAnnotationHandler;

/**
 * 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public abstract class BaseJPAAnnotationHandler<ANN extends Annotation> implements JPAAnnotationHandler<ANN>{
	
	
	
	
	/**
	 * scan annotation dari dalam array, sesuai dengan tipe yang di minta. misal cari annotation yang tipe nya Access
	 **/
	protected Annotation findFirstAnnotationByType (Annotation[] annotations , Class<? extends Annotation> cls) {
		if ( annotations==null||annotations.length==0)
			return null ; 
		for ( Annotation scn : annotations){
			try {
				scn.getClass().asSubclass(cls); 
				return scn ; 
			} catch (Exception e) {
				
			} 
		}
		
		return null ; 
	}

}
