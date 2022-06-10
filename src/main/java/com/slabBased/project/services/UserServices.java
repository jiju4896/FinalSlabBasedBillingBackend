package com.slabBased.project.services;

import com.slabBased.project.Dto.UserLoginRequestDto;
import com.slabBased.project.Dto.UserLoginResponseDto;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    @Autowired
    UserRepository uRepo;
    @Autowired
    UserLoginRequestDtoServices userLoginDtoServices;
    @Autowired
    UserLoginResponseDtoService userLoginResponseService;
    String logCheck;
    Long userResponseId;
    Boolean userFound;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String addUserAccount(User user) {
        if (uRepo.existsByUserName(user.getUserName())) {
            throw new RuntimeException("Username already exists");
        }
        User usr = new User();
        usr.setId(user.getId());
        usr.setUserName(user.getUserName());
        usr.setEmail(user.getEmail());
        usr.setFirstName(user.getFirstName());
        usr.setLastName(user.getLastName());
        usr.setPassword(passwordEncoder.encode(user.getPassword()));

        uRepo.save(usr);

        return "USER REGISTERED !";

    }

    public UserLoginResponseDto userLoginCheck(UserLoginRequestDto userRequest) {


        List<UserLoginRequestDto> userList = userLoginDtoServices.getUserLoginDetails();
        for (UserLoginRequestDto usrLoginRequestDto : userList) {
            if (usrLoginRequestDto.getUserName().equals(userRequest.getUserName())) {

                if (!(passwordEncoder.matches(userRequest.getPassword(), usrLoginRequestDto.getPassword()))) {
                    logCheck = "Invalid Password";
                    userResponseId = null;
                    //throw new RuntimeException("Invalid Password");


                } else {
                    logCheck = "Access Granted!";
                    userResponseId = userLoginResponseService.getUserIdForResponse(userRequest.getUserName());
                    break;
                }

            } else {

                logCheck = "Invalid Username";
                userResponseId = null;
                //throw new RuntimeException("Invalid Username ");

            }

        }

        return userLoginResponseService.getResponseObject(logCheck, userResponseId, (userRequest.getUserName()));
    }


    public Boolean userNameCheck(String userName) {

        List<UserLoginRequestDto> userList = userLoginDtoServices.getUserLoginDetails();
        for (UserLoginRequestDto userLoginRequestDto : userList) {
            userFound = userLoginRequestDto.getUserName().equals(userName);

        }
        return userFound;
    }


}