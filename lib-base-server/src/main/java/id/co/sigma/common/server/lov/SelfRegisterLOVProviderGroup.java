package id.co.sigma.common.server.lov;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * LOV provider group yang bisa self registering. agar bisa Self Registered class harus comply berikut ini
 *  <ol>
 * <li>di kasi annotation {@link org.springframework.beans.factory.annotation.Autowire}</li>
 * <li>class di annotate {@link org.springframework.context.annotation.Lazy} dengan value=<i>false</i>.Lazy bean akan menyebabkan service tidak di kenali(pemanggilan RPC akan mere turn page not found)</li>
 * 
 * </ol>
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa(gede.sutarsa@gmail.com)</a>
 * @version $Id
 * @since 5 aug 2012
 **/
public abstract class SelfRegisterLOVProviderGroup extends CustomLOVProviderGroup{

	@Autowired
	protected ISelfRegisterLovManager lovManager ;
	
	@PostConstruct
	protected void runRegisterTask(){
		lovManager.register(this); 
	}
}
