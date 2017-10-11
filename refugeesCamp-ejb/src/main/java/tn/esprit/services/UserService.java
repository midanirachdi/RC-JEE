package tn.esprit.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.User;
import tn.esprit.interfaces.UserServiceInterface;

@Stateless
@LocalBean
public class UserService extends AbstractFacade<User> implements UserServiceInterface{
	
    public UserService() {
        super(User.class);
    }
    
    
    @PersistenceContext
    private EntityManager em;


	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	
	
	public void registerUser(User u,String type){
		
		
	}
    
    


}
