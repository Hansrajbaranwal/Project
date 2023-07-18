package com.virtusa.ridesharingrestful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserDataExistsException extends Exception{


    public UserDataExistsException(String error)
    {
         super(error);
    }

    public UserDataExistsException(){}


}
