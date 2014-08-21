package id.co.sigma.common.server.dao.util.impl;

import java.lang.annotation.Annotation;

import javax.persistence.Access;
import javax.persistence.AccessType;

import id.co.sigma.common.server.dao.util.JPAAnnotationHandler;

/**
 * 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public class AccessAnnotationHandler implements JPAAnnotationHandler<Access>{

	
	private static final String TEMPLATE ="access='%s'";
	@Override
	public String handleAnnotation(String originalMappedFieldName,
			Class<?> originalDataTypeClass, Access annotationData,
			Annotation[] allAnnotations, boolean useField) {
		return  String.format(TEMPLATE, AccessType.FIELD.equals(   annotationData.value()) ? "FIELD" : "PROPERTY") ;
	}

}
