package com.slabBased.project.services;

import com.slabBased.project.Dto.GetAllUsersDetailsDTO;

import java.util.List;

public interface UserDtoService {
    List<GetAllUsersDetailsDTO> getUserDetails();
}
