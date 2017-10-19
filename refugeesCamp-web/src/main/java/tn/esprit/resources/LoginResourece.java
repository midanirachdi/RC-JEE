package tn.esprit.resources;

import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import tn.esprit.authorization.CredentialsAuth;
import tn.esprit.authorization.Iauth;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;

@Path("/login")
@RequestScoped
public class LoginResourece {
	
	private final String KEY_B64=Base64.getEncoder().encodeToString("secret".getBytes());
	
	@Inject 
	private UserService us;
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logUser(@HeaderParam("Authorization") String authStr)
	{
	     Iauth auth= new CredentialsAuth(us);
	    User u= auth.authentify(authStr);
	    if(u==null)
	    {
	    	   return Response.status(Status.FORBIDDEN).entity("Error")
	   				.build();
	    }
	  
	    

	   String jwtString = Jwts.builder()
			   .claim("id",u.getId())
			   .claim("role", u.getClass().getSimpleName())
			   .signWith(SignatureAlgorithm.HS512, KEY_B64).compact();
	   
	  
	   
	   return Response.status(200)
				.entity(jwtString)
				.build();

	}
	
	

}
