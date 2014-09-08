/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.data.serializer;

import id.co.sigma.common.data.ObjectSerializer;
import java.math.BigInteger;

/**
 *
 * @author gps
 */
public class BigIntegerSerializer implements  ObjectSerializer<BigInteger>{

    
    
    private static final  String[] FQCN =  {
        BigInteger.class.getName()  
    
    }; 
    
    @Override
    public String serialize(BigInteger object) {
        if ( object==null)
            return null ; 
        return object.toString(); 
    }

    @Override
    public BigInteger deserialize(String stringRepresentation) {
         if ( stringRepresentation==null ||stringRepresentation.isEmpty())
            return null ; 
         return new BigInteger(stringRepresentation); 
    }
    @Override
    public String[] acceptedClassFQCNS() {
        return FQCN ; 
    }
}
