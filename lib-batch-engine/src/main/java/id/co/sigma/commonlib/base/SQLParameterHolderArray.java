package id.co.sigma.commonlib.base;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.BeansException;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class SQLParameterHolderArray  implements IJobParameterSerilzable, Iterable<SQLParameterHolder>{

	
	private ArrayList<SQLParameterHolder> actualDataHolder = new ArrayList<SQLParameterHolder>(); 
	
	
	public static final String SQL_PARAMETER_SIZE_KEY="QUERY_PARAMETER_SIZE";
	
	
	
	public void putParameters (SQLParameterHolder[] params){
		actualDataHolder.clear(); 
		if ( params== null||params.length==0)
			return ; 
		for  (SQLParameterHolder scn : params ){
			actualDataHolder.add(scn); 
		}
	}
	
	
	
	public void putParameters ( Collection< SQLParameterHolder> params){
		actualDataHolder.clear(); 
		if ( params== null||params.isEmpty())
			return ; 
		for  (SQLParameterHolder scn : params ){
			actualDataHolder.add(scn); 
		}
	}
	
	@Override
	public void fetchParameterFromJobParameters(JobParameters params)
			throws IllegalArgumentException, IllegalAccessException,
			BeansException, InvocationTargetException {
		actualDataHolder.clear(); 
		long ttl = params.getLong(SQL_PARAMETER_SIZE_KEY);
		int ttlint = (int)ttl; 
		for ( int i=0 ; i< ttlint; i++){
			SQLParameterHolder a = new SQLParameterHolder(); 
			a.setJobParameterIndexNumber(i);
			a.fetchParameterFromJobParameters(params);
			actualDataHolder.add(a); 
		}
		
	}

	@Override
	public void putParameterToParameterBuilder(
			JobParametersBuilder jobParameterBuilder) throws Exception {
		jobParameterBuilder.addLong(  SQL_PARAMETER_SIZE_KEY  ,(long)actualDataHolder.size() );
		int i=0 ; 
		for ( SQLParameterHolder scn : actualDataHolder){
			scn.setJobParameterIndexNumber(i++);
			scn.putParameterToParameterBuilder(jobParameterBuilder);
		}
	}

	@Override
	public Iterator<SQLParameterHolder> iterator() {
		return this.actualDataHolder.iterator();
	}
	
	public SQLParameterHolder[] getParameterArray () {
		if ( actualDataHolder.isEmpty())
			return null ;
		SQLParameterHolder[] retval = new SQLParameterHolder[actualDataHolder.size()];
		actualDataHolder.toArray(retval); 
		return retval ; 
	}

}
