package id.co.sigma.commonlib.importengine.data;



/**
 * align data, kalau fixed length. ini berarti dalam fixed length, apakah kiri atau kanan yang di trim
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public enum FixedLengthDataAlign {
	
	
	leftAlign("L") , 
	rightAlign("R") ; 
	
	private String internalRepresentation ; 
	
	private FixedLengthDataAlign(String rep){
		this.internalRepresentation = rep; 
	}

	@Override
	public String toString() {
		return internalRepresentation;
	}
}
