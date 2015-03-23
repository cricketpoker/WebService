package com.cricpoker.data.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.joda.time.DateTime;

import com.cricpoker.data.access.UserDao;
import com.cricpoker.data.objects.User;

// Will map the resource to the URL todos
@Path("/users")
public class UsersResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of todos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<User> getTodosBrowser() {
		List<User> users = new ArrayList<User>();
		users = new UserDao().listAllUsers();
		return users;
	}

	// Return the list of todos for applications
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<User> getTodos() {
		User user = createUser(DateTime.now(), "soham", 200, 1);
		List<User> users = new ArrayList<User>() {
		};
		
		users.add(user);
		
		return users;
	}

	public User createUser(DateTime lastLoggedInTime, String displayName,
			int tokensLeft, int favTeamId) {

		User user = new User();
		user.setDisplayName(displayName);
		user.setFavTeamId(favTeamId);
		user.setLastLoggedInTime(lastLoggedInTime);
		user.setTokensLeft(tokensLeft);
		user = new UserDao().insert(user);
		return user;
	}
	
	// returns the number of todos
	// Use http://localhost:8080/de.vogella.jersey.todo/rest/todos/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = new UserDao().listAllUsers().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newTodo(@FormParam("id") String id,
			@FormParam("summary") String summary,
			@FormParam("description") String description,
			@Context HttpServletResponse servletResponse) throws IOException {
		if (description != null) {
		}

		servletResponse.sendRedirect("../create_todo.html");
	}

	// Defines that the next path parameter after todos is
	// treated as a parameter and passed to the TodoResources
	// Allows to type http://localhost:8080/de.vogella.jersey.todo/rest/todos/1
	// 1 will be treaded as parameter todo and passed to TodoResource
	@Path("{todo}")
	public UserResource getTodo(@PathParam("todo") String id) {
		return new UserResource(uriInfo, request, id);
	}

}