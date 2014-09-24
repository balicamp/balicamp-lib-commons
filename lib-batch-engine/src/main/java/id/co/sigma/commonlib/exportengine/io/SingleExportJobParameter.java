package id.co.sigma.commonlib.exportengine.io;

   

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import id.co.sigma.commonlib.base.BaseSpringBatchJobParameterWrapper;
import id.co.sigma.commonlib.base.SQLParameterHolder;
import id.co.sigma.commonlib.base.SQLParameterHolderArray;
import id.co.sigma.commonlib.base.serializer.SendToParameterField;

/**
 * single job untuk export
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class SingleExportJobParameter extends BaseSpringBatchJobParameterWrapper{

	
	
	private static final Logger logger = LoggerFactory.getLogger(SingleExportJobParameter.class); 
	
	private static final String CONVERTERS_SIZE_KEY="converterCount"; 
	
	
	
	
	
	/**
	 * select statment 
	 */
	@SendToParameterField(key="selectSqlStatment")
	protected String selectSqlStatment ;
	
	
	
	/**
	 * format default Date
	 **/
	@SendToParameterField(key="defaultDatePattern")
	protected String defaultDateFormatterPattern ="yyyy-MM-dd"; 
	
	
	/**
	 * target output path untuk 
	 */
	@SendToParameterField(key="targetOutputPath")
	protected String targetOutputPath ; 
	
	
	/**
	 * delimiter dari data apa
	 */
	@SendToParameterField(key="dataDelimiter")
	protected String delimiter = ";"; 
	
	
	/**
	 * parameters untuk array
	 */
	protected SQLParameterHolder [] sqlParameters ;
	
	
	/**
	 * converter untuk konversi dari object menjadi string
	 */
	protected NamedConverter [] converters; 
	
	
	
	/**
	 * nama bean yang handle job
	 */
	@SendToParameterField(key=id.co.sigma.commonlib.base.JobListenerPropagator.JOB_LISTENER_BEAN_NAME_KEY)
	protected String jobListenerBeanName ; 
	
	
	
	public SingleExportJobParameter( ){
		
		
	}
	
	
	
	
	/**
	 * select statment 
	 */
	public void setSelectSqlStatment(String selectSqlStatment) {
		this.selectSqlStatment = selectSqlStatment;
	}
	/**
	 * select statment 
	 */
	public String getSelectSqlStatment() {
		return selectSqlStatment;
	}
	
	
	/**
	 * converter untuk konversi dari object menjadi string
	 */
	public NamedConverter[] getConverters() {
		return converters;
	}
	/**
	 * converter untuk konversi dari object menjadi string
	 */
	public void setConverters(NamedConverter[] converters) {
		this.converters = converters;
	}
	
	@Override
	public void fetchAdditionalData(JobParameters params) {
		super.fetchAdditionalData(params);
		long count = params.getLong(CONVERTERS_SIZE_KEY); 
		int cntInt = ((Long)count).intValue();
		if ( cntInt>0){
			converters = new NamedConverter[cntInt]; 
			for ( int i = 0 ; i < cntInt; i++){
				converters[i] = new NamedConverter(); 
				converters[i].setSequenceNumber(i);
				try {
					converters[i].fetchParameterFromJobParameters(params);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		// array of params 
		SQLParameterHolderArray paramQuerys = new SQLParameterHolderArray(); 
		try {
			paramQuerys.fetchParameterFromJobParameters(params);
			this.sqlParameters = paramQuerys.getParameterArray();  
		} catch (Exception e) {
			logger.error("gagal membaca data parameter dari job parameter. error : "  + e.getMessage()  ,e);
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void putAdditionaFields(JobParametersBuilder jobParameterBuilder)
			throws Exception {
		super.putAdditionaFields(jobParameterBuilder);
		if (converters== null ){
			jobParameterBuilder.addLong(CONVERTERS_SIZE_KEY, 0L) ; 
		}else{
			jobParameterBuilder.addLong(CONVERTERS_SIZE_KEY, (long)converters.length) ;
			int i=0; 
			for (NamedConverter scn : converters){
				scn.setSequenceNumber(i++);
				scn.putParameterToParameterBuilder(jobParameterBuilder);
			}
		}
		
		
		if ( this.sqlParameters!= null&& sqlParameters.length>0){
			SQLParameterHolderArray arr = new SQLParameterHolderArray(); 
			arr.putParameters(sqlParameters);
			arr.putParameterToParameterBuilder(jobParameterBuilder);
		}
		
		
	}
	/**
	 * format default Date
	 **/
	public void setDefaultDateFormatterPattern(
			String defaultDateFormatterPattern) {
		this.defaultDateFormatterPattern = defaultDateFormatterPattern;
	}
	/**
	 * format default Date
	 **/
	public String getDefaultDateFormatterPattern() {
		return defaultDateFormatterPattern;
	}


	/**
	 * target output path untuk 
	 */
	public String getTargetOutputPath() {
		return targetOutputPath;
	}



	/**
	 * target output path untuk 
	 */
	public void setTargetOutputPath(String targetOutputPath) {
		this.targetOutputPath = targetOutputPath;
	}
	
	
	/**
	 * delimiter dari data apa
	 */
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	/**
	 * delimiter dari data apa
	 */
	public String getDelimiter() {
		return delimiter;
	}




	public SQLParameterHolder[] getSqlParameters() {
		return sqlParameters;
	}




	public void setSqlParameters(SQLParameterHolder[] sqlParameters) {
		this.sqlParameters = sqlParameters;
	}
	
	/**
	 * nama bean yang handle job
	 */
	public void setJobListenerBeanName(String jobListenerBeanName) {
		this.jobListenerBeanName = jobListenerBeanName;
	}
	/**
	 * nama bean yang handle job
	 */
	public String getJobListenerBeanName() {
		return jobListenerBeanName;
	}
	
}
