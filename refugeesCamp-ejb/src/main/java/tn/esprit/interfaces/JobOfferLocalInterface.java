package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.JobOffer;

@Local
public interface JobOfferLocalInterface {
	
	void add(JobOffer joboffer);

	void update(JobOffer joboffer);

	List<JobOffer> findAll();

	JobOffer findById(int id);

	void delete(JobOffer joboffer);

}
