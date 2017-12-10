package tn.esprit.interfaces;

import java.util.List;

import tn.esprit.entities.Evenement;
import tn.esprit.entities.Volunteer;

public interface EvenementRemoteInterface {
	void add(Evenement e);

	void update(Evenement e);

	List<Evenement> findAll();
	
	List<Evenement> findUpcoming();

	Evenement findById(int id);

	void delete(Evenement e);
	
	boolean rateEvent(Volunteer v,Evenement e,int mark);
	

}
