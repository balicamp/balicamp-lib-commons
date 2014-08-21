package id.co.sigma.common.server.dao.util;

import id.co.sigma.common.server.dao.util.impl.ColumnAnnotationHandler;
import id.co.sigma.common.server.dao.util.impl.JoinColumnAnnotationHandler;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

public final class AnnotationHandlerUtil {

	private static AnnotationHandlerUtil instance ;
	
	
	private Map<Class<? extends Annotation>, JPAAnnotationHandler<? extends Annotation>> handlerMap =new  HashMap<Class<?  extends Annotation>, JPAAnnotationHandler<? extends Annotation>>();
	
	private AnnotationHandlerUtil(){
		handlerMap.put(Column.class	, new ColumnAnnotationHandler());
		handlerMap.put(JoinColumn.class,new  JoinColumnAnnotationHandler());
		
		
	}
	
	
	public static AnnotationHandlerUtil getInstance() {
		if ( instance==null)
			instance = new AnnotationHandlerUtil(); 
		
		return instance;
	}
	
	public JPAAnnotationHandler<? extends Annotation> getHandler (Class<? extends Annotation> annotation) {
		return handlerMap.get(annotation);
	}
}
