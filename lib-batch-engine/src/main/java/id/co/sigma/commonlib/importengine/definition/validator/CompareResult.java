package id.co.sigma.commonlib.importengine.definition.validator;



/**
 * compare result
 **/
public enum CompareResult {

	equal(0) , 
	less (-1), 
	greater(1) , 
	object1Null(2) , 
	object2Null (3), 
	bothObjectNull (4),
	
	/**
	 * kasus string. cuma bisa memakai equal
	 **/
	notEqualOtherNotApplicable(5); 
	
	private int internalRepresentation ; 
	private CompareResult(int val){
		this.internalRepresentation = val; 
	}
	@Override
	public String toString() {
		return internalRepresentation +"";
	}
	
	
	/**
	 * representasi internal
	 **/
	public int getInternalRepresentation() {
		return internalRepresentation;
	}
	
}
