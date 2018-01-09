
package tn.esprit.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import tn.esprit.authorization.AllowTo;
import tn.esprit.authorization.AuthFactory;
import tn.esprit.authorization.CredentialsAuth;
import tn.esprit.authorization.Iauth;
import tn.esprit.entities.User;
import tn.esprit.entities.Volunteer;
import tn.esprit.services.MailSenderService;
import tn.esprit.services.UserService;

@Path("/home")
@RequestScoped
public class LoginResourece {
	
	private final String KEY_B64=Base64.getEncoder().encodeToString("secret".getBytes());
	
	@Inject 
	private UserService us;
	
	
	 @Context
	 UriInfo uri;
	
	
	@Inject 
	private MailSenderService ms;
	
	@GET
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logUser(@HeaderParam("Authorization") String authStr)
	{
		 if(authStr.startsWith("Bearer"))
		 {
				Jws<Claims> jws = null;
				int id=-1; 
					try {
						jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(authStr.split(" ")[1]);

					    id = Integer.parseInt(jws.getBody().get("id").toString());
						return Response.status(200)
								.header(HttpHeaders.AUTHORIZATION,authStr).header(HttpHeaders.CONTENT_TYPE, "application/json")
								.build();
					}
					catch(Exception e){
						return Response.status(Status.UNSUPPORTED_MEDIA_TYPE).build();
					}
		
			
		 }
		 String[] strs=authStr.split(" ");
	    Iauth auth= AuthFactory.getInstance(us,strs[0]);
	    System.out.println(auth.getClass());
	    User u= auth.authentify(authStr);
	    if(u==null)
	    {
	    	   return Response.status(Status.FORBIDDEN).entity("Error")
	   				.build();
	    }
	  
	    

	   String jwtString = Jwts.builder()
			   .claim("id",u.getId())
			   .claim("role", u.getClass().getSimpleName())
			   .signWith(SignatureAlgorithm.HS512, KEY_B64).compact();
	   
	  
	   
	   return Response.status(200)
				.header(HttpHeaders.AUTHORIZATION,"Bearer "+jwtString).header(HttpHeaders.CONTENT_TYPE, "application/json")
				.build();

	}
	
	
	@GET
	@Path("/reset")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetMail(@QueryParam("email") String email)
	{	
		User u=null;
		u=us.findByUserName(email);
		if(u==null && !u.isDisable())
			return Response.status(Status.NOT_FOUND).build();
		
		try {
			   Date exp = new Date(System.currentTimeMillis() + 300000);
			   String jwtString = Jwts.builder()
					   .claim("id",u.getId())
					   .claim("role","Reset")
					   .setExpiration(exp)
					   .signWith(SignatureAlgorithm.HS512, KEY_B64).compact();
			   URI baseUri = uri.getBaseUri();
			   URL baseUrl= baseUri.toURL();
			   URL composeUrl=new URL(baseUrl.getProtocol(), baseUrl.getHost(), baseUrl.getPort(), baseUrl.getFile() + "home/credentials?taccess="+jwtString, null);
			   String subject="reset mail";
			   String content="To reset your password please click the link below \n"+composeUrl+"\n this link is valid only for 5 mn";
			   ms.send(u.getEmail(),subject,content);
			   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(200).build();
	}
	
	
	@GET
	@Path("/credentials")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePasswd(@QueryParam("taccess") String taccess){
		Jws<Claims> jws = null;
	   String newPass;
		try {
			jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(taccess);

			int id = Integer.parseInt(jws.getBody().get("id").toString());
			 newPass=us.generatePassword(id);
			 User u=us.find(id);
			   String subject="Refugees camp new password";
			   String content="You'r new password is : "+newPass;
			   ms.send(u.getEmail(),subject,content);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return Response.status(200).build();
	}
	
	
	@GET
	@Path("/confirm")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public Response ValidateUserEmail(@QueryParam("taccess") String taccess){
		Jws<Claims> jws = null;
		
		try {
			jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(taccess);

			 String email =jws.getBody().get("email").toString();
	
			 User u=us.findByUserName(email);
			 u.setDisable(false);
			 us.updateUserNoPassword(u);
		} catch (Exception e) {
		return Response.status(Status.UNAUTHORIZED).build();
		}
		return Response.status(Status.ACCEPTED).build();
	}
	
	
	@GET
	@Path("/fauth")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public Response doFacebookLogin(@QueryParam("facebookToken") String taccess){
		User user=null;
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(""
					+ "https://graph.facebook.com/v2.11/me?fields=id%2Cname%2Cfirst_name%2Clast_name%2Cbirthday%2Cemail&access_token="+taccess);
			CloseableHttpResponse response = httpclient.execute(httpget);
			String body = EntityUtils.toString(response.getEntity());
			response.close();
			
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map = mapper.readValue(body, new TypeReference<Map<String, String>>(){});
			
			user=us.facebookLogin(map.get("id").toString());
			if(user==null)
			{
				user=new Volunteer();
				user.setFirstName(map.get("first_name").toString());
				user.setLastName(map.get("last_name").toString());
				user.setEmail(map.get("email").toString());
				user.setFacebookId(map.get("id").toString());
				us.create(user);
				user=us.facebookLogin(map.get("id").toString());
			}

			return Response.status(Status.OK).entity(user).build();
		} catch (ClientProtocolException e) {

			  return Response.status(Status.FORBIDDEN).entity("Error")
		   				.build();
		} catch (IOException e) {

			  return Response.status(Status.FORBIDDEN).entity("Error")
		   				.build();
		}
	}

}
