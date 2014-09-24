package id.co.sigma.common.server.lov;

import id.co.sigma.common.data.ICreateAndModifyAuditedObject;

/**
 * generic LOV untuk tipe yang create + modify audited object
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 *@author <a href="mailto:gede.wibawa@sigma.co.id">Dode</a>
 */
public abstract class GenericAuditTrailedObjectCustomLOVProvider<KEY, DATA extends ICreateAndModifyAuditedObject>  extends GenericCustomLOVProvider<KEY, DATA>{

	protected String getVersionFromData(DATA data) {
		return data.getModifiedOn() !=null ? data.getModifiedOn().getTime() + "" : 
			data.getCreatedOn().getTime() + ""  ;
	};
	@Override
	protected boolean isNewerVersionNumber(String currentVersion,
			String scannedVersion) {
		if ( currentVersion!= null && scannedVersion== null)
			return false ; 
		if ( currentVersion== null && scannedVersion!= null)
			return true ; 
		long cur = Long.parseLong(currentVersion); 
		long scn = Long.parseLong(scannedVersion); 
		return scn> cur;
	}
	
}
