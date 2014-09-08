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
public class BooleanSerializer implements  ObjectSerializer<Boolean>{
    
    
    private static final  String[] FQCN =  {
        Boolean.class.getName() , 
        boolean.class.getName() 
    
    }; 
    

    @Override
    public String serialize(Boolean object) {
        if ( object==null)
            return null ; 
        return object.toString(); 
    }

    @Override
    public Boolean deserialize(String stringRepresentation) {
        if ( stringRepresentation==null ||stringRepresentation.isEmpty())
            return null ; 
        return Boolean.TRUE.toString().equals(stringRepresentation); 
    }

    @Override
    public String[] acceptedClassFQCNS() {
        return FQCN ; 
    }
    
    
    
}
