package tn.esprit.entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@DiscriminatorValue(value = "CampChef")
@JsonTypeName(value = "CampChef")
public class CampChef extends User {

	public CampChef(){
		super();
	}

	@OneToMany(mappedBy="campchef",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<JobOffer> joboffers;
	
	
	@OneToMany(mappedBy="creator",fetch=FetchType.LAZY)
	@JsonManagedReference
    @JsonInclude(value=Include.NON_EMPTY)
	private List<Evenement> events;

	public List<JobOffer> getJoboffers() {
		return joboffers;
	}

	public void setJoboffers(List<JobOffer> joboffers) {
		this.joboffers = joboffers;
	}
	
	public List<Evenement> getEvents() {
		return events;
	}

	public void setEvents(List<Evenement> events) {
		this.events = events;
	}
	
	

}
