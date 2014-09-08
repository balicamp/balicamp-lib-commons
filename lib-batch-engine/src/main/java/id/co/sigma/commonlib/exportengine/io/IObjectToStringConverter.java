package id.co.sigma.commonlib.exportengine.io;

import id.co.sigma.commonlib.base.IJobParameterSerilzable;

public interface IObjectToStringConverter extends IJobParameterSerilzable{
	
	
	
	/**
	 * prefix untuk menaruh key converter type
	 */
	public static String CONVERTER_TYPE_KEY_PREFIX ="converterType"; 
	
	
	public String transform (Object rawObject); 
	
	
	
	/**
	 * kode tipe konverter
	 */
	public OuputDataType getConverterTypeCode () ; 
	/**
	 * nomor urut dari converter dalam array
	 */
	public void setSequenceNumber(int sequenceNumber)  ; 

}
