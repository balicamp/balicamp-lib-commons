package id.co.sigma.common.server.service.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import id.co.sigma.common.data.app.DualControlEnabledData;



/**
 * 
 * 
 * sering nya, applikasi memerlukan data human readable dari field data. misal nya : 
 * table transaction limit cuma menyimpan : 
 * <ol>
 * <li>id dari role user</li>
 * 
 * </ol>
 * Agar di screen bisa di tampilkan role, keterangan role dll, maka harus ada class lain yang mencarikan informasi itu
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * 
 **/
public abstract class BaseDualControlAdditionalDataBinder<DATA extends DualControlEnabledData<?, ?>> implements InitializingBean{
	
	
	
	@Autowired
	protected IDualControlAdditionalDataBinderManager dualControlAdditionalDataBinderManager; 
	 
	
	/**
	 * bind additional data ke dalam object agar tampilan menjadi sesuai. contoh yang paling populer : 
         * kabupaten peru data kecamatan. binding ke variable ini di handle masing-masing melalui method ini
         * 
	 **/
	public abstract void bindAdditionalData (List<DATA> dataForClient); 
	
	
	/**
	 * bind data tunggal. additional data
	 **/
	public final   void bindAdditionalData ( DATA  dataForClient){
		if ( dataForClient== null)
			return ; 
		List<DATA> tmp = new ArrayList< DATA>();
		tmp.add(dataForClient);
		bindAdditionalData(tmp);
		
	}
	
	
	
	/**
	 * class yang di bind data dual control nya dengan external data
	 **/
	public abstract Class<DATA> getHandledClass () ; 
	
	

	
	@Override
	public void afterPropertiesSet() throws Exception {
		dualControlAdditionalDataBinderManager.register(this);
	}
}
