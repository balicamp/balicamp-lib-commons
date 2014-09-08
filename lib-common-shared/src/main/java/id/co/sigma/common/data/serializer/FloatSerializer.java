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
public class FloatSerializer implements  ObjectSerializer<Float>{

    
    private static final  String[] FQCN =  {
        Float.class.getName() , 
        Float.class.getName() 
    
    }; 
    
    @Override
    public String serialize(Float object) {
        if ( object==null)
            return null ; 
        return object.toString(); 
    }

    @Override
    public Float deserialize(String stringRepresentation) {
         if ( stringRepresentation==null ||stringRepresentation.isEmpty())
            return null ; 
         return Float.parseFloat(stringRepresentation); 
    }
    @Override
    public String[] acceptedClassFQCNS() {
        return FQCN ; 
    }
}
