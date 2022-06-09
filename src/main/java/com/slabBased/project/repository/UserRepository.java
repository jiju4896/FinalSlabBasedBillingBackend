package com.slabBased.project.repository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.slabBased.project.entity.User;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{
	@Query(value = "SELECT * FROM tbl_user WHERE  userName = :userName   ", nativeQuery = true)
	User getByPassword(@Param("userName") String userName);
	Boolean existsByPassword(String password);

	boolean existsByUserName(String userName);

	User findByUserName(String userName);
	
}
