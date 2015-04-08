package com.cricpoker.data.resources;

import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.joda.time.DateTime;

import com.cricpoker.data.access.UserDao;
import com.cricpoker.data.objects.User;

@Path("/users")
public class UserResource {
	
	private UserDao userDao;
	
	public UserResource() {
		userDao = new UserDao();
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User addOrGetUser(@FormParam("displayName") String displayName,
			@FormParam("lastLoggedInTime") Date lastLoggedInTime,
			@FormParam("favTeamId") int favTeamId) {
		
		User user = userDao.createOrGet(new DateTime(), displayName, favTeamId);	
		
		return user;
	}

}
