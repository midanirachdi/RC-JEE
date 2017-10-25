package tn.esprit.services;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import tn.esprit.entities.User;
import tn.esprit.interfaces.UserServiceInterface;
import tn.esprit.utile.EncrypterClass;
import tn.esprit.utile.SearchCriteria;

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
		User result=null;
		try
		{
		 result = (User)em.createQuery("select u from User u where  u.email = :mail").setParameter("mail", mail).getSingleResult(); 
		}
		catch(Exception e){}
			
		 return result;
	}
	
	
	private boolean verifyUser(String userName,String pswd){
		
		boolean ok=false;
		User u=null;
		try {
		    u= findByUserName(userName);
			
			ok= EncrypterClass.Password.match(pswd, u.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ok && !u.isDisable();
	}
	
	public User login(String userName,String pswd)
	{
		User u=null;
	    boolean ok= verifyUser(userName, pswd);
	    if(ok)
	    {u=findByUserName(userName);}
	    
	    return u;
	}
	
	
	public boolean changePassword(int id,String newPassword){
		User u=find(id);
		try {
			u.setPassword(EncrypterClass.Password.getSaltedPassword(newPassword));
			update(u);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	private final String alphanum="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
	private String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*alphanum.length());
		builder.append(alphanum.charAt(character));
		}
		return builder.toString();
		}
	
	public void updateUserNoPassword(User u){
		User tempUser=find(u.getId());
	    u.setPassword(tempUser.getPassword());
	    update(u);
	}
	public String generatePassword(int id){
		
		String randomStr = randomAlphaNumeric(15);
		changePassword(id, randomStr);	
		return randomStr;
	}
	
	
	 public List<User> searchUser(List<SearchCriteria> params) {
	        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
	        CriteriaQuery<User> query = builder.createQuery(User.class);
	        Root r = query.from(User.class);
	 
	        Predicate predicate = builder.conjunction();
	 
	        for (SearchCriteria param : params) {
	            if (param.getOperation().equalsIgnoreCase(">")) {
	                predicate = builder.and(predicate, 
	                  builder.greaterThanOrEqualTo(r.get(param.getKey()), 
	                  param.getValue().toString()));
	            } else if (param.getOperation().equalsIgnoreCase("<")) {
	                predicate = builder.and(predicate, 
	                  builder.lessThanOrEqualTo(r.get(param.getKey()), 
	                  param.getValue().toString()));
	            } else if (param.getOperation().equalsIgnoreCase(":")) {
	                if (r.get(param.getKey()).getJavaType() == String.class) {
	                    predicate = builder.and(predicate, 
	                      builder.like(r.get(param.getKey()), 
	                      "%" + param.getValue() + "%"));
	                } else {
	                    predicate = builder.and(predicate, 
	                      builder.equal(r.get(param.getKey()), param.getValue()));
	                }
	            }
	        }
	        query.where(predicate);
	 
	        List<User> result = getEntityManager().createQuery(query).getResultList();
	        return result;
	    }
	 
	 public List<User> searchParams(String id,String lName,String fName,String email)
	 {
		 List<SearchCriteria> params=new ArrayList<SearchCriteria>();
		 if(!id.equals("")){
			params.add(new SearchCriteria("id", ":",Integer.parseInt(id) )) ;
		 }
		 
		 if(!fName.trim().equals(""))
		 {params.add(new SearchCriteria("firstName", ":", fName)) ;}
		 
		 
		 if(!lName.trim().equals(""))
		 {params.add(new SearchCriteria("lastName", ":", lName)) ;}

		 
		 
		 if(!email.trim().equals(""))
		 {params.add(new SearchCriteria("email", ":", email)) ;}
		 
		 return searchUser(params);
	 }
    
    


}
