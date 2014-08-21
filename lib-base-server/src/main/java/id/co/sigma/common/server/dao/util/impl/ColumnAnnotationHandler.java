package id.co.sigma.common.server.dao.util.impl;

import java.lang.annotation.Annotation;

import javax.persistence.Column;

import id.co.sigma.common.server.dao.util.JPAAnnotationHandler;

public class ColumnAnnotationHandler implements JPAAnnotationHandler<Column> {

	@Override
	public String handleAnnotation(String originalMappedFieldName,
			Class<?> originalDataTypeClass, Column annotationData, Annotation[] allAnnotations , boolean useField) {
		StringBuffer buff = new StringBuffer();
		Column c =  annotationData;
		
		buff.append("<column name=\"" + c.name()+"\"");
		buff.append(" length=\"" + c.length() +"\" ");
		buff.append(" unique=\"" + c.unique()+"\" " );
		buff.append(" nullable=\"" + c.nullable()+"\" " );
		buff.append(" insertable=\"" + c.insertable()+"\" " );
		buff.append(" updatable=\"" + c.updatable()+"\" />" );
		
		return buff.toString();
	}



}
