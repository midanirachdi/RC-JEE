package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import tn.esprit.entities.Camp;

@Stateless
@LocalBean
public class CampService {
	
	
    @PersistenceContext
    private EntityManager em;
    
    
	public Camp create(Camp entity){
		em.persist(entity);
		return entity;
	}
	
	
	public void update(Camp entity){
		em.merge(entity);
	}
	
	
	
	public void remove(Camp entity){
	em.remove(em.merge(entity));
	}
	
	
	public Camp find(int id){
		return em.find(Camp.class, id);
	}
	
	
	
	public List<Camp> findAll(){
		CriteriaQuery cq=em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Camp.class));
		return em.createQuery(cq).getResultList();
	}

}
