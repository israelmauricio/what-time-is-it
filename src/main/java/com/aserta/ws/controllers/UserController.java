package com.aserta.ws.controllers;

import java.time.LocalDate;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aserta.business.entities.UserSignUp;
import com.aserta.business.layer.EmailAlreadyRegisteredException;

@RestController
public class UserController {

	@RequestMapping(path = "/ws/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void signUp(@RequestBody UserSignUp userSignUp) throws EmailAlreadyRegisteredException, Exception {
		throw new Exception("Not implemented yet");
	}
	
}
