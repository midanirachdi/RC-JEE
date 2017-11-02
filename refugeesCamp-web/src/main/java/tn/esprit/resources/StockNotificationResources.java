package tn.esprit.resources;
/*
* author: Salim Ben Hassine
*/

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.authorization.AllowTo;
import tn.esprit.entities.StockNotification;
import tn.esprit.services.StockNotificationService;

@RequestScoped
@Path("/stock/notification")
public class StockNotificationResources {

	@EJB
	StockNotificationService snServ;
	
	public StockNotificationResources() {
		super();
	}

	public StockNotificationService getStockNotificationService() {
		return snServ;
	}

	public void setStockNotificationService(StockNotificationService snServ) {
		this.snServ = snServ;
	}



	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@AllowTo(roles = { "Admin" })
	public Response GetAllStockNotification() {
		
		List<StockNotification> stockNotification = new ArrayList<StockNotification>();
		stockNotification = snServ.findAll();

		if (!stockNotification.isEmpty()) return Response.status(Status.OK).entity(stockNotification).build();
		
		return Response.status(Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@AllowTo(roles = { "Admin" })
	public Response DeleteStockNotification(@PathParam(value = "id") int id) {
		
		StockNotification stockNotification = snServ.findById(id);
		
		if (snServ.delete(stockNotification))
			return Response.status(Status.OK).build();
		return Response.status(404).build();
	}
}