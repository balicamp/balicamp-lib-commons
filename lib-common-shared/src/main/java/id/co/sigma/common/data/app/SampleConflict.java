package id.co.sigma.common.data.app;

import id.co.sigma.common.security.domain.Branch;

/**
 * 
 * @author <a href="mailto:gusti.darmawan@sigma.co.id">Eka Darmawan</a>
 */
public class SampleConflict {
	
	
	
	public String getMessage () {
		return "hello world";
	}
	
	public Branch getDataBranch(){
		return new Branch();
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}