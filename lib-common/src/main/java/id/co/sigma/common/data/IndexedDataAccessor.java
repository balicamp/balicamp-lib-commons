package id.co.sigma.common.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * field accessor
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class IndexedDataAccessor implements IsSerializable{
	
	private HashMap<String, Integer> indexer = new HashMap<String, Integer>();
	private String[] indexerFields ;
	
	
	/**
	 * nama-nama class dalam data yang di kirimkan ke client. karena setiap object di kirim dalam string, maka tipe nya perlu di kirimkan juga 
	 **/
	private String[] indexerClassNames ; 
	public String[] getIndexerFields() {
		return indexerFields;
	}
	
	
	
	/**
	 * ini menaruh nama-nama field yang akan di kirim dlaam paket data ke client. ini berguna untuk akeses ke ke field dengan data index
	 * @param indexerFields nama-nama field yang di transfer ke client
	 * @param excludedFieldIndex ini field-field yang di reject. jadinya tidak akan di kirim ke client
	 **/
	public void setIndexerFields(String[] indexerFields, ArrayList<Integer > excludedFieldIndex) {
		indexer.clear();
		
		if ( indexerFields!=null){
			this.indexerFields = new String[indexerFields.length-excludedFieldIndex.size()];
			int arrayIndexer = 0 ; // ini untuk menaruh agar isi array tetap urut
			for ( int i=0;i< indexerFields.length;i++ ){
				if ( excludedFieldIndex.contains(i))
					continue ; 
				indexer.put(indexerFields[i], i);
				this.indexerFields[arrayIndexer] = indexerFields[i];
				arrayIndexer++; 
			}
		}
		else{
			this.indexerFields = null ; 
		}
	}
	
	
	
	/**
	 * getter data dari array
	 * @param field(ini bersesuaian dengan definisi grid)
	 **/
	@SuppressWarnings("unchecked")
	public<TIPE> TIPE get( Object[] dataToRead ,  String field){
		try {
			if ( !indexer.containsKey(field)){
				GWT.log("hei,hei watched it!!, " + field +",tidak ada dalam daftar field request");
				return null ; 
			}
			int idx= indexer.get(field);
			if ( idx< 0 || idx> dataToRead.length-1)
				return null ; 
			return (TIPE) dataToRead[idx];
		} catch (Exception e) {
			GWT.log("field : " + field + ", memuat error :" + e.getMessage() , e);
			return null ; 
		}
		
	}

	
	
	/**
	 * ini otomatis akan mengisi {@link #indexerClassNames}, jadi class apa saja yang di pass ke client
	 **/
	public void plugPassedClassesCatalog(ArrayList<Class<?>> classes) {
		if ( classes!=null&&!classes.isEmpty()){
			indexerClassNames=new String[classes.size()];
			int i =0;
			for ( Class<?> scn : classes){
				indexerClassNames[i++]=scn.getName();
			}
		}
			
	}

	/**
	 * nama-nama class dalam data yang di kirimkan ke client. karena setiap object di kirim dalam string, maka tipe nya perlu di kirimkan juga 
	 **/
	public String[] getIndexerClassNames() {
		return indexerClassNames;
	}


	/**
	 * nama-nama class dalam data yang di kirimkan ke client. karena setiap object di kirim dalam string, maka tipe nya perlu di kirimkan juga 
	 **/
	public void setIndexerClassNames(String[] indexerClassNames) {
		this.indexerClassNames = indexerClassNames;
	} 
	

}
