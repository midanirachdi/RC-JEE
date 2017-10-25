package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Refugee implements Serializable {

	public enum LanguageLevelEnum {
		A, B, C
	}

	public enum HighestDegreeEnum {
		BAC, BACplus3, BACplus5, BACplus8
	}


	private int id;
	private String firstname;
	private String lastName;
	private String sex;
	

	private Date dateOfBirth;
	private String nationality;
	

	private Camp rcamp;

	
	private LanguageLevelEnum frenchlanguageLevel;
	
	private LanguageLevelEnum englishlanguageLevel;

	
	private HighestDegreeEnum highestDegree;

	private int yearsOfExperience;
	private String email;
	private String fieldOfWork;
	private String adress;
	private int phoneNumber;
	

	
	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Enumerated(EnumType.STRING)
	public LanguageLevelEnum getFrenchlanguageLevel() {
		return frenchlanguageLevel;
	}

	public void setFrenchlanguageLevel(LanguageLevelEnum frenchlanguageLevel) {
		this.frenchlanguageLevel = frenchlanguageLevel;
	}
	@Enumerated(EnumType.STRING)
	public LanguageLevelEnum getEnglishlanguageLevel() {
		return englishlanguageLevel;
	}

	public void setEnglishlanguageLevel(LanguageLevelEnum englishlanguageLevel) {
		this.englishlanguageLevel = englishlanguageLevel;
	}
	@Enumerated(EnumType.STRING)
	public HighestDegreeEnum getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(HighestDegreeEnum highestDegree) {
		this.highestDegree = highestDegree;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getFieldOfWork() {
		return fieldOfWork;
	}

	public void setFieldOfWork(String fieldOfWork) {
		this.fieldOfWork = fieldOfWork;
	}




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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}



	

	public Refugee(String firstname, String lastName, String sex, Date dateOfBirth, String nationality, Camp rcamp,
			LanguageLevelEnum frenchlanguageLevel, LanguageLevelEnum englishlanguageLevel,
			HighestDegreeEnum highestDegree, int yearsOfExperience, String email, String fieldOfWork, String adress,
			int phoneNumber) {
		super();
		this.firstname = firstname;
		this.lastName = lastName;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
		this.nationality = nationality;
		this.rcamp = rcamp;
		this.frenchlanguageLevel = frenchlanguageLevel;
		this.englishlanguageLevel = englishlanguageLevel;
		this.highestDegree = highestDegree;
		this.yearsOfExperience = yearsOfExperience;
		this.email = email;
		this.fieldOfWork = fieldOfWork;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="camp_ID")
	@JsonBackReference(value="forraffa")
	public Camp getRcamp() {
		return rcamp;
	}

	public void setRcamp(Camp rcamp) {
		this.rcamp = rcamp;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

}