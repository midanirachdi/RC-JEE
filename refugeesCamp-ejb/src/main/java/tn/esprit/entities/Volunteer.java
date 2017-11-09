package tn.esprit.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = "Volunteer")
@JsonTypeName(value = "Volunteer")
public class Volunteer extends User {
	
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "volunter_event", joinColumns = @JoinColumn(name = "volunteer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"))
	private List<Event> events;
	
	public Volunteer(){}

	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}
