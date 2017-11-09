package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	private String imagename;
	private CampChef creator;
	private List<Volunteer> staff;
	private List<Refugee> refugees;
	
	
	public Evenement() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	public Date getDateEvent() {
		return dateEvent;
	}
	public int getNbplace() {
		return nbplace;
	}
	public String getName() {
		return name;
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
	public List<Volunteer> getStaff() {
		return staff;
	}
	@ManyToMany(mappedBy="events")
	public List<Refugee> getRefugees() {
		return refugees;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	
	


}
