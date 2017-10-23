package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.JobOffer;

@Remote
public interface JobOfferRemoteInterface {
	
	boolean add(JobOffer joboffer);

	boolean update(JobOffer joboffer);

	List<JobOffer> findAll();

	JobOffer findById(int id);
	
	List<JobOffer> findByDistrictChief(int id);

	boolean delete(JobOffer joboffer);

}
