package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.JobOffer;

@Remote
public interface JobOfferInterface {
	
	void add(JobOffer joboffer);

	void update(JobOffer joboffer);

	List<JobOffer> findAll();

	JobOffer findById(int id);

	void delete(JobOffer joboffer);

}
