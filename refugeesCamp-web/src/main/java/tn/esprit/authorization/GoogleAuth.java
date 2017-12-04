package tn.esprit.authorization;

import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.google.common.util.concurrent.Service;

import tn.esprit.entities.User;
import tn.esprit.entities.Volunteer;
import tn.esprit.services.UserService;

public class GoogleAuth implements Iauth {
	
	
	private final String CLIENT_SECRET_FILE="client_secret_735769301666-mu7lr3ooks90i60rf5q86pincnrt3547.apps.googleusercontent.com.json";
	
	UserService us;
	
	
	public GoogleAuth(UserService us){
		this.us=us;
	}

	@Override
	public User authentify(String token) {
		String[] strs=token.split(" ");
		User u=null;

		try{
		
			GoogleTokenResponse tokenResponse =
			          new GoogleAuthorizationCodeTokenRequest(
			              new NetHttpTransport(),
			              JacksonFactory.getDefaultInstance(),
			              "https://www.googleapis.com/oauth2/v4/token",
			              "735769301666-mu7lr3ooks90i60rf5q86pincnrt3547.apps.googleusercontent.com",
			              "p85Hvg9VMm_XIBRM9h3Gf_Yn",
			              strs[1],
			              "http://localhost:4200")  // Specify the same redirect URI that you use with your web
			                             // app. If you don't have a web version of your app, you can
			                             // specify an empty string.
			          .execute();
			String accessToken = tokenResponse.getAccessToken();
			GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
			 Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential).setApplicationName(
			          "Oauth2").build();
			 Userinfoplus userinfo = oauth2.userinfo().get().execute();
			 userinfo.toPrettyString();
			 u=us.googleLogin(userinfo.getId());
			 if(u==null)
			 {
				 u=new Volunteer();
				 u.setEmail(userinfo.getEmail());
				 u.setFirstName(userinfo.getFamilyName());
				 u.setLastName(userinfo.getGivenName());
				 u.setGoogleId(userinfo.getId());
				 us.create(u);
			 }
			 u=us.googleLogin(userinfo.getId());
			}
		catch(Exception e){
			e.printStackTrace();
		}

		return u;
	}

}
