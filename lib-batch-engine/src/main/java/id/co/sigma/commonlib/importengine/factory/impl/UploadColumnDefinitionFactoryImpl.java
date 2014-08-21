package id.co.sigma.commonlib.importengine.factory.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import id.co.sigma.commonlib.importengine.data.ColumnDataType;
import id.co.sigma.commonlib.importengine.data.MappingSourceColumnDefinitionData;
import id.co.sigma.commonlib.importengine.data.MappingTargetColumnDefinitionData;
import id.co.sigma.commonlib.importengine.data.StringKeyTranslationDataMap;
import id.co.sigma.commonlib.importengine.definition.src.BaseFileColumnDefinition;
import id.co.sigma.commonlib.importengine.definition.src.BigDecimalFileColumnDefinition;
import id.co.sigma.commonlib.importengine.definition.src.DateFileColumnDefinition;
import id.co.sigma.commonlib.importengine.definition.src.SimpleStringColumnDefinition;
import id.co.sigma.commonlib.importengine.definition.src.TranslatedWithMapperUploadColumnDefinition;
import id.co.sigma.commonlib.importengine.definition.target.BaseTargetColumnDefinition;
import id.co.sigma.commonlib.importengine.definition.target.PassThroughTargetColumnDefinition;
import id.co.sigma.commonlib.importengine.definition.target.SQLFormulaTargetColumnDefinition;
import id.co.sigma.commonlib.importengine.factory.IUploadColumnDefinitionFactory;




/**
 * factory column def
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
@Service
public class UploadColumnDefinitionFactoryImpl implements IUploadColumnDefinitionFactory {
	
	
	/**
	 * logger instance
	 **/
	private static final Logger logger = LoggerFactory.getLogger(UploadColumnDefinitionFactoryImpl.class); 
	
	/**
	 * mandatory validation fail message
	 **/
	@Resource(name="mandatoryValidationFailMessageI18NKey")
	private String mandatoryValidationFailMessageI18NKey ; 
	
	/**
	 * ini untuk kalau tipe data salah
	 **/
	@Resource(name="invalidDateTypeMessageI18NKey")
	private String invalidDateTypeMessageI18NKey ; 
	
	
	
	/**
	 * data tidak berada dalam mapper
	 **/
	@Resource(name="dataOutOfMapperDataMessageI18NKey")
	private String dataOutOfMapperDataMessageI18NKey ; 
	
	
	
	
	/**
	 * ini kalau angka yang di kirimkan tidak valid
	 **/
	@Resource(name="invalidNumberMessageI18NKey")
	private String invalidNumberMessageI18NKey ; 
	
	
	
	@SuppressWarnings("unchecked")
	public BaseFileColumnDefinition<?> generateColumnDefinition (MappingSourceColumnDefinitionData columnDefinition) {
		if (ColumnDataType.date.equals(columnDefinition.getDataType())){
			DateFileColumnDefinition dateCol = new DateFileColumnDefinition(); 
			
			assignCommonField(columnDefinition, dateCol);
			dateCol.setDateTimeFormat(columnDefinition.getDateFormat());
			dateCol.setInvalidDateTypeMessageI18Key(invalidDateTypeMessageI18NKey);
			return dateCol ; 
		}
		else if (ColumnDataType.numeric.equals(columnDefinition.getDataType())){
			BigDecimalFileColumnDefinition columnTransformer = new BigDecimalFileColumnDefinition();
			assignCommonField(columnDefinition, columnTransformer);
			columnTransformer.setBigdecimalInvalidDataSourceMessageI18NKey(invalidNumberMessageI18NKey);
			columnTransformer.setUseDotDecimalSeparator(columnDefinition.isUseDotDecimalSeparator());
			return columnTransformer; 
		}
		
		else if (ColumnDataType.mappedText.equals(columnDefinition.getDataType()) ){
			TranslatedWithMapperUploadColumnDefinition<String> columnTransformer = new TranslatedWithMapperUploadColumnDefinition<String>();
			assignCommonField(columnDefinition, columnTransformer); 
			columnTransformer.setDataOutOfMapMessageI18NKey(dataOutOfMapperDataMessageI18NKey);
			columnTransformer.assignDataMap((StringKeyTranslationDataMap<String>[]) columnDefinition.getMapperData());
			return columnTransformer ; 
		}
		else if (ColumnDataType.mappedNumber.equals(columnDefinition.getDataType()) ){
			TranslatedWithMapperUploadColumnDefinition<Integer> columnTransformer = new TranslatedWithMapperUploadColumnDefinition<Integer>();
			assignCommonField(columnDefinition, columnTransformer); 
			columnTransformer.setDataOutOfMapMessageI18NKey(dataOutOfMapperDataMessageI18NKey);
			columnTransformer.assignDataMap((StringKeyTranslationDataMap<Integer>[]) columnDefinition.getMapperData());
			return columnTransformer ; 
		}
		else if (ColumnDataType.text.equals(columnDefinition.getDataType())){
			SimpleStringColumnDefinition columnTransformer = new SimpleStringColumnDefinition();
			assignCommonField(columnDefinition, columnTransformer); 
			//columnTransformer.
			
			return columnTransformer ; 
		}
		return null ; 
		
		
	}
	@Override
	public BaseTargetColumnDefinition generateColumnDefinition(
			MappingTargetColumnDefinitionData columnDefinition,
			Collection<BaseFileColumnDefinition<?>> sourceColumns) {
		
		return  new SQLFormulaTargetColumnDefinition(columnDefinition.getTargetColumnName() , columnDefinition.getFormula()); 
		
	}
	
	@Override
	public Collection<BaseTargetColumnDefinition> generateColumnDefinitions(
			Collection<MappingTargetColumnDefinitionData> columnDefinitions,
			Collection<BaseFileColumnDefinition<?>> sourceColumnDefs) {
		if ( columnDefinitions==null||columnDefinitions.isEmpty())
			return null;
		ArrayList<BaseTargetColumnDefinition> retval = new ArrayList<BaseTargetColumnDefinition>(); 
		for (MappingTargetColumnDefinitionData scn : columnDefinitions ){
			BaseTargetColumnDefinition swp = this.generateColumnDefinition(scn, sourceColumnDefs); 
			if ( swp!=null)
				retval.add(swp); 
				
		}
		return retval ;
	}
	
	protected void assignCommonField(MappingSourceColumnDefinitionData columnDefinition , BaseFileColumnDefinition<?> columnTransformer){
		columnTransformer.setColumnOnFileName(columnDefinition.getColumnOnFileName());
		columnTransformer.setMandatory(columnDefinition.isMandatory());
		//columnTransformer.setTargetTableName(columnDefinition.getTargetTableName());
		//columnTransformer.setTargetColumnName(columnDefinition.getTargetColumnName());
		columnTransformer.setMandatoryValidationI18NKey(mandatoryValidationFailMessageI18NKey);
		
		
	}


	@Override
	public List<BaseFileColumnDefinition<?>> generateColumnDefinitions(
			List<MappingSourceColumnDefinitionData> columnDefinitions) {
		if ( columnDefinitions==null||columnDefinitions.isEmpty()){
			return null ; 
                }
		List<BaseFileColumnDefinition<?>> retval = new ArrayList<BaseFileColumnDefinition<?>>();
		for ( MappingSourceColumnDefinitionData scn : columnDefinitions){
			retval.add(generateColumnDefinition(scn));
		}
		return retval;
	}
	

}
