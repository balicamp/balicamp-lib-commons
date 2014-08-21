package id.co.sigma.common.data.query;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum SimpleQueryFilterOperator implements IsSerializable {
	
	
	equal("=:PARAM", "eq" ) , 
	less("<:PARAM", "less"), 
	lessEqual("<=:PARAM"  , "le" ), 
	greater(">:PARAM"  , "greater"), 
	greaterEqual(">=:PARAM" , "ge") , 
	likeTailOnly("like", "like_end"), 
	likeFrontOnly("like" , "like_front"), 
	
	
	/**
	 * field yang di isikan null
	 **/
	fieldIsNull ("is null" , "is_null") ,
	
	/**
	 * start add by dode
	 */
	/**
	 * field yang di isikan null
	 **/
	fieldIsNotNull ("is not null" , "is_not_null") ,
	/**
	 * field yang di isikan null
	 **/
	notEqual ("<>:PARAM", "not_eq") ,
	/**
	 * field yang di isikan null
	 **/
	fieldIn ("in", "field_in") ,
	
	
	/**
	 * ini untuk panjang dari data. valid dalam versi hibernate
	 */
	fieldLengthEqual ( "length(:PARAM)" , "field_varchar_len"), 
	
	/**
	 * field yang di isikan null
	 **/
	fieldNotIn ("not in", "field_not_in") ,
	/**
	 * end add by dode
	 */
	
	/**
	 * versi ini kalau user tidak entry % , % akan di tambahkan di depan dan belakang
	 **/
	likeBothSide("like" , "like_both"), 
	/**
	 * ini kalau % di entry kan oleh user
	 **/
	likePercentProvided (" like :PARAM" , "like_custome"),
	
	dayEqual("day(PARAMDATE)=:PARAM" , "day_eq"),
	monthEqual("month(PARAMDATE)=:PARAM" , "month_eq"),
	yearEqual("year(PARAMDATE)=:PARAM", "year_eq"),
	
	/**
	 * add by dode
	 * menambahkan hour greater equal, minute greater equal, hour less equal, minute less equal 
	 */
	dayGreaterEqual("day(PARAMDATE)>=:PARAM", "day_great_eq"),
	monthGreaterEqual("month(PARAMDATE)>=:PARAM" , "month_great_eq"),
	yearGreaterEqual("year(PARAMDATE)>=:PARAM", "year_great_eq"),
	dayLessEqual("day(PARAMDATE)<=:PARAM", "day_less_eq"),
	monthLessEqual("month(PARAMDATE)<=:PARAM" , "month_less_eq"),
	yearLessEqual("year(PARAMDATE)<=:PARAM", "year_less_eq"),
	
	
	
	
	/**
	 * add by dode
	 * menambahkan hour greater equal, minute greater equal, hour less equal, minute less equal 
	 */
	hourGreaterEqual("hour(PARAMDATE) >= :PARAM","hour_great_eq"),
	minuteGreaterEqual("minute(PARAMDATE) >= :PARAM","minute_great_eq"),
	hourLessEqual("hour(PARAMDATE) <= :PARAM","hour_less_eq"),
	minuteLessEqual("minute(PARAMDATE) <= :PARAM","minute_less_eq") ,
	hourIn("hour(PARAMDATE) in", "hour_in"),
	
	/**
	 * tanggal antara 2
	 **/
	dateBetween ("between" , "between_date");
	
	
	
	 
	
	
	private String internalRepresentation ;
	
	private String code ; 
	
	/**
	 * dalam enum yang perlu di replace apa. default di buat PARAM
	 **/
	public static final String STRING_PARAM_TO_REPLACE ="PARAM";
	
	public static final String STRING_PARAM_DATE_TO_REPLACE ="PARAMDATE";
	
	
	static final Map<String, SimpleQueryFilterOperator> indexedEnum = new HashMap<String, SimpleQueryFilterOperator>(); 
	static {
		for ( SimpleQueryFilterOperator scn : SimpleQueryFilterOperator.values()){
			indexedEnum.put(scn.code  , scn);
		}
	}
	
	private SimpleQueryFilterOperator(String operator, String code ){
		this.internalRepresentation  = operator ; 
		this.code = code;
	}
	
	
	
	
	/**
	 * generate enum dari raw string. ini yang perlu di translasikan menjadi enum
	 **/
	public static SimpleQueryFilterOperator generateFromString (String rawString) {
		if ( !indexedEnum.containsKey(rawString))
			return null ; 
		return indexedEnum.get(rawString) ;
	}
	
	public String getCode() {
		return code;
	}
	@Override
	public String toString() {
		return internalRepresentation   ;
	}
        
        
        
        
        /**
         * fulll code dari enum
        */
        public String generateFullCode () {
            return     code ; 
        }

   
}
