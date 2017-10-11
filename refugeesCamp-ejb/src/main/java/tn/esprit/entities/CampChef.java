package tn.esprit.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = "CampChef")
@JsonTypeName(value = "CampChef")
public class CampChef extends User {

	public CampChef(){}
}
