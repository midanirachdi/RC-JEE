package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Rating implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RatingId ratingId;

	@Temporal(TemporalType.DATE)
	private Date dateOfRating;
	
	private int mark;
	
	@ManyToOne
	@JoinColumn(name = "idVolunteer", referencedColumnName = "id", insertable = false, updatable = false)
	private Volunteer volunteer;
	
	@ManyToOne
	@JoinColumn(name = "idEvent", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonBackReference
	private Evenement evenement;
	
	public Rating(){
	}
	public Rating(int mark, Volunteer volunteer, Evenement evenement) {
		super();
		this.volunteer = volunteer;
		this.mark = mark;
		this.volunteer = volunteer;
		this.evenement = evenement;
		this.dateOfRating=new Date();
	}

	public RatingId getRatingId() {
		return ratingId;
	}

	public Date getDateOfRating() {
		return dateOfRating;
	}

	public int getMark() {
		return mark;
	}

	public Volunteer getVolunteer() {
		return volunteer;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setRatingId(RatingId ratingId) {
		this.ratingId = ratingId;
	}

	public void setDateOfRating(Date dateOfRating) {
		this.dateOfRating = dateOfRating;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}
	
	
	
	
	
	
	

}
