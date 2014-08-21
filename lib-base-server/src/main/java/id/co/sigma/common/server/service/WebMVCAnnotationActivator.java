package id.co.sigma.common.server.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;



/**
 * 
 * class kosong. class ini akan memaksa agar ada bean dengan tipe {@link DefaultAnnotationHandlerMapping} dalam spring bean<br/>
 * dengan cara begini, bean yang controller akan bekerja
 * 
 **/

public class WebMVCAnnotationActivator extends DefaultAnnotationHandlerMapping{

}
