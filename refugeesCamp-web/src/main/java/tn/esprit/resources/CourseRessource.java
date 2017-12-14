package tn.esprit.resources;

import java.io.IOException;
import java.util.Base64;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import tn.esprit.authorization.AllowTo;
import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.Course;
import tn.esprit.entities.User;
import tn.esprit.interfaces.CourseService;
import tn.esprit.services.CourseImpl;
import tn.esprit.services.UserService;

@Path("/course")
@RequestScoped
public class CourseRessource {
	
	private final String KEY_B64 = Base64.getEncoder().encodeToString("secret".getBytes());
	
	@EJB
	CourseImpl metier;
	
	@EJB
	UserService us;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"DistrictChef"})
	public Response addProject(Course c,@HeaderParam("Authorization") String auth){
		
			String token=auth.split(" ")[1];
			Jws<Claims> jws = null;
			jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(token);
			int iduser = Integer.parseInt(jws.getBody().get("id").toString());
			User u=us.find(iduser);
			System.out.println(u.getId());
			c.setDischef((DistrictChef)u);
			metier.addCourse(c);
		return Response.status(Status.CREATED).build();	
	}
	/////////////
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"DistrictChef"})
	@Path("/mycourses")
	public Response getCourse(@HeaderParam("Authorization") String auth)
	{
		String token=auth.split(" ")[1];
		Jws<Claims> jws = null;
		jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(token);
		int iduser = Integer.parseInt(jws.getBody().get("id").toString());
		User u=us.find(iduser);
			
		return Response.ok(	metier.getCourseByDis((DistrictChef)u)).build();	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@AllowTo(roles={"DistrictChef"})
	public Response getCourse()
	{
		return Response.ok(metier.listAll()).build();	
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"DistrictChef","Admin"})
	public Response updateProject(Course c)
	{
	metier.updateCourse(c);
	return Response.status(Status.ACCEPTED).build();
		
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"DistrictChef"})
	public Response deleteProject(Course c)
	{
	metier.deleteCourse(c);
	return Response.status(Status.ACCEPTED).build();
		
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"DistrictChef"})
	public Response getCourseById(@PathParam("id")int id)
	{
		return Response.ok(metier.findCourseById(id)).build();	
	}
	
	
	@GET
	@Path("/test")
	public String test()
	{
		return "TEST refugee";		
	}

}
