package com.slabBased.project.repository;


import com.slabBased.project.entity.Slabs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlabsRepository extends JpaRepository<Slabs, Long> {
    @Query(value = "SELECT * FROM tbl_slabs WHERE  slab_period_id = :userid  ", nativeQuery = true)
    List<Slabs> getAllSlabsInCurrentPeriod(@Param("userid") Long userid);
}
