package id.co.sigma.commonlib.journal.engine.io.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import id.co.sigma.commonlib.journal.engine.IDataPrimaryKeyProvider;
import id.co.sigma.commonlib.journal.engine.IJournalConfigurationAware;
import id.co.sigma.commonlib.journal.engine.data.JournalDetail;
import id.co.sigma.commonlib.journal.engine.data.JournalHeader;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;



/**
 * writer journal header. pekerjaan nya di sini : 
 * <ol>
 * <li>set ID dari journal Header</li>
 * <li>set id journal header pada child item</li>
 * </ol>
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a> 
 **/
public class JournalHeaderItemWriter extends JdbcBatchItemWriter<JournalHeader>  implements IJournalConfigurationAware {
	
	
	
	/**
	 * data sequence provider
	 **/
	@Autowired
	private IDataPrimaryKeyProvider dataPrimaryKeyProvider ; 
	
	
	private DataSource dataSource ; 
	
	public JournalHeaderItemWriter(){
		super(); 
		setSql("insert into " + JournalHeader.TABLE_NAME + 
				"( jrnh_id , rule_id , jrnh_ttl_d , jrnh_ttl_c ," +
				" jrnh_acct_date , jrnh_period_code , " +
				" jrnh_unit_code , jrnh_reff_no , additional_data1 , " +
				" additional_data2 , batch_proccess_id ) " +
				" values (/*1- jrnh_id*/? , /*2- rule_id*/? , /*3- jrnh_ttl_d*/? , " +
				"/*4- jrnh_ttl_c*/? , /*5- jrnh_acct_date*/? , /*6- jrnh_period_code*/? , " +
				"/*7- jrnh_unit_code*/? ,/*8- jrnh_reff_no*/ ? , /*9- additional_data1*/? ,/*10-additional_data2*/ ? , " +
				"/*11-batch_proccess_id*/?)");
		setItemPreparedStatementSetter(new ItemPreparedStatementSetter<JournalHeader>() {
			@Override
			public void setValues(JournalHeader headData, PreparedStatement prepSmt)
					throws SQLException {
				prepSmt.setObject(1, headData.getId()); 
				prepSmt.setObject(2, headData.getJournalRuleId());
				prepSmt.setBigDecimal(3, headData.getTotalDebet()); 
				prepSmt.setObject(4, headData.getTotalCredit()); 
				prepSmt.setObject(5, headData.getAccountingDate()); 
				prepSmt.setObject(6, headData.getAccountingPeriodCode()); 
				prepSmt.setObject(7 , headData.getBranchCode()); 
				prepSmt.setObject(8 , headData.getReffNumber());
				prepSmt.setString(9 , headData.getAdditionalData1());
				prepSmt.setString(10 , headData.getAdditionalData2());
				prepSmt.setLong(11 , headData.getBatchGroupId());
			}
		}); 
	}
	
	
	@Override
	public void write(List<? extends JournalHeader> headers) throws Exception {
		//FIXME: set sequence dari header disini
		if ( headers!=null&& !headers.isEmpty()){
			//STEP1 : set sequence 
			Long swap =  dataPrimaryKeyProvider.getDataKey(JournalHeader.TABLE_NAME, headers.size());
			long idStart = swap==null? 1: swap.longValue(); 
			ArrayList<JournalDetail> detailAs1Array = new ArrayList<JournalDetail>();
			for ( JournalHeader scn : headers){
				scn.setId(idStart); 
				idStart++; 
				if ( scn.getDetails()==null||scn.getDetails().isEmpty())
					continue ;
				for ( JournalDetail det : scn.getDetails()){
					det.setHeaderId(scn.getId());
					detailAs1Array.add(det);
				}
			}
			//STEP2: tulis haader
			super.write(headers);
			
			 
			//STEP3: kirim ke  
			writeJournalDetails(detailAs1Array); 
			// setelah menjadi 1 array, kita set array nya
		}
		 
		
		
		
		
		
	}
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource ;
		super.setDataSource(dataSource);
		
	}
	
	
	
	/**
	 * writer ke journal details
	 **/
	private void writeJournalDetails (List<JournalDetail> details) throws Exception{
		if ( details==null||details.isEmpty())
			return ; 
		JdbcTemplate tmpl = new JdbcTemplate(dataSource);
		Long swap = dataPrimaryKeyProvider.getDataKey(JournalDetail.TABLE_NAME, details.size()); 
		long idData = swap==null? 1 : swap.longValue(); 
		for ( JournalDetail d : details){
			d.setId(idData); 
			idData++ ; 
			tmpl.update("insert into " + 
			JournalDetail.TABLE_NAME+"(jrnd_id , jrnh_id , jrnd_dc , jrnd_amount , jrnd_gl_code , jrnd_seq_no ) " +
					" values (? , ? , ? , ? , ? , ?) " , 
					d.getId() , d.getHeaderId() , d.getGlPositionCode() , d.getAmount() , d.getGlCode() , d.getSequenceNumber()); 
		}
		
	}
	
	
	
	private Long journalConfigurationId; 
	
	
	@Override
	public void setJournalConfigurationId(Long journalConfigurationId) {
		this.journalConfigurationId = journalConfigurationId ; 
		
	}
	
	public Long getJournalConfigurationId() {
		return journalConfigurationId;
	}

}
