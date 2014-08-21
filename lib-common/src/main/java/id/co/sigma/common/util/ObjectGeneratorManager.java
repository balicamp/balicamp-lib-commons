/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.util;

import id.co.sigma.common.data.serializer.AbstractObjectGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * collector object generator manager. ini untuk takle masalah kelemehan reflection
 * @author gps
 */
public class ObjectGeneratorManager {
    
    private static ObjectGeneratorManager instance ; 
    
     
    private Map<String , IObjectGenerator >  indexedgenerators ; 
    
    
    
    
    
    /**
     * flag, variale {@link #indexedgenerators} pernah di replace dengan map lain atau tidak. ini untuk optimasi
     **/
    private boolean indexerMapReplaced = false ; 
    
    
    
    
    private ObjectGeneratorManager(){
        indexedgenerators = new HashMap<String , IObjectGenerator >();
        indexedgenerators.put(String.class.getName(), new IObjectGenerator() {
			@Override
			public <T> T instantiateSampleObject(String objectFQCN) {
				if ( String.class.getName().equals(objectFQCN))
					return (T)"" ; 
				return null;
			}
			@Override
			public <T> T[] instantiateArray(String objectFQCN, int size) {
				if ( String.class.getName().equals(objectFQCN))
					return ( T[])new String[size] ; 
				return null;
			}
		}); 
        indexedgenerators.put(Integer.class.getName(), new IObjectGenerator() {
			@Override
			public <T> T instantiateSampleObject(String objectFQCN) {
				if ( Integer.class.getName().equals(objectFQCN))
					return (T)new Integer(0) ; 
				return null;
			}
			@Override
			public <T> T[] instantiateArray(String objectFQCN, int size) {
				if ( Integer.class.getName().equals(objectFQCN))
					return ( T[])new Integer[size] ; 
				return null;
			}
		}); 
         indexedgenerators.put(Float.class.getName(), new IObjectGenerator() {
			@Override
			public <T> T instantiateSampleObject(String objectFQCN) {
				if ( Float.class.getName().equals(objectFQCN))
					return (T)new Float(0) ; 
				return null;
			}
			@Override
			public <T> T[] instantiateArray(String objectFQCN, int size) {
				if ( Float.class.getName().equals(objectFQCN))
					return ( T[])new Integer[size] ; 
				return null;
			}
		}); 
         indexedgenerators.put(Double.class.getName(), new IObjectGenerator() {
			@Override
			public <T> T instantiateSampleObject(String objectFQCN) {
				if ( Double.class.getName().equals(objectFQCN))
					return (T)new Double(0) ; 
				return null;
			}
			@Override
			public <T> T[] instantiateArray(String objectFQCN, int size) {
				if ( Double.class.getName().equals(objectFQCN))
					return ( T[])new Double[size] ; 
				return null;
			}
		}); 
         indexedgenerators.put(Long.class.getName(), new IObjectGenerator() {
			@Override
			public <T> T instantiateSampleObject(String objectFQCN) {
				if ( Long.class.getName().equals(objectFQCN))
					return (T)new Double(0) ; 
				return null;
			}
			@Override
			public <T> T[] instantiateArray(String objectFQCN, int size) {
				if ( Long.class.getName().equals(objectFQCN))
					return ( T[])new Double[size] ; 
				return null;
			}
		}); 
         
         indexedgenerators.put(Date.class.getName(), new IObjectGenerator() {
			@Override
			public <T> T instantiateSampleObject(String objectFQCN) {
				if ( Long.class.getName().equals(objectFQCN))
					return (T)new Date() ; 
				return null;
			}
			@Override
			public <T> T[] instantiateArray(String objectFQCN, int size) {
				if ( Long.class.getName().equals(objectFQCN))
					return ( T[])new Date[size] ; 
				return null;
			}
		}); 
         
         indexedgenerators.put(Boolean.class.getName(), new IObjectGenerator() {
			@Override
			public <T> T instantiateSampleObject(String objectFQCN) {
				if ( Boolean.class.getName().equals(objectFQCN))
					return (T)new Boolean(false) ; 
				return null;
			}
			@Override
			public <T> T[] instantiateArray(String objectFQCN, int size) {
				if ( Boolean.class.getName().equals(objectFQCN))
					return ( T[])new Boolean[size] ; 
				return null;
			}
		}); 
         
         indexedgenerators.put(BigInteger.class.getName(), new IObjectGenerator() {
			@Override
			public <T> T instantiateSampleObject(String objectFQCN) {
				if ( BigInteger.class.getName().equals(objectFQCN))
					return (T)new BigInteger("1") ; 
				return null;
			}
			@Override
			public <T> T[] instantiateArray(String objectFQCN, int size) {
				if ( BigInteger.class.getName().equals(objectFQCN))
					return ( T[])new BigInteger[size] ; 
				return null;
			}
		});
         indexedgenerators.put(BigDecimal.class.getName(), new IObjectGenerator() {
			@Override
			public <T> T instantiateSampleObject(String objectFQCN) {
				if ( BigDecimal.class.getName().equals(objectFQCN))
					return (T)new BigDecimal("1") ; 
				return null;
			}
			@Override
			public <T> T[] instantiateArray(String objectFQCN, int size) {
				if ( BigDecimal.class.getName().equals(objectFQCN))
					return ( T[])new BigDecimal[size] ; 
				return null;
			}
		});
         
        
    }
    /**
     * singleton instance
     */
    public static ObjectGeneratorManager getInstance() {
        if ( instance == null)
            instance = new ObjectGeneratorManager(); 
        return instance;
    }
    
    
    
    
    /**
     * instantiate sample object dengan FQCN 
     */
    public <T> T instantiateSampleObject ( String fqcn ){
    	if ( fqcn == null || fqcn.isEmpty())
    		return null ; 
        if ( !indexedgenerators.containsKey(fqcn)){
        	String msg = "maaf, saya tidak tahu bagaimana caranya untuk mengcreate object : " + fqcn + ", mohon di ceck code anda" ; 
        	
        	System.out.println(  msg) ; 
            return null ; 
        }
        return  indexedgenerators.get(fqcn).instantiateSampleObject(fqcn); 
    }
    
    
    /**
	 * instantiate array of object
	 **/
	public <T> T[] instantiateArray (String objectFQCN, int size) {
		if ( !indexedgenerators.containsKey(objectFQCN)){
			System.out.println(  "fqcn : " + objectFQCN + " , tidak di temukan dalam Object generator. Tidak mungkin unutk instantiate object ini, proses reflection tidak akan bekerja")  ; 
            return null ;
		}
		return indexedgenerators.get(objectFQCN).instantiateArray(objectFQCN, size);
	}
    /**
     * register generator instance
     */
    public void registerGenerator (String fqcn  , IObjectGenerator generator) {
        this.indexedgenerators.put(fqcn, generator); 
    }
    
    
    
    
    
    
    /**
     * register object dengan abstract generator. semua item yang blm bisa di create oleh generator - di register langsung
     **/
    public void registerGenerator (  AbstractObjectGenerator objectGenerator) {
    	if ( objectGenerator== null|| objectGenerator.generatedClass()== null || objectGenerator.generatedClass().length==0)
    		return   ; 
    	for ( Class<?> scn : objectGenerator.generatedClass()){
    		registerGenerator(scn.getName(), objectGenerator);
    	}
    }
    
    
    
    
    /**
     * raw access ke generator manager. ini untuk populate shelf adjust task
     **/
    public Map<String, IObjectGenerator> getIndexedgenerators() {
		return indexedgenerators;
	}
    
    
    
    /**
     * repalce map of generator. ini bisa memaksa kita inject generator ke dalam hasn map
     **/
    public void setIndexedgenerators(
			Map<String, IObjectGenerator> indexedgenerators) {
		this.indexedgenerators = indexedgenerators;
		this.indexerMapReplaced = true ; 
	}
    
    
    /**
     * flag, variale {@link #indexedgenerators} pernah di replace dengan map lain atau tidak. ini untuk optimasi
     **/
    public boolean isIndexerMapReplaced() {
		return indexerMapReplaced;
	}
    
    
    
    
    /**
     * mengecek ini object yang di kenali atau tidak
     * @param fqcn fqcn dari class yang hendak di cek
     **/
    public boolean isKnownType (String fqcn ) {
    	return this.indexedgenerators.containsKey(fqcn); 
    }
    
    
    
    
    /**
     * tipe object yang di kenali oleh app
     **/
    public Collection<String> getKnownTypes () {
    	return this.indexedgenerators.keySet(); 
    }
    
}
