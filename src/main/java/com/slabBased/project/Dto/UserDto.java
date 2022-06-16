package com.slabBased.project.Dto;

import com.slabBased.project.entity.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;
    private String email;


    private String userName;

    private Set<Role> roles=new HashSet<>();

}
