package id.co.sigma.commonlib.journal.metadata;

import id.co.sigma.commonlib.journal.engine.data.JournalDetail;




/**
 * 
 * 
 * journal rule meta data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class JournalRuleDetail {
	
	
	/**
	 * nama column dari source column. jadinya data gl di ambil dari mana
	 **/
	private String valueSourceColumn ; 
	
	
	
	
	/**
	 * journal rule mapper. kalau di perlukan proses translasi
	 **/
	private JournalRuleMapper journalRuleMapper  ; 
	
	
	
	
	
	/**
	 * Kode GL. ini kalau metode rule statis, kalau ini null , berarti yang di perhitungkan adalah variable {@link #journalRuleMapper}
	 **/
	private String glCode ; 
	
	
	/**
	 * posisi debet/credit dari GL
	 **/
	private String glPositionCode ; 

	/**
	 * journal rule mapper. kalau di perlukan proses translasi
	 **/
	public void setJournalRuleMapper(JournalRuleMapper journalRuleMapper) {
		this.journalRuleMapper = journalRuleMapper;
	}
	/**
	 * journal rule mapper. kalau di perlukan proses translasi
	 **/
	public JournalRuleMapper getJournalRuleMapper() {
		return journalRuleMapper;
	}
	
	/**
	 * nama column dari source column. jadinya data gl di ambil dari mana
	 **/
	public void setValueSourceColumn(String valueSourceColumn) {
		this.valueSourceColumn = valueSourceColumn;
	}
	/**
	 * nama column dari source column. jadinya data gl di ambil dari mana
	 **/
	public String getValueSourceColumn() {
		return valueSourceColumn;
	}
	/**
	 * Kode GL. ini kalau metode rule statis, kalau ini null , berarti yang di perhitungkan adalah variable {@link #journalRuleMapper}
	 **/
	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}
	/**
	 * Kode GL. ini kalau metode rule statis, kalau ini null , berarti yang di perhitungkan adalah variable {@link #journalRuleMapper}
	 **/
	public String getGlCode() {
		return glCode;
	}
	
	/**
	 * posisi debet/credit dari GL
	 **/
	public void setGlPositionCode(String glPositionCode) {
		this.glPositionCode = glPositionCode;
	}
	/**
	 * posisi debet/credit dari GL
	 **/
	public String getGlPositionCode() {
		return glPositionCode;
	}
	@Override
	public String toString() {
		return "JournalRuleDetail [valueSourceColumn=" + valueSourceColumn
				+ ", journalRuleMapper=" + journalRuleMapper + ", glCode="
				+ glCode + ", glPositionCode=" + glPositionCode + "]";
	}
}
