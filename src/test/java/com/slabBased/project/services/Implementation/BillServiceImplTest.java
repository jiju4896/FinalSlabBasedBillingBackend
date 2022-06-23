package com.slabBased.project.services.Implementation;


import com.slabBased.project.entity.Bill;
import com.slabBased.project.entity.SlabPeriod;
import com.slabBased.project.entity.Slabs;
import com.slabBased.project.repository.BillRepository;
import com.slabBased.project.repository.SlabPeriodRepository;

import com.slabBased.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillServiceImplTest {
    @Mock
    BillRepository billRepository;
    @Mock
    SlabPeriodRepository slabPeriodRepository;
    @Mock
    UserRepository userRepository;


    BillServiceImpl billService;

    @BeforeEach
    void setUp() {
        this.billService = new BillServiceImpl(this.billRepository, this.userRepository, this.slabPeriodRepository);
    }

    @Test
    void addSlabPeriodTest() {
        Date fDate= new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime();
        Date tDate= new GregorianCalendar(2022, Calendar.APRIL, 11).getTime();
        SlabPeriod slabPeriod = new SlabPeriod(15L,fDate,tDate);
        when(slabPeriodRepository.save(any(SlabPeriod.class))).thenReturn(slabPeriod);
        billService.addSlabPeriod(slabPeriod);
        verify(slabPeriodRepository, times(1)).save(slabPeriod);
        assertEquals("SLAB PERIOD CREATED",billService.addSlabPeriod(slabPeriod));
    }

    @Test
    void addSlabTest() {
        Slabs slabs = new Slabs();
        SlabPeriod slabPeriod = new SlabPeriod();
        when(slabPeriodRepository.findAllById(1L)).thenReturn(slabPeriod);
       when(slabPeriodRepository.save(any(SlabPeriod.class))).thenReturn(slabPeriod);
        String callingAddSlabMethodFromBillService=billService.addSlab(1L, slabs);
        verify(slabPeriodRepository, times(1)).findAllById(1L);

        assertEquals("Slab Range And Rate Created",callingAddSlabMethodFromBillService);

    }

    @Test
    void getAllSlabPeriodTest() {
        billService.getAllSlabPeriod();
        verify(slabPeriodRepository).findAll();
    }

    @Test
    void billCalculatorTest() {
        Date fDate= new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime();
        Bill bill=new Bill(fDate,255.0,15L);
      billService.billCalculator(bill);
        verify(billRepository).getLastBillDetails(bill.getUserId());
        verify(slabPeriodRepository , times(1)).findAll();
      assertEquals("Slab Rate not yet created",billService.billCalculator(bill));
    }

    @Test
    void getAllBillsByUserIdTest() {
        billService.getAllBillsByUserId(63L);
        verify(billRepository).findAllByUserId(63L);
    }

    @Test
    void getLastBillOfCurrentUserTest() {
        billService.getLastBillOfCurrentUser(62L);
        verify(billRepository).getLastBillDetails(62L);
    }

    @Test
    void getBillDetailsByBillIdTest() {
        billService.getBillDetailsByBillId(617L);
        verify(billRepository).findById(617L);
    }
}