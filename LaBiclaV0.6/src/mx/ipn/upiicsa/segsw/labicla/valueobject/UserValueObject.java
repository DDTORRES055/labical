package mx.ipn.upiicsa.segsw.labicla.valueobject;

import java.io.Serializable;

public class UserValueObject implements Serializable
{
	
	private String email;
	private String firstname;
	private String lastname;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getName() {
		return firstname + " "  + lastname;
	}
	
	

}
