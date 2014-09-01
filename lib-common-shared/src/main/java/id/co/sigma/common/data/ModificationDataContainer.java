package id.co.sigma.common.data;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * modification container. all posible value
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * 
 **/
public class ModificationDataContainer<DATA> implements  Serializable, IsSerializable , IJSONFriendlyObject<ModificationDataContainer<DATA>>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -475483190431331103L;
	/**
	 * data yang baru di add new ke dalam data(belum di simpan ke dalam database)
	 **/
	protected List<DATA> newlyAppendedData ; 
	/**
	 * data yang sudah di simpan di database, dan di modifikasi di client(kelompok ini musti issue -> update statement)
	 **/
	protected List<DATA> editedData ;
	/**
	 * item yang di hapus
	 **/
	protected List<DATA> erasedData ;
	
	
	
	public ModificationDataContainer(){
		newlyAppendedData= new ArrayList<DATA>();
		editedData =  new ArrayList<DATA>();
		erasedData= new ArrayList<DATA>();
	}
	
	
	
	/**
	 * item yang di hapus
	 **/
	public void setErasedData(List<DATA> erasedData) {
		this.erasedData = erasedData;
	}
	/**
	 * item yang di hapus
	 **/
	public List<DATA> getErasedData() {
		return erasedData;
	}
	/**
	 * data yang sudah di simpan di database, dan di modifikasi di client(kelompok ini musti issue -> update statement)
	 **/
	public List<DATA> getEditedData() {
		return editedData;
	}
	/**
	 * data yang sudah di simpan di database, dan di modifikasi di client(kelompok ini musti issue -> update statement)
	 **/
	public void setEditedData(List<DATA> editedData) {
		this.editedData = editedData;
	}
	/**
	 * data yang baru di add new ke dalam data(belum di simpan ke dalam database)
	 **/
	public void setNewlyAppendedData(List<DATA> newlyAppendedData) {
		this.newlyAppendedData = newlyAppendedData;
	}
	/**
	 * data yang baru di add new ke dalam data(belum di simpan ke dalam database)
	 **/
	public List<DATA> getNewlyAppendedData() {
		return newlyAppendedData;
	}
	

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		
		jsonContainerData.putIfJsonEnableObjects("newlyAppendedData", getNewlyAppendedData());
		jsonContainerData.putIfJsonEnableObjects("erasedData", getErasedData());
		jsonContainerData.putIfJsonEnableObjects("editedData", getEditedData());
		
	}
	
	

	
	@Override
	public ModificationDataContainer<DATA> instantiateFromJSON(
			ParsedJSONContainer jsonContainerData) {
		 
		ModificationDataContainer<DATA> retval = new ModificationDataContainer<DATA>() ;
		retval.setEditedData(  getFromJsonAndTransfer(jsonContainerData,  "editedData" ));
		
		retval.setErasedData(  getFromJsonAndTransfer( jsonContainerData , "erasedData" ));
		retval.setNewlyAppendedData( getFromJsonAndTransfer(jsonContainerData , "newlyAppendedData" ));
		
		
		
		return retval;
	}
	
	protected List<DATA> getFromJsonAndTransfer (ParsedJSONContainer jsonContainerData , String key) {
		String fqcn  =  jsonContainerData.getArrayTypeFQCN(key); 
		if ( fqcn== null|| fqcn.length()==0)
			return null ; 
		@SuppressWarnings("unchecked")
		List<DATA> tmp = (List<DATA>) jsonContainerData.getAsArraylist(key, fqcn) ;
		if ( tmp== null|| tmp.isEmpty())
			return null ; 
		List<DATA> retval = new ArrayList< DATA>();
		retval.addAll(tmp); 
		return retval ; 
	}
	
	
	/**
	 * merge modification data container 1 ke dalam lain nya
	 */
	public void mergeData (ModificationDataContainer<DATA> other) {
		if ( other== null)
			return ; 
		if ( other.getNewlyAppendedData()!= null && !other.getNewlyAppendedData().isEmpty()){
			newlyAppendedData.addAll(other.getNewlyAppendedData());
		}
		if ( other.getEditedData()!= null && !other.getEditedData().isEmpty()){
			editedData.addAll(other.getEditedData()) ;
		}
		if ( other.getErasedData()!= null && !other.getErasedData().isEmpty()){
			erasedData.addAll(other.getErasedData()); 
		}
	}
}
