package tn.esprit.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "DistrictChef")
public class DistrictChef extends User{

}
