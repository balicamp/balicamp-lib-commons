package id.co.sigma.commonlib.housekeeper;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class HouseKeepingConstant {
	
	
	
	
	/**
	 * ini nama file di mana list of table di taruh. data di taruh dalam format JSON
	 **/
	public static final String ARCHIVED_FOLDER_LIST_FILE_NAME_ON_ARCHIVE="archived-tables.json"; 
	
	
	/**
	 * key untuk statement insert into
	 **/
	public static final String INSERT_INTO_JSON_KEY="insertIntoStatement";
	
	
	
	/**
	 * key untuk menaruh tipe dari data yang di serialize kan
	 **/
	public static final String SERIALIZED_FIELD_TYPE_KEY="fieldTypes";
	
	
	
	public static final String SELECT_STATEMENT_KEY="selectStatementKey";
	
	
	public static final String INSERT_STATEMENT_KEY="insertIntoStatementKey";
	
	
	
	
	/**
	 * key nama table yang perlu di hapus
	 **/
	public static final String TABLE_NAME_KEY="tableName" ; 
	
	/**
	 * absoulte path , di mana file akan di taruh
	 **/
	public static final String OUTPUT_FILE_ABSOULTE_PATH = "outputAbsPath";
	
	
	
	/**
	 * directory di mana file sql di buat. ini untuk di tar.gz
	 **/
	public static final String OUTPUT_FILE_DIRECTORY_PATH ="outputDirectory";
	
	
	/**
	 * data details
	 **/
	public static final String DATA_DETAIL_KEY="details";
	
	
	
	
	/**
	 * agar json lebih compact, nama untuk data detail , key di pakai automated. P1,P2 dst..
	 **/
	public static final String SHORT_DETAIL_KEY_PREFIX ="P";
	
	
	/**
	 * mapper dari versi short dengan actual field long
	 **/
	public static final String DETAIL_MAPPER_KEY ="fieldMapper";
	
	
	/**
	 * step untuk proses extract insert into. ini berulang sesuai dengan jumlah table
	 **/
	public static final String REPETAED_EXTRACT_SQL_STATEMENT_STEP ="extract-insert-into-step";
	
	
	/**
	 * ini setelah semua insert into ke create. file di konversi menjadi zip
	 **/
	public static final String ARCHIVE_TO_COMPRESED_FILE_STEP ="zip-insert-intp-sql-to-one-zipfile-step";
	
	
	
	/**
	 * ini nama bean yang akan di trigger kalau job selesai di kerjakan
	 **/
	public static final String AFTER_JOB_HANDLER_BEAN ="after-job-bean-name"; 
	
	 

}
