package com.slabBased.project.repository;

import com.slabBased.project.entity.Slabperiod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SlabperiodRepository extends JpaRepository<Slabperiod,Long> {
    @Query(value = "SELECT * FROM tbl_slabperiod  ORDER BY Id DESC LIMIT 1  ", nativeQuery = true)
    Slabperiod getLastPeriod();
}
