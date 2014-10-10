package id.co.sigma.common.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Command;

/**
 * data wrapper untuk edit data dalam list dari sisi client. ini akan me wrap : add new , edited, remove , none dalam 1 pack data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class ClientSideListDataEditorContainer<DATA> implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3407716928113087819L;

	/**
	 * handler pada saat data berganti
	 **/
	protected List<Command> handlerOnDataChange  = new ArrayList<Command>(); 
	
	/**
	 * data yang baru di add new ke dalam data(belum di simpan ke dalam database)
	 **/
	protected List<DATA> newlyAppendedData = new ArrayList<DATA>() ; 
	/**
	 * data yang sudah di simpan di database, dan di modifikasi di client(kelompok ini musti issue -> update statement)
	 **/
	protected List<DATA> editedData  = new ArrayList<DATA>();
	/**
	 * semua data yang visible di client(yang baru, yang di edit, yang sama sekali tidak di modifikasi)
	 **/
	protected List<DATA> allStillExistData = new ArrayList<DATA>();
	/**
	 * item yang di hapus
	 **/
	private List<DATA> erasedData  = new ArrayList<DATA>();
	
	
	
	public ClientSideListDataEditorContainer(){
		newlyAppendedData= new ArrayList<DATA>(); 
		editedData= new ArrayList<DATA>();
		allStillExistData= new ArrayList<DATA>();
		erasedData= new ArrayList<DATA>();
		handlerOnDataChange= new ArrayList<Command>(); 
	}
	
	/**
	 * handler/listener data chane
	 **/
	public void appendDataChangeHandler(Command handler){
		if ( this.handlerOnDataChange.contains(handler))
			return ; 
		this.handlerOnDataChange.add(handler); 
	}
	
	
	/**
	 * remove data change handler
	 **/
	public void removeDataChangeHandler(Command handler){
		if ( this.handlerOnDataChange.contains(handler))
			return ; 
		this.handlerOnDataChange.add(handler); 
	}
	
	
	
	/**
	 * tambah object baru ke dalam database
	 **/
	public void appendNewItem (DATA data){
		
		appendNewItem(data, true);
	}
	
	
	public void appendNewItem (DATA data, boolean fireChangeEvent){
		if ( data==null)
			return ; 
		if ( allStillExistData.contains(data))
			return ; 
		if(this.erasedData.contains(data)) {
			this.erasedData.remove(data);
		}
		this.newlyAppendedData.add(data); 
		allStillExistData.add(data); 
		if ( fireChangeEvent)
			fireDataChangeEvent(); 
		
	}
	
	
	
	
	
	/**
	 * tambah list of object baru ke dalam database
	 **/
	public void appendNewItems(List<DATA> datas) {
		if (datas == null || datas.size() <= 0)
			return;

		for (DATA datum : datas) {
			if (datum == null || allStillExistData.contains(datum))
				continue;
			
			if(this.erasedData.contains(datum)) {
				this.erasedData.remove(datum);
			}
			
			this.newlyAppendedData.add(datum);
			allStillExistData.add(datum);
		}

		fireDataChangeEvent();

	}
	
	/**
	 * modifikasi data
	 **/
	public void modifyItem (DATA data){
		modifyItem(data, true);
	}
	
	
	public void modifyItem (DATA data, boolean fireChangeEvent){
		if ( allStillExistData.contains(data)){
			if ( this.newlyAppendedData.contains(data)|| this.editedData.contains(data)) {
				// in case data yang diubah ada di newlyAppendedData atau editedData
				fireDataChangeEvent();
				return ; 
			}
			this.editedData.add(data); 
		}
		if (fireChangeEvent)
			fireDataChangeEvent();
	}
	
	/**
	 * hapus dari 
	 **/
	public void eraseData (DATA data){
		
		if ( removeDataWorker(data))
			fireDataChangeEvent();
		
	}
	
	public void eraseData(List<DATA> datas) {
		if (datas == null || datas.isEmpty()) 
			return;
		
		boolean needFire = false;
		
		try {
			for ( DATA scn : datas){
				if ( removeDataWorker(scn))
					needFire= true ;
			}
		} finally {
			if ( needFire)
				fireDataChangeEvent();
		}
		
	}
	
	
	
	/**
	 * initiae fill data dengan data yang already persisted
	 **/
	public void initiateAndFillData ( List<DATA> data){
		newlyAppendedData.clear();
		editedData.clear();
		erasedData.clear();
		allStillExistData.clear();
		if ( data!=null && !data.isEmpty()){
			allStillExistData.addAll(data);
		}
		fireDataChangeEvent();
	}
	
	protected boolean removeDataWorker(DATA data){
		if(newlyAppendedData.contains(data)){
			newlyAppendedData.remove(data);
			allStillExistData.remove(data);
			 
			return true;
		}
		if(editedData.contains(data)){
			editedData.remove(data);
			allStillExistData.remove(data);
			erasedData.add(data);
			 
			return true;
		} 
		
		if(allStillExistData.contains(data)){
		
			allStillExistData.remove(data);
			erasedData.add(data);
			return true;
		}
		return false;
		
	}
	
	/**
	 * data yang baru di add new ke dalam data(belum di simpan ke dalam database)
	 **/
	public List<DATA> getNewlyAppendedData() {
		return newlyAppendedData;
	}
	
	/**
	 * data yang sudah di simpan di database, dan di modifikasi di client(kelompok ini musti issue -> update statement)
	 **/
	public List<DATA> getEditedData() {
		return editedData;
	}
	
	
	/**
	 * semua data yang visible di client(yang baru, yang di edit, yang sama sekali tidak di modifikasi)
	 **/
	public List<DATA> getAllStillExistData() {
		return allStillExistData;
	}
	
	
	public void setAllStillExistData(List<? extends DATA> param) {
		this.allStillExistData=(List<DATA>) param;
	}
	
	/**
	 * item yang di hapus
	 **/
	public List<DATA> getErasedData() {
		return erasedData;
	}
	
	/**
	 * add data to allStillExistData ; jika di dlm list table sudah ada datanya
	 * @param data
	 */
	public void addToAllStillExistData(DATA data){
		if(!allStillExistData.contains(data)){
			allStillExistData.add(data);
		}
	}
	
	/**
	 * clear semua data
	 */
	public void clearAllData(){
		clearAllData(true);
	}
	
	
	/**
	 * kosongkan semua data. ini optional disable notifikasi
	 **/
	public void clearAllData(boolean notifychange){
		allStillExistData.clear();
		newlyAppendedData.clear();
		editedData.clear();
		erasedData.clear();
		if(notifychange)
			fireDataChangeEvent();
	}
	
	
	
	/**
	 * propagate event handler data change
	 **/
	public void fireDataChangeEvent (){
		if ( this.handlerOnDataChange==null||handlerOnDataChange.isEmpty())
			return ; 
		for ( Command scn : handlerOnDataChange){
			scn.execute(); 
		}
	}
	
	
	/**
	 * generate data modification wrapper
	 **/
	public ModificationDataContainer<DATA> generateModificationData(){
		ModificationDataContainer<DATA> retval = new ModificationDataContainer<DATA>();
		retval.setEditedData(editedData); 
		retval.setErasedData(erasedData); 
		retval.setNewlyAppendedData(newlyAppendedData) ;
		return retval ; 
		
	}
	
	
	
	
	
	/**
	 * mengecek apakah object ini new object atau bukan
	 **/
	public boolean isNewObject (DATA data){
		if ( this.newlyAppendedData.isEmpty())
			return false ; 
		for ( DATA scn : newlyAppendedData){
			if ( scn.equals(data))
				return true ; 
		}
		return false ; 
	}
	
	
	
	
	
	/**
	 * index(basis 0) dari data dalam grid. jadi data X di dalam grid ini index berapa
	 * @param dataToCheck data yang perlu di cek index nya
	 * @return index data dalam grid, berbais 0  
	 * 
	 **/
	public int getDataRowIndex (DATA dataToCheck ) {
		if ( dataToCheck==null)
			return -1 ; 
		for ( int i = 0 ; i < allStillExistData.size() ; i++){
			if ( dataToCheck.equals(allStillExistData.get(i)))
				return i ; 
		}
		return -1 ; 
	}
	
	
	/**
	 * ada berapa data yang aktiv dalam data container. 
	 **/
	public int getAvaliableDataCount () {
		return allStillExistData.size() ; 
		
		
		
	}
}
