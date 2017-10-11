package tn.esprit.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import utile.JsonUserMapper;


@Path("/public")
public class PublicResoureces {

	@Path("/test")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String doTest(String req)
	{
		
		return JsonUserMapper.Convert(req);
	}
}
