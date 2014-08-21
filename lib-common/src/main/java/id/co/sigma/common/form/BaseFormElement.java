package id.co.sigma.common.form;



/**
 * interface form element. ini untuk menarik ke base item-item yang di anggap bisa seragam
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface BaseFormElement {


	/**
	 * status kontrol, enable atau tidak
	 **/
	public boolean isEnabled() ;


	/**
	 * set control ke state enable/disable(sesuai parameter)
	 * @param enable true = enable
	 **/
	public void setEnabled(boolean enable);
	
	
	/**
	 * set id(DOM Id) dari node,old school HTML ID. bagus untuk automated test
	 **/
	public void setDomId(String id);
	
	/**
	 * get id dari node
	 **/
	public String getDomId();
	

}
