package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Camp;
import tn.esprit.entities.Donation;
import tn.esprit.interfaces.DonationRemoteInterface;

@Stateless
@LocalBean
public class DonationService implements DonationRemoteInterface{
	
	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;
	@Override
	public boolean add(Donation d) {
		em.persist(d);
		return true;
	}

	@Override
	public boolean delete(Donation d) {
		em.remove(em.merge(d));
		return true;
	}

	@Override
	public List<Donation> findAll() {
		String requete = "SELECT d FROM Donation d";
		return em.createQuery(requete,Donation.class).getResultList();
	}

	@Override
	public List<Donation> findByCamp(Camp c) {
		String requete = "SELECT d FROM Donation d WHERE d.to_camp ="+c.getId();
		return em.createQuery(requete,Donation.class).getResultList();
	}

	@Override
	public Donation findById(String id) {
		return em.find(Donation.class, id);
	}

	@Override
	public boolean update(Donation d) {
		em.merge(d);
		return true;
	}
	
}
