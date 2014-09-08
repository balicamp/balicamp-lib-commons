package id.co.sigma.commonlib.housekeeper.io;


import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.sql.DataSource;

import id.co.sigma.commonlib.base.IRecieveJDBCMetadataField;
import id.co.sigma.commonlib.housekeeper.HouseKeeperJobParameter;
import id.co.sigma.commonlib.housekeeper.HouseKeeperJobParameters;
import id.co.sigma.commonlib.housekeeper.HouseKeepingConstant;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

import com.google.gson.JsonObject;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class HouseKeeperArchiveProcessor implements ItemProcessor<Object[]	, JsonObject> , IRecieveJDBCMetadataField , IHouseKeeperProcessor{

	
	
	
	/**
	 * array of nama params . ini apa yang akan di masukan ke dalam json
	 **/
	private String[] paramNames ; 
	
	
	
	
	/**
	 * array of json placer.ini sesuai dengan tipe data
	 **/
	private JSONPlacerWorker[] jsonPlacer ; 
	
	
	
	/**
	 * total line yang di proses
	 **/
	private int lineReaded ; 
	
	
	private StringBuffer inStatment = new StringBuffer(); 
	
	
	
	
	/**
	 * in template proses delete
	 **/
	private String inTemplate ; 
	
	private int ttl ; 
	
	
	
	
	/**
	 * parameter job. di cache
	 **/
	HouseKeeperJobParameter jobParam ; 
	
	
	
	private int[] indexOfPKOnRawData ; 
	
	private int[] dataTypeArray ; 
	
	 
	
	private ArrayList<Object> deleteParameters  =new ArrayList<Object>(); 
	
	@Override
	public JsonObject process(Object[] item) throws Exception {
		JsonObject a = new JsonObject(); 
		for ( int i = 0 ; i <ttl ; i++ ){
			jsonPlacer[i].put(a, paramNames[i], item[i]);
		}
		lineReaded++;
		if ( inStatment.length()>0)
			inStatment.append(" OR ");
		inStatment.append(inTemplate);
		for ( int scn :indexOfPKOnRawData ){
			deleteParameters.add(item[scn]);
		}
		
		
		return a;
	}

	@Override
	public void onRecieveMetadata(ResultSetMetaData resultsetMetata)  throws Exception{
		int size = resultsetMetata.getColumnCount() ; 
		jsonPlacer = new JSONPlacerWorker[size];
		paramNames = new String[size];
		for ( int i = 1 ; i<= size ; i++){
			String columnName = resultsetMetata.getColumnClassName(i) ; 
			jsonPlacer[i-1] = JSONPlacerWorkerManager.getInstance().getJSONPlacer(  columnName );
			paramNames[i-1] = HouseKeepingConstant.SHORT_DETAIL_KEY_PREFIX + (i-1) ;// resultsetMetata.getColumnName(i).toUpperCase();
		}
		ttl = resultsetMetata.getColumnCount(); 
		lineReaded= 0 ; 
		inStatment = new StringBuffer(); 
		deleteParameters.clear();
		indexOfPKOnRawData = new int[jobParam.getPrimaryKeyColumns() .length];
		dataTypeArray = new int[indexOfPKOnRawData.length];
		int idx = 0 ; 
		for ( String pk :jobParam.getPrimaryKeyColumns() ){
			
			for ( int i = 1 ; i<= size ; i++){
				if ( pk.equalsIgnoreCase(resultsetMetata.getColumnName(i))){
					indexOfPKOnRawData[idx] = i-1 ; 
					dataTypeArray[idx] = resultsetMetata.getColumnType(i);
					idx++; 
					break ; 
				}
			}
			
		}
	}

	
	
	@BeforeStep
	protected void beforeStepHandler( StepExecution stepExecution ) throws Exception {
		jobParam =    (new HouseKeeperJobParameters()).instantiateParameter(stepExecution);
		String[] pks = jobParam.getPrimaryKeyColumns();
		StringBuffer pkBuilder = new StringBuffer(); 
		for ( String scn : pks){
			if ( pkBuilder.length()>0)
				pkBuilder.append("AND ");
			pkBuilder.append("(");
			pkBuilder.append(scn);
			pkBuilder.append("=?");
			pkBuilder.append(")");
		}
		inTemplate = pkBuilder.toString(); 
		
		
	}
	
	
	@Override
	public String getWhereStatement() {
		return inStatment.toString();
	}

	@Override
	public Object[] getEraseParameterDataTypes() {
		return this.deleteParameters.toArray();
	}

	@Override
	public int[] getSqlDataTypeKey() {
		int cntPK =  dataTypeArray.length; 
		
		int[] retval = new int[lineReaded *cntPK];
		for ( int i = 0 ; i< lineReaded;i++){
			int j = (i*cntPK)  ;  
			for ( int scn : dataTypeArray){
				retval[j] = scn;
				j++;
			}
		}
		return retval;
	}

	@Override
	public void resetDeleteConstructor() {
		lineReaded = 0 ; 
		inStatment = new StringBuffer(); 
		deleteParameters.clear(); 
	}
	
	@Override
	public HouseKeeperJobParameter getHouseKeeperJobParameter() {
		return this.jobParam;
	}
	

}
