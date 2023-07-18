package com.virtusa.ridesharingrestful.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.ridesharingrestful.entity.User;
import com.virtusa.ridesharingrestful.repository.UserRepository;

@Service
public class UserService{
	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	public User deleteUser(int id) {
		User user =getUserById(id);
		if(user!=null) {
			userRepository.deleteById(id);
		}
		return user;
	}

	public List<User> searchUser(String keyword) {
		return userRepository.search(keyword);
	}

	public User insertUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(User user) {
		
		return userRepository.save(user);
	}

}
