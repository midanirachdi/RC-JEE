package tn.esprit.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue(value = "DistrictChef")
@JsonTypeName(value = "DistrictChef")
public class DistrictChef extends User{
	
	public DistrictChef(){}
	
	  @ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="camp_ID")	
	  private Camp camp;
}
