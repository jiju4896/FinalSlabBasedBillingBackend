package com.slabBased.project.calculator;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class BillCalculatorTest {
    Date fDate= new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime();
    Date tDate= new GregorianCalendar(2022, Calendar.APRIL, 11).getTime();
    Date cDate= new GregorianCalendar(2022, Calendar.MARCH, 11).getTime();

    BillCalculator bc= new BillCalculator(fDate,tDate,cDate);



    @Test
    void testIfDateIsWithinRange() {
       assertEquals(true,bc.isWithinRange());

    }
    @Test
    void testIfDateIsOutsideRange() {
        assertEquals(false,bc.isWithinRange());

    }


}