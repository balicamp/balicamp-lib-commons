package id.co.sigma.commonlib.importengine.definition.validator;



/**
 * base class simple validator
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public abstract class BaseSimpleFieldValidator<DATA> implements ISimpleFieldValidator<DATA> {
	
	
	private String validationFailureMessageI18NKey ;
	/**
	 * getter, kalau proses validation failure, alam memakai apa message atas kegagalan ini
	 **/
	public String getValidationFailureMessageI18NKey() {
		return validationFailureMessageI18NKey ; 
	}
	
	/**
	 * pasangan dari {@link #getValidationFailureMessageI18NKey()}, ini untuk mempush data key ke dalam
	 **/
	public void setValidationFailureMessageI18NKey(String i18NKey) {
		this.validationFailureMessageI18NKey = i18NKey ; 
	}
	
	
	

}
