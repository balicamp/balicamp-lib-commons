package id.co.sigma.common.data;


import java.util.HashSet;

import com.google.gwt.user.client.Command;



/**
 * data container untuk grid yang size nya besar.Menjaga konsistensi. <br/>
 * jadinya kalau di select all, entire match data akan di flag selected. <br/>
 * ini di desain agar paging friendly. jadinya pergantian halaman, apa yang ke select akan tetap ke track<br/>
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 **/
public abstract class LargeSizeDataGridSelectorContainer<DATA> {
	
	private HashSet<String> selectedDataIds = new HashSet<String>();
	private HashSet<String> unselectedDataIds = new HashSet<String>();
	
	
	
	private SingleKeyVersionDataExtractor<DATA> dataKeyExtractor ; 
	/**
	 * ini di set true kalau sudah pernah ada perintah select all. <br/>
	 * methode kerja spt ini :<br/> 
	 * modeSelectAll = true -> yang di isikan {@link #unselectedDataIds}. artinya yang di simpan hanya yang ke un select<br/>
	 * modeSelectAll=false -> yang di isikan adalah {@link #selectedDataIds}. <br/>
	 * tujuan nya agar kita cuma mentransmit data dalam jumlah yg lbh kecil
	 **/
	private boolean modeSelectAll = false ; 

	

	/**
	 * command listener kalau ada perubahan selection
	 **/
	private Command selectionChangeListener ; 
	
	/**
	 * generate data extractor. Di sarankan tidak mempergunakan <i>anonim class</i> agar methode bisa di pergunakan di siisi client dan server
	 * 
	 * 
	 **/
	protected abstract SingleKeyVersionDataExtractor<DATA> generateDataKeyAsString () ; 
	
	
	
	/**
	 * reset data container
	 **/
	public void reset(){
		modeSelectAll = false ;
		selectedDataIds.clear();
		unselectedDataIds.clear();
	}
	
	/**
	 * di trigger kalau item di select/ un selected. di bind pada on click dari checkbox
	 * @param data data yang akan di evaluasi(selected atau tidak)
	 * @param selected flag item selected/tidak true kalau item checked 
	 **/
	public void updateItemState(DATA data, boolean selected ) {
		String key = this.dataKeyExtractor.generateSingleStringKey(data);
		if ( modeSelectAll){
			if ( selected)
				unselectedDataIds.remove(selected);
			else
				unselectedDataIds.add(key);
		}else{
			if ( selected)
				selectedDataIds.remove(selected);
			else
				selectedDataIds.add(key);
		}
	}
	
	
	/**
	 * tukar selection jadinya dari yang ke select jadi select. dan sebaliknya dari yang tidak selected menjadi selected
	 **/
	public void invertSelection () {
		if ( modeSelectAll){
			selectedDataIds.clear();
			if (! unselectedDataIds.isEmpty()){
				selectedDataIds.addAll(unselectedDataIds);
				unselectedDataIds.clear();
			}
		}else{
			unselectedDataIds.clear();
			if (!selectedDataIds.isEmpty()){
				unselectedDataIds.addAll(selectedDataIds);
				selectedDataIds.clear();
			}
		}
		modeSelectAll=!modeSelectAll;
		fireSelectionChangeNotification();
		
	}
	
	
	/**
	 * make select all. jadinya seluruh data akan di pilih
	 **/
	public void selectAll (){
		boolean noNeedNotification = (modeSelectAll && unselectedDataIds.isEmpty());
		
		unselectedDataIds.clear(); 
		selectedDataIds.clear();
		modeSelectAll = true ;
		if (! noNeedNotification)
			fireSelectionChangeNotification();
	}
	
	
	/**
	 * mengosongkan selection
	 **/
	public void unselectAll (){
	boolean noNeedNotification = (!modeSelectAll && selectedDataIds.isEmpty());
		
		unselectedDataIds.clear(); 
		selectedDataIds.clear();
		modeSelectAll = true ;
		if (! noNeedNotification)
			fireSelectionChangeNotification();
	}
	
	
	
	
	/**
	 * ini di set true kalau sudah pernah ada perintah select all. <br/>
	 * methode kerja spt ini :<br/> 
	 * modeSelectAll = true -> yang di isikan {@link #unselectedDataIds}. artinya yang di simpan hanya yang ke un select<br/>
	 * modeSelectAll=false -> yang di isikan adalah {@link #selectedDataIds}. <br/>
	 * tujuan nya agar kita cuma mentransmit data dalam jumlah yg lbh kecil
	 **/
	public boolean isModeSelectAll() {
		return modeSelectAll;
	}
	
	
	/**
	 * worker untuk propagasi selection change 
	 **/
	private void fireSelectionChangeNotification () {
		if ( selectionChangeListener!=null)
			selectionChangeListener.execute();
	}
	
	
	
	/**
	 * timpa selection change listener. ini seharusnya dari gridevent 
	 *@param selectionChangeListener kalau ada perubahan pilihan 
	 **/
	public void assignSelectionChangeListener(Command selectionChangeListener) {
		this.selectionChangeListener = selectionChangeListener;
	}
	
}
