package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Camp;
import tn.esprit.interfaces.CampRemoteInterface;


@Stateless
@LocalBean
public class CampService implements CampRemoteInterface{

	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;

	@Override
	public void add(Camp c) {
		em.persist(c);
		em.flush();

	}

	@Override
	public void update(Camp c) {
		em.merge(c);
	}

	@Override
	public Camp findById(int id) {
		return em.find(Camp.class, id);

	}

	@Override
	public void delete(Camp c) {
		em.remove(em.merge(c));
	}

	@Override
	public List<Camp> findAll() {
		
		String requete = "SELECT jo FROM Camp jo";
		return em.createQuery(requete,Camp.class).getResultList();
	}

}
