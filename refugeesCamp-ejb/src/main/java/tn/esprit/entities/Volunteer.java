package tn.esprit.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = "Volunteer")
@JsonTypeName(value = "Volunteer")
public class Volunteer extends User {
	
	public Volunteer(){}

}
