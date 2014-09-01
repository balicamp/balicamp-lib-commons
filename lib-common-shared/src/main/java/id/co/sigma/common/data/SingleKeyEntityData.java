package id.co.sigma.common.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * interface dengan primary key 1 object(object key sebaiknya tipe primitif)
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 22-sept-2012
 **/
public interface SingleKeyEntityData<KEY> extends IsSerializable , Serializable{
	/**
	 * primary key dari data
	 **/
	public abstract KEY getId();
	
	
	/**
	 * 
	 **/
	public abstract void  setId(KEY id);

}
