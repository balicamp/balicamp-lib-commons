package id.co.sigma.common.server.dao.base;

import java.io.Serializable;

/**
 * simple key value untuk passing parameter berbasis simple field
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class SimpleKeyValueParameter {
	
	
	
	/**
	 * nama field untuk di update
	 */
	private String fieldName ; 
	
	
	
	
	/**
	 * paramter yang hendak di set ke dalam database
	 */
	private Serializable parameter  ; 
	
	public SimpleKeyValueParameter(){}
	
	public SimpleKeyValueParameter(String fieldName, Serializable parameter) {
		super();
		this.fieldName = fieldName;
		this.parameter = parameter;
	}

	/**
	 * nama field untuk di update
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * nama field untuk di update
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	/**
	 * paramter yang hendak di set ke dalam database
	 */
	public void setParameter(Serializable parameter) {
		this.parameter = parameter;
	}
	/**
	 * paramter yang hendak di set ke dalam database
	 */
	public Serializable getParameter() {
		return parameter;
	}

}
