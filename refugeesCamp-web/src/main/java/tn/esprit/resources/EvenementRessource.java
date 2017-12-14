package tn.esprit.resources;

import java.util.Base64;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import tn.esprit.authorization.AllowTo;
import tn.esprit.entities.CampChef;
import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.Evenement;
import tn.esprit.entities.User;
import tn.esprit.entities.Volunteer;
import tn.esprit.services.EvenementService;
import tn.esprit.services.UserService;

@Path("/evenements")
public class EvenementRessource {
	private final String KEY_B64 = Base64.getEncoder().encodeToString("secret".getBytes());
	@EJB
	EvenementService es;
	@EJB
	UserService us;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"CampChef"})
	public Response addEvenement(Evenement e , @HeaderParam("Authorization") String auth){
		String token=auth.split(" ")[1];
		Jws<Claims> jws = null;
		jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(token);
		int iduser = Integer.parseInt(jws.getBody().get("id").toString());
		User u=us.find(iduser);
		e.setCreator((CampChef)u);
		es.add(e);
		return Response.ok().build();
	}
	@GET
	@Path("/rateEvent/{idvolunteer}/{idevenement}/{mark}")
	@AllowTo(roles={"Volunteer"})
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response RateEvenement(@PathParam("idvolunteer")int idvolunteer,@PathParam("idevenement")int idevenement,@PathParam("mark")int mark){
		Volunteer v=(Volunteer) us.find(idvolunteer);
		Evenement e=es.findById(idevenement);
		es.rateEvent(v, e, mark);
		return Response.ok().build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(){
		List<Evenement> ls= es.findAll();
		if (!ls.isEmpty())
			return Response.ok().entity(ls).build();
		return Response.noContent().build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/myevents/{id}")
	@AllowTo(roles={"CampChef"})
	public Response getMyEvents(@PathParam("id")int iduser){
		List<Evenement> ls= es.findMine(iduser);
		if (!ls.isEmpty())
			return Response.ok().entity(ls).build();
		return Response.noContent().build();
	}
	@GET
	@Path("/upcoming")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUpcoming(){
		List<Evenement> ls= es.findUpcoming();
		if (!ls.isEmpty())
			return Response.ok().entity(ls).build();
		return Response.noContent().build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvenement(@PathParam("id")int id){
		Evenement e= es.findById(id);
		if (e!=null) {
			 return Response.ok().entity(e).build();
		}
		return Response.noContent().build();
	}
	
	@GET
	@Path("/creator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCreator(@PathParam("id")int id){
		Evenement e= es.findById(id);
		CampChef c=(CampChef) us.find(e.getCreator().getId());
		if (c!=null) {
			return Response.ok().entity(c).build();
		}
		return Response.noContent().build();
	}
	@GET
	@Path("/rating/{id}")
	public Response getRating(@PathParam("id")int id){
		double note=es.calculnote(id);
		return Response.ok().entity(note+"").build();
	}
	
	@DELETE
	@Path("/{id}")
	@AllowTo(roles={"CampChef"})
	public Response deleteEvenement(@PathParam("id")int id){
		Evenement e =es.findById(id);
		es.delete(e);
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"CampChef"})
	public Response updateEvenement(Evenement e){
		es.update(e);
		return Response.ok().build();
	}
	
	

}
