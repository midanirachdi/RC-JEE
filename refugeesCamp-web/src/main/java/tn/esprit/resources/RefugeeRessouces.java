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
	public List<Refugee> GetAllRefugees() {
		return refugeeS.findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Refugee GetRefugeeById(@PathParam(value = "id") int id) {
		return refugeeS.findById(id);
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String DeleteRefugee(@PathParam(value = "id") int id) {
		Refugee re = refugeeS.findById(id);
		refugeeS.delete(re);
		return "Le refugee " + re.getId() + " a été supprimée";
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public String AddRefugee(Refugee r)
	{
		refugeeS.add(r);
		return "ajout effectué !!";
	}
	
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String UpdateRefugee(@PathParam(value = "id") int id,Refugee r){
		r.setId(id); // we have to add the setId or the hibernate will add a new record in DataBase
		refugeeS.update(r);
		return "update with seccess";
	}
}
