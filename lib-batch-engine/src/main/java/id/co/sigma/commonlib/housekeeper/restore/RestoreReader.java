package id.co.sigma.commonlib.housekeeper.restore;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.google.gson.stream.JsonReader;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class RestoreReader implements ItemReader<Map<String, Object>>{

	
	
	private  JsonReader reader ; 
	
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RestoreReader.class);
	
	private RestoreParameterDetail parameterDetail ; 
	
	
	
	
	
	 
	
	@Override
	public Map<String, Object> read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		if ( !reader.hasNext())
			return null ; 
		Map<String, Object> retval = new HashMap<String, Object>(); 
		reader.beginObject();
		ArrayList<IJSONFieldFetcher> fetchers =  parameterDetail.getFieldFetchers();
		int i = 0 ; 
		while(reader.hasNext()){
			String oldKey =  reader.nextName() ; 
			Object val =  fetchers.get(i).getCurrentObjectAndPutToMap(reader   ) ;
			retval.put(parameterDetail.getShortVersusActualFields().get(oldKey), val);
			i++; 
		}
		
		
		reader.endObject();
		 
		return retval ; 
	}
	
	@AfterStep
	protected void afterStepHandler( StepExecution stepExecution ) throws Exception{
		//reader.endArray();
		//reader.endObject();
		if ( reader!= null)
			reader.close(); 
	}
	
	@BeforeStep
	public void beforeStepHandler( StepExecution stepExecution ) throws Exception {
		
		RestoreParameterDetail  p = (RestoreParameterDetail)stepExecution.getJobExecution().getExecutionContext().get(RestoreParameterHeader.CURRENT_RESTORE_PARAM_KEY);
		parameterDetail = p; 
		reader = new JsonReader(new FileReader(p.getFilePathToLoad()));
		
		
		 
		
		reader.beginObject();
		
		reader.nextName(); 
		String sqlInsert = reader.nextString(); 
		System.out.println(sqlInsert ); 
		
		System.out.println(  reader.nextName()); 
		reader.beginArray();
		//fieldTypes
		while( reader.hasNext()){
			System.out.println(  reader.nextInt()); 
		}
		reader.endArray();
		//fieldMapper
		System.out.println(reader.nextName());
		reader.beginArray(); 
		while( reader.hasNext()){
			reader.beginObject(); 
			System.out.println(  reader.nextName() +":" +  reader.nextString());  
			reader.endObject(); 
		}
		reader.endArray(); 
		reader.nextName(); 
		//details
		reader.beginArray(); 
	}
	
	
	
	

}
