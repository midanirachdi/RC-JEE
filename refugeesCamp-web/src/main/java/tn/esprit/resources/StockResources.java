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

import tn.esprit.entities.Need;
import tn.esprit.entities.Stock;
import tn.esprit.services.NeedImpl;
import tn.esprit.services.StockService;

/*
* author: Salim Ben Hassine
*/
@RequestScoped
@Path("/stock")
public class StockResources {

	@EJB
	StockService stockService;
	@EJB
	NeedImpl needService;
	

	public StockResources() {
		super();
	}

	public StockService getstockService() {
		return stockService;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

	public NeedImpl getNeedService() {
		return needService;
	}

	public void setNeedService(NeedImpl needService) {
		this.needService = needService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddStock(Stock stock) {
		
		if (stockService.add(stock)) return Response.status(Status.CREATED).build();
		return Response.status(Status.NOT_FOUND).build();
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAllStock() {
		
		List<Stock> stock = new ArrayList<Stock>();
		stock = stockService.findAll();

		if (!stock.isEmpty()) return Response.status(Status.OK).entity(stock).build();
		
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetStockById(@PathParam(value = "id") int id) {
		Stock stock = stockService.findById(id);
		if(stock!=null)
			return Response.status(Status.OK).entity(stock).build();
		return Response.status(404).build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteStock(@PathParam(value = "id") int id) {
		
		Stock stock = stockService.findById(id);
		
		if (stockService.delete(stock))
			return Response.status(Status.OK).build();
		return Response.status(404).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response UpdateStock(Stock stock) {
		Stock exStock=stockService.findById(stock.getId());
		//Change Tests:
		if(stock.getStockType()!=null) {exStock.setStockType(stock.getStockType());}
		if(stock.getQteTotal()!=0) {exStock.setQteTotal(stock.getQteTotal());}
		if(stock.getQteInStock()!=0) {exStock.setQteInStock(stock.getQteInStock());}
		if(stock.getStockValue()!=0) {exStock.setStockValue(stock.getStockValue());}
		if(stock.getNotes()!=null) {exStock.setNotes(stock.getNotes());}
		
		if(stockService.update(exStock))
			return Response.status(Status.OK).build();
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("/manageneeds")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listRequestedNeeds() {
		
		List<Need> need = new ArrayList<Need>();
		need = needService.listAll();

		if (!need.isEmpty()) return Response.status(Status.OK).entity(need).build();
		
		return Response.status(Status.BAD_GATEWAY).build();
	}
	
	@GET
	@Path("/manageneeds/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetNeedRequestById(@PathParam(value = "id") int id) {
		Need need = stockService.findNeedById(id);
		if(need!=null)
			return Response.status(Status.OK).entity(need).build();
		return Response.status(404).build();
	}
	
	

}


