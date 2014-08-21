package id.co.sigma.common.form;



/**
 * panel yang bisa <i>safely</i> disposed
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface DisposeablePanel {

	/**
	 * dispose panel
	 **/
	public void dispose () ; 

	/**
	 * mendaftarkan child yang disposable dari. ini di chain, jadinya kalau di dispose berantai, semua child akan ke dispose
	 */
	public void registerDisposableChild (DisposeablePanel disposableChild);

	/**
	 * kontra dari #registerDisposableChild, ini untuk meremove widget dari disposable panel. ini di pergunakan dalam usecase widget di detach dari parent
	 */
	public void unregisterDisposableChild(DisposeablePanel disposableChild) ; 



	/**
	 * parent dari disposable panel
	 */
	public DisposeablePanel getParentDisposablePanel  ();



	/**
	 * setter parent dari disposable panel
	 */
	public void setParentDisposablePanel(DisposeablePanel parent);

}
