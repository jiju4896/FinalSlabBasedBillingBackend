package com.slabBased.project.controller;

import com.slabBased.project.Dto.UserLoginRequestDto;
import com.slabBased.project.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.slabBased.project.entity.User;


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

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public Object loginUser(@RequestBody UserLoginRequestDto user) {


		return userServices.userLoginCheck(user);
	}

	@GetMapping("/username/check")
	public Boolean userNameCheck(@RequestParam String userName) {

		return userServices.userNameCheck(userName);

	}



}
