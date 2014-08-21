package id.co.sigma.common.data.lov;

public interface ILookupHeader {

	public abstract String getLovId();

	public abstract String getLovRemark();

	public abstract String getVersion();

	public abstract boolean isCacheable();

	
	/**
	 * internalization code
	 **/
	public void setI18Key(String i18Key) ;
	/**
	 * internalization code
	 **/
	public String getI18Key();
		
}