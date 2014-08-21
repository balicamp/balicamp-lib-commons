package id.co.sigma.common.rpc.common;

import java.math.BigInteger;
import java.util.List;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.rpc.JSONSerializedRemoteService;
import id.co.sigma.common.util.json.IJSONFriendlyObject;

/**
 * RPC service general purpose, ini di pergunakan untuk akses method yang shared.bisa di pergunakan berbagai macam
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface GeneralPurposeRPC extends JSONSerializedRemoteService{
	
	
	
	/**
	 * select data dengan ke page. data di akases dengan FQCN dari data. 
	 * <ol>
	 * <li>data yang di request merupakan data yang di JPA mapped, bukan DTO</li>
	 * <li>data di filter dengan filter sederhana, client perlu menyediakan filter + sorts</li>
	 * </ol>
	 * 
	 **/
	public PagedResultHolder<IJSONFriendlyObject<?>> getPagedData (String objectFQCN , SimpleQueryFilter[] filters , SimpleSortArgument[] sorts , int page , int pageSize) throws Exception ;
	
	
	/**
	 * insert object baru ke dalam database
	 * @param newObject 
	 */
	public void insertData (IJSONFriendlyObject<?> newObject)  throws Exception ; 
	
	
	/**
	 * mencari data dengan big integer ID
	 **/
	IJSONFriendlyObject<?> getObjectByBigInteger ( String objectFQCN  ,  BigInteger objectId )  throws Exception;
	
	
	
	/**
	 * select data dengan id. ID berupa long.ini hanya bekerja untuk JPA object
	 */
	IJSONFriendlyObject<?> getObjectById ( String objectFQCN  ,  Long objectId )  throws Exception;
	
	
	/**
	 * membaca data dengan key string
	 */
	IJSONFriendlyObject<?> getObjectById ( String objectFQCN  ,  String objectId )  throws Exception;
	
	
	
	
	
	/**
	 * select data dengan id. ID berupa Integer. ini hanya bekerja untuk JPA object
	 * @param objectFQCN FQCN dari object
	 */
	IJSONFriendlyObject<?> getObjectById ( String objectFQCN  ,  Integer objectId )  throws Exception;
	
	
	
	/**
	 * generate double submit avoidence token. Token ini di akan di rematch pada saat request sampai ke server. methode nya kurang lebih begini : 
	 * <ol>
	 * <li>pada awal, panel aktiv musti request token dan cache dalam variable</li>
	 * <li>panel musti menyimpan token</li>
	 * <li>pada saat RPC di kirim, token ini musti di kirimkan juga ke server</li>
	 * </ol>
	 * 
	 * @return token
	 */
	public String generateDoubleSubmitAvoidenceToken () ; 

}
