package com.slabBased.project.repository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.slabBased.project.entity.User;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{
	@Query(value = "SELECT * FROM tbl_user WHERE  username = :username   ", nativeQuery = true)
	User getByPassword(@Param("username") String username);
	Boolean existsByPassword(String password);

	boolean existsByUsername(String username);

	User findByUsername(String username);
	
}
