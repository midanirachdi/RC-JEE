package tn.esprit.resources;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.ejb.EJB;
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
import tn.esprit.entities.JobOffer;
import tn.esprit.entities.Refugee;
import tn.esprit.entities.User;
import tn.esprit.services.JobOfferImpl;
import tn.esprit.services.RefugeeService;
import tn.esprit.services.UserService;
import tn.esprit.utils.GenerateCoverLetterPdf;

@Path("/joboffers")
public class JobOfferResource {

	private final String KEY_B64 = Base64.getEncoder().encodeToString("secret".getBytes());
	
	@EJB
	JobOfferImpl joService;

	@EJB
	RefugeeService rs;
	@EJB
	UserService us;
	@EJB
	RefugeeService refugeeS;

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
	@Produces(MediaType.APPLICATION_JSON)
	@AllowTo(roles = { "DistrictChef" })
	public Response AddJobOffer(
			JobOffer jo,
			@HeaderParam("Authorization") String auth) {
		String token=auth.split(" ")[1];
		Jws<Claims> jws = null;
		jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(token);
		int iduser = Integer.parseInt(jws.getBody().get("id").toString());
		User u=us.find(iduser);
		
		jo.setDistrictchef((DistrictChef)u);
		if (joService.add(jo))
			return Response.status(Status.CREATED).build();
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@AllowTo(roles = { "DistrictChef","CampChef" })
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
	@AllowTo(roles={"DistrictChef"})
	public Response GetJobOfferById(@PathParam(value = "id") int id) {
		JobOffer jo = joService.findById(id);
		if (jo != null)
			return Response.status(Status.CREATED).entity(jo).build();
		return Response.status(404).build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	//@AllowTo(roles = { "DistrictChef" })
	public Response DeleteJobOffer(@PathParam(value = "id") int id) {
		JobOffer jo = joService.findById(id);
		if (joService.delete(jo))
			return Response.status(Status.ACCEPTED).build();
		return Response.status(404).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AllowTo(roles = { "DistrictChef" })
	public Response UpdateJobOffer(JobOffer jo) {
		if (joService.update(jo))
			return Response.status(Status.ACCEPTED).build();
		return Response.status(Status.NOT_MODIFIED).build();
	}

	@GET
	@Path("/{id_jobOffer}/best_candidates")
	@Produces(MediaType.APPLICATION_JSON)
	//@AllowTo(roles = { "CampChef" })
	public Response GetBestCandidates(@PathParam(value = "id_jobOffer") int id_jobOffer) {
		JobOffer jo = joService.findById(id_jobOffer);
		if (jo != null) {
			List<Refugee> bestCandidates = new ArrayList<>();
			bestCandidates = refugeeS.findBestCandidates(jo.getFieldOfWork());
			if (!bestCandidates.isEmpty()) {
				for (Refugee r : bestCandidates) {
					refugeeS.sendMail(jo.getTitle(), r.getEmail(), jo.getId(), r.getId());
				}

				return Response.status(Status.ACCEPTED).entity(bestCandidates).build();
			}
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/{id_jobOffer}/refugees/{id_refugee}/pdf")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GenerateMotivationLetter(@PathParam(value = "id_jobOffer") int id_jobOffer,
			@PathParam(value = "id_refugee") int id_refugee) {
		JobOffer jo = joService.findById(id_jobOffer);
		Refugee r = refugeeS.findById(id_refugee);
		if (jo != null && r != null) {
			GenerateCoverLetterPdf g = new GenerateCoverLetterPdf();
			g.topdf(jo, r);
			return Response.ok("Thank you for using our services . You will find your cover letter in your desktop .")
					.build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

}
