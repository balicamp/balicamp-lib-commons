package id.co.sigma.common.server.lov;

import javax.annotation.PostConstruct;

/**
 * 
 * LOV yang self registered
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public abstract class BaseSelfRegister2ndLevelLOVProvider<KEY , PARENTVALUE> extends Base2ndLevelLOVProvider<KEY , PARENTVALUE>{

	@org.springframework.beans.factory.annotation.Autowired
	protected ISelfRegisterLovManager lovManager ; 
	
	
	@PostConstruct
	protected void postConstuct(){
		lovManager.register(this);
	}
}
