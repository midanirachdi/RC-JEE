package tn.esprit.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Admin")
public class Admin extends User {
		

}
