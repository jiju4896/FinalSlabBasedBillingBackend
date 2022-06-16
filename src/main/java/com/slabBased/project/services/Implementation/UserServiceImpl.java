package com.slabBased.project.services.Implementation;

import com.slabBased.project.Dto.UserLoginRequestDto;
import com.slabBased.project.entity.Role;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;
import com.slabBased.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository uRepo;
    @Autowired
    UserLoginRequestDtoServiceImpl userLoginDtoServices;
    @Autowired
    UserLoginResponseDtoServiceImpl userLoginResponseService;
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
        Role role=new Role();
        role.setRoleName("USER");
        role.setRoleDescription("This user has only user rights!!");
        usr.getRoles().add(role);

        uRepo.save(usr);

        return "USER REGISTERED !";

    }



/*
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
    }*/


    public Boolean userNameCheck(String userName) {

        List<UserLoginRequestDto> userList = userLoginDtoServices.getUserLoginDetails();
        for (UserLoginRequestDto userLoginRequestDto : userList) {
            userFound = userLoginRequestDto.getUserName().equals(userName);

        }
        return userFound;
    }


    public String addRole(Long userId, Role roleRequest) {
        try {
            Role role=new Role();
            role.setRoleName(roleRequest.getRoleName());
            role.setRoleDescription(roleRequest.getRoleDescription());
            User user=uRepo.findAllById(userId);
            user.getRoles().add(role);
            uRepo.save(user);

        } catch (Exception e) {
            System.out.println("Unable to SAVE");

        }
        return "New Role Added";
    }
}