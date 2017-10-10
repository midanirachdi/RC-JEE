package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.entities.JobOffer;

@Stateless
public class JobOfferImpl implements JobOfferService {

	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;

	@Override
	public void add(JobOffer joboffer) {

		em.persist(joboffer);
		System.out.println("offre de travail ajoutée");

	}

	@Override
	public void update(JobOffer joboffer) {
		// merge old and new instances
		em.merge(joboffer);
		System.out.println("offre de travail mise à jour");
	}

	@Override
	public JobOffer findById(int id) {
		// méthode typée : doit avoir une classe de retour
		return em.find(JobOffer.class, id);

	}

	@Override
	public void delete(JobOffer joboffer) {

		em.remove(joboffer);
		System.out.println("offre de travail supprimée");

	}

	@Override
	public List<JobOffer> findAll() {

		/*forcing the type to be jobOffer instead of object
		 *TypedQuery extends query 
		 */
		TypedQuery<JobOffer> query = 
				em.createQuery("SELECT jo FROM JobOffer jo",JobOffer.class);
		// query.getFirstResult();
		List<JobOffer> jobOfferList = query.getResultList();
	
		return jobOfferList;
	}

}
