package com.slabBased.project.services.Implementation;

import com.slabBased.project.Dto.UserLoginRequestDto;
import com.slabBased.project.Dto.UserLoginResponseDto;
import com.slabBased.project.entity.Role;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @Mock
    UserLoginRequestDtoServiceImpl userLoginRequestDtoService;

    @Mock
    UserLoginResponseDtoServiceImpl userLoginResponseDtoService;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        this.userService = new UserServiceImpl(this.userRepository, this.userLoginRequestDtoService,this.userLoginResponseDtoService);


    }

    @Test
    void addUserAccountTest() {
        User user = new User(15L, "Lijesh", "o", "test@123.com", "123", "lijesh12");
        when(userRepository.save(any(User.class))).thenReturn(user);
        userService.addUserAccount(user);
        verify(userRepository, times(1)).existsByUserName(user.getUserName());
        assertEquals("USER REGISTERED !", userService.addUserAccount(user));

    }


    @Test
    void deleteUserRole() {
        User user = new User();
        when(userRepository.findAllById(1L)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals("unable to delete try later", userService.deleteUserRole(1L, 25L));
        verify(userRepository).findAllById(1L);
        verify(userRepository).save(user);

    }

    @Test
    void addRole() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findAllById(1L)).thenReturn(user);
        Role role = new Role();
        String callingAddRoleMethod = userService.addRole(1L, role);
        verify(userRepository).findAllById(1L);

        verify(userRepository).save(user);
        assertEquals("New Role Added", callingAddRoleMethod);
    }

    @Test
    void deleteUserByTheId() {

        String callingDeleteUserById = userService.deleteUserByTheId(1L);
        verify(userRepository, times(1)).deleteById(1L);
        assertEquals("User Deleted", callingDeleteUserById);


    }

    @Test
    void modifyUser() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findAllById(1L)).thenReturn(user);
        String callingUserServiceModifyUserMethod = userService.modifyUser(1L, user);
        verify(userRepository, times(1)).findAllById(1L);
        verify(userRepository).save(user);
        assertEquals("User Updated", callingUserServiceModifyUserMethod);

    }


    @Test
    void userNameCheck() {
        UserLoginRequestDto userLoginRequestDto1 = new UserLoginRequestDto("saji122", "1254");
        UserLoginRequestDto userLoginRequestDto2 = new UserLoginRequestDto("saji12248", "123");
        List<UserLoginRequestDto> userList = new ArrayList<>();
        userList.add(userLoginRequestDto2);
        userList.add(userLoginRequestDto1);
        when(userLoginRequestDtoService.getUserLoginDetails()).thenReturn(userList);
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto("saji122", "1254");
        Boolean ifUserNameIsFoundInDatabase=userService.userNameCheck(userLoginRequestDto.getUserName());

        verify(userLoginRequestDtoService).getUserLoginDetails();
        assertTrue(ifUserNameIsFoundInDatabase);

        Boolean ifUserNameIsNotFoundInDB =userService.userNameCheck("nintriva");
        assertFalse(ifUserNameIsNotFoundInDB);


    }
}