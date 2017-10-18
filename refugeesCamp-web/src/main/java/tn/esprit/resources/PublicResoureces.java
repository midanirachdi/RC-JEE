package tn.esprit.resources;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.entities.User;
import tn.esprit.services.UserService;



@Path("/public")
@Stateless
@LocalBean
public class PublicResoureces {
	
	
	@EJB
	private UserService us;

	@Path("/test")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String doTest(String req)
	{
		User itemWithOwner=null;
		try {
			itemWithOwner = new ObjectMapper().readValue(req, User.class);
			us.registerUser(itemWithOwner);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return itemWithOwner.getClass().getName();
	}
	
	@GET
	@Path("/login")
	public Response addUser(@HeaderParam("Athorization") String userNameAndPassword) {

		String decodedString="";
		byte[] decoded = Base64.getDecoder().decode(userNameAndPassword);
		try {
		decodedString=(new String(decoded, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Response.status(200)
			.entity("userName and password is  : " + decodedString)
			.build();

	}
	
	
	
	

}
