package com.slabBased.project.Dto;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String userName;
    private String password;

    public UserLoginRequestDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
