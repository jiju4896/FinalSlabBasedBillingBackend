package com.slabBased.project.services.Implementation;

import com.slabBased.project.Dto.UserLoginRequestDto;
import com.slabBased.project.entity.Role;
import com.slabBased.project.entity.Token;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.TokenRepository;
import com.slabBased.project.repository.UserRepository;
import com.slabBased.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.slabBased.project.utils.ConstantUtil.passwordRegex;


@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository uRepo;
    @Autowired
    UserLoginRequestDtoServiceImpl userLoginDtoServices;

    @Autowired
    TokenRepository tokenRepository;


    Boolean userFound;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private Long tokenId;


    public UserServiceImpl(UserRepository uRepo, UserLoginRequestDtoServiceImpl userLoginRequestDtoService) {
        this.uRepo = uRepo;
        this.userLoginDtoServices = userLoginRequestDtoService;


    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = uRepo.findAllByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
        return authorities;


    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(passwordRegex);

        if (password == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
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
        if (isValidPassword(user.getPassword())) {
            usr.setPassword(passwordEncoder.encode(user.getPassword()));
        } else
            throw new RuntimeException("Invalid Password!! password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit");
        Role role = new Role();
        role.setRoleName("USER");
        role.setRoleDescription("This user has only user rights!!");
        usr.getRoles().add(role);

        uRepo.save(usr);

        return "USER REGISTERED !";

    }


    public Boolean userNameCheck(String userName) {

        List<UserLoginRequestDto> userList = userLoginDtoServices.getUserLoginDetails();

        for (UserLoginRequestDto userLoginRequestDto : userList) {
            userFound = userLoginRequestDto.getUserName().equals(userName);
            if (userFound) {
                break;
            }

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
            role.setRoleName(roleRequest.getRoleName().toUpperCase());
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

        if (userRequest.getFirstName() == (null)) {
            usr.setFirstName(usr.getFirstName());
        } else {
            usr.setFirstName(userRequest.getFirstName());
        }
        if (userRequest.getLastName() == (null)) {
            usr.setLastName(usr.getLastName());
        } else {
            usr.setLastName(userRequest.getLastName());
        }
        if (userRequest.getPassword() == (null)) {
            usr.setPassword(usr.getPassword());
        } else {
            usr.setPassword(userRequest.getPassword());
        }
        if (userRequest.getEmail() == (null)) {
            usr.setEmail(usr.getEmail());
        } else {
            usr.setEmail(userRequest.getEmail());
        }
        if (usr.getUserName() == (null)) {
            usr.setUserName(usr.getUserName());
        } else {
            usr.setUserName(userRequest.getUserName());
        }
        uRepo.save(usr);
        return "User Updated";
    }

    public String deleteMultipleUsers(List<User> user) {
        for (User usr : user) {
            uRepo.deleteById(usr.getId());
        }
        return "All Users Deleted!!";
    }

    public User findAllUserDetailsFromUserName(String userName) {
        return uRepo.findAllByUserName(userName);
    }

    public void setTokenIdFromRequest(Long tokenIdFromRequest) {
        this.tokenId = tokenIdFromRequest;

    }

    public String userLogout() {
        SecurityContextHolder.clearContext();
       Token token= tokenRepository.getById(tokenId);
        System.out.println(token.getTokenUserId());

    List<Token> tokenList=tokenRepository.findAllByTokenUserId(token.getTokenUserId());
        for (Token token1:tokenList){
            tokenRepository.deleteById(token1.getTokenId());
        }

        return "User Logout Success";
    }
}