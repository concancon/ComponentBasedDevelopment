package de.htw.ai.kbe.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htw.ai.kbe.bean.User;
import de.htw.ai.kbe.storage.IAuthDatabase;

@Path("/auth")
public class AuthService {

	private IAuthDatabase authDatabase;

	@Inject
	public AuthService(IAuthDatabase authDb) {
		this.authDatabase = authDb;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAuthToken(@QueryParam("userId") String userId) {
		String token;
		if (authDatabase.getUserById(userId) == null) {
			System.out.println("went in the 403 we wrote in getAuthToken");
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		User user = authDatabase.getUserById(userId);

		token = user.getToken().getTokenStr();

		return Response.ok(token).build();

	}

}
