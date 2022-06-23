package com.slabBased.project.services.Implementation;

import com.slabBased.project.Dto.UserLoginRequestDto;
import com.slabBased.project.entity.Role;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest

class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        this.userService = new UserServiceImpl(this.userRepository);


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
        User user=new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findAllById(1L)).thenReturn(user);
        Role role=new Role();
       String callingAddRoleMethod= userService.addRole(1L,role);
        verify(userRepository).findAllById(1L);

        verify(userRepository).save(user);
        assertEquals("New Role Added",callingAddRoleMethod);
    }

    @Test
    void deleteUserByTheId() {

        String callingDeleteUserById= userService.deleteUserByTheId(1L);
        verify(userRepository,times(1)).deleteById(1L);
        assertEquals("User Deleted",callingDeleteUserById);



    }

    @Test
    void modifyUser() {
        User user=new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findAllById(1L)).thenReturn(user);
        String callingUserServiceModifyUserMethod= userService.modifyUser(1L,user);
        verify(userRepository,times(1)).findAllById(1L);
        verify(userRepository).save(user);
        assertEquals("User Updated",callingUserServiceModifyUserMethod);

    }
}