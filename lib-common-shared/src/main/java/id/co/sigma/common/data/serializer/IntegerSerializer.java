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
public class IntegerSerializer implements  ObjectSerializer<Integer>{

    private static final  String[] FQCN =  {
        Integer.class.getName() , 
        int.class.getName() 
    
    }; 
    @Override
    public String serialize(Integer object) {
        if ( object==null)
            return null ; 
        return object.toString(); 
    }

    @Override
    public Integer deserialize(String stringRepresentation) {
         if ( stringRepresentation==null ||stringRepresentation.isEmpty())
            return null ; 
         return Integer.parseInt(stringRepresentation); 
    }
    @Override
    public String[] acceptedClassFQCNS() {
        return FQCN ; 
    }
}
