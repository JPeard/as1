package com.example.jpeard.as1;

import android.test.ActivityInstrumentationTestCase2;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

/**
 * Created by jpeard on 1/27/16.
 */
public class DataLogTest extends ActivityInstrumentationTestCase2{
    public  DataLogTest() {super(LogViewingActivity.class);}

    public void testSetDate(){
        DataLog log = new DataLog();
        log.setDate("2012-01-01");

        assertEquals(log.getDate(), new SimpleDateFormat("yyyy-mm-dd").parse("2012-01-01", new ParsePosition(0)).toString());
    }

    public void testSetStation(){
        DataLog log = new DataLog();
        log.setStation("Costco");

        assertEquals(log.getStation(),"Costco");
    }

    public void testSetOdometer(){
        DataLog log = new DataLog();
        log.setOdometer(20.2);

        assertEquals(log.getOdometer(),20.2);
    }


    public void testSetFuelGrade(){
        DataLog log = new DataLog();
        log.setFuel_grade("premium");

        assertEquals(log.getFuel_grade(),"premium");
    }

    public void testSetFuelAmount(){
        DataLog log = new DataLog();
        log.setFuel_amount(12.45);

        assertEquals(log.getFuel_amount(), 12.45);
    }

    public void testSetUnitPrice(){
        DataLog log = new DataLog();
        log.setFuel_amount(12.45);
        log.setUnit_cost(80.7);

        assertEquals(log.getFuel_amount(), 12.45);
        assertEquals(log.getUnit_cost(), 80.7);
        assertEquals(log.getFuel_cost(), 80.7*12.45);
    }
}
