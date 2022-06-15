package com.slabBased.project.repository;

import com.slabBased.project.entity.SlabPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SlabPeriodRepository extends JpaRepository<SlabPeriod,Long> {
    @Query(value = "SELECT * FROM tbl_slab_period  ORDER BY Id DESC LIMIT 1  ", nativeQuery = true)
    SlabPeriod getLastPeriod();
    SlabPeriod findAllById(Long id);
}
