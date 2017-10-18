package tn.esprit.filters;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.esprit.activator.RestActivator;
import tn.esprit.authorization.AllowTo;

public class CostumFilter implements ContainerRequestFilter {

	private final String AUTH_PREFIX = "Bearer";
	private final String KEY_B64 = Base64.getEncoder().encodeToString("secret".getBytes());

	private String[] roles;

	public CostumFilter(String[] annRoles) {
		roles = annRoles;
	}

	public CostumFilter() {
	};

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {

		String auth = ctx.getHeaderString("Authorization");
		
		if ((auth!=null)&& auth.startsWith(AUTH_PREFIX)) {
			String[] authTab = auth.split(" ");

			Jws<Claims> jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64))
					.parseClaimsJws(authTab[1]);

			String tokenRole = jws.getBody().get("role").toString();
			if (!hasRole(tokenRole))
				ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}

		else
			ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
	}

	private boolean hasRole(String tknRole) {
		boolean ok = false;
		int i = 0;
		while (!ok && i<roles.length) {
			ok = tknRole.equals(roles[i]);
			i++;
		}
		return ok;
	}

}
