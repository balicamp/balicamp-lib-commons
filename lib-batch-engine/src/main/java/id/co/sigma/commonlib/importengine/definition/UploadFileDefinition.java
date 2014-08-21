package id.co.sigma.commonlib.importengine.definition;

import id.co.sigma.commonlib.importengine.definition.src.BaseFileColumnDefinition;
import id.co.sigma.commonlib.importengine.definition.target.BaseTargetColumnDefinition;

import java.util.ArrayList;
import java.util.List;

import org.hsqldb.ColumnSchema;



/**
 * file definition
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class UploadFileDefinition {
	
	
	
	
	
	
	/**
	 * id dari file definition. di konversikan ke dalam string
	 **/
	private String fileDefinitionId ; 
	
	/**
	 * flag mandatory atau tidak nih konfigurasi
	 **/
	private boolean mandatory ; 
	
	/**
	 * nama file
	 **/
	private String name ;
	
	/**
	 * description dari file
	 **/
	private String description ; 
        
     
	/**
	 * nama table target, kemana data akan di
	 **/
	private String targetTableName ; 
    
    
   
    /**
     * delimiter yang di pergunakan (kalau file text)
     */
    private char delimiter ; 
	
	/**
	 * column definition. apa saja yang available dalam 1 group
	 **/
	private List<BaseFileColumnDefinition<?>> columns = new ArrayList<BaseFileColumnDefinition<?>>(); 
	
	
	

	/**
	 * list of target column definitions. ini ke colom apa saja data akan di insert kan
	 **/
	private List<BaseTargetColumnDefinition> targetDefinitions ; 
	
	
	
	/**
	 * statement untuk insert ke table target
	 **/
	private String insertStatement ; 
	
	
	
	
	/**
	 * cache colum name only
	 **/
	private String[]  columnNamesFromFile ; 
	
	
	
	
	
	/**
	 * nama pada {@link ColumnSchema}, ini di pergunakan untuk membaca flat file. apa saja yang perlu di baca
	 **/
	public String[] retrieveColumnNamesFromFile () {
		if ( columnNamesFromFile!=null&&columnNamesFromFile.length>0)
			return columnNamesFromFile ; 
		columnNamesFromFile = new String[columns.size()];
		
		int i=0 ;
		for ( BaseFileColumnDefinition<?> scn : columns){
			columnNamesFromFile[i++] = scn.getColumnTitle() ; 
		}
		
		return columnNamesFromFile ; 
	}
	
	
	
	
	
	
	
	
	/**
	 * generate insert into statement untuk staging 1. FYI parameter di bikin urut dengan index 1
	 **/
	public String retrieveInsertStatement () {
		if ( this.insertStatement!=null&&this.insertStatement.length()>0)
			return this.insertStatement ; 
		
		
		StringBuffer buffCols = new StringBuffer(); 
		StringBuffer buffValues = new StringBuffer();
		
		 
		
		for ( BaseTargetColumnDefinition  scn : targetDefinitions){
			buffCols.append(scn.getTargetColumnName());
			buffCols.append(",");
			buffValues.append (  scn.generateValueStatment() +  ",");
			 
		}
		buffCols.delete(buffCols.length()-1 , buffCols.length());
		buffValues.delete(buffValues.length()-1, buffValues.length());
		
		
		StringBuffer buff = new StringBuffer(); 
		buff.append("INSERT INTO " + targetTableName + "(");
		buff.append(buffCols);
		
		buff.append(")");
		buff.append(" values(");
		buff.append(buffValues);
		buff.append(");");
		insertStatement = buff.toString() ; 
		
		return insertStatement ; 
	}
	
	
	
	
	
	/**
	 * description dari file
	 **/
	public String getDescription() {
		return description;
	}
	/**
	 * description dari file
	 **/
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * nama file
	 **/
	public String getName() {
		return name;
	}
	/**
	 * nama file
	 **/
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * flag mandatory atau tidak nih konfigurasi
	 **/
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	/**
	 * flag mandatory atau tidak nih konfigurasi
	 **/
	public boolean isMandatory() {
		return mandatory;
	}
	
	/**
	 * column definition. apa saja yang available dalam 1 group
	 **/
	public List<BaseFileColumnDefinition<?>> getColumns() {
		return columns;
	}
	/**
	 * id dari file definition. di konversikan ke dalam string
	 **/
	public String getFileDefinitionId() {
		return fileDefinitionId;
	}
	/**
	 * id dari file definition. di konversikan ke dalam string
	 **/
	public void setFileDefinitionId(String fileDefinitionId) {
		this.fileDefinitionId = fileDefinitionId;
	}
      /**
         * delimiter yang di pergunakan (kalau file text)
         */    
    public char getDelimiter() {
        return delimiter;
    }
    
    /**
	 * nama table target, kemana data akan di
	 **/
    public String getTargetTableName() {
		return targetTableName;
	}
    /**
	 * nama table target, kemana data akan di
	 **/
    public void setTargetTableName(String targetTableName) {
		this.targetTableName = targetTableName;
	}
  /**
         * delimiter yang di pergunakan (kalau file text)
         */
    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }
  
    /**
	 * list of target column definitions. ini ke colom apa saja data akan di insert kan
	 **/
    public List<BaseTargetColumnDefinition> getTargetDefinitions() {
		return targetDefinitions;
	}

    /**
	 * list of target column definitions. ini ke colom apa saja data akan di insert kan
	 **/
	public void setTargetDefinitions(
			List<BaseTargetColumnDefinition> targetDefinitions) {
		this.targetDefinitions = targetDefinitions;
	}
	/**
	 * column definition. apa saja yang available dalam 1 group
	 **/
	public void setColumns(List<BaseFileColumnDefinition<?>> columns) {
		this.columns = columns;
	}

}
