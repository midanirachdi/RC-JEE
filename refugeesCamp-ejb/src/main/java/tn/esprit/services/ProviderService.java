package tn.esprit.services;
/*
* author: Salim Ben Hassine
*/

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Provider;
import tn.esprit.interfaces.ProviderLocalInterface;
import tn.esprit.interfaces.ProviderRemoteInterface;

@Stateless
@LocalBean
public class ProviderService implements ProviderLocalInterface,ProviderRemoteInterface {
	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;

	@Override
	public boolean add(Provider provider) {
		if (provider != null) {
			em.persist(provider);
			return true;
		}
		return false;

	}

	@Override
	public List<Provider> findAll() {
		String requete = "SELECT n FROM Provider n";
		return em.createQuery(requete, Provider.class).getResultList();
	}

	@Override
	public boolean update(Provider provider) {
		// merge old and new instances
		if (provider != null) {
			em.merge(provider);
			return true;
		}
		return false;

	}

	@Override
	public Provider findById(int id) {
		return em.find(Provider.class, id);

	}

	@Override
	public boolean delete(Provider provider) {
		if (provider != null) {
			em.remove(em.merge(provider));
			return true;
		}
		return false;
	}
}