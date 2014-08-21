package id.co.sigma.commonlib.importengine.io;


import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;



/**
 * simple, cuma mambangun array of strin berdasarkan definisi. tidak ada proses
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class SimpleFlatFileDataMapper implements FieldSetMapper<String[]>{
	
	
	/**
	 * column definitions
	 **/
	//private BaseUploadColumnDefinition<?> [] columnDefinitions ;

	
	
	
	public String[] mapFieldSet(FieldSet fieldSet) throws BindException {
		 
		String[] retval = new String[fieldSet.getFieldCount()];
		for ( int i=0;i<fieldSet.getFieldCount();i++){
			retval[i]=fieldSet.readString(i);
		}
		return retval;
	} 
	
	
	

}
