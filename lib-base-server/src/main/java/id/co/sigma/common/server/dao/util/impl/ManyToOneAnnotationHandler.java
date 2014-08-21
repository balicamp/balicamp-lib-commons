package id.co.sigma.common.server.dao.util.impl;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


/**
 * ini handler many-to-one
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public class ManyToOneAnnotationHandler extends BaseJPAAnnotationHandler<ManyToOne>{

	
	private static final String CLOSING_PATTERN ="</many-to-one>";
	
	private static final String OPEN_PATTERN ="<many-to-one " +
			" name='%s' " +
			" fetch='%s' " +
			" target-entity='%s'  " +
			" %s " +
			" optional='%s'>";
	private static final Map<CascadeType, String> CASCADE_TYPE=new HashMap<CascadeType, String>(); 
	static {
		CASCADE_TYPE.put(CascadeType.ALL,"<cascade-all/>");
		CASCADE_TYPE.put(CascadeType.DETACH,"<cascade-detach/>");
		CASCADE_TYPE.put(CascadeType.MERGE,"<cascade-merge/>");
		CASCADE_TYPE.put(CascadeType.PERSIST,"<cascade-persist/>");
		CASCADE_TYPE.put(CascadeType.REFRESH,"<cascade-refresh/>");
		CASCADE_TYPE.put(CascadeType.REMOVE,"<cascade-remove/>");
	}
	
	private static final String CASCADE_PATTERN="<cascade>%s</cascade>";
	
	private final AccessAnnotationHandler accessHandler = new AccessAnnotationHandler(); 
	
	
	
	private final JoinColumnAnnotationHandler joinHandler = new JoinColumnAnnotationHandler(); 
	
	
	private final JoinColumnsAnnotationHandler joinsHandler = new JoinColumnsAnnotationHandler(); 
	@Override
	public String handleAnnotation(String originalMappedFieldName,
			Class<?> originalDataTypeClass, ManyToOne annotationData, Annotation[] allAnnotations , boolean useField) {
		//
		String fetch =  FetchType.LAZY.equals( annotationData.fetch())?"LAZY" : "EAGER";
		String targetEntity = annotationData.targetEntity()==null? originalDataTypeClass.toString() : annotationData.targetEntity().toString() ;
		Annotation accssAnn =  findFirstAnnotationByType(allAnnotations, Access.class);
		String accessMarker = accssAnn==null ?"" : accessHandler.handleAnnotation(originalMappedFieldName, originalDataTypeClass, (Access) accssAnn, allAnnotations, useField);
		String optional =  annotationData.optional()?"true" : "false" ; 
	
		String openString = String.format(OPEN_PATTERN, originalMappedFieldName , fetch , targetEntity,accessMarker ,optional );
		
		 
		String cascadeStatement = generateCascade(annotationData);
		
		String joinStatement =""; 
		Annotation swap  = findFirstAnnotationByType(allAnnotations, JoinColumn.class);
		if ( swap!=null){
			joinStatement = joinHandler.handleAnnotation(originalMappedFieldName, originalDataTypeClass, (JoinColumn) swap, allAnnotations, useField);
		}
		else{
			Annotation jcs =findFirstAnnotationByType(allAnnotations, JoinColumns.class);
			if ( jcs!=null){
				joinStatement = joinsHandler.handleAnnotation(originalMappedFieldName, originalDataTypeClass, (JoinColumns) jcs, allAnnotations, useField);
			}
		}
		
		
		return openString 
				
				+cascadeStatement
				+joinStatement // ini untuk join statement
				+ CLOSING_PATTERN;
	}	
	
	
	
	
	/**
	 * generate statement cascade
	 **/
	private String generateCascade ( ManyToOne annotationData) {
		if ( annotationData.cascade()==null||annotationData.cascade().length==0)
			return ""; 
		StringBuffer bfr = new StringBuffer(); 
		for (  CascadeType scn : annotationData.cascade()){
			CASCADE_TYPE.get(scn);
		}
		return String.format( CASCADE_PATTERN , bfr.toString());
	}
	
	

}
