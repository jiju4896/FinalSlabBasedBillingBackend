package com.slabBased.project.repository;

import com.slabBased.project.entity.SlabPeriod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SlabPeriodRepositoryTest {
    @Autowired
    private SlabPeriodRepository slabPeriodRepository;

    @Test
    void findAllById() {
        SlabPeriod slabPeriodWhenPeriodExistById=slabPeriodRepository.findAllById(57L);
        assertNotNull(slabPeriodWhenPeriodExistById);

        SlabPeriod slabPeriodWhenPeriodDoesNotExistsForTheId=slabPeriodRepository.findAllById(61L);
        assertNull(slabPeriodWhenPeriodDoesNotExistsForTheId);
    }
}