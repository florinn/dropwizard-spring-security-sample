package com.github.florinn.samples.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.github.florinn.samples.security.Principal;
import com.yammer.dropwizard.auth.Auth;

@Path("/hello")
public class HelloWorldResource {
	@GET
	public Response sayHello(@Auth Principal principal, @Context UriInfo uriInfo) {
		return Response.ok("Hello " + principal.getUsername()).build();
	}
}
