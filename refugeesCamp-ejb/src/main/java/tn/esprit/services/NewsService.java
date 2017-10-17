package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		String requete = "SELECT n FROM News n";
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

	@Override
	public boolean delete(News news) {
		if (news != null) {
			em.remove(em.merge(news));
			return true;
		}
		return false;
	}

}
