package tn.esprit.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Camp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7242693779822127156L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;

	private String name;
	@OneToMany(mappedBy="rcamp")
	private List<Refugee> refugees;
	
	
	@OneToMany(mappedBy="camp")
	private List<DistrictChef> districtchiefs;
	
	@OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="campChef_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private CampChef campchief;
	
	private String country;
	
	private int capacity;

	private Date createdAt;
	
	private boolean state;
	
	public Camp(){
		super();
		createdAt=new Date();
		refugees=new ArrayList<Refugee>();
		districtchiefs=new ArrayList<>();
	}
	
	public Camp(String name,boolean state,String country,int capacity){
		this();
		this.name=name;
		this.country=country;
		this.capacity=capacity;
		this.state=state;
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

	@Override
	public String toString() {
		return "Camp [id=" + id + ", name=" + name + ", country=" + country + ", capacity=" + capacity + ", createdAt="
				+ createdAt + ", state=" + state + "]";
	}
	
	
	
	
	
}
