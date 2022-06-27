package com.slabBased.project.repository;

import com.slabBased.project.entity.SlabPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlabPeriodRepository extends JpaRepository<SlabPeriod, Long> {

    SlabPeriod findAllById(Long id);
}
