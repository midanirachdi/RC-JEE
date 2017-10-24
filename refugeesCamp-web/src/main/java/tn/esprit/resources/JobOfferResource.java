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
import tn.esprit.entities.CampChef;
import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.JobOffer;
import tn.esprit.services.JobOfferImpl;
import tn.esprit.services.RefugeeService;
import tn.esprit.services.UserService;


@Path("/joboffers")
@RequestScoped
public class JobOfferResource {

	@EJB
	JobOfferImpl joService;

	@EJB
	RefugeeService rs;
	@EJB
	UserService us;
	
	public JobOfferResource() {
		super();
	}

	public JobOfferImpl getJoService() {
		return joService;
	}

	public void setJoService(JobOfferImpl joService) {
		this.joService = joService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	//@AllowTo(roles={"Admin"})
	public Response AddJobOffer(
			JobOffer jo) {
		if (joService.add(jo))
			return Response.status(Status.CREATED).build();
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAllJobOffers() {
		List<JobOffer> jolist = new ArrayList<JobOffer>();
		jolist = joService.findAll();

		if (!jolist.isEmpty())
			return Response.status(Status.CREATED).entity(jolist).build();
		return Response.status(Status.NOT_FOUND).build();
	}


	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetJobOfferById(@PathParam(value = "id") int id) {
		JobOffer jo = joService.findById(id);
		if(jo!=null)
			return Response.status(Status.CREATED).entity(jo).build();
		return Response.status(404).build();
	}

	@GET
	@Path("/DistrictChef/{dc_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetJobOffersByDistrictChief(@PathParam(value = "dc_id") int dc_id) {
		
		List<JobOffer> jolist = new ArrayList<JobOffer>();
		jolist = joService.findByDistrictChief(dc_id);

		if (!jolist.isEmpty())
			return Response.status(Status.CREATED).entity(jolist).build();
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteJobOffer(@PathParam(value = "id") int id) {
		JobOffer jo = joService.findById(id);
		if (joService.delete(jo))
			return Response.status(Status.CREATED).build();
		return Response.status(404).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response UpdateJobOffer(JobOffer jo) {
		if(joService.update(jo))
			return Response.status(Status.CREATED).build();
		return Response.status(Status.NOT_FOUND).build();
	}

}
