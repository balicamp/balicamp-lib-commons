package id.co.balicamp.lab;

import java.util.Date;

import id.co.sigma.common.server.util.strings.fixedlength.FixedLengthUtils;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class SerializerTester {
	
	
	public static void main ( String[] arg) throws Exception {
		SampleDomain s = new SampleDomain(); 
		s.setAngka(1);
		s.setName("Gede S");
		s.setTgl(new Date());
		System.out.println(   FixedLengthUtils.getInstance().generateFixedLengthString(s)); 
	}

}
