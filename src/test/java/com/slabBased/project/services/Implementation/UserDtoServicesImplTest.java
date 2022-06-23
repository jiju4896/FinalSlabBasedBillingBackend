package com.slabBased.project.services.Implementation;

import com.slabBased.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@SpringBootTest
class UserDtoServicesImplTest {
    @Mock
    UserRepository userRepository;

    private UserDtoServicesImpl userDtoServices;

    @BeforeEach
    void setUp() {
        this.userDtoServices=new UserDtoServicesImpl(this.userRepository);
    }

    @Test
    void getUserDetailsTest() {
        userDtoServices.getUserDetails();
        verify(userRepository,times(1)).findAll();
    }

}