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
class UserLoginRequestDtoServiceImplTest {
@Mock
    UserRepository userRepository;
private UserLoginRequestDtoServiceImpl userLoginRequestDtoService;

    @BeforeEach
    void setUp() {
        this.userLoginRequestDtoService=new UserLoginRequestDtoServiceImpl(this.userRepository);
    }

    @Test
    void getUserLoginDetailsTest() {
        userLoginRequestDtoService.getUserLoginDetails();
        verify(userRepository,times(1)).findAll();
    }
}