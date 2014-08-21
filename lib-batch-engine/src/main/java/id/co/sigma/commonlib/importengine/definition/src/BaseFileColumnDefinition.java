package id.co.sigma.commonlib.importengine.definition.src;

import id.co.sigma.commonlib.importengine.data.FixedLengthDataAlign;
import id.co.sigma.commonlib.importengine.definition.validator.ISimpleFieldValidator;
import id.co.sigma.commonlib.importengine.exception.DataTypeViolationException;
import id.co.sigma.commonlib.importengine.exception.MandatoryFieldViolationException;
import id.co.sigma.commonlib.importengine.exception.UploadFieldConvertionException;
import id.co.sigma.commonlib.importengine.exception.UploadFieldValidationException;


/**
 * definis column column
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseFileColumnDefinition<RESULT>  {
	
	
	
	
	
	/**
	 * prefix nama column automated stagig 1
	 **/
	public static final String AUTOMATED_STAGING_COLUMN_NAME_PREFIX="COL_"; 
	/**
	 * title dari column
	 **/
	protected String columnTitle ; 
	
	
	
	
	/**
	 * nama column dalam file. Ini di pergunakan dalam proses compose formula
	 **/
	private String columnOnFileName ; 
	
	
	
	/**
	 * panjang data. ini di pakai kalau file, tipe nya fixed length
	 **/
	protected int lengthOfTextData ; 
	
	
	/**
	 * align dari text data
	 **/
	protected FixedLengthDataAlign textDataAlign ; 
	
	
	/**
	 * nomor index column . ikut dalam file nya
	 **/
	private int columnIndexNumber ; 
	/**
	 * ini sama dengan column title di upper case, replace spasi dengan _
	 **/
	protected String internalNameForColumn ; 
	
	
	/**
	 * data mandatory atau tidak
	 **/
	protected boolean mandatory ; 
	
	
	
	/**
	 * key resource bundle untuk kasus internalization. data ini di inject dari container. sebaiknya ini di inject via IOC
	 **/
	protected String mandatoryValidationI18NKey ; 
	
	
	
	
	
	
	
	/**
	 * flag, field ini index column atau bukan. kalau index column. ini akan di pergunakan untuk join, angkut data dari staging 1 ke staging 2
	 **/
	protected boolean indexColumn ; 
	
	
	
	
	/**
	 * validator simple. langsung pada 1 field saja. dengan operator simple saja
	 **/
	private ISimpleFieldValidator<RESULT>[] simpleFieldValidators ; 
	/**
	 * worker untuk konversi dari string ke data yang di perlukan
	 * @param uploadSrcData string yang perlu di konversikan
	 **/
	protected abstract RESULT translateStringToData (String uploadSrcData) throws DataTypeViolationException; 
	
	
	
	/**
	 * generate data dari string ke data yang di perlukan. method ini bridge ke {@link #translateStringToData(String)}
	 **/
	public RESULT extractData (String uploadSrcData) throws UploadFieldConvertionException {
		validateMandatory(uploadSrcData);
		if ( uploadSrcData==null||uploadSrcData.trim().length()==0)
			return null ; 
		return translateStringToData(uploadSrcData);
	}
	/**
	 * title dari column
	 **/
	public String getColumnTitle() {
		return columnTitle;
	}
	
	/**
	 * title dari column
	 **/
	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
		internalNameForColumn = this.columnTitle.toUpperCase();
		internalNameForColumn = internalNameForColumn.replaceAll("\\ ", "_");
	}
	
	
	/**
	 * data mandatory atau tidak
	 **/
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	
	/**
	 * data mandatory atau tidak
	 **/
	public boolean isMandatory() {
		return mandatory;
	}
	
	
	
	
	
	/**
	 * shared thing, validasi mandatory. kalau null atau 0 length, di anggap invalid
	 **/
	protected void validateMandatory(String srcRawStringdata) throws MandatoryFieldViolationException{
		if ( !mandatory)// ngapain ndak mandatory kok
			return ; 
		if ( srcRawStringdata==null||srcRawStringdata.length()==0)
			throw new MandatoryFieldViolationException("gagal memvalidasi field " + getColumnTitle() + ", field ini mandatory, namun data kosong", getColumnTitle(), srcRawStringdata, getMandatoryValidationI18NKey()); 
	}
	
	
	
	
	/**
	 * run data validity validation
	 **/
	@SuppressWarnings("unchecked")
	public void validateSimpleFieldValidation(Object data ) throws UploadFieldValidationException{
		if ( simpleFieldValidators==null||simpleFieldValidators.length==0)
			return ;
		for ( ISimpleFieldValidator<RESULT> scn : this.simpleFieldValidators){
			if ( scn.validate((RESULT) data)){
				throw new UploadFieldValidationException(scn.generateErrorMessage((RESULT) data), "not known",  scn.getValidationFailureMessageI18NKey());
			}
		}
	}

	/**
	 * key resource bundle untuk kasus internalization. data ini di inject dari container. sebaiknya ini di inject via IOC
	 **/
	public void setMandatoryValidationI18NKey(String mandatoryValidationI18NKey) {
		this.mandatoryValidationI18NKey = mandatoryValidationI18NKey;
	}
	/**
	 * key resource bundle untuk kasus internalization. data ini di inject dari container. sebaiknya ini di inject via IOC
	 **/
	public String getMandatoryValidationI18NKey() {
		return mandatoryValidationI18NKey;
	}
	
	
	
	
	/**
	 * nomor index column . ikut dalam file nya
	 **/
	public int getColumnIndexNumber() {
		return columnIndexNumber;
	}
	/**
	 * nomor index column . ikut dalam file nya
	 **/
	public void setColumnIndexNumber(int columnIndexNumber) {
		this.columnIndexNumber = columnIndexNumber;
	}
	
	/**
	 * align dari text data
	 **/
	public FixedLengthDataAlign getTextDataAlign() {
		return textDataAlign;
	}
	
	/**
	 * align dari text data
	 **/
	public void setTextDataAlign(FixedLengthDataAlign textDataAlign) {
		this.textDataAlign = textDataAlign;
	}
	
	/**
	 * flag, field ini index column atau bukan. kalau index column. ini akan di pergunakan untuk join, angkut data dari staging 1 ke staging 2
	 **/
	public boolean isIndexColumn() {
		return indexColumn;
	}
	/**
	 * flag, field ini index column atau bukan. kalau index column. ini akan di pergunakan untuk join, angkut data dari staging 1 ke staging 2
	 **/
	public void setIndexColumn(boolean indexColumn) {
		this.indexColumn = indexColumn;
	}
	
	
	/**
	 * validator simple. langsung pada 1 field saja. dengan operator simple saja
	 **/
	public void setSimpleFieldValidators(
			ISimpleFieldValidator<RESULT>[] simpleFieldValidators) {
		this.simpleFieldValidators = simpleFieldValidators;
	}
	/**
	 * validator simple. langsung pada 1 field saja. dengan operator simple saja
	 **/
	public ISimpleFieldValidator<RESULT>[] getSimpleFieldValidators() {
		return simpleFieldValidators;
	}
	
	/**
	 * nama column dalam file. Ini di pergunakan dalam proses compose formula
	 **/
	public void setColumnOnFileName(String columnOnFileName) {
		this.columnOnFileName = columnOnFileName;
	}
	/**
	 * nama column dalam file. Ini di pergunakan dalam proses compose formula
	 **/
	public String getColumnOnFileName() {
		return columnOnFileName;
	}
}
