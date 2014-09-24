package id.co.sigma.commonlib.journal.engine.io.reader;

import java.util.List;

import id.co.sigma.commonlib.journal.engine.JournalConstant;
import id.co.sigma.commonlib.journal.engine.data.JournalDetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class JournalDetailItemReader implements ItemReader<JournalDetail>{

	
	
	/**
	 * logger
	 **/
	private static final Logger logger = LoggerFactory.getLogger(JournalDetailItemReader.class);
	
	
	
	/**
	 * Job execution , di inject oleh batch infrastructure, melalui methode {@link #plugStepExecution(StepExecution)}
	 **/
	private StepExecution stepExecution;
	
	@Override
	public JournalDetail read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		ExecutionContext stepContext = this.stepExecution.getExecutionContext();
		Object swap =  stepContext.get(JournalConstant.JOURNAL_DETAIL_VARIABLE_KEY);
		if(swap==null){
			logger.debug("tidak ada data detil di temukan dalam execution context."); 
			return null ; 
		}
		if ( !( swap instanceof List)){
			logger.debug("step execution dengan key : " + JournalConstant.JOURNAL_DETAIL_VARIABLE_KEY + " , bukan instance : " + List.class.getName()  +",proses tidak di teruskan ");
			return null ; 
		}
		@SuppressWarnings("unchecked")
		List<JournalDetail> dets = (List<JournalDetail>)swap ;
		if (dets.isEmpty())
			return null ; 
		JournalDetail w = dets.get(0); 
		dets.remove(0);
		return w;
	}
	
	@BeforeStep
    public void plugStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}
