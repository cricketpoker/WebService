package com.cricpoker.data.access;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.cricpoker.data.objects.User;

public class UserDao extends CommonDao<User> {

	private final int DEFAULT_TOKENS = 2000;

	public UserDao() {
		super(User.class);
	}

	public User createOrGet(DateTime lastLoggedInTime, String displayName, int favTeamId) {
		
		List<User> users = listUserBySingleCriteria("displayName", displayName);
		User user;
		
		if(users.isEmpty()) {
			user = new User();
			user.setDisplayName(displayName);
			user.setFavTeamId(favTeamId);
			user.setLastLoggedInTime(lastLoggedInTime);
			user.setTokensLeft(DEFAULT_TOKENS);
			insert(user);
		} else {
			user = users.get(0);
		}
		return user;
	}

	public List<User> listUserBySingleCriteria(String columnName,
			String columnValue) {

		List<User> users = queryByCriteria(columnName,
				columnValue);
		return users;
	}

	public List<User> listAllUsers() {

		User user = createOrGet(DateTime.now(), "soham", 1);
		List<User> users = new ArrayList<User>() {
		};
		
		users.add(user);
		
		return users;
	}

	/*
	 * public UserDao() {
	 * 
	 * User todo = new User("1", "This is a web service");
	 * todo.setDescription("What are you looking for???");
	 * contentProvider.put("1", todo); todo = new User("2", "Yay!");
	 * todo.setDescription("Read above lines again and again");
	 * contentProvider.put("2", todo);
	 * 
	 * }
	 */
}
