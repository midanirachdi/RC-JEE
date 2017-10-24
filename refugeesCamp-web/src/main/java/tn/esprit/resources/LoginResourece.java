package tn.esprit.resources;

import java.net.URI;
import java.net.URL;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import tn.esprit.authorization.CredentialsAuth;
import tn.esprit.authorization.Iauth;
import tn.esprit.entities.User;
import tn.esprit.services.MailSenderService;
import tn.esprit.services.UserService;

@Path("/home")
@RequestScoped
public class LoginResourece {
	
	private final String KEY_B64=Base64.getEncoder().encodeToString("secret".getBytes());
	
	@Inject 
	private UserService us;
	
	
	 @Context
	 UriInfo uri;
	
	
	@Inject 
	private MailSenderService ms;
	
	@GET
	@Path("/login")
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
				.header(HttpHeaders.AUTHORIZATION,"Bearer "+jwtString)
				.build();

	}
	
	
	@GET
	@Path("/reset")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetMail(@QueryParam("email") String email)
	{	
		User u=null;
		u=us.findByUserName(email);
		if(u==null)
			return Response.status(Status.NOT_FOUND).build();
		
		try {
			   Date exp = new Date(System.currentTimeMillis() + 300000);
			   String jwtString = Jwts.builder()
					   .claim("id",u.getId())
					   .claim("role","Reset")
					   .setExpiration(exp)
					   .signWith(SignatureAlgorithm.HS512, KEY_B64).compact();
			   URI baseUri = uri.getBaseUri();
			   URL baseUrl= baseUri.toURL();
			   URL composeUrl=new URL(baseUrl.getProtocol(), baseUrl.getHost(), baseUrl.getPort(), baseUrl.getFile() + "home/credentials?taccess="+jwtString, null);
			   String subject="reset mail";
			   String content="To reset your password please click the link below \n"+composeUrl+"\n this link is valid only for 5 mn";
			   ms.send(u.getEmail(),subject,content);
			   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(200).build();
	}
	
	
	@GET
	@Path("/credentials")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePasswd(@QueryParam("taccess") String taccess){
		Jws<Claims> jws = null;
	   String newPass;
		try {
			jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(taccess);

			int id = Integer.parseInt(jws.getBody().get("id").toString());
			 newPass=us.generatePassword(id);
			 User u=us.find(id);
			   String subject="reset mail";
			   String content="You'r new password is : "+newPass;
			   ms.send(u.getEmail(),subject,content);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return Response.status(200).build();
	}
	
	
	
	

}
