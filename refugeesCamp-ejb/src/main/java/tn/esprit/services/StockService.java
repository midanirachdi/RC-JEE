package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Need;
import tn.esprit.entities.Stock;
import tn.esprit.interfaces.StockLocalInterface;
import tn.esprit.interfaces.StockRemoteInterface;

/*
* author: Salim Ben Hassine
*/

@Stateless
@LocalBean
public class StockService implements StockLocalInterface,StockRemoteInterface {
	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;

	@Override
	public boolean add(Stock stock) {
		if (stock != null) {
			em.persist(stock);
			return true;
		}
		return false;

	}

	@Override
	public List<Stock> findAll() {
		String requete = "SELECT n FROM Stock n";
		return em.createQuery(requete, Stock.class).getResultList();
	}

	@Override
	public boolean update(Stock stock) {
		// merge old and new instances
		if (stock != null) {
			em.merge(stock);
			return true;
		}
		return false;

	}

	@Override
	public Stock findById(int id) {
		return em.find(Stock.class, id);

	}

	@Override
	public boolean delete(Stock stock) {
		if (stock != null) {
			em.remove(em.merge(stock));
			return true;
		}
		return false;
	}
	/* ********** a remplacer ***/
	public Need findNeedById(int id) {
		return em.find(Need.class, id);

	}
	/* *****************************/
	
	public boolean AcceptNeedDemand(Need need) {
		Stock stock=new Stock();
		int status=0;
		if(need.getStatus()==1) {
			
			String req="Select s from stock s where s.stockType=:p";
			stock=em.createQuery(req,Stock.class).setParameter("p",need.getType()).getSingleResult();
		
			/* * check Status **/ 
			if(stock.getQteInStock()>=need.getQuantity()) {
				/* status modification */
				status=1;
				stock.setQteInStock(stock.getQteInStock()-need.getQuantity());
				need.setStatus(status);
				/* persist */
				update(stock);
				if (need != null) {
					em.merge(need);}
				return true;
			}
		
		
		}
		
		return false;
	}
	
	public boolean RefuseNeedDemand(Need need) {
		Stock stock=new Stock();
		int status=0;
		if(need.getStatus()==-1) {
			
			String req="Select s from stock s where s.stockType=:p";
			stock=em.createQuery(req,Stock.class).setParameter("p",need.getType()).getSingleResult();
		
			/* * check Status **/ 
			if(stock.getQteInStock()>=need.getQuantity()) {
				/* status modification */
				status=-1;
				need.setStatus(status);
				/* persist */
				if (need != null) {
					em.merge(need);}
				return true;
			}
		
		
		}
		
		return false;
	}
	
	
}

