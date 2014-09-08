/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.data.serializer;

import id.co.sigma.common.data.ObjectSerializer;
import java.math.BigDecimal;

/**
 *
 * @author gps
 */
public class BigDecimalSerializer implements  ObjectSerializer<BigDecimal>{

    
    private static final  String[] FQCN =  {
        BigDecimal.class.getName()  
    
    }; 
    
    @Override
    public String serialize(BigDecimal object) {
        if ( object==null)
            return null ; 
        return object.toString(); 
    }

    @Override
    public BigDecimal deserialize(String stringRepresentation) {
         if ( stringRepresentation==null ||stringRepresentation.isEmpty())
            return null ; 
         return new BigDecimal(stringRepresentation); 
    }
    @Override
    public String[] acceptedClassFQCNS() {
        return FQCN ; 
    }
}
