package id.co.sigma.common.data;



import id.co.sigma.common.data.app.SimpleDualControlData;

/**
 * base class untuk data yang di drive oleh system configuration table
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @param <D> entity class
 * @param <K> class enum untuk key locate data (key , desc ) dalam system parameters.
 */
public abstract class SystemParamTableConfigurationData<D extends SimpleDualControlData<?> , K extends SimpleSytemParameterDrivenValue<?>> extends SimpleDualControlData<D> 
	implements SystemParamDrivenClass<D, K>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4113225269075911282L;
	
	

	
	@Override
	public void setActiveFlag(String activeFlag) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getActiveFlag() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	@Override
	public boolean isEraseDataOnApproveErase() {
		return true;
	}
}
