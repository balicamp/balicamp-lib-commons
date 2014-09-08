package id.co.sigma.commonlib.exportengine;

import java.io.File;
import java.io.IOException;

import id.co.sigma.commonlib.exportengine.data.ExportToTextFileMetadata;
import id.co.sigma.commonlib.exportengine.data.IExportToTextFileMetadataProvider;





public class ExportMetadataProviderTestImpl implements IExportToTextFileMetadataProvider{

	
	ExportToTextFileMetadata retval = null ; 
	
	@Override
	public ExportToTextFileMetadata getMetaData(String id) {
		if ( retval !=null)
			return retval ; 
		retval = new ExportToTextFileMetadata(); 
		retval.setDateFormat("yyyy-MM-dd");
		retval.setIdAsString(id);
		retval.setNumberOfDecimal(2);
		retval.setTargetFileDelimiter(";");
		retval.setUseDotDecimalSeparator(false);
		retval.setDecimalColumns(new String[]{"v_decimal"});
		
		retval.setSelectStatement("select * from export_tes where id_batch=:ID_BATCH  and kode_unit =:KODE_UNIT");
		return retval;
	}

}
