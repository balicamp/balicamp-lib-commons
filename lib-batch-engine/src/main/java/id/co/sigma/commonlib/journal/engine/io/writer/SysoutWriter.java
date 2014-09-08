package id.co.sigma.commonlib.journal.engine.io.writer;

import java.util.List;

import id.co.sigma.commonlib.journal.engine.data.JournalDetail;
import id.co.sigma.commonlib.journal.engine.data.JournalHeader;

import org.springframework.batch.item.ItemWriter;

public class SysoutWriter implements ItemWriter<JournalHeader>{

	@Override
	public void write(List<? extends JournalHeader> heads) throws Exception {
		for ( JournalHeader head : heads){
			System.out.println("App Date is :" + head.getApplicationDate() +", acct date is :" + head.getAccountingDate() + 
					",total credit:" +  head.getTotalCredit() + 
					",total debet:" + head.getTotalDebet() );
			if ( head.getDetails()!= null){
				for (JournalDetail det : head.getDetails()){
					System.out.println("GL:" + det.getGlCode() + ",amount: "  + det.getAmount() + ", pos :" + det.getGlPositionCode() );
				}
			}
			
		}
		
	}

	

}
