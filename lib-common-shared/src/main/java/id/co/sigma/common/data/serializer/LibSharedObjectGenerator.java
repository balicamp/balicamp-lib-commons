package id.co.sigma.common.data.serializer;


import id.co.sigma.common.data.InvalidSpreadSheetCellFormatException;
import id.co.sigma.common.data.SpreadSheetExceptionType;
import id.co.sigma.common.data.app.CommonDualControlBulkDataContainer;
import id.co.sigma.common.data.app.DualControlEnabledOperation;
import id.co.sigma.common.util.json.IJSONFriendlyObject;

/**
 * generator untuk object yang di mark {@link IJSONFriendlyObject}. kalau ada penambahan object dalam lib shared class ini perlu di update
 * 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class LibSharedObjectGenerator extends AbstractObjectGenerator{
	
	
	
	private static Class<?> [] CLASSES ={
		
		id.co.sigma.common.data.lov.CommonLOV.class, 
		id.co.sigma.common.data.lov.CommonLOVHeader.class , 
		id.co.sigma.common.data.query.SimpleQueryFilter.class,
		id.co.sigma.common.data.query.SimpleSortArgument.class,
		
		//add by dode
		id.co.sigma.common.data.app.exception.InvalidDualControlStateException.class,
		id.co.sigma.common.data.app.exception.UnsupportedDualControlDataKeyType.class,
		id.co.sigma.common.data.app.security.ClientSecurityData.class,
		
		
		// dual control thing
		id.co.sigma.common.data.app.CommonDualControlContainerTable.class,
		DualControlEnabledOperation.class, 
		CommonDualControlBulkDataContainer.class , 
		
		
		id.co.sigma.common.data.app.I18FormMasterConfigurationNotDefinedException.class,
		id.co.sigma.common.data.app.LabelDataWrapper.class,
		id.co.sigma.common.data.app.NoConfigurationChangeException.class,
		id.co.sigma.common.data.entity.I18NTextGroup.class,
		id.co.sigma.common.data.entity.I18Code.class,
		id.co.sigma.common.data.entity.FormLabelPK.class,
		id.co.sigma.common.data.entity.FormLabel.class,
		id.co.sigma.common.data.entity.FormElementConfigurationPK.class,
		id.co.sigma.common.data.entity.FormElementConfiguration.class,
		id.co.sigma.common.data.entity.FormConfigurationSummaryPK.class,
		id.co.sigma.common.data.entity.FormConfigurationSummary.class,
		id.co.sigma.common.data.entity.I18Text.class,
		id.co.sigma.common.data.entity.I18TextPK.class,
		id.co.sigma.common.data.lov.Common2ndLevelLOV.class,
		id.co.sigma.common.data.lov.Common2ndLevelLOVHeader.class,
		id.co.sigma.common.data.lov.LOV2ndLevelRequestArgument.class,
		id.co.sigma.common.data.lov.LOVRequestArgument.class,
		id.co.sigma.common.data.ActualSimpleGridDataWrapper.class,
		
		id.co.sigma.common.exception.CacheStillUpToDateException.class,
		id.co.sigma.common.exception.CommonWrappedSerializableException.class,
		id.co.sigma.common.exception.DataNotFoundException.class,
		id.co.sigma.common.exception.UnknownLookupValueProviderException.class,
		id.co.sigma.common.exception.LoginExpiredException.class, 
		
		
		// enum 
		id.co.sigma.common.data.lov.LOVSource.class, 
		id.co.sigma.common.data.lov.LOV2ndLevelRequestArgument.class,
		id.co.sigma.common.data.lov.LOVRequestArgument.class, 
		id.co.sigma.common.data.approval.SimpleApprovalStatus.class , 
		// approval 
		id.co.sigma.common.data.approval.CommonApprovalHeader.class  , 
		id.co.sigma.common.data.approval.CommonApprovalDefinition.class , 
		
		id.co.sigma.common.exception.BadBulkUploadDataException.class  , 
		id.co.sigma.common.data.app.HeaderDataOnlyCommonDualControlContainerTable.class , 
		id.co.sigma.common.data.app.SimpleMasterDataDualControlApprovalResult.class , 
		
		id.co.sigma.common.data.app.security.MenuEditingData.class , 
		id.co.sigma.common.exception.DataDuplicationOnUploadedDataException.class, 
		InvalidSpreadSheetCellFormatException.class,
		SpreadSheetExceptionType.class ,
		id.co.sigma.common.data.AppConfigurationDrivenDetaiResultHolder.class , 
		id.co.sigma.common.data.DataWithToken.class , 
		id.co.sigma.common.exception.InvalidTokenException.class , 
		id.co.sigma.common.exception.TokenNotPassedException.class
		
	};

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> T instantiateSampleObject(String objectFQCN) {
		if (id.co.sigma.common.data.lov.CommonLOV.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.lov.CommonLOV();
		if (id.co.sigma.common.data.lov.CommonLOVHeader.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.lov.CommonLOVHeader();
		if (id.co.sigma.common.data.query.SimpleQueryFilter.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.query.SimpleQueryFilter();
		if (id.co.sigma.common.data.query.SimpleSortArgument.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.query.SimpleSortArgument();

		//add by dode
		if (id.co.sigma.common.data.app.exception.InvalidDualControlStateException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.app.exception.InvalidDualControlStateException();
		if (id.co.sigma.common.data.app.exception.UnsupportedDualControlDataKeyType.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.app.exception.UnsupportedDualControlDataKeyType();
		if (id.co.sigma.common.data.app.security.ClientSecurityData.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.app.security.ClientSecurityData();
		if (id.co.sigma.common.data.app.CommonDualControlContainerTable.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.app.CommonDualControlContainerTable();
		if (id.co.sigma.common.data.app.I18FormMasterConfigurationNotDefinedException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.app.I18FormMasterConfigurationNotDefinedException();
		if (id.co.sigma.common.data.app.LabelDataWrapper.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.app.LabelDataWrapper();
		if (id.co.sigma.common.data.app.NoConfigurationChangeException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.app.NoConfigurationChangeException();
		if (id.co.sigma.common.data.entity.I18NTextGroup.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.entity.I18NTextGroup();
		if (id.co.sigma.common.data.entity.I18Code.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.entity.I18Code();
		if (id.co.sigma.common.data.entity.FormLabelPK.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.entity.FormLabelPK();
		if (id.co.sigma.common.data.entity.FormLabel.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.entity.FormLabel();
		if (id.co.sigma.common.data.entity.FormElementConfigurationPK.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.entity.FormElementConfigurationPK();
		if (id.co.sigma.common.data.entity.FormElementConfiguration.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.entity.FormElementConfiguration();
		if (id.co.sigma.common.data.entity.FormConfigurationSummaryPK.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.entity.FormConfigurationSummaryPK();
		if (id.co.sigma.common.data.entity.FormConfigurationSummary.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.entity.FormConfigurationSummary();
		if (id.co.sigma.common.data.entity.I18Text.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.entity.I18Text();
		if (id.co.sigma.common.data.entity.I18TextPK.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.entity.I18TextPK();
		if (id.co.sigma.common.data.lov.Common2ndLevelLOV.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.lov.Common2ndLevelLOV();
		if (id.co.sigma.common.data.lov.Common2ndLevelLOVHeader.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.lov.Common2ndLevelLOVHeader();
		
		if (id.co.sigma.common.exception.CacheStillUpToDateException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.exception.CacheStillUpToDateException();
		if (id.co.sigma.common.exception.CommonWrappedSerializableException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.exception.CommonWrappedSerializableException();
		if (id.co.sigma.common.exception.DataNotFoundException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.exception.DataNotFoundException();
		if (id.co.sigma.common.exception.UnknownLookupValueProviderException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.exception.UnknownLookupValueProviderException();
		if ( id.co.sigma.common.exception.LoginExpiredException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.exception.LoginExpiredException();
		
		//enum
		if ( id.co.sigma.common.data.lov.LOVSource.class.getName().equals(objectFQCN)) return (T) id.co.sigma.common.data.lov.LOVSource.directFromLookupTable ;
		if (id.co.sigma.common.data.lov.LOV2ndLevelRequestArgument.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.lov.LOV2ndLevelRequestArgument();
		if (id.co.sigma.common.data.lov.LOVRequestArgument.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.lov.LOVRequestArgument();
		if ( DualControlEnabledOperation.class.getName().equals(objectFQCN))return (T)DualControlEnabledOperation.DELETE  ;
		if ( id.co.sigma.common.data.approval.SimpleApprovalStatus.class.getName().equals(objectFQCN))return (T)id.co.sigma.common.data.approval.SimpleApprovalStatus.APPROVED;
		if ( CommonDualControlBulkDataContainer.class.getName().equals(objectFQCN)) return (T) new CommonDualControlBulkDataContainer();
		
		if ( id.co.sigma.common.data.approval.CommonApprovalHeader.class.getName().equals( objectFQCN)) return (T) new id.co.sigma.common.data.approval.CommonApprovalHeader() ;    
		if ( id.co.sigma.common.data.approval.CommonApprovalDefinition.class.getName().equals( objectFQCN)) return (T) new id.co.sigma.common.data.approval.CommonApprovalDefinition() ;
		if ( id.co.sigma.common.exception.BadBulkUploadDataException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.exception.BadBulkUploadDataException();
		if(id.co.sigma.common.data.app.HeaderDataOnlyCommonDualControlContainerTable.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.app.HeaderDataOnlyCommonDualControlContainerTable(); 
		if ( id.co.sigma.common.data.app.SimpleMasterDataDualControlApprovalResult.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.app.SimpleMasterDataDualControlApprovalResult() ;
		if ( id.co.sigma.common.data.app.security.MenuEditingData.class.getName().equals(objectFQCN)) return (T) new   id.co.sigma.common.data.app.security.MenuEditingData() ;
		
		if ( id.co.sigma.common.exception.DataDuplicationOnUploadedDataException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.exception.DataDuplicationOnUploadedDataException() ;
		if(InvalidSpreadSheetCellFormatException.class.getName().equals(objectFQCN)) return (T) new InvalidSpreadSheetCellFormatException();
		if(SpreadSheetExceptionType.class.getName().equals(objectFQCN)) return (T) SpreadSheetExceptionType.BOOLEAN_EXCEPTION;
		if ( id.co.sigma.common.data.AppConfigurationDrivenDetaiResultHolder.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.data.AppConfigurationDrivenDetaiResultHolder()  ;
		if ( id.co.sigma.common.data.DataWithToken.class.getName().equals(objectFQCN)) return (T)new id.co.sigma.common.data.DataWithToken();
		if ( id.co.sigma.common.exception.InvalidTokenException.class.getName().equals(objectFQCN)) return (T) new id.co.sigma.common.exception.InvalidTokenException() ;
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] instantiateArray(String objectFQCN, int size) {
		if (id.co.sigma.common.data.lov.CommonLOV.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.lov.CommonLOV[size];
		if (id.co.sigma.common.data.lov.CommonLOVHeader.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.lov.CommonLOVHeader[size];
		if (id.co.sigma.common.data.query.SimpleQueryFilter.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.query.SimpleQueryFilter[size];
		if (id.co.sigma.common.data.query.SimpleSortArgument.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.query.SimpleSortArgument[size];
		
		//add by dode
		if (id.co.sigma.common.data.app.exception.InvalidDualControlStateException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.app.exception.InvalidDualControlStateException[size];
		if (id.co.sigma.common.data.app.exception.UnsupportedDualControlDataKeyType.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.app.exception.UnsupportedDualControlDataKeyType[size];
		if (id.co.sigma.common.data.app.security.ClientSecurityData.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.app.security.ClientSecurityData[size];
		if (id.co.sigma.common.data.app.CommonDualControlContainerTable.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.app.CommonDualControlContainerTable[size];
		if ( DualControlEnabledOperation.class.getName().equals(objectFQCN))return (T[])new DualControlEnabledOperation[size];
		if (id.co.sigma.common.data.app.I18FormMasterConfigurationNotDefinedException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.app.I18FormMasterConfigurationNotDefinedException[size];
		if (id.co.sigma.common.data.app.LabelDataWrapper.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.app.LabelDataWrapper[size];
		if (id.co.sigma.common.data.app.NoConfigurationChangeException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.app.NoConfigurationChangeException[size];
		if (id.co.sigma.common.data.entity.I18NTextGroup.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.entity.I18NTextGroup[size];
		if (id.co.sigma.common.data.entity.I18Code.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.entity.I18Code[size];
		if (id.co.sigma.common.data.entity.FormLabelPK.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.entity.FormLabelPK[size];
		if (id.co.sigma.common.data.entity.FormLabel.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.entity.FormLabel[size];
		if (id.co.sigma.common.data.entity.FormElementConfigurationPK.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.entity.FormElementConfigurationPK[size];
		if (id.co.sigma.common.data.entity.FormElementConfiguration.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.entity.FormElementConfiguration[size];
		if (id.co.sigma.common.data.entity.FormConfigurationSummaryPK.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.entity.FormConfigurationSummaryPK[size];
		if (id.co.sigma.common.data.entity.FormConfigurationSummary.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.entity.FormConfigurationSummary[size];
		if (id.co.sigma.common.data.entity.I18Text.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.entity.I18Text[size];
		if (id.co.sigma.common.data.entity.I18TextPK.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.entity.I18TextPK[size];
		if (id.co.sigma.common.data.lov.Common2ndLevelLOV.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.lov.Common2ndLevelLOV[size];
		if (id.co.sigma.common.data.lov.Common2ndLevelLOVHeader.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.lov.Common2ndLevelLOVHeader[size];
		
		if (id.co.sigma.common.exception.CacheStillUpToDateException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.exception.CacheStillUpToDateException[size];
		if (id.co.sigma.common.exception.CommonWrappedSerializableException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.exception.CommonWrappedSerializableException[size];
		if (id.co.sigma.common.exception.DataNotFoundException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.exception.DataNotFoundException[size];
		if (id.co.sigma.common.exception.UnknownLookupValueProviderException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.exception.UnknownLookupValueProviderException[size];
		
		
		if ( CommonDualControlBulkDataContainer.class.getName().equals(objectFQCN)) return (T[]) new CommonDualControlBulkDataContainer[size];
		
		
		
		//enum
		if ( id.co.sigma.common.data.lov.LOVSource.class.getName().equals(objectFQCN)) return (T[])  new id.co.sigma.common.data.lov.LOVSource[size] ;
		if (id.co.sigma.common.data.lov.LOV2ndLevelRequestArgument.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.lov.LOV2ndLevelRequestArgument[size];
		if (id.co.sigma.common.data.lov.LOVRequestArgument.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.lov.LOVRequestArgument[size];	
		if ( id.co.sigma.common.data.approval.SimpleApprovalStatus.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.approval.SimpleApprovalStatus[size];
		if ( id.co.sigma.common.exception.LoginExpiredException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.exception.LoginExpiredException[size]; 
		
		// approval 
		if ( id.co.sigma.common.data.approval.CommonApprovalHeader.class.getName().equals( objectFQCN)) return (T[]) new id.co.sigma.common.data.approval.CommonApprovalHeader[size] ;
		if ( id.co.sigma.common.data.approval.CommonApprovalDefinition.class.getName().equals( objectFQCN)) return (T[]) new id.co.sigma.common.data.approval.CommonApprovalDefinition[size] ;
		if ( id.co.sigma.common.exception.BadBulkUploadDataException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.exception.BadBulkUploadDataException[size];
		if(id.co.sigma.common.data.app.HeaderDataOnlyCommonDualControlContainerTable.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.app.HeaderDataOnlyCommonDualControlContainerTable[size];
		if ( id.co.sigma.common.data.app.SimpleMasterDataDualControlApprovalResult.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.app.SimpleMasterDataDualControlApprovalResult[size];
		
		if ( id.co.sigma.common.data.app.security.MenuEditingData.class.getName().equals(objectFQCN)) return (T[]) new   id.co.sigma.common.data.app.security.MenuEditingData[size] ;
		if ( id.co.sigma.common.exception.DataDuplicationOnUploadedDataException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.exception.DataDuplicationOnUploadedDataException[size] ;
		if(InvalidSpreadSheetCellFormatException.class.getName().equals(objectFQCN)) return (T[]) new InvalidSpreadSheetCellFormatException[size];
		if(SpreadSheetExceptionType.class.getName().equals(objectFQCN)) return (T[]) new SpreadSheetExceptionType[size];
		if ( id.co.sigma.common.data.AppConfigurationDrivenDetaiResultHolder.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.AppConfigurationDrivenDetaiResultHolder[size]  ;
		if ( id.co.sigma.common.data.DataWithToken.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.data.DataWithToken[size];
		if ( id.co.sigma.common.exception.InvalidTokenException.class.getName().equals(objectFQCN)) return (T[]) new id.co.sigma.common.exception.InvalidTokenException[size] ; 
		return null;
	}

	@Override
	public Class<?>[] generatedClass() {
		return CLASSES;
	}

}
