package tn.esprit.entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = "CampChef")
@JsonTypeName(value = "CampChef")
public class CampChef extends User {

	public CampChef(){
		super();
	}
	
	@OneToMany(mappedBy="campchef",fetch=FetchType.EAGER)
	@JsonManagedReference
	  private List<JobOffer> joboffers;

	public List<JobOffer> getJoboffers() {
		return joboffers;
	}

	public void setJoboffers(List<JobOffer> joboffers) {
		this.joboffers = joboffers;
	}

	@Override
	public String toString() {
		return super.toString() + ",\njoboffers=" + joboffers + "\n}";
	}
	  
}
