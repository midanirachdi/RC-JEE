package tn.esprit.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Task;
import tn.esprit.interfaces.TaskLocalInterface;
import tn.esprit.interfaces.TaskRemoteInterface;

@Stateless
@LocalBean
public class TaskImpl implements TaskRemoteInterface, TaskLocalInterface{

	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;

	
	public boolean add(Task joboffer) {

		
				em.persist(joboffer);
				return true;
		

	}


}
