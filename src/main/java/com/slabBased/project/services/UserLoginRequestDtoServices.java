package com.slabBased.project.services;

import com.slabBased.project.Dto.UserLoginRequestDto;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLoginRequestDtoServices {
    @Autowired
    UserRepository uRepo;
    @Autowired
    private ModelMapper modelMapper;

    private UserLoginRequestDto entityToDtoConversion(User user){
        return modelMapper.map(user, UserLoginRequestDto.class);
    }

   public List<UserLoginRequestDto> getUserLoginDetails(){
        return uRepo.findAll().stream().map(this::entityToDtoConversion).collect(Collectors.toList());


   }

}
