package tn.esprit.resources;

import java.io.IOException;
import java.util.Base64;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import tn.esprit.authorization.AllowTo;
import tn.esprit.entities.Admin;
import tn.esprit.entities.CampChef;
import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.User;
import tn.esprit.entities.Volunteer;
import tn.esprit.services.UserService;

@Path("users")
@RequestScoped
public class UserResource {
	
	@Inject 
	private UserService us;
	
	private final String KEY_B64 = Base64.getEncoder().encodeToString("secret".getBytes());

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doRegister(String req,@HeaderParam("Authorization") String auth)
	{


		User user=null;
		try {
			user = new ObjectMapper().readValue(req, User.class);
			if(auth!=null)
			{ String token=auth.split(" ")[1];
		    String role="";
			Jws<Claims> jws = null;
			jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(token);
			role=jws.getBody().get("role").toString();
			System.out.println(role+" ****");
			
			if(((user instanceof CampChef)||(user instanceof DistrictChef)||(user instanceof Admin))&& !role.equals("Admin"))
			{return  Response.status(Response.Status.UNAUTHORIZED).build();}}
			else if (!(user instanceof Volunteer))
			return Response.status(Response.Status.UNAUTHORIZED).build();
			 
	

			us.registerUser(user);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Response.Status.FORBIDDEN).build();
		}
		
		return Response.status(Response.Status.ACCEPTED).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"Admin"})
	public Response doList()
	{
		System.out.println("method");
		return Response.status(Response.Status.ACCEPTED).entity(us.findAll()).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@AllowTo(roles={"Admin"})
	public Response doFindById(@PathParam("id") String id) 
	{
		return Response.status(Response.Status.FOUND).entity(us.find(Integer.parseInt(id))).build();
	}
	
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"Admin","CampChef","DistrictChef","Volunteer"})
	public Response doUpdate(String req,@HeaderParam("Authorization") String auth)
	{
		
		int id;
		User user=null;
		try {
			
			String token=auth.split(" ")[1];
			Jws<Claims> jws = null;
			jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(token);
			id=Integer.parseInt(jws.getBody().get("id").toString());
			user = new ObjectMapper().readValue(req, User.class);
			if(user.getId()!=id)
			 return  Response.status(Response.Status.UNAUTHORIZED).build();
			
			us.updateUserNoPassword(user);
			
		} catch (IOException e) {
			return Response.status(Response.Status.NOT_MODIFIED).build();
		}
		
		return Response.status(Response.Status.ACCEPTED).build();
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@AllowTo(roles={"Admin"})
	public Response doDelete(@PathParam("id") String id) 
	{	
		User usToDelete=us.find(Integer.parseInt(id));
		us.remove(usToDelete);
		return Response.status(Response.Status.ACCEPTED).build();
	}
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/search")
	@AllowTo(roles={"Admin"})
	public Response advancedSearch(
			@QueryParam("id") String id,
			@QueryParam("fName") String fName,
			@QueryParam("lName") String lName,
			@QueryParam("email") String email)
	{

		
		return  Response.status(Response.Status.ACCEPTED).entity(us.searchParams(id, lName, fName, email)).build();
	}
	
	
	
	@GET
	@Path("/paginator")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"Admin"})
	public Response paginatorQuery(
			@QueryParam("page") int page,
			@QueryParam("rowNumber") int rowNumber
		)
	{

		
		return  Response.status(Response.Status.ACCEPTED).header("nbr", us.count()).entity(us.findRange(page, rowNumber)).build();
	}
	
	
	
	
	
	
	
	
}
