package tn.esprit.resources;

import java.io.IOException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
import javax.ws.rs.core.Response.Status;

import tn.esprit.entities.Need;
import tn.esprit.interfaces.NeedService;
import tn.esprit.services.NeedImpl;

@Path("/need")
@RequestScoped
public class NeedRessource {
	@EJB
	NeedImpl metier;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProject(Need t){
		metier.addNeed(t);
		return Response.status(Status.CREATED).build();	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNeed()
	{
		return Response.ok(metier.listAll()).build();	
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProject(Need t)
	{
	metier.updateNeed(t);
	return Response.status(Status.ACCEPTED).build();
		
	}
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProject(Need t)
	{
	metier.deleteNeed(t);
	return Response.status(Status.ACCEPTED).build();
		
	}
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response getNeedById(@PathParam("id")int id)
	{
		return Response.ok(metier.findNeedById(id)).build();	
	}
	
	/*@GET
	@Path("/listerpar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNeed2()
	{
		
		return Response.ok(metier.findAll()).build();
		
	}*/
	
	
	
	@GET
	@Path("/test")
	public String test()
	{
		return "TEST refugee";		
	}

}
