package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import tn.esprit.interfaces.IdentifiedInterface;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Admin.class, name = "Admin"),

    @JsonSubTypes.Type(value = DistrictChef.class, name = "DistrictChef"),
    
    @JsonSubTypes.Type(value= CampChef.class,name="CampChef"),
    
    @JsonSubTypes.Type(value= Volunteer.class,name="Volunteer"),
    }
	
	
)
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


	public User() {
		
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
	

	
	public List<Task> getTasks() {
		return tasks;
	}


	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}


	@Override
	public String toString() {
		return " User {\nfirstName=" + firstName + ",\nlastName=" + lastName + ",\nemail=" + email + ",\nbirthDay=" + birthDay
				+ ",\nadress=" + adress + ",\npassword=" + password + ",\ndisable=" + disable + ",\nlastResetQuery="
				+ lastResetQuery + ",";
	}



	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
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
	

	
	@OneToMany(mappedBy = "user")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<Topic> topics;
	
	@OneToMany(mappedBy = "user")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<Comment> comments;

	public Set<Topic> getTopics() {
		return topics;
	}


	public void setTopics(Set<Topic> topics) {
		topics = topics;
	}


	public Set<Comment> getComments() {
		return comments;
	}


	public void setComments(Set<Comment> comments) {
		comments = comments;
	}

	
	@OneToMany(mappedBy="assignedTo",fetch=FetchType.EAGER)
	@JsonManagedReference
	private List<Task> tasks;

	
		
}
