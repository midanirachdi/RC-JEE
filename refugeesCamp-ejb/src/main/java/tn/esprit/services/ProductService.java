package tn.esprit.services;
/*
* author: Salim Ben Hassine
*/

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Product;
import tn.esprit.interfaces.ProductLocalInterface;
import tn.esprit.interfaces.ProductRemoteInterface;

@Stateless
@LocalBean
public class ProductService implements ProductLocalInterface,ProductRemoteInterface {
	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;

	@Override
	public boolean add(Product product) {
		if (product != null) {
			em.persist(product);
			return true;
		}
		return false;

	}

	@Override
	public List<Product> findAll() {
		String requete = "SELECT n FROM Product n";
		return em.createQuery(requete, Product.class).getResultList();
	}

	@Override
	public boolean update(Product product) {
		// merge old and new instances
		if (product != null) {
			em.merge(product);
			return true;
		}
		return false;

	}

	@Override
	public Product findById(int id) {
		return em.find(Product.class, id);

	}

	@Override
	public boolean delete(Product product) {
		if (product != null) {
			em.remove(em.merge(product));
			return true;
		}
		return false;
	}
}