package id.co.sigma.commonlib.importengine.definition.validator;

import id.co.sigma.commonlib.importengine.definition.validator.comparator.BigDecimalComparator;
import id.co.sigma.commonlib.importengine.definition.validator.comparator.DateComparator;
import id.co.sigma.commonlib.importengine.definition.validator.comparator.ISimpleComparator;
import id.co.sigma.commonlib.importengine.definition.validator.comparator.StringComparator;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;



/**
 * comparator dengan in
 **/
public class SimpleInValidator<DATA> extends BaseSimpleFieldValidator<DATA> {

	private ISimpleComparator<DATA>[] comparators ; 
	private String inDataAsString ;   
	
	
	
	@SuppressWarnings("unchecked")
	public SimpleInValidator( Class<? extends DATA > dataClass ,   DATA[] arr){
		if ( arr==null||arr.length==0){
			comparators= null ; 
			inDataAsString="[]";
		}
		else{
			comparators = (ISimpleComparator<DATA>[])new SimpleInValidator<?>[arr.length];
			inDataAsString="[";
			for ( int i=0;i<arr.length;i++){
				comparators[i] = instantiateComparator(dataClass, arr[i]);
				inDataAsString+="" + arr[i];
			}
			inDataAsString+="]";
		}
			
	}
	
	
	@SuppressWarnings("unchecked")
	public SimpleInValidator( Class<? extends DATA > dataClass ,   Collection<DATA> comparatorDatas){
		if ( comparatorDatas==null||comparatorDatas.isEmpty()){
			comparators= null ; 
			inDataAsString="[]";
		}
		else{
			comparators = (ISimpleComparator<DATA>[])new SimpleInValidator<?>[comparatorDatas.size()];
			int i =0;
			inDataAsString="[";
			for ( DATA scn : comparatorDatas){
				comparators[i] = instantiateComparator(dataClass, scn);
				inDataAsString+="" + scn;
				i++;
			}
			inDataAsString+="]";
		}
			
	}
	@Override
	public boolean validate(DATA data) {
		if(comparators==null||comparators.length==0)
			return false; 
		for ( ISimpleComparator<DATA> scn : comparators){
			if ( CompareResult.equal.getInternalRepresentation()==  scn.compare(data).getInternalRepresentation())
				return true ; 
		}
		return false;
	}

	
	@SuppressWarnings("unchecked")
	protected ISimpleComparator<DATA> instantiateComparator ( Class<? extends DATA > dataClass ,    DATA comparatorValue){
		if ( BigDecimal.class.equals(  dataClass))
			return (ISimpleComparator<DATA>) new BigDecimalComparator((BigDecimal)comparatorValue);
		else if ( Date.class.equals(dataClass))
			return (ISimpleComparator<DATA>) new DateComparator((Date)comparatorValue);
		return (ISimpleComparator<DATA>) new StringComparator((String)comparatorValue);
	}
	
	@Override
	public String generateErrorMessage(DATA data) {
		return "fail to validate :" + data + ", should be in  :" + inDataAsString; 
	}
}
