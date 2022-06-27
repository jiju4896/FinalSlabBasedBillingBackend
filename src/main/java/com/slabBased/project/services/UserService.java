package com.slabBased.project.services;

import com.slabBased.project.entity.Role;
import com.slabBased.project.entity.User;

public interface UserService {
    Boolean userNameCheck(String userName);

    String addRole(Long userId, Role roleRequest);

    String deleteUserRole(Long userId, Long roleId);

    String addUserAccount(User user);


    String deleteUserByTheId(Long userId);

    String modifyUser(Long userId, User userRequest);
}
