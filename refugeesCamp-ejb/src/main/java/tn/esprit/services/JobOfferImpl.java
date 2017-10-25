package tn.esprit.services;

import java.util.Date;
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
public class JobOfferImpl implements JobOfferRemoteInterface, JobOfferLocalInterface {

	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;

	@Override
	public boolean add(JobOffer joboffer) {

		if (joboffer != null) {
			Date date_debut = joboffer.getBegindate();
			Date date_fin = joboffer.getEnddate();

			if (date_debut.before(date_fin) == true) {
				em.persist(joboffer);
				return true;
			}
			return false;
		}
		return false;

	}

	@Override
	public List<JobOffer> findAll() {
		String requete = "SELECT jo FROM JobOffer jo ORDER BY jo.begindate DESC";
		return em.createQuery(requete, JobOffer.class).getResultList();
	}

	@Override
	public boolean update(JobOffer joboffer) {
		// merge old and new instances
		if (joboffer != null) {
			Date date_debut = joboffer.getBegindate();
			Date date_fin = joboffer.getEnddate();

			if (date_debut.before(date_fin) == true) {
				em.merge(joboffer);
				return true;
			}
			return false;
		}
		return false;

	}

	@Override
	public JobOffer findById(int id) {
		// méthode typée : doit avoir une classe de retour
		return em.find(JobOffer.class, id);

	}

	@Override
	public boolean delete(JobOffer joboffer) {
		if (joboffer != null) {
			em.remove(em.merge(joboffer));
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobOffer> findByDistrictChief(int dc_id) {

		String requete = "SELECT jo FROM JobOffer jo JOIN jo.districtchef d WHERE d.id= ?1 ORDER BY jo.begindate DESC";
		return em.createQuery(requete).setParameter(1, dc_id).getResultList();

	}

}
