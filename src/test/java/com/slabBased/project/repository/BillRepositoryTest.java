package com.slabBased.project.repository;

import com.slabBased.project.entity.Bill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class BillRepositoryTest {
@Autowired
private BillRepository billRepository;


    @Test

    void findByIdTest() {


        Bill billWhenBillIdExists=billRepository.findById(63);
        assertNotEquals(null,billWhenBillIdExists);

        Bill billWhenBillIdDoesNotExists=billRepository.findById(60);
        assertNull(billWhenBillIdDoesNotExists);

    }

    @Test
    void findAllTest() {
      List<Bill> billWhenStorageTableIsNotEmpty=billRepository.findAll();
      assertFalse(billWhenStorageTableIsNotEmpty.isEmpty());
    }

    @Test
    void findAllByUserIdTest() {
        List<Bill> allBillWhenUserIdIsPresent=billRepository.findAllByUserId(61L);
        assertFalse(allBillWhenUserIdIsPresent.isEmpty());

        List<Bill> emptyBillWhenUserIdIsAbsent=billRepository.findAllByUserId(55L);
        assertTrue(emptyBillWhenUserIdIsAbsent.isEmpty());
    }

    @Test
    void getLastBillDetails() {
        Bill getLastBillDetailsWhenBillExistsForSpecificUser=billRepository.getLastBillDetails(61L);
        assertNotNull(getLastBillDetailsWhenBillExistsForSpecificUser);
        Bill getLastBillDetailsWhenBillDoesNotExistForTheUser=billRepository.getLastBillDetails(55L);
        assertNull(getLastBillDetailsWhenBillDoesNotExistForTheUser);
    }
}