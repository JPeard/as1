package com.example.jpeard.as1;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jpeard on 1/27/16.
 */
public class DataLog {
    private Date date;
    private String station;
    private Double odometer;
    private String fuel_grade;
    private Double fuel_amount;
    private Double unit_cost;
    private Double fuel_cost;

    //private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public DataLog(){

    }

    public DataLog(String date, String station, Double odometer, String fuel_grade, Double fuel_amount, Double unit_cost) {
        this.date = new SimpleDateFormat("yyyy-mm-dd").parse(date,new ParsePosition(0));
        this.station = station;
        this.odometer = odometer;
        this.fuel_grade = fuel_grade;
        this.fuel_amount = fuel_amount;
        this.unit_cost = unit_cost;
        this.fuel_cost = this.fuel_amount*this.unit_cost;}

    public String getDate() {
        return new SimpleDateFormat("yyyy-mm-dd").format(date);
    }

    public String getStation() {
        return station;
    }

    public Double getOdometer() {
        return odometer;
    }

    public String getFuel_grade() {
        return fuel_grade;
    }

    public Double getFuel_amount() {
        return fuel_amount;
    }

    public Double getUnit_cost() {
        return unit_cost;
    }

    public Double getFuel_cost() {
        return fuel_cost;
    }

    public void setDate(String date) {
        this.date = new SimpleDateFormat("yyyy-mm-dd").parse(date,new ParsePosition(0));
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setOdometer(Double odometer) {
        this.odometer = odometer;
    }

    public void setFuel_grade(String fuel_grade) {
        this.fuel_grade = fuel_grade;
    }

    public void setFuel_amount(Double fuel_amount) {
	// Sets the amount of fuel purchased and updates
	// the calculated fuel costs
        this.fuel_amount = fuel_amount;
        if (this.unit_cost == null){
            this.fuel_cost = 0.0;
        }
        else{
            this.fuel_cost = this.fuel_amount*this.unit_cost;
        }
    }

    public void setUnit_cost(Double unit_cost) {
	// Sets the cost of each unit of fuel purchased
	// and updates the calculated fuel costs
        this.unit_cost = unit_cost;
        if (this.fuel_amount == null){
            this.fuel_cost = 0.0;
        }
        else{
            this.fuel_cost = this.fuel_amount*this.unit_cost;
        }
    }

}

