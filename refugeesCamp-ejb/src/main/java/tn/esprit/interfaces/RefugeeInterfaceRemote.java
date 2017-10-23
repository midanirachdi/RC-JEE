package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.JobOffer;
import tn.esprit.entities.Refugee;

@Remote
public interface RefugeeInterfaceRemote {
	boolean add(Refugee refugee);

	boolean update(Refugee refugee);
	
	boolean delete(Refugee refugee);

	List<Refugee> findAll();

	Refugee findById(int id);

	List<Refugee> findByName(String name);
	
	int countRefugeePerGender(String sex);
	
	List<Refugee> findBestCandidates(String fieldOfWork);//-midani
	
	boolean sendMail(String jobOfferTitle , String refugeeEmail, int id_jobOffer, int id_refugee);	

	//	int countRefugeePerAge(int a, int b);
}
