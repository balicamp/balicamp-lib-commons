package id.co.sigma.common.server.lov.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import id.co.sigma.common.data.lov.Common2ndLevelLOVHeader;
import id.co.sigma.common.data.lov.CommonLOV;
import id.co.sigma.common.data.lov.CommonLOVHeader;
import id.co.sigma.common.data.lov.ILookupDetail;
import id.co.sigma.common.data.lov.ILookupHeader;
import id.co.sigma.common.data.lov.LOV2ndLevelRequestArgument;
import id.co.sigma.common.data.lov.LOVRequestArgument;
import id.co.sigma.common.data.lov.LOVSource;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleQueryFilterOperator;
import id.co.sigma.common.exception.CacheStillUpToDateException;
import id.co.sigma.common.exception.UnknownLookupValueProviderException;
import id.co.sigma.common.security.domain.lov.LookupHeader;
import id.co.sigma.common.server.dao.DirectTableLookupProviderDao;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.lov.Base2ndLevelLOVProvider;
import id.co.sigma.common.server.lov.BaseLOVProvider;
import id.co.sigma.common.server.lov.ILOVProviderService;
import id.co.sigma.common.server.lov.ISelfRegisterLovManager;
import id.co.sigma.common.server.service.AbstractService;

/**
 * layer service penyedia LOV( list of value)
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class LOVProviderServiceImpl extends AbstractService implements ILOVProviderService{


	private static final Logger logger = LoggerFactory.getLogger(LOVProviderServiceImpl.class); 

	@Autowired(required=true )
	@Qualifier(value="common-lov.directTableProvider")
	private DirectTableLookupProviderDao directTableLookupProviderDao ;
	
	
	
	@Autowired
	private ISelfRegisterLovManager selfRegisterLovManager;
	
	@Autowired
	IGeneralPurposeDao generalPurposeDao; 
	
	
	@Override
	public Map<String, CommonLOVHeader> getLOVAsMap(String localizationCode,
			Collection<String> lovIds) {
		
		
		SimpleQueryFilter s = new SimpleQueryFilter() ;
		if ( lovIds== null || lovIds.isEmpty())
			return null ; 
		
		String [] arr = new String[lovIds.size()]; 
		lovIds.toArray(arr); 
		
		
		s.setField("id");
		s.setFilter(arr);
		s.setOperator(SimpleQueryFilterOperator.fieldIn);
		
		SimpleQueryFilter [] flts = new SimpleQueryFilter[]{
			s	
		} ; 
		ArrayList<String> nonDirects = new ArrayList<String>();
		nonDirects.addAll(lovIds); 
		ArrayList<String> directs = new ArrayList<String>(); 
		try {
			List<LookupHeader> heads =  generalPurposeDao.list(LookupHeader.class, flts , null );
			if ( heads != null){
				for (LookupHeader scn : heads ) {
					directs.add(scn.getLovId()); 
					nonDirects.remove(scn.getLovId());
				}
			}
			
		} catch (Exception e) {
			logger.error("gagal membaca data header dao.error : " + e.getMessage() , e );
			return null ; 
		}
		 
		List<LOVRequestArgument> dataRequest = new ArrayList<LOVRequestArgument>() ; 
		for (String scn : directs){
			LOVRequestArgument d = new LOVRequestArgument(); 
			d.setId(scn);
			d.setSource(LOVSource.directFromLookupTable);
			dataRequest.add(d); 
		}
		for (String scn : nonDirects){
			LOVRequestArgument d = new LOVRequestArgument(); 
			d.setId(scn);
			d.setSource(LOVSource.useCustomProvider);
			dataRequest.add(d);
		}
		
		List<CommonLOVHeader> hsl =  getModifiedLOVs(localizationCode, dataRequest, false);
		if ( hsl== null || hsl.isEmpty())
			return null ; 
		HashMap<String, CommonLOVHeader> rtv = new HashMap<String, CommonLOVHeader>(); 
		for ( CommonLOVHeader scn : hsl) {
			rtv.put(scn.getLovId(), scn); 
		}
		return rtv ; 
	}
	
	
	@Override
	public List<CommonLOVHeader> getModifiedLOVs(String localizationCode,
			List<LOVRequestArgument> dataRequest) {
		
		return getModifiedLOVs(localizationCode, dataRequest, true); 
		
	}
	
	
	private List<CommonLOVHeader> getModifiedLOVs(String localizationCode,
			List<LOVRequestArgument> dataRequest, boolean checkForExpired) {
		try {
			List<LOVRequestArgument> directTable = new ArrayList<LOVRequestArgument>(); 
			List<CommonLOVHeader> retval = new ArrayList<CommonLOVHeader>();
			for ( LOVRequestArgument scn : dataRequest){
				if ( LOVSource.directFromLookupTable.equals(scn.getSource()))
					directTable.add(scn); 
				else if ( LOVSource.useCustomProvider.equals(scn.getSource())){
					BaseLOVProvider provider =selfRegisterLovManager.get(scn.getId());
					if ( provider!=null){
						if ( checkForExpired){
							String dbVersion = provider.getVersion();
							if ( dbVersion!= null) {
								if (dbVersion.equals(  scn.getCacheVersion()))
									continue ;
							}
							else if ( scn.getCacheVersion()!= null) {
								if (scn.getCacheVersion().equals( dbVersion  ))
									continue ;
							}
						}
						
						
						CommonLOVHeader  headCustom = provider.getLookupValues(localizationCode, scn); //customLOVProviders.get(scn.getId()).getLookupValues(localizationCode, scn);
						if ( headCustom!=null)
							retval.add(headCustom); 
					}
				}	
			}
			
			if ( !directTable.isEmpty()){
				Collection<CommonLOVHeader> directData = getDirectLookupTableLOVData(localizationCode , directTable,checkForExpired);
				if(directData!=null&&!directData.isEmpty())
					retval.addAll(directData);
			}
			
			
			return retval;
		} catch (Exception e) {
			e.printStackTrace(); 
			return null ; 
		}
		
	}
	 
	
		
	/**
	 * LOV yang di ambil langsung dari table lendor_data.m_lookup_details
	 * @param lovIDs list of lov id yang hendak di cek
	 **/
	private Collection<CommonLOVHeader> getDirectLookupTableLOVData(String localizationCode,List<LOVRequestArgument> lovIDs, boolean checkVersion){
		if ( lovIDs==null||lovIDs.isEmpty())
			return null ; 
		// baca dulu semua header def 
		List<String> ids = new ArrayList<String>();
		for ( LOVRequestArgument scn : lovIDs){
			ids.add(scn.getId());
		}
		List<ILookupHeader> heads = this.directTableLookupProviderDao.getLookupHeaders(localizationCode , ids) ;
		if ( heads==null||heads.isEmpty())
			return null ; 
		
		// pastikan bahwa yang masih up to date masuk
		Map<String, LOVRequestArgument> indexed= new HashMap<String, LOVRequestArgument>();
		
		for(LOVRequestArgument scn :  lovIDs){
				if ( checkVersion){
					if ( scn.getCacheVersion()==null|| scn.getCacheVersion().length()==0)
					continue ;
				}
				indexed.put(scn.getId(), scn) ; 
		}
		
		
		List<CommonLOVHeader> needSync = new ArrayList<CommonLOVHeader>(); 
		
		//Map<String, LookupHeader> indexedHeader= new HashMap<String, LookupHeader>();
		Map<String, CommonLOVHeader> indexedUnSync = new HashMap<String, CommonLOVHeader>();
		
		// check mana saja yang perlu baca ulang()
		
		for ( ILookupHeader lh : heads){
			if ( indexed.containsKey(lh.getLovId())  &&  lh.isCacheable() 
					&& lh.getVersion()!=null){
				LOVRequestArgument ar= indexed.get(lh.getLovId());
				// check versi nya sama ndak
				if ( checkVersion&&  lh.getVersion().equals(  ar.getCacheVersion())){
					continue ; 
				}
			}
			CommonLOVHeader notSyncHead = new CommonLOVHeader();
			notSyncHead.setCacheable(lh.isCacheable());
			notSyncHead.setLovId(lh.getLovId());
			notSyncHead.setLovRemark(lh.getLovRemark());
			notSyncHead.setSource(LOVSource.directFromLookupTable);
			notSyncHead.setVersion(lh.getVersion());
			notSyncHead.setDetails(new ArrayList<CommonLOV>());
				
			needSync.add(notSyncHead); 		
			indexedUnSync.put(notSyncHead.getLovId(), notSyncHead);
			
		}
		/// check dari lookup details 
		if ( needSync==null||needSync.isEmpty()){
			return null ;
		
		
		 
		}
		List<ILookupDetail> allDetails = this.directTableLookupProviderDao.getLookupDetails(localizationCode , indexedUnSync.keySet()); 
		if ( allDetails==null|| allDetails.isEmpty())
			return null ; 
		 
		 
		for ( ILookupDetail scn : allDetails){
			
			if ( !indexedUnSync.containsKey(scn.getParentId()))
				continue ;
			
			CommonLOV cLov = new CommonLOV();
			
			cLov.setDataValue(scn.getDataValue()); 
			cLov.setLabel(scn.getLabel()); 
			cLov.setAdditionalData1(scn.getAdditionalData1()); 
			cLov.setAdditionalData2(scn.getAdditionalData2()); 
			indexedUnSync.get(scn.getParentId()).getDetails().add(cLov);
			 
			
		}
		
		return indexedUnSync.values() ; 
		
		
		
	}



	@Override
	public Common2ndLevelLOVHeader getModifiedLOV(String localizationCode,
			LOV2ndLevelRequestArgument dataRequest) throws UnknownLookupValueProviderException, CacheStillUpToDateException , Exception{
		Base2ndLevelLOVProvider provider =  this.selfRegisterLovManager.get2ndLevelProvider(dataRequest.getLookupId().getId());
		if(provider==null)
			 throw new UnknownLookupValueProviderException("Lookup value untuk :"   + dataRequest.getLookupId().getId() +",tidak di temukan. Cek apakah anda salah id, atau item ini belum di registerd di server"
				, dataRequest.getLookupId().getId()	)   ;
		return  provider.getLookupValues(localizationCode, dataRequest);
	}



	@Override
	public Common2ndLevelLOVHeader getSameParent2ndLevelLOV(
			String localizationCode,
			LOV2ndLevelRequestArgument lovRequestArgument)
			throws UnknownLookupValueProviderException,
			CacheStillUpToDateException, Exception {
		Base2ndLevelLOVProvider provider =  this.selfRegisterLovManager.get2ndLevelProvider(lovRequestArgument.getLookupId().getId());
		if(provider==null)
			 throw new UnknownLookupValueProviderException("Lookup value untuk :"   + lovRequestArgument.getLookupId().getId() +",tidak di temukan. Cek apakah anda salah id, atau item ini belum di registerd di server"
				, lovRequestArgument.getLookupId().getId()	)   ;
		return provider.getSameLevelLookupValues(localizationCode, lovRequestArgument.getParentLovValueId(), lovRequestArgument.getCacheVersion());
	}


}
