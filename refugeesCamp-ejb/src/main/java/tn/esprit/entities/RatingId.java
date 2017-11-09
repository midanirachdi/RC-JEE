package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RatingId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idVolunteer;
	private int idEvent;
	
	public RatingId(){
	}
	public RatingId(int idVolunteer, int idEvent) {
		super();
		this.idVolunteer = idVolunteer;
		this.idEvent = idEvent;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEvent;
		result = prime * result + idVolunteer;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RatingId other = (RatingId) obj;
		if (idEvent != other.idEvent)
			return false;
		if (idVolunteer != other.idVolunteer)
			return false;
		return true;
	}
	public int getIdVolunteer() {
		return idVolunteer;
	}
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdVolunteer(int idVolunteer) {
		this.idVolunteer = idVolunteer;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	
	
	
}
