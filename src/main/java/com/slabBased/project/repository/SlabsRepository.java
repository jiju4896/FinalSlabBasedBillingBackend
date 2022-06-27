package com.slabBased.project.repository;


import com.slabBased.project.entity.Slabs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlabsRepository extends JpaRepository<Slabs, Long> {

}
