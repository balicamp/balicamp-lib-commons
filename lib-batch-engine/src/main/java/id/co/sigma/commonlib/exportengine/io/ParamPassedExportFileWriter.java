package id.co.sigma.commonlib.exportengine.io;


import id.co.sigma.commonlib.base.ICustomJobExecutionListener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.PassThroughFieldExtractor;
import org.springframework.core.io.FileSystemResource;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ParamPassedExportFileWriter extends FlatFileItemWriter<String[]>{

	private static final Logger logger = LoggerFactory.getLogger(ParamPassedExportFileWriter.class); 
	
	private SingleExportJobParameter exportJobParameter;
	
	PassThroughFieldExtractor<String[]> fieldExtractor;  
	
	DelimitedLineAggregator<String[]>   lineAggregator ; 
	public ParamPassedExportFileWriter(){
		super(); 
		lineAggregator = new DelimitedLineAggregator<String[]>(); 
		fieldExtractor = new PassThroughFieldExtractor<String[]>();
		setLineAggregator(lineAggregator);

		lineAggregator.setFieldExtractor(fieldExtractor);
	}
	
	
	private StepExecution stepExecution ;

	@BeforeStep
	public void beforeStepHandler( StepExecution stepExecution ) throws Exception {
		exportJobParameter = new SingleExportJobParameter();
		exportJobParameter.fetchParameterFromJobParameters(stepExecution.getJobParameters());
		setResource(new FileSystemResource(exportJobParameter.getTargetOutputPath()));
		
		
		
		lineAggregator.setDelimiter(exportJobParameter.getDelimiter());
		stepExecution.getExecutionContext().putInt(ICustomJobExecutionListener.OUTPUT_WRITED_COUNT_KEY, 0);
		this.stepExecution = stepExecution ;  
	}
	
	
	@Override
	public void write(List<? extends String[]> items) throws Exception {
		
		if ( items != null && !items.isEmpty()){
			int current = 0 ; 
			try{
				current =  stepExecution.getJobExecution().getExecutionContext().getInt(ICustomJobExecutionListener.OUTPUT_WRITED_COUNT_KEY);
			}catch ( Exception exc){
				logger.error("gagal membaca data dengan key : " + ICustomJobExecutionListener.OUTPUT_WRITED_COUNT_KEY + ", error  : "  +exc.getMessage() );
			}
			current = current +items.size(); 
			stepExecution.getJobExecution().getExecutionContext().putInt(ICustomJobExecutionListener.OUTPUT_WRITED_COUNT_KEY, current);
		}
		super.write(items);
	}
}
