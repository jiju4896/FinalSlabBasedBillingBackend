package com.slabBased.project.controller;

import com.slabBased.project.Dto.UserDto;
import com.slabBased.project.entity.Role;
import com.slabBased.project.services.Implementation.UserDtoServicesImpl;
import com.slabBased.project.services.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.slabBased.project.entity.User;

import java.util.List;


@RestController
@RequestMapping("/sign")
@CrossOrigin("*")
public class UserController {

	@Autowired
    UserServiceImpl userServices;
	@Autowired
	UserDtoServicesImpl userDtoServices;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Object addUser(@RequestBody User user) {

		return userServices.addUserAccount(user);
	}

	@GetMapping("get-all/users")
	public List<UserDto> getAllUsers(){
		return userDtoServices.getUserDetails();

	}

/*

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public Object loginUser(@RequestBody UserLoginRequestDto user) {


		return userServices.userLoginCheck(user);
	}*/

	@PutMapping("{userId}/role")
	@ResponseStatus(HttpStatus.CREATED)
	public String addRole(@PathVariable(value = "userId") Long userId, @RequestBody Role roleRequest) {


		return userServices.addRole(userId,roleRequest);
	}
	@GetMapping("/username/check")
	public Boolean userNameCheck(@RequestParam String userName) {

		return userServices.userNameCheck(userName);

	}



}
