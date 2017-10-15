package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import tn.esprit.entities.Camp;
import tn.esprit.interfaces.CampRemoteInterface;

@Stateless
@LocalBean
public class CampService implements CampRemoteInterface{
	
	
    @PersistenceContext
    private EntityManager em;
    
    
	public void addCamp(Camp c){
		em.persist(c);
		em.flush();
	}
	
	
	public void updateCamp(Camp c){
		em.merge(c);
	}
	
	
	
	public void deleteCamp(Camp c){
		em.remove(c);
	}
	
	
	public Camp findCamp(int id){
		return em.find(Camp.class, id);
	}
	
	
	
	public List<Camp> findAll(){
		CriteriaQuery cq=em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Camp.class));
		return em.createQuery(cq).getResultList();
	}

}
