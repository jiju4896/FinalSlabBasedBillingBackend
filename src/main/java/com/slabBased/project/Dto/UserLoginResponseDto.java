package com.slabBased.project.Dto;

import com.slabBased.project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDto {
    private String accessToken;
    private String loginResponse;
    private String userName;
    private Long userId;
    private Set<Role> roleSet=new HashSet<>();


}
