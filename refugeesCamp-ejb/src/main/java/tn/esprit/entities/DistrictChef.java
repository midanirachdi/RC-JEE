package tn.esprit.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@DiscriminatorValue(value = "DistrictChef")
@JsonTypeName(value = "DistrictChef")
public class DistrictChef extends User {

	public DistrictChef() {
		super();
	}

	@OneToMany(mappedBy = "dischef", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "districtNeeds")
    @JsonInclude(value=Include.NON_EMPTY)
	private Set<Need> needs;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "camp_ID")
	@JsonBackReference
	private Camp camp;

	@OneToMany(mappedBy = "districtchef", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<JobOffer> joboffers;

	public List<JobOffer> getJoboffers() {
		return joboffers;
	}

	public void setJoboffers(List<JobOffer> joboffers) {
		this.joboffers = joboffers;
	}

	@Override
	public String toString() {
		return super.toString() + ",\ncamp=" + camp + ",\njoboffers=" + joboffers + "\n}";
	}

	//////// debut
	@OneToMany(mappedBy = "dischef", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "districtCourses")
	private Set<Course> courses;

	////////// fin

}
