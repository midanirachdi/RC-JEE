package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.JobOffer;
import tn.esprit.interfaces.JobOfferLocalInterface;
import tn.esprit.interfaces.JobOfferRemoteInterface;


@Stateless
@LocalBean
public class JobOfferImpl implements JobOfferRemoteInterface,JobOfferLocalInterface {

	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;

	@Override
	public void add(JobOffer joboffer) {

		em.persist(joboffer);
		em.flush();
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

		em.remove(em.merge(joboffer));
		System.out.println("offre de travail supprimée");

	}

	@Override
	public List<JobOffer> findAll() {
		
		String requete = "SELECT jo FROM JobOffer jo";
		return em.createQuery(requete,JobOffer.class).getResultList();
	}

}
