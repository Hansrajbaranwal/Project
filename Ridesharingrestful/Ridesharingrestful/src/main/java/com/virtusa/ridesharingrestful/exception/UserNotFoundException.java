package com.virtusa.ridesharingrestful.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
		
	}
	public UserNotFoundException(String message) {
		super(message);
	}

}
