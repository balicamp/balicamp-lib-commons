package id.co.sigma.commonlib.importengine.data;




/**
 * ini untuk menampung informasi column definition dari database
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id; 
 **/
public class MappingSourceColumnDefinitionData {
	
	
	
	
	
	
	
	/**
	 * column title/ nama column
	 **/
	private String columnOnFileName ; 
	
	
	/**
	 * data type yang di pergunakan
	 **/
	private ColumnDataType dataType;
	
	/**
	 * panjang char, atau number. ini untuk validasi etc
	 **/
	private int dataLength ; 
	
	
	
	/**
	 * nilai setelah demal point, berapa di ijinkan
	 **/
	private int decimalPoint ; 
	
	
	/**
	 * decimal separator file entry, pakai , atau . <br/>
	 * kalau true berarti memakai <strong>.</strong>
	 **/
	private boolean useDotDecimalSeparator  = false ; 
	
	
	/**
	 * flag mandatory atau tidak
	 **/
	private boolean mandatory ;
	
	
	
	/**
	 * date format yang di proses column ini
	 **/
	private String dateFormat ; 
	/**
	 * flag, apakah ini memliki mapper array atau tidak
	 * ini menentukan translasi dari data hasil upload ke field yang di terima ama aplikasi
	 **/
	private boolean haveMapperArray =false; 
	
	
	
	

	
	
	
	/**
	 * ini column index atau bukan
	 **/
	private boolean indexColumn ; 
	
	
	/**
	 * mapper data
	 **/
	private StringKeyTranslationDataMap<?>[] mapperData ;
	
	public MappingSourceColumnDefinitionData(){
		
	}
	
	
	
	
	public MappingSourceColumnDefinitionData(String nameOfColumn, ColumnDataType dataType,
			boolean mandatory) {
		
		this.columnOnFileName = nameOfColumn;
		this.dataType = dataType;
		this.mandatory = mandatory;
	}
	
	
	
	

	

	/**
	 * column title/ nama column
	 **/
	public String getColumnOnFileName() {
		return columnOnFileName;
	}/**
	 * decimal separator file entry, pakai , atau . <br/>
	 * kalau true berarti memakai <strong>.</strong>
	
	/**
	 * column title/ nama column
	 **/
	public void setColumnOnFileName(String nameOfColumn) {
		this.columnOnFileName = nameOfColumn;
	}
	/**
	 * data type yang di pergunakan
	 **/
	public ColumnDataType getDataType() {
		return dataType;
	}
	/**
	 * data type yang di pergunakan
	 **/
	public void setDataType(ColumnDataType dataType) {
		this.dataType = dataType;
	}
	/**
	 * panjang char, atau number. ini untuk validasi etc
	 **/
	public int getDataLength() {
		return dataLength;
	}
	/**
	 * panjang char, atau number. ini untuk validasi etc
	 **/
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	/**
	 * nilai setelah demal point, berapa di ijinkan
	 **/
	public int getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * nilai setelah demal point, berapa di ijinkan
	 **/
	public void setDecimalPoint(int decimalPoint) {
		this.decimalPoint = decimalPoint;
	} 
	
	/**
	 * flag mandatory atau tidak
	 **/
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	/**
	 * flag mandatory atau tidak
	 **/
	public boolean isMandatory() {
		return mandatory;
	}
	
	/**
	 * flag, apakah ini memliki mapper array atau tidak
	 * ini menentukan translasi dari data hasil upload ke field yang di terima ama aplikasi
	 **/
	public boolean isHaveMapperArray() {
		return haveMapperArray;
	}
	/**
	 * flag, apakah ini memliki mapper array atau tidak
	 * ini menentukan translasi dari data hasil upload ke field yang di terima ama aplikasi
	 **/
	public void setHaveMapperArray(boolean haveMapperArray) {
		this.haveMapperArray = haveMapperArray;
	}




	@Override
	public String toString() {
		String swap =  "MappingColumnDefinition [columnOnFileName=" + columnOnFileName
				+ ", dataType=" + dataType + ", dataLength=" + dataLength
				+ ", decimalPoint=" + decimalPoint + ", mandatory=" + mandatory
				+ ", haveMapperArray=" + haveMapperArray + "]";
		if ( this.mapperData!=null&&mapperData.length>0){
			swap +="mapperData:";
			for ( StringKeyTranslationDataMap<?> scn : mapperData){
				swap+="," + scn.toString();
			}
		}
		return swap ; 
	}




	/**
	 * mapper data
	 **/
	public StringKeyTranslationDataMap<?>[] getMapperData() {
		return mapperData;
	}




	/**
	 * mapper data
	 **/
	public void setMapperData(StringKeyTranslationDataMap<?>[] mapperData) {
		this.mapperData = mapperData;
	}


	/**
	 * date format yang di proses column ini
	 **/

	public String getDateFormat() {
		return dateFormat;
	}



	/**
	 * date format yang di proses column ini
	 **/
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	/**
	 * ini column index atau bukan
	 **/
	public void setIndexColumn(boolean indexColumn) {
		this.indexColumn = indexColumn;
	}
	/**
	 * ini column index atau bukan
	 **/
	public boolean isIndexColumn() {
		return indexColumn;
	}
	
	/**
	 * decimal separator file entry, pakai , atau . <br/>
	 * kalau true berarti memakai <strong>.</strong>
	 **/
	public void setUseDotDecimalSeparator(boolean useDotDecimalSeparator) {
		this.useDotDecimalSeparator = useDotDecimalSeparator;
	}
	/**
	 * decimal separator file entry, pakai , atau . <br/>
	 * kalau true berarti memakai <strong>.</strong>
	 **/
	public boolean isUseDotDecimalSeparator() {
		return useDotDecimalSeparator;
	}
}
