package com.accenture.springtask.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.accenture.springtask.model.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;

	static {
		users.add(new User(1, "Serhat", new Date()));
		users.add(new User(2, "Selim", new Date()));
		users.add(new User(3, "Genc", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public User findUserById(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User deleteUserById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
