package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
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
@DiscriminatorValue(value = "Course")
public class Course implements Serializable{

	private int id;
	private String name;
	private Date startdate;
	private Date enddate;
	private String description;
	private DistrictChef dischef ;
	
	private static final long serialVersionUID = 1L;///La sérialisation nécessite d’accéder à tous les champs de l’objet un par un pour sauvegarder leur valeur

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@ManyToOne(fetch=FetchType.EAGER)	///ta36ini 4e ver lmet3ala9 bihe
	@JoinColumn(name="iddcchef")
	@JsonBackReference(value="districtCourses")
	public DistrictChef getDischef() {
		return dischef;
	}

	public void setDischef(DistrictChef dischef) {
		this.dischef = dischef;
	}

	
	
	



	
}
