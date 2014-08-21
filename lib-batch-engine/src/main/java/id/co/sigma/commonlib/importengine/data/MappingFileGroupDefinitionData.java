package id.co.sigma.commonlib.importengine.data;



/**
 * definisi file group
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class MappingFileGroupDefinitionData {
	/**
	 * id file group
	 **/
	private String fileGroupId ;  
	

	/**
	 * deskripsi dari file 
	 **/
	private String description ;
	
	
	/**
	 * flag, pergunakan delimiter atau tidak. kalau false, berarti file tipe nya fixed length
	 **/
	private boolean useDelimiter ;
	
	
	
	/**
	 * valid kalau {@link #useDelimiter} = true , delimieter apa yang di pergunakan
	 **/
	private Character delimiterChar ;
	
	
	
	/**
	 * files dalam group 
	 **/
	private MappingFileDefinitionData[] files ; 
	
	
	
	
	

	/**
	 * id file group
	 **/
	public String getFileGroupId() {
		return fileGroupId;
	}

	/**
	 * id file group
	 **/
	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}
	/**
	 * deskripsi dari file 
	 **/
	public String getDescription() {
		return description;
	}

	/**
	 * deskripsi dari file 
	 **/
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * files dalam group 
	 **/
	public void setFiles(MappingFileDefinitionData[] files) {
		this.files = files;
	}
	/**
	 * files dalam group 
	 **/
	public MappingFileDefinitionData[] getFiles() {
		return files;
	}
	
	/**
	 * flag, pergunakan delimiter atau tidak. kalau false, berarti file tipe nya fixed length
	 **/
	public void setUseDelimiter(boolean useDelimiter) {
		this.useDelimiter = useDelimiter;
	}
	/**
	 * flag, pergunakan delimiter atau tidak. kalau false, berarti file tipe nya fixed length
	 **/
	public boolean isUseDelimiter() {
		return useDelimiter;
	}
	
	/**
	 * valid kalau {@link #useDelimiter} = true , delimieter apa yang di pergunakan
	 **/
	public Character getDelimiterChar() {
		return delimiterChar;
	}
	
	/**
	 * valid kalau {@link #useDelimiter} = true , delimieter apa yang di pergunakan
	 **/
	public void setDelimiterChar(Character delimiterChar) {
		this.delimiterChar = delimiterChar;
	}
	
	
	
	
}
