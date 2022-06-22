package com.slabBased.project.Dto;

import com.slabBased.project.entity.Bill;
import com.slabBased.project.entity.Role;
import lombok.Data;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private Set<Role> roles = new HashSet<>();

    private List<Bill> bill = new ArrayList<>();

}
