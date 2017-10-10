package tn.esprit.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="camp")
public class Camp implements Serializable{
	@Id
	@GeneratedValue
	private int id;
	@Column(name="name")
	private String name;
	
	private List<Refugee> refugees;
	
	private List<DistrictChef> districtchiefs;
	
	private CampChef campchief;
	@Column(name="country")
	private String country;
	@Column(name="capacity")
	private int capacity;
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name="state")
	private boolean state;
	
	public Camp(){
		refugees=new ArrayList<>();
		districtchiefs=new ArrayList<>();
		state=false;
		createdAt=new Date();
	}
	
	public Camp(String name,CampChef campchief,String country,int capacity){
		this();
		this.name=name;
		this.campchief=campchief;
		this.country=country;
		this.capacity=capacity;
	}
	public Camp(String name,CampChef campchief,String country,int capacity,List<Refugee> refugees,List<DistrictChef> districtchiefs){
		this();
		this.name=name;
		this.campchief=campchief;
		this.country=country;
		this.capacity=capacity;
		this.districtchiefs=districtchiefs;
		this.refugees=refugees;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Refugee> getRefugees() {
		return refugees;
	}
	public void setRefugees(List<Refugee> refugees) {
		this.refugees = refugees;
	}
	public List<DistrictChef> getDistrictchiefs() {
		return districtchiefs;
	}
	public void setDistrictchiefs(List<DistrictChef> districtchiefs) {
		this.districtchiefs = districtchiefs;
	}
	public CampChef getCampchief() {
		return campchief;
	}
	public void setCampchief(CampChef campchief) {
		this.campchief = campchief;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	
	
}
