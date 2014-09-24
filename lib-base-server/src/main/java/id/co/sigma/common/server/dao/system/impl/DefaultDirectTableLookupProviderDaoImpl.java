package id.co.sigma.common.server.dao.system.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import id.co.sigma.common.data.lov.CommonLOV;
import id.co.sigma.common.data.lov.CommonLOVHeader;
import id.co.sigma.common.data.lov.ILookupDetail;
import id.co.sigma.common.data.lov.ILookupHeader;
import id.co.sigma.common.data.lov.LOVSource;
import id.co.sigma.common.server.dao.DirectTableLookupProviderDao;
import id.co.sigma.common.server.dao.base.BaseJPADao;
import id.co.sigma.common.server.data.lookup.Common1LevelLOVDetail;
import id.co.sigma.common.server.data.lookup.Common1LevelLOVHeader;



/**
 * 
 * ini class default untuk lookup. ini set akses ke m_lookup_header + m_lookup_detail
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * 
 **/
public class DefaultDirectTableLookupProviderDaoImpl extends BaseJPADao implements DirectTableLookupProviderDao{

	
	private static final String LOV_IDS_PREFIX="IDS"; 
	@Override
	public List<ILookupHeader> getLookupHeaders(String localizationCode,
			Collection<String> lovIds) {
		if ( localizationCode==null|| lovIds.isEmpty())
			return null ; 
		String hqlSmt = "SELECT a from " + Common1LevelLOVHeader.class.getName() + " a where a.i18Key= :I18KEY and a.lovId in (" +genarateInStatement(LOV_IDS_PREFIX, lovIds.size())  + ")";
		Query q =  getEntityManager().createQuery(hqlSmt);
		q = fillInParams(q, LOV_IDS_PREFIX, lovIds);
		q =  q.setParameter("I18KEY", localizationCode);
		
		@SuppressWarnings("unchecked")
		List<Common1LevelLOVHeader> swap =  q.getResultList();
		if ( swap==null||swap.isEmpty())
			return null ; 
		ArrayList<ILookupHeader> headRet = new ArrayList<ILookupHeader>(); 
		for (Common1LevelLOVHeader scn : swap){
			CommonLOVHeader a = new CommonLOVHeader(); 
			headRet.add(a); 
			a.setCacheable(scn.isCacheable()); 
			a.setI18Key(localizationCode); 
			a.setLovId(scn.getLovId()); 
			a.setLovRemark(scn.getLovRemark()); 
			a.setSource(LOVSource.directFromLookupTable); 
			a.setVersion(scn.getVersion()); 
		}
		return headRet;
	}

	@Override
	public List<ILookupDetail> getLookupDetails(String localizationCode,
			Collection<String> lovIds) {
		if (lovIds==null||lovIds.isEmpty())
			return null ;
		String hqlSmt = "SELECT a from " + Common1LevelLOVDetail.class.getName() + " a where a.languageCode=:I18KEY and a.parentId in (" +genarateInStatement(LOV_IDS_PREFIX, lovIds.size())  + ") order by a.sequence ";
		Query q =  getEntityManager().createQuery(hqlSmt);
		q =  q.setParameter("I18KEY", localizationCode);
		q = fillInParams(q, LOV_IDS_PREFIX, lovIds);
		@SuppressWarnings("unchecked")
		List<Common1LevelLOVDetail> swap =  q.getResultList();
		if ( swap==null||swap.isEmpty())
			return null ; 
		ArrayList<ILookupDetail> headRet = new ArrayList<ILookupDetail>(); 
		for (Common1LevelLOVDetail scn : swap){
			CommonLOV a = new CommonLOV(); 
			headRet.add(a); 
			a.setAdditionalData1(scn.getAdditionalData1()); 
			a.setAdditionalData2(scn.getAdditionalData2()); 
			a.setDataValue(scn.getDataValue()); 
			a.setLabel(scn.getLabel()); 
			a.setParentId(scn.getParentId()); 
		}
		return headRet;
	}

}
