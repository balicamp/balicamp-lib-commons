package id.co.sigma.common.data.serializer.json;


import id.co.sigma.common.data.ObjectSerializer;
import id.co.sigma.common.data.serializer.IJSONWrapperObject;
import id.co.sigma.common.util.ObjectGeneratorManager;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONArrayContainer;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 *
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */

public class PrimitiveArrayDataWrapper implements IJSONFriendlyObject<PrimitiveArrayDataWrapper>, IJSONWrapperObject<Object[]>{

	
	private String fqcn ; 
	
	private Object[] actualData ; 
	
	
	public PrimitiveArrayDataWrapper(){}
	
	
	public PrimitiveArrayDataWrapper( ParsedJSONContainer container){
		PrimitiveArrayDataWrapper w = instantiateFromJSON(container);
		fqcn = w.fqcn ; 
		actualData = w.actualData ; 
		
		
	}
	
	
	/*
	public PrimitiveArrayDataWrapper ( String[] arr ){
		this.fqcn = String.class.getName() ;  
		this.actualData  = arr ; 		
	}
	
	
	public PrimitiveArrayDataWrapper ( Integer[] arr ){
		this.fqcn = Integer.class.getName() ;  
		this.actualData  = arr ; 		
	}
	public PrimitiveArrayDataWrapper ( Long[] arr ){
		this.fqcn = Long.class.getName() ;  
		this.actualData  = arr ; 		
	}
	public PrimitiveArrayDataWrapper ( Float[] arr ){
		this.fqcn = Float.class.getName() ;  
		this.actualData  = arr ; 		
	}
	public PrimitiveArrayDataWrapper ( Double[] arr ){
		this.fqcn = Double.class.getName() ;  
		this.actualData  = arr ; 		
	}
	
	public PrimitiveArrayDataWrapper ( Date[] arr ){
		this.fqcn = Date.class.getName() ;  
		this.actualData  = arr ; 		
	}
	
	public PrimitiveArrayDataWrapper ( BigDecimal[] arr ){
		this.fqcn = BigDecimal.class.getName() ;  
		this.actualData  = arr ; 		
	}
	
	
	public PrimitiveArrayDataWrapper ( BigInteger[] arr ){
		this.fqcn = BigDecimal.class.getName() ;  
		this.actualData  = arr ; 		
	}
	
	*/
	public PrimitiveArrayDataWrapper (   Object[] arr ){
		if ( arr== null || arr.length==0){
			throw new RuntimeException("Versi ini tidak mengijinkan null dalam konstruktor. mohon di recheck validasi anda");
		}
		this.actualData = arr ; 
		for ( Object scn : arr){
			if ( scn!= null ){
				this.fqcn = scn.getClass().getName(); 
				if (! ObjectSerializerManager.isSimpleObject(fqcn)){
					throw new RuntimeException("object :" + fqcn + " tidak bisa di terima. hanya simple object di ijinkan lewat ke class ini" ); 
				}
			}
		}
	}
	
	
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put(FQCN_MARKER_KEY, fqcn); 
		
		if ( actualData!= null){
			ObjectSerializer serializer =  ObjectSerializerManager.getInstance().getSerializer(fqcn);
			String [] arr = new String[actualData.length];
			for ( int i = 0 ; i < actualData.length ; i++){
				if ( actualData[i]!= null)
					arr[i] = serializer.serialize(actualData[i]);
			}
			jsonContainerData.appendToArray(ACTUAL_ARRAY_DATA_KEY, arr); 
			jsonContainerData.put(IS_ARRAY_MARKER_PROPERTY, true);
		}
	}

	@Override
	public PrimitiveArrayDataWrapper instantiateFromJSON(ParsedJSONContainer jsonContainerData) {
		PrimitiveArrayDataWrapper retval = new PrimitiveArrayDataWrapper( ); 
		
		
		retval.fqcn   =  jsonContainerData.getAsString( FQCN_MARKER_KEY );
		ObjectSerializer serializer =  ObjectSerializerManager.getInstance().getSerializer(retval.fqcn );
		if ( serializer== null){
			System.err.println("serializer untuk object : " + retval.fqcn  + ", tidak di temukan");
			return null ; 
		}
		ParsedJSONArrayContainer cArr =  jsonContainerData.getAsArray(ACTUAL_ARRAY_DATA_KEY);
		if ( cArr!= null &&  cArr.length()>0 ){
			retval.actualData = ObjectGeneratorManager.getInstance().instantiateArray(retval.fqcn, cArr.length()) ; //  [cArr.length()];
			for ( int i=0; i< cArr.length();i++){
				retval.actualData[i] =serializer.deserialize( cArr.getAsString(i));
			}
		}
		return retval;
	}


	@Override
	public Object[] getActualData() {
		return this.actualData;
	}
	
	
	

}
