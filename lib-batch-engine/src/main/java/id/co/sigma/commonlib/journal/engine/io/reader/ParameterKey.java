package id.co.sigma.commonlib.journal.engine.io.reader;



/**
 * wrapper internal key dari query param. ini untuk memaksa proses menaruh key ke dalam data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class ParameterKey {
	
	private String internalKey ; 
	
	public ParameterKey(String key){
		this.internalKey = key ; 
	}
	
	
	/**
	 * internal Key dari object
	 **/
	public String getInternalKey() {
		return internalKey;
	}
	

	

}
