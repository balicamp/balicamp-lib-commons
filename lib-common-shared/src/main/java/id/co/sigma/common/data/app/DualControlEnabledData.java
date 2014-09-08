package id.co.sigma.common.data.app;

import id.co.sigma.common.data.ICreateAuditedData;
import id.co.sigma.common.data.IModifyAuditedData;
import id.co.sigma.common.util.json.IJSONFriendlyObject;

import java.io.Serializable;


import com.google.gwt.user.client.rpc.IsSerializable;




/**
 * base interface untuk dual control enable data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * 
 **/
public interface DualControlEnabledData<POJO 
	extends DualControlEnabledData<?, ?>, PK extends Serializable> 
	extends Serializable, IsSerializable , ICreateAuditedData , IModifyAuditedData, IJSONFriendlyObject<POJO>{
	
	
	
	
	/**
	 * flag status active
	 **/
	public static enum DualDataActiveStatusFlag {
		ACTIVE("A") , 
		NOT_ACTIVE("D"); 
		private String internalVar ; 
		private DualDataActiveStatusFlag( String internalCOde ){
			this.internalVar = internalCOde ; 
		}
		@Override
		public String toString() { 
			return internalVar;
		}
	}
	
	
	/**
	 * set approval status code ke dalam data. data actual di isi dengan approval status code untuk kemudahan track data
	 * 
	 **/
	public void setApprovalStatus (DualControlApprovalStatusCode approvalStatus ) ;
	
	
	
	/**
	 * field-field apa saja yang di modifikasi. dalam proses update, field-field ini akan di timpa dengan data yang ada dari client
	 **/
	public String [] retrieveModifableFields() ; 
	/**
	 * current approval status code
	 **/
	public DualControlApprovalStatusCode getApprovalStatus (  ) ;
	
	
	
	/**
	 * nama field yang di annotated untuk approval status. ini untuk proses otomasi proses select. jadi field yang di map sebagain approval status, nama nya apa
	 **/
	public String getApprovalStatusJPAAnnotatedField () ; 
	
	
	/**
	 * set status data di hapus. rekomendasi nya dalam table ada flag. kalau misal nya {@link #isEraseDataOnApproveErase()} = true, field ini bisa di abaikan
	 **/
	public void setActiveFlag (String activeFlag ); 
	
	
	/**
	 * flag status active
	 **/
	public String getActiveFlag ( );
	
	
	/**
	 * valid kalau {@link #isEraseDataOnApproveErase()} = false. 
	 * kalau tidak di hapus secara row, maka yang di pergunakan adalah active flag. utnuk ini ada field yang mencatat active flag. Field ini di return 
	 **/
	public String getActiveFlagJPAAnnotatedField(); 
	
	
	 
	
	
	
	/**
	 * get PK as single object
	 **/
	public PK getPrimaryKey () ;
	
	

	/**
	 * tipe data dari primary key
	 **/
	public Class<PK> getPrimaryKeyClassType();
	
	
	/**
	 * konversi object ke string json
	 **/
	public String generateJSONString() ;
	
	
	/**
	 * generate OBJECT data dengan berdasar data JSON string 
	 * @param jsonString json string untuk di konversi ke json 
	 **/
	// turn off, ikut metode IJSONFriendlyObject
	/*public POJO instantiateFormJSOnString(String jsonString) ; /*
	
	/**
	 * key 1 as string. kalau non string, proses konversi perlu di siapkan sendiri.<br/> 
	 * field ini untuk di simpan ke dalam field m_dual_control_table.key_1
	 **/
	public String getKey1AsString () ; 
	
	/**
	 * 
	 * field ini untuk di simpan ke dalam field m_dual_control_table.key_2
	 **/
	public String getKey2AsString () ;
	
	
	/**
	 * table yang dual control enabled juga wajib men-track id dari dual control row. ini untuk track balik, data saat ini di proses dengan id berapa<br/>
	 * rekomendasi nya : ada filed curr_dual_ctr_id refer ke table m_dual_control_table.pk
	 **/
	public void setCurrentCommonDualControlId (Long id ) ; 
	
	
	/**
	 * table yang dual control enabled juga wajib men-track id dari dual control row. ini untuk track balik, data saat ini di proses dengan id berapa
	 **/
	public Long getCurrentCommonDualControlId ( ) ;
	
	
	
	/**
	 * flag hapus data dari table target atau cuma flag delete saja. kalau flag ini = true maka data akan di hapus dari phisical data setelah di approve
	 **/
	public boolean isEraseDataOnApproveErase () ; 
	
	
	
	
	/**
	 * nama field yang menjadi JPA primary key. ini di pergunakan untuk proses automated load by id
	 */
	public String getPrimaryKeyJPAName () ; 


}
