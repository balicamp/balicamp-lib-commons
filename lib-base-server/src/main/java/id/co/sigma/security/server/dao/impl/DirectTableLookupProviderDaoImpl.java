package id.co.sigma.security.server.dao.impl;

import id.co.sigma.common.data.lov.ILookupDetail;
import id.co.sigma.common.data.lov.ILookupHeader;
import id.co.sigma.common.server.dao.DirectTableLookupProviderDao;
import id.co.sigma.common.server.dao.base.BaseJPADao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;




public class DirectTableLookupProviderDaoImpl extends BaseJPADao implements DirectTableLookupProviderDao{
	
	@Override
	public List<ILookupHeader> getLookupHeaders(String localizationCode,
			Collection<String> lovIds) {
		if(lovIds==null||lovIds.isEmpty())
			return null ;
		List<String> useGenericTable = new ArrayList<String>();		
		for ( String scn : lovIds){			
			useGenericTable.add( scn);
		}
		ArrayList<ILookupHeader> merged = new ArrayList<ILookupHeader>();
		if (! useGenericTable.isEmpty()){
			@SuppressWarnings("unchecked")
			List<ILookupHeader> directSrc =  getEntityManager().createNamedQuery("LOV::GET_LOV_BY-I18AND_IDS")
						.setParameter("I18", localizationCode)
						.setParameter("IDS", lovIds).getResultList();
			if ( directSrc!=null)
				merged.addAll(directSrc);
		}		
		
		
		return merged;
	}

	@Override
	public List<ILookupDetail> getLookupDetails(String localizationCode,
			Collection<String> lovIds) {
		if (lovIds==null||lovIds.isEmpty())
			return null ; 
		
			String hql =
					"select a   " +
					" from  " +
					"	LookupDetail a inner join fetch a.header " +
					" where " +
					"	a.header.i18Key =:I18 " +
					"	AND  a.header.id in ( " + this.genarateInStatement("IDS", lovIds.size())+")" +
					" order by " +
					"		a.sequence asc ";
			Query q = getEntityManager().createQuery(hql)
					.setParameter("I18", localizationCode);
			@SuppressWarnings("unchecked")
			List<ILookupDetail> swaps =   this.fillInParams(q, "IDS", lovIds)
						.getResultList();
			return swaps ; 
				
	}
}
