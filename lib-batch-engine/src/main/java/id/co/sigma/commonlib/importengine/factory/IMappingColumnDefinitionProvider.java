package id.co.sigma.commonlib.importengine.factory;

import java.util.List;

import id.co.sigma.commonlib.importengine.data.MappingFileDefinitionData;
import id.co.sigma.commonlib.importengine.data.MappingSourceColumnDefinitionData;
import id.co.sigma.commonlib.importengine.data.MappingFileGroupDefinitionData;
import id.co.sigma.commonlib.importengine.data.MappingTargetColumnDefinitionData;



/**
 * mapping column def provider
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface IMappingColumnDefinitionProvider {
	
	
	
	
	
	
	
	/**
	 * method ini untuk membaca file group definition. ini include file + column di dalam nya
	 **/
	public MappingFileGroupDefinitionData getFileGroupDefinition (String filGroupId) ;
	
	
	

}
