package tn.esprit.entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = "DistrictChef")
@JsonTypeName(value = "DistrictChef")
public class DistrictChef extends User{
	
	public DistrictChef(){}
	
	  @ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="camp_ID")	
	  private Camp camp;
	  
	  @OneToMany(mappedBy="districtchef")
	  private List<JobOffer> joboffers;

	public List<JobOffer> getJoboffers() {
		return joboffers;
	}

	public void setJoboffers(List<JobOffer> joboffers) {
		this.joboffers = joboffers;
	}
	  
}
