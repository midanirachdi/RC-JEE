package tn.esprit.resources;

import java.util.List;

import javax.ejb.EJB;
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

import tn.esprit.entities.Camp;
import tn.esprit.services.CampService;
@Path("/camp")
public class CampRessource {
	@EJB
	CampService cs;
	
	
	public CampRessource() {
		super();
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Camp> listall(){
		return cs.findAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCamp(Camp c){
		cs.add(c);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteCamp(@PathParam("id")int id){
		Camp c =cs.findById(id);
		cs.delete(c);
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCamp(Camp c){
		cs.update(c);
		return Response.ok().build();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Camp getCamp(@PathParam("id")int id){
		return cs.findById(id);
	}
	
	
}
