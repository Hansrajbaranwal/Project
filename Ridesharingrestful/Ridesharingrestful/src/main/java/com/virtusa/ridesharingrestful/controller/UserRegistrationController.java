package com.virtusa.ridesharingrestful.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.ridesharingrestful.entity.Status;
import com.virtusa.ridesharingrestful.entity.User;
import com.virtusa.ridesharingrestful.service.UserService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping()
public class UserRegistrationController {
	
	Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	private UserService userService;
	
	@PostMapping("/users/sign-up")
    public Status registerUser(@Valid @RequestBody User newUser) {
        List<User> users = userService.getUsers();
        logger.debug("New user: {}. " , newUser);
        for (User user : users) {
        	logger.debug("Registered user: {}." , newUser);
            if (user.equals(newUser)) {
            	logger.info("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }
        userService.insertUser(newUser);
        return Status.SUCCESS;
    }
    @PostMapping("/users/sign-in")
    public Status loginUser(@Valid @RequestBody User user) {
        List<User> users = userService.getUsers();
        for (User other : users) {
            if (other.equals(user)) {
                user.setActive(true);
                userService.insertUser(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    @PostMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userService.getUsers();
        for (User other : users) {
            if (other.equals(user)) {
                user.setActive(false);
                userService.insertUser(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
	
}
