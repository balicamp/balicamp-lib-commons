package id.co.sigma.commonlib.importengine.definition.validator;

import id.co.sigma.commonlib.importengine.definition.validator.comparator.BigDecimalComparator;
import id.co.sigma.commonlib.importengine.definition.validator.comparator.DateComparator;
import id.co.sigma.commonlib.importengine.definition.validator.comparator.ISimpleComparator;

import java.math.BigDecimal;
import java.util.Date;




/**
 * validator dengan sign &lt;
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class SimpleLessEqualValidator<DATA> extends BaseSimpleFieldValidator<DATA>{

	private ISimpleComparator<DATA> comparator ; 
	
	private DATA comparatorData ;
	
	@SuppressWarnings("unchecked")
	public SimpleLessEqualValidator(Date comparatorValue){
		super(); 
		comparatorData = (DATA) comparatorValue ;
		comparator = (ISimpleComparator<DATA>) new DateComparator(comparatorValue); 
	}
	
	@SuppressWarnings("unchecked")
	public SimpleLessEqualValidator(BigDecimal comparatorValue){
		super();  
		comparatorData = (DATA) comparatorValue ;
		comparator = (ISimpleComparator<DATA>) new BigDecimalComparator(comparatorValue); 
	}
	
	
	
	
	@Override
	public boolean validate(DATA data) {
		CompareResult rsl =  comparator.compare(data); 
		return CompareResult.less.getInternalRepresentation()==rsl.getInternalRepresentation()||CompareResult.equal.getInternalRepresentation()==rsl.getInternalRepresentation();
	}
	
	@Override
	public String generateErrorMessage(DATA data) {
		return "fail to validate :" + data + ", should be less or equal  :" + comparatorData; 
	}

}
