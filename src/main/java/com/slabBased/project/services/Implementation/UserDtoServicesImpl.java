package com.slabBased.project.services.Implementation;

import com.slabBased.project.Dto.GetAllUsersDetailsDTO;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;
import com.slabBased.project.services.UserDtoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDtoServicesImpl implements UserDtoService {
    @Autowired
    UserRepository uRepo;
    @Autowired
    private ModelMapper modelMapper;

    public UserDtoServicesImpl(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    private GetAllUsersDetailsDTO entityToDtoConversion(User user) {
        return modelMapper.map(user, GetAllUsersDetailsDTO.class);
    }

    public List<GetAllUsersDetailsDTO> getUserDetails() {
        return uRepo.findAll().stream().map(this::entityToDtoConversion).collect(Collectors.toList());


    }
}
