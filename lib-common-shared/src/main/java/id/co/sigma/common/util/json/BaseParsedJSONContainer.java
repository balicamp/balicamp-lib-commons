package id.co.sigma.common.util.json;

import id.co.sigma.common.data.serializer.DateSerializer;
import id.co.sigma.common.data.serializer.json.ObjectSerializerManager;
import id.co.sigma.common.util.ObjectGeneratorManager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public abstract class BaseParsedJSONContainer implements ParsedJSONContainer{

	
	
	
	
	private static final String ARRAY_TYPE_FQCN_PREFIX ="[L";
	
	
	
	
	
	public static void main (String[] args){
		
		String fqcn =String[].class.getName() ; 
		System.out.println(fqcn
				.substring(2 , fqcn.length()-1));
		
		
	}
	
	
	
	
	/**
	 * sederhana. cari dari array
	 **/
	protected Object getArrayObject (String key, String expectedResultFQCN) {
		String actualType  = expectedResultFQCN.substring(2,expectedResultFQCN.length()-1);
		if (!ObjectSerializerManager.isSimpleObject(actualType)){
			Object [] s =getAsArrayByFQCN(key, actualType);  
			return  s;
		}
		if (   "java.lang.String".equals(  actualType)){
			return getAsStringArray(key);
		} 
		else if (java.math.BigInteger.class.getName().equals(expectedResultFQCN)){
			return getAsArrayOfIntegers( key);
		}else if ( java.math.BigDecimal.class.getName().equals(expectedResultFQCN)){
			return getAsArrayOfBigDecimals(key) ;
		}
		else if ( Long.class.getName().equals(expectedResultFQCN)|| long.class.getName().equals(expectedResultFQCN)){
			return getAsArrayOfLongs(key);
		}
		else if ( Integer.class.getName().equals(expectedResultFQCN) || int.class.getName().equals(expectedResultFQCN))
			return getAsArrayOfIntegers(key);
		
		else if ( Short.class.getName().equals(expectedResultFQCN) || short.class.getName().equals(expectedResultFQCN))
			return getAsArrayOfShorts(key);
		else
			System.err.println("maaf, anda melewatkan tipe class : actualType(array). object ini gagal di serialisikan");
			 
		return null ; 
				
		
			
				
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key, String expectedResultFQCN) {
		
		if ( expectedResultFQCN== null|| expectedResultFQCN.isEmpty()){
			 System.out.println(  "FQCN utnuk key : " + key + " null atau 0 length. mohon evaluasi json berikut ini  : " + this.getJSONString());
			
			return null ;
		}
		
		
		try {
			
			if ( expectedResultFQCN.startsWith(ARRAY_TYPE_FQCN_PREFIX)){
				
				Object s = getArrayObject(key, expectedResultFQCN);
				if ( s== null)
					return null ; 
				return (T) s ; 
				
				
				
			}else{
				try{
					if ( Integer.class.getName().equals(expectedResultFQCN) || int.class.getName().equals(expectedResultFQCN)){
						
						return (T)getAsInteger(key);
					}else if ( Float.class.getName().equals(expectedResultFQCN) || float.class.getName().equals(expectedResultFQCN)){
						Double d = getAsNumber(key)  ;
						if ( d== null)
							return null ; 
						return (T) new Float(d.floatValue());
					}
					else if ( Double.class.getName().equals(expectedResultFQCN) || double.class.getName().equals(expectedResultFQCN)){
						return (T)    getAsNumber(key) ;
					}
					else if ( Long.class.getName().equals(expectedResultFQCN) || long.class.getName().equals(expectedResultFQCN)){
						Double d =  getAsNumber(key) ;
						if ( d== null)
							return null ; 
						return (T)  new Long ( d.longValue() );
					}
					else if ( Boolean.class.getName().equals(expectedResultFQCN) || boolean.class.getName().equals(expectedResultFQCN)){
						return (T)  getAsBoolean(key);
					}
					else if ( Short.class.getName().equals(expectedResultFQCN) || short.class.getName().equals(expectedResultFQCN)){
						
						Double d = getAsNumber(key) ; 
						if ( d== null)
							return null ; 
						return (T) new Short(d.shortValue()); 
					}
					else if ( BigInteger.class.getName().equals(expectedResultFQCN)  ){
							
		                                    Double val =  getAsNumber(key); 
							//String val = getAsString(key);
							if ( val== null )
								return null ; 
							return (T)  new BigInteger(val.intValue() +"");
						
						
					}
					else if ( BigDecimal.class.getName().equals(expectedResultFQCN)  ){
		                            Double val =  getAsNumber(key); 
		                            if ( val== null )
						return null; 
		                            return (T)  new BigDecimal(val);
					}
					else if ( String.class.getName().equals(expectedResultFQCN)  ){
						return (T)  getAsString(key);
					}
					else if ( Date.class.getName().equals(expectedResultFQCN)  ){
						return (T)  getAsDate(key);
					}
					
					Object o =  ObjectGeneratorManager.getInstance().instantiateSampleObject(expectedResultFQCN);
					if ( o instanceof IJSONFriendlyObject){
						@SuppressWarnings("rawtypes")
						IJSONFriendlyObject smpl = (IJSONFriendlyObject )o;
						return (T) getAsSubJSONObject(key, smpl);
					}
					
					
					System.out.println("object dengan tipe " + expectedResultFQCN + " tidak bisa di serialize kan as json. mohon di revire code anda. ");
					
					
					return null;
				}catch ( Exception exc){
					System.out.println(  "gagal membaca data dengan key " + key + ", fqcn : " + expectedResultFQCN + " , json : " + getJSONString() + "\nerror : "  + exc.getMessage() );
					
					return null ; 
				}
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace(); 
			return null ; 
		}
		
	}
	
	 @Override
	 public Integer getAsInteger(String key) {
	        Number swap = getAsNumber(key); 
	            if ( swap == null)
	                return null; 
	            return swap.intValue(); 
	                    
	    }
	 
	
	@Override
	public void put(String key, Integer value) {
		if ( value== null){
			putNull(key );
			return ; 
		}
		this.put(key, value.doubleValue());
		
	}

	@Override
	public void put(String key, BigInteger value) {
		if ( value== null){
			putNull(key );
			return ; 
		}
		this.put(key, value.doubleValue());
		
	}

	@Override
	public void put(String key, BigDecimal value) {
		if ( value== null){
			putNull(key );
			return ; 
		}
		this.put(key, value.doubleValue());
		
	}
	
	@Override
	public void put(String key, Double value) {
		if ( value== null){
			putNull(key );
			return ; 
		}
		this.put(key, value.doubleValue());
		
	}
	
	@Override
	public void put(String key, Long value) {
		if ( value== null){
			putNull(key );
			return ; 
		}
		this.put(key, value.doubleValue());
		
	}

	@Override
	public void put(String key, Float value) {
		if ( value== null){
			putNull(key );
			return ; 
		}
		this.put(key, value.doubleValue());
		
	}
	
	
	@Override
	public void put(String key, Short value) {
		if ( value== null){
			putNull(key );
			return ; 
		}
		this.put(key, value.doubleValue());
		
		
	}
	
	
	
	@Override
	public Date getAsDate(String key) {
		String dt =  getAsString(key);
		if ( dt== null|| dt.length()==0)
			return null ; 
		try {
			return SharedServerClientLogicManager.getInstance().getDateTimeParser().parse(dt, DateSerializer.DATE_TIME_SERIALIZER_PATTERN);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		 
		
		
	}
	
	@Override
	public void putIfJsonEnableObjects(String key, List<?> arrayOfData) {
		if ( arrayOfData== null|| arrayOfData.isEmpty()){
			putNull(key); 
			return ; 
		}
		
		boolean isJSON = true ;
		
		String fqcn = "" ; 
		
		for ( Object scn : arrayOfData){
			if ( scn!= null){
				if (   !(scn instanceof IJSONFriendlyObject) ){
					isJSON = false ;
					break;
				}
				String swap  = scn.getClass().getName() ;
				if ( fqcn.length()==0){
					fqcn = swap ;
				}else{
					if (! fqcn.equals(swap))
						throw new RuntimeException("array yang tidak di masukan tidak homogen, serialization dengan json versi saat ini belum bisa menangani array non homogen. mohon review code anda. class yang di temukan misal nya : " + swap + ", " + fqcn ); 
				}
				 
			}
		}
		if ( !isJSON){
			throw new RuntimeException("anda mencoba memasukan object yang non JSON Serializable langsung ke dalam object.proses ini di tolak") ; 
		}
		if ( fqcn.length()== 0){
			putNull(key); 
			return ; 
		}
		@SuppressWarnings("unchecked")
		List<IJSONFriendlyObject<?> > tmpp = (List<IJSONFriendlyObject<?> >)arrayOfData ; 
		put(key, tmpp); 
		
		
		
		
	}
	
	
	
	@Override
	public void put(String key, Date value) {
		if ( value== null){
			putNull(key);
			return ; 
		}
		this.put(key, SharedServerClientLogicManager.getInstance().getDateTimeParser().format(value, DateSerializer.DATE_TIME_SERIALIZER_PATTERN));
		
	}
	
	
	/**
	 * menaruh null dalam json actual
	 **/
	protected abstract void putNull ( String key ); 
	
	
	
	/**
	 * set JSON value null untuk key tertentu
	 **/
	public void setNull (String key ){
		putNull(key); 
	}
	
	
	@Override
	public String getArrayTypeFQCN(String arrayKey) {
		String key= arrayKey + JSON_ARRAY_DATA_TYPE_SUFFIX  ; 
		return getAsString(key);
	}
	
	@Override
	public <T extends IJSONFriendlyObject<T>> T[] getAsArray(String key,
			Class<T> expectedObjectClass) {
		 return (T[]) getAsArrayByFQCN(key , expectedObjectClass.getName());
	}
	
	
	@Override
	public Object[] getAsArrayByFQCN(String key,
			String fqcn) {
		

		if ( fqcn== null|| fqcn.isEmpty()){
			System.out.println( "FQCN utnuk key : " + key + " null atau 0 length. mohon evaluasi json berikut ini  : " + this.getJSONString());
			
			return null ;
		}
		
		
		ParsedJSONArrayContainer ac =  getAsArray(key);
		if ( ac== null||ac.length()==0)
			return null;
		
		 
		IJSONFriendlyObject sample = ObjectGeneratorManager.getInstance().instantiateSampleObject(fqcn);
		Object[] retval = ObjectGeneratorManager.getInstance().instantiateArray(fqcn, ac.length());
		for ( int i = 0 ; i < ac.length() ; i++){
			retval[i] = sample.instantiateFromJSON( ac.get(i)); 
		}
		return retval;
	}
	
	@Override
	public Date[] getAsArrayOfDates(String key) {
		ParsedJSONArrayContainer ac =  getAsArray(key);
		if ( ac== null||ac.length()==0)
			return null;
		Date[] retval = new Date[ac.length()];
		CrossDateTimeParser dtParser =  SharedServerClientLogicManager.getInstance().getDateTimeParser(); 
		for ( int i = 0 ; i < ac.length() ; i++){
			String str =  ac.getAsString(i);
			if ( str!= null && str.length()>0)
				try {
					retval[i] = dtParser.parse(str, DateSerializer.DATE_TIME_SERIALIZER_PATTERN);
				} catch (Exception e) {
					System.out.println("gagal cast string :" + str + "- menjadi date.error :" + e.getMessage());
					e.printStackTrace();
				}
		}
		
		return retval ; 
	}
	
	@Override
	public void appendToArray(String key, BigDecimal[] values) {
		if ( values== null|| values.length==0){
			putNull(key); 
			return ;
		}
		for ( BigDecimal scn : values){
			appendToArray(key, scn.doubleValue());
		}
	}
	
	
	@Override
	public void appendToArray(String key, BigInteger[] values) {
		if ( values== null|| values.length==0){
			putNull(key); 
			return ;
		}
		for ( BigInteger scn : values){
			appendToArray(key, scn.doubleValue());
		}
		
	}
	
	@Override
	public void appendToArray(String key, Date[] values) {
		if ( values== null|| values.length==0){
			putNull(key); 
			return ;
		}
		CrossDateTimeParser p = SharedServerClientLogicManager.getInstance().getDateTimeParser();
		String format = DateSerializer.DATE_TIME_SERIALIZER_PATTERN ; 
		for ( Date scn : values){
			appendToArray(key, p.format( scn , format));
		}
		
			
		
	}
	
	@Override
	public void appendToArray(String key, Integer[] values) {
		if ( values== null|| values.length==0){
			putNull(key); 
			return ;
		}
		for ( Integer scn : values){
			appendToArray(key, scn.doubleValue());
		}
	}
	
	@Override
	public BigDecimal[] getAsArrayOfBigDecimals(String key) {
		if ( !contain(key))
			return null ;
		ParsedJSONArrayContainer arr = getAsArray(key); 
		if ( arr== null||arr.length()==0)
			return null; 
		BigDecimal retval []= new BigDecimal[arr.length()];
		for ( int i = 0 ; i< arr.length();i++){
			Double d = arr.getAsNumber(i); 
			retval[i] = (d == null?null : new BigDecimal(d)); 
		}
		return retval;
	}
	
	@Override
	public BigInteger[] getAsArrayOfBigIntegers(String key) {
		if ( !contain(key))
			return null ;
		ParsedJSONArrayContainer arr = getAsArray(key); 
		if ( arr== null||arr.length()==0)
			return null; 
		BigInteger retval []= new BigInteger[arr.length()];
		for ( int i = 0 ; i< arr.length();i++){
			Double d = arr.getAsNumber(i); 
			retval[i] = (d == null?null : new BigInteger(d.intValue()+"")); 
		}
		return retval;
	}
	
	@Override
	public Float[] getAsArrayOfFloats(String key) {
		if ( !contain(key))
			return null ;
		ParsedJSONArrayContainer arr = getAsArray(key); 
		if ( arr== null||arr.length()==0)
			return null; 
		Float retval []= new Float[arr.length()];
		for ( int i = 0 ; i< arr.length();i++){
			Double d = arr.getAsNumber(i); 
			retval[i] = (d == null?null :  d.floatValue()); 
		}
		return retval;
	}
	
	@Override
	public Integer[] getAsArrayOfIntegers(String key) {
		if ( !contain(key))
			return null ;
		ParsedJSONArrayContainer arr = getAsArray(key); 
		if ( arr== null||arr.length()==0)
			return null; 
		Integer retval []= new Integer[arr.length()];
		for ( int i = 0 ; i< arr.length();i++){
			Double d = arr.getAsNumber(i); 
			retval[i] = (d == null?null :  d.intValue()); 
		}
		return retval;
	}
	
	@Override
	public Short[] getAsArrayOfShorts(String key) {
		if ( !contain(key))
			return null ;
		ParsedJSONArrayContainer arr = getAsArray(key); 
		if ( arr== null||arr.length()==0)
			return null; 
		Short retval []= new Short[arr.length()];
		for ( int i = 0 ; i< arr.length();i++){
			Double d = arr.getAsNumber(i); 
			retval[i] = (d == null?null :  d.shortValue()); 
		}
		return retval;
	}
	
	@Override
	public Long[] getAsArrayOfLongs(String key) {
		if ( !contain(key))
			return null ;
		ParsedJSONArrayContainer arr = getAsArray(key); 
		if ( arr== null||arr.length()==0)
			return null; 
		Long retval []= new Long[arr.length()];
		for ( int i = 0 ; i< arr.length();i++){
			Double d = arr.getAsNumber(i); 
			retval[i] = (d == null?null :  d.longValue()); 
		}
		return retval;
	}
        
	@Override
	public BigInteger getAsBigInteger(String key) {
		Double d = getAsNumber(key); 
		if ( d== null)
			return null ;  
			
		return new BigInteger(d.intValue() + "");
	}
}
