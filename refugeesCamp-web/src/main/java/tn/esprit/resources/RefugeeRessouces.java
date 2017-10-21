package tn.esprit.resources;

import java.util.ArrayList;
import java.util.List;

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

import tn.esprit.authorization.AllowTo;
import tn.esprit.entities.JobOffer;
import tn.esprit.entities.Refugee;
import tn.esprit.services.RefugeeService;

@RequestScoped
@Path("/Refugee")
public class RefugeeRessouces {

	@EJB
	RefugeeService refugeeS;

	public RefugeeRessouces() {
		super();
	}
	
	public RefugeeService getRefugeeS(){
		return refugeeS;
	}
	
	public void setRefugeeService(RefugeeService refugeeS){
		this.refugeeS = refugeeS;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAllRefugees() {
		List<Refugee> l = new ArrayList<Refugee>();
		l= refugeeS.findAll();
		if (l != null)
			return Response.status(200).entity(l).build();
		else return Response.status(404).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetRefugeeById(@PathParam(value = "id") int id) {
		Refugee r = refugeeS.findById(id);
		if (r != null)
			return Response.status(200).entity(r).build();
		else return Response.status(Response.Status.NOT_FOUND).entity("refugee with id : "+ id + " not found !").build();
	}
	
	 
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteRefugee(@PathParam(value = "id") int id) {
		Refugee re = refugeeS.findById(id);
		if (re != null) {
			if (refugeeS.delete(re)){
			return Response.ok("Refugee deleted successfuly").build();}
			else return Response.status(500).build(); // 500 internal server error
		} else return Response.status(Response.Status.NO_CONTENT).entity("refugee with id : "+ id + " not found !").build();
	}
	// @AllowTo(roles={"Admin"})
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON) 
	public Response AddRefugee(Refugee r)
	{
		if (refugeeS.add(r)) {
		return Response.status(Status.CREATED).build();	
		}
		else return Response.status(400).build(); // 400 Bad request
	}
	
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateRefugee(@PathParam(value = "id") int id,Refugee r){
		Refugee re = refugeeS.findById(id);
		if (re != null) {
			r.setId(id); // we have to add the setId or the hibernate will add a new record in DataBase
			if (refugeeS.update(r)) {
			return Response.ok("Refugee updated successfuly").build();
			}else return Response.status(304).build(); // 304 Not modified
		} else return Response.status(Response.Status.NOT_FOUND).entity("refugee with id : "+ id + " not found !").build();
	}
	
	@GET
	@Path("/gender/{sex}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetRefugeeByGender(@PathParam(value="sex") String sex) {
		int f = refugeeS.countRefugeePerGender(sex);
		int f1 = refugeeS.findAll().size();
		double f2 = (f*100)/f1;
			return Response.status(200).entity(f2).build();
	}
	
	@GET
	@Path("/AgeCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public Response CountRefugeePerAge() {
		List<Integer> liste = refugeeS.countRefugeePerAge();
		return Response.status(200).entity(liste).build();
	}
}
