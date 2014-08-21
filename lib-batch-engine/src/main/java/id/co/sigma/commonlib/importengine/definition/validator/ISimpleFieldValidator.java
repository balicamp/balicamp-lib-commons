package id.co.sigma.commonlib.importengine.definition.validator;



/**
 * simple field validator. ini model nya mem-validasi field dengan simple rule : <ol>
 * <li>=</li>
 * <li>!=</li>
 * <li>&gt;</li>
 * <li>&gt;=</li>
 * <li>&lt;</li>
 * <li>&lt;=</li>
 * <li>IN</li>
 * <li>NOT IN</li>
 * </ol>
 * @param <DATA> data yang hendak di validasi. ini isinya kurang lebih String, Date BigDecimal
 **/
public interface ISimpleFieldValidator<DATA> {
	
	
	/**
	 * worker untuk memvalidasi 
	 * @param data data yang akan di validasi
	 * @return true = validasi sukses, else = validasi gagal
	 * 
	 **/
	public boolean validate(DATA data) ; 
	
	/**
	 * getter, kalau proses validation failure, alam memakai apa message atas kegagalan ini
	 **/
	public String getValidationFailureMessageI18NKey() ; 
	
	/**
	 * pasangan dari {@link #getValidationFailureMessageI18NKey()}, ini untuk mempush data key ke dalam
	 **/
	public void setValidationFailureMessageI18NKey(String i18NKey) ;
	
	
	
	
	/**
	 * generate error message untuk validator. data di generate berdasarkan data yang bermasalah, dan field untuk mana field yang bermasalah ini
	 * @param data data yang bermasalah
	 * @return message errror 
	 **/
	public String generateErrorMessage(DATA data) ; 

}
