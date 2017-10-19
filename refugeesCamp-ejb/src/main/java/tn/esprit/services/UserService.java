package tn.esprit.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.User;
import tn.esprit.interfaces.UserServiceInterface;
import tn.esprit.utile.EncrypterClass;

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
	
	
	
	public void registerUser(User u){
		
	   try {
		u.setPassword(EncrypterClass.Password.getSaltedPassword(u.getPassword()));
		create(u);
		//mail service
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
	
	
	public User findByUserName(String mail){	
		User result = (User)em.createQuery("select u from User u where  u.email = :mail").setParameter("mail", mail).getSingleResult(); 
		return result;
	}
	
	
	private boolean verifyUser(String userName,String pswd){
		
		User u= findByUserName(userName);
		boolean ok=false;
		
		try {
			ok= EncrypterClass.Password.match(pswd, u.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ok;
	}
	
	public User login(String userName,String pswd)
	{
		User u=null;
	    boolean ok= verifyUser(userName, pswd);
	    if(ok)
	    {u=findByUserName(userName);}
	    
	    return u;
	}
    
    


}
