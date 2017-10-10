package tn.esprit.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import tn.esprit.entities.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService extends AbstractFacade<User>{


	
	
	
    public UserService() {
        super(User.class);
    }

    private EntityManager em;
    
	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}
    
    


}
