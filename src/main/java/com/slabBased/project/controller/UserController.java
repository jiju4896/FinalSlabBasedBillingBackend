package com.slabBased.project.controller;

import com.slabBased.project.Dto.UserDto;
import com.slabBased.project.Dto.UserLoginRequestDto;
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
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Object loginUser(@RequestBody UserLoginRequestDto user) throws RuntimeException {


        return userServices.userLoginCheck(user);
    }

    /*To get All Users*/
    @GetMapping("get-all/users")
    public List<UserDto> getAllUsers() {
        return userDtoServices.getUserDetails();

    }

    /*For updating User Details*/
    @PutMapping("user/modify/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String modifyUserDetails(@PathVariable(value = "userId") Long userId, @RequestBody User userRequest) throws RuntimeException {
        return userServices.modifyUser(userId, userRequest);
    }

    /*For updating user Role*/
    @PutMapping("{userId}/role")
    @ResponseStatus(HttpStatus.CREATED)
    public String addRole(@PathVariable(value = "userId") Long userId, @RequestBody Role roleRequest) throws RuntimeException {


        return userServices.addRole(userId, roleRequest);
    }


    /*To delete an User Role*/
    @DeleteMapping("/delete/user/{userId}/role/{roleId}")
    public String deleteId(@PathVariable(value = "userId") Long userId, @PathVariable(value = "roleId") Long roleId) throws RuntimeException {
        return userServices.deleteUserRole(userId, roleId);
    }

    /*For Deleting an User*/
    @DeleteMapping("/delete/user/{userId}")
    public String deleteUserById(@PathVariable(value = "userId") Long userId) throws RuntimeException {
        return userServices.deleteUserByTheId(userId);
    }


}
