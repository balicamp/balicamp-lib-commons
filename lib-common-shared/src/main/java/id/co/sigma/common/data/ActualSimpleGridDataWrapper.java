package id.co.sigma.common.data;

import id.co.sigma.common.data.serializer.json.ObjectSerializerManager;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * wrapper data grid. use case nya : 
 * <br/>
 * <ol>
 * 	<li>table berisi &gt; 20 column</li>
 * <li>grid hanya akan menampilkan 5 column</li>
 * <li>object di buat nested(dengan JPA) </li>
 * <ol>
 * kalau di kirimkan POJO, bisa jadi object graph akan ke load semua ke client. yang mana ini bisa menyebabkan lamban, LIE
 * untuk ini, object di kirim dalam bentuk list of array of Object
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 20-sept-2012
 * @deprecated pertimbangakn metode lain. karena sekarang transfer data juga sudah built in dengan json. pertimbangkan utuilize {@link IJSONFriendlyObject}
 **/
@Deprecated
public class ActualSimpleGridDataWrapper<KEY> implements IsSerializable   {
	
	private KEY id ; 
	private Object[] values ;
	
	
	/**
	 * accessor {@link #values} dengan key string
	 **/
	private IndexedDataAccessor indexedDataAccessor ;  
	
	public KEY getId() {
		return id;
	}
	public void setId(KEY id) {
		this.id = id;
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
		
	}
	/**
	 * accessor {@link #values} dengan key string
	 **/
	public IndexedDataAccessor getIndexedDataAccessor() {
		return indexedDataAccessor;
	}
	/**
	 * accessor {@link #values} dengan key string
	 **/
	public void setIndexedDataAccessor(IndexedDataAccessor indexedDataAccessor) {
		
		this.indexedDataAccessor = indexedDataAccessor;
	}
	
	private final String nullString = null ;

}
