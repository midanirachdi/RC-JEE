package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Evenement;
import tn.esprit.entities.Rating;
import tn.esprit.entities.RatingId;
import tn.esprit.entities.Volunteer;
import tn.esprit.interfaces.EvenementRemoteInterface;

@Stateless
@LocalBean
public class EvenementService implements EvenementRemoteInterface{
	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;

	@Override
	public void add(Evenement e) {
		em.persist(e);
		em.flush();
		
	}

	@Override
	public void update(Evenement e) {
		em.merge(e);
		
	}

	@Override
	public List<Evenement> findAll() {
		String requete = "SELECT ev FROM Evenement ev order by ev.dateEvent desc";
		return em.createQuery(requete,Evenement.class).getResultList();
	}

	@Override
	public Evenement findById(int id) {
		return em.find(Evenement.class, id);
	}

	@Override
	public void delete(Evenement e) {
		em.merge(e);
		for (Rating r : e.getRatings()) {
			em.remove(em.merge(r));
		}
		em.remove(em.merge(e));
	}

	@Override
	public boolean rateEvent(Volunteer v, Evenement e, int mark) {
		Rating r =new Rating(mark,v,e);
		r.setRatingId(new RatingId(v.getId(), e.getId()));
		em.merge(r);
		return true;
	}

	@Override
	public List<Evenement> findUpcoming() {
		String requete = "SELECT ev FROM Evenement ev WHERE ev.dateEvent > CURRENT_DATE order by ev.dateEvent";
		return em.createQuery(requete,Evenement.class).getResultList();
	}

	@Override
	public double calculnote(int id) {
		Evenement e =em.find(Evenement.class, id);
		int sum=0;
		for (Rating r : e.getRatings()) {
			sum+=r.getMark();
		}
		if (e.getRatings().size()!=0)
			return sum/e.getRatings().size();
		else
			return -1;
	}

	@Override
	public List<Evenement> findMine(int id) {
		String requete = "SELECT ev FROM Evenement ev where ev.creator.id = :id order by ev.dateEvent desc";
		return em.createQuery(requete,Evenement.class).setParameter("id", id).getResultList();
	}
	
	

}
