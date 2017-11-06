package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.StockNotification;

/*
* author: Salim Ben Hassine
*/
@Stateless
@LocalBean
public class StockNotificationService {

	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;
	
	public boolean add(StockNotification stn) {
		if (stn != null) {
			em.persist(stn);
			return true;
		}
		return false;

	}

	public List<StockNotification> findAll() {
		
		String requete = "SELECT n FROM StockNotification n where n.status=:p";
		
		List<StockNotification> lr= em.createQuery(requete, StockNotification.class).setParameter("p",0).getResultList();
		if(lr.size()>0) {
		for(StockNotification sn : lr) {
			sn.setStatus(1);
			em.merge(sn);
		}}
		return lr;
	}

	public boolean update(StockNotification stockNotification) {
		// merge old and new instances
		if (stockNotification != null) {
			em.merge(stockNotification);
			return true;
		}
		return false;

	}

	public StockNotification findById(int id) {
		return em.find(StockNotification.class, id);

	}

	public boolean delete(StockNotification stockNotification) {
		if (stockNotification != null) {
			em.remove(em.merge(stockNotification));
			return true;
		}
		return false;
	}
}

