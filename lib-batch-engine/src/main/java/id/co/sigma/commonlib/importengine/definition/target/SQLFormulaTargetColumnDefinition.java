package id.co.sigma.commonlib.importengine.definition.target;



/**
 * 
 * 
 * definisi column target dengan sql formula. Beberapa syarat perlu di penuhi
 * <ol>
 * <li>yang di pergunakan adalam semua statement valid dalam sql. * / + - dan function dalam underlying database.
 * Tergantung apa yang anda pergunakan. </li>
 * 
 * <li>Parameter mempergunakan notasi :, misal variable sumber dari text file di namai <i>AMOUNT</i>, <i>PCT</i> di formulakan menjadi spt ini :AMOUNT*:PCT/100</li>
 * 
 * </ol>
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * 
 **/
public class SQLFormulaTargetColumnDefinition extends BaseTargetColumnDefinition{

	/**
	 * 
	 * formula untuk SQL  
	 **/
	private String formula ; 
	
	
	
	public SQLFormulaTargetColumnDefinition(String targetColumnName,
			String formula) {
		super(targetColumnName);
		this.formula = formula;
	}



	@Override
	public String generateValueStatment() {
		return formula;
	}


	

	
}
