package com.slabBased.project.services.Implementation;

import com.slabBased.project.Dto.UserLoginRequestDto;
import com.slabBased.project.Dto.UserLoginResponseDto;
import com.slabBased.project.entity.Role;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;
import com.slabBased.project.services.UserService;
import com.slabBased.project.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository uRepo;
    @Autowired
    UserLoginRequestDtoServiceImpl userLoginDtoServices;
    @Autowired
    UserLoginResponseDtoServiceImpl userLoginResponseService;


    @Autowired
    JwtTokenUtil jwtTokenUtil;
    String logCheck;
    Long userResponseId;

    Boolean userFound;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository uRepo, UserLoginRequestDtoServiceImpl userLoginRequestDtoService, UserLoginResponseDtoServiceImpl userLoginResponseService) {
        this.uRepo = uRepo;
        this.userLoginDtoServices = userLoginRequestDtoService;
        this.userLoginResponseService = userLoginResponseService;

    }

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
        Role role = new Role();
        role.setRoleName("USER");
        role.setRoleDescription("This user has only user rights!!");
        usr.getRoles().add(role);

        uRepo.save(usr);

        return "USER REGISTERED !";

    }


    public String userLoginCheck(UserLoginRequestDto userRequest) {
        String outputResponse = " ";


        List<UserLoginRequestDto> userList = userLoginDtoServices.getUserLoginDetails();
        for (UserLoginRequestDto usrLoginRequestDto : userList) {
            if (usrLoginRequestDto.getUserName().equals(userRequest.getUserName())) {

                if (!(passwordEncoder.matches(userRequest.getPassword(), usrLoginRequestDto.getPassword()))) {
                    outputResponse = "Invalid Password";
                    userResponseId = null;
                    //throw new RuntimeException("Invalid Password");


                } else {

                    userResponseId = userLoginResponseService.getUserIdForResponse(userRequest.getUserName());
                    logCheck = "Access Granted!";
                    UserLoginResponseDto userLoginResponseDto = userLoginResponseService.getResponseObject(logCheck, userResponseId, (userRequest.getUserName()));
                    Map<String, Object> claims = new HashMap<>();
                    claims.put("logResponse", userLoginResponseDto);

                    String userName = userRequest.getUserName();

                    outputResponse = jwtTokenUtil.doGenerateToken(claims, userName);

                    break;
                }

            } else {

                outputResponse = "Invalid Username ";
                userResponseId = null;
                // throw new RuntimeException("Invalid Username ");

            }

        }


        return outputResponse;
    }


    public Boolean userNameCheck(String userName) {

        List<UserLoginRequestDto> userList = userLoginDtoServices.getUserLoginDetails();
        for (UserLoginRequestDto userLoginRequestDto : userList) {
            userFound = userLoginRequestDto.getUserName().equals(userName);

        }
        return userFound;
    }

    public String deleteUserRole(Long userId, Long roleId) {
        String result;
        User user = uRepo.findAllById(userId);
        Set<Role> roleSet = user.getRoles();
        boolean role = roleSet.removeIf(role1 -> role1.getId().equals(roleId));
        user.setRoles(roleSet);
        uRepo.save(user);
        if (role) {
            result = "role Deleted";
        } else {
            result = "unable to delete try later";
        }


        return result;
    }

    public String addRole(Long userId, Role roleRequest) {
        try {
            Role role = new Role();
            role.setRoleName(roleRequest.getRoleName());
            role.setRoleDescription(roleRequest.getRoleDescription());
            User user = uRepo.findAllById(userId);
            user.getRoles().add(role);
            uRepo.save(user);

        } catch (Exception e) {
            System.out.println("Unable to SAVE");

        }
        return "New Role Added";
    }

    public String deleteUserByTheId(Long userId) {
        uRepo.deleteById(userId);
        return "User Deleted";
    }

    public String modifyUser(Long userId, User userRequest) {
        User usr = uRepo.findAllById(userId);
        usr.setFirstName(userRequest.getFirstName());
        usr.setLastName(userRequest.getLastName());
        usr.setPassword(userRequest.getPassword());
        usr.setEmail(userRequest.getEmail());
        uRepo.save(usr);
        return "User Updated";
    }
}