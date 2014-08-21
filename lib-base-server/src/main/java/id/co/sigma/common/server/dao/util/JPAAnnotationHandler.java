package id.co.sigma.common.server.dao.util;

import java.lang.annotation.Annotation;

public interface JPAAnnotationHandler<ANN extends Annotation> {
	
	
	
	/**
	 * worker untuk memproses 1 annotation. kalau nested ini akan di sebar ke actual worker
	 **/
	public String handleAnnotation ( String originalMappedFieldName, Class<?> originalDataTypeClass,   ANN annotationData , Annotation[] allAnnotations , boolean useField);

}
