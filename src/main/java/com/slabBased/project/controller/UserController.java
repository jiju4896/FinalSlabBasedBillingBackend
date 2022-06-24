package com.slabBased.project.controller;

import com.slabBased.project.Dto.UserDto;
import com.slabBased.project.Dto.UserLoginRequestDto;
import com.slabBased.project.Dto.UserLoginResponseDto;
import com.slabBased.project.entity.Role;
import com.slabBased.project.services.Implementation.UserDtoServicesImpl;
import com.slabBased.project.services.Implementation.UserServiceImpl;
import com.slabBased.project.configuration.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.slabBased.project.entity.User;

import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserServiceImpl userServices;
    @Autowired
    UserDtoServicesImpl userDtoServices;


    /* For UserName Availability Check */
    @GetMapping("/username/check")
    public Boolean userNameCheck(@RequestParam String userName) {

        return userServices.userNameCheck(userName);

    }


    /*For Registration Purpose*/
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addUser(@RequestBody User user) throws RuntimeException {

        return userServices.addUserAccount(user);
    }

    /*For Login Purpose*/
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody UserLoginRequestDto loginUser) throws AuthenticationException {
        JwtTokenProvider jwtTokenProvider =new JwtTokenProvider();
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUserName(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new UserLoginResponseDto(token));
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginResponseDto loginUser(@RequestBody UserLoginRequestDto user) throws RuntimeException {


        return userServices.userLoginCheck(user);
    }

    /*To get All Users*/
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get-all/users")
    public List<UserDto> getAllUsers() {
        return userDtoServices.getUserDetails();

    }

    /*For updating User Details*/
    @PreAuthorize("hasRole('ADMIN','USER')")
    @PutMapping("user/modify/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String modifyUserDetails(@PathVariable(value = "userId") Long userId, @RequestBody User userRequest) throws RuntimeException {
        return userServices.modifyUser(userId, userRequest);
    }

    /*For updating user Role*/
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{userId}/role")
    @ResponseStatus(HttpStatus.CREATED)
    public String addRole(@PathVariable(value = "userId") Long userId, @RequestBody Role roleRequest) throws RuntimeException {


        return userServices.addRole(userId, roleRequest);
    }


    /*To delete an User Role*/
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/user/{userId}/role/{roleId}")
    public String deleteId(@PathVariable(value = "userId") Long userId, @PathVariable(value = "roleId") Long roleId) throws RuntimeException {
        return userServices.deleteUserRole(userId, roleId);
    }

    /*For Deleting an User*/
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/user/{userId}")
    public String deleteUserById(@PathVariable(value = "userId") Long userId) throws RuntimeException {
        return userServices.deleteUserByTheId(userId);
    }


}
