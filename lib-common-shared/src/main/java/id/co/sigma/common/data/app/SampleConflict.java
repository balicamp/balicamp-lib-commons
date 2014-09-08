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

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	public void Tests(){
		System.out.println("Hallo brow");
		System.out.println("Eka");
		System.out.println("Raka");
		System.out.println("Goesde");
	}
	
	public void test2(){
		System.out.println("tes 123");
		System.out.println("Tset Lagi");
	}
	
	
}
