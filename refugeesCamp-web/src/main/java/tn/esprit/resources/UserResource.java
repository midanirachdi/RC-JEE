package tn.esprit.resources;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.net.URI;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.esprit.authorization.AllowTo;
import tn.esprit.entities.Admin;
import tn.esprit.entities.CampChef;
import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.JobOffer;
import tn.esprit.entities.User;
import tn.esprit.entities.Volunteer;

import tn.esprit.services.MailSenderService;

import tn.esprit.services.JobOfferImpl;

import tn.esprit.services.UserService;

@Path("users")
@RequestScoped
public class UserResource {
	
	@Inject 
	private UserService us;

	
	
	 @Context
	 UriInfo uri;
	
	
	@Inject 
	private MailSenderService ms;

	@Inject
	JobOfferImpl joService;

	
	
	public void whenWriteStringUsingBufferedWritter_thenCorrect(String msg) 
			  throws IOException {
			    String str = msg;
			    BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\azgar\\Documents\\tmp\\log.txt"));
			    writer.write(str);
			     
			    writer.close();
			}
	
	private final String KEY_B64 = Base64.getEncoder().encodeToString("secret".getBytes());

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doRegister(String req,@HeaderParam("Authorization") String auth)
	{
		try {
			whenWriteStringUsingBufferedWritter_thenCorrect(req);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(req);
		
		User user=null;
		User emailUserExisting=null;
		try {
			user = new ObjectMapper().readValue(req, User.class);
	
			if(auth!=null || auth=="")
			{ 
		
			String token=auth.split(" ")[1];
		    String role="";
			Jws<Claims> jws = null;
			jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(token);
			role=jws.getBody().get("role").toString();
			if(((user instanceof CampChef)||(user instanceof DistrictChef)||(user instanceof Admin))&& !role.equals("Admin"))
			{return  Response.status(Response.Status.UNAUTHORIZED).build();}}
			else if (!(user instanceof Volunteer))
			return Response.status(Response.Status.UNAUTHORIZED).build();
			 	
			   user.setDisable(true);
				emailUserExisting=us.findByUserName(user.getEmail());
				if(emailUserExisting!=null)
				{ return Response.status(Status.CONFLICT).build();}
			   us.registerUser(user);
			   Date exp = new Date(System.currentTimeMillis() + 300000);
			   String jwtString = Jwts.builder()
					   .claim("email",user.getEmail())
					   .claim("role","System")
					   .setExpiration(exp)
					   .signWith(SignatureAlgorithm.HS512, KEY_B64).compact();
			   URI baseUri = uri.getBaseUri();
			   URL baseUrl= baseUri.toURL();
			   URL composeUrl=new URL(baseUrl.getProtocol(), baseUrl.getHost(), baseUrl.getPort(), baseUrl.getFile() + "home/confirm?taccess="+jwtString, null);
			   String subject="Refugees camp email confirmation";
			   String content="Please activate your account by clicking the link below\n"+composeUrl;
			   ms.send(user.getEmail(), subject, content);
			   
			
			
		} catch (IOException e) {
			System.out.println(req);
			e.printStackTrace();
			return Response.status(Response.Status.FORBIDDEN).build();
		} catch (Exception e) {
			return Response.status(Status.EXPECTATION_FAILED).build();
		}
		
		return Response.status(Response.Status.CREATED).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"Admin"})
	public Response doList()
	{
		System.out.println("method");
		return Response.status(Response.Status.ACCEPTED).entity(us.findAll()).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@AllowTo(roles={"Admin"})
	public Response doFindById(@PathParam("id") String id) 
	{
		return Response.status(Response.Status.FOUND).entity(us.find(Integer.parseInt(id))).build();
	}
	
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"Admin","CampChef","DistrictChef","Volunteer"})
	public Response doUpdate(String req,@HeaderParam("Authorization") String auth)
	{
		
		int id;
		User user=null;
		try {
			
			String token=auth.split(" ")[1];
			Jws<Claims> jws = null;
			jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(token);
			id=Integer.parseInt(jws.getBody().get("id").toString());
			user = new ObjectMapper().readValue(req, User.class);
			if(user.getId()!=id)
			 return  Response.status(Response.Status.UNAUTHORIZED).build();
			
			us.updateUserNoPassword(user);
			
		} catch (IOException e) {
			return Response.status(Response.Status.NOT_MODIFIED).build();
		}
		
		return Response.status(Response.Status.ACCEPTED).build();
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@AllowTo(roles={"Admin"})
	public Response doDelete(@PathParam("id") String id) 
	{	
		User usToDelete=us.find(Integer.parseInt(id));
		us.remove(usToDelete);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/me")
	@AllowTo(roles={"Admin","CampChef","DistrictChef","Volunteer"})
	public Response doGetMe(@HeaderParam("Authorization") String auth){
		
		int id;
		User user=null;
		try {
			
			String token=auth.split(" ")[1];
			Jws<Claims> jws = null;
			jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(token);
			id=Integer.parseInt(jws.getBody().get("id").toString());
			user=us.find(id);
			user.setPassword("");
			
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_MODIFIED).build();
		}
		
		return   Response.status(Response.Status.ACCEPTED).entity(user).build();
	}
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/search")
	@AllowTo(roles={"Admin"})
	public Response advancedSearch(
			@QueryParam("id") String id,
			@QueryParam("fName") String fName,
			@QueryParam("lName") String lName,
			@QueryParam("email") String email)
	{

		
		return  Response.status(Response.Status.ACCEPTED).entity(us.searchParams(id, lName, fName, email)).build();
	}
	
	
	
	@GET
	@Path("/paginator")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@AllowTo(roles={"Admin"})
	public Response paginatorQuery(
			@QueryParam("page") int page,
			@QueryParam("rowNumber") int rowNumber
		)
	{
		if(page <=0 || rowNumber <=0)
			return Response.status(Status.EXPECTATION_FAILED).build();
		
		return  Response.status(Response.Status.ACCEPTED).header("nbr", us.count()).entity(us.findRange(page, rowNumber)).build();
	}
	
	
	
	@GET
	@Path("/{dc_id}/joboffers")
	@Produces(MediaType.APPLICATION_JSON)
	//@AllowTo(roles={"DistrictChef"})
	public Response GetJobOffersByDistrictChief(@PathParam(value = "dc_id") int dc_id) {
		
		List<JobOffer> jolist = new ArrayList<JobOffer>();
		jolist = joService.findByDistrictChief(dc_id);

		if (!jolist.isEmpty())
			return Response.status(Status.ACCEPTED).entity(jolist).build();
		return Response.status(Status.NOT_FOUND).build();
	}
	
	
	
	
}
