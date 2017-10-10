package tn.esprit.entities;

import java.util.Date;

public class Refugee {

	private int id;
	private String firstname;
	private String lastName;
	private String sex;
	private Date dateOfBirth;
	private String nationality;
	
	public Refugee() {
		super();
	}

	public Refugee(String firstname, String lastName, String sex, Date dateOfBirth, String nationality) {
		super();
		this.firstname = firstname;
		this.lastName = lastName;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
		this.nationality = nationality;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
}