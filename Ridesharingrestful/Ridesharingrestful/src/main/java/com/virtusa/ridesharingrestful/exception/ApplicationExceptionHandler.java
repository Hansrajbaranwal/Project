package com.virtusa.ridesharingrestful.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException userNotFound){
		logger.info("Global Exception handled");
		return new ResponseEntity<>(userNotFound.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<String> handleDataNotFoundException(DataNotFoundException dataNotFound){
		logger.info("Global Data Exception handled");
		return new ResponseEntity<>(dataNotFound.getMessage(),HttpStatus.NOT_FOUND);
	}
}
