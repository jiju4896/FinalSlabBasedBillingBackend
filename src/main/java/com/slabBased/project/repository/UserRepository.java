package com.slabBased.project.repository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.slabBased.project.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{

	boolean existsByUserName(String userName);


    User findAllById(Long userId);

	@Override
	void deleteById(Long Id);

	User findAllByUserName(String userName);


}
