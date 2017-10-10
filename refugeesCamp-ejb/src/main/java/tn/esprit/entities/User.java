package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tn.esprit.interfaces.IdentifiedInterface;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public abstract class User implements IdentifiedInterface,Serializable{
	
    public static final long serialVersionUID = 196919661993L;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getBirthDay() {
		return birthDay;
	}


	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isDisable() {
		return disable;
	}


	public void setDisable(boolean disable) {
		this.disable = disable;
	}


	public String getLastResetQuery() {
		return lastResetQuery;
	}


	public void setLastResetQuery(String lastResetQuery) {
		this.lastResetQuery = lastResetQuery;
	}

	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	
	private String firstName;
	
	
	private String lastName;
	
	
	private String email;
	

	@Temporal(TemporalType.DATE)
	private Date birthDay;
	
	
	private String adress;
	
	
	private String password;
	
	
	
	private boolean disable;
	
	
	private String lastResetQuery;
	
		
}
