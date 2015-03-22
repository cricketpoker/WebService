package com.cricpoker.data.access;

import java.util.HashMap;
import java.util.Map;

import com.cricpoker.data.objects.User;

public enum UserDao {
	instance;

	private Map<String, User> contentProvider = new HashMap<String, User>();

	private UserDao() {

		User todo = new User("1", "This is a web service");
		todo.setDescription("What are you looking for???");
		contentProvider.put("1", todo);
		todo = new User("2", "Yay!");
		todo.setDescription("Read above lines again");
		contentProvider.put("2", todo);

	}

	public Map<String, User> getModel() {
		return contentProvider;
	}

}
