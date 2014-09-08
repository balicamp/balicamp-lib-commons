package id.co.sigma.common.server.service.system.impl;

import id.co.sigma.common.data.SimpleSytemParameterDrivenValue;

/**
 * metadata utnuk meyimpan field yang isi di taruh dalam system parameter
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class SystemParameterFieldMetadata {
	
	
	/**
	 * nama field dalam calss
	 */
	private String fieldName ;
	
	/**
	 * definisi data
	 */
	private SimpleSytemParameterDrivenValue<?> parameterDefinition ; 
	
	/**
	 * definisi data
	 */
	public SimpleSytemParameterDrivenValue<?> getParameterDefinition() {
		return parameterDefinition;
	}
	/**
	 * definisi data
	 */
	public void setParameterDefinition(
			SimpleSytemParameterDrivenValue<?> parameterDefinition) {
		this.parameterDefinition = parameterDefinition;
	}
	/**
	 * nama field dalam calss
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * nama field dalam calss
	 */
	public String getFieldName() {
		return fieldName;
	}

}
