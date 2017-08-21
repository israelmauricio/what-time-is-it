package com.aserta.ws.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aserta.business.entities.UserSignUp;
import com.aserta.business.interfaces.IUserSignUpService;
import com.aserta.business.layer.EmailAlreadyRegisteredException;

@RestController
public class UserController {
	
	private IUserSignUpService userSignUpService;
	
	public UserController(IUserSignUpService userSignUpService) {
		this.userSignUpService = userSignUpService;
	}

	@RequestMapping(path = "/ws/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Integer> signUp(@RequestBody UserSignUp userSignUp) throws EmailAlreadyRegisteredException, Exception {
	
		int userId = userSignUpService.execute(userSignUp);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	
	}
	
	@RequestMapping(path = "/ws/isra", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  ResponseEntity<Date> isra() {
	
		GregorianCalendar calendar= new GregorianCalendar(1980,5,27);
		System.out.println(calendar.getTime());
		return new ResponseEntity<Date>(calendar.getTime(),HttpStatus.OK);
		
	
	}
	
	
}