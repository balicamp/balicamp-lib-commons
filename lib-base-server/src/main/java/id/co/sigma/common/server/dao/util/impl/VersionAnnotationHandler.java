package id.co.sigma.common.server.dao.util.impl;

import java.lang.annotation.Annotation;

import javax.persistence.Column;
import javax.persistence.Version;

/**
 * ini untuk handler annotation {@link Version}
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public class VersionAnnotationHandler extends BaseJPAAnnotationHandler<Version>{

	
	
	private static final String TEMPLATE="<version name='%s' >%s </version>";
	
	private static final ColumnAnnotationHandler COLUMN_GENERATOR = new ColumnAnnotationHandler();
	@Override
	public String handleAnnotation(String originalMappedFieldName,
			Class<?> originalDataTypeClass, Version annotationData,
			Annotation[] allAnnotations, boolean useField) {
		
		Annotation ann = findFirstAnnotationByType(allAnnotations, Column.class);
		if ( ann==null)
			return "" ; 
		return String.format(TEMPLATE, originalMappedFieldName  ,COLUMN_GENERATOR.handleAnnotation(originalMappedFieldName, originalDataTypeClass, (Column)ann, allAnnotations, useField));
	}

}
