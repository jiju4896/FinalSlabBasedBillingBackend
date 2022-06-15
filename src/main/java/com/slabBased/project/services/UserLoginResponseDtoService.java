package com.slabBased.project.services;

import com.slabBased.project.Dto.UserLoginResponseDto;

import java.util.List;

public interface UserLoginResponseDtoService {
    Long getUserIdForResponse(String userName);
    List<UserLoginResponseDto> getUserLoginResponseDetails();
    UserLoginResponseDto getResponseObject(String logCheck,Long userResponseId,String userName);
}
