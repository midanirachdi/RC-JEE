package tn.esprit.authorization;



import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.HeaderParam;

import tn.esprit.entities.User;
import tn.esprit.services.UserService;


public class CredentialsAuth implements Iauth {
	
	public CredentialsAuth(UserService us) {
		this.userService=us;
	}

	private final String AUTH_PREFIX="Basic";
	
	UserService userService;
	
	@Override
	public User authentify(String crd) {
		User u=null;
		if(crd.startsWith(AUTH_PREFIX))
		{
			crd=crd.substring(5);
			crd=crd.trim();
			String decodedString="";
			byte[] decoded = Base64.getDecoder().decode(crd);
			try {
			decodedString=(new String(decoded, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
			String[] tableCred= decodedString.split(":");
			u=userService.login(tableCred[0], tableCred[1]);
			
		}
		
		
		return u;
	}

}
