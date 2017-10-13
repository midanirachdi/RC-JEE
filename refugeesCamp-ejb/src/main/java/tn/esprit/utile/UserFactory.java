package tn.esprit.utile;

import tn.esprit.entities.Admin;
import tn.esprit.entities.CampChef;
import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.User;
import tn.esprit.entities.Volunteer;

public class UserFactory {
	
	public static User getInstance(String type){
		User u;
		switch (type) {
		case "Admin":u=new Admin();break;
		case "CampChef":u=new CampChef();break;
		case "DistrictChef":u=new DistrictChef();break;
		case "Volunteer":u=new Volunteer();break;
		
		default:
			u=null;
		}
		
		return u;
	}

}
