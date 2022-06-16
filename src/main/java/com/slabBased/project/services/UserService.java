package com.slabBased.project.services;

import com.slabBased.project.entity.Role;

public interface UserService {
     Boolean userNameCheck(String userName);
    String addRole(Long userId, Role roleRequest);
}
