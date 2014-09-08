package id.co.sigma.commonlib.base;

/**
 * class wrapper data ternormalisasi. use case nya spt ini
 * statement asli : 
 * SELECT 
FROM 
A WHERE
A.ID=$PPARAM_ID
AND A.NAME=$PPARAM_ID
*
* proses cuma menerima : 
* 
* SELECT 
FROM 
A WHERE
A.ID=?
AND A.NAME=?
* pekerjaan nya adalah mengganti : $PPARAM_ID -> ? dan $PPARAM_ID
* parameter di susun ulang sebagai array of object nanti nya,
**/
public class ReadSelectStatementMetaData {
	
	
	
	/**
	 * statement SQL yang sudah di normalize. 
	 **/
	private String normalizeStatement ; 
	
	/**
	 * marameter. dan dalam query
	 **/
	private String[] namedParameterArray ; 
	
	public void setNormalizeStatement(String normalizeStatement) {
		this.normalizeStatement = normalizeStatement;
	}
	
	
	public String getNormalizeStatement() {
		return normalizeStatement;
	}
	
	
	public void setNamedParameterArray(String[] namedParameterArray) {
		this.namedParameterArray = namedParameterArray;
	}
	
	public String[] getNamedParameterArray() {
		return namedParameterArray;
	}
	
	
	
}

