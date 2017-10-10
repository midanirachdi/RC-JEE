package tn.esprit.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/hello")
public class testResource {
	
	@GET
	public String sayHello(){		
		return "hello world : Refugees Camp";
	}

}
