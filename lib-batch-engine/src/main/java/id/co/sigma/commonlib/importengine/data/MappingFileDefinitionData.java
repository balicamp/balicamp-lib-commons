package id.co.sigma.commonlib.importengine.data;



/**
 * definisi file, aggregate dari coumn
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * 
 **/
public class MappingFileDefinitionData {
	
	
	/**
	 * id file , di buat as string
	 **/
	private String fileId ; 
	
	
	
	/**
	 * id file group
	 **/
	private String fileGroupId ; 
	/**
	 * nomor urutan file
	 **/
	private int sequenceNumber ;
	
	/**
	 * flag data mandatory atau tidak
	 **/
	private boolean mandatatory ; 
	/**
	 * nama file
	 **/
	private String name ; 
	/**
	 * deskripsi file
	 **/
	private String description ;
	
	
	/**
	 * staging 2 layer atau bukan
	 **/
	private boolean use2LayerStaging ; 
	
	/**
	 * delimiter dari text file, default =|
	 **/
	private char delimiter ='|'; 

	
	
	
	
	/**
	 * table target, kemana data akan di kirimkan dari hasil pembacaan
	 **/
	private String targetTable ; 
	
	
	/**
	 * columns dalam file
	 **/
	private MappingSourceColumnDefinitionData[] sourceColumns ; 
	
	
	/**
	 * definisi target database. mengarah ke mana data dari proses pembacaan
	 **/
	private MappingTargetColumnDefinitionData[] targetDefinitions ; 
	
	/**
	 * definisi target database. mengarah ke mana data dari proses pembacaan
	 **/
	public MappingTargetColumnDefinitionData[] getTargetDefinitions() {
		return targetDefinitions;
	}
	/**
	 * definisi target database. mengarah ke mana data dari proses pembacaan
	 **/
	public void setTargetDefinitions(
			MappingTargetColumnDefinitionData[] targetDefinitions) {
		this.targetDefinitions = targetDefinitions;
	}
	
	
	
	/**
	 * id file , di buat as string
	 **/
	public String getFileId() {
		return fileId;
	}
	/**
	 * id file , di buat as string
	 **/
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**
	 * nomor urutan file
	 **/
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * nomor urutan file
	 **/
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public boolean isMandatatory() {
		return mandatatory;
	}
	public void setMandatatory(boolean mandatatory) {
		this.mandatatory = mandatatory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	} 

	/**
	 * delimiter dari text file, default =|
	 **/
	public void setDelimiter(char delimiter) {
		this.delimiter = delimiter;
	}
	/**
	 * delimiter dari text file, default =|
	 **/
	public char getDelimiter() {
		return delimiter;
	}
	
	/**
	 * staging 2 layer atau bukan
	 **/
	public void setUse2LayerStaging(boolean use2LayerStaging) {
		this.use2LayerStaging = use2LayerStaging;
	}
	
	/**
	 * staging 2 layer atau bukan
	 **/
	public boolean isUse2LayerStaging() {
		return use2LayerStaging;
	}
	
	/**
	 * id file group
	 **/
	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}
	/**
	 * id file group
	 **/
	public String getFileGroupId() {
		return fileGroupId;
	}
	
	
	
	/**
	 * columns dalam file
	 **/
	public void setSourceColumns(MappingSourceColumnDefinitionData[] columns) {
		this.sourceColumns = columns;
	}
	/**
	 * columns dalam file
	 **/
	public MappingSourceColumnDefinitionData[] getSourceColumns() {
		return sourceColumns;
	}
	
	/**
	 * table target, kemana data akan di kirimkan dari hasil pembacaan
	 **/
	public String getTargetTable() {
		return targetTable;
	}
	/**
	 * table target, kemana data akan di kirimkan dari hasil pembacaan
	 **/
	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}
}
