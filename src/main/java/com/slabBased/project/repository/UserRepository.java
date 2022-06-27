package com.slabBased.project.repository;

import com.slabBased.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserName(String userName);


    User findAllById(Long userId);

    @Override
    void deleteById(Long Id);

    User findAllByUserName(String userName);


}
