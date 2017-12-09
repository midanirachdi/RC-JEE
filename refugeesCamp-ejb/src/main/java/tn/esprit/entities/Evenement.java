package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Evenement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date dateEvent;
	private int nbplace;
	private String name;
	private String location;
	private String description;
	private String imagename;
	private CampChef creator;
	private List<Volunteer> staff;
	private List<Refugee> refugees;
	private List<Rating> ratings;
	
	
	public Evenement() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateEvent() {
		return dateEvent;
	}
	public int getNbplace() {
		return nbplace;
	}
	public String getName() {
		return name;
	}
	@Column(length=1500)
	public String getDescription() {
		return description;
	}
	public String getLocation() {
		return location;
	}

	public String getImagename() {
		return imagename;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	public CampChef getCreator() {
		return creator;
	}
	@ManyToMany(mappedBy = "events")
	@JsonIgnore
	public List<Volunteer> getStaff() {
		return staff;
	}
	@ManyToMany(mappedBy="events")
	@JsonIgnore
	public List<Refugee> getRefugees() {
		return refugees;
	}
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy = "evenement",fetch=FetchType.EAGER)
	@JsonManagedReference
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}
	public void setNbplace(int nbplace) {
		this.nbplace = nbplace;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	
	public void setCreator(CampChef creator) {
		this.creator = creator;
	}
	
	public void setStaff(List<Volunteer> staff) {
		this.staff = staff;
	}
	
	public void setRefugees(List<Refugee> refugees) {
		this.refugees = refugees;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	
	
	
	


}
