package de.htw.ai.kbe.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htw.ai.kbe.storage.IAuthDatabase;
import de.htw.ai.kbe.user.User;

@Path("/Auth")
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
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		User user = authDatabase.getUserById(userId);

		token = user.getToken().getTokenStr();

		return Response.ok(token).build();

	}

}
