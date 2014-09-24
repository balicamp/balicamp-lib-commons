package id.co.sigma.commonlib.exportengine.io;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import id.co.sigma.commonlib.base.ExportEngineConstant;
import id.co.sigma.commonlib.exportengine.data.ExportToTextFileMetadata;
import id.co.sigma.commonlib.exportengine.data.IExportToTextFileMetadataProvider;



import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * transformer , dari array of object menjadi array of String. karena target file bentuknya adalah text file
 * di sini akan melibatkan formating thd : 
 * <ol>
 * 	<li>tanggal, pla dari tanggal spt apa</li>
 *  <li>Number, decimal separator apa, berapa angka di belakang koma etc</li>
 * </ol>
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class TableToTextFileProcessor implements ItemProcessor<Object[], String[]>{

	/**
	 * <strong>.</strong>[dot/titik] untuk pemisah desimal 
	 **/
	private boolean useDotDecimalSeparator = true ; 
	
	/**
	 * currency formatter, ini untuk mentrim data, berapa angka dsb
	 **/
	//private DecimalFormat currencyFormatter ; 
	private String currencyFormatterPattern ;
	
	
	
	/**
	 * job context
	 **/
	private ExecutionContext jobContext ; 

	//private SimpleDateFormat dateFormatter; 
	
	
	private String dateFormatterPattern ; 
	 
	/**
	 * penyedia metadata export to file text . 
	 **/
	@Autowired
	private IExportToTextFileMetadataProvider exportToTextFileMetadataProvider ;
	
	
	
	
	
	
	
	
	@Override
	public String[] process(Object[] rawObjectArray) throws Exception {
		if ( rawObjectArray ==null)
			return null; 
		String[] retval = new String[rawObjectArray.length];
		IObjectToStringConverter  converters[] = getConverters() ; 
		for ( int i = 0 ; i < rawObjectArray.length ; i++){
			Object swap =rawObjectArray[i];  
			 
			retval[i] = converters[i].transform(  swap); 
		}
		return retval;
	}
	
	
	
	
	
	/**
	 * id configuration dari file export
	 **/
	public void setConfigurationId(String configurationId) {
		final ExportToTextFileMetadata metaData  =  this.exportToTextFileMetadataProvider.getMetaData(configurationId);
		
		StringBuffer zeroFiller = new StringBuffer(ExportEngineConstant.FORMATTER_CURRENCY_PREFIX); 
		for ( int i=0 ; i< metaData.getNumberOfDecimal() ; i++){
			zeroFiller.append("0");
		}
		//this.currencyFormatter =new DecimalFormat(zeroFiller.toString());
		currencyFormatterPattern = zeroFiller.toString(); 
		
		this.useDotDecimalSeparator = metaData.isUseDotDecimalSeparator() ;
		
		this.dateFormatterPattern =  metaData.getDateFormat() ;
		 
		
	}

	
	
	
	public void beforeProccess () {
		
	}
	
	
	
	
	@BeforeStep
	public void getMyData(StepExecution stepExecution) {
		JobExecution jobExecution = stepExecution.getJobExecution();
		jobContext = jobExecution.getExecutionContext();
		
	}
	
	
	
	
	private IObjectToStringConverter[] cachedConverter ; 
	
	
	
	/**
	 * membaca converters object yang sudah di masukan ke dalam jobcontext oleh reader 
	 **/
	protected IObjectToStringConverter[] getConverters () {
		if(cachedConverter !=null)
			return cachedConverter ; 
		Object swap = jobContext.get(ExportEngineConstant.COLUMN_CONVERTER_KEY); 
		if ( swap==null)
			return null ; 
		OuputDataType[] dtType =(OuputDataType[])swap;
		cachedConverter = new IObjectToStringConverter[dtType.length];
		int i=0 ; 
		for (OuputDataType scn : dtType  ){
			
			cachedConverter[i++] = 
						OuputDataType.commonToString.equals(scn) ? 
						new CommonToStringConverter() :
						(OuputDataType.money.equals(scn) ?  new CurrencyToStringConverter(currencyFormatterPattern, useDotDecimalSeparator) 
						: 
						(
							OuputDataType.date.equals(scn) ? new DateToStringConverter(dateFormatterPattern) : new NoConverter()
						)); 	
		}
		return  cachedConverter ; 
	}
		
}
