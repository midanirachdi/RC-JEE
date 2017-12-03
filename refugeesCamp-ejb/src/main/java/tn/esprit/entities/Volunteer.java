package tn.esprit.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeName;


@Entity
@DiscriminatorValue(value = "Volunteer")
@JsonTypeName(value = "Volunteer")
public class Volunteer extends User {
	
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "volunter_event", joinColumns = @JoinColumn(name = "volunteer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"))
    @JsonInclude(value=Include.NON_EMPTY)
    private List<Evenement> events;
    
	@OneToMany(mappedBy = "volunteer")
    @JsonInclude(value=Include.NON_EMPTY)
	private List<Rating> ratings;
	
	public Volunteer(){}

	public List<Evenement> getEvents() {
		return events;
	}
	public void setEvents(List<Evenement> events) {
		this.events = events;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	
	
}
