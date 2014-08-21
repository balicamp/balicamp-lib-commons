package id.co.sigma.common.server.dao.util.impl;

import java.lang.annotation.Annotation;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;

/**
 * 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public class JoinColumnsAnnotationHandler extends BaseJPAAnnotationHandler<JoinColumns>{

	
	private static final JoinColumnAnnotationHandler joinHandler = new JoinColumnAnnotationHandler(); 
	
	
	
	@Override
	public String handleAnnotation(String originalMappedFieldName,
			Class<?> originalDataTypeClass, JoinColumns annotationData,
			Annotation[] allAnnotations, boolean useField) {
		if ( annotationData.value()==null||annotationData.value().length==0)
			return "" ;
		StringBuffer bfr = new StringBuffer() ; 
		for ( JoinColumn jc : annotationData.value()){
			bfr.append(joinHandler.handleAnnotation(originalMappedFieldName, originalDataTypeClass, jc, allAnnotations, useField));
		}
		return bfr.toString();
	}

}
