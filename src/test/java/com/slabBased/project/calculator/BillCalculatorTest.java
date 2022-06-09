package com.slabBased.project.calculator;

import com.slabBased.project.utils.BillCalculatorUtils;
import org.junit.jupiter.api.Test;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class BillCalculatorTest {
    Date fDate= new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime();
    Date tDate= new GregorianCalendar(2022, Calendar.APRIL, 11).getTime();
    Date cDate= new GregorianCalendar(2022, Calendar.MARCH, 11).getTime();

    BillCalculatorUtils bc= new BillCalculatorUtils(fDate,tDate,cDate);



    @Test
    void testIfDateIsWithinRange() {
       assertTrue(bc.isWithinRange());

    }
    @Test
    void failTestIfDateIsOutsideRange() {
        assertFalse(bc.isWithinRange());

    }


}