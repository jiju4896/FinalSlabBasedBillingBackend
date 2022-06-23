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
class UserLoginResponseDtoServiceImplTest {
    @Mock
    UserRepository userRepository;

    private  UserLoginResponseDtoServiceImpl userLoginResponseDtoService;

    @BeforeEach
    void setUp(){
        this.userLoginResponseDtoService=new UserLoginResponseDtoServiceImpl(this.userRepository);
    }

    @Test
    void getUserLoginResponseDetailsTest() {
        userLoginResponseDtoService.getUserLoginResponseDetails();
        verify(userRepository,times(1)).findAll();
    }

    @Test
    void getUserIdForResponseTest() {
        userLoginResponseDtoService.getUserIdForResponse("nintriva");
        verify(userRepository,times(1)).findAll();
    }


}