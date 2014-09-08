package id.co.sigma.sandbox.shared.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
@Entity
@Table(name="dept")
public class Department implements IJSONFriendlyObject<Department>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3713446950873463867L;
	@Id
	@Column(name="code" , length=16)
	private String code ;
	@Column(name="dept_name" , length=128)
	private String name ; 
	@Column(name="count_peoples")
	private Integer numberOfPeoples; 

	
	
	public Department() {}
	
	
	
	public Department(String code, String name, Integer numberOfPeoples) {
		super();
		this.code = code;
		this.name = name;
		this.numberOfPeoples = numberOfPeoples;
	}



	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("code", code);
		jsonContainer.put("name", name);
		jsonContainer.put("numberOfPeoples", numberOfPeoples);
		
	}

	@Override
	public Department instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		Department retval = new Department();
		retval.code = jsonContainer.getAsString( "code"); 
		retval.name = jsonContainer.getAsString("code");
		retval.numberOfPeoples = jsonContainer.getAsInteger( "numberOfPeoples");
		
		return retval;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfPeoples() {
		return numberOfPeoples;
	}

	public void setNumberOfPeoples(Integer numberOfPeoples) {
		this.numberOfPeoples = numberOfPeoples;
	}

}
