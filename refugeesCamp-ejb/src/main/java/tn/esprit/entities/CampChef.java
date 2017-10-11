package tn.esprit.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CampChef")
public class CampChef extends User {

}
