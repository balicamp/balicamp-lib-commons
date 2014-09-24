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
public class VoidSerializer implements ObjectSerializer<Void>{

    @Override
    public String serialize(Void object) {
        return "-[VOID]-"; 
    }

    @Override
    public Void deserialize(String stringRepresentation) {
        return null ; 
    }

    private static final  String[] FQCN =  {
        Void.class.getName() 
    }; 
    @Override
    public String[] acceptedClassFQCNS() {
        return FQCN; 
    }
    
}
