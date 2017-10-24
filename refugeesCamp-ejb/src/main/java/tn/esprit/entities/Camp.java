package tn.esprit.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Camp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7242693779822127156L;
	
	
	private int id;

	private String name;
	
	private List<Refugee> refugees;
	
	private List<DistrictChef> districtchiefs;
	
	private CampChef campchief;
	
	private String country;
	
	private int capacity;

	private Date createdAt;
	
	private boolean state;
	
	public Camp(){
		super();
	}
	
	public Camp(String name,boolean state,String country,int capacity,Date createdAt){
		super();
		this.name=name;
		this.country=country;
		this.capacity=capacity;
		this.state=state;
		this.createdAt=createdAt;
	}
	
	public Camp(String name,CampChef campchief,String country,int capacity,Date createdAt){
		super();
		this.name=name;
		this.campchief=campchief;
		this.country=country;
		this.capacity=capacity;
		this.createdAt=createdAt;
	}
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
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
	@OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="campChef_ID", unique= true, nullable=true, insertable=true, updatable=true)
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
	
	
	
	@OneToMany(mappedBy="rcamp",fetch=FetchType.EAGER)
	@JsonManagedReference
	public List<Refugee> getRefugees() {
		return refugees;
	}

	public void setRefugees(List<Refugee> refugees) {
		this.refugees = refugees;
	}
	@OneToMany(mappedBy="camp",fetch=FetchType.EAGER)
	@JsonManagedReference
	public List<DistrictChef> getDistrictchiefs() {
		return districtchiefs;
	}

	public void setDistrictchiefs(List<DistrictChef> districtchiefs) {
		this.districtchiefs = districtchiefs;
	}

	@Override
	public String toString() {
		return "Camp [id=" + id + ", name=" + name + ", country=" + country + ", capacity=" + capacity + ", createdAt="
				+ createdAt + ", state=" + state + "]";
	}
	
	
	
	
	
}
