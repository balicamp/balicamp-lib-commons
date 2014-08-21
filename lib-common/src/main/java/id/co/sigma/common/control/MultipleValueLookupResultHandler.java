package id.co.sigma.common.control;

import java.util.List;




/**
 * di desain untuk menerima data loookup panel
 * dengan multiple values.
 * @author wayan ari
 *
 * @param <RESULT> result data.
 */

public interface MultipleValueLookupResultHandler<RESULT> {


	/**
	 * method yang akan di trigger kalau selection sudah selesai di lakukan,
	 * dan harus mengembalikan multiple values.
	 **/
	public void onSelectionDone(List<RESULT> data); 
}
