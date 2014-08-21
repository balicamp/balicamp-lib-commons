/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.data.serializer.json;

import id.co.sigma.common.data.ObjectSerializer;
import id.co.sigma.common.data.serializer.BigDecimalSerializer;
import id.co.sigma.common.data.serializer.BigIntegerSerializer;
import id.co.sigma.common.data.serializer.BooleanSerializer;
import id.co.sigma.common.data.serializer.DateSerializer;
import id.co.sigma.common.data.serializer.IntegerSerializer;
import id.co.sigma.common.data.serializer.LongSerializer;
import id.co.sigma.common.data.serializer.StringSerializer;
import id.co.sigma.common.data.serializer.VoidSerializer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * spoller object serializer
 * @author Gede Sutarsa
 */
public final class ObjectSerializerManager {
    
    
    private static ObjectSerializerManager instance ; 
    
    
     /**
     * tipe data simple yang tidak di json kan
     */
    public final static Class <?>[] SIMPLE_OBJECTS  ={
        Long.class , 
        long.class , 
        Integer.class , 
        int.class , 
        Float.class , 
        float.class , 
        Short.class , 
        short.class , 
        String.class ,
        Boolean.class ,
        boolean.class , 
        Date.class , 
        java.sql.Date.class , 
        BigDecimal.class , 
        BigInteger.class ,
        Void.class
    
    }; 
    
    
    /**
     * object as simple array. ini untuk kemudahan konversi
     */
    public final static ArrayList<String> SIMPLE_OBJECT_ARRAY =new ArrayList<String>();
    
    
    
    public static final ArrayList<String> SIMPLE_ARRAY_OFOBJECT_TYPES = new ArrayList<String>(); 
    public static final String [] SIMPLE_TYPE_FQCNS = new String[SIMPLE_OBJECTS.length] ;
    
    
    static {
        for ( Class<?> scn : SIMPLE_OBJECTS){
            SIMPLE_OBJECT_ARRAY.add(scn.getName());
            SIMPLE_ARRAY_OFOBJECT_TYPES.add("[L" + scn.getName() +";"); 
        }
        for (int i=0;i<SIMPLE_TYPE_FQCNS.length;i++){
        	SIMPLE_TYPE_FQCNS[i]=SIMPLE_OBJECTS[i].getName();
        }
    }
    
    
    
    
    
    
    /**
     * mengecek object tipe simple atau tidak
     */
    public static boolean isSimpleObject ( String fqcn ){
        return SIMPLE_OBJECT_ARRAY.contains(fqcn);
    }
    
    
    
    /**
     * ini untuk mengecek array of simple object atau bukan
     **/
    public boolean isArrayofSimpleObject ( String classFqcn ){
    	return SIMPLE_ARRAY_OFOBJECT_TYPES.contains(classFqcn);
    }
    
    
    
    
    
    
    
    private Map<String , ObjectSerializer<?>> indexedSerializer ; 
    
    
    
    
    private ObjectSerializerManager () {
        indexedSerializer = new HashMap<String, ObjectSerializer<?>>();
       registerSerializer(new BigDecimalSerializer());
        registerSerializer(new BigIntegerSerializer());
        registerSerializer(new BooleanSerializer());
        registerSerializer(new DateSerializer());
        registerSerializer(new IntegerSerializer());
        registerSerializer(new LongSerializer());
        registerSerializer(new VoidSerializer());
        registerSerializer(new StringSerializer());
        
        
        
        
        
    }
    
    
    
    
    
    /**
     * register serializer ke dalam manager
     */
    public void registerSerializer (ObjectSerializer<?> serializer ){
        if ( serializer==null || serializer.acceptedClassFQCNS()== null || serializer.acceptedClassFQCNS().length ==0)
            return ; 
        for ( String scn : serializer.acceptedClassFQCNS()){
            indexedSerializer.put(scn, serializer); 
        }
                    
    }
    
    
    
    public  ObjectSerializer<?> getSerializer (String fqcn ){
        return this.indexedSerializer.get(fqcn);
    }
    
    
    /**
     * checker apakah ada deserializer untuk object dengan fqcn yang di minta
     */
    public boolean isHaveSerializer (String fqcn ) {
        return this.indexedSerializer.containsKey(fqcn); 
    }

    public static ObjectSerializerManager getInstance() {
        if ( instance == null){
            instance = new ObjectSerializerManager(); 
        }
        return instance;
    }
    
    
    
    
    public static void main (String [] args){
    	System.out.println(Object[].class.getName());
    }
    
    
    
    
}
