package com.slabBased.project.repository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.slabBased.project.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{
	@Query(value = "SELECT * FROM tbl_user WHERE  user_name = :userName   ", nativeQuery = true)
	String getByPassword(@Param("userName") String userName);

	boolean existsByUserName(String userName);


    User findAllById(Long userId);


}
