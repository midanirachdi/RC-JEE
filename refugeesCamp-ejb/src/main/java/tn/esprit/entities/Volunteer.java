package tn.esprit.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Volunteer")
public class Volunteer extends User {

}
