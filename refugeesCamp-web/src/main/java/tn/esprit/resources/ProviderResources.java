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

import tn.esprit.entities.Provider;
import tn.esprit.services.ProviderService;

/*
* author: Salim Ben Hassine
*/
@RequestScoped
@Path("/stock/provider")
public class ProviderResources {

	@EJB
	ProviderService providerService;
	

	public ProviderResources() {
		super();
	}

	public ProviderService getproviderService() {
		return providerService;
	}

	public void setProductService(ProviderService providerService) {
		this.providerService = providerService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddProduct(Provider provider) {
		
		if (providerService.add(provider)) return Response.status(Status.CREATED).build();
		return Response.status(Status.NOT_FOUND).build();
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAllProduct() {
		
		List<Provider> provider = new ArrayList<Provider>();
		provider = providerService.findAll();

		if (!provider.isEmpty()) return Response.status(Status.OK).entity(provider).build();
		
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetProductById(@PathParam(value = "id") int id) {
		Provider provider = providerService.findById(id);
		if(provider!=null)
			return Response.status(Status.OK).entity(provider).build();
		return Response.status(404).build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteProduct(@PathParam(value = "id") int id) {
		
		Provider provider = providerService.findById(id);
		
		if (providerService.delete(provider))
			return Response.status(Status.OK).build();
		return Response.status(404).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response UpdateProvider(Provider provider) {
		Provider exProvider=providerService.findById(provider.getId());
		//Change Tests:
		if(provider.getProviderName()!=null) {exProvider.setProviderName(provider.getProviderName());}
		if(provider.getAddress()!=null) {exProvider.setAddress(provider.getAddress());}
		if(provider.getCountry()!=null) {exProvider.setCountry(provider.getCountry());}
		if(provider.getTel()!=0) {exProvider.setTel(provider.getTel());}
		if(provider.getStock()!=null) {exProvider.setStock(provider.getStock());}

		if(providerService.update(exProvider))
			return Response.status(Status.OK).build();
		return Response.status(Status.NOT_FOUND).build();
	}
}
