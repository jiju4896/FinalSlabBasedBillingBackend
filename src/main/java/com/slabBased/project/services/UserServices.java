package com.slabBased.project.services;

import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    UserRepository uRepo;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String addUserAccount(User user){
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

    public String userLoginCheck(User user){
        String logCheck;
        if (!(uRepo.existsByUserName(user.getUserName()))) {
            logCheck="Invalid Username";

            throw new RuntimeException("Invalid Username ");

        } else {
            User usr ;
            usr = uRepo.getByPassword(user.getUserName());

            if (!(passwordEncoder.matches(user.getPassword(), usr.getPassword()))) {
                logCheck="Invalid Password";
                throw new RuntimeException("Invalid Password");

            }else{
                logCheck ="User Access!";
            }

        }
        return logCheck;
    }


public Boolean userNameCheck(String userName){
        return uRepo.existsByUserName(userName);
}
public User getMatchUserName(String userName){
        return uRepo.findByUserName(userName);
}

}