/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.data.query;

/**
 *
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseGridDataProviderNativeSql {
    
    
    
    /**
     * ini di tambahkan ke dalam 
     */
    public static final String QUERY_PARAM_PREFIX="NATIVE_QPARAM_"; 
    
    
    /**
     * generate select data statement. paging menjadi tanggung jawab anda. 
     * <ol>
     * <li>statement select di ijinkan mempergunakan notasi <strong>:</strong> untuk parameter. Misalnya :<br/>
     * select a.* from t_table a where a.id =:NATIVE_QPARAM_ID 
     * 
     * sebagai catatan : NATIVE_QPARAM_ di ambil dari 
     * </li>
     * 
     * <li>parameter yang di inject dengan 2 pola : </li>
     * </ol>
     * @param dataIdAsStingStringSelectColumnName ini adalah dari data. di buat menjadi single string. kalau misalnya table berisi beberapa column, maka data harus di concat. Ini sekaligus akan menjadi data id di client
     * @param fetchedFieldNames field-field yang di fetch untuk di lemparkan ke client. 
     * @param fetchSize ukuran data di baca
     * @param firstRowPositionToFetch posisi row pertama yang akan di baca. ini untuk paging, data di ambil dari index berapa, dengan ukuran pembacaan berapa
     * @param queryFilters filters untuk mengquery data
     * @param sortArguments argument untuk men-sort data. sort by field apa
     * 
     */
    public abstract  String generateDataSql(  String dataIdAsStingStringSelectColumnName ,   String[] fetchedFieldNames , int fetchSize , int firstRowPositionToFetch , SimpleQueryFilter[] queryFilters , SimpleSortArgument[] sortArguments); 
    
    /**
     * generate count sql statement
     * @param aliasForCountColumnName nama alias untuk count column name,select statement anda harus comply dengan ini. contoh : <br/><code>
     * aliasForCountColumnName = cnt<br/>
     * select statement anda harus spt ini :<br/> 
     * select count(a.kode_unit) cnt from ALL_SAMPLE 
     * 
     * 
     * </code>
     */
    public abstract String generateCountSql( SimpleQueryFilter[] queryFilters , String aliasForCountColumnName);
    
    
    
    
}
