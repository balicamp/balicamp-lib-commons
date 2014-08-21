package id.co.sigma.commonlib.importengine.factory;

import id.co.sigma.commonlib.importengine.data.MappingSourceColumnDefinitionData;
import id.co.sigma.commonlib.importengine.data.MappingTargetColumnDefinitionData;
import id.co.sigma.commonlib.importengine.definition.src.BaseFileColumnDefinition;
import id.co.sigma.commonlib.importengine.definition.target.BaseTargetColumnDefinition;

import java.util.Collection;
import java.util.List;



/**
 * interface column definition
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface IUploadColumnDefinitionFactory {

	
	/**
	 * translate dari column definition * hasil pembacaan dari database etc* ke dlaam column definition yang di perlukan
	 * @param columnDefinition column definition yang perlu di translate
	 **/
	public abstract BaseFileColumnDefinition<?> generateColumnDefinition(
			MappingSourceColumnDefinitionData columnDefinition);
	
	
	/**
	 * worker untuk menggenerate target column definition
	 **/
	public abstract BaseTargetColumnDefinition generateColumnDefinition(MappingTargetColumnDefinitionData columnDefinition, Collection<BaseFileColumnDefinition<?> > sourceColumnDefs) ; 
	
	/**
	 * worker untuk menggenerate target column definition
	 * @param columnDefinitions definisi target columns
	 * @param sourceColumnDefs source column def, ini untuk link passthrought data maper 
	 **/
	public abstract Collection< BaseTargetColumnDefinition> generateColumnDefinitions(Collection< MappingTargetColumnDefinitionData> columnDefinitions, Collection<BaseFileColumnDefinition<?> > sourceColumnDefs) ;
	
	
	
	
	
	public abstract List<BaseFileColumnDefinition<?>> generateColumnDefinitions(
			 List<MappingSourceColumnDefinitionData> columnDefinitions); 

}