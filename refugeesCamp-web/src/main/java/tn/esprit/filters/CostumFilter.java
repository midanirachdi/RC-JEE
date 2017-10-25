package tn.esprit.filters;

import java.io.IOException;
import java.util.Base64;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;


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
		System.out.println("filter");
		if ((auth != null) && auth.startsWith(AUTH_PREFIX)) {
			String[] authTab = auth.split(" ");
			Jws<Claims> jws = null;
			try {
				jws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(KEY_B64)).parseClaimsJws(authTab[1]);

				String tokenRole = jws.getBody().get("role").toString();
				if (!hasRole(tokenRole))
					ctx.abortWith(Response.status(Response.Status.METHOD_NOT_ALLOWED).build());
			} catch (SignatureException e) {
				ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}

		}

		else
			ctx.abortWith(Response.status(Response.Status.FORBIDDEN).build());
	}

	private boolean hasRole(String tknRole) {
		boolean ok = false;
		int i = 0;
		while (!ok && i < roles.length) {
			ok = tknRole.equals(roles[i]);
			i++;
		}
		return ok;
	}

}
