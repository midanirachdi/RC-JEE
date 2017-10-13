package tn.esprit.resources;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
			us.create(itemWithOwner);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return itemWithOwner.getClass().getName();
	}
}
