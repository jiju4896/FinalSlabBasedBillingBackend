package com.slabBased.project.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.slabBased.project.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{
	Bill findById(long id);

	@Override
	List<Bill> findAll();

	List<Bill> findAllByUsername(String username);

	@Query(value = "SELECT * FROM tbl_billstorage WHERE  username = :username   ", nativeQuery = true)
	Bill getLastBillDetails(@Param("username") String username);
}