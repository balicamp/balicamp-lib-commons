package id.co.sigma.commonlib.importengine.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import id.co.sigma.commonlib.importengine.data.MappingFileDefinitionData;
import id.co.sigma.commonlib.importengine.data.MappingSourceColumnDefinitionData;
import id.co.sigma.commonlib.importengine.data.MappingFileGroupDefinitionData;
import id.co.sigma.commonlib.importengine.data.MappingTargetColumnDefinitionData;
import id.co.sigma.commonlib.importengine.definition.UploadFileDefinition;
import id.co.sigma.commonlib.importengine.definition.UploadFileGroupDefinition;
import id.co.sigma.commonlib.importengine.definition.src.BaseFileColumnDefinition;
import id.co.sigma.commonlib.importengine.definition.target.BaseTargetColumnDefinition;
import id.co.sigma.commonlib.importengine.factory.IMappingColumnDefinitionProvider;
import id.co.sigma.commonlib.importengine.factory.IUploadColumnDefinitionFactory;
import id.co.sigma.commonlib.importengine.service.IUploadFileDefinitionService;



/**
 * provider upload definition
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class UploadFileDefinitionServiceImpl implements IUploadFileDefinitionService{
	
	
	
	/**
	 * provider mapping column definition data, data berasal dari database. tipe apa, length berapa etc
	 **/
	@Autowired
	private IMappingColumnDefinitionProvider mappingColumnDefinitionProvider ; 
	
	
	
	/**
	 * factory untuk create {@link BaseFileColumnDefinition} , based on {@link MappingSourceColumnDefinitionData}
	 **/
	@Autowired
	private IUploadColumnDefinitionFactory uploadColumnDefinitionFactory ; 
	
	
	
	private Map<String, UploadFileDefinition> indexedFileDefinition = new HashMap<String, UploadFileDefinition>() ; 

	@Override
	public UploadFileDefinition getColumnDefiniton(String fileDefinitionIdAsString) {
		UploadFileDefinition def = getFileDefintionFromCache(fileDefinitionIdAsString); 
		if ( def !=null)
			return def ; 
		
		return null ; 
	}
	
	
	
	
	
	/**
	 * provider mapping column definition data, data berasal dari database. tipe apa, length berapa etc. autowired
	 **/
	public IMappingColumnDefinitionProvider getMappingColumnDefinitionProvider() {
		return mappingColumnDefinitionProvider;
	}
	
	
	/**
	 * mengirim file definition ke dalam cache
	 **/
	protected void submitToCache (UploadFileDefinition fileDefinition ) {
		indexedFileDefinition.put(fileDefinition.getFileDefinitionId(), fileDefinition);
	}
	
	
	/**
	 * membaca file definition dari cache
	 **/
	protected UploadFileDefinition getFileDefintionFromCache (String fileId ) {
		if ( indexedFileDefinition.containsKey(fileId))
			return indexedFileDefinition.get(fileId);
		return null ; 
	}


	
	public UploadFileGroupDefinition getFileGroupDefinition(
			String fileGroupDefinitionIdAsString) {
		MappingFileGroupDefinitionData fileGroup =  mappingColumnDefinitionProvider.getFileGroupDefinition(fileGroupDefinitionIdAsString);
		UploadFileGroupDefinition uploadFileDef = new UploadFileGroupDefinition(); 
		
		uploadFileDef.setUploadFileGroupId(fileGroupDefinitionIdAsString);
		uploadFileDef.setUseDelimiter(fileGroup.isUseDelimiter()); 
		if ( fileGroup.isUseDelimiter())
			uploadFileDef.setDelimiterChar(fileGroup.getDelimiterChar()); 
		
		MappingFileDefinitionData[] fileDefs =  fileGroup.getFiles();
		UploadFileDefinition fileDefActual[] = new UploadFileDefinition[fileDefs.length];
		for ( int i = 0 ;i< fileDefs.length ; i++){
			fileDefActual[i]= generateFileDefinition(fileDefs[i]);
			indexedFileDefinition.put(fileDefActual[i].getFileDefinitionId(), fileDefActual[i]);
		}
		uploadFileDef.assignRawFileDefinitions(fileDefActual);
		return uploadFileDef;
	}
	
	
	/**
	 * worker untuk create upload file definition + column defs dari file def
	 **/
	protected UploadFileDefinition generateFileDefinition (MappingFileDefinitionData fileDef) {
		
		
		UploadFileDefinition actualDef = new UploadFileDefinition();
		
		
		
		 
		//1. baca source column definiition
		
		MappingSourceColumnDefinitionData[] colDatas =  fileDef.getSourceColumns(); 
		actualDef.setFileDefinitionId(fileDef.getFileId()); 
		actualDef.setDescription(fileDef.getDescription());
		actualDef.setMandatory(fileDef.isMandatatory());
		actualDef.setName(fileDef.getName());
		actualDef.setDelimiter(fileDef.getDelimiter());
		actualDef.setTargetTableName(fileDef.getTargetTable());
		if ( colDatas!=null){
			for ( MappingSourceColumnDefinitionData scn : colDatas){
				actualDef.getColumns().add(uploadColumnDefinitionFactory.generateColumnDefinition(scn));
			}
		}
		
		//2. baca dest column definition
		MappingTargetColumnDefinitionData[] targetCols = fileDef.getTargetDefinitions(); 
		if ( targetCols!=null&& targetCols.length>0){
			ArrayList<BaseTargetColumnDefinition> swap = new ArrayList<BaseTargetColumnDefinition>() ; 
			actualDef.setTargetDefinitions(swap);
			for ( MappingTargetColumnDefinitionData scn :targetCols ){
				BaseTargetColumnDefinition trgCol =  uploadColumnDefinitionFactory.generateColumnDefinition(scn, actualDef.getColumns());
				if ( trgCol!=null)
					swap.add(trgCol);
			}
			 
		}
		
		return actualDef;
	}

}
