package tn.esprit.authorization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.entities.User;
import tn.esprit.entities.Volunteer;
import tn.esprit.services.UserService;

public class FacebookAuth implements Iauth  {

	private UserService us;
	private String token;
	 public FacebookAuth(UserService us) {
		this.us=us;
	}
	
	@Override
	public User authentify(String ftoken) {
		
			String strs[]=ftoken.split(" ");
			User user=null;
			try {
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet httpget = new HttpGet(""
						+ "https://graph.facebook.com/v2.11/me?fields=id%2Cname%2Cfirst_name%2Clast_name%2Cbirthday%2Cemail&access_token="+strs[1]);
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

			
			} catch (ClientProtocolException e) {


			} catch (IOException e) {

			}
		

		
		return user;
	}

}
