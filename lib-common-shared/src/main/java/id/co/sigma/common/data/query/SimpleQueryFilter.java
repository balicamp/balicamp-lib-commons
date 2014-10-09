package id.co.sigma.common.data.query;

import id.co.sigma.common.data.TransportSimpleGridDataWrapper;
import id.co.sigma.common.data.reflection.ClientReflectableClass;
import id.co.sigma.common.util.json.CrossDateTimeParser;
import id.co.sigma.common.util.json.SharedServerClientLogicManager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;




import com.google.gwt.user.client.rpc.IsSerializable;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;




/**
 * Base class untuk simple query
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
@ClientReflectableClass
public class SimpleQueryFilter  implements IsSerializable , IJSONFriendlyObject<SimpleQueryFilter>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8606979867899438046L;
	
	
	
	/**
	 * pattern yang di pergunakan untuk mengirim data dalam bentuk date as string. ini di pergunakan dalam field {@link TransportSimpleGridDataWrapper#getValues()}
	 **/
	public static final String DATE_TIME_TO_STRING_SERIALIZATION_PATTERN = "yyyy-MM-dd HH:mm:ss:S";



	/**
	 * pattern yang di pergunakan untuk mengirim data dalam bentuk date as string. ini di pergunakan dalam field {@link TransportSimpleGridDataWrapper#getValues()}
	 **/
	public static final String DATE_TO_STRING_SERIALIZATION_PATTERN = "yyyy-MM-dd HH:mm:ss:S";
	
	
	
	/**
	 * versi ini kalau 																																																																																																																																																																																																																																		yang di perhitungkan cuma date
	 **/
	public static final String DATE_ONLY_STRING_SERIALIZATION_PATTERN = "yyyy-MM-dd";
	
	protected String field ; 
	protected SimpleQueryFilterOperator operator ; 
	protected String filter ; 

	
	/**
	 * tipe filter class. sesuai dengan yang di set ke dalam variable {@link #filter}
	 **/
	protected String filterTypeClass ;
	
	
	/**
	 * berlaku kalau tipe date. jadinya jam + menit di sertakan atau tidak
	 */
	private boolean includeHourOnParam  =false ; 
	
	public SimpleQueryFilter(){
		
	}
	
	
	
	public SimpleQueryFilter(String field,
			SimpleQueryFilterOperator operator, String filter) {
		super();
		this.field = field;
		this.setOperator(  operator);
		setFilter(filter); 
	}
	
	
	public SimpleQueryFilter(String field,
			SimpleQueryFilterOperator operator, Integer filter) {
		super();
		this.field = field;
		this.setOperator(  operator);
		setFilter(filter); 
	}
	
	
	
	
	
	public SimpleQueryFilter(String field,
			SimpleQueryFilterOperator operator, BigInteger filter) {
		super();
		this.field = field;
		this.setOperator(  operator);
		setFilter(filter); 
	}
	
	
	
	public SimpleQueryFilter(String field,
			SimpleQueryFilterOperator operator, Long filter) {
		super();
		this.field = field;
		this.setOperator(  operator);
		setFilter(filter); 
	}
	
	


	public SimpleQueryFilter(String field,
			SimpleQueryFilterOperator operator, Boolean filter) {
		super();
		this.field = field;
		this.setOperator(  operator);
		setFilter(filter); 
	}
	
	public SimpleQueryFilter(String field,
			SimpleQueryFilterOperator operator, Date filter) {
		super();
		this.field = field;
		if ( SimpleQueryFilterOperator.dateBetween.equals(operator)){
			this.setFilter(filter, filter);
		}else{
			setFilter(filter); 
		}
		this.setOperator( operator);
		
	}
	public SimpleQueryFilter(String field,String[] inFilters ) {
		super(); 
		this.field = field ; 
		this.operator = SimpleQueryFilterOperator.fieldIn ; 
		setFilter(inFilters); 
		
		
	}
	
	
	public SimpleQueryFilter(String field,Integer[] inFilters ) {
		super(); 
		this.field = field ; 
		setFilter(inFilters); 
	}
	
	
	public SimpleQueryFilter(String field,BigInteger[] inFilters ) {
		super(); 
		this.field = field ; 
		setFilter(inFilters); 
	}
	
	
	
	
	public SimpleQueryFilter(String field,Long[] inFilters ) {
		super(); 
		this.field = field ; 
		setFilter(inFilters); 
	}

	
	
	
	public SimpleQueryFilter(String field, Date dateFrom , Date dateTo ) {
		super() ; 
		setField(field); 
		setOperator(SimpleQueryFilterOperator.dateBetween);
		setFilter(dateFrom, dateTo);
	}
	
	
	
	
	

	public String getField() {
		return field;
	}

	public final void setField(String field) {
		this.field = field;
	}

	public SimpleQueryFilterOperator getOperator() {
		return operator;
	}
	
	public final void setOperator(SimpleQueryFilterOperator operator) {
		this.operator = operator;
		if ( SimpleQueryFilterOperator.fieldIsNull.equals(  this.operator) || SimpleQueryFilterOperator.fieldIsNotNull.equals(this.operator)){
			setFilter("null");
		}
	}

	public String getFilter() {
		return filter;
	}

	public final void setFilter(String filter) {
		this.filter = filter;
		this.filterTypeClass=String.class.getName();
	}
	
	public void assignFilterWorker(Object filter){
		if ( filter==null)
			this.filter=null;
		else{
			this.filterTypeClass= filter.getClass().getName();
			if ( filter instanceof Date){
				this.setFilter(( Date)filter);
			}
			else {
				this.filter=filter.toString();
			}
			
		}	
	}
	
	
	/**
	 * set filter.ini tidak bekerja dnegan like , tipe data long
	 **/
	public void setFilter(Long filter){
		assignFilterWorker(filter);	
	}
	
	/**
	 * set filter.ini tidak bekerja dnegan like , tipe data Integer
	 **/
	public void setFilter(Integer filter){
		assignFilterWorker(filter);	
	}
	/**
	 * set filter.ini tidak bekerja dnegan like , tipe data Float
	 **/
	public void setFilter(Float filter){
		assignFilterWorker(filter);	
	}
	/**
	 * set filter.ini tidak bekerja dnegan like , tipe data BigDecimal
	 **/
	public void setFilter(BigDecimal filter){
		assignFilterWorker(filter);	
	}
	/**
	 * set filter.ini tidak bekerja dnegan like , tipe data BigInteger
	 **/
	public final void setFilter(BigInteger filter){
		assignFilterWorker(filter);	
	}
	/**
	 * set filter.ini tidak bekerja dnegan like , tipe data Boolean
	 **/
	public final void setFilter(Boolean filter){
		assignFilterWorker(filter);	
	}
	/**
	 * set filter.ini tidak bekerja dnegan like , tipe data Date
	 **/
	public final void setFilter(Date filter){
		filterTypeClass = Date.class.getName();
		if ( filter==null)
			this.filter=null;
		else
			this.filter = SharedServerClientLogicManager.getInstance().getDateTimeParser().format(filter,  DATE_ONLY_STRING_SERIALIZATION_PATTERN);
	}
	
	
	
	
	/**
	 * add by dode
	 * set filter untuk date between operator
	 * @param dateFrom tanggal dari
	 * @param dateTo tanggal sampai
	 */
	public final void setFilter(Date dateFrom, Date dateTo) {
		filterTypeClass = String.class.getName();
		setOperator(SimpleQueryFilterOperator.dateBetween);
		if (dateFrom == null || dateTo == null) {
			this.filter = null;
		} else {
			CrossDateTimeParser parserTgl =  SharedServerClientLogicManager.getInstance().getDateTimeParser();
			if(parserTgl!=null){
				String parsedDateFrom = parserTgl.format(dateFrom, DATE_ONLY_STRING_SERIALIZATION_PATTERN);
				String parsedDateTo = parserTgl.format(dateTo, DATE_ONLY_STRING_SERIALIZATION_PATTERN);
				this.filter = parsedDateFrom + "dateseparator" + parsedDateTo;
			}else{
				this.filter=null;
			}
		}
	}

	/**
	* ditambahkan oleh wayan.ari
	*/
	public final void setFilterFullDate(Date dateFrom, Date dateTo) {
		filterTypeClass = String.class.getName();
		if (dateFrom == null || dateTo == null) {
			this.filter = null;
		} else {
			CrossDateTimeParser parserTgl =  SharedServerClientLogicManager.getInstance().getDateTimeParser(); 
			this.filter = parserTgl.format(dateFrom, DATE_TO_STRING_SERIALIZATION_PATTERN) + "dateseparator" + 
					parserTgl.format(dateTo, DATE_TO_STRING_SERIALIZATION_PATTERN);
		}
	}
	
	/**
	 * @author dode
	 * set filter array of string, untuk operator in dan not in
	 */
	public void setFilter(String[] arrFilter) {
		if (arrFilter == null || arrFilter.length == 0) {
			this.filter = "";
		} else {
			String comma = "";
			String filter = "";
			for (String scn : arrFilter) {
				if (filter.length() > 0) {
					comma = ",";
				}
				filter += comma + " '" + scn + "'";
				this.filter = filter;
			}
		}
		
		assignFilterWorker(filter);
		// gede sutarsa , 2 oct ini otomatis pakai operator in
		setOperator(SimpleQueryFilterOperator.fieldIn);
		
	}
	
	/**
	 * @author dode
	 * 
	 * set filter array of number, untuk operator in dan not in
	 */
	public void setFilter(Number[] arrFilter) {
		if (arrFilter == null || arrFilter.length == 0) {
			this.filter = "";
		} else {
			String comma = "";
			String swapFilter = "";
			for (Number scn : arrFilter) {
				if ( scn== null)
					continue ; 
				if (swapFilter.length() > 0) {
					comma = ",";
				}
				swapFilter += comma + " " + scn + "";
			}
			this.filter = swapFilter;
		}
		assignFilterWorker(filter);
		setOperator(SimpleQueryFilterOperator.fieldIn);
		// gede sutarsa , 2 oct ini otomatis pakai operator in
//				setOperator(SimpleQueryFilterOperator.fieldIn);
	}

	/**
	 * tipe filter class. sesuai dengan yang di set ke dalam variable {@link #filter}
	 **/
	public String getFilterTypeClass() {
		return filterTypeClass;
	}

	/**
	 * tipe filter class. sesuai dengan yang di set ke dalam variable {@link #filter}
	 **/
	public void setFilterTypeClass(String filterTypeClass) {
		this.filterTypeClass = filterTypeClass;
	}



	@Override
	public String toString() {
		return  "{\"field\":\""+field+"\" ,  \"operator\":\""+(operator!=null?operator.toString(): null)+"\" , \"filter\":\""+filter+"\" , \"filterTypeClass\":\""+filterTypeClass+"\" }"    ;
		/* "{\"field\":" + field + ", operator="
				+ operator + ", filter=" + filter + ", filterTypeClass="
				+ filterTypeClass + "]";*/
	}

    @Override
    public void translateToJSON(ParsedJSONContainer jsonContainerData) {
        jsonContainerData.put("field",getField());
        jsonContainerData.put("filter", getFilter());
        jsonContainerData.put("filterTypeClass", getFilterTypeClass()); 
        jsonContainerData.put("operator", getOperator().generateFullCode());
    }

    @Override
    public SimpleQueryFilter instantiateFromJSON(ParsedJSONContainer jsonContainer) {
        SimpleQueryFilter retval = new SimpleQueryFilter() ; 
        retval.setField(jsonContainer.getAsString("field"));
        retval.setFilter(jsonContainer.getAsString("filter"));
        retval.setFilterTypeClass(jsonContainer.getAsString("filterTypeClass"));
        retval.setOperator(SimpleQueryFilterOperator.generateFromString(jsonContainer.getAsString("operator")));
        
        return retval ; 
                 
    }
	
    
    /**
     * membuat tgl 00 dari suatu date. ini untuk membuat between statement
     */
    protected Date generateDate23 (Date date){
    	if ( date== null)
    		return null ; 
    	String tglOnly =  SharedServerClientLogicManager.getInstance().getDateTimeParser().format(date, DATE_ONLY_STRING_SERIALIZATION_PATTERN) +" 23:59:59:0"; 
    	try {
			return  SharedServerClientLogicManager.getInstance().getDateTimeParser().parse(tglOnly, DATE_TIME_TO_STRING_SERIALIZATION_PATTERN);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null ; 
		}
    }
    /**
     * membuat tgl 00 dari suatu date. ini untuk membuat between statement
     */
    protected Date generateDate00 (Date date){
    	if ( date== null)
    		return null ; 
    	String tglOnly =  SharedServerClientLogicManager.getInstance().getDateTimeParser().format(date, DATE_ONLY_STRING_SERIALIZATION_PATTERN) +" 00:00:00:0"; 
    	try {
			return  SharedServerClientLogicManager.getInstance().getDateTimeParser().parse(tglOnly, DATE_TIME_TO_STRING_SERIALIZATION_PATTERN);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null ; 
		}
    }
    
    /**
	 * berlaku kalau tipe date. jadinya jam + menit di sertakan atau tidak
	 */
    public void setIncludeHourOnParam(boolean includeHourOnParam) {
		this.includeHourOnParam = includeHourOnParam;
	}
    /**
	 * berlaku kalau tipe date. jadinya jam + menit di sertakan atau tidak
	 */
    public boolean isIncludeHourOnParam() {
		return includeHourOnParam;
	}
    
    
    
    /**
     * pola untuk date serialization
     */
    public String getDateSerializationPattern () {
    	return includeHourOnParam ? DATE_TIME_TO_STRING_SERIALIZATION_PATTERN  
    				: DATE_ONLY_STRING_SERIALIZATION_PATTERN ; 
    }
}
