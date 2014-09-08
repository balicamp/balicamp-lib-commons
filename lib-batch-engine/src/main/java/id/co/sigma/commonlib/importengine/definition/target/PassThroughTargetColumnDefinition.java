package id.co.sigma.commonlib.importengine.definition.target;

import id.co.sigma.commonlib.importengine.definition.src.BaseFileColumnDefinition;



/**
 * tipe pass through, di telan mentah-mentah dari infput. jadinya tidak ada translasi yang di perlukan 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class PassThroughTargetColumnDefinition extends BaseTargetColumnDefinition{
	
	/**
	 * dari mana data ini di dapatkan
	 **/
	private BaseFileColumnDefinition<?> sourceColumn ; 
	
	
	
	public PassThroughTargetColumnDefinition(String targetColumnName,
			BaseFileColumnDefinition<?> sourceColumn) {
		super(targetColumnName);
		this.sourceColumn = sourceColumn;
	}


	/**
	 * dari mana data ini di dapatkan
	 **/
	public BaseFileColumnDefinition<?> getSourceColumn() {
		return sourceColumn;
	}
	
	
	@Override
	public String generateValueStatment() {
		return ":" + sourceColumn.getColumnOnFileName();
	}

}
