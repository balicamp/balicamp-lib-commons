package id.co.sigma.commonlib.housekeeper.io;

import id.co.sigma.commonlib.base.ArraySpringBatchJobParameterWrapper;
import id.co.sigma.commonlib.base.IRecieveJDBCMetadataField;
import id.co.sigma.commonlib.housekeeper.HouseKeeperJobParameter;
import id.co.sigma.commonlib.housekeeper.HouseKeeperJobParameters;
import id.co.sigma.commonlib.housekeeper.HouseKeepingConstant;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSetMetaData;
import java.util.List;







import javax.sql.DataSource;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;



/**
 * writer data di archive. data pindahkan ke dalam json parameter dan di zip.
 * format :<br/> 
 * {<br/>
 * sqlStatement : "sql statement dengan parameter, 0, 1 ds",<br/> 
 * parameters:[<br/>
 * ] <br/>
 * <br/>
 * }<br/>
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class HouseKeeperArchiveWriter extends FlatFileItemWriter<JsonElement> implements IRecieveJDBCMetadataField  {

	
	
	
	/**
	 * line delimiter. ini perlu handling with care. karena beda OS bisa beda representasi
	 **/
	private String lineDelimiter ="\n"; 
	
	/**
	 * template insert into statement
	 **/
	private String insertIntoTemplate ; 
	
	
	/**
	 * flag, header sudah di tulis atau tidak
	 **/
	private boolean firstLineWrited  ; 
	

	/**
	 * array of json placer.ini sesuai dengan tipe data
	 **/
	@SuppressWarnings("rawtypes")
	private JSONPlacerWorker[] jsonPlacer ; 
	
	
	
	
	private JsonArray columnMapArray  ; 
	
	
	
	private DataSource dataSource ; 
	
	
	/**
	 * reference ke processor
	 **/
	private IHouseKeeperProcessor houseKeeperProcessor ; 
	
	/**
	 * writer reference. di keep agar bisa nulis header. header baru bisa di tulis kalau kita sudah mendapatkan metadata. metadata baru di dapat pada method : {@link #onRecieveMetadata(ResultSetMetaData)}
	 **/
	private Writer writerReference ; 
	
	
	
	public HouseKeeperArchiveWriter(){
		super(); 
		setLineSeparator(" ");
		setLineAggregator(new LineAggregator<JsonElement>() {
			@Override
			public String aggregate(JsonElement item) {
				String retval = (firstLineWrited?"," :""  ) +   item.toString()  + lineDelimiter ;
				firstLineWrited = true ; 
				return retval ; 
			}
		});
		
		this.setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writerReference = writer  ;// keep saja reference
				
			}
		});
		
		setFooterCallback(new FlatFileFooterCallback() {
			@Override
			public void writeFooter(Writer writer) throws IOException {
				writer.write(lineDelimiter +"]}");
			}
		});
	}
	
	 
	@Override
	public void write(List<? extends JsonElement> items) throws Exception {
		eraseData();
		super.write(items);
	}
	
	
	
	
	
	/**
	 * woker untuk menghapus table
	 **/
	private void eraseData (  ){
		
		JdbcTemplate tmpl = new JdbcTemplate(dataSource);
		
		String delSmt = "DELETE FROM " + houseKeeperProcessor.getHouseKeeperJobParameter().getTableName() + " WHERE " + houseKeeperProcessor.getWhereStatement()  ; 
		System.out.println(delSmt);
		
		
		tmpl.update( delSmt, houseKeeperProcessor.getEraseParameterDataTypes() , houseKeeperProcessor.getSqlDataTypeKey());
		houseKeeperProcessor.resetDeleteConstructor(); 
	}
	
	public static void main (String[] arg){
		JsonObject jso = new JsonObject(); 
		String someSortOfHellString ="insert into \"---\n-\"";
		jso.addProperty("insertIntoStatement",someSortOfHellString );
		System.out.println(jso.toString().replaceAll("\\}", ""));
	}
	
	
	@Override
	public void onRecieveMetadata(ResultSetMetaData resultsetMetata)  throws Exception{
		jsonPlacer = new JSONPlacerWorker[resultsetMetata.getColumnCount()];
		columnMapArray = new JsonArray(); 
		for ( int i = 1 ; i<= jsonPlacer.length ; i++){
			jsonPlacer[i-1] = JSONPlacerWorkerManager.getInstance().getJSONPlacer(  resultsetMetata.getColumnClassName(i));
			
			JsonObject a = new JsonObject(); 
			a.addProperty(HouseKeepingConstant.SHORT_DETAIL_KEY_PREFIX + (i-1) , resultsetMetata.getColumnName(i).toUpperCase());
			columnMapArray.add(a);
		}
		renderHeaderJson(jsonPlacer, resultsetMetata);
	}
	 
	@BeforeStep
	protected void beforeStepHandler( StepExecution stepExecution ) throws Exception {
		
		StringBuffer strLogger = new StringBuffer(); 
		
		try{
			HouseKeeperJobParameter param =  (new HouseKeeperJobParameters()).instantiateParameter(stepExecution);
			strLogger.append("nama table : " + param.getTableName() + ", ");
			strLogger.append("insert into  : " + param.getInsertIntoStatment() + ", ");
			strLogger.append("seq no  : " + param.getSequenceNumber() + ", ");
			
			
			
			String pathTmp = stepExecution.getJobParameters().getString(HouseKeepingConstant.OUTPUT_FILE_DIRECTORY_PATH)  + File.separator + param.getTableName() +".json" ; 
			setResource(new FileSystemResource(pathTmp));
			insertIntoTemplate = param.getInsertIntoStatment();
			firstLineWrited = false ;
		}finally {
			System.out.println(strLogger);
			logger.debug(strLogger);
		}
		
		
	}
	
	
	
	
	/**
	 *menulis header data. sql statement , parameter type. ini akan menulus<br/>
	 *{ \"insertIntoStatement\":\n
	 *id.co.sigma.commonlib.housekeeper.HouseKeepingConstant.SERIALIZED_FIELD_TYPE_KEY(value)
	 *
	 **/
	protected void renderHeaderJson ( @SuppressWarnings("rawtypes") JSONPlacerWorker[] jsonDefinition , ResultSetMetaData resultsetMetata) throws Exception{
		JsonObject h = new JsonObject();
		h.addProperty(HouseKeepingConstant.INSERT_INTO_JSON_KEY, insertIntoTemplate);
		JsonArray paramDefHolder = new JsonArray(); 
		h.add(HouseKeepingConstant.SERIALIZED_FIELD_TYPE_KEY, paramDefHolder);
		h.add(HouseKeepingConstant.DETAIL_MAPPER_KEY, columnMapArray);
		int i=1 ; 
		for ( JSONPlacerWorker<?> scn : jsonDefinition){
			int colTypeSql =  resultsetMetata.getColumnType(i); 
			i++; 
			paramDefHolder.add(new JsonPrimitive(colTypeSql/*scn.getSerializationTypeCode()*/));
		}
		String txt =  h.toString() ;
		txt = txt.substring(0, txt.length()-1);
		writerReference.write(txt);
		writerReference.write(lineDelimiter);
		writerReference.write(",\"" + HouseKeepingConstant.DATA_DETAIL_KEY +"\":[" +lineDelimiter);
	}
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource ;
	}
	
	/**
	 * reference ke processor
	 **/
	public IHouseKeeperProcessor getHouseKeeperProcessor() {
		return houseKeeperProcessor;
	}
	/**
	 * reference ke processor
	 **/
	public void setHouseKeeperProcessor(
			IHouseKeeperProcessor houseKeeperProcessor) {
		this.houseKeeperProcessor = houseKeeperProcessor;
	}
}
