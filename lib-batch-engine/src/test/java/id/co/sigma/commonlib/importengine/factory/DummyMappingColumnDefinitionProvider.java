package id.co.sigma.commonlib.importengine.factory;

import id.co.sigma.commonlib.importengine.data.ColumnDataType;
import id.co.sigma.commonlib.importengine.data.MappingFileDefinitionData;
import id.co.sigma.commonlib.importengine.data.MappingSourceColumnDefinitionData;
import id.co.sigma.commonlib.importengine.data.MappingFileGroupDefinitionData;
import id.co.sigma.commonlib.importengine.data.MappingTargetColumnDefinitionData;
import id.co.sigma.commonlib.importengine.data.StringKeyTranslationDataMap;
import id.co.sigma.commonlib.importengine.factory.IMappingColumnDefinitionProvider;

import java.util.ArrayList;
import java.util.List;

public class DummyMappingColumnDefinitionProvider implements IMappingColumnDefinitionProvider{

	
	
	
	
	private ArrayList<MappingSourceColumnDefinitionData> columnDefsDummy   ; 
	
	
	private ArrayList<MappingTargetColumnDefinitionData> columDefTarget ; 
	
	
	private MappingFileGroupDefinitionData fileGroupDefinitionData ; 
	
	public DummyMappingColumnDefinitionProvider(){
			
		columnDefsDummy  = new ArrayList<MappingSourceColumnDefinitionData>();
		columDefTarget = new ArrayList<MappingTargetColumnDefinitionData>(); 
		
		
		
		MappingSourceColumnDefinitionData cnama = new MappingSourceColumnDefinitionData("nama", ColumnDataType.text, true) ; 
		MappingSourceColumnDefinitionData cMail =  new MappingSourceColumnDefinitionData("email", ColumnDataType.text, true) ;
		MappingSourceColumnDefinitionData cTahun =  new MappingSourceColumnDefinitionData("tahun", ColumnDataType.numeric, true) ;
		MappingSourceColumnDefinitionData cTgl = new MappingSourceColumnDefinitionData("tgl_gabung", ColumnDataType.date, true); 
		MappingSourceColumnDefinitionData cPosisi =new MappingSourceColumnDefinitionData("posisi", ColumnDataType.mappedText  , true);  
		MappingSourceColumnDefinitionData cPosisiInt =new MappingSourceColumnDefinitionData("posisi_int", ColumnDataType.mappedText  , true) ;  
		columnDefsDummy.add(cnama);
		columnDefsDummy.add(cMail);
		columnDefsDummy.add(cTahun);
		columnDefsDummy.add(cTgl);
		columnDefsDummy.add(cPosisi);
		columnDefsDummy.add(cPosisiInt);
		
		
		columDefTarget.add(new MappingTargetColumnDefinitionData("nama" ,":" +  cnama.getColumnOnFileName())); 
		columDefTarget.add(new MappingTargetColumnDefinitionData("email" ,":" + cMail.getColumnOnFileName()));
		columDefTarget.add(new MappingTargetColumnDefinitionData("tahun" , ":" +cTahun.getColumnOnFileName()));
		columDefTarget.add(new MappingTargetColumnDefinitionData("tgl_gabung" , ":" +cTgl.getColumnOnFileName()));
		columDefTarget.add(new MappingTargetColumnDefinitionData("posisi" ,":" + cPosisi.getColumnOnFileName()));
		columDefTarget.add(new MappingTargetColumnDefinitionData("posisi_int" ,":" + cPosisiInt.getColumnOnFileName()));
		

		cTgl.setDateFormat("yyyy-MM-dd");
		MappingSourceColumnDefinitionData[] arr = new MappingSourceColumnDefinitionData[columnDefsDummy.size()]; 
		columnDefsDummy.toArray(arr);
		fileGroupDefinitionData = new MappingFileGroupDefinitionData();
		
		
		MappingFileDefinitionData fileDef = new MappingFileDefinitionData();
		
		
		cPosisiInt.setMapperData(new StringKeyTranslationDataMap<?>[]{
				new StringKeyTranslationDataMap<Integer>(101, "1") , 
				new StringKeyTranslationDataMap<Integer>(102, "2") ,
				
		}); 
		
		cPosisi.setMapperData(new StringKeyTranslationDataMap<?>[]{
				new StringKeyTranslationDataMap<String>("SE-2", "SA") , 
				new StringKeyTranslationDataMap<String>("SE-1", "TRN") ,
				
		}); 
		
		MappingTargetColumnDefinitionData [] targetTargetsArr = new MappingTargetColumnDefinitionData[columDefTarget.size()];
		columDefTarget.toArray(targetTargetsArr); 
		fileDef.setFileId("1");
		fileDef.setDescription("Test file");
		fileDef.setMandatatory(true);
		fileDef.setName("Test file");
		fileDef.setSequenceNumber(1);
		fileDef.setDelimiter('|');
		fileDef.setSourceColumns(arr);
		fileDef.setTargetDefinitions(targetTargetsArr); 
		fileDef.setTargetTable("batch1");
		
		
		
		
		fileGroupDefinitionData.setFiles(new MappingFileDefinitionData[]{
			fileDef	
		});
		
	}

	
	public List<MappingSourceColumnDefinitionData> getMappingColumnDefinition(
			String mappingFileId) {
		return columnDefsDummy;
	}

	
	public MappingFileDefinitionData getFileDefinition(String mappingFileId) {
		
		MappingFileDefinitionData a = new MappingFileDefinitionData(); 
		a.setFileId(mappingFileId);
		a.setDescription("Test file");
		a.setMandatatory(true);
		a.setName("Test file");
		a.setSequenceNumber(1);
		a.setDelimiter('|');
		return a;
	}

	@Override
	public MappingFileGroupDefinitionData getFileGroupDefinition(
			String filGroupId) {
		return fileGroupDefinitionData;
	}

}
