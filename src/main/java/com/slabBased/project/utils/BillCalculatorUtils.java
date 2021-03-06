package com.slabBased.project.utils;


import java.util.Date;


public class BillCalculatorUtils {
   Date fromDate, toDate, currDate;

    Double prevRead;
    Double curRead;
    Double netUnit=0.0;
    Double startRead;
    Double endRead;
    Double slabValue;

    public BillCalculatorUtils(Double startRead, Double endRead, Double curRead, Double prevRead) {
        this.curRead = curRead;
        this.startRead = startRead;
        this.endRead = endRead;
        this.prevRead = prevRead;
    }

    public BillCalculatorUtils(Double slabValue) {

        this.slabValue = slabValue;
    }

    public BillCalculatorUtils(Date fromDate, Date toDate, Date currDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.currDate = currDate;
    }


    public Double netUnitCalc(Double curRead,Double prevRead) {
        netUnit = curRead - prevRead;

        return netUnit;
    }

    public boolean isWithinRange() {


        return !(currDate.before(fromDate) || currDate.after(toDate));
    }


    public boolean slabCheck() {


        return ((startRead <= netUnit) && (netUnit <= endRead));


    }


    public Double billGenerator(Double netUnit,Double slabValue) {

        return (netUnit * slabValue);


    }

}
