package com.slabBased.project.services.Implementation;

import com.slabBased.project.Dto.UserLoginRequestDto;
import com.slabBased.project.Dto.UserLoginResponseDto;
import com.slabBased.project.entity.Role;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;
import com.slabBased.project.services.UserService;
import com.slabBased.project.configuration.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;


@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException{

        User user = uRepo.findAllByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),  getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
        return authorities;



       }

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


    public UserLoginResponseDto userLoginCheck(UserLoginRequestDto userRequest) {
        String outputResponse = " ";
        String accessToken = null;
        userResponseId = null;
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        UserLoginResponseDto finalUserLoginResponseDto = new UserLoginResponseDto();

        List<UserLoginRequestDto> userList = userLoginDtoServices.getUserLoginDetails();
        for (UserLoginRequestDto usrLoginRequestDto : userList) {
            if (usrLoginRequestDto.getUserName().equals(userRequest.getUserName())) {

                if (!(passwordEncoder.matches(userRequest.getPassword(), usrLoginRequestDto.getPassword()))) {
                    outputResponse = "Invalid Password";

                    //throw new RuntimeException("Invalid Password");


                } else {

                    userResponseId = userLoginResponseService.getUserIdForResponse(userRequest.getUserName());

                    UserLoginResponseDto userLoginResponseDto = userLoginResponseService.getResponseObject(outputResponse, userResponseId, (userRequest.getUserName()));
                    Map<String, Object> claims = new HashMap<>();
                    claims.put("logResponse", userLoginResponseDto);

                    String userName = userRequest.getUserName();

                    outputResponse = "Access Granted!";
                    accessToken = jwtTokenProvider.doGenerateToken(claims, userName);

                    break;
                }

            } else {

                outputResponse = "Invalid Username ";

                // throw new RuntimeException("Invalid Username ");

            }

        }
        finalUserLoginResponseDto.setLoginResponse(outputResponse);
        finalUserLoginResponseDto.setUserName(userRequest.getUserName());
        finalUserLoginResponseDto.setUserId(userResponseId);
        finalUserLoginResponseDto.setAccessToken(accessToken);


        return finalUserLoginResponseDto;
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