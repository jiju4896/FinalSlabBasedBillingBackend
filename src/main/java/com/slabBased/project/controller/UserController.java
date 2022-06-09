package com.slabBased.project.controller;

import com.slabBased.project.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;

@RestController
@RequestMapping("/sign")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserServices userServices;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Object addUser(@RequestBody User user) {

		return userServices.addUserAccount(user);
	}

	@PostMapping("/regLog")
	@ResponseStatus(HttpStatus.OK)
	public Object loginUser(@RequestBody User user) {


		return userServices.userLoginCheck(user);
	}

	@GetMapping("/userNameCheck")
	public Boolean userNameCheck(@RequestParam String userName) {

		return userServices.userNameCheck(userName);

	}

	@GetMapping("/userMatch")

	public User getMatchByUser(@RequestParam String userName) {

		return userServices.getMatchUserName(userName);
	}

}
