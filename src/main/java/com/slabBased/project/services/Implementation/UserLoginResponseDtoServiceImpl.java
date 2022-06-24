package com.slabBased.project.services.Implementation;

import com.slabBased.project.Dto.UserLoginResponseDto;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;
import com.slabBased.project.services.UserLoginResponseDtoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLoginResponseDtoServiceImpl implements UserLoginResponseDtoService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserLoginResponseDtoServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
                userId = userLoginResponseDto.getUserId();
            }
        }

        return userId;
    }
    public UserLoginResponseDto getResponseObject(String logCheck,Long userResponseId,String userName){
        UserLoginResponseDto userLoginResponse = new UserLoginResponseDto();
        userLoginResponse.setUserName(userName);
        userLoginResponse.setLoginResponse(logCheck);
        userLoginResponse.setUserId(userResponseId);
        return userLoginResponse;

    }


}
