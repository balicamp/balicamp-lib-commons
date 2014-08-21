package id.co.sigma.sandbox.shared.domain;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * personal 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
@Table(name="person")
@Entity
public class Person implements IJSONFriendlyObject<Person>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2633941595182506422L;

	@Id
	@Column(name="pk")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id ; 
	
	@Column(name="name" , length=128)
	private String name ;
	
	@Column(name="email")
	private String email ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("id", id);
		jsonContainer.put("name", name);
		jsonContainer.put("email", email);
	}

	@Override
	public Person instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		Person retval = new Person();
		retval.id = jsonContainer.getAsInteger("id");
		retval.email = jsonContainer.getAsString("email");
		retval.name = jsonContainer.getAsString("name");
		return retval;
	}  
	

}
