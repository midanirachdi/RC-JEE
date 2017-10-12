package tn.esprit.resources;

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

import tn.esprit.entities.JobOffer;
import tn.esprit.services.JobOfferImpl;

@RequestScoped
@Path("/joboffer")
public class JobOfferResource {

	@EJB
	JobOfferImpl joService;

	public JobOfferResource() {
		super();
	}

	public JobOfferImpl getJoService() {
		return joService;
	}

	public void setJoService(JobOfferImpl joService) {
		this.joService = joService;
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JobOffer> GetAllJobOffers() {
		return joService.findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public JobOffer GetJobOfferById(@PathParam(value = "id") int id) {
		return joService.findById(id);
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String DeleteJobOffer(@PathParam(value = "id") int id) {
		JobOffer jo = joService.findById(id);
		joService.delete(jo);
		return "L'offre " + jo.getId() + " a été supprimée";
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateJobOffer(JobOffer jo) {
		joService.update(jo);
		return jo.toString();
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String AddJobOffer(JobOffer jo) {
		joService.add(jo);
		return jo.toString();

	}
}
