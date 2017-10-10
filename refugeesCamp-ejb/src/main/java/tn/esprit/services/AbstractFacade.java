package tn.esprit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractFacade <T extends IdentifiedInterface>{

	private Class<T> entityClass;
	
	public AbstractFacade(Class<T> entityClass){
		this.entityClass=entityClass;
	}
	
	protected abstract EntityManager getEntityManager();
	
	public int create(T entity){
		getEntityManager().persist(entity);
		return entity.getId();
	}
	
	public void update(T entity){
		getEntityManager().merge(entity);
	}
	
	public void remove(T entity){
		getEntityManager().remove(getEntityManager().merge(entity));
	}
	
	public T find(Object id){
		return getEntityManager().find(entityClass, id);
	}
	
	public List<T> findAll(){
		CriteriaQuery cq= getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}
	
	public List<T> findRange(int startPosition,int maxResult){
		CriteriaQuery cq=getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query q= getEntityManager().createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResult+startPosition);
		
		return q.getResultList();		
	}
	
	
	public int count(){
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt=cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query q= getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
}
