package tn.esprit.authorization;

import tn.esprit.services.UserService;

public class AuthFactory {
	
	public static Iauth getInstance(UserService us,String mode){
		
		
		
		
		switch(mode)
		{
		case "Basic":return new CredentialsAuth(us);
		case "ftoken" : return new FacebookAuth(us);
		case "gtoken": return new GoogleAuth(us);
		}
		
		
		return null;
	}

}
