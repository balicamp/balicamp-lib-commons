package id.co.sigma.commonlib.journal.engine;



/**
 * provider primary key untuk database
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface IDataPrimaryKeyProvider {
	
	
	
	/**
	 * yang di lakukan method : 
	 * <ol>
	 * <li>Membaca posisis sequence/primary key untuk data</li>
	 * <li>menaikan da ta sequence = current data + <i>numberOfRequeiredKey</i>, dengan begini proses berikut nya tidak mengalami constraint violation</li>
	 * <li>return key pertama data</li>
	 * </ol>
	 * @param tableName nama table yang perlu di ambilkan data key nya
	 * @param numberOfRequeiredKey berapa data yang memerlukan primary key, ini untuk menaikan sequence data
	 **/
	public Long getDataKey (String tableName , int numberOfRequeiredKey) ; 

}
