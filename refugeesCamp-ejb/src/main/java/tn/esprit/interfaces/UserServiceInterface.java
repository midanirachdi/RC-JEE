package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.User;
import tn.esprit.utile.SearchCriteria;

@Local
public interface UserServiceInterface {
	
	
	void registerUser(User u);
	
	 User findByUserName(String mail);
	 
	 User login(String userName,String pswd);
	 
	 boolean changePassword(int id,String newPassword);
	 
	 void updateUserNoPassword(User u);
	 
	 String generatePassword(int id);
	 
	 List<User> searchUser(List<SearchCriteria> params);
	 
	 
	 List<User> searchParams(String id,String lName,String fName,String email);
}
