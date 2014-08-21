package id.co.sigma.common.control;



/**
 * di desain untuk menerima data loookup panel
 * @param <RESULT> result data
 **/
public interface SingleValueLookupResultHandler<RESULT> {

	/**
	 * method yang akan di trigger kalau selectio sudah selesai di lakukan
	 **/
	public void onSelectionDone (RESULT data); 

}
