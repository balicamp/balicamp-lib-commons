/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.data.serializer;

import id.co.sigma.common.data.ObjectSerializer;
import id.co.sigma.common.util.json.SharedServerClientLogicManager;
import java.util.Date;

/**
 *
 * @author gps
 */
public class DateSerializer implements  ObjectSerializer<Date>{

    public static final String DATE_TIME_SERIALIZER_PATTERN = "yyyy-MM-dd HH:mm:ss"; 
    
    
    private static final  String[] FQCN =  {
        Date.class.getName()  
    
    }; 
    
    @Override
    public String serialize(Date object) {
        if ( object==null)
            return null ; 
        
        return SharedServerClientLogicManager.getInstance().getDateTimeParser().format(object, DATE_TIME_SERIALIZER_PATTERN); 
    }

    @Override
    public Date deserialize(String stringRepresentation) {
         if ( stringRepresentation==null ||stringRepresentation.isEmpty())
            return null ; 
        try { 
            return SharedServerClientLogicManager.getInstance().getDateTimeParser().parse(stringRepresentation, DATE_TIME_SERIALIZER_PATTERN);
        } catch (Exception ex) {
            return null ; 
        }
    }
    @Override
    public String[] acceptedClassFQCNS() {
        return FQCN ; 
    }
}
