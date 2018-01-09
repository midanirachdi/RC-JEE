package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.News;
import tn.esprit.interfaces.NewsLocalInterface;
import tn.esprit.interfaces.NewsRemoteInterface;

@Stateless
@LocalBean
public class NewsService implements NewsRemoteInterface, NewsLocalInterface {

	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;

	@Override
	public boolean add(News news) {
		if (news != null) {
			em.persist(news);
			return true;
		}
		return false;

	}

	@Override
	public List<News> findAll() {
		String requete = "SELECT n FROM News n ORDER BY n.dateOfCreation DESC";
		return em.createQuery(requete, News.class).getResultList();
	}

	@Override
	public boolean update(News news) {
		// merge old and new instances
		if (news != null) {
			em.merge(news);
			return true;
		}
		return false;

	}

	@Override
	public News findById(int id) {
		return em.find(News.class, id);

	}
	
	/*
	public List<News> findLimitRows(int start,int rows) {
		String req="FROM News n ORDER BY n.dateOfCreation DESC";
		Query q = em.createQuery(req,News.class).setFirstResult(start).setMaxResults(rows).getResultList();
		q.setFirstResult(start);
		q.setMaxResults(rows);
		return (List<News>) q.getResultList();
		
		/*String sql = "SELECT * FROM `news` ORDER by `dateOfCreation` DESC LIMIT start=:start,10;" + 
				""
				+ "SELECT * FROM EMPLOYEE WHERE id = :employee_id";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Employee.class);
		query.setParameter("employee_id", 10);
		List results = query.list();

	}*/
	@Override
	public List<News> findByCountry(String country) {
		String req="select n from News n where n.location=:country ORDER BY n.dateOfCreation DESC";
		return em.createQuery(req, News.class)
				.setParameter("country", country)
				.getResultList();

	}
	
	@Override
	public boolean delete(News news) {
		if (news != null) {
			em.remove(em.merge(news));
			return true;
		}
		return false;
	}

}
