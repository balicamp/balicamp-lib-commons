package id.co.sigma.common.data;



public class ClientSideListDataEditorContainerSortable<DATA extends ISortableData> extends ClientSideListDataEditorContainer<DATA>{

	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1101335460412907575L;

	@Override
	public void appendNewItem(DATA data) {
		data.setSequenceNumber(getAvaliableDataCount() +1); 
		super.appendNewItem(data);
	}
	
	@Override
	public void appendNewItem(DATA data, boolean fireChangeEvent) {
		data.setSequenceNumber(getAvaliableDataCount() +1); 
		super.appendNewItem(data, fireChangeEvent);
	}


	@Override
	protected boolean removeDataWorker(DATA data) {
		// 1 musti ketemu dulu diri sendiri index berapa
		int myIndex = 0 ; 
		for ( DATA scn : this.allStillExistData){
			if ( data.equals(scn)){
				break ; 
			}
			myIndex ++ ;
		}
		
		int maxLength = this.allStillExistData.size() ;
		for ( int i = myIndex+1 ;i< maxLength  ; i++ ){
			DATA d =  allStillExistData.get(i);
			d.setSequenceNumber(d.getSequenceNumber()-1); 
			if (! this.newlyAppendedData.contains(d)){
				if(!editedData.contains(d)){
					editedData.add(d); 
				}	
			}
		}
		
		return super.removeDataWorker(data);
	}
	
	
	
	/**
	 * data di naikan, artinya sequence = sequence -1 
	 **/
	public void moveDataUp(DATA data) {
		//1. cari data sebelum dia
		//int myIndex = data.getSequenceNumber() ;
		int idx = 0 ;
		DATA beforeData = null ; 
		for ( DATA scn : this.allStillExistData){
			
			if ( scn.equals(data )/* myIndex>= scn.getSequenceNumber()*/){
				beforeData = allStillExistData.get(idx-1); 
				break ; 
			}
			idx++ ;
		}
		exchangeSequenceNumber(data, beforeData);
	}
	
	
	
	
	
	
	/**
	 * data di turunkan, artinya sequence = sequence -1
	 **/
	public void moveDataDown (DATA data){
		// 1.  cari data sesudah dirinya
		//int myIndex = data.getSequenceNumber() ;
		int idx = 0 ;
		DATA afterData = null ; 
		for ( DATA scn : this.allStillExistData){
			
			if ( scn.equals(data )/* myIndex>= scn.getSequenceNumber()*/){
				afterData = allStillExistData.get(idx+1); 
				break ; 
			}
			idx++ ; 
		}
		exchangeSequenceNumber(afterData, data);
	}
	
	
	
	
	/**
	 * ini untuk memindahkan index dari data, karena tukar posisi
	 **/
	protected void exchangeSequenceNumber (DATA largerSeqData , DATA smallerSeqData) {
		int gedean = largerSeqData.getSequenceNumber() ; 
		int cenikan = smallerSeqData.getSequenceNumber() ;  
		 
		int indexSmaller = 0  ; 
		for ( DATA scn : allStillExistData){
			if ( scn.equals(smallerSeqData)){
				break ; 
			}
			indexSmaller++  ; 
		}
		
//		System.out.println("masuk indexsmaller > 0");
		allStillExistData.remove(largerSeqData);
		allStillExistData.remove(smallerSeqData);
		allStillExistData.add(indexSmaller, largerSeqData);
		allStillExistData.add(indexSmaller+1, smallerSeqData);

		smallerSeqData.setSequenceNumber(gedean);
		largerSeqData.setSequenceNumber(cenikan);
			
		//
		if (! this.newlyAppendedData.contains(smallerSeqData)){
			if(!editedData.contains(smallerSeqData)){
				editedData.add(smallerSeqData); 
			}	
		}
		
		if (! this.newlyAppendedData.contains(largerSeqData)){
			if(!editedData.contains(largerSeqData)){
				editedData.add(largerSeqData); 
			}	
		}
		fireDataChangeEvent();
		
	}
	

}
