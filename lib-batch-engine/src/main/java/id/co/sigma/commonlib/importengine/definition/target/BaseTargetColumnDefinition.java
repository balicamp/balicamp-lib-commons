package id.co.sigma.commonlib.importengine.definition.target;



/**
 * base class untuk definisi target column definition di masukan ke mana data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a> 
 **/
public abstract class BaseTargetColumnDefinition {
	
	/**
	 * nama column target akhir, di mana data harus di taruh
	 **/
	protected String targetColumnName ; 
	
	
	
	
	
	
	public BaseTargetColumnDefinition(String targetColumnName) {
		super();
		this.targetColumnName = targetColumnName;
	}
	/**
	 * nama column target akhir, di mana data harus di taruh
	 **/
	public void setTargetColumnName(String targetColumnName) {
		this.targetColumnName = targetColumnName;
	}
	/**
	 * nama column target akhir, di mana data harus di taruh
	 **/
	public String getTargetColumnName() {
		return targetColumnName;
	}
	
	
	
	/**
	 * generate statement values. kalau pass throught, maka akan ikut dengan nama variable input
	 **/
	public abstract String generateValueStatment () ; 
	
	 

}
