package id.co.sigma.commonlib.importengine.data;



/**
 * definisi target
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class MappingTargetColumnDefinitionData {
	
	

	/**
	 * column tujuan dari data
	 **/
	private String targetColumnName ;
	
	
	/**
	 * formula dari mana dat adi dapat
	 **/
	private String formula ; 
	
	
	
	
	
	public MappingTargetColumnDefinitionData(){}
	
	
	
	public MappingTargetColumnDefinitionData(String targetColumnName
			) {
		super();
		this.targetColumnName = targetColumnName;
	}



	
	
	public MappingTargetColumnDefinitionData(String targetColumnName,
			String formula) {
		super();
		this.targetColumnName = targetColumnName;
		this.formula = formula;
	}



	/**
	 * column tujuan dari data
	 **/
	public String getTargetColumnName() {
		return targetColumnName;
	}
	/**
	 * column tujuan dari data
	 **/
	public void setTargetColumnName(String targetColumnName) {
		this.targetColumnName = targetColumnName;
	}
	
	
	
	/**
	 * formula dari mana dat adi dapat
	 **/
	public String getFormula() {
		return formula;
	}
	/**
	 * formula dari mana dat adi dapat
	 **/
	public void setFormula(String formula) {
		this.formula = formula;
	}
	

}
