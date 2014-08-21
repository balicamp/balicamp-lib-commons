package id.co.balicamp.lab;
 
import java.util.Date;

import id.co.sigma.common.server.util.strings.fixedlength.FixedLengthField;
import id.co.sigma.common.server.util.strings.fixedlength.impl.DDMMYYYYDateFixedLengthSerializer;
import id.co.sigma.common.server.util.strings.fixedlength.impl.StringCustomFixedLengthSerializer;
import id.co.sigma.common.server.util.strings.fixedlength.impl.ZeroLeadedItegerCustomFixedLengthSerializer;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class SampleDomain {
	
	
	@FixedLengthField(index=1 , stringMaxLength=3  , serializer = StringCustomFixedLengthSerializer.class)
	private String name ; 
	
	
	@FixedLengthField(index=2 , stringMaxLength=10  , serializer = ZeroLeadedItegerCustomFixedLengthSerializer.class)
	private Integer angka ;

	
	@FixedLengthField(index=3 , stringMaxLength=8 , serializer= DDMMYYYYDateFixedLengthSerializer.class)
	private Date tgl  ; 

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getAngka() {
		return angka;
	}


	public void setAngka(Integer angka) {
		this.angka = angka;
	}


	public Date getTgl() {
		return tgl;
	}


	public void setTgl(Date tgl) {
		this.tgl = tgl;
	} 
	
	

}
