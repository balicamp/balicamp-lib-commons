package id.co.sigma.common.server.dao.util.impl;

import java.lang.annotation.Annotation;

import javax.persistence.JoinColumn;





/**
 * handler untuk join column
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class JoinColumnAnnotationHandler extends   BaseJPAAnnotationHandler<JoinColumn>{

	
	
	protected static final String PATTERN = "<join-column name='%s'" + 
					" insertable='%s' 	" +
					" updatable='%s'  	" +
					" nullable='%s' 		" +
					" unique='%s'  			" +
					" referenced-column-name='%s' />	";
	
	
	
	@Override
	public String handleAnnotation(String originalMappedFieldName,
			Class<?> originalDataTypeClass, JoinColumn joinColumnDef, Annotation[] allAnnotations , boolean useField) {
		
		String colName = joinColumnDef.name() ; 
		String insertable = joinColumnDef.insertable()?"true":"false";
		String updatable= joinColumnDef.updatable()?"true":"false";
		String nullable = joinColumnDef.nullable()?"true":"false";
		String unique = joinColumnDef.unique()?"true":"false";
		String refName = joinColumnDef.referencedColumnName() ; 
		refName = refName==null||refName.length()==0? colName : refName ; 
		return String.format(PATTERN, colName, insertable, updatable, nullable, unique, refName);
	}

	
}
