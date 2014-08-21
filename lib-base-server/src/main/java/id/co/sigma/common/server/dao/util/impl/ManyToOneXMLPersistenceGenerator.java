package id.co.sigma.common.server.dao.util.impl;


import java.lang.reflect.Field;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


import id.co.sigma.common.server.dao.util.AnnotationHandlerUtil;
import id.co.sigma.common.server.dao.util.IXMLPersistenceGenerator;
import id.co.sigma.common.server.dao.util.JPAAnnotationHandler;



/**
 * worker utnuk handle many to one
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class ManyToOneXMLPersistenceGenerator implements IXMLPersistenceGenerator{

	@Override
	public String generateXMLComplience(Field pojoField) {
		ManyToOne manyToOne =  pojoField.getAnnotation(ManyToOne.class);
		JoinColumns cols =  pojoField.getAnnotation(JoinColumns.class);
		StringBuffer buffer = new StringBuffer();
		@SuppressWarnings("rawtypes")
		Class swap =  manyToOne.targetEntity();
		 
		buffer.append("<many-to-one name=\""+pojoField.getName()+"\" fetch=\""+ (FetchType.LAZY.equals( manyToOne.fetch())? "LAZY" :"EAGER") +"\"");
		if ( !void.class.equals(swap))
			buffer.append(" target-entity=\""+manyToOne.targetEntity().getName()+"\" ");
		 buffer.append(">");
		JPAAnnotationHandler<JoinColumn> jnHandler =   (JPAAnnotationHandler<JoinColumn>) AnnotationHandlerUtil.getInstance().getHandler(JoinColumn.class);
		
		if (cols!=null){
			JoinColumn[] allJoins= cols.value();
			for ( JoinColumn scn : allJoins){
				// buffer.append(jnHandler.handleAnnotation(originalMappedFieldName, originalDataTypeClass, annotationData))
			}
		}
		else{
			/*
			buffer.append(handleJoinColumn(pojoField.getAnnotation(JoinColumn.class)));
			Annotation[] allAnns =  pojoField.getAnnotations();
			if ( allAnns!=null && allAnns.length>0){
				for ( Annotation scn : allAnns){
					if ( JoinColumn.class.equals(scn.getClass())){
						buffer.append(handleJoinColumn((JoinColumn)scn));
					}
				}
			}
			*/
		}
		buffer.append("</many-to-one>");
		
		return buffer.toString();
	}


	
	
	

}
