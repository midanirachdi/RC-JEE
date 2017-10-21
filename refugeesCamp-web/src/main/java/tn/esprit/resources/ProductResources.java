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

import tn.esprit.entities.Product;
import tn.esprit.services.ProductService;

/*
* author: Salim Ben Hassine
*/
@RequestScoped
@Path("/stock/product")
public class ProductResources {

	@EJB
	ProductService productService;
	

	public ProductResources() {
		super();
	}

	public ProductService getproductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddProduct(Product product) {
		
		if (productService.add(product)) return Response.status(Status.CREATED).build();
		return Response.status(Status.NOT_FOUND).build();
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAllProduct() {
		
		List<Product> product = new ArrayList<Product>();
		product = productService.findAll();

		if (!product.isEmpty()) return Response.status(Status.OK).entity(product).build();
		
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetProductById(@PathParam(value = "id") int id) {
		Product product = productService.findById(id);
		if(product!=null)
			return Response.status(Status.OK).entity(product).build();
		return Response.status(404).build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteProduct(@PathParam(value = "id") int id) {
		
		Product product = productService.findById(id);
		
		if (productService.delete(product))
			return Response.status(Status.OK).build();
		return Response.status(404).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response UpdateProduct(Product product) {
		Product exProduct=productService.findById(product.getId());
		//Change Tests:
		if(product.getProductName()!=null) {exProduct.setProductName(product.getProductName());}
		if(product.getType()!=null) {exProduct.setType(product.getType());}
		if(product.getDescription()!=null) {exProduct.setDescription(product.getDescription());}
		if(product.getProvider()!=null) {exProduct.setProvider(product.getProvider());}
		if(product.getUnitPrice()!=0) {exProduct.setUnitPrice(product.getUnitPrice());}
		if(product.getStock()!=null) {exProduct.setStock(product.getStock());}

		if(productService.update(exProduct))
			return Response.status(Status.OK).build();
		return Response.status(Status.NOT_FOUND).build();
	}
}
