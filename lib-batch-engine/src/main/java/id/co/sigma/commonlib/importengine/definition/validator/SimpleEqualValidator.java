package id.co.sigma.commonlib.importengine.definition.validator;

import id.co.sigma.commonlib.importengine.definition.validator.comparator.BigDecimalComparator;
import id.co.sigma.commonlib.importengine.definition.validator.comparator.DateComparator;
import id.co.sigma.commonlib.importengine.definition.validator.comparator.ISimpleComparator;
import id.co.sigma.commonlib.importengine.definition.validator.comparator.StringComparator;

import java.math.BigDecimal;
import java.util.Date;



/**
 * validator equal
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class SimpleEqualValidator<DATA> extends BaseSimpleFieldValidator<DATA>{
	
	
	private ISimpleComparator<DATA> comparator ; 
	
	private DATA comparatorData ;  
	
	@SuppressWarnings("unchecked")
	public SimpleEqualValidator(Date comparatorValue){
		super(); 
		comparatorData = (DATA) comparatorValue ;
		comparator = (ISimpleComparator<DATA>) new DateComparator(comparatorValue); 
	}
	
	@SuppressWarnings("unchecked")
	public SimpleEqualValidator(BigDecimal comparatorValue){
		super();  
		comparatorData = (DATA) comparatorValue ;
		comparator = (ISimpleComparator<DATA>) new BigDecimalComparator(comparatorValue); 
	}
	
	@SuppressWarnings("unchecked")
	public SimpleEqualValidator(String comparatorValue){
		super(); 
		comparatorData = (DATA) comparatorValue ;
		comparator = (ISimpleComparator<DATA>) new StringComparator(comparatorValue); 
	}
	
	
	

	@Override
	public boolean validate(DATA data) {
		CompareResult rsl =  comparator.compare(data); 
		return CompareResult.equal.getInternalRepresentation()==rsl.getInternalRepresentation();
	}
	
	@Override
	public String generateErrorMessage(DATA data) {
		return "fail to validate :" + data + ", should be equal :" + comparatorData; 
	}
	

}
