package tn.esprit.resources;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.entities.Evenement;
import tn.esprit.entities.Volunteer;
import tn.esprit.services.EvenementService;
import tn.esprit.services.UserService;

@Path("/evenements")
public class EvenementRessource {
	@EJB
	EvenementService es;
	@EJB
	UserService us;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEvenement(Evenement e){
		es.add(e);
		return Response.ok().build();
	}
	@POST
	@Path("/rateEvent")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response RateEvenement(@FormParam("idvolunteer")int idvolunteer,@FormParam("idevenement")int idevenement,@FormParam("mark")int mark){
		Volunteer v=(Volunteer) us.find(idvolunteer);
		Evenement e=es.findById(idevenement);
		es.rateEvent(v, e, mark);
		return Response.ok().build();
	}
	
	

}
