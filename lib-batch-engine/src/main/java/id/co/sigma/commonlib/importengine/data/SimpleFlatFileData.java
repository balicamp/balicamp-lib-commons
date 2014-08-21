package id.co.sigma.commonlib.importengine.data;

import id.co.sigma.commonlib.importengine.exception.BaseUploadException;

import java.util.ArrayList;
import java.util.Arrays;



/**
 * wrapper data hasil translasi
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class SimpleFlatFileData {
	
	
	/**
	 * data yang berhasil di translasikan
	 **/
	private Object[] data ;

	/**
	 * data beres atau data yang di reject
	 **/
	private boolean rejectedData ;
	
	
	
	/**
	 * exceptions
	 **/
	private ArrayList<BaseUploadException> exceptions =new ArrayList<BaseUploadException>();
	/**
	 * data yang berhasil di translasikan
	 **/
	public Object[] getData() {
		return data;
	}

	/**
	 * data yang berhasil di translasikan
	 **/
	public void setData(Object[] data) {
		this.data = data;
	}
	
	/**
	 * data beres atau data yang di reject
	 **/
	public boolean isRejectedData() {
		return rejectedData;
	}
	/**
	 * data beres atau data yang di reject
	 **/
	public void setRejectedData(boolean rejectedData) {
		this.rejectedData = rejectedData;
	} 
	
	/**
	 * masukan exption ke dalam data
	 **/
	public void pushExceptions(BaseUploadException exception){
		rejectedData = true ; 
		this.exceptions.add(exception); 
	}

	@Override
	public String toString() {
		return "SimpleFlatFileData [data=" + Arrays.toString(data) + "]";
	}

	

}
