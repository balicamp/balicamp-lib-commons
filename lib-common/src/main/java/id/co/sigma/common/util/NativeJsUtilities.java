
package id.co.sigma.common.util;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public final class NativeJsUtilities {

	
	
	/**
	 * nama method untuk put object. ini method yang shared usage bersama lain nya
	 **/
	public final  static String SHARED_PUT_OBJECT_METHOD_NAME="sigma_put_object_js_worker";
	
	
	/**
	 * method untuk get object
	 **/
	public final  static String SHARED_GET_OBJECT_METHOD_NAME="sigma_get_object_js_worker";
	private static NativeJsUtilities instance ;
	
	private NativeJsUtilities(){
		putSharedWorker();
	}
	
	public static NativeJsUtilities getInstance() {
		if ( instance==null)
			instance= new NativeJsUtilities();
		return instance;
	}
	
	
	/**
	 * worker put object
	 **/
	private native void putSharedWorker ()/*-{
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_PUT_OBJECT_METHOD_NAME]=function (target, key , value){
			if ( target==null|| key==null|| key.length==0)
				return ;
			if ( key.indexOf(".")>-1){
				var arr = key.split(".");
				var current = target;
				for ( ix=0;ix<arr.length-1;ix++){
					current[arr[ix]]=current[arr[ix]]||{};
					current=current[arr[ix]];
				}
				current[arr[arr.length-1]]=value;
			}
			else{
				target[key]=value;
			}	
		};
		
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_GET_OBJECT_METHOD_NAME]=function (target, key ){
			if ( target==null|| key==null|| key.length==0)
				return ;
			if ( key.indexOf(".")>-1){
				var arr = key.split(".");
				var current = target;
				for ( ix=0;ix<arr.length-1;ix++){
					current[arr[ix]]=current[arr[ix]]||{};
					current=current[arr[ix]];
				}
				return current[arr[arr.length-1]];
			}
			else{
				return target[key];
			}		
		};
		
	}-*/;
	
	
	/**
	 * put object. pls pergunakan hanya dengan argument javascript object
	 * @param value sebaiknya hanya javascript
	 * @param key key dari object. bisa simple(misal <i>alamat</i>) atau nested object(misal : <i>id.co.sigma.office</i>)
	 * @param target ke mana data akan di taruh
	 **/
	public native void putObjectRawType (JavaScriptObject target , String key , Object value)/*-{
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_PUT_OBJECT_METHOD_NAME](target, key, value);
		//target[key]=value;
	}-*/;
	
	
	/**
	 * put POJO object.<span style="color:red"><strong>use it carefully</strong></span>. kalau native di lewakan di sini akan di boxing !!
	 * @param key key dari object. bisa simple(misal <i>alamat</i>) atau nested object(misal : <i>id.co.sigma.office</i>)
	 **/
	public native void putPOJOObject (JavaScriptObject target , String key , Object value)/*-{
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_PUT_OBJECT_METHOD_NAME](target, key, value);
	}-*/;
	/**
	 * worker untuk menaruh object dalam javascript object.object dalam bentuk String
	 * @param target js di mana data akan di taruh
	 * @param key key dari object. bisa simple(misal <i>alamat</i>) atau nested object(misal : <i>id.co.sigma.office</i>)
	 * @param value value yang akan di taruh dalam js object
	 **/
	public native void putObject (JavaScriptObject target , String key , String value)/*-{
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_PUT_OBJECT_METHOD_NAME](target, key, value);
	}-*/;
	
	/**
	 * menaruh int ke javascript object. method ini support nested key, misal person.name -> akan menaruh pada object person , field name
	 * @param target js object di mana data akan di taruh
	 * @param key key untuk menaruh object dalam js
	 * @param value nilai yang perlu di taruh dalam object
	 **/
	public native void putObject (JavaScriptObject target , String key , int value)/*-{
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_PUT_OBJECT_METHOD_NAME](target, key, value);
	}-*/;
	/**
	 * worker untuk menaruh float ke dalam javascript object
	 **/
	public native void putObject (JavaScriptObject target , String key , float value)/*-{
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_PUT_OBJECT_METHOD_NAME](target, key, value);
	}-*/;
	/**
	 * versi ini untuk menaruh boolean dalam raw javascript object
	 * @param target di mana data mau di taruh
	 * @param key key untuk menaruh data dalam javascript object
	 **/
	public native void putObject (JavaScriptObject target , String key , double value)/*-{
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_PUT_OBJECT_METHOD_NAME](target, key, value);
	}-*/;
	/**
	 * menaruh boolean object dalam raw js object
	 **/
	public native void putObject (JavaScriptObject target , String key , boolean value)/*-{
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_PUT_OBJECT_METHOD_NAME](target, key, value);
	}-*/;
	
	
	
	/**
	 * membaca boolean variable dari javascript object
	 * @param target di mana property di taruh
	 * @param key key untuk menaruh object dalam js
	 **/
	public native boolean getBoolean(JavaScriptObject target , String key)/*-{
		return !!$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_GET_OBJECT_METHOD_NAME](target, key);
	}-*/; 
	
	
	/**
	 * membaca data string dari javascript object
	 * @param target dari js object mana data akan di ambil
	 * @param key key untuk membaca string
	 **/
	public native String  getString(JavaScriptObject target , String key)/*-{
	
		var methodName =@id.co.sigma.common.util.NativeJsUtilities::SHARED_GET_OBJECT_METHOD_NAME;  
		try{
			var swap =$wnd[methodName](target, key);
			if ( swap==null)
				return null ; 
			return swap  +""; 
		}
		catch ( exc){
			if ( console!=null && (typeof console !=undefined)  && console.debug!=null && (typeof console.debug !=undefined) )
			  console.debug("error get string karena masalah ini :" + exc.message +",method name : " + methodName); 
		}
		
	}-*/; 
	
	
	
	
	/**
	 * membaca float dari javascript object
	 * @param target di js mana data di ambil
	 * @param key key untuk membaca data js
	 **/
	public native Float  getFloat(JavaScriptObject target , String key)/*-{
		var swap =$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_GET_OBJECT_METHOD_NAME](target, key);
		if ( swap==null)
			return null ; 
		if ( $wnd.isNaN( swap  /1) )
		 	return null ; 
		return swap/1 ;  	
	}-*/; 
	
	
	
	/**
	 * membaca integer dari object
	 **/
	public native Integer  getInt(JavaScriptObject target , String key)/*-{
		var swap =$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_GET_OBJECT_METHOD_NAME](target, key);
		if ( swap==null)
			return null ; 
		if ( $wnd.isNaN( swap  /1) )
		 	return null ; 
		return swap/1 ;
	}-*/; 
	
	
	
	/**
	 * menaruh data angka, namun di kirim dalam string. js engine akan konversi jadi angka kembali
	 **/
	public native void putStringButNumber (JavaScriptObject target , String key , String value)/*-{
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_PUT_OBJECT_METHOD_NAME](target, key, value/1);
	}-*/;
	
	public native void putObject (JavaScriptObject target , String key , JavaScriptObject value)/*-{
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_PUT_OBJECT_METHOD_NAME](target, key, value);
	}-*/;
	public native void putNullObject (JavaScriptObject target , String key )/*-{
		$wnd[@id.co.sigma.common.util.NativeJsUtilities::SHARED_PUT_OBJECT_METHOD_NAME](target, key, null);
	}-*/;
	
	
	
	
	/**
	 * push string ke dalam array
	 * @param target javascript array
	 * @param value value(string) untuk di push ke dalam array
	 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
	 **/
	public native void pushToArray(JavaScriptObject target, String value)/*-{
		target.push(value);
	
	}-*/;
	
	
	/**
	 * push int ke dalam array
	 **/
	public native void pushToArray(JavaScriptObject target, Integer value)/*-{
		target.push(value);
	
	}-*/;
	
	public native void pushToArray(JavaScriptObject target, Float value)/*-{
		target.push(value);
	
	}-*/;
	
	public native void pushToArray(JavaScriptObject target, JavaScriptObject value)/*-{
		target.push(value);
	
	}-*/;
	
	
	/**
	 * rubah dari java version date menjadi raw js date
	 **/
	public JavaScriptObject generateNativeJsDate(Date date){
		if ( date==null)
			return null ;
		//FIXME: pls di ujikan
		return generateRawDate(  date.getTime()+"");
		
	}

	private native JavaScriptObject generateRawDate (String mlsTime )/*-{
		var a = new Date();
		a.setTime(mlsTime/1);
		return a;
	
	}-*/;
	
	
	
	/**
	 * create text nodes
	 **/
	public native Element createTextNode(String text)/*-{
		return $doc.createTextNode(text);
	}-*/;
	
	
	
	
	/**
	 * menghitung panjang dari array
	 **/
	public native int countArrayLength (JavaScriptObject arrayObject) /*-{
		if ( arrayObject==null)
			return ; 
		return arrayObject.length ; 	
	
	}-*/;
	
	
	
	
	/**
	 * membaca string data dari array 
	 **/
	public native String fetchStringData (JavaScriptObject arrayObject, int index) /*-{
		return arrayObject[index]
	}-*/;
	
	
	
	/**
	 * menulis ke ke console log
	 **/
	public native void writeToBrowserConsole (String message)/*-{
		if ( $wnd.console ==null || typeof $wnd.console == undefined ) 
			return ; 
		if ( $wnd.console.debug ==null ||typeof $wnd.console.debug ==undefined ) 
		 	return ; 
		$wnd.console.debug(message);  	
	
	
	}-*/;
	
	
	
	/**
	 * worker utnuk menaruh variable ke dalam level window variable, data di taruh dengan key String
	 * @param key key untuk menaruh variable dalam level window
	 * @param rawJsObject raw js object yang akan di taruh dalam window level variable 
	 **/
	public  native  void putVariableToWindowVariable (String key, JavaScriptObject rawJsObject)/*-{
		$wnd[key] = rawJsObject ; 
	}-*/;
	
	
	/**
	 * worker untuk menarik variable dari level window. variable di akses dengan key dari variable
	 **/
	public  native  String getStringVariableToWindowVariable (String key )/*-{
		return $wnd[key] ; 
	}-*/;
	
	
	/**
	 * membaca reference ke window
	 **/
	public native JavaScriptObject getWindowElementReference ()/*-{
		return $wnd;
	}-*/;
}
