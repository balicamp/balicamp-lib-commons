package id.co.sigma.commonlib.importengine.definition;





/**
 * aggregate of {@link UploadFileDefinition}
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class UploadFileGroupDefinition {
	
	
	/**
	 * ID file group
	 **/
	private String uploadFileGroupId ; 
	
	
	

	/**
	 * flag, pergunakan delimiter atau tidak. kalau false, berarti file tipe nya fixed length
	 **/
	private boolean useDelimiter ;
	
	
	
	/**
	 * valid kalau {@link #useDelimiter} = true , delimieter apa yang di pergunakan
	 **/
	private Character delimiterChar ;
	
	/**
	 * file definition tanpa pengelompokan. ini semua yang ada dalam 1 group. ini merupakan feed untuk {@link #staging1FileDefinitions}, dan {@link #actualFileDefinitions}
	 **/
	private UploadFileDefinition[] rawFileDefinitions ; 
	
	
	
	
	
	
	
	/**
	 * ID file group
	 **/
	public String getUploadFileGroupId() {
		return uploadFileGroupId;
	}

	/**
	 * ID file group
	 **/
	public void setUploadFileGroupId(String uploadFileGroupId) {
		this.uploadFileGroupId = uploadFileGroupId;
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
	
	
	/**
	 * file definition tanpa pengelompokan. ini semua yang ada dalam 1 group. ini merupakan feed untuk {@link #staging1FileDefinitions}, dan {@link #actualFileDefinitions}
	 **/
	public void assignRawFileDefinitions(
			UploadFileDefinition[] rawFileDefinitions) {
		this.rawFileDefinitions = rawFileDefinitions;
		if ( this.rawFileDefinitions==null){
			return ; 
		}
		
		
		
		
	}
}
