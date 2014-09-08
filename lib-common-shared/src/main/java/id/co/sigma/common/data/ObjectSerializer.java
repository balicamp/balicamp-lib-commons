/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.data;


/**
 * Interface untuk object serializer vs deserializer
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ObjectSerializer<T> {
    
    
   
    
    
    
    /**
     * serialize object ke dalam bentuk string
     */
    public String serialize (T object) ; 
    
    /**
     * deserialize object dari string menjadi object
     */
    public T deserialize (String stringRepresentation); 
    
    
    
    public String [] acceptedClassFQCNS () ; 
    
    
}
