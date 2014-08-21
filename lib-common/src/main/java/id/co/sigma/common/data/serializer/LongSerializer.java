/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.data.serializer;

import id.co.sigma.common.data.ObjectSerializer;

/**
 *
 * @author gps
 */
public class LongSerializer implements  ObjectSerializer<Long>{

    
    private static final  String[] FQCN =  {
        Long.class.getName() , 
        long.class.getName() 
    
    }; 
    
    @Override
    public String serialize(Long object) {
        if ( object==null)
            return null ; 
        return object.toString(); 
    }

    @Override
    public Long deserialize(String stringRepresentation) {
         if ( stringRepresentation==null ||stringRepresentation.isEmpty())
            return null ; 
         return Long.parseLong(stringRepresentation); 
    }
    @Override
    public String[] acceptedClassFQCNS() {
        return FQCN ; 
    }
}
