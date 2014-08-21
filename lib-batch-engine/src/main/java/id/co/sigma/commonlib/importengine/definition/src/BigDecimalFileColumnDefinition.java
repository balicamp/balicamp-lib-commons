package id.co.sigma.commonlib.importengine.definition.src;

import id.co.sigma.commonlib.importengine.exception.DataTypeViolationException;

import java.math.BigDecimal;



/**
 * uploader dengan tipe data big decimal
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class BigDecimalFileColumnDefinition extends BaseFileColumnDefinition<BigDecimal>{

	
	
	/**
	 * key internalization untuk message kalau decimal field salah
	 **/
	private String bigdecimalInvalidDataSourceMessageI18NKey ;
	
	
	
	
	public BigDecimalFileColumnDefinition(){
		super(); 
	}
	
	/**
	 * decimal separator = true = pakai . (format inggris) 
	 **/
	private boolean useDotDecimalSeparator = false ; 


	@Override
	protected BigDecimal translateStringToData(String uploadSrcData)
			throws DataTypeViolationException {
		
		if (uploadSrcData==null||uploadSrcData.length()==0)
			return null ; 
		if ( !useDotDecimalSeparator){
			
			if (uploadSrcData.indexOf(",")>=0){
				String[] arr = uploadSrcData.split("\\,"); 
				uploadSrcData =  arr[0].replaceAll("\\.", "") + "." + arr[1];
				
			}
			
		}
		try {
			return new BigDecimal(uploadSrcData.trim());
		} catch (Exception e) {
			throw new DataTypeViolationException("gagal konversi value  " + uploadSrcData + ", field " + getColumnTitle() +",ini bukan angka yang valid"  ,getColumnTitle() , uploadSrcData , bigdecimalInvalidDataSourceMessageI18NKey, BigDecimal.class.getName());
		}
	}
	
	/**
	 * key internalization untuk message kalau decimal field salah
	 **/
	public String getBigdecimalInvalidDataSourceMessageI18NKey() {
		return bigdecimalInvalidDataSourceMessageI18NKey;
	}

	/**
	 * key internalization untuk message kalau decimal field salah
	 **/
	public void setBigdecimalInvalidDataSourceMessageI18NKey(
			String bigdecimalInvalidDataSourceMessageI18NKey) {
		this.bigdecimalInvalidDataSourceMessageI18NKey = bigdecimalInvalidDataSourceMessageI18NKey;
	}
	
	
	/**
	 * decimal separator = true = pakai . (format inggris) 
	 **/
	public void setUseDotDecimalSeparator(boolean useDotDecimalSeparator) {
		this.useDotDecimalSeparator = useDotDecimalSeparator;
	}
	
	/**
	 * decimal separator = true = pakai . (format inggris) 
	 **/
	public boolean isUseDotDecimalSeparator() {
		return useDotDecimalSeparator;
	}

}
