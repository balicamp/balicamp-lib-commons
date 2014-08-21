package id.co.sigma.common.server.dao;

import id.co.sigma.common.data.lov.ILookupDetail;
import id.co.sigma.common.data.lov.ILookupHeader;

import java.util.Collection;
import java.util.List;



/**
 * dao yang harus anda sediakan untuk akses ke Lookup table generic dalam aplikasi
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 * @since 21-aug-2012
 **/
public interface DirectTableLookupProviderDao {
	
	
	
	/**
	 * membaca list of lookup header dengan list ID dari lookup
	 * @param lovIds collection of lookup ID yang perlu di load dari storage
	 **/
	public List<ILookupHeader> getLookupHeaders( String localizationCode ,  Collection<String> lovIds);
	
	
	/**
	 * membaca lookup detail dari storage. data wajib di sort berdasarkan field urutan Lookup
	 **/
	public List<ILookupDetail> getLookupDetails(String localizationCode , Collection<String> lovIds);
	
	

}
