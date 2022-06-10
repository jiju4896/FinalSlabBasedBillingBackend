package com.slabBased.project.services;

import com.slabBased.project.Dto.UserLoginResponseDto;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLoginResponseDtoService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    Long userId;
    private UserLoginResponseDto entityToDtoConversion(User user){

   return modelMapper.map(user, UserLoginResponseDto.class);
    }

    public List<UserLoginResponseDto> getUserLoginResponseDetails(){
        return userRepository.findAll().stream().map(this::entityToDtoConversion).collect(Collectors.toList());


    }
    public Long getUserIdForResponse(String userName){

        List<UserLoginResponseDto> idList=getUserLoginResponseDetails();

        for (UserLoginResponseDto userLoginResponseDto : idList) {
            if (userLoginResponseDto.getUserName().equals(userName)) {
                userId = userLoginResponseDto.getId();
            }
        }

        return userId;
    }
    public UserLoginResponseDto getResponseObject(String logCheck,Long userResponseId,String userName){
        UserLoginResponseDto userLoginResponse = new UserLoginResponseDto();
        userLoginResponse.setUserName(userName);
        userLoginResponse.setLoginResponse(logCheck);
        userLoginResponse.setId(userResponseId);
        return userLoginResponse;

    }


}
