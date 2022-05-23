package com.slabBased.project.controller;
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
import org.springframework.web.bind.annotation.RestController;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;

@RestController
@RequestMapping("/sign")
@CrossOrigin("*")
public class UserController {
	@Autowired
	UserRepository uRepo;
	
	
	   
	 
	 PasswordEncoder passwordEncoder=
	         new BCryptPasswordEncoder();
	    

	@PostMapping("/register")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		if (uRepo.existsByUsername(user.getUsername())) {
			throw new RuntimeException("Username already exists");
		}
		 User usr = new User();
	        usr.setId(user.getId());
	        usr.setUsername(user.getUsername());
	        usr.setEmail(user.getEmail());
	        usr.setFirstname(user.getFirstname());
	        usr.setLastname(user.getLastname());
	        usr.setPassword(passwordEncoder.encode(user.getPassword()));

	     

	       
		return new ResponseEntity<User>(uRepo.save(usr), HttpStatus.CREATED);
	}

	@PostMapping("/reglog")
	public ResponseEntity<User> loginUser(@RequestBody User user) {
		
		
		if(!(uRepo.existsByUsername(user.getUsername())) ) {

			throw new RuntimeException("Invalid Username ");

		}else {
			User usr= new User();
			usr=uRepo.getByPassword(user.getUsername());
			
			if(!(passwordEncoder.matches(user.getPassword(), usr.getPassword()))) {
				throw new RuntimeException("Invalid Password");
				
				
			}
			
		}
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@GetMapping("/usermatch")

	public ResponseEntity<User> getMatchByUser(@RequestParam String username) {

		return new ResponseEntity<User>(uRepo.findByUsername(username), HttpStatus.OK);
	}

}
