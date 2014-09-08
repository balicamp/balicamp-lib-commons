package id.co.sigma.common.data.app;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * status common batch
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public enum CommonBatchStatus implements IJSONFriendlyObject<CommonBatchStatus>{
	
	      
	
	/**
	 * belum di eksekusi sama sekali
	 */
	NOT_YET_EXECUTED ("not-yet")  , 
	/**
	 * task masih berjalan
	 */
	running("running") , 
	/**
	 * gagal. berarti ada yang perlu di perbaiki
	 */
	fail("fail") , 
	
	/**
	 * task sukses
	 */
	success("success") ; 
	
	
	
	
	
	
	private String internalCode ;
	
	
	
	public static CommonBatchStatus instantiateFromString (String sample) {
		for ( CommonBatchStatus scn : CommonBatchStatus.values()) {
			if ( scn.internalCode.equals(sample))
				return scn ; 
		}
		return null ; 
		
	}
	
	private CommonBatchStatus(String internalCode ) {
		this.internalCode = internalCode ; 
	}
	
	@Override
	public String toString() {
		return internalCode;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("internalKey", internalCode);
		
	}

	@Override
	public CommonBatchStatus instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		String code = jsonContainer.getAsString("internalKey"); 
		return instantiateFromString(code);
	}

}
