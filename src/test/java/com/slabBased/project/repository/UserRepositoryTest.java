package com.slabBased.project.repository;

import com.slabBased.project.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void existsByUserName() {
      boolean shouldBeTrueWhenUserNameExistsInTheDB=userRepository.existsByUserName("Sajesh23");
      assertTrue(shouldBeTrueWhenUserNameExistsInTheDB);
      boolean shouldBeFalseWhenUserNameDoesNotExistInTheDb=userRepository.existsByUserName("nintriva");
      assertFalse(shouldBeFalseWhenUserNameDoesNotExistInTheDb);

    }

    @Test
    void findAllById() {
        User userDetailsWhenUserExistWithTheId=userRepository.findAllById(61L);
        assertNotNull(userDetailsWhenUserExistWithTheId);

        User userDetailsShouldBeNullWhenUserWithUserIdDoesNotExist=userRepository.findAllById(55L);
        assertNull(userDetailsShouldBeNullWhenUserWithUserIdDoesNotExist);
    }

    @Test
    void deleteByIdTest() {
        User userSavedForTestBeforeDelete=new User(2L,"Jiju","V","test@gmail.com","2333","jiju123");
        userRepository.save(userSavedForTestBeforeDelete);
        User toGetTheIdOfTheLastSavedUserMentionedAbove=userRepository.findAllByUserName("jiju123");
        assertNotNull(toGetTheIdOfTheLastSavedUserMentionedAbove);
       userRepository.deleteById(toGetTheIdOfTheLastSavedUserMentionedAbove.getId());
        User userDetailsShouldBeNullWhenUserWithUserIdDoesNotExist=userRepository.findAllById(toGetTheIdOfTheLastSavedUserMentionedAbove.getId());
        assertNull(userDetailsShouldBeNullWhenUserWithUserIdDoesNotExist);
    }

    @Test
    void findAllByUserName() {
        User userDetailsWhenUserExistWithTheUserName=userRepository.findAllByUserName("Sajesh23");
        assertNotNull(userDetailsWhenUserExistWithTheUserName);

        User userDetailsShouldBeNullWhenUserWithUserNameDoesNotExist=userRepository.findAllByUserName("nintriva");
        assertNull(userDetailsShouldBeNullWhenUserWithUserNameDoesNotExist);
    }
}