package com.virtusa.ridesharingrestful.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtusa.ridesharingrestful.entity.User;
import com.virtusa.ridesharingrestful.exception.UserNotFoundException;
import com.virtusa.ridesharingrestful.service.UserService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/admin/users")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.getUsers();
		if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<User> insertUser(@RequestBody User user) {

		User useradd = userService.insertUser(user);
		logger.debug(" {}." , useradd);
		return new ResponseEntity<>(useradd, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer uid) {
		User user = userService.getUserById(uid);
		if(user !=null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		else
			throw new UserNotFoundException("User " + uid + " not found");
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Integer> deleteUser(@PathVariable("id") Integer uid) {

		User user = userService.deleteUser(uid);
		logger.debug(" {}." , user);
		return ResponseEntity.ok(user.getUserId());
	}

	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer uid, @RequestBody User user) {
		user.setUserId(uid);
		User userupdate = userService.updateUser(user);
		logger.debug(" {}." , userupdate);
		return new ResponseEntity<>(userupdate, HttpStatus.OK);
	}
	
	@GetMapping("searchuser/{keyword}")
	public ResponseEntity<List<User>> searchUser(@PathVariable("keyword") String keyword) {
		List<User> result = userService.searchUser(keyword);
		return new ResponseEntity<>(result, HttpStatus.FOUND);
	}

}
