package id.co.sigma.common.data.lov;

public interface ILookupDetail {

	
	/**
	 * id parent LOV
	 **/
	public String getParentId();
	/**
	 * value dari parameter
	 **/
	public abstract String getDataValue();
	
	/**
	 * data tambahan 2
	 **/
	public abstract String getAdditionalData2();

	/**
	 * extended data 1
	 **/
	public abstract String getAdditionalData1();

	

	/**
	 * label parameter. visible bagi user
	 **/
	public abstract String getLabel();

}