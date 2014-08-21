package id.co.sigma.common.server.service.system.lov;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import id.co.sigma.common.data.CoreLibLookup;
import id.co.sigma.common.data.entity.I18NTextGroup;
import id.co.sigma.common.data.lov.CommonLOV;
import id.co.sigma.common.data.lov.StrongTypedCustomLOVID;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.dao.system.ApplicationConfigurationDao;
import id.co.sigma.common.server.lov.BaseSelfRegisterLOVProvider;

/**
 * ini LOV provider untuk I18Groups
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public class I18NGroupLOVProvider extends BaseSelfRegisterLOVProvider{

	
	@Autowired
	private IGeneralPurposeDao generalPurposeDao ; 
	
	
	private List<CommonLOV> lovArray ;
	
	private final  SimpleSortArgument sorterArray[] =new SimpleSortArgument[]{
		new SimpleSortArgument("id",true)
	};
	
	private void readLOVData() {
		List<I18NTextGroup> groups =  generalPurposeDao.list(I18NTextGroup.class, sorterArray);
		String parentId = CoreLibLookup.i18TextGroup.toString() ; 
		lovArray = new ArrayList<CommonLOV>();
		if ( groups!=null&&!groups.isEmpty()){
			for ( I18NTextGroup scn : groups){
				CommonLOV b = new CommonLOV(); 
				b.setParentId(parentId);
				b.setDataValue( scn.getId());
				b.setLabel(scn.getDescription());
				lovArray.add(b);
			}
		}
		
		
	}
	
	@Override
	public List<CommonLOV> getLookupValues(String localizationCode) {
		if(lovArray==null)
			readLOVData();
		
		return lovArray;
	}

	@Override
	public boolean isCacheable() {
		return true;
	}

	@Override
	public String getVersion() {
		//FIXME: group blm cacheable
		return "1";
	}

	@Override
	public StrongTypedCustomLOVID getType() {
		return CoreLibLookup.i18TextGroup;
	}

}
