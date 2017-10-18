package tn.esprit.resources;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.authorization.AllowTo;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;

@Path("users")
@RequestScoped
public class UserResource {
	
	@Inject 
	private UserService us;
	
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doRegister(String req)
	{
		User user=null;
		try {
			user = new ObjectMapper().readValue(req, User.class);
			us.registerUser(user);
			
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
		
		return Response.status(Response.Status.ACCEPTED).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"Admin"})
	public Response doList()
	{
		return Response.status(Response.Status.ACCEPTED).entity(us.findAll()).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response doFindById(@PathParam("id") String id) 
	{
		return Response.status(Response.Status.FOUND).entity(us.find(Integer.parseInt(id))).build();
	}
	
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doUpdate(String req)
	{
		User user=null;
		try {
			user = new ObjectMapper().readValue(req, User.class);
			us.update(user);
			
		} catch (IOException e) {
			return Response.status(Response.Status.NOT_MODIFIED).build();
		}
		
		return Response.status(Response.Status.ACCEPTED).build();
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response doDelete(@PathParam("id") String id) 
	{	
		User usToDelete=us.find(Integer.parseInt(id));
		us.remove(usToDelete);
		return Response.status(Response.Status.ACCEPTED).build();
	}
	
	
	
	
	
	
	
	
	
	
}
